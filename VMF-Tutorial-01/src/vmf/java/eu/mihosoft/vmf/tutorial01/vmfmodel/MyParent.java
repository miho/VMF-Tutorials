package eu.mihosoft.vmf.tutorial01.vmfmodel;

import eu.mihosoft.vmf.core.Container;
import eu.mihosoft.vmf.core.Contains;


interface Parent {

    @Contains(opposite = "parent")
    MyChild[] getChildren();

    String getName();
}

interface MyChild {
    @Container(opposite="children")
    Parent getParent();

    int getValue();
}

