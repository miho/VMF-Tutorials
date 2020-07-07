# VMF Tutorial 15

[HOME](https://github.com/miho/VMF-Tutorials/blob/master/README.md)

## External Types

### What you will learn

In this tutorial you will learn

- how to declare external types in the model without adding them to the classpath


### What are external types

External types are not part of the model. They belong to external libraries or frameworks.
Even though VMF discourages their use, it is not fully inevitable. To improve the quality of
the generated code it is necessary to establish an annotation to handle those cases. The first
case that is soved is an external type that is not part of the classpath of the code generator
but will be on the class or module path of the generated code used at runtime. Future extensions
might improve the external type annotation to allow the definition of types which are treates as
immutable etc.

### How to use External Types in a Model?

Consider the following model:

```java
package eu.mihosoft.vmf.tutorial15.vmfmodel;

import eu.mihosoft.vmf.core.*;

@ExternalType(pkgName="eu.mihosoft.vvecmath")
interface Vector3d {}


interface MyModel {
    Vector3d getLocation();
}
```

Instead of adding the `Vector3d` type to the classpath in can be declared in the model. The code generator uses the 
surrogate type `Vector3d` during code generation. It replaces the package of the type with the specified package name.

## Conclusion

Congrats, you have successfully added external types to a VMF model.

If you are lazy you can get the full project [here](https://github.com/miho/VMF-Tutorials/tree/master/VMF-Tutorial-15). To run the code checkout the corresponding [section in the introduction tutorial](https://github.com/miho/VMF-Tutorials/blob/master/VMF-Tutorial-01/README.md#running-the-tutorial).


[HOME](https://github.com/miho/VMF-Tutorials/blob/master/README.md)



