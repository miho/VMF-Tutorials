package eu.mihosoft.vmf.tutorial12;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // first, we create a storer with two items
        Store store = Store.newBuilder().withId("my store").build();
        Item item1 = Item.newBuilder().withId("my item 1").build();
        Item item2 = Item.newBuilder().withId("my item 2").build();
        store.getItems().add(item1);
        store.getItems().add(item2);

        // print our store
        System.out.println(store.toString());

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

        if(store.equals(deepCopy) || store.equals(shallowCopy)) {
            throw new RuntimeException("neither deep copy nor shallow copy shall be equal to original store");
        }

        // now let's revert the id if both copies to the original:
        deepCopy.setId(store.getId());
        shallowCopy.setId(store.getId());

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

        if(deepCopy.equals(store)) {
            throw new RuntimeException("deep copy must not be equal to original store");
        }

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

        if(!shallowCopy.vmf().content().equals(store)) {
            throw new RuntimeException("shallow copy should be equal to original store");
        }

    }
}
