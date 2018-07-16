package eu.mihosoft.vmf.tutorial04;

import eu.mihosoft.vmf.runtime.core.Change;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;


public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // create a new parent instance
        Node root = Node.newInstance();

        // start change recorder for undo
        root.vmf().changes().start();

        // register change listener
        root.vmf().changes().addListener(
                (evt)-> {
                    System.out.println("evt: " + evt.propertyName());

                    if(evt.propertyChange().isPresent()) {
                        System.out.println("  -> oldValue: " + evt.propertyChange().get().oldValue());
                        System.out.println("  -> newValue: " + evt.propertyChange().get().newValue());
                    } else if (evt.listChange().isPresent()) {
                        System.out.println("  -> " + evt.listChange().get().toStringWithDetails());
                    }
                }
        );

        // cause a change by setting the name of parent
        root.setName("#1");

        System.out.println("--------");

        // create a new child
        Node child1 = Node.newInstance();

        // add the child to the parent
        root.getChildren().add(child1);

        System.out.println("--------");

        // cause a change by setting the value property of child 1
        child1.setName("#2");

        System.out.println("--------");

        // create a deep clone of root
        Node rootClone = root.vmf().content().deepCopy();

        // ensure that rootClone and parent are equal ...
        System.out.println("root eq rootClone: " + Objects.equals(root,rootClone));

        // ... but not identical
        System.out.println("root != rootclone: " + (root!=rootClone));

        // use automatically generated toString() method
        System.out.println(" -> root:      " + root);
        System.out.println(" -> rootClone: " + rootClone);

        System.out.println("--------");

        // show number of changes
        System.out.println("#changes: " + root.vmf().changes().all().size()+"\n");

        // invert change order ...
        List<Change> changesToRevert = new ArrayList<>(root.vmf().changes().all());
        Collections.reverse(changesToRevert);

        // ... and undo all changes
        changesToRevert.stream().forEach(c->{
            System.out.println("-------- undo change: --------");c.undo();}
        );

        // after undo we compare the clone and the empty parent (they are not equal)
        // we expect the parent to be empty (all changes are reverted)
        System.out.println("--------");
        System.out.println("parent eq clone: " + Objects.equals(root,rootClone));
        System.out.println(" -> root:      " + root);
        System.out.println(" -> rootClone: " + rootClone);
    }
}