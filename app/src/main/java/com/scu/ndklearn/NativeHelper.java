package com.scu.ndklearn;

public class NativeHelper{
    static {
        System.loadLibrary("native-lib");
    }
    public native static void setArray1(byte[] buffer,int len);
    public native static void setArray2(byte[] buffer,int len);

}