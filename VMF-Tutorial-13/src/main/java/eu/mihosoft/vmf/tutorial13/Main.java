package eu.mihosoft.vmf.tutorial13;

import eu.mihosoft.vmf.runtime.core.Reflect;
import eu.mihosoft.vmf.runtime.core.Type;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Type typeOfA = A.type();
        Reflect reflectionOfA = typeOfA.reflect();

        System.out.println("--------------------");
        System.out.println("Properties of A:");
        reflectionOfA.properties().forEach(p-> {
            System.out.println(" -> " + p.getName());
        });

        // we expect the following output:
        //
        // Properties of A:
        // -> a
        // -> name

        System.out.println("--------------------");

        A a = A.newInstance();
        a.vmf().reflect().propertyByName("name").ifPresent(p-> {
            p.addChangeListener(c-> {
                System.out.println("[CHANGE]:changed property 'name': " + p.get());
            });

            p.set("my new name");
        });

        // we expect the following output: 
        //
        // [CHANGE]:changed property 'name': my new name

        System.out.println("--------------------");

        System.out.println("Super Types of B:");
        B.type().superTypes().forEach(t->{
            System.out.println(" -> " + t.getName());
        });

        // we expect the following output: 
        //
        // Super Types of B:
        // -> eu.mihosoft.vmf.tutorial13.A

        System.out.println("--------------------");

        System.out.println("Annotations of A:");
        A.type().reflect().annotations().forEach(ann-> {
            System.out.println(" -> " + ann.getKey() + ", " + ann.getValue());
        });

        // we expect the following output: 
        //
        // Annotations of A:
        // -> my-key, info about type a

        System.out.println("--------------------");

        System.out.println("Properties of A:");
        reflectionOfA.properties().forEach(p-> {
            System.out.println(" -> property-name : " + p.getName());
            System.out.println("    type name     : " + p.getType().getName());
            System.out.println("    model-type    : " + p.getType().isModelType());
        });

        // we expect the following output:
        // Properties of A:
        // -> property-name : a
        //    type name     : eu.mihosoft.vmf.tutorial13.A
        //    model-type    : true
        // -> property-name : name
        //    type name     : java.lang.String
        //    model-type    : false

    }
}
