package simple.myapplication;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

    TextView tv,tv2;
    Test test=new Test();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv=(TextView)findViewById(R.id.textView);
        tv2=(TextView)findViewById(R.id.textView2);
//        tv.setText("hfggfgagh");
        Log.i("gssssssssssssssssssssss", "onCreate: hahhahhahahah");
    }

    public void do1(View view) {
        int[] x=new int[10000000];
        long time=System.currentTimeMillis();
        test.opArr(x);
        tv.setText("natve:"+x[10]+",time:"+(System.currentTimeMillis()-time));
        time=System.currentTimeMillis();
        test.opArr1(x);
        tv2.setText("java:"+x[10]+",time:"+(System.currentTimeMillis()-time));

    }
}
