package eu.mihosoft.vmf.tutorial03;

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
        Parent parent = Parent.newInstance();

        // start change recorder for undo
        parent.vmf().changes().start();

        // register change listener
        parent.vmf().changes().addListener(
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
        parent.setName("Parent 1");

        System.out.println("--------");

        // create a new child
        Child child1 = Child.newInstance();

        // add the child to the parent
        parent.getChildren().add(child1);

        System.out.println("--------");

        // containment references make it possible: the child automatically knows its parent
        System.out.println("my parent: " + child1.getParent().getName());

        System.out.println("--------");

        // cause a change by setting the value property of child 1
        child1.setValue(42);

        System.out.println("--------");

        // get a read-only instance of parent
        // use auto-completion to check that it has no setter methods
        // lists also contain read-only instances and are unmodifiable as well
        ReadOnlyParent parentRo = parent.asReadOnly();

        // create a deep clone of parent
        Parent parentClone = parent.vmf().content().deepCopy();

        // ensure that parentClone and parent are equal ...
        System.out.println("parent eq clone: " + Objects.equals(parent,parentClone));

        // ... but not identical
        System.out.println("parent != clone: " + (parent!=parentClone));

        // use automatically generated toString() method
        System.out.println(" -> parent:      " + parent);
        System.out.println(" -> parentClone: " + parentClone);

        System.out.println("--------");

        // show number of changes
        System.out.println("#changes: " + parent.vmf().changes().all().size()+"\n");

        // invert change order ...
        List<Change> changesToRevert = new ArrayList<>(parent.vmf().changes().all());
        Collections.reverse(changesToRevert);

        // ... and undo all changes
        changesToRevert.stream().forEach(c->{
            System.out.println("-------- undo change: --------");c.undo();}
        );

        // after undo we compare the clone and the empty parent (they are not equal)
        // we expect the parent to be empty (all changes are reverted)
        System.out.println("--------");
        System.out.println("parent eq clone: " + Objects.equals(parent,parentClone));
        System.out.println(" -> parent:      " + parent);
        System.out.println(" -> parentClone: " + parentClone);

    }
}