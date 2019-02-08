package eu.mihosoft.vmf.tutorial13.vmfmodel;

import eu.mihosoft.vmf.core.*;

@Annotation(key="my-key", value="info about type a")
interface A {
    String getName();
    A getA();
}

interface B extends A {}





