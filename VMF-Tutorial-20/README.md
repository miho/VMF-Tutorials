# Tutorial 20 · Generic model relationships and existing JSON

**VMF 0.4.1 and later.** This example currently uses `0.4.1-SNAPSHOT`. [Version guide](../README.md#vmf-versions)

A supplier sends a catalog with an `items` array. Each instrument carries a SKU and an inline label. Open [the input document](src/main/resources/catalog.json) to see the entire wire format.

The catalog and a spare-parts stockroom use the same `Shelf<T>`. A shelf owns an ordered set of products and can feature one of them. [Model definition](src/main/vmf/eu/mihosoft/vmf/tutorial20/vmfmodel/Catalog.java)

```java
@InterfaceOnly
interface Shelf<T extends Product> {
    @Contains
    @Multiplicity(kind = Multiplicity.Kind.ORDERED_SET)
    @Annotation(key = "vmf:jackson:rename", value = "items")
    T[] getProducts();

    T getFeatured();
}

interface Catalog extends Shelf<Instrument> {
    Set<Label> getLabels();
}

@InterfaceOnly
interface LocatedShelf<P extends Product> extends Shelf<P> {
    String getLocation();
}

interface Stockroom extends LocatedShelf<SparePart> {}
```

`Catalog` binds `T` directly to `Instrument`. `LocatedShelf` adds a location and passes its own parameter `P` to `Shelf`. When `Stockroom` binds `P` to `SparePart`, its inherited `getProducts()` returns an `OrderedRefSet<SparePart>` and `getFeatured()` returns a `SparePart`.

Both shelves contain an item, so the same method can feature the first product on either shelf.

```java
private static <T extends Product> T featureFirst(Shelf<T> shelf) {
    T first = shelf.getProducts().get(0);
    shelf.setFeatured(first);
    return first;
}
```

```java
Shelf<Instrument> shelf = catalog;
Instrument gauge = featureFirst(shelf);

LocatedShelf<SparePart> parts = stockroom;
SparePart filter = featureFirst(parts);
```

The read-only types follow the same inheritance path. Through `ReadOnlyLocatedShelf<ReadOnlySparePart>` you can call `getLocation()` on the shelf and `getFeatured().getPackSize()` on its part. The corresponding catalog view is a `ReadOnlyShelf<ReadOnlyInstrument>`.

The default Jackson module reads the supplier’s `items` directly into the catalog. Each inline label describes a separate value. The program adds the featured instrument’s label to the catalog’s labels and saves with `withReferences(true)`. After reloading each shelf, it checks that the featured product is the very object in its collection. It also checks which of two equal-valued labels the instrument shares, and that the stockroom retains its location and the part’s pack size. [Runnable example](src/main/java/eu/mihosoft/vmf/tutorial20/Main.java)

For scalar bounds, related type parameters, and products that implement two model interfaces, see [Tutorial 20b · Choosing a generic bound](Tutorial-20b.md).

## Run the example

With JDK 21, open `VMF-Tutorial-20` and run `./gradlew run` on macOS or Linux, or `.\gradlew.bat run` on Windows.
