package eu.mihosoft.vmf.tutorial12.vmfmodel;

import eu.mihosoft.vmf.core.*;

interface Store {

    String getId();

    Item[] getItems();

    @DelegateTo(className="eu.mihosoft.vmf.tutorial12.StoreDelegate")
    String toString();

}

interface Item {

    String getId();

    @DelegateTo(className="eu.mihosoft.vmf.tutorial12.ItemDelegate")
    String toString();
}





