plugins {
    id(Plugins.application)
    kotlin(Plugins.kotlinAndroid)
    kotlin(Plugins.kapt)
    id(Plugins.hilt)
    id(Plugins.safeArgs)
}

android {
    namespace = Config.applicationId
    compileSdk = Config.compileSdk

    defaultConfig {
        applicationId = Config.applicationId
        minSdk = Config.minSdk
        targetSdk = Config.targetSdk
        versionCode = Config.versionCode
        versionName = Config.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        debug {
            buildConfigField("String", "BASE_URL", "\"https://restcountries.com/v2/\"")
        }
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String", "BASE_URL", "\"https://restcountries.com/v2/\"")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = Config.jvmTarget
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    // Core
    implementation(Dependencies.AndroidCore.core)

    // UI components
    implementation(Dependencies.UIComponents.appCompat)
    implementation(Dependencies.UIComponents.material)
    implementation(Dependencies.UIComponents.constraint)

    // Navigation Component
    implementation(Dependencies.NavigationComponent.navigationUI)
    implementation(Dependencies.NavigationComponent.navigationFragment)

    // Glide
    implementation(Dependencies.Glide.glide)
    kapt(Dependencies.Glide.compiler)

    // Retrofit
    implementation(Dependencies.Retrofit.retrofit)

    // Gson
    implementation(Dependencies.Gson.gson)

    // OkHttp
    implementation(Dependencies.OkHttp.bom)
    implementation(Dependencies.OkHttp.okhttp)
    implementation(Dependencies.OkHttp.interceptor)

    // Dagger-Hilt
    implementation(Dependencies.Hilt.hiltAndroid)
    kapt(Dependencies.Hilt.hiltCompiler)

    // Coroutines
    implementation(Dependencies.Coroutines.kotlinCoroutines)
    implementation(Dependencies.Coroutines.kotlinCoroutinesCore)

    // ViewBinding
    implementation(Dependencies.ViewBindingPropertyDelegate.bindingNoReflection)
}