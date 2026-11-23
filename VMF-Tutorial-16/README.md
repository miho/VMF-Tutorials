# Tutorial 16 · Generating models with Maven

[Tutorial index](../README.md) · [Previous](../VMF-Tutorial-15/README.md) · [Next](../VMF-Tutorial-17/README.md)

This tutorial uses `eu.mihosoft.vmf:vmf-maven-plugin:0.4.0` and `vmf-runtime` from Maven Central. [POM](pom.xml)

## How the build works

1. Define the model under `src/main/vmf` in a package ending in `.vmfmodel`. [Greeting.java](src/main/vmf/eu/mihosoft/vmf/tutorial16/vmfmodel/Greeting.java)
2. Bind the plugin's `vmf` goal. It runs in `generate-sources`, generates Java into `target/generated-sources/java-vmf`, and adds that directory to Maven's compile sources.
3. Maven compiles the generated model and the application. The generated interface lives in the application package, with the `.vmfmodel` suffix removed. The program calls `Greeting.newBuilder()`. [Main.java](src/main/java/eu/mihosoft/vmf/tutorial16/Main.java)
4. The Exec Maven Plugin runs the program during `verify`.

## Run the lesson

With JDK 21 and Maven 3.9, run this from the tutorial directory.

```sh
mvn -B -ntp clean verify
```

The build generates and compiles the model and prints `Hello from Maven!`.
