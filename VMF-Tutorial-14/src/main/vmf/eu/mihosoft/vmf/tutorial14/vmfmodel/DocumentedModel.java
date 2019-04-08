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





