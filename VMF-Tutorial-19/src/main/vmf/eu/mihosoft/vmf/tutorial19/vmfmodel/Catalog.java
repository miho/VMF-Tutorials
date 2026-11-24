package eu.mihosoft.vmf.tutorial19.vmfmodel;

import eu.mihosoft.vcollections.VMap;
import eu.mihosoft.vmf.core.Contains;
import eu.mihosoft.vmf.core.InterfaceOnly;
import eu.mihosoft.vmf.core.Keyed;

interface Catalog {
    @Contains
    @Keyed(by = "sku")
    Item[] getItems();

    @Contains
    @Keyed(by = "item")
    Inspection[] getInspections();
}

interface Item {
    String getSku();
}

interface Inspection {
    Item getItem();

    @Contains
    Reading[] getReadings();
}

interface Reading {
    double getValue();
}

@InterfaceOnly
interface Table<K, V> {
    VMap<K, V> getEntries();
}

interface StockByYear extends Table<Integer, Integer> {}
