# VMF Tutorial 14

[HOME](https://github.com/miho/VMF-Tutorials/blob/master/README.md)

## Custom Model Documentation

### What you will learn

In this tutorial you will learn

- how to document model entities and properties


### What is Custom Model Documentation

VMF creates documentation for the generated API. In addition to that it is often
required to add domain specific documentation. This documentation is added to the
API documentation.

### How to Document a Model?

Consider the following model:

```java
package eu.mihosoft.vmf.tutorial14.vmfmodel;

import eu.mihosoft.vmf.core.*;

@Doc("Represents a finite state machine.")
interface FSM {

    @Doc("Name of this finite state machine.")
    String getName();

    // ... 
}

```

The `@Doc()` annotation can be used to annotate model entities, i.e., interfaces
and properties, i.e., methods. Here's the fully documented FSM model:

```java
package eu.mihosoft.vmf.tutorial14.vmfmodel;

import eu.mihosoft.vmf.core.*;

@Doc("Represents a finite state machine.")
interface FSM {

    @Doc("Name of this finite state machine.")
    String getName();

    @Doc("The initial state of this finite state machine.")
    State getInitialState();
    @Doc("The current state of this finite state machine.")
    State getCurrentState();
    @Doc("The final state of this finite state machine.")
    State getFinalState();
    
    @Doc("The complete state of this finite state machine.")
    @Contains(opposite="owningFSM")
    State[] getOwnedState();
    
}

@Doc("Represents the state of a FSM.")
interface State {

    @Doc("Name of this state.")
    String getName();
    
    @Doc("FSM this state belongs to.")
    @Container(opposite = "ownedState")
    FSM getOwningFSM();
    
    @Doc("Outgoing transition of this state (connection to next state).")
    @Contains(opposite="source")
    Transition getOutgoingTransition();
    @Doc("Incoming transition of this state (connection from previous state).")
    @Contains(opposite="target")
    Transition getIncomingTransition();
    
}


@Doc("Transition between two states.")
interface Transition {
    
    @Doc("Source state.")
    @Container(opposite="outgoingTransition")
    State getSource();
    @Doc("Target state.")
    @Container(opposite="incomingTransition")
    State getTarget();
    
    @Doc("Action that is executed when applying this transition.")
    Action getAction();
}


@Doc("Action that can be executed by the FSM.")
interface Action {
    String getName();
}
```

## Conclusion

Congrats, you have successfully used the `@Doc`annotation to document a VMF model.

If you are lazy you can get the full project [here](https://github.com/miho/VMF-Tutorials/tree/master/VMF-Tutorial-14). To run the code checkout the corresponding [section in the introduction tutorial](https://github.com/miho/VMF-Tutorials/blob/master/VMF-Tutorial-01/README.md#running-the-tutorial).


[HOME](https://github.com/miho/VMF-Tutorials/blob/master/README.md)



