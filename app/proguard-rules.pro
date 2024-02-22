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

# Keep Retrofit and its annotations
-keep class retrofit2.** { *; }
-keepattributes Signature
-keepattributes Exceptions
-keepclasseswithmembers class * {
    @retrofit2.http.* <methods>;
}

-keep class com.google.gson.** { *; }

##---------------Begin: proguard configuration for Gson  ----------
# Gson uses generic type information stored in a class file when working with fields. Proguard
# removes such information by default, so configure it to keep all of it.
-keepattributes Signature

# For using GSON @Expose annotation
-keepattributes *Annotation*

# Gson specific classes
-dontwarn sun.misc.**
#-keep class com.google.gson.stream.** { *; }

# Application classes that will be serialized/deserialized over Gson
-keep class com.google.gson.examples.android.responseModel.** { *; }

# Prevent proguard from stripping interface information from TypeAdapterFactory,
# JsonSerializer, JsonDeserializer instances (so they can be used in @JsonAdapter)
-keep class * implements com.google.gson.TypeAdapterFactory
-keep class * implements com.google.gson.JsonSerializer
-keep class * implements com.google.gson.JsonDeserializer

# Prevent R8 from leaving Data object members always null
-keepclassmembers,allowobfuscation class * {
  @com.google.gson.annotations.SerializedName <fields>;
}
##---------------End: proguard configuration for Gson  ----------

-keep class live.durbar.bangabandhuapp.data.model.**

# Keep the Retrofit service interfaces
-keep interface live.durbar.bangabandhuapp.data.ApiService.** { *; }

-keep class live.durbar.bangabandhuapp.data.repository.**

# Keep Picasso classes and methods
-keep class com.squareup.picasso.** { *; }

# Keep Picasso's request handler classes
-keep class com.squareup.picasso.downloader.** { *; }

# Keep Picasso transformations
-keepclassmembers class * implements com.squareup.picasso.Transformation {
    *;
}

-keep class live.durbar.bangabandhuapp.ui.live.VideoActivity
-keepclassmembers class live.durbar.bangabandhuapp.ui.live.VideoActivity {
    *;
}
-keepclassmembers class live.durbar.bangabandhuapp.MainActivity {
    *;
}

-keep class io.agora.rtc.** { *; }
-dontwarn io.agora.rtc.**

# Add other rules as needed

# Keep the entire Api class and its members
-keep class live.durbar.bangabandhuapp.data.Api { *; }

# Keep the ApiService interface
-keep interface live.durbar.bangabandhuapp.data.ApiService { *; }

# Keep the GsonConverterFactory class
-keep class retrofit2.converter.gson.GsonConverterFactory { *; }

# Keep the annotations used by retrofit
-keepattributes Signature, InnerClasses

# Keep the retrofit2 package
-keep class retrofit2.** { *; }

# Keep the HttpLoggingInterceptor class
-keep class okhttp3.logging.HttpLoggingInterceptor { *; }

# Keep the OkHttpClient class
-keep class okhttp3.** { *; }

# Don't warn about missing classes in the okhttp3 package
-dontwarn okhttp3.**











