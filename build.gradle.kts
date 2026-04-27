plugins {
    application
    id("org.graalvm.buildtools.native") version "1.1.0"
}

application {
    mainClass = "org.jacoco.cli.internal.App"
}

graalvmNative {
    binaries.all {
        resources.autodetect()
    }
    binaries {
        named("main") {
            imageName.set("jacoco-cli")
            mainClass.set("org.jacoco.cli.internal.App")
            buildArgs.add("-H:IncludeResourceBundles=org.kohsuke.args4j.Messages")
            buildArgs.add("-H:IncludeResourceBundles=org.jacoco.core.jacoco")
            buildArgs.add("-H:IncludeResourceBundles=org.jacoco.cli.internal.Messages")
            javaLauncher.set(javaToolchains.launcherFor {
                languageVersion.set(JavaLanguageVersion.of(25))
            })
        }
    }
}

dependencies {
    implementation(libs.jacoco.cli)
}
