# Tutorial 09 · Property defaults

[Tutorial index](../README.md) · [Previous](../VMF-Tutorial-08/README.md) · [Next](../VMF-Tutorial-10/README.md)

Use `@DefaultValue` to choose a property's initial value. VMF reports a property as unset whenever its current value equals its default.

## Declare defaults

Supply Java expressions in the annotations. The string literal includes escaped quotation marks so the generator can emit it as Java source. [Model](src/main/vmf/eu/mihosoft/vmf/tutorial09/vmfmodel/DefaultValues.java)

```java
interface ObjectWithDefaultValues {
    @DefaultValue("23")
    Integer getValue();

    @DefaultValue("\"my name\"")
    String getName();
}
```

## Inspect and restore the default

```java
ObjectWithDefaultValues object = ObjectWithDefaultValues.newInstance();
var name = object.vmf().reflect().propertyByName("name").orElseThrow();

System.out.println(name.isSet()); // false
object.setName("another name");
System.out.println(name.isSet()); // true
name.unset();
System.out.println(name.isSet()); // false
```

`unset()` restores the default, so the final getter returns `"my name"`. Setting the property to that same value explicitly also makes `isSet()` return `false`.

## Run the lesson

With JDK 21, run `./gradlew run` from this directory. On Windows, use `.\gradlew.bat run`. The example displays both initial values and the name's status through a complete set-and-unset cycle. [Runnable example](src/main/java/eu/mihosoft/vmf/tutorial09/Main.java)

```text
Initial value = 23, isSet = false
Initial name = my name, isSet = false
After set: name = another name, isSet = true
After unset: name = my name, isSet = false
```
