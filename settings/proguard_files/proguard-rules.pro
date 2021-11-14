-keepattributes *Annotation*, Signature, Exception

# Application classes that will be serialized/deserialized over Gson
-keep class com.ouvrirdeveloper.data.models.entities.** { <fields>; }
# Uncomment this to preserve the line number information for
# debugging stack traces.
-keepattributes SourceFile,LineNumberTable
-keepclasseswithmembernames class * { native <methods>; }

-keepclassmembers class **.R$* {
    public static <fields>;
}
# Preserve annotated Javascript interface methods.
-keepclassmembers class * {
    @android.webkit.JavascriptInterface <methods>;
}
-keepclassmembers class * implements android.os.Parcelable {
    public static final ** CREATOR;
}
# The support libraries contains references to newer platform versions.
# Don't warn about those in case this app is linking against an older
# platform version. We know about them, and they are safe.
-dontnote android.support.**
-dontnote androidx.**
-dontwarn android.support.**
-dontwarn androidx.**
# This class is deprecated, but remains for backward compatibility.
-dontwarn android.util.FloatMath

# Understand the @Keep support annotation.
-keep class android.support.annotation.Keep
-keep class androidx.annotation.Keep

-keep @android.support.annotation.Keep class * {*;}
-keep @androidx.annotation.Keep class * {*;}

-keepclasseswithmembers class * {
    @android.support.annotation.Keep <methods>;
}

-keepclasseswithmembers class * {
    @androidx.annotation.Keep <methods>;
}

-keepclasseswithmembers class * {
    @android.support.annotation.Keep <fields>;
}

-keepclasseswithmembers class * {
    @androidx.annotation.Keep <fields>;
}

-keepclasseswithmembers class * {
    @android.support.annotation.Keep <init>(...);
}

-keepclasseswithmembers class * {
    @androidx.annotation.Keep <init>(...);
}
# These classes are duplicated between android.jar and org.apache.http.legacy.jar.
-dontnote org.apache.http.**
-dontnote android.net.http.**


-keepnames class androidx.navigation.fragment.NavHostFragment

-keepattributes *Annotation*, InnerClasses
-dontnote kotlinx.serialization.AnnotationsKt # core serialization annotations

# kotlinx-serialization-json specific. Add this if you have java.lang.NoClassDefFoundError kotlinx.serialization.json.JsonObjectSerializer
-keepclassmembers class kotlinx.serialization.json.** {
    *** Companion;
}
-keepclasseswithmembers class kotlinx.serialization.json.** {
    kotlinx.serialization.KSerializer serializer(...);
}

-keep,includedescriptorclasses class com.ouvrirdeveloper.domain.models.**$$serializer { *; }
-keepclassmembers class com.ouvrirdeveloper.domain.models.** {
    *** Companion;
}
-keepclasseswithmembers class com.ouvrirdeveloper.domain.models.models.** {
    kotlinx.serialization.KSerializer serializer(...);
}

-keep class bitter.jnibridge.* { *; }
-keep class org.fmod.* { *; }
-keep class com.google.androidgamesdk.ChoreographerCallback { *; }
-keep class com.google.androidgamesdk.SwappyDisplayManager { *; }

# For enabling unobfusticated line number and method names in crash report
-keepattributes SourceFile,LineNumberTable        # Keep file names and line numbers.
-keep public class * extends java.lang.Exception  # Optional: Keep custom exceptions.
-keep class android.support.v7.** { *; }
-keep class * extends androidx.lifecycle.ViewModel { *; }
-keep class com.ouvrirdeveloper.core { *; }
-keep class com.ouvrirdeveloper.reportbook.features.home.epoxy { *; }
-keep class com.ouvrirdeveloper.reportbook.viewbinding { *; }
