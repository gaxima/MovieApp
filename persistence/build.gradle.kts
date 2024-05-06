plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id("org.sonarqube")
}

android {
    namespace = "${AppConfig.applicationId}.persistence"
    compileSdk = AppConfig.compileSdkVersion

    defaultConfig {
        minSdk = AppConfig.minSdkVersion

        //testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        testInstrumentationRunner = "br.com.movieapp.HiltTestRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false

        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {

    implementation(Libs.Kotlin.coreKtxVersion())
    implementation(Libs.Kotlin.getAppCompact())
    implementation(Libs.Material.getMaterialVersion())


    //Room
    implementation(Libs.Room.getRoomVersion())
    implementation(Libs.Room.getRoomRuntimeVersion())
    ksp(Libs.Room.getRoomCompilerVersion())

    //Hilt
    implementation(Libs.DaggerHilt.getDaggerHiltAndroidVersion())
    implementation(Libs.DaggerHilt.getDaggerHiltNavigationComposeVersion())
    ksp(Libs.DaggerHilt.getDaggerHiltCompilerVersion())
    ksp(Libs.Hilt.getHiltCompilerVersion())


    testImplementation(Libs.Test.getJunitVersion())
    implementation(Libs.Truth.getTruthVersion())
    androidTestImplementation(Libs.DaggerHilt.getDaggerHiltTesting())
    kspAndroidTest(Libs.DaggerHilt.getDaggerHiltCompilerVersion())
    androidTestImplementation(Libs.Kotlin.getCoreTesting())
    androidTestImplementation(Libs.Coroutines.getTestVersion())
    androidTestImplementation(Libs.Truth.getTruthVersion())

    testImplementation(Libs.Mockito.getMockitoVersion())
    androidTestImplementation(Libs.Mockito.getMockitoVersion())
    testImplementation(Libs.Mockito.getMockitoInlineVersion())


    androidTestImplementation(Libs.Test.getExtJunitVersion())
    androidTestImplementation(Libs.Test.getEspressoVersion())

    androidTestImplementation(Libs.Test.getComposeUiTestVersion())


    debugImplementation(Libs.Compose.getComposeToolingVersion())
    debugImplementation(Libs.Compose.getComposeUiTestManifestVersion())

}