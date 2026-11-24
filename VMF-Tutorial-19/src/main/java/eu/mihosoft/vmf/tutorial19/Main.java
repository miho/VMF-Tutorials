package eu.mihosoft.vmf.tutorial19;

public class Main {
    public static void main(String[] args) {
        var catalog = Catalog.newInstance();
        var gauge = Item.newBuilder().withSku("G-27").build();
        catalog.getItems().add(gauge);
        if (catalog.findItemsBySku("G-27").orElseThrow() != gauge) throw new AssertionError("SKU lookup");
        gauge.setSku("G-28");
        if (catalog.findItemsBySku("G-27").isPresent()) throw new AssertionError("Stale SKU");
        if (catalog.asReadOnly().findItemsBySku("G-28").isEmpty()) throw new AssertionError("Read-only lookup");

        var inspection = Inspection.newBuilder().withItem(gauge).build();
        inspection.getReadings().add(Reading.newBuilder().withValue(19.75).build());
        catalog.getInspections().add(inspection);
        var reading = catalog.findInspectionsByItem(gauge).orElseThrow().getReadings().get(0);
        System.out.println(gauge.getSku() + " measured " + reading.getValue());

        var stock = StockByYear.newInstance();
        stock.getEntries().put(2026, 12);
        Table<Integer, Integer> years = stock;
        if (years.getEntries().get(2026) != 12) throw new AssertionError("Typed integer lookup");
    }
}
