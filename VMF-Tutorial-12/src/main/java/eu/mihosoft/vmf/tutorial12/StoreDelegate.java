package eu.mihosoft.vmf.tutorial12;

import eu.mihosoft.vmf.runtime.core.DelegatedBehavior;

public class StoreDelegate implements DelegatedBehavior<Store> {
    
    private Store caller;
    @Override
    public void setCaller(Store caller) {
        this.caller = caller;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("> store: " + caller.getId() + " \n");

        for(Item i : caller.getItems()) {
            sb.append(" -> " + i.toString()).append("\n");
        }

        return sb.toString();
    }
}