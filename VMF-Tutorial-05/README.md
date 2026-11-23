# Tutorial 05 · Use generated builders

[Tutorial index](../README.md) · [Previous](../VMF-Tutorial-04/README.md) · [Next](../VMF-Tutorial-06/README.md)

Generate builders to set initial values and copy selected state between model objects. A `Node` inherits `name` from `WithName` and `id` from `WithId`. [Model definition](src/main/vmf/eu/mihosoft/vmf/tutorial05/vmfmodel/Node.java) · [Java example](src/main/java/eu/mihosoft/vmf/tutorial05/Main.java)

## Construct a node

```java
Node source = Node.newBuilder()
        .withName("Original")
        .withId(3)
        .build();
```

`Node.newBuilder()` accepts both inherited properties. Calling `build()` creates the node with those values.

## Copy the name to an existing node

```java
Node destination = Node.newBuilder()
        .withName("Placeholder")
        .withId(9)
        .build();

WithName.newBuilder().applyFrom(source).applyTo(destination);
```

`applyFrom` copies properties exposed by the builder type. The `WithName` builder reads the source name and writes it to `destination` through `applyTo`. The destination keeps its own ID.

## Run the lesson

With JDK 21, run `./gradlew run` from this directory. On Windows, use `.\gradlew.bat run`.

```text
Source: Original (ID 3)
Destination: Original (ID 9)
```
