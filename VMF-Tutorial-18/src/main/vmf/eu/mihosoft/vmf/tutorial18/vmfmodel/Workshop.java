package eu.mihosoft.vmf.tutorial18.vmfmodel;

import eu.mihosoft.vmf.core.Contains;
import eu.mihosoft.vmf.core.Multiplicity;
import java.util.List;
import java.util.Set;

interface Workshop {
    @Contains
    @Multiplicity(lower = 1, upper = 4)
    Station[] getStations();

    Set<String> getCapabilities();

    @Multiplicity(kind = Multiplicity.Kind.ORDERED_SET)
    List<String> getSetupSteps();
}

interface Station {
    @Multiplicity(lower = 1)
    String getName();
}
