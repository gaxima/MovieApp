import org.jetbrains.kotlin.konan.properties.Properties


plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("org.sonarqube")
}


android {
    namespace = "${AppConfig.applicationId}.commons"
    compileSdk = AppConfig.compileSdkVersion

    defaultConfig {
        minSdk = AppConfig.minSdkVersion

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"


        //load the values from .properties file
        val keystoreFile = project.rootProject.file("apikey.properties")
        val properties = Properties()
        properties.load(keystoreFile.inputStream())
        //return empty key in case something goes wrong
        val apiKey = properties.getProperty("API_KEY") ?: ""
        val baseUrlImage = properties.getProperty("BASE_URL_IMAGE") ?: ""
        val baseUrl = properties.getProperty("BASE_URL") ?: ""
        buildConfigField(type = "String", name = "API_KEY", value = "\"$apiKey\"")
        buildConfigField(type = "String", name = "BASE_URL_IMAGE", value = "\"$baseUrlImage\"")
        buildConfigField(type = "String", name = "BASE_URL", value = "\"$baseUrl\"")

    }
    buildFeatures {
        compose = true
        buildConfig = true
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}
sonar{
    properties{
        property("sonar.projectKey", "gaxima_MovieApp")
        property("sonar.organization", "gaxima")
        property("sonar.host.url", "https://sonarcloud.io")
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