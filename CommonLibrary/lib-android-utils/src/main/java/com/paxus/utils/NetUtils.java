package com.paxus.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.LinkAddress;
import android.net.LinkProperties;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.RouteInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.annotation.RequiresPermission;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.SocketException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

/**
 * Created by Yanina.Yang on 6/26/2020.
 */
public class NetUtils {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static String getAddressFromLinkProperties(LinkProperties linkProperties) {
        try {
            for (LinkAddress linkAddress : linkProperties.getLinkAddresses()) {
                InetAddress address = linkAddress.getAddress();
                if (address instanceof Inet4Address) {
                    return address.getHostAddress();
                }
            }
        } catch (Exception e) {
            //Ignore it
        }
        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static String getNetMaskFromLinkProperties(LinkProperties linkProperties) {
        try {
            for (LinkAddress linkAddress : linkProperties.getLinkAddresses()) {
                InetAddress address = linkAddress.getAddress();
                if (address instanceof Inet4Address) {
                    int prefixLength = linkAddress.getPrefixLength();
                    return getNetmaskFromPrefixLength(prefixLength);
                }
            }
        } catch (Exception e) {
            //Ignore it
        }
        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static String[] getDNSFromLinkProperties(LinkProperties linkProperties) {
        try {
            List<String> dns = new ArrayList<>();
            for (InetAddress address : linkProperties.getDnsServers()) {
                dns.add(address.getHostAddress());
            }
            return dns.toArray(new String[0]);
        } catch (Exception e) {
            //Ignore it
        }
        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static String getGatewayFromLinkProperties(LinkProperties linkProperties) {
        try {
            List<String> gateway = new ArrayList<>();
            for (RouteInfo routeInfo : linkProperties.getRoutes()) {
                InetAddress address = routeInfo.getGateway();
                if (address instanceof Inet4Address) {
                    gateway.add(routeInfo.getGateway().getHostAddress());
                }
            }
            if (gateway.size() > 0) {
                return gateway.get(0);
            }
        } catch (Exception e) {
            //Ignore it
        }
        return null;
    }

    private static String getNetmaskFromPrefixLength(int prefixLength) {
        int[] masks = new int[]{0, 0, 0, 0};
        for (int i = 0; i < masks.length; i++) {
            int n = prefixLength - i * 8;
            if (n > 0) {
                if (n > 8) {
                    n = 8;
                }
                masks[i] += (0xFF << (8 - n)) & 0xFF;
            }
        }
        return String.format(Locale.US, "%d.%d.%d.%d", masks[0], masks[1], masks[2], masks[3]);
    }


    public static String getIpAddress(Context context) {
        String address = null;
        ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(
                Context.CONNECTIVITY_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && mConnectivityManager != null) {
            LinkProperties linkProperties = null;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                Network network = mConnectivityManager.getActiveNetwork();
                linkProperties = mConnectivityManager.getLinkProperties(network);
                address = getAddressFromLinkProperties(linkProperties);
            } else {
                Network[] networks = mConnectivityManager.getAllNetworks();
                for (Network network : networks) {
                    linkProperties = mConnectivityManager.getLinkProperties(network);
                    address = getAddressFromLinkProperties(linkProperties);
                    if (address != null && !address.isEmpty()) {
                        break;
                    }
                }
            }
        } else {
            try {
                ArrayList<NetworkInterface> nilist = Collections.list(NetworkInterface.getNetworkInterfaces());
                for (NetworkInterface ni : nilist) {
                    ArrayList<InetAddress> ialist = Collections.list(ni.getInetAddresses());
                    for (InetAddress inetAddress : ialist) {
                        if (!inetAddress.isLoopbackAddress() && !inetAddress.isLinkLocalAddress() && inetAddress instanceof Inet4Address) {
                            address = inetAddress.getHostAddress();
                        }
                    }
                }
            } catch (SocketException e) {
                //Ignore it
            }
        }

        if (address == null) {

            WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);

            if (wifiManager == null || !wifiManager.isWifiEnabled()) {
                return "0.0.0.0";
//                wifiManager.setWifiEnabled(true);
            }
            WifiInfo wifiInfo = wifiManager.getConnectionInfo();
            int ipAddress = wifiInfo.getIpAddress();

            address = formatIpAddress(ipAddress);
        }

        // IPv6 address will not be shown like WifiInfo internally does.
        return address;
    }

    @RequiresPermission(android.Manifest.permission.ACCESS_NETWORK_STATE)
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static String getNetMask(Context context) {
        String mask = null;
        ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(
                Context.CONNECTIVITY_SERVICE);

        if (mConnectivityManager != null) {
            LinkProperties linkProperties = null;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                Network network = mConnectivityManager.getActiveNetwork();
                linkProperties = mConnectivityManager.getLinkProperties(network);
                mask = getNetMaskFromLinkProperties(linkProperties);
            } else {
                Network[] networks = mConnectivityManager.getAllNetworks();
                for (Network network : networks) {
                    linkProperties = mConnectivityManager.getLinkProperties(network);
                    mask = getNetMaskFromLinkProperties(linkProperties);
                    if (mask != null && !mask.isEmpty()) {
                        break;
                    }
                }
            }
        }

        return mask;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static String[] getDNS(Context context) {
        String[] dns = null;
        ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(
                Context.CONNECTIVITY_SERVICE);

        if (mConnectivityManager != null) {
            LinkProperties linkProperties = null;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                Network network = mConnectivityManager.getActiveNetwork();
                linkProperties = mConnectivityManager.getLinkProperties(network);
                dns = getDNSFromLinkProperties(linkProperties);
            } else {
                Network[] networks = mConnectivityManager.getAllNetworks();
                for (Network network : networks) {
                    linkProperties = mConnectivityManager.getLinkProperties(network);
                    dns = getDNSFromLinkProperties(linkProperties);
                    if (dns != null && dns.length > 0) {
                        break;
                    }
                }
            }
        }

        return dns;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static String getGateway(Context context) {
        String gateway = null;
        ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(
                Context.CONNECTIVITY_SERVICE);

        if (mConnectivityManager != null) {
            LinkProperties linkProperties = null;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                Network network = mConnectivityManager.getActiveNetwork();
                linkProperties = mConnectivityManager.getLinkProperties(network);
                gateway = getGatewayFromLinkProperties(linkProperties);
            } else {
                Network[] networks = mConnectivityManager.getAllNetworks();
                for (Network network : networks) {
                    linkProperties = mConnectivityManager.getLinkProperties(network);
                    gateway = getGatewayFromLinkProperties(linkProperties);
                    if (gateway != null && !gateway.isEmpty()) {
                        break;
                    }
                }
            }
        }

        return gateway;
    }


    public static String formatIpAddress(int ipAdress) {

        return (ipAdress & 0xFF) + "." +
                ((ipAdress >> 8) & 0xFF) + "." +
                ((ipAdress >> 16) & 0xFF) + "." +
                (ipAdress >> 24 & 0xFF);
    }

    public static boolean isAvailablePort(int port) {
        boolean result = true;
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(port);
            serverSocket.close();
        } catch (IOException e) {
            result = false;
        }
        return result;
    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (info != null && info.isConnected()) {
                return info.getState() == NetworkInfo.State.CONNECTED;
            }
        }
        return false;
    }

    public static boolean ping(String url) throws Exception {

        try {
            //make a URL to a known source
            URL surl = new URL("http://" + url);

            //open a connection to that source
            HttpURLConnection urlConnect = (HttpURLConnection) surl.openConnection();

            //trying to retrieve data from the source. If there
            //is no connection, this line will fail
            Object objData = urlConnect.getContent();
            return objData != null;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
