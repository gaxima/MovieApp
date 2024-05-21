
plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    id("com.google.devtools.ksp")
    id("org.sonarqube")
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

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        vectorDrawables {
            useSupportLibrary = true
        }

    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    sonarqube{
        properties{
            property("sonar.projectKey", "gaxima_MovieApp")
            property("sonar.organization", "gaxima")
            property("sonar.host.url", "https://sonarcloud.io")
            property("sonar.sources", "src/")
        }
    }
}

dependencies {


    implementation(project(":core:network"))
    implementation(project(":commons"))
    implementation(project(":ui"))
    implementation(project(":movie_popular_feature"))
    implementation(project(":search_movie_feature"))
    implementation(project(":details_movie_feature"))
    implementation(project(":movie_favorite_feature"))



    implementation(Libs.Kotlin.coreKtxVersion())

    testImplementation(Libs.Test.getJunitVersion())
    androidTestImplementation(Libs.Test.getExtJunitVersion())
    androidTestImplementation(Libs.Test.getEspressoVersion())
    androidTestImplementation(Libs.Test.getComposeUiTestVersion())
    debugImplementation(Libs.Compose.getComposeToolingVersion())
    debugImplementation(Libs.Compose.getComposeUiTestManifestVersion())

    implementation(Libs.Material.getMaterialVersion())
    //DataStore
    implementation(Libs.DataStore.getDataStoreVersion())

    // Splashscreenw
    implementation(Libs.SplashScreen.getSplashScreenVersion())

    //DI - Hilt
    implementation(Libs.DaggerHilt.getDaggerHiltAndroidVersion())
    implementation(Libs.DaggerHilt.getDaggerHiltNavigationComposeVersion())
    ksp(Libs.DaggerHilt.getDaggerHiltCompilerVersion())
    ksp(Libs.DaggerHilt.getDaggerHiltCompilerVersion())

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
    ksp(Libs.Room.getRoomCompilerVersion())
}