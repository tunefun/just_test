//
// Created by noting on 16-7-2.
//
#include <stdio.h>
#include "simple_myapplication_Test.h"
#include "test.h"
#include <time.h>
#include <android/log.h>
//
//JNIEXPORT jstring JNICALL Java_simple_myapplication_Test_getInt
//        (JNIEnv *env, jobject obj) {
//    return (*env).NewStringUTF("我来自jni");
//}
//
//JNIEXPORT void JNICALL
//Java_simple_myapplication_Test_opArr(JNIEnv *env, jobject instance, jintArray a_) {
//    // TODO
//    jint *a = env->GetIntArrayElements(a_, NULL);
//    int len=env->GetArrayLength(a_);
//    toGrey(a,len);
//    env->ReleaseIntArrayElements(a_, a, 0);
//}

JNIEXPORT void JNICALL
Java_simple_myapplication_Convert_1C_CtoGrey(JNIEnv *env, jclass type, jintArray m_) {
    jint *m = env->GetIntArrayElements(m_, NULL);

    // TODO

    clock_t t=clock();
    toGrey(m,env->GetArrayLength(m_));
    __android_log_print(ANDROID_LOG_INFO,"C时间","C时间:%ld",(clock()-t)/CLOCKS_PER_SEC);
    env->ReleaseIntArrayElements(m_, m, 0);
}

JNIEXPORT void JNICALL
Java_simple_myapplication_Convert_1C_CpreDo(JNIEnv *env, jclass type, jintArray p1_,
                                            jintArray p2_) {
    jint *p1 = env->GetIntArrayElements(p1_, NULL);
    jint *p2 = env->GetIntArrayElements(p2_, NULL);

    // TODO

    preDo(p1,env->GetArrayLength(p1_),p2,env->GetArrayLength(p2_));

    env->ReleaseIntArrayElements(p1_, p1, 0);
    env->ReleaseIntArrayElements(p2_, p2, 0);
}

JNIEXPORT void JNICALL
Java_simple_myapplication_Convert_1C_Ccomb(JNIEnv *env, jclass type, jintArray p1_, jintArray p2_) {
    jint *p1 = env->GetIntArrayElements(p1_, NULL);
    jint *p2 = env->GetIntArrayElements(p2_, NULL);

    // TODO

    comb(p1,env->GetArrayLength(p1_),p2,env->GetArrayLength(p2_));

    env->ReleaseIntArrayElements(p1_, p1, 0);
    env->ReleaseIntArrayElements(p2_, p2, 0);
}