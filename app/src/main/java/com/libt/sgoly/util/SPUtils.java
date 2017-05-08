/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.libt.sgoly.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.util.Base64;
import android.widget.ImageView;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Map;

/**
 * API 9 及以上
 * Created by gc on 2016/11/6.
 */
public class SPUtils {

    private SPUtils() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 保存文件名
     */
    public static final String FILE_NAME = "config";

    /**
     * 保存数据的方法，根据类型调用不同的保存方法
     */
    public static void putString(Context context, String key, String value) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        sp.edit().putString(key, value).apply();//commit同步的，apply异步的
    }
    public static void putInt(Context context, String key, int value) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        sp.edit().putInt(key, value).apply();
    }
    public static void putBoolean(Context context, String key, boolean value) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        sp.edit().putBoolean(key, value).apply();
    }
    public static void putFloat(Context context, String key, float value) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        sp.edit().putFloat(key, value).apply();
    }
    public static void putLong(Context context, String key, long value) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        sp.edit().putLong(key, value).apply();
    }

    /**
     * 保存图片到SharedPreferences
     * @param mContext
     * @param imageView
     */
    public static void putImage(Context mContext, String key, ImageView imageView) {
        BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
        Bitmap bitmap = drawable.getBitmap();
        // 将Bitmap压缩成字节数组输出流
        ByteArrayOutputStream byStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 80, byStream);
        // 利用Base64将我们的字节数组输出流转换成String
        byte[] byteArray = byStream.toByteArray();
        String imgString = new String(Base64.encodeToString(byteArray, Base64.DEFAULT));
        // 将String保存shareUtils
        SPUtils.putString(mContext, key, imgString);
    }

    /**
     * 从SharedPreferences读取图片
     * @param mContext
     * @param imageView
     */
    public static Bitmap getImage(Context mContext, String key, ImageView imageView) {
        String imgString = SPUtils.getString(mContext, key, "");
        if (!imgString.equals("")) {
            // 利用Base64将我们string转换
            byte[] byteArray = Base64.decode(imgString, Base64.DEFAULT);
            ByteArrayInputStream byStream = new ByteArrayInputStream(byteArray);
            // 生成bitmap
            return BitmapFactory.decodeStream(byStream);
        }
        return null;
    }

    /**
     * 获取数据的方法，根据默认值得到数据的类型，然后调用对应方法获取值
     */
    public static String getString(Context context,String key,String defValue){
        SharedPreferences sp=context.getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE);
        return sp.getString(key,defValue);
    }
    public static int getInt(Context context,String key,int defValue){
        SharedPreferences sp=context.getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE);
        return sp.getInt(key,defValue);
    }
    public static boolean getBoolean(Context context,String key,boolean defValue){
        SharedPreferences sp=context.getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE);
        return sp.getBoolean(key,defValue);
    }
    public static float getFloat(Context context,String key,float defValue){
        SharedPreferences sp=context.getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE);
        return sp.getFloat(key,defValue);
    }public static long getLong(Context context,String key,long defValue){
        SharedPreferences sp=context.getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE);
        return sp.getLong(key,defValue);
    }

    /**
     * 移除某个key对应的值
     */
    public static void remove(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        sp.edit().remove(key).apply();
    }

    /**
     * 清除所有数据
     */
    public static void clear(Context context) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        sp.edit().clear().apply();
    }

    /**
     * 查询某个key是否已经存在
     */
    public static boolean contains(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        return sp.contains(key);
    }

    /**
     * 返回所有的键值对
     */
    public static Map<String, ?> getAll(Context context) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        return sp.getAll();
    }

}