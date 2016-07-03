package simple.myapplication;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

    TextView tv,tv2;
    LinearLayout ll;
    ImageView iv;
    Test test=new Test();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv=(TextView)findViewById(R.id.textView);
        tv2=(TextView)findViewById(R.id.textView2);
        ll=(LinearLayout)findViewById(R.id.ll);
        iv=(ImageView)findViewById(R.id.image);
//        tv.setText("hfggfgagh");
        Log.i("gssssssssssssssssssssss", "onCreate: hahhahhahahah");
    }

    public void do1(View view) {
        Bitmap bmp=getBitmapFromView(ll);
        int[] x = new int[bmp.getWidth()*bmp.getHeight()];
        bmp.getPixels(x,0,bmp.getWidth(),0,0,bmp.getWidth(),bmp.getHeight());
        long time=System.currentTimeMillis();
        test.opArr1(x);
        tv2.setText("java:"+x[0]+",time:"+(System.currentTimeMillis()-time));
        time=System.currentTimeMillis();
        test.opArr(x);
        tv.setText("natve:"+x[0]+",time:"+(System.currentTimeMillis()-time));
        bmp.setPixels(x,0,bmp.getWidth(),0,0,bmp.getWidth(),bmp.getHeight());
        iv.setImageBitmap(bmp);
    }


    private Bitmap getBitmapFromView(View view) {
        Bitmap bitmap = null;
        try {
            int width = view.getWidth();
            int height = view.getHeight();
            if(width != 0 && height != 0){
                bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(bitmap);
                view.layout(0, 0, width, height);
                view.draw(canvas);
            }
        } catch (Exception e) {
            bitmap = null;
            e.getStackTrace();
        }
        return bitmap;
    }
}
