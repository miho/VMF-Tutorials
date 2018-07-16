package eu.mihosoft.vmf.tutorial04.vmfmodel;

import eu.mihosoft.vmf.core.Container;
import eu.mihosoft.vmf.core.Contains;


interface Node {

    String getName();

    @Contains(opposite = "parent")
    Node[] getChildren();

    @Container(opposite = "children")
    Node getParent();

}