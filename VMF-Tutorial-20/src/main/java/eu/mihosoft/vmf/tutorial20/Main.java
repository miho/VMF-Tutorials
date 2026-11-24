package eu.mihosoft.vmf.tutorial20;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.mihosoft.vmf.jackson.VMFJacksonModule;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class Main {
    public static void main(String[] args) throws IOException {
        var mode = VMFJacksonModule.RUNTIME_TYPE.EXPERIMENTAL;
        var json = new ObjectMapper().registerModule(VMFJacksonModule.newInstance(mode));
        JsonNode supplier;
        try (InputStream source = Objects.requireNonNull(Main.class.getResourceAsStream("/catalog.json"))) {
            supplier = json.readTree(source);
        }
        Catalog catalog = json.treeToValue(supplier, Catalog.class);

        var exported = json.readTree(json.writeValueAsBytes(catalog));
        if (!exported.equals(supplier)) throw new AssertionError("Supplier document changed on round trip");

        Shelf<Instrument> shelf = catalog;
        Instrument gauge = featureFirst(shelf);
        System.out.println("Read " + gauge.getSku() + " from the supplier's JSON");

        Label label = gauge.getLabel();
        if (catalog.getLabels().contains(label)) throw new AssertionError("Input described separate labels");
        catalog.getLabels().add(label);
        ReadOnlyShelf<ReadOnlyInstrument> view = catalog.asReadOnly();
        if (view.getFeatured() != gauge.asReadOnly()) throw new AssertionError("Expected live read-only view");

        var graph = new ObjectMapper().registerModule(VMFJacksonModule.newInstance(mode).withReferences(true));
        Catalog restored = graph.readValue(graph.writeValueAsBytes(catalog), Catalog.class);
        Instrument featured = restored.getFeatured();
        if (featured != restored.getProducts().get(0)) throw new AssertionError("Lost the featured instrument");
        if (restored.getLabels().size() != 2 || !restored.getLabels().contains(featured.getLabel())) {
            throw new AssertionError("Lost a label identity");
        }
        System.out.println("Restored the featured instrument and both equal-valued labels");

        demonstrateStockroom(graph);
    }

    private static void demonstrateStockroom(ObjectMapper graph) throws IOException {
        SparePart filter = SparePart.newBuilder().withSku("F-12").withPackSize(4).build();
        Stockroom stockroom = Stockroom.newBuilder().withLocation("Bin B4").withProducts(filter).build();
        LocatedShelf<SparePart> parts = stockroom;
        SparePart featured = featureFirst(parts);
        if (featured != filter) throw new AssertionError("Expected the filter on the shelf");

        Stockroom restored = graph.readValue(graph.writeValueAsBytes(stockroom), Stockroom.class);
        ReadOnlyLocatedShelf<ReadOnlySparePart> view = restored.asReadOnly();
        ReadOnlySparePart saved = view.getFeatured();
        if (saved != view.getProducts().get(0) || saved.getPackSize() != 4 || !"Bin B4".equals(view.getLocation())) {
            throw new AssertionError("Lost the featured part, pack size, or shelf location");
        }
        System.out.println("Featured " + saved.getSku() + " at " + view.getLocation()
                + " (pack of " + saved.getPackSize() + ")");
    }

    private static <T extends Product> T featureFirst(Shelf<T> shelf) {
        T first = shelf.getProducts().get(0);
        shelf.setFeatured(first);
        return first;
    }
}
