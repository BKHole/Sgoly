package com.libt.sgoly.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.ImageView;

import org.kymjs.kjframe.KJActivity;
import org.kymjs.kjframe.utils.FileUtils;
import org.kymjs.kjframe.utils.PreferenceHelper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public abstract class BaseActivity extends AppCompatActivity {

    public Activity aty;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        aty = this;
        super.onCreate(savedInstanceState);
    }

    public void addGuideImage(int resourceId, String tag, int xmlId) {
        boolean isFirst = PreferenceHelper.readBoolean(this,tag , "first_open",true);
        if(!isFirst ){
            //如果不是第一次 就返回
            return;
        }
        PreferenceHelper.write(this,tag,"first_open",false);
        //找到跟布局view
        View view = getWindow().getDecorView().findViewById(xmlId);
        if(view==null)return;
        //判断是否是第一次进入APP


        //获取上一层的framelayout
        ViewParent viewParent = view.getParent();
        if(viewParent instanceof FrameLayout){
            final FrameLayout frameLayout = (FrameLayout)viewParent;
            final ImageView guideImage = new ImageView(this);
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT);
            guideImage.setLayoutParams(params);
            guideImage.setScaleType(ImageView.ScaleType.FIT_XY);
            guideImage.setImageResource(resourceId);
            guideImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    frameLayout.removeView(guideImage);
                }
            });
            frameLayout.addView(guideImage);//添加引导图片

        }


    }

    /**
     * 添加 根布局的一张引导图层
     * 使用条件：需要在activity的content xml设置一个id
     * @param resourceId 图层资源ID
     * @param xmlId xml id
     */
    public void addGuideImage(int resourceId,int xmlId) {
        String tag = aty.getClass().getSimpleName();
       addGuideImage(resourceId,tag,xmlId);
    }


    /**
     * 写入本地缓存文件
     * @param t
     * @param folder
     * @param fileName
     */
    public void writeToLocal(String t,String folder,String fileName) {

        //只储存  最新一页的缓存数据
        File file = FileUtils.getSaveFile(folder, fileName);
        FileWriter writer = null;
        try {
            writer = new FileWriter(file);
            writer.write(t.trim());
            writer.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * 读取本地缓存文件
     * @param folder
     * @param fileName
     * @return
     */
    public String getFromLocal(String folder,String fileName){
        File file = FileUtils.getSaveFile( folder, fileName);
        char[] buffer = new char[1024];
        StringBuilder builder = new StringBuilder();
        String  data ;
        BufferedReader br = null;
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(file);
            br = new BufferedReader(fileReader);
            try {
                while ((data = br.readLine()) != null) {
                    builder.append(data);
                }
                return builder.toString();

            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }



}
