package collectionHierarchy;

public class AddCollection extends Collection implements Addable {

    AddCollection() {
        super();
    }

    @Override
    public int add(String element) {
        return this.addLast(element);
    }
}
