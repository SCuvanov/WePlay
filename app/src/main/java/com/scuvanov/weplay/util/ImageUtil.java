package com.scuvanov.weplay.util;

import android.content.Context;

public class ImageUtil {

    public static int getImageId(Context context, String imageName) {
        return context.getResources().getIdentifier("drawable/" + imageName, null, context.getPackageName());
    }

}
