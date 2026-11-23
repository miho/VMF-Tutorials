package eu.mihosoft.vmf.tutorial06;

import eu.mihosoft.vmf.runtime.core.VIterator.IterationStrategy;

public class Main {
    public static void main(String[] args) {
        Node root = Node.newBuilder().withName("root").withVisible(true).build();
        Node child1 = Node.newBuilder().withName("child 1").withVisible(true).build();
        Node child2 = Node.newBuilder().withName("child 2").withVisible(true).build();
        Node child3 = Node.newBuilder().withName("child 3").withVisible(true).build();

        root.setChild1(child1);
        root.setChild2(child2);
        root.setChild3(child3);

        System.out.println("Object graph");
        root.vmf().content().stream(Node.class)
                .forEach(node -> System.out.println(node.getName()));

        System.out.println("\nRoot properties");
        root.vmf().reflect().properties()
                .forEach(property -> System.out.println(property.getName()));

        child2.setVisible(false);
        System.out.println("\nVisible nodes");
        root.vmf().content().stream(Node.class)
                .filter(Node::isVisible)
                .forEach(node -> System.out.println(node.getName()));

        System.out.println("\nContainment tree");
        root.vmf().content().stream(Node.class, IterationStrategy.CONTAINMENT_TREE)
                .forEach(node -> System.out.println(node.getName()));
    }
}
