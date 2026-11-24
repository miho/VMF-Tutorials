package eu.mihosoft.vmf.tutorial20.vmfmodel;

import eu.mihosoft.vmf.core.Annotation;
import eu.mihosoft.vmf.core.Contains;
import eu.mihosoft.vmf.core.Immutable;
import eu.mihosoft.vmf.core.InterfaceOnly;
import eu.mihosoft.vmf.core.Multiplicity;
import java.util.Set;

@InterfaceOnly
interface Product {
    String getSku();
}

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

interface Instrument extends Product {
    Label getLabel();
}

@Immutable
interface Label {
    String getText();
}
