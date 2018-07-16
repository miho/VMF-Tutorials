# VMF Tutorial 4

[HOME](https://github.com/miho/VMF-Tutorials/blob/master/README.md) [NEXT ->](https://github.com/miho/VMF-Tutorials/edit/master/VMF-Tutorial-05/README.md)


# TBD

## Undo/Redo

### What you will learn

In this tutorial you will learn how to

- use the undo/redo API

### The Model

```java
package eu.mihosoft.vmf.tutorial02.vmfmodel;

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
          ├── /eu/mihosoft/vmf/tutorial01/vmfmodel/Parent.java
          └── ...
```

### Running the Code Generator

After we created our first model definition we are ready to run the code generator via the `vmfGenModelSource`task, e.g. via

```
./gradlew vmfGenModelSources
```

VMF should show the following output:

```
> Task :vmfGenModelSources
 -> generating code for vmf model in package: eu/mihosoft/vmf/tutorial01/vmfmodel
```

### Using the Code

To use the code just use the generated code from your regular Java code, e.g, in `src/main/java`:

```java
package eu.mihosoft.vmf.tutorial02;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // create a new parent instance
        Parent parent = Parent.newInstance();
        
        // set parent's name
        parent.setName("My Name");
        
        // check that name is set
        if("My Name".equals(parent.getName())) {
          System.out.println("name is correctly set");
        } else {
          System.out.println("something went wrong :(");
        }
        
    }
}
```

Congrats, you have successfully created your first VMF model.

[HOME](https://github.com/miho/VMF-Tutorials/blob/master/README.md) [NEXT ->](https://github.com/miho/VMF-Tutorials/edit/master/VMF-Tutorial-02/README.md)



