# Tutorial 12 · Deep and shallow copies

[Tutorial index](../README.md) · [Previous](../VMF-Tutorial-11/README.md) · [Next](../VMF-Tutorial-13/README.md)

Use `vmf().content().deepCopy()` to copy a model graph. Use `shallowCopy()` when a new top-level object should reuse the original reference values.

The [model](src/main/vmf/eu/mihosoft/vmf/tutorial12/vmfmodel/Cloning.java) gives each `Store` an ID and a list of referenced `Item` objects. The items are ordinary references, so two stores can refer to the same item.

```java
interface Store {
    String getId();
    Item[] getItems();
}

interface Item {
    String getId();
}
```

The [example](src/main/java/eu/mihosoft/vmf/tutorial12/Main.java) builds a store and makes one copy of each kind. It changes both copies' store IDs and then edits the first item in each copy.

```java
Store deepCopy = store.vmf().content().deepCopy();
Store shallowCopy = store.vmf().content().shallowCopy();
deepCopy.setId("deep copy");
shallowCopy.setId("shallow copy");

deepCopy.getItems().get(0).setId("deep edit");
shallowCopy.getItems().get(0).setId("shared edit");
```

The deep copy has its own first item. The shallow copy reuses the original item, so the last mutation is visible through both stores. Use a deep copy when the copied graph needs its own contained children. A populated containment list cannot be shallow-copied because its children have a single owner.

## Run the lesson

With JDK 21, run `./gradlew run` from this directory. On Windows, use `.\gradlew.bat run`. The first item printed for each store shows whether it was copied or shared.

| Store | First item ID |
| --- | --- |
| Original | `shared edit` |
| Deep copy | `deep edit` |
| Shallow copy | `shared edit` |
