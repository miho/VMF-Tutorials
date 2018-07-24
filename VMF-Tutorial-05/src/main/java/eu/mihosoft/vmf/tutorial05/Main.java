package eu.mihosoft.vmf.tutorial05;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // create a new parent instance
        Node node1 = Node.newBuilder().
                withName("my node"). // set the name
                withId(3).           // set the id
                build();

        Node node2 = Node.newInstance();

        // copy properties defined in 'WithName' from 'node1' to 'node2'
        WithName.newBuilder().applyFrom(node1).applyTo(node2);

        System.out.println("> node1.name == node2.name: " + node1.getName().equals(node2.getName()));

    }
}