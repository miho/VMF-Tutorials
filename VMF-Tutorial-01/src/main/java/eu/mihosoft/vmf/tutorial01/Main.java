package eu.mihosoft.vmf.tutorial01;

import eu.mihosoft.vmf.runtime.core.Change;
import eu.mihosoft.vmf.runtime.core.VIterator;

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

        // set parent's name
        parent.setName("My Name");

        // check that name is set
        if ("My Name".equals(parent.getName())) {
            System.out.println("> GOOD: name is correctly set");
        } else {
            System.out.println("> BAD: something went wrong :(");
        }

    }
}