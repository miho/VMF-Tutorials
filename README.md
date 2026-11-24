# VMF Tutorials

[VMF](https://github.com/miho/VMF) generates Java model APIs from annotated Java interfaces. Lessons 01–17 use [VMF 0.4.0](https://github.com/miho/VMF/releases/tag/v0.4.0). Lessons 18 and 19 use the collection feature branch and include local build instructions. Each lesson has a model definition and a program you can run.

## Run a tutorial

Install JDK 21 and clone this repository. From a Gradle tutorial directory, such as `VMF-Tutorial-01`, run the example with the included Gradle wrapper.

```sh
./gradlew run
```

On Windows, run `.\gradlew.bat run`. The first build downloads Gradle and the released dependencies. The model definitions live under `src/main/vmf`, and the VMF plugin writes the generated Java sources to `build/generated-src/vmf-main`.

Tutorial 16 uses Maven 3.9 and the VMF Maven plugin from Maven Central. From its directory, run `mvn clean verify` to generate the model, build the project, and run the example. [Maven tutorial](VMF-Tutorial-16/README.md)

## Run every tutorial

Install the feature builds described in [Tutorial 18](VMF-Tutorial-18/README.md) for lessons 18 and 19. From the repository root, run `./build-and-run-all.sh` on macOS or Linux, or `.\build-and-run-all.bat` on Windows. You'll need JDK 21 and Maven 3.9. Both scripts perform clean builds and stop if an example fails.

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
