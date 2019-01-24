package eu.mihosoft.vmf.tutorial05.vmfmodel;



interface WithName {

    String getName();

}


interface WithId {

    int getId();

}

interface Node extends WithName, WithId{

}

