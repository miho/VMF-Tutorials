package eu.mihosoft.vmf.tutorial10.vmfmodel;

import eu.mihosoft.vmf.core.*;

interface ObjectToCompare {

    String getId();
    Integer getData();

}

interface ObjectToCompareId {

    String getId();

    @IgnoreEquals
    Integer getData();

}



