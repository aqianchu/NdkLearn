#include <jni.h>
#include <string>

extern "C"
JNIEXPORT void JNICALL
Java_com_scu_ndklearn_NativeHelper_setArray1(JNIEnv *env, jclass type, jbyteArray buffer_,
                                             jint len) {
    //将本地指针直接指向含有Java端数组的内存地址，依赖Jvm的具体实现
    //速度和效率要比GetByteArrayRegion方法要高很多。
    jbyte *buffer = env->GetByteArrayElements(buffer_, NULL);
    for (int i = 0; i < len; ++i) {
        buffer[i] = i;//可以通过buffer指针直接访问这段数组的值，这里修改会同步到Java端。不建议通过本地数组修改Java端的值。
    }
    env->ReleaseByteArrayElements(buffer_, buffer, 0);//最后不要忘了释放内存。
}
extern "C"
JNIEXPORT void JNICALL
Java_com_scu_ndklearn_NativeHelper_setArray2(JNIEnv *env, jclass type, jbyteArray buffer_,
                                             jint len) {
    jbyte *buffer = new jbyte[len];
    //直接将Java端的数组直接拷贝到本地数组中
    env->GetByteArrayRegion(buffer_,0,len,buffer);
    for (int i = 0; i < len; ++i) {
        buffer[i] = i;//修改本地的数组，注意这里Java端的数组不会同步改变。
    }
    delete(buffer);
}
extern "C"
jstring
Java_com_scu_ndklearn_MainActivity_stringFromJNI(
        JNIEnv* env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}


