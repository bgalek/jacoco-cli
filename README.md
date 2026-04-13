# jacoco-cli

A standalone native executable for [JaCoCo CLI](https://www.jacoco.org/jacoco/trunk/doc/cli.html), built with GraalVM
Native Image.

This project compiles the JaCoCo CLI (`jacococli.jar`) into a single platform-specific binary using GraalVM, eliminating
the need for a JVM at runtime.

## Build

Build the native executable:

```sh
./gradlew nativeCompile
```

The binary will be placed at `build/native/nativeCompile/jacoco-cli`.

## Usage

Use it as a drop-in replacement for `java -jar jacococli.jar`:

```sh
# Show help
./jacoco-cli help

# Generate a report
./jacoco-cli report coverage.exec \
  --classfiles build/classes \
  --sourcefiles src/main/java \
  --html report

# Merge execution data files
./jacoco-cli merge a.exec b.exec --destfile merged.exec

# Dump execution data from a running JVM agent
./jacoco-cli dump --address localhost --port 6300 --destfile coverage.exec

# Compute code instrumentation
./jacoco-cli instrument classes --dest instrumented
```

## Supported Platforms

Pre-built binaries are available on the [Releases](../../releases) page for:

| OS      | Architecture | Binary                          |
|---------|--------------|---------------------------------|
| Linux   | x86_64       | `jacoco-cli-linux-x86_64`       |
| Linux   | aarch64      | `jacoco-cli-linux-aarch64`      |
| macOS   | aarch64      | `jacoco-cli-macos-aarch64`      |
| Windows | x86_64       | `jacoco-cli-windows-x86_64.exe` |

## How It Works

The project wraps `org.jacoco.cli.internal.Main` (JaCoCo 0.8.14) and compiles it to a native binary via
the [GraalVM Native Build Tools](https://graalvm.github.io/native-build-tools/) Gradle plugin. The custom `App` entry
point rewrites help text to reference `jacoco-cli` instead of `java -jar jacococli.jar`.
