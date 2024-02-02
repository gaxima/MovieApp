plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    id("kotlin-kapt")
}

val apiKeyPropertiesFile = rootProject.file("apikey.properties")
val apiKeyProperties = Properties()
FileInputStream(apiKeyPropertiesFile).use { inputStream ->
    apiKeyProperties.load(inputStream)
}


android {
    namespace = AppConfig.applicationId
    compileSdk = AppConfig.compileSdkVersion

    defaultConfig {
        applicationId = AppConfig.applicationId
        minSdk = AppConfig.minSdkVersion
        targetSdk = AppConfig.targetSdkVersion
        versionCode = AppConfig.versionCode
        versionName = AppConfig.versionName

        testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner"

        vectorDrawables {
            useSupportLibrary = true
        }


        buildConfigField("String", "API_KEY", "\"${apiKeyProperties.getProperty("API_KEY")}\"")
        buildConfigField("String", "BASE_URL", "\"${apiKeyProperties.getProperty("BASE_URL")}\"")
        buildConfigField(
            "String",
            "BASE_URL_IMAGE",
            "\"${apiKeyProperties.getProperty("BASE_URL_IMAGE")}\""
        )
    }

    buildTypes {
        release {
            minifyEnabled = false
            proguardFiles getDefaultProguardFile ("proguard-android-optimize.txt"), "proguard-rules.pro"
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.0"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {


    implementation project (":core:network")
    implementation project (":commons")
    implementation project (":ui")
    implementation project (":movie_popular_feature")
    implementation project (":search_movie_feature")
    implementation project (":details_movie_feature")

    implementation(Libs.Kotlin.coreKtxVersion())

    testImplementation(Libs.Test.getJunitVersion())
    androidTestImplementation(Libs.Test.getExtJunitVersion())
    androidTestImplementation(Libs.Test.getEspressoVersion())
    androidTestImplementation(Libs.Test.getComposeUiTestVersion())
    debugImplementation(Libs.Compose.getComposeToolingVersion())
    debugImplementation(Libs.Compose.getComposeUiTestManifestVersion())


    //DataStore
    implementation(Libs.DataStore.getDataStoreVersion())

    // Splashscreenw
    implementation(Libs.SplashScreen.getSplashScreenVersion())

    //DI - Hilt
    implementation(Libs.DaggerHilt.getDaggerHiltAndroidVersion())
    implementation(Libs.DaggerHilt.getDaggerHiltNavigationComposeVersion())
    kapt(Libs.DaggerHilt.getDaggerHiltCompilerVersion())
    kapt kapt (Libs.DaggerHilt.getDaggerHiltCompilerVersion())

    // Others - Compose dependencies
    implementation(Libs.Compose.getMaterialsIconsExtendedVersion())
    implementation(Libs.Compose.getNavigationComposeVersion())


    // Coroutines
    implementation(Libs.Coroutines.getCoreVersion())

    // Coroutine Lifecycle Scopes
    implementation(Libs.LifeCycleVersion.getLifeCycleViewModelVersion())
    implementation(Libs.LifeCycleVersion.getLifeCycleRuntimeVersion())


    //Room
    implementation(Libs.Room.getRoomVersion())
    implementation(Libs.Room.getRoomRuntimeVersion())
    kapt(Libs.Room.getRoomCompilerVersion())
}