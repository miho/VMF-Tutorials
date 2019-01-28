package eu.mihosoft.vmf.tutorial11.vmfmodel;

import eu.mihosoft.vmf.core.*;

interface Node {

    String getId();

    @Annotation(key="api",value="input")
    Node getA();

    @Annotation(key="api",value="input")
    Node getB();

    @Annotation(key="api",value="output")
    Node getC();
}





