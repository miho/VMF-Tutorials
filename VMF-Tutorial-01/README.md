# VMF Tutorial 1

## Defining your First Model

A VMF model consists of annotated Java interfaces. We could call this "wannabe" code. We just specify the interface and its properties and get a rich implementation that implements all of that for us.

### Setting up a Gradle Project

Since VMF comes with a convenient Gradle plugin it's easy to setup. We just have to add the VMF plugin id, e.g. via

```gradle
plugins {
  id "eu.mihosoft.vmf" version "0.1.1" // use latest version
}
```
Now we can configure VMF and specify which version shall be used:

```gradle
vmf {
    version = '0.1' // use desired VMF version
}
```

The plugin adds a source set `src/vmf/java` to our Gradle project intended for the model definition. 
In our first example we want to generate code for a very basic model. It just consists of one interface `Parent` with a single String property `name`. Here's how we can define the model as Java interface:

```java
package eu.mihosoft.vmf.tutorial01.vmfmodel;

interface Parent {
    String getName();
}
```

The source directories of our tutorial project looks like this:

```
src
├── main/java ...
│         └── ...
│   
└── vmf/java
          ├── /eu/mihosoft/vmf/tutorial01/vmfmodel/MyParent.java
          └── ...
```

After we created our first model definition we are ready to run the code generator via the `vmfGenModelSource`task. VMF should show the following output:

```
> Task :vmfGenModelSources
 -> generating code for vmf model in package: eu/mihosoft/vmf/tutorial01/vmfmodel
```

