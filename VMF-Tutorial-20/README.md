# Tutorial 20 · Generic model relationships and existing JSON

A supplier sends a catalog with an `items` array. Each instrument carries a SKU and an inline label. Open [the input document](src/main/resources/catalog.json) to see the entire wire format.

The model declares a reusable `Shelf<T extends Product>`. Its `getProducts()` method owns an ordered set of products and maps to the supplier’s `items` field with `@Annotation(key = "vmf:jackson:rename", value = "items")`. The concrete declaration supplies the type of each item.

```java
interface Catalog extends Shelf<Instrument> {
    Set<Label> getLabels();
}
```

The default Jackson module reads this document directly as a `Catalog`. Each inline label describes a separate value. The generated Java types carry the binding through both views.

```java
Shelf<Instrument> shelf = catalog;
ReadOnlyShelf<ReadOnlyInstrument> view = catalog.asReadOnly();
```

The example then selects the existing instrument as featured and adds its label to the catalog’s labels. At that point the graph contains two equal-valued labels, one shared between two properties. Saving with `withReferences(true)` preserves which instrument is featured and which label it shares. On reload the program checks both identities.

Run `./gradlew run` or `.\gradlew.bat run` on Windows. Install the feature builds described in [Tutorial 18](../VMF-Tutorial-18/README.md) first, then run `./gradlew publishToMavenLocal` in VMF’s `jackson` directory.
