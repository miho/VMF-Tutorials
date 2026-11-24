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

        Shelf<Instrument> shelf = catalog;
        Instrument gauge = shelf.getProducts().get(0);
        Label label = gauge.getLabel();
        if (catalog.getLabels().contains(label)) throw new AssertionError("Input described separate labels");
        var exported = json.readTree(json.writeValueAsBytes(catalog));
        if (!exported.equals(supplier)) throw new AssertionError("Supplier document changed on round trip");
        System.out.println("Read " + gauge.getSku() + " from the supplier's JSON");

        shelf.setFeatured(gauge);
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
    }
}
