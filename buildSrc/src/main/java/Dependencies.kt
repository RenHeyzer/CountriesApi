object Dependencies {

    object AndroidCore {
        const val core = "androidx.core:core-ktx:1.9.0"
    }

    object UIComponents {
        const val constraint = "androidx.constraintlayout:constraintlayout:${Versions.constraint}"
        const val material = "com.google.android.material:material:${Versions.material}"
        const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    }

    object Coroutines {
        const val kotlinCoroutines =
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
        const val kotlinCoroutinesCore =
            "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    }

    object NavigationComponent {
        const val navigationFragment =
            "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
        const val navigationUI = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
    }

    object Retrofit {
        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    }

    object Gson {
        const val gson = "com.squareup.retrofit2:converter-gson:${Versions.gson}"
    }

    object OkHttp {
        const val bom = "com.squareup.okhttp3:okhttp-bom:4.9.0"
        const val okhttp = "com.squareup.okhttp3:okhttp"
        const val interceptor = "com.squareup.okhttp3:logging-interceptor"
    }

    object ViewBindingPropertyDelegate {
        private const val version = "1.5.6"
        const val bindingNoReflection =
            "com.github.kirich1409:viewbindingpropertydelegate-noreflection:$version"
    }

    object Glide {
        const val glide = "com.github.bumptech.glide:glide:4.12.0"
        const val compiler = "com.github.bumptech.glide:compiler:4.14.2"
    }

    object Activity {
        const val version = "androidx.activity:activity-ktx:1.4.0"
    }

    object Fragment {
        const val version = "androidx.fragment:fragment-ktx:1.4.1"
    }

    object Lifecycles {
        const val lifecycle = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycles}"
        const val lifecycleViewModel =
            "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycles}"
        const val lifecycleLiveData =
            "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycles}"
    }

    object Hilt {
        const val hiltAndroid = "com.google.dagger:hilt-android:${Versions.hilt}"
        const val hiltCompiler = "com.google.dagger:hilt-compiler:${Versions.hilt}"
    }
}