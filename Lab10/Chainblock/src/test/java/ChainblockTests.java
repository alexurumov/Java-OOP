import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;

public class ChainblockTests {

    private static final int INITIAL_ID = 1;
    private static final TransactionStatus TRANSACTION_STATUS = TransactionStatus.SUCCESSFUL;
    private static final String SENDER = "Sender";
    private static final String RECEIVER = "Receiver";
    private static final double INITIAL_AMOUNT = 12.0;


    private Transaction first;
    private Transaction second;
    private Transaction third;

    private Chainblock chainblock;

    @Before
    public void setup() {
        this.chainblock = new ChainblockImpl();
        this.first  = new TransactionImpl(INITIAL_ID, TRANSACTION_STATUS, SENDER, RECEIVER, INITIAL_AMOUNT);
        this.second  = new TransactionImpl(INITIAL_ID + 1, TransactionStatus.FAILED, SENDER, RECEIVER, INITIAL_AMOUNT + 5.0);;
        this.third = new TransactionImpl(INITIAL_ID + 2, TransactionStatus.SUCCESSFUL, SENDER, RECEIVER, INITIAL_AMOUNT + 10.0);
    }

    @Test
    public void constructorShouldInitialiseRecordDataStructure() {
        assertNotNull(this.chainblock.getRecord());
    }

    @Test
    public void addShouldIncreaseRecordSizeCorrectly() {
        this.chainblock.add(first);

        assertEquals(1, this.chainblock.getCount());
    }

    @Test
    public void addShouldNotIncreaseSizeWhenDuplicateTransactionIds() {
        this.chainblock.add(first);
        this.chainblock.add(first);
        this.chainblock.add(first);

        assertEquals(1, this.chainblock.getCount());
    }

    @Test
    public void containsShouldReturnFalseIfTransactionIsNotContainedById() {
        this.chainblock.add(first);
        assertFalse(this.chainblock.contains(2));
    }

    @Test
    public void containsShouldReturnFalseIfTransactionIsNotContainedByTransaction() {
        assertFalse(this.chainblock.contains(first));
    }

    @Test
    public void containsShouldReturnTrueIfPresentByTransaction() {
        this.chainblock.add(first);
        assertTrue(this.chainblock.contains(first));
    }

    @Test
    public void containsShouldReturnTrueIfPresentById() {
        this.chainblock.add(first);
        assertTrue(this.chainblock.contains(INITIAL_ID));
    }

    @Test
    public void getCountShouldReturnZeroIfRecordIsEmpty() {
        assertEquals(0, this.chainblock.getCount());
    }

    @Test
    public void getCountShouldReturnCorrectSize() {
        this.chainblock.add(first);
        Transaction second = new TransactionImpl(INITIAL_ID + 1, TRANSACTION_STATUS, SENDER, RECEIVER, INITIAL_AMOUNT);
        this.chainblock.add(second);

        assertEquals(2, this.chainblock.getCount());
    }

    @Test (expected = IllegalArgumentException.class)
    public void changeStatusShouldThrowExceptionIfNoSuchTransactionExists() {
        this.chainblock.changeTransactionStatus(1, TransactionStatus.FAILED);
    }

    @Test (expected = IllegalArgumentException.class)
    public void getByIdShouldThrowExceptionIfNoSuchTransaction() {
        this.chainblock.getById(1);
    }

    @Test
    public void getByIdShouldReturnCorrectTransactionById() {
        this.chainblock.add(first);
        Transaction transaction = this.chainblock.getById(1);
        assertEquals(first, transaction);
    }

    @Test
    public void changeStatusShouldWorkProperly() {
        this.chainblock.add(first);
        this.chainblock.changeTransactionStatus(INITIAL_ID, TransactionStatus.FAILED);

        assertEquals(TransactionStatus.FAILED, this.chainblock.getById(INITIAL_ID).getStatus());
    }

    @Test (expected = IllegalArgumentException.class)
    public void removeTransactionShouldThrowExceptionIfNoSuchTransaction() {
        this.chainblock.removeTransactionById(INITIAL_ID);
    }

    @Test
    public void removeTransactionShouldRemoveTransactionFromRecord() {
        this.chainblock.add(first);
        this.chainblock.removeTransactionById(INITIAL_ID);
        assertFalse(this.chainblock.contains(first));
    }

    @Test (expected = IllegalArgumentException.class)
    public void getByStatusShouldReturnThrowExceptionIfNoSuch() {
        this.chainblock.add(first);
        this.chainblock.add(second);

        this.chainblock.getByTransactionStatus(TransactionStatus.UNAUTHORIZED);
    }

    @Test
    public void getByStatusShouldReturnCorrectResult() {
        this.chainblock.add(first);
        this.chainblock.add(second);
        this.chainblock.changeTransactionStatus(second.getId(), TransactionStatus.SUCCESSFUL);
        this.chainblock.add(third);

        Iterable<Transaction> actual = this.chainblock.getByTransactionStatus(TransactionStatus.SUCCESSFUL);


        Iterator<Transaction> iterator = actual.iterator();

        assertIterableIsOrderedDecending(iterator);
    }

    @Test (expected = IllegalArgumentException.class)
    public void getAllSendersWithTransactionStatusShouldThrowExceptionIfNoSuchFound() {
        TransactionImpl t1 = new TransactionImpl(1, TransactionStatus.SUCCESSFUL, "Pesho", "Gosho", 10);
        TransactionImpl t2 = new TransactionImpl(2, TransactionStatus.FAILED, "Pesho", "Gosho", 10);
        TransactionImpl t3 = new TransactionImpl(3, TransactionStatus.SUCCESSFUL, "Pesho", "Gosho", 30);
        TransactionImpl t4 = new TransactionImpl(4, TransactionStatus.ABORTED, "Pesho", "Gosho", 10);

        this.chainblock.getAllSendersWithTransactionStatus(TransactionStatus.SUCCESSFUL);

    }

    @Test
    public void getAllSendersWithTransactionStatusShouldReturnCorrectSendersOrdered() {
        TransactionImpl t1 = new TransactionImpl(1, TransactionStatus.SUCCESSFUL, "Pesho", "Gosho", 10);
        TransactionImpl t2 = new TransactionImpl(2, TransactionStatus.FAILED, "Pesho", "Gosho", 10);
        TransactionImpl t3 = new TransactionImpl(3, TransactionStatus.SUCCESSFUL, "Gosho", "Pesho", 30);
        TransactionImpl t4 = new TransactionImpl(4, TransactionStatus.SUCCESSFUL, "Sasho", "Gosho", 20);
        this.chainblock.add(t1);
        this.chainblock.add(t2);
        this.chainblock.add(t3);
        this.chainblock.add(t4);

        Iterable<String> senders = this.chainblock.getAllSendersWithTransactionStatus(TransactionStatus.SUCCESSFUL);

        Iterator<String> iterator = senders.iterator();

        String[] actual = {"Gosho", "Sasho", "Pesho"};

        int i = 0;
        while (iterator.hasNext()) {
            assertEquals(actual[i++], iterator.next());
        }
    }

    @Test (expected = IllegalArgumentException.class)
    public void getAllReceiversWithTransactionStatusShouldThrowExceptionIfNoSuchFound() {
        TransactionImpl t1 = new TransactionImpl(1, TransactionStatus.SUCCESSFUL, "Pesho", "Gosho", 10);
        TransactionImpl t2 = new TransactionImpl(2, TransactionStatus.FAILED, "Gosho", "Pesho", 10);
        TransactionImpl t3 = new TransactionImpl(3, TransactionStatus.SUCCESSFUL, "Pesho", "Sasho", 30);
        TransactionImpl t4 = new TransactionImpl(4, TransactionStatus.ABORTED, "Pesho", "Gosho", 10);

        this.chainblock.getAllReceiversWithTransactionStatus(TransactionStatus.UNAUTHORIZED);

    }

    @Test 
    public void getAllReceiversWithTransactionStatusShouldReturnCorrectReceivedsOrdered() {
        TransactionImpl t1 = new TransactionImpl(1, TransactionStatus.SUCCESSFUL, "Pesho", "Gosho", 10);
        TransactionImpl t2 = new TransactionImpl(2, TransactionStatus.FAILED, "Gosho", "Pesho", 10);
        TransactionImpl t3 = new TransactionImpl(3, TransactionStatus.SUCCESSFUL, "Pesho", "Sasho", 30);
        TransactionImpl t4 = new TransactionImpl(4, TransactionStatus.ABORTED, "Pesho", "Gosho", 10);

        this.chainblock.add(t1);
        this.chainblock.add(t2);
        this.chainblock.add(t3);
        this.chainblock.add(t4);

        Iterable<String> receivers = this.chainblock.getAllReceiversWithTransactionStatus(TransactionStatus.SUCCESSFUL);
        Iterator<String> iterator = receivers.iterator();

        String[] actual = {"Sasho", "Gosho"};

        int i = 0;
        while (iterator.hasNext()) {
            assertEquals(actual[i++], iterator.next());
        }

    }

    @Test
    public void getAllOrderedByAmountDescendingThenByIdShouldReturnEmptyIterableWhenNoTransactionsFound() {
        Iterable<Transaction> ordered = this.chainblock.getAllOrderedByAmountDescendingThenById();
        boolean hasNext = ordered.iterator().hasNext();
        assertFalse(hasNext);
    }

    @Test
    public void getAllOrderedByAmountDescendingThenByIdShouldReturnCorrectTransactionsOrdered() {
        TransactionImpl t1 = new TransactionImpl(1, TransactionStatus.SUCCESSFUL, "Pesho", "Gosho", 10);
        TransactionImpl t2 = new TransactionImpl(2, TransactionStatus.FAILED, "Gosho", "Pesho", 20);
        TransactionImpl t3 = new TransactionImpl(3, TransactionStatus.SUCCESSFUL, "Pesho", "Sasho", 30);
        TransactionImpl t4 = new TransactionImpl(4, TransactionStatus.ABORTED, "Pesho", "Gosho", 10);

        this.chainblock.add(t1);
        this.chainblock.add(t2);
        this.chainblock.add(t3);
        this.chainblock.add(t4);

        Iterable<Transaction> ordered = this.chainblock.getAllOrderedByAmountDescendingThenById();
        Iterator<Transaction> iterator = ordered.iterator();

        Transaction[] actual = {t3, t2, t1, t4};

        assertArrayAndIterable(iterator, actual);
    }

    @Test (expected = IllegalArgumentException.class)
    public void getBySenderOrderedByAmountDescendingShouldThrowExeptionWhenNoSuchFound() {
        Iterable<Transaction> ordered = this.chainblock.getBySenderOrderedByAmountDescending("Pesho");
    }

    @Test
    public void getBySenderOrderedByAmountDescendingShouldReturnCorrectSorted() {
        TransactionImpl t1 = new TransactionImpl(1, TransactionStatus.SUCCESSFUL, "Pesho", "Gosho", 10);
        TransactionImpl t2 = new TransactionImpl(2, TransactionStatus.FAILED, "Gosho", "Pesho", 20);
        TransactionImpl t3 = new TransactionImpl(3, TransactionStatus.SUCCESSFUL, "Pesho", "Sasho", 30);
        TransactionImpl t4 = new TransactionImpl(4, TransactionStatus.ABORTED, "Pesho", "Gosho", 10);

        this.chainblock.add(t1);
        this.chainblock.add(t2);
        this.chainblock.add(t3);
        this.chainblock.add(t4);

        Iterable<Transaction> ordered = this.chainblock.getBySenderOrderedByAmountDescending("Pesho");
        Iterator<Transaction> iterator = ordered.iterator();

        Transaction[] actual = {t3, t1, t4};

        assertArrayAndIterable(iterator, actual);
    }

    @Test (expected = IllegalArgumentException.class)
    public void getByReceiverOrderedByAmountThenByIdShouldThrowExeptionWhenNoSuchFound() {
        this.chainblock.getByReceiverOrderedByAmountThenById("Pesho");
    }

    @Test
    public void getByReceiverOrderedByAmountThenByIdShouldReturnCorrectSorted() {
        TransactionImpl t1 = new TransactionImpl(1, TransactionStatus.SUCCESSFUL, "Pesho", "Gosho", 10);
        TransactionImpl t2 = new TransactionImpl(2, TransactionStatus.FAILED, "Gosho", "Pesho", 20);
        TransactionImpl t3 = new TransactionImpl(3, TransactionStatus.SUCCESSFUL, "Pesho", "Sasho", 30);
        TransactionImpl t4 = new TransactionImpl(4, TransactionStatus.ABORTED, "Pesho", "Gosho", 10);

        this.chainblock.add(t1);
        this.chainblock.add(t2);
        this.chainblock.add(t3);
        this.chainblock.add(t4);

        Iterable<Transaction> ordered = this.chainblock.getByReceiverOrderedByAmountThenById("Gosho");
        Iterator<Transaction> iterator = ordered.iterator();

        Transaction[] actual = {t1, t4};

        assertArrayAndIterable(iterator, actual);
    }

    @Test
    public void getByTransactionStatusAndMaximumAmountShouldReturnEmptyIterableWhenEmptyRecord() {
        Iterable<Transaction> ordered = this.chainblock.getByTransactionStatusAndMaximumAmount(TransactionStatus.SUCCESSFUL, 9);
        boolean hasNext = ordered.iterator().hasNext();
        assertFalse(hasNext);
    }

    @Test
    public void getByTransactionStatusAndMaximumAmountShouldReturnEmptyIterableWhenNoSuchFound() {
        TransactionImpl t1 = new TransactionImpl(1, TransactionStatus.SUCCESSFUL, "Pesho", "Gosho", 10);
        TransactionImpl t2 = new TransactionImpl(2, TransactionStatus.FAILED, "Gosho", "Pesho", 20);
        TransactionImpl t3 = new TransactionImpl(3, TransactionStatus.SUCCESSFUL, "Pesho", "Sasho", 30);
        TransactionImpl t4 = new TransactionImpl(4, TransactionStatus.ABORTED, "Pesho", "Gosho", 10);

        this.chainblock.add(t1);
        this.chainblock.add(t2);
        this.chainblock.add(t3);
        this.chainblock.add(t4);

        Iterable<Transaction> ordered = this.chainblock.getByTransactionStatusAndMaximumAmount(TransactionStatus.SUCCESSFUL, 9);
        boolean hasNext = ordered.iterator().hasNext();
        assertFalse(hasNext);
    }

    @Test
    public void getByTransactionStatusAndMaximumAmountShouldReturnCorrectlySortedTransactions() {
        TransactionImpl t1 = new TransactionImpl(1, TransactionStatus.SUCCESSFUL, "Pesho", "Gosho", 10);
        TransactionImpl t2 = new TransactionImpl(2, TransactionStatus.FAILED, "Gosho", "Pesho", 20);
        TransactionImpl t3 = new TransactionImpl(3, TransactionStatus.SUCCESSFUL, "Pesho", "Sasho", 30);
        TransactionImpl t4 = new TransactionImpl(4, TransactionStatus.ABORTED, "Pesho", "Gosho", 10);

        this.chainblock.add(t1);
        this.chainblock.add(t2);
        this.chainblock.add(t3);
        this.chainblock.add(t4);

        Iterable<Transaction> ordered = this.chainblock.getByTransactionStatusAndMaximumAmount(TransactionStatus.SUCCESSFUL, 40);
        Iterator<Transaction> iterator = ordered.iterator();

        Transaction[] actual = {t3, t1};

        assertArrayAndIterable(iterator, actual);
    }

    @Test (expected = IllegalArgumentException.class)
    public void getBySenderAndMinimumAmountDescendingShouldThrowExceptionWhenNoSuchFound() {
        this.chainblock.getBySenderAndMinimumAmountDescending("Pesho", 12);
    }

    @Test
    public void getBySenderAndMinimumAmountDescendingShouldReturnCorrectlySortedTransactions() {
        TransactionImpl t1 = new TransactionImpl(1, TransactionStatus.SUCCESSFUL, "Pesho", "Gosho", 10);
        TransactionImpl t2 = new TransactionImpl(2, TransactionStatus.FAILED, "Gosho", "Pesho", 20);
        TransactionImpl t3 = new TransactionImpl(3, TransactionStatus.SUCCESSFUL, "Pesho", "Sasho", 30);
        TransactionImpl t4 = new TransactionImpl(4, TransactionStatus.ABORTED, "Pesho", "Gosho", 10);

        this.chainblock.add(t1);
        this.chainblock.add(t2);
        this.chainblock.add(t3);
        this.chainblock.add(t4);

        Iterable<Transaction> ordered = this.chainblock.getBySenderAndMinimumAmountDescending("Pesho", 10);
        Iterator<Transaction> iterator = ordered.iterator();

        Transaction[] actual = {t3};

        assertArrayAndIterable(iterator, actual);
    }

    @Test (expected = IllegalArgumentException.class)
    public void getByReceiverAndAmountRangeShouldThrowExceptionWhenNoSuchFound() {
        this.chainblock.getByReceiverAndAmountRange("Pesho", 12, 15);
    }

    @Test
    public void getByReceiverAndAmountRangeShouldReturnCorrectTransactionsSorted() {
        TransactionImpl t1 = new TransactionImpl(1, TransactionStatus.SUCCESSFUL, "Pesho", "Gosho", 10);
        TransactionImpl t2 = new TransactionImpl(2, TransactionStatus.FAILED, "Gosho", "Pesho", 20);
        TransactionImpl t3 = new TransactionImpl(3, TransactionStatus.SUCCESSFUL, "Pesho", "Sasho", 30);
        TransactionImpl t4 = new TransactionImpl(4, TransactionStatus.ABORTED, "Pesho", "Gosho", 15);

        this.chainblock.add(t1);
        this.chainblock.add(t2);
        this.chainblock.add(t3);
        this.chainblock.add(t4);

        Iterable<Transaction> ordered = this.chainblock.getByReceiverAndAmountRange("Gosho", 10, 16);
        Iterator<Transaction> iterator = ordered.iterator();

        Transaction[] actual = {t4, t1};

        assertArrayAndIterable(iterator, actual);
    }

    @Test
    public void getAllInAmountRangeShouldReturnCorrectTransactionsSortedWhenSomeOutOfRange() {
        TransactionImpl t1 = new TransactionImpl(1, TransactionStatus.SUCCESSFUL, "Pesho", "Gosho", 10);
        TransactionImpl t2 = new TransactionImpl(2, TransactionStatus.FAILED, "Gosho", "Pesho", 20);
        TransactionImpl t3 = new TransactionImpl(3, TransactionStatus.SUCCESSFUL, "Pesho", "Sasho", 30);
        TransactionImpl t4 = new TransactionImpl(4, TransactionStatus.ABORTED, "Pesho", "Gosho", 15);

        this.chainblock.add(t1);
        this.chainblock.add(t2);
        this.chainblock.add(t3);
        this.chainblock.add(t4);

        Iterable<Transaction> ordered = this.chainblock.getAllInAmountRange(10, 20);
        Iterator<Transaction> iterator = ordered.iterator();

        Transaction[] actual = {t1, t2, t4};

        assertArrayAndIterable(iterator, actual);
    }

    @Test
    public void getAllInAmountRangeShouldReturnCorrectTransactionsSortedWhenAllOutOfRange() {
        TransactionImpl t1 = new TransactionImpl(1, TransactionStatus.SUCCESSFUL, "Pesho", "Gosho", 10);
        TransactionImpl t2 = new TransactionImpl(2, TransactionStatus.FAILED, "Gosho", "Pesho", 20);
        TransactionImpl t3 = new TransactionImpl(3, TransactionStatus.SUCCESSFUL, "Pesho", "Sasho", 30);
        TransactionImpl t4 = new TransactionImpl(4, TransactionStatus.ABORTED, "Pesho", "Gosho", 15);

        this.chainblock.add(t1);
        this.chainblock.add(t2);
        this.chainblock.add(t3);
        this.chainblock.add(t4);

        Iterable<Transaction> ordered = this.chainblock.getAllInAmountRange(4, 9);
        boolean hasNext = ordered.iterator().hasNext();

        assertFalse(hasNext);
    }

    @Test
    public void getAllInAmountRangeShouldReturnCorrectTransactionsSortedWhenAllInRange() {
        TransactionImpl t1 = new TransactionImpl(1, TransactionStatus.SUCCESSFUL, "Pesho", "Gosho", 10);
        TransactionImpl t2 = new TransactionImpl(2, TransactionStatus.FAILED, "Gosho", "Pesho", 20);
        TransactionImpl t3 = new TransactionImpl(3, TransactionStatus.SUCCESSFUL, "Pesho", "Sasho", 30);
        TransactionImpl t4 = new TransactionImpl(4, TransactionStatus.ABORTED, "Pesho", "Gosho", 15);

        this.chainblock.add(t1);
        this.chainblock.add(t2);
        this.chainblock.add(t3);
        this.chainblock.add(t4);

        Iterable<Transaction> ordered = this.chainblock.getAllInAmountRange(10, 30);
        Iterator<Transaction> iterator = ordered.iterator();

        Transaction[] actual = {t1, t2, t3, t4};

        assertArrayAndIterable(iterator, actual);
    }

    private void assertArrayAndIterable(Iterator<Transaction> iterator, Transaction[] actual) {
        int i = 0;
        while (iterator.hasNext()) {
            assertEquals(actual[i++], iterator.next());
        }
    }


    private void assertIterableIsOrderedDecending(Iterator<Transaction> iterator) {
        boolean sorted = true;
        while (iterator.hasNext()) {
            if (iterator.next().getAmount() > iterator.next().getAmount()) {
                sorted = false;
                return;
            }
        }

        assertTrue(sorted);
    }
}

