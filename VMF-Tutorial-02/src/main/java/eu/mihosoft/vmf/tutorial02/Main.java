package eu.mihosoft.vmf.tutorial02;

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

        // cause another  change
        parent.setName("Parent 2");


    }
}