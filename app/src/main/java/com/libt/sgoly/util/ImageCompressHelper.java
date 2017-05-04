package com.libt.sgoly.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ImageCompressHelper {

    private String tempFileCompressPath;//临时压缩保存路径
    private File tempFileCompress;//临时压缩file
    /**
     * 单例模式
     */
    static ImageCompressHelper instance;//句柄
    private  ImageCompressHelper(){
    }
    public static ImageCompressHelper getInstance(){
        if(instance==null) instance=new ImageCompressHelper();
        return instance;
    }
    /**
     * 压缩图片
     * @param context
     * @return
     */
    public String compressIMG(Context context, String sourcePath){
        ImageCompressUtil compress = new ImageCompressUtil();
        ImageCompressUtil.CompressOptions options = new ImageCompressUtil.CompressOptions();
        options.uri = Uri.fromFile(new File(sourcePath));
        Bitmap bitmap = compress.compressFromUri(context, options);
        if (bitmap!=null) {
            saveBitmap(bitmap);
            return tempFileCompressPath;
        } else {
            return null;
        }
    }

    /**
     * 保存方法
     */
    public void saveBitmap(Bitmap bitmap) {
        //指定调用相机拍照后照片的储存路径
        tempFileCompressPath = getTempPhotoFileName();
        tempFileCompress = new File(tempFileCompressPath);
        if (tempFileCompress.exists()) {
            tempFileCompress.delete();
        }
        try {
            FileOutputStream out = new FileOutputStream(tempFileCompress);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 使用系统当前日期加以调整作为照片的名称
     */
    private String getTempPhotoFileName() {
        //创建一个以当前时间为名称的文件
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("'IMG'_yyyyMMdd_HHmmss");
        return Environment.getExternalStorageDirectory() + "/" + dateFormat.format(date) + ".jpg";
    }
}
