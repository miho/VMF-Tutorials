# VMF Tutorial 9

[HOME](https://github.com/miho/VMF-Tutorials/blob/master/README.md) [NEXT ->](https://github.com/miho/VMF-Tutorials/blob/master/VMF-Tutorial-10/README.md)

## Custom Default Values for Properties

### What you will learn

In this tutorial you will learn how to

- define default values
- check if properties are set or unset

### Declaring Default Values

VMF properties can have default values. A default value is the initial state of a property. In general, properties can be set and unset. A property is defined as unset if it is equal to its default value. Usually, the default value is just `null`. But sometimes it is necessary to define a custom value as default. This can be achieved with the annotation `@DefaultValue(value="the value")`.

The model below declares the entity `ObjectWithDefaultValues` with two properties `value` and `name`. The `value` property has the default value `23` and `name` has the default value `"my name"`. Here's the corresponding source code:

```java
package eu.mihosoft.vmf.tutorial09.vmfmodel;

import eu.mihosoft.vmf.core.*;

interface ObjectWithDefaultValues {
    
    @DefaultValue(value="23")
    Integer getValue();

    @DefaultValue(value="\"my name\"")
    String getName();

}
```

If we create an instance its properties should already be set to the custom defaults:

```java
// first, we create an object
ObjectWithDefaultValues obj = ObjectWithDefaultValues.newInstance();

// now we can get the default values
System.out.println("Value: " + obj.getValue());
System.out.println("Name:  " + obj.getName());
```

### Checking whether Properties are set or unset

We can check whether the properties are set or unset:

```java
// we use `p.isSet()` to check if the property is set
String valueIsSet = obj.vmf().reflect().propertyByName("value").map(p->""+p.isSet()).orElse("<not available>");
String nameIsSet = obj.vmf().reflect().propertyByName("name").map(p->""+p.isSet()).orElse("<not available>");
System.out.println("Value is set:  " + valueIsSet );
System.out.println("Name is set:   " + nameIsSet );
```

We expect the properties to be unset (`false`). If we change the name property, it should be set:

```java
// if we set a property to a different value we expect it to be set (`true`)
obj.setName("another name");
System.out.println("Value is set:  " + propertySetOrUnset.apply("value") );
System.out.println("Name is set:   " + propertySetOrUnset.apply("name")  );
```

If you run this code it should show that `name` is set (`true`).

To revert back to the initial state, we can call the `unset()` method:

```java
// unset name property, name should be shown as unset (`true`)
obj.vmf().reflect().propertyByName("name").ifPresent(p->p.unset());
System.out.println("Value is set:  " + propertySetOrUnset.apply("value") );
System.out.println("Name is set:   " + propertySetOrUnset.apply("name")  );
```

## Conclusion

Congrats, you have successfully defined custom default values for properties. 

If you are lazy you can get the full project [here](https://github.com/miho/VMF-Tutorials/tree/master/VMF-Tutorial-09). To run the code checkout the corresponding [section in the introduction tutorial](https://github.com/miho/VMF-Tutorials/blob/master/VMF-Tutorial-01/README.md#running-the-tutorial).


[HOME](https://github.com/miho/VMF-Tutorials/blob/master/README.md) [NEXT ->](https://github.com/miho/VMF-Tutorials/blob/master/VMF-Tutorial-10/README.md)



