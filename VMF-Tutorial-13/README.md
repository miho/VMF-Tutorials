# Tutorial 13 · The reflection API

[Tutorial index](../README.md) · [Previous](../VMF-Tutorial-12/README.md) · [Next](../VMF-Tutorial-14/README.md)

VMF reflection lets tools discover a model's types and properties at runtime and work with properties by name. The [model](src/main/vmf/eu/mihosoft/vmf/tutorial13/vmfmodel/Reflection.java) defines a type `A` with a string property and a reference to another `A`. Type `B` extends `A`.

## Inspect a type

Start with the generated interface's static type information. A property descriptor gives you its name and declared type.

```java
Reflect type = A.type().reflect();
for (Property property : type.properties()) {
    System.out.printf("%s: %s (model type: %s)%n", property.getName(),
            property.getType().getName(), property.getType().isModelType());
}
```

The `a` property has a model type, while `name` is a Java string. The [example](src/main/java/eu/mihosoft/vmf/tutorial13/Main.java) also uses `B.type().superTypes()` to find the parent type and `type.annotationByKey("my-key")` to read the annotation declared on `A`. See [Tutorial 11](../VMF-Tutorial-11/README.md) for property annotations.

## Edit an instance

Use `a.vmf().reflect()` to read or edit the values of a particular instance. This form also gives a listener a concrete object to observe.

```java
A a = A.newInstance();
Property name = a.vmf().reflect().propertyByName("name").orElseThrow();
var listener = name.addChangeListener(change ->
        System.out.println("name changed to " + a.getName()));
name.set("my new name");
listener.unsubscribe();
```

The reflective call sets the same property as `a.setName(...)` and triggers its change listener.

## Run the lesson

With JDK 21, run `./gradlew run` from this directory. On Windows, use `.\gradlew.bat run`.

```text
Properties of A:
a: eu.mihosoft.vmf.tutorial13.A (model type: true)
name: java.lang.String (model type: false)
name changed to my new name
Supertypes of B:
eu.mihosoft.vmf.tutorial13.A
Annotation on A: info about type a
```
