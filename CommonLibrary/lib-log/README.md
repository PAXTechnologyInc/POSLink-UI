## log(android lib)
Adapter design log component, depends on com.orhanobut:logger
### build
to turn on/off output BroadPOS log through logcat in release build, please comment/uncomment public static int println(...) of class android.util.Log.</br>
e.g. for BroadPOS-FDRC</br>
proguard location: `BroadPOS-FDRC\FDRC\fdrc.app\proguard-rules.pro -> class android.util.Log.`</br>

Comment public static int println(...) to turn on output BroadPOS log through logcat in release, uncomment it to close.
