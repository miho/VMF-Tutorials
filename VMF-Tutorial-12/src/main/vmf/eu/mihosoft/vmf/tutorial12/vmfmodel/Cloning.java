package eu.mihosoft.vmf.tutorial12.vmfmodel;

import eu.mihosoft.vmf.core.*;

interface Store {

    String getId();

    @Contains(opposite="parent")
    Item[] getItems();

    @DelegateTo(className="eu.mihosoft.vmf.tutorial12.StoreDelegate")
    String toString();

}

interface Item {

    String getId();

    @Container(opposite="items")
    Store getParent();

    @DelegateTo(className="eu.mihosoft.vmf.tutorial12.ItemDelegate")
    String toString();
}





