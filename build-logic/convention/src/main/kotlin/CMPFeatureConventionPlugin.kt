import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.mifos.libs

class CMPFeatureConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("mifos.kmp.library")
                apply("org.jetbrains.kotlin.plugin.compose")
                apply("org.jetbrains.compose")
            }

            dependencies {
                add("commonMainImplementation", libs.findLibrary("jb.composeRuntime").get())
                add("commonMainImplementation", libs.findLibrary("jb.lifecycle.compose").get())
                add("commonMainImplementation", libs.findLibrary("jb.composeViewmodel").get())
                add("commonMainImplementation", libs.findLibrary("jb.lifecycleViewmodel").get())
                add("commonMainImplementation", libs.findLibrary("jb.lifecycleViewmodelSavedState").get())
                add("commonMainImplementation", libs.findLibrary("jb.savedstate").get())
                add("commonMainImplementation", libs.findLibrary("jb.bundle").get())
                add("commonMainImplementation", libs.findLibrary("jb.composeNavigation").get())
                add("commonMainImplementation", libs.findLibrary("jb.compose.ui").get())
                add("commonMainImplementation", libs.findLibrary("jb.ui.tooling.preview").get())
                add("commonMainImplementation", libs.findLibrary("kermit.logger").get())
                add("commonMainImplementation", libs.findLibrary("material3").get())
                add("commonMainImplementation", libs.findLibrary("foundation").get())
                add("commonMainImplementation", libs.findLibrary("material3.icons").get())
                add("commonMainImplementation", libs.findLibrary("components.resources").get())


                add("androidMainImplementation", libs.findLibrary("kotlinx.coroutines.android").get())

                add("desktopMainImplementation", libs.findLibrary("kotlinx.coroutines.swing").get())

            }
        }
    }
}