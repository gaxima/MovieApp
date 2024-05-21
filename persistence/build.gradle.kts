plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
    id("org.sonarqube")
}

android {
    namespace = "${AppConfig.applicationId}.persistence"
    compileSdk = AppConfig.compileSdkVersion

    defaultConfig {
        minSdk = AppConfig.minSdkVersion

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
//        testInstrumentationRunner = "br.com.movieapp.HiltTestRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
        debug {
            enableAndroidTestCoverage = true
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
}

dependencies {

    implementation(Libs.Kotlin.coreKtxVersion())
    implementation(Libs.Kotlin.getAppCompact())
    implementation(Libs.Material.getMaterialVersion())

    //Room
    implementation(Libs.Room.getRoomVersion())
    implementation(Libs.Room.getRoomRuntimeVersion())
    implementation("androidx.test:runner:1.5.2")
    ksp(Libs.Room.getRoomCompilerVersion())

    //Hilt
    implementation(Libs.DaggerHilt.getDaggerHiltAndroidVersion())
    implementation(Libs.DaggerHilt.getDaggerHiltNavigationComposeVersion())
    ksp(Libs.DaggerHilt.getDaggerHiltCompilerVersion())
    ksp(Libs.Hilt.getHiltCompilerVersion())

    testImplementation(Libs.Test.getJunitVersion())
    implementation(Libs.Truth.getTruthVersion())
//    androidTestImplementation(Libs.DaggerHilt.getDaggerHiltTesting())
    testImplementation(Libs.DaggerHilt.getDaggerHiltTesting())
//    kspAndroidTest(Libs.DaggerHilt.getDaggerHiltCompilerVersion())
    kspTest(Libs.DaggerHilt.getDaggerHiltCompilerVersion())
//    androidTestImplementation(Libs.Kotlin.getCoreTesting())
    testImplementation(Libs.Kotlin.getCoreTesting())
//    androidTestImplementation(Libs.Coroutines.getTestVersion())
    testImplementation(Libs.Coroutines.getTestVersion())
//    androidTestImplementation(Libs.Truth.getTruthVersion())
    testImplementation(Libs.Truth.getTruthVersion())


    testImplementation(Libs.Mockito.getMockitoVersion())
//    androidTestImplementation(Libs.Mockito.getMockitoVersion())
    testImplementation(Libs.Mockito.getMockitoVersion())
    testImplementation(Libs.Mockito.getMockitoInlineVersion())

//    androidTestImplementation(Libs.Test.getExtJunitVersion())
    testImplementation(Libs.Test.getExtJunitVersion())
//    androidTestImplementation(Libs.Test.getEspressoVersion())
     testImplementation(Libs.Test.getEspressoVersion())

//    androidTestImplementation(Libs.Test.getComposeUiTestVersion())
    testImplementation(Libs.Test.getComposeUiTestVersion())
    testImplementation(Libs.Test.getRoboletricVersion())

    debugImplementation(Libs.Compose.getComposeToolingVersion())
    debugImplementation(Libs.Compose.getComposeUiTestManifestVersion())

}