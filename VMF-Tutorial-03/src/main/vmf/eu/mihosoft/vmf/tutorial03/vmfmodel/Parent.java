package eu.mihosoft.vmf.tutorial03.vmfmodel;

import eu.mihosoft.vmf.core.Container;
import eu.mihosoft.vmf.core.Contains;


interface Parent {

    @Contains(opposite = "parent")
    Child getChild();

    String getName();
}

interface Child {
    @Container(opposite="child")
    Parent getParent();

    int getValue();
}