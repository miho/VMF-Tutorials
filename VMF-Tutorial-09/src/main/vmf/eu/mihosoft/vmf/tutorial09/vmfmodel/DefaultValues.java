package eu.mihosoft.vmf.tutorial09.vmfmodel;

import eu.mihosoft.vmf.core.*;

interface ObjectWithDefaultValues {
    
    @DefaultValue(value="23")
    Integer getValue();

    @DefaultValue(value="\"my name\"")
    String getName();

}



