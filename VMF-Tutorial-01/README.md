# Tutorial 01 · Define your first VMF model

[Tutorial index](../README.md) · [Next](../VMF-Tutorial-02/README.md)

Define a model as a Java interface, then use the generated type from regular Java code.

## Define and generate the model

The Gradle build uses the `eu.mihosoft.vmf` plugin. Put the model definition under `src/main/vmf` in a package ending in `.vmfmodel`. The interface declares the property through its getter. [Model definition](src/main/vmf/eu/mihosoft/vmf/tutorial01/vmfmodel/MyParent.java)

```java
package eu.mihosoft.vmf.tutorial01.vmfmodel;

interface Parent {
    String getName();
}
```

VMF generates `Parent` in `eu.mihosoft.vmf.tutorial01`. The generated interface has a factory for new instances and a setter for the mutable `name` property. `Main` lives in that same package. [Java example](src/main/java/eu/mihosoft/vmf/tutorial01/Main.java)

```java
Parent parent = Parent.newInstance();
parent.setName("My Name");
System.out.println("Parent name: " + parent.getName());
```

## Run the lesson

With JDK 21, run `./gradlew run` from this directory. On Windows, use `.\gradlew.bat run`. The Gradle 9 wrapper runs generation before compiling the Java example.

```text
Parent name: My Name
```

To generate sources without running the example, call `./gradlew vmfGenModelSources` or `.\gradlew.bat vmfGenModelSources`. Look for the generated Java under `build/generated-src/vmf-main`.
