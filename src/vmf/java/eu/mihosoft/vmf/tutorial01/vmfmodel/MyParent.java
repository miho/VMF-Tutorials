package eu.mihosoft.vmf.tutorial01.vmfmodel;

import eu.mihosoft.vmf.core.Container;
import eu.mihosoft.vmf.core.Contains;


interface NamedElement {
    String getName();
}

interface MyParent extends NamedElement{
    @Contains(opposite = "parent")
    MyChild[] getChildren();
}

interface MyChild extends NamedElement{
    @Container(opposite="parent")
    MyParent getParent();

}

