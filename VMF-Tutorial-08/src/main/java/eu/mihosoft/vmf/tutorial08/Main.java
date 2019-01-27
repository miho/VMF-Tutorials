package eu.mihosoft.vmf.tutorial08;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // first, we create an object
        ObjectWithCustomBehavior obj = ObjectWithCustomBehavior.newInstance();

        // them we set properties a and b
        obj.setA(2);
        obj.setB(3);

        // finally, we call our custom method and compute the sum of a + b
        int sum = obj.computeSum();
        
        System.out.println("Sum: " + sum);

    }
}
