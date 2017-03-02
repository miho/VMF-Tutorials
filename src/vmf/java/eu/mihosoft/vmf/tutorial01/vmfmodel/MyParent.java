package eu.mihosoft.vmf.tutorial01.vmfmodel;

import eu.mihosoft.vmf.core.Container;
import eu.mihosoft.vmf.core.Contains;


interface MyParent {
    @Contains(opposite = "parent")
    MyChild[] getChildren();

    String getName();

    MyChild getAnotherChild();
}

interface MyChild {
    @Container(opposite="parent")
    MyParent getParent();

    String getName();

    MyParent getParentRef();

    MyParent getParentRef2();
}
