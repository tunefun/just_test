package simple.myapplication;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import android.content.*;
import android.net.*;
import android.provider.*;
import android.database.*;
import android.graphics.*;
import java.io.*;
import android.util.*;

import simple.myapplication.R;


public class MainActivity extends Activity
{
    TextView []tv=new TextView[3];
    TextView timetv;
    ImageView mv1,mv2;
    String []pp=new String[3];
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv[1]=(TextView)findViewById(R.id.tv1);
        tv[2]=(TextView)findViewById(R.id.tv2);
        timetv=(TextView)findViewById(R.id.time);
        mv1=(ImageView)findViewById(R.id.mv1);
        mv2=(ImageView)findViewById(R.id.mv2);
        //tv1.setText("ok...");
        Log.d("cgghgg","start ok!!!!!!!");
    }
    public void pickPic1(View v){
        Intent i = new Intent(
                Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i, 1);
    }
    public void pickPic2(View v){
        Intent i = new Intent(
                Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i, 2);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode<=2){
            if(resultCode == RESULT_OK && null != data) {
                Uri selectedImage = data.getData();
                String[] filePathColumn = { MediaStore.Images.Media.DATA };

                Cursor cursor = getContentResolver().query(selectedImage,
                        filePathColumn, null, null, null);
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                String picturePath = cursor.getString(columnIndex);
                cursor.close();
                pp[requestCode]=picturePath;
                tv[requestCode].setText(picturePath);
                // String picturePath contains the path of selected Image
            }
        }
        else{

        }
    }
    public void comb(View v){
        long time=System.currentTimeMillis();
        Bitmap bmp1=BitmapFactory.decodeFile(pp[1]);
        Bitmap bmp2=BitmapFactory.decodeFile(pp[2]);
        //mv1.setImageBitmap(bmp1);
        long time1=System.currentTimeMillis();
        bmp1=Convert.convertGreyImg(bmp1);
        Log.i("纯java时间","java时间："+(System.currentTimeMillis()-time1));
        //mv1.setImageBitmap(bmp1);
        bmp2=Convert.convertGreyImg(bmp2);
        Convert.preDo(bmp1,bmp2);
        //mv1.setImageBitmap(bmp1);
        Bitmap bmp=Convert.combImg(bmp1,bmp2);
        mv1.setImageBitmap(bmp);
        save2Png(bmp,pp[1]+".png");
        timetv.setText("java:"+(System.currentTimeMillis()-time));
    }
    public void comb_c(View view) {
        long time=System.currentTimeMillis();
        Bitmap bmp1=BitmapFactory.decodeFile(pp[1]);
        Bitmap bmp2=BitmapFactory.decodeFile(pp[2]);
        //mv1.setImageBitmap(bmp1);
        long time1=System.currentTimeMillis();
        bmp1=Convert_C.convertGreyImg(bmp1);
        Log.i("java时间","java时间："+(System.currentTimeMillis()-time1));

        //mv1.setImageBitmap(bmp1);
        bmp2=Convert_C.convertGreyImg(bmp2);
        Convert_C.preDo(bmp1,bmp2);
        //mv1.setImageBitmap(bmp1);
        Bitmap bmp=Convert_C.combImg(bmp1,bmp2);
        mv2.setImageBitmap(bmp);
        save2Png(bmp,pp[1]+".png");
        timetv.setText("C:"+(System.currentTimeMillis()-time));
    }
    public void save2Png(Bitmap bmp,String fileName){
        File bitmapFile = new File(fileName);
        FileOutputStream bitmapWtriter = null;
        try {
            bitmapWtriter = new FileOutputStream(bitmapFile);
        }
        catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        bmp.compress(Bitmap.CompressFormat.PNG, 90, bitmapWtriter);
    }
    //调用系统的剪裁处理图片并保存至imageUri中
    public static void cropImageUri(Activity activity, Uri orgUri, int requestCode) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(orgUri, "image/*");
        intent.putExtra("crop", "true");
		/*intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);
		intent.putExtra("outputX", width);
		intent.putExtra("outputY", height);
		intent.putExtra("scale", true);
		//将剪切的图片保存到目标Uri中
		intent.putExtra(MediaStore.EXTRA_OUTPUT, desUri);*/
        intent.putExtra("return-data", false);
        //intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true);
        activity.startActivityForResult(intent, requestCode);
    }

}
