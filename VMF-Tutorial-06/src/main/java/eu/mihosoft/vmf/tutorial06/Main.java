package eu.mihosoft.vmf.tutorial06;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // create a new Node instance (this is our root)
        Node root = Node.newBuilder().withName("root").withVisible(true).build();

        // create two children
        Node child1 = Node.newBuilder().withName("child 1").withVisible(true).build();
        Node child2 = Node.newBuilder().withName("child 2").withVisible(true).build();
        Node child3 = Node.newBuilder().withName("child 3").withVisible(true).build();
        
        // and add them to the root node
        root.setChild1(child1);
        root.setChild2(child2);
        root.setChild3(child3);

        System.out.println("Object Graph: ");

        // traverse our object graph
        // if we use `stream(Node.class)` only node objects are traversed and we don't need to
        // do any type casting to access Node's methods
        root.vmf().content().stream(Node.class).forEach(
            (node)-> System.out.println("-> node: " + node.getName())
        );

        // this usually would print 
        //
        // -> node: root
        // -> node: child 1
        // -> node: child 2
        // -> node: child 3

        // but with our custom property order (see model) it will print this instead (order has changed)
        // -> node: root
        // -> node: child 3
        // -> node: child 2
        // -> node: child 1

        // if we want to access all properties of a Node object, we can do the following
        // -> see the reflection tutorial for details
        System.out.println("\nProperties of root: ");
        root.vmf().reflect().properties().forEach(p -> System.out.println("-> prop: " + p.getName()));

        // we expect this output:
        // -> prop: name
        // -> prop: visible
        // -> prop: child3
        // -> prop: child2
        // -> prop: child1
    

        // now we can easily declare nodes as invisible and filter them out in the stream:

        // -> child 2 should be invisible
        child2.setVisible(false);

        // we use the simple predicate `n->n.getVisible()` to filter invisible instances
        System.out.println("\nObject Graph without 'child 2': ");
        root.vmf().content().stream(Node.class).filter(n->n.getVisible()).forEach(
            (node)-> System.out.println("-> node: " + node.getName())
        );

        // we should see the following output:
        // -> node: root
        // -> node: child 3
        // -> node: child 1
        
    }
}