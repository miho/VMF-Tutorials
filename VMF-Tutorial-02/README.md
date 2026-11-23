# Tutorial 02 · Observe property changes

[Tutorial index](../README.md) · [Previous](../VMF-Tutorial-01/README.md) · [Next](../VMF-Tutorial-03/README.md)

Subscribe to changes of the `name` property on a generated `Parent`. The callback receives the property name and the old and new values. [Model definition](src/main/vmf/eu/mihosoft/vmf/tutorial02/vmfmodel/Parent.java) · [Java example](src/main/java/eu/mihosoft/vmf/tutorial02/Main.java)

```java
Parent parent = Parent.newInstance();
var subscription = parent.vmf().changes().addPropertyListener("name", change -> {
    var values = change.propertyChange().orElseThrow();
    System.out.printf("%s: %s -> %s%n", change.propertyName(), values.oldValue(), values.newValue());
});

parent.setName("Parent 1");
parent.setName("Parent 2");
subscription.unsubscribe();

parent.setName("Parent 3");
System.out.println("Name after unsubscribe: " + parent.getName());
```

This subscription observes the named property on `parent`. VMF calls it synchronously during each setter. Keep the returned handle to end the subscription when the consumer is done. After `unsubscribe()`, the third setter still changes the model.

## Run the lesson

With JDK 21, run `./gradlew run` from this directory. On Windows, use `.\gradlew.bat run`.

```text
name: null -> Parent 1
name: Parent 1 -> Parent 2
Name after unsubscribe: Parent 3
```
