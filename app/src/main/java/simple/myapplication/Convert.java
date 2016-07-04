package simple.myapplication;

/**
 * Created by noting on 16-7-2.
 */
import android.graphics.*;
import android.widget.*;
import android.util.*;

public class Convert
{
    //public static Bitmap reSize(Bitmap bmp,int w,int h){

    //}
    public static Bitmap convertGreyImg(Bitmap img) {
        int width = img.getWidth();         //获取位图的宽
        int height = img.getHeight();       //获取位图的高

        int []pixels = new int[width * height]; //通过位图的大小创建像素点数组

        img.getPixels(pixels, 0, width, 0, 0, width, height);
        int alpha = 0xFF << 24;
        for(int i = 0; i < height; i++)  {
            for(int j = 0; j < width; j++) {
                int grey = pixels[width * i + j];

                int red = ((grey  & 0x00FF0000 ) >> 16);
                int green = ((grey & 0x0000FF00) >> 8);
                int blue = (grey & 0x000000FF);

                grey = (int)((float) red * 0.3 + (float)green * 0.59 + (float)blue * 0.11);
                grey = alpha | (grey << 16) | (grey << 8) | grey;
                pixels[width * i + j] = grey;
            }
        }
        Bitmap result = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        result.setPixels(pixels, 0, width, 0, 0, width, height);
        return result;
    }
    public static void preDo(Bitmap img1,Bitmap img2){

        int width1 = img1.getWidth();         //获取位图的宽
        int height1 = img1.getHeight();       //获取位图的高
        int width2 = img2.getWidth();         //获取位图的宽
        int height2 = img2.getHeight();       //获取位图的高
        int mw=width1<width2?width1:width2;
        int mh=height1<height2?height1:height2;
        int []pixels1 = new int[mw * mh]; //通过位图的大小创建像素点数组
        int []pixels2 = new int[mw * mh]; //通过位图的大小创建像素点数组

        img1.getPixels(pixels1, 0, mw, 0, 0, mw, mh);
        img2.getPixels(pixels2, 0, mw, 0, 0, mw, mh);
        for(int i = 0; i < mh; i++)  {
            for(int j = 0; j < mw; j++) {
                int g1 = pixels1[mw * i + j]&0xff;
                int g2 = pixels2[mw * i + j]&0xff;
                g1=g1/2+128;
                g2/=2;
                g1 = 0xff<<24|(g1 << 16) | (g1 << 8) | g1;
                pixels1[mw * i + j] = g1;
                g2 = 0xff<<24|(g2 << 16) | (g2 << 8) | g2;
                pixels2[mw * i + j] = g2;
            }
        }
        // Bitmap result = Bitmap.createBitmap(width1, height1, Bitmap.Config.ARGB_8888);
        img1.setPixels(pixels1, 0, mw, 0, 0, mw, mh);
        Log.d("width","old:"+width1+",new:"+img1.getWidth());
        img2.setPixels(pixels2, 0, mw, 0, 0, mw, mh);
    }
    public static Bitmap combImg(Bitmap img1,Bitmap img2) {
        final int white=0xff;
        int width1 = img1.getWidth();         //获取位图的宽
        int height1 = img1.getHeight();       //获取位图的高
        //int width2 = img2.getWidth();         //获取位图的宽
        //int height2 = img2.getHeight();       //获取位图的高
        int []pixels1 = new int[width1 * height1]; //通过位图的大小创建像素点数组
        int []pixels2 = new int[width1 * height1]; //通过位图的大小创建像素点数组

        img1.getPixels(pixels1, 0, width1, 0, 0, width1, height1);
        img2.getPixels(pixels2, 0, width1, 0, 0, width1, height1);
        int alpha = 0;
        for(int i = 0; i < height1; i++)  {
            for(int j = 0; j < width1; j++) {
                int g1 = pixels1[width1 * i + j]&0xff;
                int g2 = pixels2[width1 * i + j]&0xff;
                alpha=white-(g1-g2);
                if(alpha<0){
                    alpha=0xff;
                }
                else if(alpha!=0){
                    g1=(g2*white)/alpha;
                }
                //grey = (int)((float) red * 0.3 + (float)green * 0.59 + (float)blue * 0.11);
                g1 = (alpha<<24) | (g1 << 16) | (g1 << 8) | g1;
                pixels1[width1 * i + j] = g1;
            }
        }
        Bitmap result = Bitmap.createBitmap(width1, height1, Bitmap.Config.ARGB_8888);
        result.setPixels(pixels1, 0, width1, 0, 0, width1, height1);
        return result;
    }
}