import kotlinx.kover.gradle.plugin.dsl.AggregationType
import kotlinx.kover.gradle.plugin.dsl.MetricType

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    id("com.google.devtools.ksp")
    id("org.sonarqube")
    id("org.jetbrains.kotlinx.kover")
}

koverReport {
    verify {
        rule("Line Coverage") {
            isEnabled = true
            bound {
                minValue = 20 // Minimum coverage percentage
                metric = MetricType.LINE
                aggregation = AggregationType.COVERED_PERCENTAGE
            }
        }
    }
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
    testOptions{
        unitTests{
            isIncludeAndroidResources = true
        }
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

    testImplementation(Libs.Test.getCoroutinesTestVersion())
    ////////////////////////////////////////////

    implementation(Libs.Truth.getTruthVersion())

    //javax inject
    implementation(Libs.Javax.getJavaxInjectVersion())

    //hilt
    kspAndroidTest(Libs.DaggerHilt.getDaggerHiltCompilerVersion())
    androidTestImplementation(Libs.DaggerHilt.getDaggerHiltTesting())

    //Unit tests
    testImplementation(Libs.Test.getJunitVersion())
    testImplementation(Libs.Kotlin.getCoreTesting())
    /**/testImplementation(Libs.Coroutines.getTestVersion())
    /**/testImplementation(Libs.Mockito.getMockitoKotlinVersion())
    testImplementation(Libs.Mockito.getMockitoVersion())
    testImplementation(Libs.Mockito.getMockitoInlineVersion())
    testImplementation(Libs.Paging.getPagingCommonVersion())

    //instrumentation tests
    androidTestImplementation(Libs.Kotlin.getCoreTesting())
    androidTestImplementation(Libs.Coroutines.getTestVersion())
    androidTestImplementation(Libs.Truth.getTruthVersion())
    androidTestImplementation(Libs.Test.getExtJunitVersion())
    androidTestImplementation(Libs.Test.getEspressoVersion())

    androidTestImplementation(Libs.Test.getComposeUiTestVersion())
    debugImplementation(Libs.Compose.getComposeToolingVersion())
    debugImplementation(Libs.Compose.getComposeUiTestManifestVersion())

    testImplementation(Libs.Test.getRoboletricVersion())

}