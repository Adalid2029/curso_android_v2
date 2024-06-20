plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    kotlin("kapt")  // Habilita Kotlin Annotation Processing Tool (KAPT)
}

android {
    namespace = "com.cursos.posgrado.cursoandroidv2"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.cursos.posgrado.cursoandroidv2"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation("androidx.recyclerview:recyclerview:1.2.1")
    val room_version = "2.6.1"
    implementation("androidx.room:room-runtime:$room_version")
    kapt("androidx.room:room-compiler:$room_version")

    // Opcionales - Extensiones de Kotlin y soporte de Coroutines para Room
    implementation("androidx.room:room-ktx:$room_version")

    // Opcionales - Soporte RxJava2 para Room
    implementation("androidx.room:room-rxjava2:$room_version")

    // Opcionales - Soporte RxJava3 para Room
    implementation("androidx.room:room-rxjava3:$room_version")

    // Opcionales - Soporte Guava para Room, incluyendo Optional y ListenableFuture
    implementation("androidx.room:room-guava:$room_version")

    // Opcionales - Helpers de prueba
    testImplementation("androidx.room:room-testing:$room_version")

    // Opcionales - Integración con Paging 3
    implementation("androidx.room:room-paging:$room_version")

    // Dependencia adicional para Lifecycle
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.2")
    implementation( "androidx.activity:activity-ktx:1.5.1")

}