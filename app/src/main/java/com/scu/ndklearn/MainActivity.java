package com.scu.ndklearn;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener {
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    // Example of a call to a native method
    tv = (TextView) findViewById(R.id.sample_text);
    tv.setText(stringFromJNI());
        Button btn = (Button)findViewById(R.id.testbyte);
        btn.setOnClickListener(this);
        findViewById(R.id.testbyte2).setOnClickListener(this);
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.testbyte:
                final int SIZE = 100;
                byte[] te = new byte[SIZE];
                NativeHelper.setArray1(te,SIZE);
                StringBuilder sb = new StringBuilder(SIZE);
                for(int i=0;i<SIZE;i++){
                    Log.i("TEST","te["+i+"] = "+te[i]);
                    sb.append("te["+i+"] = "+te[i]+"\n");
                }
                tv.setText(sb.toString());
                break;
            case R.id.testbyte2:
                final int SIZE1 = 100;
                byte[] te1 = new byte[SIZE1];
                NativeHelper.setArray2(te1,SIZE1);
                StringBuilder sb1 = new StringBuilder(SIZE1);
                for(int i=0;i<SIZE1;i++){
                    Log.i("TEST","te["+i+"] = "+te1[i]);
                    sb1.append("te["+i+"] = "+te1[i]+"\n");
                }
                tv.setText(sb1.toString());
                break;
        }
    }
}
