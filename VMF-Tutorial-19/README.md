# Tutorial 19 · Looking up model objects by key

**VMF 0.4.1 and later.** This example currently uses `0.4.1-SNAPSHOT`. [Version guide](../README.md#vmf-versions)

A catalog owns its items. Looking up an item by SKU should return the very same object held by the catalog.

```java
@Contains
@Keyed(by = "sku")
Item[] getItems();
```

`catalog.findItemsBySku("G-27")` returns an `Optional<Item>`. The item list is the source of truth. The same declaration can use a model reference as its key. In this example an inspection references its item and owns several readings. `catalog.findInspectionsByItem(gauge)` looks up the inspection by the identity of that item.

For plain values, declare a `VMap<K,V>`. This example binds a generic table to integer years and stock counts.

```java
@InterfaceOnly
interface Table<K, V> {
    VMap<K, V> getEntries();
}

interface StockByYear extends Table<Integer, Integer> {}
```

## Run the example

With JDK 21, open `VMF-Tutorial-19` and run `./gradlew run` on macOS or Linux, or `.\gradlew.bat run` on Windows.
