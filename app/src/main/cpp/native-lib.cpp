#include <jni.h>
#include <string>

extern "C"
JNIEXPORT jstring JNICALL
Java_com_example_nameageandroid_MainActivity_processData(
        JNIEnv* env,
        jobject,
        jstring name,
        jint age) {

    const char* nativeName = env->GetStringUTFChars(name, nullptr);

    std::string result = "مرحباً ";
    result += nativeName;
    result += "! عمرك ";
    result += std::to_string(age);
    result += " سنة.";

    env->ReleaseStringUTFChars(name, nativeName);

    return env->NewStringUTF(result.c_str());
}
