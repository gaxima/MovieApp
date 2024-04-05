import org.jetbrains.kotlin.konan.properties.Properties


plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}


android {
    namespace = "${AppConfig.applicationId}.commons"
    compileSdk = AppConfig.compileSdkVersion

    defaultConfig {
        minSdk = AppConfig.minSdkVersion

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"


//        buildConfigField(
//            type = "String",
//            name = "API_KEY",
//            value = if (project.properties["apiKey"] != "") project.properties["apiKey"].toString() else ""
//        )

        //load the values from .properties file
        val keystoreFile = project.rootProject.file("apikey.properties")
        val properties = Properties()
        properties.load(keystoreFile.inputStream())
        //return empty key in case something goes wrong
        val apiKey = properties.getProperty("API_KEY") ?: ""
        val baseUrlImage = properties.getProperty("BASE_URL_IMAGE") ?: ""
        val baseUrl = properties.getProperty("BASE_URL") ?: ""
        buildConfigField(type = "String", name = "API_KEY", value = apiKey)
        buildConfigField(type = "String", name = "BASE_URL_IMAGE", value = baseUrlImage)
        buildConfigField(type = "String", name = "BASE_URL", value = baseUrl)

    }
    buildFeatures {
        compose = true
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    implementation(Libs.Kotlin.coreKtxVersion())
    implementation(Libs.Kotlin.getAppCompact())

    //Timber
    api(Libs.Timber.getTimberVersion())

    implementation(Libs.Activity.getActivityComposeVersion())
    implementation(Libs.Compose.getComposeUIVersion())
    implementation(Libs.Compose.getComposeToolingPreviewVersion())
    implementation(Libs.Compose.getComposeMaterialVersion())
    debugImplementation(Libs.Compose.getComposeToolingVersion())
    debugImplementation(Libs.Compose.getComposeUiTestManifestVersion())
}