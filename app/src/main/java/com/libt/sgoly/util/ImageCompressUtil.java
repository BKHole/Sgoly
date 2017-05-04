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
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore.Images;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * 图片压缩工具类
 *
 * Created by gc on 2015/9/13.
 *
 * ImageCompress compress = new ImageCompress();
 *        ImageCompress.CompressOptions options = new ImageCompress.CompressOptions();
 *        options.uri = Uri.fromFile(new File(sourcePath));
 *        options.maxWidth=Constants.RESIZEBITMAP_WIDTH;
 *        options.maxHeight=Constants.RESIZEBITMAP_HEIGHT;
 *        Bitmap bitmap = compress.compressFromUri(UploadWithPhotoBaseActivity.this, options);
 */
public class ImageCompressUtil {
    public static final String CONTENT = "content";
    public static final String FILE = "file";

    /**
     * 图片压缩参数
     */
    public static class CompressOptions {
        public static final int DEFAULT_WIDTH = 720;
        public static final int DEFAULT_HEIGHT = 1280;
        public int maxWidth = DEFAULT_WIDTH;
        public int maxHeight = DEFAULT_HEIGHT;
        public File destFile; //压缩后图片保存的文件
        public CompressFormat imgFormat = CompressFormat.JPEG; //图片压缩格式,默认为jpg格式
        public int quality = 30; //图片压缩比例 默认为30
        public Uri uri;
    }

    public Bitmap compressFromUri(Context context, CompressOptions compressOptions) {

        String filePath = getFilePath(context, compressOptions.uri); // URI指向的文件路径
        if (null == filePath) {
            return null;
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;

        Bitmap temp = BitmapFactory.decodeFile(filePath, options);

        int actualWidth = options.outWidth;
        int actualHeight = options.outHeight;

        int desiredWidth = getResizedDimension(compressOptions.maxWidth,
                compressOptions.maxHeight, actualWidth, actualHeight);
        int desiredHeight = getResizedDimension(compressOptions.maxHeight,
                compressOptions.maxWidth, actualHeight, actualWidth);

        options.inJustDecodeBounds = false;
        options.inSampleSize = findBestSampleSize(actualWidth, actualHeight,
                desiredWidth, desiredHeight);

        Bitmap bitmap = null;

        Bitmap destBitmap = BitmapFactory.decodeFile(filePath, options);

        if (destBitmap!=null) {
            // If necessary, scale down to the maximal acceptable size.
            if (destBitmap.getWidth() > desiredWidth
                    || destBitmap.getHeight() > desiredHeight) {
                bitmap = Bitmap.createScaledBitmap(destBitmap, desiredWidth,
                        desiredHeight, true);
                destBitmap.recycle();
            } else {
                bitmap = destBitmap;
            }

            // compress file if need
            if (null != compressOptions.destFile) {
                compressFile(compressOptions, bitmap);
            }
        }
        return bitmap;
    }

    /**
     * compress file from bitmap with compressOptions
     *
     * @param compressOptions
     * @param bitmap
     */
    private void compressFile(CompressOptions compressOptions, Bitmap bitmap) {
        OutputStream stream = null;
        try {
            stream = new FileOutputStream(compressOptions.destFile);
        } catch (FileNotFoundException e) {
            Log.e("ImageCompress", e.getMessage());
        }
        bitmap.compress(compressOptions.imgFormat, compressOptions.quality,
                stream);
    }

    private int findBestSampleSize(int actualWidth, int actualHeight,
                                          int desiredWidth, int desiredHeight) {
        double wr = (double) actualWidth / desiredWidth;
        double hr = (double) actualHeight / desiredHeight;
        double ratio = Math.min(wr, hr);
        float n = 1.0f;
        while ((n * 2) <= ratio) {
            n *= 2;
        }
        return (int) n;
    }

    private int getResizedDimension(int maxPrimary, int maxSecondary,
                                           int actualPrimary, int actualSecondary) {
        // If no dominant value at all, just return the actual.
        if (maxPrimary == 0 && maxSecondary == 0) {
            return actualPrimary;
        }

        // If primary is unspecified, scale primary to match secondary's scaling
        // ratio.
        if (maxPrimary == 0) {
            double ratio = (double) maxSecondary / (double) actualSecondary;
            return (int) (actualPrimary * ratio);
        }

        if (maxSecondary == 0) {
            return maxPrimary;
        }

        double ratio = (double) actualSecondary / (double) actualPrimary;
        int resized = maxPrimary;
        if (resized * ratio > maxSecondary) {
            resized = (int) (maxSecondary / ratio);
        }
        return resized;
    }

    /**
     * 获取文件的路径
     * @param context
     * @param uri
     * @return
     */
    private String getFilePath(Context context, Uri uri) {

        String filePath = null;
        if (CONTENT.equalsIgnoreCase(uri.getScheme())) {
            Cursor cursor = context.getContentResolver().query(uri,
                    new String[] { Images.Media.DATA }, null, null, null);
            if (null == cursor) {
                return null;
            }
            try {
                if (cursor.moveToNext()) {
                    filePath = cursor.getString(cursor.getColumnIndex(Images.Media.DATA));
                }
            } finally {
                cursor.close();
            }
        }
        // 从文件中选择
        if (FILE.equalsIgnoreCase(uri.getScheme())) {
            filePath = uri.getPath();
        }
        return filePath;
    }

}
