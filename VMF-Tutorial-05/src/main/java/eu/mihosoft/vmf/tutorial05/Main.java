package eu.mihosoft.vmf.tutorial05;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // create a new node instance
        Node node1 = Node.newBuilder().
                withName("my node"). // set the name
                withId(3).           // set the id
                build();

        Node node2 = Node.newInstance();

        // reference properties defined in 'WithName' from 'node1'
        // and apply it to 'node2':
        // - using the builder of a class we inherit from allows to selectively
        //   apply state defined in this class to another instance
        WithName.newBuilder().applyFrom(node1).applyTo(node2);

        // check whether the 'name' property has been applied correctly
        System.out.println("> node1.name == node2.name: " + node1.getName().
            equals(node2.getName()));

    }
}
