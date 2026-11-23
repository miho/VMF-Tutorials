# Tutorial 10 · Equality and hash codes

[Tutorial index](../README.md) · [Previous](../VMF-Tutorial-09/README.md) · [Next](../VMF-Tutorial-11/README.md)

The two model types have the same scalar properties, `id` and `data`. Their equality rules use different sets of properties. [Model](src/main/vmf/eu/mihosoft/vmf/tutorial10/vmfmodel/EqualsAndHashCode.java)

## Compare property values

Add `@VMFEquals` to generate `equals()` and `hashCode()` for the type.

```java
@VMFEquals
interface ObjectToCompare {
    String getId();
    Integer getData();
}
```

The example creates three instances with the same ID. The first two have the same data, so `first.equals(same)` returns `true` even though the instances are distinct. The third has a different data value, so `first.equals(differentData)` returns `false`. [Runnable example](src/main/java/eu/mihosoft/vmf/tutorial10/Main.java)

## Exclude a property

```java
@VMFEquals
interface ObjectToCompareId {
    String getId();

    @IgnoreEquals
    Integer getData();
}
```

`@IgnoreEquals` excludes `data` from comparison and hashing. Two `ObjectToCompareId` objects with the same ID compare equal and have equal hash codes. Keep values that participate in equality stable while an object is a key in a hash-based collection.

An unconfigured mutable type uses instance equality by default. You can set a model-wide default once with `@VMFModel(equality = VMFEquals.EqualsType.CONTAINMENT_AND_EXTERNAL)`, or opt in one type at a time with `@VMFEquals`. When using instance equality, `object.vmf().content().equals(other)` and `object.vmf().content().hashCode()` still expose the generated content comparison and hash.

## Run the lesson

With JDK 21, run `./gradlew run` from this directory. On Windows, use `.\gradlew.bat run`.

```text
Distinct instances: true
Same ID and data: true
Same ID, different data: false
Ignoring data, same ID: true
Ignoring data, same hash: true
```
