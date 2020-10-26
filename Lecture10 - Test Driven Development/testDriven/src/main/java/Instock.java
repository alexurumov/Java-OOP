import jdk.jshell.spi.ExecutionControl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Instock implements ProductStock {

    private List<Product> products;

    public Instock() {
        this.products = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return this.products.size();
    }

    @Override
    public boolean contains(Product product) {
        return this.products.contains(product);
    }

    @Override
    public void add(Product product) {
        this.products.add(product);
    }

    @Override
    public void changeQuantity(String product, int quantity) {
//        this.products.replaceAll(p -> {
//            if(p.getLabel().equals(product)) {
//                int newQuantity = p.getQuantity() + quantity;
//                p.setQuantity(newQuantity);
//            }
//            return p;
//        });

        Product product1 = this.findByLabel(product);
        int currentQuantity = product1.getQuantity();
        product1.setQuantity(currentQuantity + quantity);
    }

    @Override
    public Product find(int index) {
        return this.products.get(index - 1);
    }

    @Override
    public Product findByLabel(String label) {
        return this.products
                .stream()
                .filter(p -> p.getLabel().equals(label))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public Iterable<Product> findFirstByAlphabeticalOrder(int count) {
        ArrayList<Product> result = new ArrayList<>();

        if (0 < count && count <= this.getCount()) {
            this.products
                    .stream()
                    .sorted(Product::compareTo)
                    .limit(count)
                    .forEach(result::add);
        }
        return result;
    }

    @Override
    public Iterable<Product> findAllInRange(double lo, double hi) {
        return getSortedStreamByPrice(lo, hi)
                .collect(Collectors.toList());
    }

    private Stream<Product> getSortedStreamByPrice(double lo, double hi) {
        return this.products
                .stream()
                .filter(p -> p.getPrice() > lo && p.getPrice() <= hi)
                .sorted((f, s) -> Double.compare(s.getPrice(), f.getPrice()));
    }

    @Override
    public Iterable<Product> findAllByPrice(double price) {
        return this.products
                .stream()
                .filter(p -> p.getPrice() == price)
                .sorted((f, s) -> Double.compare(s.getPrice(), f.getPrice()))
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Product> findFirstMostExpensiveProducts(int count) {
        if (count > this.getCount()) throw new IllegalArgumentException();

        Stream<Product> allInRange = getSortedStreamByPrice(0, Double.MAX_VALUE);

        List<Product> result = allInRange.limit(count).collect(Collectors.toList());


        return result;
    }

    @Override
    public Iterable<Product> findAllByQuantity(int quantity) {
        return this.products
                .stream()
                .filter(p -> p.getQuantity() == quantity)
                .collect(Collectors.toList());
    }

    @Override
    public Iterator<Product> iterator() {
        return this.products.iterator();
    }
}
