package com.union.bangbang.zero.util;

import android.app.Activity;
import android.content.ContentProvider;
import android.content.SharedPreferences;

import com.union.bangbang.zero.BuildConfig;

import java.util.Set;

import static android.content.Context.MODE_PRIVATE;

/**
 * 不乱于心，不困于情。不畏将来，不念过往。如此，安好!
 * <p>
 * 深谋若谷，深交若水。深明大义，深悉小节。已然，静舒!
 * <p>
 * 善宽以怀，善感以恩。善博以浪，善精以业。这般，最佳!
 * <p>
 * 勿感于时，勿伤于怀。勿耽美色，勿沉虚妄。从今，进取!
 * <p>
 * 无愧于天，无愧于地。无怍于人，无惧于鬼。这样，人生!
 */
public class SharedPreferencesUtil {
    // TODO: 2019-02-06 😡记得使用 ContentProvider 解决多线程的问题
//    ContentProvider

    private static SharedPreferences sharedPreferences;

//    private void save(Activity activity, String key, Object value) {
//        if (sharedPreferences == null)
//            sharedPreferences = activity.getSharedPreferences(BuildConfig.APPLICATION_ID, MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();//获取编辑器
//        if (value instanceof String) {
//            editor.putString(key, (String) value);
//        } else if (value instanceof Integer) {
//            editor.putInt(key, (Integer) value);
//        } else if (value instanceof Long) {
//            editor.putLong(key, (Long) value);
//        } else if (value instanceof Float) {
//            editor.putFloat(key, (Float) value);
//        } else if (value instanceof Boolean) {
//            editor.putBoolean(key, (Boolean) value);
//        }
//        editor.apply();
//    }

//    private <T> T save(Activity activity, String key, Class<T> clazz) {
//        if (sharedPreferences == null)
//            sharedPreferences = activity.getSharedPreferences(BuildConfig.APPLICATION_ID, MODE_PRIVATE);
//        if (clazz == String.class) {
//           return (T)sharedPreferences.getString(key, null);
//        } else if (clazz == Integer.class) {
//            return (T)  sharedPreferences.getInt(key, 0);
//        } else if (clazz == Long.class) {
//            sharedPreferences.getLong(key, 0);
//        } else if (clazz == Float.class) {
//            sharedPreferences.getFloat(key, 0);
//        } else if (clazz == Boolean.class) {
//            sharedPreferences.getBoolean(key, false);
//        }
//    }
}
