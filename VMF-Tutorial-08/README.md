# VMF Tutorial 8

[HOME](https://github.com/miho/VMF-Tutorials/blob/master/README.md) [NEXT ->](https://github.com/miho/VMF-Tutorials/blob/master/VMF-Tutorial-09/README.md)

## Custom Behavior & Delegation

### What you will learn

In this tutorial you will learn how to

- implement custom functionality
- use and implement the `DelegatedBehavior<T extends VObject>` interface

### Declaring a Delegation Method

Delegation methods are a convenient mechanism for providing custom functionality. Unlike other frameworks, VMF restricts custom behavior and does not allow modification of generated code. This doesn't mean that custom functionality can't be implemented. But it ensures that custom code is strictly separated from the generated code. Additionally, the namespace for custom functionality is restricted. Methods starting with `get...`, `set...` or `is...` are reserved for properties. Except for properties and the predefined methods, e.g., `vmf()` or `asReadOnly()`, method names for delegation can be freely chosen. 

This is the model we are going to use:

```java
package eu.mihosoft.vmf.tutorial08.vmfmodel;

import eu.mihosoft.vmf.core.*;

interface ObjectWithCustomBehavior {
    
    int getA();
    int getB();

    @DelegateTo(className="eu.mihosoft.vmf.tutorial08.CustomBehavior")
    int computeSum();
}
```

`ObjectWithCustomBehavior` has two properties `a` and `b`. Additionally, it declares a method `int computeSum()` that takes no arguments and returns an `int`. The annotation `@DelegateTo` defines which delegation class is used to perform the actual computation.


This is how the delegation class looks like:

```java
package eu.mihosoft.vmf.tutorial08;

import eu.mihosoft.vmf.runtime.core.DelegatedBehavior;

public class CustomBehavior implements DelegatedBehavior<ObjectWithCustomBehavior> {
    private ObjectWithCustomBehavior caller;

    @Override
    public void setCaller(ObjectWithCustomBehavior caller) {
        this.caller = caller;
    }

    /**
     * Delegated behavior. It is called whenever {@code caller.computeSum()} is called. 
     * This method computes and returns the sum of property 'a' and 'b'.
     * @return sum of 'a' and 'b'
     */
    public int computeSum() {
        return caller.getA() + caller.getB();
    }
}
```

It implements `DelegatedBehavior<ObjectWithCustomBehavior>` which declares just one method, `setCaller(T caller)`. This method is called during object initialization and gives us access to the caller that delegates to `CustomBehavior`. Then we just need to implement the custom behavior. In our case this is the method `int computeSum()`.

### Calling a Delegation Method

Now we can create an instance and try out our custom method:

```java
// first, we create an object
ObjectWithCustomBehavior obj = ObjectWithCustomBehavior.newInstance();

// them we set properties a and b
obj.setA(2);
obj.setB(3);

// finally, we call our custom method and compute the sum of a + b
int sum = obj.computeSum();

System.out.println("Sum: " + sum);
```

### Custom Behavior during Instantiation

If custom behavior should be executed during instantiation, it is possible to annotate the class directly:

```java

@DelegateTo(className="eu.mihosoft.vmf.tutorial08.CustomBehavior")
interface ObjectWithCustomBehavior {

}
```

Then the delegation class just needs one additional method that is called during instantiation: `onObjectWithCustomBehaviorInstantiated()`. The naming scheme is `on${ClassName}Instantiated()` where `${ClassName}` is the name of the model entity to be extended with custom behavior. Adding behavior during instantiation is convenient and allows to automatically register listeners to objects etc.

## Conclusion

Congrats, you have successfully implemented custom behavior. 

If you are lazy you can get the full project [here](https://github.com/miho/VMF-Tutorials/tree/master/VMF-Tutorial-08). To run the code checkout the corresponding [section in the introduction tutorial](https://github.com/miho/VMF-Tutorials/blob/master/VMF-Tutorial-01/README.md#running-the-tutorial).


[HOME](https://github.com/miho/VMF-Tutorials/blob/master/README.md) [NEXT ->](https://github.com/miho/VMF-Tutorials/blob/master/VMF-Tutorial-08/README.md)



