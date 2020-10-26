package bankAccount;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class BankAccountTest {
    private BankAccount bankAccount;
    private static final String DEFAULT_NAME = "BankAccount";
    private static final BigDecimal DEFAULT_AMOUNT = BigDecimal.valueOf(100);
    private static final BigDecimal DEPOSIT_AMOUNT = BigDecimal.valueOf(50);
    private static final BigDecimal WITHDRAW_AMOUNT = BigDecimal.valueOf(75);

    @Before
    public void setup() {
        this.bankAccount = new BankAccount(DEFAULT_NAME, DEFAULT_AMOUNT);
    }

    @Test (expected = IllegalArgumentException.class)
    public void setNameShouldThrowExceptionIfNameLengthIsLessThanLowerBound() {
        BankAccount bankAccount = new BankAccount("12", BigDecimal.valueOf(100));
    }

    @Test (expected = IllegalArgumentException.class)
    public void setNameShouldThrowExceptionIfNameLengthIsMoreThanUpperBound() {
        BankAccount bankAccount = new BankAccount("11111111111111111111111111", BigDecimal.valueOf(100));
    }

    @Test
    public void setNameShouldSetCorrectValueWhenInBounds() {
        BankAccount bankAccount = new BankAccount("BankAccount", BigDecimal.valueOf(100));
        String actual = bankAccount.getName();

        assertEquals(actual, "BankAccount");
    }

    @Test (expected = IllegalArgumentException.class)
    public void setBalanceShouldThrowExceptionWhenValueIsNegative() {
        BankAccount bankAccount = new BankAccount("BankAccount", BigDecimal.valueOf(-1));
    }

    @Test
    public void setBalanceShouldSetCorrectBalanceWhenNotNegative() {
        BankAccount bankAccount = new BankAccount("BankAccount", BigDecimal.valueOf(100));
        BigDecimal balance = bankAccount.getBalance();
        int actual = balance.intValue();

        assertEquals(actual, 100);
    }

    @Test (expected = UnsupportedOperationException.class)
    public void depositShouldThrowExceptionWhenAmountIsNegativeOrZero() {
        this.bankAccount.deposit(BigDecimal.valueOf(0));
    }

    @Test
    public void depositShouldIncreaseAmountCorrectly() {
        this.bankAccount.deposit(DEPOSIT_AMOUNT);

        assertEquals(this.bankAccount.getBalance(), DEPOSIT_AMOUNT.add(DEFAULT_AMOUNT));
    }

    @Test (expected = UnsupportedOperationException.class)
    public void withdrawShouldThrowExceptionWhenAmountIsNegative() {
        this.bankAccount.withdraw(BigDecimal.valueOf(-5));
    }

    @Test (expected = UnsupportedOperationException.class)
    public void withdrawShouldThrowExceptionWhenAmountMoreThanBalance() {
        this.bankAccount.withdraw(DEFAULT_AMOUNT.multiply(BigDecimal.valueOf(2)));
    }

    @Test
    public void withdrawShouldDecreaseBalanceCorrectly() {
        this.bankAccount.withdraw(WITHDRAW_AMOUNT);

        assertEquals(this.bankAccount.getBalance(), DEFAULT_AMOUNT.subtract(WITHDRAW_AMOUNT));
    }

    @Test
    public void getNameShouldReturnCorrectValue() {
        assertEquals(this.bankAccount.getName(), DEFAULT_NAME);
    }

    @Test
    public void getBalanceShouldReturnCorrectValue() {
        assertEquals(this.bankAccount.getBalance(), DEFAULT_AMOUNT);
    }

}