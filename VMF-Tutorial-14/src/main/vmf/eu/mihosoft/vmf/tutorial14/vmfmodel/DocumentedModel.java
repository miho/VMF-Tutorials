package eu.mihosoft.vmf.tutorial14.vmfmodel;

import eu.mihosoft.vmf.core.Container;
import eu.mihosoft.vmf.core.Contains;
import eu.mihosoft.vmf.core.Doc;

@Doc("Represents a finite state machine.")
interface FSM {
    @Doc("The name of this state machine.")
    String getName();

    @Doc("The state from which execution starts.")
    State getInitialState();

    @Doc("The currently active state.")
    State getCurrentState();

    @Doc("The state in which execution ends.")
    State getFinalState();

    @Doc("The states owned by this state machine.")
    @Contains(opposite = "owningFSM")
    State[] getStates();

    @Doc("The transitions owned by this state machine.")
    @Contains(opposite = "owningFSM")
    Transition[] getTransitions();
}

@Doc("Represents a state in a state machine.")
interface State {
    @Doc("The name of this state.")
    String getName();

    @Doc("The state machine that owns this state.")
    @Container(opposite = "states")
    FSM getOwningFSM();
}

@Doc("A transition between two states.")
interface Transition {
    @Doc("The state machine that owns this transition.")
    @Container(opposite = "transitions")
    FSM getOwningFSM();

    @Doc("The state in which the transition starts.")
    State getSource();

    @Doc("The state that becomes active after the transition.")
    State getTarget();

    @Doc("The action associated with the transition.")
    Action getAction();
}

@Doc("An action that the state machine can execute.")
interface Action {
    @Doc("The name of this action.")
    String getName();
}
