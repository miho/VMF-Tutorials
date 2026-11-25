package eu.mihosoft.vmf.tutorial18.vmfmodel;

import eu.mihosoft.vmf.core.Contains;
import eu.mihosoft.vmf.core.Container;
import eu.mihosoft.vmf.core.Length;
import eu.mihosoft.vmf.core.Multiplicity;
import eu.mihosoft.vmf.core.Pattern;
import eu.mihosoft.vmf.core.Range;
import eu.mihosoft.vmf.core.VMFEquals;
import java.util.List;
import java.util.Set;

interface Workshop {
    @Contains(opposite = "workshop")
    @Multiplicity(lower = 1, upper = 4, kind = Multiplicity.Kind.ORDERED_SET)
    Station[] getStations();

    Set<Station> getInspectionStations();

    Set<String> getCapabilities();

    @Multiplicity(kind = Multiplicity.Kind.ORDERED_SET)
    List<String> getSetupSteps();
}

@VMFEquals(VMFEquals.EqualsType.CONTAINMENT_AND_EXTERNAL)
interface Station {
    @Container(opposite = "stations")
    Workshop getWorkshop();

    @Multiplicity(lower = 1)
    @Length(min = 2, max = 24)
    @Pattern("^[A-Z][A-Za-z ]*$")
    String getName();

    @Range(min = "1", max = "100")
    int getPowerWatts();
}
