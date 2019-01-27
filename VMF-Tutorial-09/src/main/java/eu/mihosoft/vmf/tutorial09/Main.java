package eu.mihosoft.vmf.tutorial09;

import java.util.function.Function;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // first, we create an object
        ObjectWithDefaultValues obj = ObjectWithDefaultValues.newInstance();

        // now we can get the default values
        System.out.println("Value: " + obj.getValue());
        System.out.println("Name:  " + obj.getName());

        // we use `p.isSet()` to check if the property is set
        Function<String,String> propertySetOrUnset = (propName) -> {
            return obj.vmf().reflect().propertyByName(propName).
                   map(p->""+p.isSet()).orElse("<not available>");
        };

        // we expect both properties to be unset (`false`)
        System.out.println("Value is set:  " + propertySetOrUnset.apply("value") );
        System.out.println("Name is set:   " + propertySetOrUnset.apply("name")  );
        
        System.out.println("--");

        // if we set a property to a different value we expect it to be set (`true`)
        obj.setName("another name");
        System.out.println("Value is set:  " + propertySetOrUnset.apply("value") );
        System.out.println("Name is set:   " + propertySetOrUnset.apply("name")  );

        System.out.println("--");
        
        // unset name property, name should be shown as unset (`true`)
        obj.vmf().reflect().propertyByName("name").ifPresent(p->p.unset());
        System.out.println("Value is set:  " + propertySetOrUnset.apply("value") );
        System.out.println("Name is set:   " + propertySetOrUnset.apply("name")  );

    }
}
