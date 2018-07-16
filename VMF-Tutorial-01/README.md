# VMF Tutorial 1

## Defining your First Model

A VMF model consists of annotated Java interfaces. We could call this "wannabe" code. We just specify the interface and its properties and get a rich implementation that implements all of that for us.

In our first example we want to generate code for a very basic model. It just consists of one interface `Parent` with a single String property `name`. Here's how we can define the model as Java interface:

```java
package eu.mihosoft.vmf.tutorial01.vmfmodel;

interface Parent {
    String getName();
}
```
