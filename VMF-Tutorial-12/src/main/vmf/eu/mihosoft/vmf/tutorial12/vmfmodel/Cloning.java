package eu.mihosoft.vmf.tutorial12.vmfmodel;

import eu.mihosoft.vmf.core.DelegateTo;
import eu.mihosoft.vmf.core.VMFEquals;

@VMFEquals
interface Store {

    String getId();

    Item[] getItems();

    @DelegateTo(className="eu.mihosoft.vmf.tutorial12.StoreDelegate")
    String toString();

}

@VMFEquals
interface Item {

    String getId();

    @DelegateTo(className="eu.mihosoft.vmf.tutorial12.ItemDelegate")
    String toString();
}





