package com.pax.pay.ui.def.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.vectordrawable.graphics.drawable.Animatable2Compat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.paxus.utils.log.Logger;

/**
 */
public class GifLoadOneTimeGif {
    /**
     * Gif load
     *
     * @param context
     * @param model       gif path
     * @param imageView
     * @param loopCount   display times
     * @param gifListener Gif callback listener
     */
    public static void loadOneTimeGif(Context context, Object model, final ImageView imageView, int loopCount, final GifListener gifListener) {
        Logger.d( "loadOneTimeGif start " + model);
        Glide.get(context).clearMemory();
        Glide.with(context).asGif().load(model).fitCenter().addListener(new RequestListener<GifDrawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<GifDrawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(GifDrawable resource, Object model, Target<GifDrawable> target, DataSource dataSource, boolean isFirstResource) {

//                int delay = 60 * 1000;
//                try {
//                    Field gifStateField = GifDrawable.class.getDeclaredField("state");
//                    gifStateField.setAccessible(true);
//                    Class gifStateClass = Class.forName("com.bumptech.glide.load.resource.gif.GifDrawable$GifState");
//                    Field gifFrameLoaderField = gifStateClass.getDeclaredField("frameLoader");
//                    gifFrameLoaderField.setAccessible(true);
//                    Class gifFrameLoaderClass = Class.forName("com.bumptech.glide.load.resource.gif.GifFrameLoader");
//                    Field gifDecoderField = gifFrameLoaderClass.getDeclaredField("gifDecoder");
//                    gifDecoderField.setAccessible(true);
//                    Class gifDecoderClass = Class.forName("com.bumptech.glide.gifdecoder.GifDecoder");
//                    Object gifDecoder = gifDecoderField.get(gifFrameLoaderField.get(gifStateField.get(resource)));
//                    Method getDelayMethod = gifDecoderClass.getDeclaredMethod("getDelay", int.class);
//                    getDelayMethod.setAccessible(true);
//                    //Get total count of frame
//                    int count = resource.getFrameCount();
//                    delay = 0;
//                    for (int i = 0; i < (count - 1); i++) {
//                        //get duration time of each frame
//                        delay += (int) getDelayMethod.invoke(gifDecoder, i);
//                    }
//                } catch (IllegalAccessException e) {
//                    Logger.e(e);
//                } catch (NoSuchFieldException e) {
//                    Logger.e(e);
//                } catch (NoSuchMethodException e) {
//                    Logger.e(e);
//                } catch (ClassNotFoundException e) {
//                    Logger.e(e);
//                } catch (InvocationTargetException e) {
//                    Logger.e(e);
//                } finally {
//                    gifListener.gifPlayDuration(delay);
//                }

                resource.registerAnimationCallback(new Animatable2Compat.AnimationCallback() {
                    @Override
                    public void onAnimationEnd(Drawable drawable) {
                        Logger.d( "onAnimationEnd ");
                        resource.stop();
                        //super.onAnimationEnd(drawable);
                        gifListener.gifPlayComplete();
                        resource.unregisterAnimationCallback(this);
                    }
                });
                resource.setLoopCount(loopCount);
                resource.start();
                return false;
            }
        }).into(imageView);
    }

    /**
     * Gif callback Listener
     */
    public interface GifListener {
        void gifPlayComplete();

        //void gifPlayDuration(int duration);
    }

}
