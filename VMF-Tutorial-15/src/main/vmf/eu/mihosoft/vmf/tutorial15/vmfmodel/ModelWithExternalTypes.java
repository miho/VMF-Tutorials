package eu.mihosoft.vmf.tutorial15.vmfmodel;

import eu.mihosoft.vmf.core.*;

@ExternalType(pkgName="eu.mihosoft.vvecmath")
interface Vector3d {}


interface MyModel {
    Vector3d getLocation();
}
