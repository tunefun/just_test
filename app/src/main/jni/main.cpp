//
// Created by noting on 16-7-2.
//
#include <stdio.h>
#include "simple_myapplication_Test.h"
#include "test.h"

JNIEXPORT jstring JNICALL Java_simple_myapplication_Test_getInt
        (JNIEnv *env, jobject obj) {
    return (*env).NewStringUTF("我来自jni");
}

JNIEXPORT void JNICALL
Java_simple_myapplication_Test_opArr(JNIEnv *env, jobject instance, jintArray a_) {
    // TODO
    jint *a = env->GetIntArrayElements(a_, NULL);
    int len=env->GetArrayLength(a_);
    toGrey(a,len);
    env->ReleaseIntArrayElements(a_, a, 0);
}
