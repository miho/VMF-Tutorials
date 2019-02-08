# VMF Tutorial 13

[HOME](https://github.com/miho/VMF-Tutorials/blob/master/README.md) [NEXT ->](https://github.com/miho/VMF-Tutorials/blob/master/VMF-Tutorial-14/README.md)

## The Reflection API

### What you will learn

In this tutorial you will learn

- what reflection is
- how to use the VMF reflection for querying type information
- how to use the VMF reflection to access and use properties reflectively
- how to use the reflection API for accessing annotations

There are also tutorials on [annotations](https://github.com/miho/VMF-Tutorials/blob/master/VMF-Tutorial-11/README.md) and [properties & default values](https://github.com/miho/VMF-Tutorials/blob/master/VMF-Tutorial-09/README.md).

### What is Reflection?

Reflection means that VMF models can analyze and modify their own structures 
at runtime via API calls. Future versions might add the ability to reflectively
add or change behavior via delegation.

### Querying Type Information

Consider the following model:

```java
package eu.mihosoft.vmf.tutorial13.vmfmodel;

import eu.mihosoft.vmf.core.*;

@Annotation(key="my-key", value="info about type a")
interface A {
    String getName();
    A getA();
}

interface B extends A {}
```

The first thing we will try is to get access to the type information of type `A` and `B`. For accessing the reflection API we have two possibilities:

- access the static reflection API via the class object, e.g., `Reflect reflectionOfA = A.type().reflect()`
- use the runtime reflection API from an instance of A, e.g., `Reflect reflectionOfA = a.vmf().reflect()`

We start with the static approach:

```java
Type typeOfA = A.type();
Reflect reflectionOfA = typeOfA.reflect();
```

and query the number properties of `A`:

```java
System.out.println("Properties of A:");
reflectionOfA.properties().forEach(p-> {
    System.out.println(" -> " + p.getName());
});
```

The output should show both properties defined in `A`, `name` as well as `a`. Each property object can set and unset
the property as well as register change listeners. But this only works if the reflection API has been aquired via an instance
since otherwise, it cannot set the instance properties. To demonstrate that, we retrieve the property by name, register
a listener and set the property via reflection, instead of using the API directly (`a.setName("a name")`):

```java
A a = A.newInstance();
a.vmf().reflect().propertyByName("name").ifPresent(p-> {
    p.addChangeListener(c-> {
        System.out.println("[CHANGE]:changed property 'name': " + p.get());
    });

    p.set("my new name");
});
```

The terminal output should show the change we made reflectively.

The property object gives access to the type of the property, can set and unset values etc. Now let's have a look at the type information provided by the reflection API. For that, we access the Type info of `B` and output its super types:

```java
System.out.println("Super Types of B:");
B.type().superTypes().forEach(t->{
    System.out.println(" -> " + t.getName());
});
```

We expect just one parent type, which is `A`. The reflection API of a type can also give us access to the annotations. Let's the the annotations of type `A`:

```java
System.out.println("Annotations of A:");
A.type().reflect().annotations().forEach(ann-> {
    System.out.println(" -> " + ann.getKey() + ", " + ann.getValue());
});
```

Finally, we can check whether a type is a model type or not. Let's revisit all properties just like in our first example and use the type objects of properties to query type name and whether it's a model type or not:

```java
System.out.println("Properties of A:");
reflectionOfA.properties().forEach(p-> {
    System.out.println(" -> property-name : " + p.getName());
    System.out.println("    type name     : " + p.getType().getName());
    System.out.println("    model-type    : " + p.getType().isModelType());
});
```

If you run this code, you will see two properties in the output. Property `name` is no model-type, but property `a` is.

## Conclusion

Congrats, you have successfully used the reflectin API to analyze a models structure and  to set properties reflectively.

If you are lazy you can get the full project [here](https://github.com/miho/VMF-Tutorials/tree/master/VMF-Tutorial-13). To run the code checkout the corresponding [section in the introduction tutorial](https://github.com/miho/VMF-Tutorials/blob/master/VMF-Tutorial-01/README.md#running-the-tutorial).


[HOME](https://github.com/miho/VMF-Tutorials/blob/master/README.md) [NEXT ->](https://github.com/miho/VMF-Tutorials/blob/master/VMF-Tutorial-14/README.md)



