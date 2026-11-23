package eu.mihosoft.vmf.tutorial08;

import eu.mihosoft.vmf.runtime.core.DelegatedBehavior;

public class CustomBehavior implements DelegatedBehavior<ObjectWithCustomBehavior> {
    private ObjectWithCustomBehavior caller;

    @Override
    public void setCaller(ObjectWithCustomBehavior caller) {
        this.caller = caller;
    }

    public int computeSum() {
        return caller.getA() + caller.getB();
    }

    public void onObjectWithCustomBehaviorInstantiated() {
        System.out.println("Object instantiated");
    }
}
