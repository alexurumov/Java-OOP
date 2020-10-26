package collectionHierarchy;

public class AddRemoveCollection extends Collection implements AddRemovable {

    public AddRemoveCollection() {
        super();
    }

    @Override
    public String remove() {
        return this.removeLast();
    }

    @Override
    public int add(String element) {
        return this.addFirst(element);
    }
}
