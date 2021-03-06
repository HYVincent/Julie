# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

#---------------------------------基本指令区----------------------------------
-optimizationpasses 5
-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-dontskipnonpubliclibraryclassmembers
-dontpreverify
-verbose
-printmapping proguardMapping.txt
-optimizations !code/simplification/cast,!field/*,!class/merging/*
-keepattributes *Annotation*,InnerClasses
-keepattributes Signature
-keepattributes SourceFile,LineNumberTable
#----------------------------------------------------------------------------

#---------------------------------默认保留区---------------------------------
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class * extends android.view.View
-keep public class com.android.vending.licensing.ILicensingService
-keep class android.support.** {*;}


-keepclasseswithmembernames class * {
    native <methods>;
}
-keepclassmembers class * extends android.app.Activity{
    public void *(android.view.View);
}
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
-keep public class * extends android.view.View{
    *** get*();
    void set*(***);
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
}
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
}
-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}
-keep class **.R$* {
 *;
}
-keepclassmembers class * {
    void *(**On*Event);
}
#----------------------------------------------------------------------------

#---------------------------------webview------------------------------------
-keepclassmembers class fqcn.of.javascript.interface.for.Webview {
   public *;
}
-keepclassmembers class * extends android.webkit.WebViewClient {
    public void *(android.webkit.WebView, java.lang.String, android.graphics.Bitmap);
    public boolean *(android.webkit.WebView, java.lang.String);
}
-keepclassmembers class * extends android.webkit.WebViewClient {
    public void *(android.webkit.WebView, jav.lang.String);
}
-keepclassmembernames class * extends android.app.Service {
    public *;
}

-keepclassmembernames class * extends android.content.BroadcastReceiver {
    public *;
}


-keepclassmembers enum * {
     public static **[] values();
     public static ** valueOf(java.lang.String);
 }
 #////////////////////////////第三方框架////////////////////////////////////

 #EventBus
 -keepattributes *Annotation*
 -keepclassmembers class ** {
     @org.greenrobot.eventbus.Subscribe <methods>;
 }
 -keep enum org.greenrobot.eventbus.ThreadMode { *; }

 #Novate
  -keep class com.tamic.novate.** {*;}

  #Okhttp
  -dontwarn okhttp3.**
  -dontwarn okio.**
  -dontwarn javax.annotation.**
  # A resource is loaded with a relative path so the package of this class must be preserved.
  -keepnames class okhttp3.internal.publicsuffix.PublicSuffixDatabase

 #rxjava
 -dontwarn rx.**
 -keep class rx.** { *; }

 -dontwarn sun.misc.**
 -keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
  long producerIndex;
  long consumerIndex;
 }
 -keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
  rx.internal.util.atomic.LinkedQueueNode producerNode;
 }
 -keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
  rx.internal.util.atomic.LinkedQueueNode consumerNode;
 }
 #retrofit2
 # Platform calls Class.forName on types which do not exist on Android to determine platform.
 -dontnote retrofit2.Platform
 # Platform used when running on RoboVM on iOS. Will not be used at runtime.
 -dontnote retrofit2.Platform$IOS$MainThreadExecutor
 # Platform used when running on Java 8 VMs. Will not be used at runtime.
 -dontwarn retrofit2.Platform$Java8
 # Retain generic type information for use by reflection by converters and adapters.
 -keepattributes Signature
 # Retain declared checked exceptions for use by a Proxy instance.
 -keepattributes Exceptions

  #MobSDK
  -keep class com.mob.**{*;}
  -keep class cn.smssdk.**{*;}
  #bugly
  -dontwarn com.tencent.bugly.**
  -keep public class com.tencent.bugly.**{*;}

 -dontwarn com.mob.**
 -dontwarn com.sun.nio.**
 -dontwarn org.conscrypt.**
 -dontwarn  org.jboss.**
 -dontwarn org.eclipse.**
 -dontwarn io.netty.**
 -dontwarn okhttp3.**
 -dontwarn gnu.io.**
 -dontwarn com.barchart.**
 -dontwarn javax.annotation.**
 -dontwarn org.codehaus.**
 -dontwarn sun.misc.**

 -keep class com.vincent.julie.bean.** {*;}
  -keep class com.vincent.julie.network.** {*;}
  -keep class com.vincent.julie.netty.** {*;}

  #Netty
  -keepattributes Signature,InnerClasses
  -keepclasseswithmembers class io.netty.** {
      *;
  }
  -dontwarn io.netty.**
  -dontwarn sun.**

  #Banner
  # glide 的混淆代码
  -keep public class * implements com.bumptech.glide.module.GlideModule
  -keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
    **[] $VALUES;
    public *;
  }
  # banner 的混淆代码
  -keep class com.youth.banner.** {
      *;
   }

   -dontwarn com.raizlabs.android.dbflow.**
     -keep class com.raizlabs.android.dbflow.** {*;}