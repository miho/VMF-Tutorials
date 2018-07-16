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
        // child 1 is implicitly observed
        child1.setValue(42);

        System.out.println("--------");

        // now we create a second parent
        Parent parent2 = Parent.newInstance();
        parent2.setName("Parent 2");

        // adding child 1 to parent2 has several interesting effects
        // 1. child1 is removed from parent1 (check change notification output)
        // 2. parent of child1 is now parent2
        parent2.getChildren().add(child1);

        // containment references make it possible: the child automatically knows its new parent
        System.out.println("my new parent: " + child1.getParent().getName());

        System.out.println("--------");

    }
}