/*
 * ============================================================================
 * COPYRIGHT
 *              Pax CORPORATION PROPRIETARY INFORMATION
 *   This software is supplied under the terms of a license agreement or
 *   nondisclosure agreement with Pax Corporation and may not be copied
 *   or disclosed except in accordance with the terms in that agreement.
 *      Copyright (C) 2016 - ? Pax Corporation. All rights reserved.
 * Module Date: 2016-12-1
 * Module Author: Steven.W
 * Description:
 *
 * ============================================================================
 */
package com.paxus.view.dialog;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class OptAnimationLoader {

    private OptAnimationLoader() {
        //do nothing
    }

    public static Animation loadAnimation(Context context, int id) throws Resources.NotFoundException {

        try (XmlResourceParser parser = context.getResources().getAnimation(id)) {
            return createAnimationFromXml(context, parser);
        } catch (XmlPullParserException | IOException ex) {
            Resources.NotFoundException rnf = new Resources.NotFoundException("Can't load animation resource ID #0x"
                    + Integer.toHexString(id));
            rnf.initCause(ex);
            throw rnf;
        }
    }

    private static Animation createAnimationFromXml(Context c, XmlPullParser parser) throws XmlPullParserException,
            IOException {

        return createAnimationFromXml(c, parser, null, Xml.asAttributeSet(parser));
    }

    private static Animation createAnimationFromXml(Context c, XmlPullParser parser, AnimationSet parent,
                                                    AttributeSet attrs) throws XmlPullParserException, IOException {

        Animation anim = null;

        // Make sure we are on a start tag.
        int type;
        int depth = parser.getDepth();

        final String set = "set";
        final String alpha = "alpha";
        final String scale = "scale";
        final String rotate = "rotate";
        final String translate = "translate";

        while (((type = parser.next()) != XmlPullParser.END_TAG || parser.getDepth() > depth)
                && type != XmlPullParser.END_DOCUMENT) {

            if (type != XmlPullParser.START_TAG) {
                continue;
            }

            String name = parser.getName();

            switch (name) {
                case set:
                    anim = new AnimationSet(c, attrs);
                    createAnimationFromXml(c, parser, (AnimationSet) anim, attrs);
                    break;
                case alpha:
                    anim = new AlphaAnimation(c, attrs);
                    break;
                case scale:
                    anim = new ScaleAnimation(c, attrs);
                    break;
                case rotate:
                    anim = new RotateAnimation(c, attrs);
                    break;
                case translate:
                    anim = new TranslateAnimation(c, attrs);
                    break;
                default:
                    anim = genAnimFromClass(c, parser, attrs);
                    break;
            }

            if (parent != null && anim != null) {
                parent.addAnimation(anim);
            }
        }

        return anim;

    }

    private static Animation genAnimFromClass(Context c, XmlPullParser parser, AttributeSet attrs) {
        Animation anim = null;
        try {
            anim = (Animation) Class.forName(parser.getName()).getConstructor(Context.class, AttributeSet.class)
                    .newInstance(c, attrs);
        } catch (Exception te) {
            Log.d("OptAnimationLoader", "", te);
        }
        return anim;
    }
}
