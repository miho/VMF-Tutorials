package eu.mihosoft.vmf.tutorial07;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // the only way to set the 'value' property for an immutable object is to
        // use the builder pattern. there is no setter method for the 'value' property
        ImmutableObject immutableObject = ImmutableObject.newBuilder().withValue(12).build();
        
        // does not compile because we can't change the initial state of immutable objects
        // immutableObject.setValue(12);     
        
        // the mutable object works exactly as expected. we can create the instance
        // and set the 'value' property afterwards.
        MutableObject mutableObject = MutableObject.newInstance();

        mutableObject.setValue(12);
        
        // to prevent that receivers of mutable objects can change their state, VMF
        // generates a read-only counterpart for each model type:
        ReadOnlyMutableObject readOnlyMutable = mutableObject.asReadOnly();

        // this won't compile
        readOnlyMutable.setValue();

        // creates a modifiable copy (deep copy)
        MutableObject mutableObject2 = readOnlyMutable.asModifiable();

    }
}
