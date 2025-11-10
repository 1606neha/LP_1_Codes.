#include <jni.h>
#include "ArithmeticJNI.h"
#include <stdio.h>

JNIEXPORT jdouble JNICALL Java_ArithmeticJNI_add
  (JNIEnv *env, jobject obj, jdouble a, jdouble b) {
    return a + b;
}

JNIEXPORT jdouble JNICALL Java_ArithmeticJNI_subtract
  (JNIEnv *env, jobject obj, jdouble a, jdouble b) {
    return a - b;
}

JNIEXPORT jdouble JNICALL Java_ArithmeticJNI_multiply
  (JNIEnv *env, jobject obj, jdouble a, jdouble b) {
    return a * b;
}

JNIEXPORT jdouble JNICALL Java_ArithmeticJNI_divide
  (JNIEnv *env, jobject obj, jdouble a, jdouble b) {
    if (b == 0) {
        printf("Error: Division by zero!\n");
        return 0;
    }
    return a / b;
}

