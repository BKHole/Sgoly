package com.libt.sgoly.fragment;

import org.kymjs.kjframe.ui.SupportFragment;
import org.kymjs.kjframe.utils.FileUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public abstract class BaseSupportFragment extends SupportFragment {

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

//    public void addGuideImage(int resourceId,int xmlId) {
//        Activity aty = getActivity();
//        if(! PreferenceHelper.readBoolean(aty, aty.getClass().getSimpleName(), "first_open",true)){
//            //如果不是第一次 就返回
//            return;
//        }
//        PreferenceHelper.write(aty,aty.getClass().getSimpleName(),"first_open",true);
//        //找到跟布局view
//        View view = aty.getWindow().getDecorView().findViewById(xmlId);
//        if(view==null)return;
//        //判断是否是第一次进入APP
//
//
//        //获取上一层的framelayout
//        ViewParent viewParent = view.getParent();
//        if(viewParent instanceof FrameLayout){
//            final FrameLayout frameLayout = (FrameLayout)viewParent;
//            final ImageView guideImage = new ImageView(aty);
//            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT);
//            guideImage.setLayoutParams(params);
//            guideImage.setScaleType(ImageView.ScaleType.FIT_XY);
//            guideImage.setImageResource(resourceId);
//            guideImage.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    frameLayout.removeView(guideImage);
//                }
//            });
//            frameLayout.addView(guideImage);//添加引导图片
//
//        }
//    }

}
