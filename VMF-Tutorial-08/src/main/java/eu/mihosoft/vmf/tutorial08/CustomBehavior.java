package eu.mihosoft.vmf.tutorial08;

import eu.mihosoft.vmf.runtime.core.DelegatedBehavior;

public class CustomBehavior implements DelegatedBehavior<ObjectWithCustomBehavior> {
    private ObjectWithCustomBehavior caller;

    @Override
    public void setCaller(ObjectWithCustomBehavior caller) {
        this.caller = caller;
    }

    /**
     * Delegated behavior. It is called whenever {@code caller.computeSum()} is called. 
     * This method computes and returns the sum of property 'a' and 'b'.
     * @return sum of 'a' and 'b'
     */
    public int computeSum() {
        return caller.getA() + caller.getB();
    }
}