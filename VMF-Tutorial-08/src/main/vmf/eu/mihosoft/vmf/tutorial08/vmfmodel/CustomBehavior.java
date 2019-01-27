package eu.mihosoft.vmf.tutorial08.vmfmodel;

import eu.mihosoft.vmf.core.*;

interface ObjectWithCustomBehavior {
    
    int getA();
    int getB();

    @DelegateTo(className="eu.mihosoft.vmf.tutorial08.CustomBehavior")
    int computeSum();
}



