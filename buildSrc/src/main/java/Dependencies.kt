object AppConfig {
    const val compileSdkVersion = 34
    const val minSdkVersion = 24
    const val targetSdkVersion = 33
    const val versionName = "1.0.0"
    const val versionCode = 1

    const val groupId = "br.com.movieapp"
    const val applicationId = groupId
    const val scheme = "MovieApp"

}

object Modules {
    const val network = ":core:network"
    const val commons = ":commons"
    const val ui = ":ui"
    const val movie_popular_feature = ":movie_popular_feature"
    const val search_feature = ":search_movie_feature"
    const val details_feature = ":details_movie_feature"
}

object Versions {

    const val ACTIVITY_VERSION = "1.8.2"
    const val KOTLIN_VERSION = "1.8.0"
    const val CORE_KTX_VERSION = "1.7.0"
    const val LIFECYCLE_RUNTIME_VERSION = "2.6.1"
    const val GRADLE_PLUGIN_VERSION = "7.4.2"

    const val DATASTORE_VERSION = "1.0.0"
    const val SPLASHSCREEN_VERSION = "1.0.0"

    const val DAGGER_HILT_VERSION = "2.45"
    const val HILT_NAVIGATION_COMPOSE_VERSION = "1.0.0"
    const val HILT_COMPILER_VERSION = "1.0.0"

    const val LIFECYCLE_VIEWMODEL_VERSION = "2.6.1"

    const val MATERIAL_ICONS_EXTENDED_VERSION = "1.4.0"
    const val NAVIGATION_COMPOSE_VERSION = "2.5.3"
    const val COMPOSE_UI_VERSION = "1.4.0"
    const val COMPOSE_TOOLING_VERSION = "1.5.4"
    const val COMPOSE_MATERIAL_VERSION = "1.5.4"

    const val COROUTINES_VERSION = "1.6.4"

    const val ROOM_VERSION = "2.4.2"

    const val GSON_VERSION = "2.10"

    const val COIL_VERSION = "2.2.2"

    const val RETROFIT_VERSION = "2.9.0"
    const val RETROFIT_CONVERTER_GSON_VERSION = "2.9.0"
    const val OKHTTP_VERSION = "5.0.0-alpha.2"
    const val LOGGING_INTERCEPTOR_VERSION = "5.0.0-alpha.2"

    const val PAGING_RUNTIME_VERSION = "3.2.1"
    const val PAGING_COMPOSE_VERSION = "3.2.1"

    const val TIMBER_VERSION = "5.0.1"

    const val JUNIT_VERSION = "4.13.2"
    const val EXT_JUNIT_VERSION = "1.1.5"
    const val ESPRESSO_VERSION = "3.5.1"
    const val COMPOSE_UI_TEST_VERSION = "1.0.0"
}

object Libs {

    object Gradle {
        fun getToolsGradleVersion() =
            "com.android.tools.build:gradle:${Versions.GRADLE_PLUGIN_VERSION}"
    }

    object Kotlin {
        fun getKotlinGradlePluginVersion() =
            "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.KOTLIN_VERSION}"

        fun getKotlinStdlibVersion() =
            "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.KOTLIN_VERSION}"

        fun getKotlinReflectVersion() =
            "org.jetbrains.kotlin:kotlin-reflect:${Versions.KOTLIN_VERSION}"

        fun coreKtxVersion() = "androidx.core:core-ktx:${Versions.CORE_KTX_VERSION}"
    }

    object Activity {
        fun getActivityComposeVersion() =
            "androidx.activity:activity-compose:${Versions.ACTIVITY_VERSION}"
    }

    object Compose {


        fun getComposeUIVersion() = "androidx.compose.ui:ui:${Versions.COMPOSE_UI_VERSION}"
        fun getComposeMaterial3Version() =
            "androidx.compose.material3:material3:${Versions.COMPOSE_MATERIAL_VERSION}"

        fun getComposeToolingPreviewVersion() =
            "androidx.compose.ui:ui-tooling-preview:${Versions.COMPOSE_UI_VERSION}"

        fun getComposeToolingVersion() =
            "androidx.compose.ui:ui-tooling:${Versions.COMPOSE_UI_VERSION}"

        fun getComposeTestJUnitVersion() = "androidx.compose.ui:ui-test-junit4:1.4.0"

        fun getNavigationComposeVersion() =
            "androidx.navigation:navigation-compose:${Versions.NAVIGATION_COMPOSE_VERSION}"

        fun getMaterialsIconsExtendedVersion() =
            "androidx.compose.material:material-icons-extended:${Versions.MATERIAL_ICONS_EXTENDED_VERSION}"

        fun getComposeUiTestManifestVersion() =
            "androidx.compose.ui:ui-test-manifest:${Versions.COMPOSE_UI_VERSION}"


    }

    object DataStore {
        fun getDataStoreVersion() =
            "androidx.datastore:datastore-preferences:${Versions.DATASTORE_VERSION}"
    }

    object Coil {
        fun getCoilVersion() = "io.coil-kt:coil-compose:${Versions.COIL_VERSION}"
    }

    object Timber {
        fun getTimberVersion() = "com.jakewharton.timber:timber:${Versions.TIMBER_VERSION}"
    }


    object LifeCycleVersion {
        fun getLifeCycleViewModelVersion() =
            "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.LIFECYCLE_VIEWMODEL_VERSION}"

        fun getLifeCycleComposeViewModelVersion() =
            "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.LIFECYCLE_VIEWMODEL_VERSION}"

        fun getLifeCycleRuntimeVersion() =
            "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.LIFECYCLE_RUNTIME_VERSION}"
    }

    object Retrofit {
        fun getRetrofitCoreVersion() =
            "com.squareup.retrofit2:retrofit:${Versions.RETROFIT_VERSION}"

        fun getRetrofitGsonVersion() =
            "com.squareup.retrofit2:converter-gson:${Versions.RETROFIT_CONVERTER_GSON_VERSION}"
    }

    object Gson {
        fun getGsonVersion() = "com.google.code.gson:gson:${Versions.GSON_VERSION}"
    }

    object OkHttp3 {
        fun getBoomVersion() = "com.squareup.okhttp3:okhttp-bom:${Versions.OKHTTP_VERSION}"
        fun getCoreVersion() = "com.squareup.okhttp3:okhttp:${Versions.OKHTTP_VERSION}"
        fun getInterceptorVersion() =
            "com.squareup.okhttp3:logging-interceptor:${Versions.LOGGING_INTERCEPTOR_VERSION}"

    }

    object Coroutines {
        fun getCoreVersion() =
            "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.COROUTINES_VERSION}"

        fun getAndroidVersion() =
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.COROUTINES_VERSION}"
    }

    object Hilt {
        fun getHiltCompilerVersion(): String =
            "androidx.hilt:hilt-compiler:${Versions.HILT_COMPILER_VERSION}"
    }


    object DaggerHilt {
        fun getDaggerHiltPluginVersion() =
            "com.google.dagger:hilt-android-gradle-plugin:${Versions.DAGGER_HILT_VERSION}"

        fun getDaggerHiltAndroidVersion() =
            "com.google.dagger:hilt-android:${Versions.DAGGER_HILT_VERSION}"

        fun getDaggerHiltCompilerVersion() =
            "com.google.dagger:hilt-android-compiler:${Versions.DAGGER_HILT_VERSION}"

        fun getDaggerHiltNavigationComposeVersion(): String =
            "androidx.hilt:hilt-navigation-compose:${Versions.HILT_COMPILER_VERSION}"
    }

    object SplashScreen {
        fun getSplashScreenVersion() =
            "androidx.hilt:hilt-navigation-compose:${Versions.SPLASHSCREEN_VERSION}"
    }

    object Test {
        fun getJunitVersion() = "junit:junit:${Versions.JUNIT_VERSION}"
        fun getExtJunitVersion() = "androidx.test.ext:junit:${Versions.EXT_JUNIT_VERSION}"
        fun getEspressoVersion() =
            "androidx.test.espresso:espresso-core:${Versions.ESPRESSO_VERSION}"

        fun getComposeUiTestVersion() =
            "androidx.compose.ui:ui-test-junit4:${Versions.COMPOSE_UI_TEST_VERSION}"
    }

    object Paging {
        fun getPagingRuntimeVersion() =
            "androidx.paging:paging-runtime:${Versions.PAGING_RUNTIME_VERSION}"

        fun getPagingComposeVersion() =
            "androidx.paging:paging-compose:${Versions.PAGING_COMPOSE_VERSION}"
    }

    object Room {
        fun getRoomVersion() = "androidx.room:room-ktx:${Versions.ROOM_VERSION}"
        fun getRoomRuntimeVersion() = "androidx.room:room-runtime:${Versions.ROOM_VERSION}"
        fun getRoomCompilerVersion() = "androidx.room:room-compiler:${Versions.ROOM_VERSION}"
    }


}


