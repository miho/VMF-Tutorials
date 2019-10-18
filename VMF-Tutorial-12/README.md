# VMF Tutorial 12

[HOME](https://github.com/miho/VMF-Tutorials/blob/master/README.md) [NEXT ->](https://github.com/miho/VMF-Tutorials/blob/master/VMF-Tutorial-13/README.md)

## Cloning (Deep Copy & Shallow Copy)

### What you will learn

In this tutorial you will learn how

- to clone object graphs
- shallow copies are different from deep copies

### Annotate Models

For the following experiments, we use the model shown below:

```java
package eu.mihosoft.vmf.tutorial12.vmfmodel;

import eu.mihosoft.vmf.core.*;

@VMFEquals
interface Store {

    String getId();

    @Contains(opposite="parent")
    Item[] getItems();

    @DelegateTo(className="eu.mihosoft.vmf.tutorial12.StoreDelegate")
    String toString();

}

@VMFEquals
interface Item {

    String getId();

    @Container(opposite="items")
    Store getParent();

    @DelegateTo(className="eu.mihosoft.vmf.tutorial12.ItemDelegate")
    String toString();
}
```

We introduced custom `toString()` methods to optimize and simplify the console output.

First, we will create a store with two items:

```java
// first, we create a storer with two items
Store store = Store.newBuilder().withId("my store").build();
Item item1 = Item.newBuilder().withId("my item 1").build();
Item item2 = Item.newBuilder().withId("my item 2").build();
store.getItems().add(item1);
store.getItems().add(item2);

// print our store
System.out.println(store.toString());
```

Now, we will create a deep copy and a shallow copy and change their ids. We compare both with the original and conclude that both should be different from the original, i.e., the original should stay untouched:

```java
// now we create a deep copy
Store deepCopy    = store.vmf().content().deepCopy();
// and a shallow copy
Store shallowCopy = store.vmf().content().shallowCopy();

// if we change the id both copies should differ from the original
deepCopy.setId("deep copy");
shallowCopy.setId("shallow copy");

System.out.println("----------------------------------------");
System.out.println(" > Equality Test after Id Change");
System.out.println("----------------------------------------");

// let's check that both copies differ from the original:
System.out.println("store.equals(deepCopy)    -> " + store.equals(deepCopy));
System.out.println("store.equals(shallowCopy) -> " + store.equals(shallowCopy));
```

So far, shallow copies and deep copies showed identical behavior. If we change the id of an item, we will see that they behave differently. Before we continue, we reset the id's of both copies:

```java
// now let's revert the id if both copies to the original:
deepCopy.setId(store.getId());
shallowCopy.setId(store.getId());
```

Now, we can change the id of the first item of the deep copy:

```java
// if we make changes to the deep copy's item list, i.e., we change an items id
// this change will only affect 'deepCopy' and leave the original untouched
deepCopy.getItems().get(0).setId("my new id 1");

System.out.println("----------------------------------------");
System.out.println(" > Deep Copy Test");
System.out.println("----------------------------------------");
System.out.println("#### Original     ####");
System.out.println(store);
System.out.println("#### Deep Copy    ####");
System.out.println(deepCopy);
```

The deep copy should show an updated item list. But the original store should remain unchanged.

Shallow copies behave differently. While they are a separate instance, they share the references of the original. Here's what happens if we repeat the experiment with the shallow copy:

```java
// if we make the same change to the shallow copy we will see that the original
// has changed as well
shallowCopy.getItems().get(0).setId("!!! my new id 1 !!!");

System.out.println("----------------------------------------");
System.out.println(" > Shallow Copy Test");
System.out.println("----------------------------------------");
System.out.println("#### Original     ####");
System.out.println(store);
System.out.println("#### Shallow Copy ####");
System.out.println(shallowCopy);
```

This change has also been applied to the original store. It shows the same item list as the shallow copy.

## Conclusion

Congrats, you have successfully cloned object graphs and learned how deep copies are different from shallow copies.

If you are lazy you can get the full project [here](https://github.com/miho/VMF-Tutorials/tree/master/VMF-Tutorial-12). To run the code checkout the corresponding [section in the introduction tutorial](https://github.com/miho/VMF-Tutorials/blob/master/VMF-Tutorial-01/README.md#running-the-tutorial).


[HOME](https://github.com/miho/VMF-Tutorials/blob/master/README.md) [NEXT ->](https://github.com/miho/VMF-Tutorials/blob/master/VMF-Tutorial-13/README.md)



