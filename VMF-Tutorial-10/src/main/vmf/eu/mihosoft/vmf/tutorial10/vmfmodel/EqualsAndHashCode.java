package eu.mihosoft.vmf.tutorial10.vmfmodel;

import eu.mihosoft.vmf.core.*;

@VMFEquals()
interface ObjectToCompare {

    String getId();
    Integer getData();

}

@VMFEquals()
interface ObjectToCompareId {

    String getId();

    @IgnoreEquals
    Integer getData();

}



