package eu.mihosoft.vmf.tutorial12;

import eu.mihosoft.vmf.runtime.core.DelegatedBehavior;

public class ItemDelegate implements DelegatedBehavior<Item> {
    
    private Item caller;
    @Override
    public void setCaller(Item caller) {
        this.caller = caller;
    }

    public String toString() {
        return "item: " + caller.getId();
    }
}