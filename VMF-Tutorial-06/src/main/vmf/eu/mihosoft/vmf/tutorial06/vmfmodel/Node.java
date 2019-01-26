package eu.mihosoft.vmf.tutorial06.vmfmodel;

import eu.mihosoft.vmf.core.*;

interface Node {

    @PropertyOrder(index = 0)
    String getName();

    @PropertyOrder(index = 1)
    Boolean getVisible();

    @PropertyOrder(index = 4)
    Node getChild1();
    @PropertyOrder(index = 3)
    Node getChild2();
    @PropertyOrder(index = 2)
    Node getChild3();
}

