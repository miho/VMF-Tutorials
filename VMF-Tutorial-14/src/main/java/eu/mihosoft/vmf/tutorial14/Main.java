package eu.mihosoft.vmf.tutorial14;

public class Main {
    public static void main(String[] args) {
        FSM machine = FSM.newBuilder().withName("Light switch").build();
        State off = State.newBuilder().withName("Off").build();
        State on = State.newBuilder().withName("On").build();
        machine.getStates().addAll(java.util.List.of(off, on));
        machine.setInitialState(off);
        machine.setCurrentState(off);
        machine.setFinalState(on);

        Action action = Action.newBuilder().withName("Press switch").build();
        Transition transition = Transition.newBuilder()
                .withSource(off).withTarget(on).withAction(action).build();
        machine.getTransitions().add(transition);

        System.out.printf("%s: %s -> %s (%s)%n", machine.getName(),
                transition.getSource().getName(), transition.getTarget().getName(), action.getName());
    }
}
