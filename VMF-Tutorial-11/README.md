# Tutorial 11 · Model annotations

[Tutorial index](../README.md) · [Previous](../VMF-Tutorial-10/README.md) · [Next](../VMF-Tutorial-12/README.md)

Use `@Annotation` to add key-value metadata to a model type or property. In this lesson, a node has two input properties and one output property. The [model definition](src/main/vmf/eu/mihosoft/vmf/tutorial11/vmfmodel/Annotations.java) uses `api` as the metadata key.

```java
interface Node {
    String getId();

    @Annotation(key = "api", value = "input")
    Node getA();

    @Annotation(key = "api", value = "input")
    Node getB();

    @Annotation(key = "api", value = "output")
    Node getC();
}
```

The [example](src/main/java/eu/mihosoft/vmf/tutorial11/Main.java) builds a node with all three ports populated. The instance's reflection API enumerates its properties. For each property, `annotationByKey("api")` returns an optional annotation. The application's filter compares the stored value with the requested role.

```java
node.vmf().reflect().properties().stream()
        .filter(property -> property.annotationByKey("api")
                .map(annotation -> role.equals(annotation.getValue()))
                .orElse(false))
        .forEach(property -> {
            Node target = (Node) property.get();
            System.out.printf("%s %s -> %s%n", role, property.getName(), target.getId());
        });
```

This approach also works for an editor that needs to list ports based on metadata. The application defines what the strings `input` and `output` mean.

## Run the lesson

With JDK 21, run `./gradlew run` from this directory. On Windows, use `.\gradlew.bat run`.

```text
input a -> node:a
input b -> node:b
output c -> node:c
```
