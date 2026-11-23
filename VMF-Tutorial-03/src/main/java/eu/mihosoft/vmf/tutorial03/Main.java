package eu.mihosoft.vmf.tutorial03;

import eu.mihosoft.vmf.runtime.core.Changes;

public class Main {
    public static void main(String[] args) {
        Parent firstParent = Parent.newBuilder().withName("Parent 1").build();
        Parent secondParent = Parent.newBuilder().withName("Parent 2").build();
        Child child = Child.newInstance();

        var values = firstParent.vmf().changes().addPropertyListener("value", change -> {
            var edit = change.propertyChange().orElseThrow();
            System.out.printf("Observed child value: %s -> %s%n", edit.oldValue(), edit.newValue());
        }, Changes.Scope.RECURSIVE);

        firstParent.setChild(child);
        System.out.println("Initial parent: " + child.getParent().getName());
        child.setValue(42);

        secondParent.setChild(child);
        System.out.println("Original parent empty: " + (firstParent.getChild() == null));
        System.out.println("New parent: " + child.getParent().getName());
        System.out.println("Same child instance: " + (secondParent.getChild() == child));
        child.setValue(43);
        System.out.println("Child value after the move: " + child.getValue());
        values.unsubscribe();

        TreeNode root = TreeNode.newBuilder().withName("Root").build();
        TreeNode leaf = TreeNode.newBuilder().withName("Leaf").build();
        root.getChildren().add(leaf);
        System.out.println("List child's parent: " + leaf.getParent().getName());

        try {
            leaf.getChildren().add(root);
        } catch (IllegalArgumentException cycle) {
            System.out.println("Containment cycle rejected");
        }
    }
}
