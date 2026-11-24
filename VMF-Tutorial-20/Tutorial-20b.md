# Tutorial 20b · Choosing a generic bound

**VMF 0.4.1 and later.** These examples and generator diagnostics were verified with `0.4.1-SNAPSHOT`. [Version guide](../README.md#vmf-versions)

A type parameter on a model interface can describe either a value or a model reference. The supplier’s shelf holds products with SKUs. If the stockroom also recorded a delivery count, that property could use a numeric value. The model snippets below use Tutorial 20’s `eu.mihosoft.vmf.tutorial20.vmfmodel` package. [Catalog.java](src/main/vmf/eu/mihosoft/vmf/tutorial20/vmfmodel/Catalog.java)

## Give a shelf a concrete type

`Shelf<T extends Product>` in Tutorial 20 is marked `@InterfaceOnly`. It defines the API shared by every shelf. Create an instance of a named specialization such as `Catalog` or `Stockroom`.

| Concrete type | Binding | Type of `getFeatured()` |
| --- | --- | --- |
| `Catalog` | `Catalog extends Shelf<Instrument>` | `Instrument` |
| `Stockroom` | `Stockroom extends LocatedShelf<SparePart>`, where `LocatedShelf<P extends Product> extends Shelf<P>` | `SparePart` |

The named model bound also tells VMF which read-only interface to generate. Once a catalog has a featured instrument, a reader can access its SKU through the generated API.

```java
ReadOnlyShelf<ReadOnlyInstrument> shelf = catalog.asReadOnly();
ReadOnlyInstrument featured = shelf.getFeatured();
String sku = featured.getSku();
```

## Constrain a value or relate two types

A stock count can use a scalar upper bound. Binding `N` to `Integer` makes `PartCount.getCount()` return `Integer`.

```java
@InterfaceOnly
interface StockCount<N extends Number> {
    N getCount();
}

interface PartCount extends StockCount<Integer> {}
```

A parameter may also refer to an earlier parameter. Here `S extends B` means the featured product must be a subtype of the chosen base type, or that type itself.

```java
@InterfaceOnly
interface Selection<B extends Product, S extends B> {
    B getCandidate();
    S getFeatured();
}

interface Filter extends SparePart {}
interface PartsSelection extends Selection<SparePart, Filter> {}
```

`PartsSelection.getCandidate()` returns `SparePart` and `getFeatured()` returns `Filter`. Their read-only counterparts return `ReadOnlySparePart` and `ReadOnlyFilter`. Each resulting property must still be a type that VMF supports for that property.

## Require two model interfaces

Suppose every product on a shipment shelf needs a SKU and a tracking code. VMF rejects a model intersection such as `T extends Product & Tracked`. Give the combined contract a name, then bind the shelf to a product that explicitly implements it.

```java
@InterfaceOnly
interface Tracked {
    String getTrackingCode();
}

@InterfaceOnly
interface TrackedProduct extends Product, Tracked {}

@InterfaceOnly
interface ShipmentShelf<T extends TrackedProduct> extends Shelf<T> {}

interface ShippingCrate extends TrackedProduct {}
interface Shipment extends ShipmentShelf<ShippingCrate> {}
```

A `Shipment` now inherits the ordered product collection from `Shelf`. Its featured crate exposes both `getSku()` and `getTrackingCode()`, including through its read-only view.

## Recognize a rejected declaration

| Declaration the generator rejects | How to express the intent |
| --- | --- |
| `interface RawCatalog extends Shelf {}` | Supply a type argument, as in `Catalog extends Shelf<Instrument>`. |
| `@InterfaceOnly interface Rank<T extends Comparable<T>> { T getRank(); }` | VMF rejects parameterized bounds. For numeric ranks use `T extends Number`. |
| `interface Pick extends Slot<Instrument> {}` with `@InterfaceOnly interface Slot<T> { T getFeatured(); }` | A mutable model binding needs a named model upper bound. Declare `Slot<T extends Product>` so VMF can project the result to `ReadOnlyInstrument`. |

[Generator validation rules](https://github.com/miho/VMF/blob/4261fe098401493a934c3a65c20373e4c5b59968/core/src/main/java/eu/mihosoft/vmf/core/GenericModelTypes.java) · [Validation tests](https://github.com/miho/VMF/blob/4261fe098401493a934c3a65c20373e4c5b59968/core/src/test/java/eu/mihosoft/vmf/generics/vmfmodel/GenericModelValidationTest.java) · [Run Tutorial 20](README.md#run-the-example)
