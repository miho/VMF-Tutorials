package eu.mihosoft.vmf.tutorial01.vmfmodel;

import eu.mihosoft.vmf.core.Container;
import eu.mihosoft.vmf.core.Contains;
import eu.mihosoft.vmf.core.InterfaceOnly;


interface NamedElement {
    String getName();
}

interface MyParent extends NamedElement{
    @Contains(opposite = "parent")
    MyChild[] getChildren();

    NamedElement[] getElements();
}

interface MyChild extends NamedElement{
    @Container(opposite="children")
    MyParent getParent();
}

