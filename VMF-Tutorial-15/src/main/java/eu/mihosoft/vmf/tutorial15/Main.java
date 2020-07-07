package eu.mihosoft.vmf.tutorial15;

import eu.mihosoft.vmf.runtime.core.Reflect;
import eu.mihosoft.vmf.runtime.core.Type;
import eu.mihosoft.vvecmath.Vector3d;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // set the location of the model
        MyModel model = MyModel.newInstance();

        // properties with external types
        model.setLocation(Vector3d.xyz(1,2,3));
        
    }
}
