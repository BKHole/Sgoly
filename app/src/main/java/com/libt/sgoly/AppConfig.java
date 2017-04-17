package com.libt.sgoly;

/**
 * 配置文件
 * Created by Administrator on 2017/4/11 0011.
 */

public class AppConfig {
    /**
     * 这里的有几个缓存路径
     * 一个是KJHTTP 和 KJBITMAP 他们都是 SD下 Naidou文件夹下
     * 二个是拍照时候的图片缓存 也是改为了 SD下 Naidou文件夹下
     * 三个是picasso的缓存路径 是默认的context下的cache里面的
     */
    public static String saveFolder = "/Sgoly";
    public static String httpCachePath = saveFolder + "/httpCache";
}
