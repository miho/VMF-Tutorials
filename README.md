# VMF Tutorials

VMF generates Java model APIs from annotated Java interfaces. Each runnable lesson includes a model definition and a small Java program. [VMF project](https://github.com/miho/VMF)

## VMF versions

| Lessons | VMF target | Dependencies used by the examples |
| --- | --- | --- |
| 01–17 | `0.4.0` | Pinned to the [VMF 0.4.0 release](https://github.com/miho/VMF/releases/tag/v0.4.0). |
| 18–20b | `0.4.1+` | Currently `0.4.1-SNAPSHOT`. The VMF runtime brings in VCollections `0.3.7-SNAPSHOT`. Tutorial 20 also uses `vmf-jackson:0.4.1-SNAPSHOT`. |

The newer tutorials download their snapshots from Maven Central’s public snapshot repository on the first run. The plugin and Jackson module share the same [version pin](config/common.properties). If you have published your own builds to Maven Local, use `./gradlew -PvmfLocal run` or `.\gradlew.bat -PvmfLocal run` from the lesson directory to look there first.

## Run a tutorial

Install JDK 21 and clone this repository. From a Gradle tutorial directory, such as `VMF-Tutorial-01`, run the example with the included Gradle wrapper.

```sh
./gradlew run
```

On Windows, run `.\gradlew.bat run`. The wrapper downloads Gradle on the first run. The model definitions live under `src/main/vmf`, and the VMF plugin writes the generated Java sources to `build/generated-src/vmf-main`.

Tutorial 16 uses Maven 3.9 and the VMF Maven plugin from Maven Central. From its directory, run `mvn clean verify` to generate the model, build the project, and run the example. [Maven tutorial](VMF-Tutorial-16/README.md)

## Run every tutorial

From the repository root, run `./build-and-run-all.sh` on macOS or Linux, or `.\build-and-run-all.bat` on Windows. You'll need JDK 21 and Maven 3.9. Both scripts perform clean builds and stop if an example fails.

## Contents

| Lesson | Topic |
| --- | --- |
| 01 | [Defining your first model](VMF-Tutorial-01/README.md) |
| 02 | [Observing model changes](VMF-Tutorial-02/README.md) |
| 03 | [Containment references](VMF-Tutorial-03/README.md) |
| 03b | [Cross-references](VMF-Tutorial-03b/README.md) |
| 04 | [Undo and redo](VMF-Tutorial-04/README.md) |
| 05 | [The builder API](VMF-Tutorial-05/README.md) |
| 06 | [Object graph traversal and property order](VMF-Tutorial-06/README.md) |
| 07 | [Immutable objects and read-only views](VMF-Tutorial-07/README.md) |
| 08 | [Custom behavior and delegation](VMF-Tutorial-08/README.md) |
| 09 | [Property defaults](VMF-Tutorial-09/README.md) |
| 10 | [Equality and hash codes](VMF-Tutorial-10/README.md) |
| 11 | [Annotations](VMF-Tutorial-11/README.md) |
| 12 | [Deep and shallow copies](VMF-Tutorial-12/README.md) |
| 13 | [Reflection](VMF-Tutorial-13/README.md) |
| 14 | [Generated model documentation](VMF-Tutorial-14/README.md) |
| 15 | [External property types](VMF-Tutorial-15/README.md) |
| 16 | [Generating models with Maven](VMF-Tutorial-16/README.md) |
| 17 | [Portable change journals in JSON, YAML, and XML](VMF-Tutorial-17/README.md) |
| 18 | [Multiplicity, lists and sets](VMF-Tutorial-18/README.md) |
| 19 | [Looking up model objects by key](VMF-Tutorial-19/README.md) |
| 20 | [Generic model relationships and existing JSON](VMF-Tutorial-20/README.md) |
| 20b | [Choosing a generic bound](VMF-Tutorial-20/Tutorial-20b.md) (optional companion to Tutorial 20) |
