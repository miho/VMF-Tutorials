# VMF Tutorial 10

[HOME](https://github.com/miho/VMF-Tutorials/blob/master/README.md) [NEXT ->](https://github.com/miho/VMF-Tutorials/blob/master/VMF-Tutorial-11/README.md)

## Equals & HashCode

### What you will learn

In this tutorial you will learn how to

- use equals() & hashCode()
- ignore properties for equals()

### Using Equals

For the following experiments, we use the model shown below:

```java
package eu.mihosoft.vmf.tutorial10.vmfmodel;

import eu.mihosoft.vmf.core.*;

interface ObjectToCompare {

    String getId();
    Integer getData();

}
```

Now let's compare three instances of `ObjectToCompare`:

```java
ObjectToCompare obj1 = ObjectToCompare.newBuilder().withId("id:0").withData(7).build();
ObjectToCompare obj2 = ObjectToCompare.newBuilder().withId("id:0").withData(7).build();
ObjectToCompare obj3 = ObjectToCompare.newBuilder().withId("id:0").withData(8).build();

System.out.println("Object 1 == Object 2 -> " + obj1.equals(obj2));
System.out.println("Object 1 == Object 3 -> " + obj1.equals(obj3));
```

The result shows that objects `obj1` and `obj2` are equal, but `obj1` and `obj3` are not.

VMF provides a fully functional implementation of the `equals()` method. That is, each property is taken into consideration. In our case that means that `obj1.equals(obj2) == true` and `obj1.equals(obj3) == false`.

### Ignoring Properties for Equals

To demonstrate the effect of `@IgnoreEquals` we define another entity `ObjectToCompareId` that ignores the `data` property in the `equals()` method provided by VMF.

```java
interface ObjectToCompareId {

    String getId();

    @IgnoreEquals
    Integer getData();

}
```

If we try out the same comparison as before but using `ObjectToCompareId` instead of the previous `ObjectToCompare`. Here's the code:

```java
ObjectToCompareId objId1 = ObjectToCompareId.newBuilder().withId("id:0").withData(7).build();
ObjectToCompareId objId2 = ObjectToCompareId.newBuilder().withId("id:0").withData(7).build();
ObjectToCompareId objId3 = ObjectToCompareId.newBuilder().withId("id:0").withData(8).build();

System.out.println("--");

System.out.println("Object 1 == Object 2 -> " + objId1.equals(objId2));
System.out.println("Object 1 == Object 3 -> " + objId1.equals(objId3));
```

In this case the objects are all equal to each other because only the `id` properties are compared.

## HashCode

HashCode is based on the `equals()` method. That means that `@IgnoreEquals` also has an effect on the implementation of the `hashCode()` method. Although it is possible to override `hashCode()` and `equals()` it is not recommended because breaking the contracts causes serious problems with collections etc.


## Conclusion

Congrats, you have successfully defined custom default values for properties. 

If you are lazy you can get the full project [here](https://github.com/miho/VMF-Tutorials/tree/master/VMF-Tutorial-10). To run the code checkout the corresponding [section in the introduction tutorial](https://github.com/miho/VMF-Tutorials/blob/master/VMF-Tutorial-01/README.md#running-the-tutorial).


[HOME](https://github.com/miho/VMF-Tutorials/blob/master/README.md) [NEXT ->](https://github.com/miho/VMF-Tutorials/blob/master/VMF-Tutorial-11/README.md)



