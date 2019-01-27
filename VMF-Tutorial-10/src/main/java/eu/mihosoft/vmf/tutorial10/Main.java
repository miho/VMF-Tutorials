package eu.mihosoft.vmf.tutorial10;

import java.util.function.Function;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        

        // `obj1` is equal to `obj2` but `obj1` is not equal to `obj3`
        ObjectToCompare obj1 = ObjectToCompare.newBuilder().withId("id:0").withData(7).build();
        ObjectToCompare obj2 = ObjectToCompare.newBuilder().withId("id:0").withData(7).build();
        ObjectToCompare obj3 = ObjectToCompare.newBuilder().withId("id:0").withData(8).build();
        
        System.out.println("Object 1 == Object 2 -> " + obj1.equals(obj2));
        System.out.println("Object 1 == Object 3 -> " + obj1.equals(obj3));

        // we expect this output:
        // Object 1 == Object 2 -> true
        // Object 1 == Object 3 -> false
        
        // in this case all objects are equal because only the `id` properties are compared
        ObjectToCompareId objId1 = ObjectToCompareId.newBuilder().withId("id:0").withData(7).build();
        ObjectToCompareId objId2 = ObjectToCompareId.newBuilder().withId("id:0").withData(7).build();
        ObjectToCompareId objId3 = ObjectToCompareId.newBuilder().withId("id:0").withData(8).build();

        System.out.println("--");

        System.out.println("Object 1 == Object 2 -> " + objId1.equals(objId2));
        System.out.println("Object 1 == Object 3 -> " + objId1.equals(objId3));

        // we expect this output:
        // Object 1 == Object 2 -> true
        // Object 1 == Object 3 -> true

    }
}
