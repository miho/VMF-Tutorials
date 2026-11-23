package eu.mihosoft.vmf.tutorial04;

import eu.mihosoft.vmf.runtime.core.Change;

public class Main {
    public static void main(String[] args) {
        Node root = Node.newBuilder().withName("Draft").build();
        Node child = Node.newBuilder().withName("Appendix").build();
        var changes = root.vmf().changes();

        changes.start();
        changes.startTransaction();
        root.setName("Published");
        root.getChildren().add(child);
        changes.publishTransaction();
        changes.stop();

        var edit = changes.transactions().get(0);
        printState("After the edit", root);
        if (!edit.isUndoable()) {
            throw new IllegalStateException("The recorded edit cannot be undone in the current state");
        }
        edit.undo();
        printState("After undo", root);

        for (Change change : edit.changes()) {
            change.apply(change.object());
        }
        printState("After redo", root);
        System.out.println("Same child instance: " + (root.getChildren().get(0) == child));

        root.setName("Edited later");
        System.out.println("Undo allowed after a later edit: " + edit.isUndoable());
    }

    private static void printState(String stage, Node root) {
        System.out.printf("%s: %s (children: %d)%n", stage, root.getName(), root.getChildren().size());
    }
}
