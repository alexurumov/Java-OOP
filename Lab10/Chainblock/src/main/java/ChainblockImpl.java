import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ChainblockImpl implements Chainblock {

    private Map<Integer, Transaction> record;

    public ChainblockImpl() {
        this.record = new HashMap<>();
    }

    @Override
    public int getCount() {
        return this.record.size();
    }

    @Override
    public Map<Integer, Transaction> getRecord() {
        return this.record;
    }

    @Override
    public void add(Transaction transaction) {
        this.record.putIfAbsent(transaction.getId(), transaction);
    }

    @Override
    public boolean contains(Transaction transaction) {
        return this.record.containsValue(transaction);
    }

    @Override
    public boolean contains(int id) {
        return this.record.containsKey(id);
    }

    @Override
    public void changeTransactionStatus(int id, TransactionStatus newStatus) {
        this.getById(id).setStatus(newStatus);
    }

    @Override
    public void removeTransactionById(int id) {
        this.record.remove(this.getById(id).getId());
    }

    @Override
    public Transaction getById(int id) {
        if (!this.record.containsKey(id)) throw new IllegalArgumentException();
        return this.record.get(id);
    }

    @Override
    public Iterable<Transaction> getByTransactionStatus(TransactionStatus status) {
        List<Transaction> result = getSortedByStatusStream(status)
                .collect(Collectors.toList());

        if (result.size() == 0) throw new IllegalArgumentException();

        return result;
    }

    @Override
    public Iterable<String> getAllSendersWithTransactionStatus(TransactionStatus status) {
        List<String> result = this.getSortedByStatusStream(status).map(Transaction::getFrom).collect(Collectors.toList());
        if (result.size() == 0) throw new IllegalArgumentException();
        return result;
    }

    @Override
    public Iterable<String> getAllReceiversWithTransactionStatus(TransactionStatus status) {
        List<String> result = this.getSortedByStatusStream(status).map(Transaction::getTo).collect(Collectors.toList());
        if (result.size() == 0) throw new IllegalArgumentException();
        return result;
    }

    @Override
    public Iterable<Transaction> getAllOrderedByAmountDescendingThenById() {
        return this.record.values()
                .stream()
                .sorted((f, s) -> {
                    int compare = Double.compare(s.getAmount(), f.getAmount());
                    if (compare == 0) {
                        return Integer.compare(f.getId(), s.getId());
                    }
                    return compare;
                }).collect(Collectors.toList());
    }

    @Override
    public Iterable<Transaction> getBySenderOrderedByAmountDescending(String sender) {
        List<Transaction> result = getTransactionsBySenderStream(sender)
                .sorted((f, s) -> Double.compare(s.getAmount(), f.getAmount()))
                .collect(Collectors.toList());

        if (result.size() == 0) throw new IllegalArgumentException();

        return result;
    }

    @Override
    public Iterable<Transaction> getByReceiverOrderedByAmountThenById(String receiver) {
        List<Transaction> result = getTransactionsByReceivedStream(receiver)
                .sorted((f, s) -> {
                    int compare = Double.compare(f.getAmount(), s.getAmount());
                    if (compare == 0) return Integer.compare(f.getId(), s.getId());
                    return compare;
                })
                .collect(Collectors.toList());

        if (result.isEmpty()) throw new IllegalArgumentException();

        return result;
    }

    private Stream<Transaction> getTransactionsByReceivedStream(String receiver) {
        return this.record.values().stream()
                .filter(transaction -> transaction.getTo().equals(receiver));
    }

    @Override
    public Iterable<Transaction> getByTransactionStatusAndMaximumAmount(TransactionStatus status, double amount) {
        return this.record.values()
                .stream()
                .filter(transaction -> transaction.getStatus().equals(status))
                .filter(transaction -> transaction.getAmount() <= amount)
                .sorted((f, s) -> Double.compare(s.getAmount(), f.getAmount()))
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Transaction> getBySenderAndMinimumAmountDescending(String sender, double amount) {
        List<Transaction> result = this.getTransactionsBySenderStream(sender)
                .filter(transaction -> transaction.getAmount() > amount)
                .sorted((f, s) -> Double.compare(s.getAmount(), f.getAmount()))
                .collect(Collectors.toList());

        if (result.isEmpty()) throw new IllegalArgumentException();

        return result;
    }

    @Override
    public Iterable<Transaction> getByReceiverAndAmountRange(String receiver, double lo, double hi) {
        List<Transaction> result = getTransactionsByReceivedStream(receiver)
                .filter(transaction -> transaction.getAmount() >= lo && transaction.getAmount() < hi)
                .collect(Collectors.toList());

        if (result.isEmpty()) throw new IllegalArgumentException();

        return result.stream()
                .sorted((f, s) -> {
                    int compare = Double.compare(s.getAmount(), f.getAmount());
                    if (compare == 0) return Integer.compare(f.getId(), s.getId());
                    return compare;
                }).collect(Collectors.toList());
    }

    @Override
    public Iterable<Transaction> getAllInAmountRange(double lo, double hi) {
        return this.record.values()
                .stream()
                .filter(transaction -> transaction.getAmount() >= lo && transaction.getAmount() <= hi)
                .collect(Collectors.toList());
    }

    @Override
    public Iterator<Transaction> iterator() {
        return this.record.values().iterator();
    }

    private Stream<Transaction> getSortedByStatusStream(TransactionStatus status) {
        return this.record.values()
                .stream()
                .filter(tr -> tr.getStatus().equals(status))
                .sorted((f, s) -> Double.compare(s.getAmount(), f.getAmount()));
    }

    private Stream<Transaction> getTransactionsBySenderStream(String sender) {
        return this.record.values()
                .stream()
                .filter(transaction -> transaction.getFrom().equals(sender));
    }
}
