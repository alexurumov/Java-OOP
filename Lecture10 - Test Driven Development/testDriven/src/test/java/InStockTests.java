import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;

public class InStockTests {
    public static final String DEFAULT_PRODUCT_LABEL = "Label";
    public static final int DEFAULT_PRODUCT_QUANTITY = 200;
    public static final double DEFAULT_PRODUCT_PRICE = 2.5;

    private Product product;
    private Product second;
    private Product third;
    private Instock stock;

    @Before
    public void setup() {
        this.product = new Product(DEFAULT_PRODUCT_LABEL, DEFAULT_PRODUCT_PRICE, DEFAULT_PRODUCT_QUANTITY);
        this.second = new Product(DEFAULT_PRODUCT_LABEL + "2", 10, 10);
        this.third = new Product(DEFAULT_PRODUCT_LABEL + "3", 11, 10);
        this.stock = new Instock();
    }

    @Test
    public void shouldAddProduct() {
        this.stock.add(product);

        assertTrue(this.stock.contains(product));

    }

    @Test
    public void shouldHaveNonZeroCountAfterProductAdd() {
        this.stock.add(product);

        assertEquals(1, this.stock.getCount());

    }

    @Test
    public void shouldHaveCountEqualsToProductsAdded() {
        this.stock.add(product);
        this.stock.add(product);
        this.stock.add(product);

        assertEquals(3, this.stock.getCount());
    }

    @Test
    public void shouldReturnFalseIfProductNotContained() {
        assertFalse(this.stock.contains(product));
    }

    @Test
    public void containsShouldReturnTrueIfProductLabelsMatch() {
        Product product2 = new Product(DEFAULT_PRODUCT_LABEL, 10, 10);
        this.stock.add(product2);

        assertTrue(this.stock.contains(product));
    }

    @Test
    public void shouldReturnCorrectProductWhenSingleProduct() {
        this.stock.add(product);

        Product actual = this.stock.find(1);

        assertEquals(product, actual);
    }

    @Test
    public void shouldReturnCorrectProductWithMultipleProducts() {
        seedStock();

        Product actual = this.stock.find(3);

        assertEquals(third, actual);
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void shouldThrowExceptionWhenFindIndexOutOfBounds() {
        this.stock.find(1);
    }

    @Test
    public void shouldAddQuantityToExistingProduct() {
        this.stock.add(product);

        this.stock.changeQuantity(product.getLabel(), DEFAULT_PRODUCT_QUANTITY);

        Product actual = this.stock.find(1);

        assertEquals(2 * DEFAULT_PRODUCT_QUANTITY, actual.getQuantity());
    }

    @Test
    public void shouldAddQuantityToExistingProductMultipleTimes() {
        this.stock.add(product);

        this.stock.changeQuantity(product.getLabel(), DEFAULT_PRODUCT_QUANTITY);
        this.stock.changeQuantity(product.getLabel(), DEFAULT_PRODUCT_QUANTITY);
        this.stock.changeQuantity(product.getLabel(), DEFAULT_PRODUCT_QUANTITY);

        Product actual = this.stock.find(1);

        assertEquals(4 * DEFAULT_PRODUCT_QUANTITY, actual.getQuantity());
    }

    @Test (expected = IllegalArgumentException.class)
    public void quantityShouldThrowExeptionIfProductDoesNotExist() {
        this.stock.changeQuantity(product.getLabel(), DEFAULT_PRODUCT_QUANTITY);
    }

    @Test
    public void findByLabelShoudReturnProduct() {
        this.stock.add(product);
        Product actual = this.stock.findByLabel(product.getLabel());
        assertEquals(actual, product);
    }

    @Test (expected = IllegalArgumentException.class)
    public void findByLabelShouldThrowExeptionIfLabelDoesNotExist() {
        this.stock.findByLabel(product.getLabel());
    }

    @Test
    public void findByLabelShouldReturnProductForMultipleProducts() {
        seedStock();

        Product actual = this.stock.findByLabel(DEFAULT_PRODUCT_LABEL + "2");
        assertEquals(actual, second);
    }

    @Test
    public void findFirstByAlphabeticalOrderShouldReturnValidResultForZeroProducts() {
        seedStock();

        Iterable<Product> actual = this.stock.findFirstByAlphabeticalOrder(0);
        assertFalse(actual.iterator().hasNext());
    }

    @Test
    public void findFirstByAlphabeticalOrderShouldReturnValidResultForEmptyStock() {
        Iterable<Product> actual = this.stock.findFirstByAlphabeticalOrder(0);

        assertFalse(actual.iterator().hasNext());
    }

    @Test
    public void findFirstByAlphabeticalOrderShouldReturnValidResultForNegativeCount() {
        Iterable<Product> actual = this.stock.findFirstByAlphabeticalOrder(-1);

        assertFalse(actual.iterator().hasNext());
    }

    @Test
    public void findFirstByAlphabeticalOrderShouldReturnValidResultForGreaterCount() {
        seedStock();

        Iterable<Product> actual = this.stock.findFirstByAlphabeticalOrder(5);
        assertFalse(actual.iterator().hasNext());
    }

    @Test
    public void findFirstByAlphabeticalOrderShouldReturnValidResult() {
        this.stock.add(second);
        this.stock.add(third);
        this.stock.add(product);

        Product[] sorted = {product, second, third};

        Iterable<Product> actual = this.stock.findFirstByAlphabeticalOrder(3);
        Iterator<Product> iterator = actual.iterator();

        assertArrayAndIterable(sorted, iterator);
    }

    @Test
    public void findAllInPriceRangeShouldReturnEmptyIterableForEmptyStock() {
        Iterable<Product> actual = this.stock.findAllInRange(5, 15);

        assertFalse(actual.iterator().hasNext());
    }

    @Test
    public void findAllInPriceRangeShouldReturnEmptyIterableIfNoneMatchPrice() {
        this.stock.add(this.product);

        Iterable<Product> actual = this.stock.findAllInRange(5, 15);

        assertFalse(actual.iterator().hasNext());
    }

    @Test
    public void findAllInPriceRangeShouldReturnEmptyIterableForNegativePrices() {
        this.stock.add(this.product);

        Iterable<Product> actual = this.stock.findAllInRange(-5, -15);

        assertFalse(actual.iterator().hasNext());
    }

    @Test
    public void findAllInPriceRangeShouldReturnValidResultFofAllMatchingRange() {
        seedStock();

        Iterable<Product> actual = this.stock.findAllInRange(DEFAULT_PRODUCT_PRICE - 1, 11);

        Iterator<Product> iterator = actual.iterator();

        Product[] sorted = {third, second, product};

        assertArrayAndIterable(sorted, iterator);
    }

    @Test
    public void findAllInPriceRangeShouldReturnValidResultForSomeMatchingRange() {
        seedStock();

        Iterable<Product> actual = this.stock.findAllInRange(DEFAULT_PRODUCT_PRICE - 1, 10);

        Iterator<Product> iterator = actual.iterator();

        Product[] sorted = {second, product};

        assertArrayAndIterable(sorted, iterator);
    }

    @Test
    public void findAllByPriceShouldReturnEmptyIterableForEmptyStock() {
        Iterable<Product> actual = this.stock.findAllByPrice(10);

        assertFalse(actual.iterator().hasNext());
    }

    @Test
    public void findAllByPriceShouldReturnEmptyIterableIfNoneMatchPrice() {
        seedStock();

        Iterable<Product> actual = this.stock.findAllByPrice(5);

        assertFalse(actual.iterator().hasNext());
    }

    @Test
    public void findAllByPriceShouldReturnValidResult() {
        seedStock();

        Iterable<Product> actual = this.stock.findAllByPrice(10);

        List<Product> expected = new ArrayList<>();
        expected.add(second);

        assertEquals(actual, expected);
    }

    @Test (expected = IllegalArgumentException.class)
    public void findMostExpensiveShouldThrowExceptionIfStockIsEmpty() {
        this.stock.findFirstMostExpensiveProducts(4);
    }

    @Test (expected = IllegalArgumentException.class)
    public void findMostExpensiveShouldThrowExceptionIfLessThanCountExist() {
        seedStock();

        this.stock.findFirstMostExpensiveProducts(4);
    }

    @Test
    public void findMostExpensiveShouldReturnCorrectResult() {
        seedStock();

        Iterable<Product> firstMostExpensiveProducts = this.stock.findFirstMostExpensiveProducts(2);

        Product[] sorted = {third, second};

        Iterator<Product> actual = firstMostExpensiveProducts.iterator();

        assertArrayAndIterable(sorted, actual);
    }

    @Test
    public void findAllByQuantityShouldReturnEmptyIterableWhenStockIsEmpty() {
        Iterable<Product> allByQuantity = this.stock.findAllByQuantity(2);

        assertFalse(allByQuantity.iterator().hasNext());
    }

    @Test
    public void findAllByQuantityShouldReturnEmptyIterableWhenNoneMatchQuantity() {
        seedStock();

        Iterable<Product> allByQuantity = this.stock.findAllByQuantity(210);

        assertFalse(allByQuantity.iterator().hasNext());
    }

    @Test
    public void findAllByQuantityShouldReturnValidResult() {
        seedStock();

        Iterable<Product> allByQuantity = this.stock.findAllByQuantity(10);

        Product[] sorted = {second, third};

        Iterator<Product> iterator = allByQuantity.iterator();

        assertArrayAndIterable(sorted, iterator);
    }

    @Test
    public void iteratorShouldBeEmptyWhenStackIsEmpty() {
        assertFalse(stock.iterator().hasNext());
    }

    @Test
    public void iteratorShouldWorkCorrectly() {
        seedStock();

        Iterator<Product> iterator = this.stock.iterator();

        Product[] sorted = {product, second, third};

        assertArrayAndIterable(sorted, iterator);
    }

    private void seedStock() {
        this.stock.add(this.product);
        this.stock.add(this.second);
        this.stock.add(this.third);
    }

    private void assertArrayAndIterable(Product[] sorted, Iterator<Product> actual) {
        assertTrue(actual.hasNext());
        int i = 0;
        while (actual.hasNext()) {
            assertEquals(sorted[i++], actual.next());
        }
    }
}
