package eu.mihosoft.vmf.tutorial11;

import java.util.function.Predicate;

import eu.mihosoft.vmf.runtime.core.Property;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // create nodes
        Node n = Node.newBuilder().withId("node:0").build();
        Node a = Node.newBuilder().withId("node:a").build();
        Node b = Node.newBuilder().withId("node:b").build();
        Node c = Node.newBuilder().withId("node:c").build();

        // set a,b,c
        n.setA(a);
        n.setB(b);
        n.setC(c);

        // predicate to filter inputs
        Predicate<Property> isInput = (p) -> {
            return p.annotationByKey("api").
              map(ann->"input".equals(ann.getValue())).orElse(false);
        };

        // predicate to filter outputs
        Predicate<Property> isOutput = (p) -> {
            return p.annotationByKey("api").
              map(ann->"output".equals(ann.getValue())).orElse(false);
        };

        // print inputs & outputs
        System.out.println("Inputs and Outputs:");

        // query inputs:
        n.vmf().reflect().properties().stream().filter(isInput).forEach(p->{
            System.out.println(
                "-> input  param '" + p.getName() + "' -> node: " + ((Node)p.get()).getId()
            );
        });

        // query outputs:
        n.vmf().reflect().properties().stream().filter(isOutput).forEach(p->{
            System.out.println(
                "-> output param '" + p.getName() + "' -> node: " + ((Node)p.get()).getId()
            );
        });

        // expected terminal output:
        //
        // Inputs and Outputs:
        // -> input  param 'a' -> node: node:a
        // -> input  param 'b' -> node: node:b
        // -> output param 'c' -> node: node:c

    }
}
