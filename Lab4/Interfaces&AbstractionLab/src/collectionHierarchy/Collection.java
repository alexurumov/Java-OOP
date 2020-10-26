package collectionHierarchy;

import java.util.ArrayList;
import java.util.List;

abstract class Collection {
    private static final int MAX_SIZE= 100;
    private List<String> items;

    public Collection() {
        this.items = new ArrayList<>(MAX_SIZE);
    }

    protected int addLast(String element) {
        this.items.add(element);
        return this.items.size() - 1;
    }

    protected int addFirst(String element) {
        this.items.add(0, element);
        return 0;
    }

    protected String removeLast() {
        int lastIndex = this.items.size() - 1;
        return this.items.remove(lastIndex);
    }

    protected String removeFirst() {
        return this.items.remove(0);
    }

    protected int getSize() {
        return this.items.size();
    }
}
