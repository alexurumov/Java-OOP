package collectionHierarchy;

public class MyListImpl extends Collection implements MyList {

    public MyListImpl() {
        super();
    }

    @Override
    public int getUsed() {
        return this.getSize();
    }

    @Override
    public String remove() {
        return this.removeFirst();
    }

    @Override
    public int add(String element) {
        return this.addFirst(element);
    }
}
