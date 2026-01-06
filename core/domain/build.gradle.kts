import com.android.build.api.dsl.androidLibrary
import org.gradle.api.problems.internal.GradleCoreProblemGroup.compilation
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.android.kotlin.multiplatform.library)
    alias(libs.plugins.kotlin.serialization)
}


kotlin {
    android {
        namespace ="org.mifos.auth.kmp.core.domain"
    }

    jvm()
    androidLibrary {
        namespace = "org.mifos.auth.kmp"
        compileSdk = libs.versions.android.compileSdk.get().toInt()
        minSdk = libs.versions.android.minSdk.get().toInt()

        withJava() // enable java compilation support
//        withHostTestBuilder {}.configure {}
//        withDeviceTestBuilder {
//            sourceSetTreeName = "test"
//        }

        compilations.configureEach {
            compilerOptions.configure {
                jvmTarget.set(
                    JvmTarget.JVM_17
                )
            }
        }
    }
    iosX64()
    iosArm64()

    sourceSets {
        commonMain.dependencies {
            api(projects.core.common)
            api(projects.core.network)
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.kotlinx.serialization.json)
        }
    }

}
