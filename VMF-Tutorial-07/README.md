# Tutorial 07 · Immutable objects and read-only views

[Tutorial index](../README.md) · [Previous](../VMF-Tutorial-06/README.md) · [Next](../VMF-Tutorial-08/README.md)

Immutable objects have fixed values. A read-only view exposes the current values of a mutable object. [Model](src/main/vmf/eu/mihosoft/vmf/tutorial07/vmfmodel/ImmutableType.java) · [Runnable example](src/main/java/eu/mihosoft/vmf/tutorial07/Main.java)

## Build an immutable object

Declare `@Immutable` on the model interface. The generated builder accepts the initial values and the generated object exposes getters. In this model, `value` is an `int`.

```java
ImmutableObject immutable = ImmutableObject.newBuilder().withValue(12).build();
System.out.println(immutable.getValue());
```

Calling `asReadOnly()` or `clone()` on a generated immutable object returns the same instance.

## Read a live view or make an independent copy

```java
MutableObject original = MutableObject.newBuilder().withValue(12).build();
ReadOnlyMutableObject view = original.asReadOnly();
original.setValue(24);
System.out.println(view.getValue()); // 24

MutableObject copy = view.asModifiable();
copy.setValue(36);
System.out.println(view.getValue()); // 24
System.out.println(copy.getValue()); // 36
```

The read-only interface exposes the original's current state through its getters. `asModifiable()` copies the VMF graph, so editing the returned object leaves the original state intact.

## Share a getter interface

Use an `@InterfaceOnly` type to declare a common getter contract. Mark the property `@GetterOnly` and choose a value type suitable for both variants, such as `String`.

```java
@InterfaceOnly
interface WithName {
    @GetterOnly
    String getName();
}

@Immutable
interface ImmutableNamed extends WithName { }

interface MutableNamed extends WithName { }
```

Both concrete types can be assigned to `WithName`. The common interface exposes only the getter, while the concrete mutable type also has a setter.

## Run the lesson

With JDK 21, run `./gradlew run` from this directory. On Windows, use `.\gradlew.bat run`.

```text
Immutable value: 12
Live view after editing original: 24
Original through view: 24
Independent copy: 36
Shared interface: immutable, mutable
```
