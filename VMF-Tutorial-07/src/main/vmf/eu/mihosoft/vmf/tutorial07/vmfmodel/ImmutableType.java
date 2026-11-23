package eu.mihosoft.vmf.tutorial07.vmfmodel;

import eu.mihosoft.vmf.core.GetterOnly;
import eu.mihosoft.vmf.core.Immutable;
import eu.mihosoft.vmf.core.InterfaceOnly;

@Immutable
interface ImmutableObject {
    int getValue();
}

interface MutableObject {
    int getValue();
}

@InterfaceOnly
interface WithName {
    @GetterOnly
    String getName();
}

@Immutable
interface ImmutableNamed extends WithName { }

interface MutableNamed extends WithName { }
