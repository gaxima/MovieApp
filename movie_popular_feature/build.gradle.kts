plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    id("com.google.devtools.ksp")
}

android {
    namespace = "${AppConfig.applicationId}.movie_popular_feature"
    compileSdk = AppConfig.compileSdkVersion

    defaultConfig {
        minSdk = AppConfig.minSdkVersion

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
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
        kotlinCompilerExtensionVersion = "1.5.1"
    }
}

dependencies {

    implementation(project(":core:network"))
    implementation(project(":commons"))
    implementation(project(":ui"))



    implementation(Libs.Kotlin.coreKtxVersion())
    implementation(Libs.Kotlin.getAppCompact())
    implementation(Libs.Material.getMaterialVersion())


    implementation(Libs.DaggerHilt.getDaggerHiltAndroidVersion())
    implementation(Libs.DaggerHilt.getDaggerHiltNavigationComposeVersion())
    ksp(Libs.DaggerHilt.getDaggerHiltCompilerVersion())
    ksp(Libs.Hilt.getHiltCompilerVersion())

    // Paging3
    implementation(Libs.Paging.getPagingRuntimeVersion())
    implementation(Libs.Paging.getPagingComposeVersion())

    testImplementation(Libs.Test.getJunitVersion())

    testImplementation(Libs.Mockk.getMockkVersion())
    testImplementation(Libs.Test.getCoroutinesTestVersion())

}