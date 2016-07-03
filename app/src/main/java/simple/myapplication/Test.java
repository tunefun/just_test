package simple.myapplication;

/**
 * Created by noting on 16-7-2.
 */
public class Test {
    static {
        try {
            System.loadLibrary("helloNDK");
        } catch (Exception e) {
            System.out.print("已成功运行");
            System.out.print(e.toString());
        }
    }
    public native String getInt();
    public native void opArr(int[] a);
    public void opArr1(int[] a){
        int len= a.length;
        for (int i = 0; i < len; i++) {
            a[i]=i*2+i%7;
        }
    }
}
