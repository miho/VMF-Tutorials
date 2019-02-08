# VMF Tutorial 7

[HOME](https://github.com/miho/VMF-Tutorials/blob/master/README.md) [NEXT ->](https://github.com/miho/VMF-Tutorials/blob/master/VMF-Tutorial-08/README.md)

## Immutable Objects and ReadOnly API

### What you will learn

In this tutorial you will learn how to

- declare model entities as immutable
- use the ReadOnly API of mutable objects to prevent modifications

### Declaring Entities as Immutable

The model we are going to use consists of a `MutableObject` entity and an `ImmutableObject` entity. Each of them has a `value` property. With the `@Immutable` annotation we can declare an entity as immutable, i.e., the state can only be defined during initialization. Changes are not possible.

```java
package eu.mihosoft.vmf.tutorial07.vmfmodel;

import eu.mihosoft.vmf.core.*;

@Immutable
interface ImmutableObject {
    int getValue();
}

interface MutableObject {
    int getValue();
}
```

### Instantiating Immutable Objects

We can instantiate immutable objects by using the builder pattern:

```java
// the only way to set the 'value' property for an immutable object is to
// use the builder pattern. there is no setter method for the 'value' property
ImmutableObject immutableObject = ImmutableObject.newBuilder().withValue(12).build();
```

Changing the state after instantiation is not possible:

```java
// does not compile because we can't change the initial state of immutable objects
immutableObject.setValue(12);
```

VMF also ensures that property types of immutables are immutable as well. Otherwise it
would be possible to indirectly change the state of immutable objects.

Just for comparison, this is how mutable objects can be initialized and modified:

```java
// the mutable object works exactly as expected. we can create the instance
// and set the 'value' property afterwards.
MutableObject mutableObject = MutableObject.newInstance();

mutableObject.setValue(12);
```

Sometimes we want to protect mutable objects from being changed. In this case, we can use the read-only API. VMF generates a read-only counterpart for each model type. We can easily access it by calling the `asReadOnly()` method:

```java
// to prevent that receivers of mutable objects can change their state, VMF
// generates a read-only counterpart for each model type:
ReadOnlyMutableObject readOnlyMutable = mutableObject.asReadOnly();

// this won't compile
readOnlyMutable.setValue()
```

It is still possible to register listeners and react to changes since it is just a read-only view of a mutable object. This only guarantees that the consumer of a read-only instance cannot make any changes. But it can still see them. The consumer of a read-only instance can also create mutable instances from read-only objects. But to ensure that we don't violate the read-only contract with the original object, VMF forces the consumer to create a deep clone which is then modifiable:

```java
// creates a modifiable copy (deep copy)
MutableObject mutableObject2 = readOnlyMutable.asModifiable();
```
## How can Mutable and Immutable Types Share a Common Super Type?

From the frameworks point of view it is important to protect the immutability-contract. This makes it impossible to allow free inheritance between mutable and immutable types. Otherwise, a subtype of an immutable could be mutable and we could not be sure whether a `ImmutableObject` instance is really immutable. This uncertainty would essentially degrade immutability to a read-only type. That is, VMF cannot allow that. 

However, there are cases in which it is desirable to declare a common super type for immutable and mutable model entities. For this purpose, VMF provides a way to declare effectively immutable types that provide an interface only. If such a lightweight interface only provides getter methods and if all involved types are immutable or also interface-only with getters only, we can assume it's immutable as well. Although, we cannot use such a type as property, we can safely inherit from it, whether we are mutable or not.

InterfaceOnly, GetterOnly & Immutability:

```jave
package mypkg.vmfmodel;
import eu.mihosoft.vmf.core.*;

@InterfaceOnly
interface WithName {

    @GetterOnly
    String getName();
}

interface ImmutableObj extends WithName { }
interface MutableObj extends WithName { }
```

We can safely use `WithName` as a common super type of mutable and immutable types:

```java
ImmutableObj immutableObj = ImmutableObj.newBuilder().withName("immutable obj").build();
MutableObj   mutableObj   = MutableObj.newBuilder().withName("mutable obj").build();
WithName withName1 = immutableObj;
WithName withName2 = mutableObj;
```

## Interesting to Know

- calling `asReadOnly()` is a no-op, immutables do not have ReadOnly-Siblings
- `clone()` is a no-op, cloning uses shared immutable references (in case of read-only objects, a shallow read-only copy is used which references the same mutable object)

## Conclusion

Congrats, you have successfully declared an immutable model entity and used the read-only API of mutable objects. 

If you are lazy you can get the full project [here](https://github.com/miho/VMF-Tutorials/tree/master/VMF-Tutorial-07). To run the code checkout the corresponding [section in the introduction tutorial](https://github.com/miho/VMF-Tutorials/blob/master/VMF-Tutorial-01/README.md#running-the-tutorial).


[HOME](https://github.com/miho/VMF-Tutorials/blob/master/README.md) [NEXT ->](https://github.com/miho/VMF-Tutorials/blob/master/VMF-Tutorial-08/README.md)



