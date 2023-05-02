package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreditAccountTest {

    @Test
    public void shouldAddToPositiveBalance() {
        CreditAccount account = new CreditAccount(0, 5_000, 15);

        account.add(3_000);

        Assertions.assertEquals(3_000, account.getBalance());
        Assertions.assertEquals(15, account.getRate());
        Assertions.assertEquals(5000, account.getCreditLimit());
    }

    @Test
    public void shouldNegativeCreditLimit() {   //отрицательный creditLimit
        Assertions.assertThrows(IllegalArgumentException.class, () -> new CreditAccount(0, -5000, 15));
    }

    @Test
    public void shouldNegativeCreditRate() {    //отрицательный rate
        Assertions.assertThrows(IllegalArgumentException.class, () -> new CreditAccount(1000, 5000, -15));
    }

    @Test
    public void shouldPay() {       //обычная покупка минус баланс
        CreditAccount account = new CreditAccount(0, 5000, 15);

        account.add(3000);
        account.pay(1000);
        Assertions.assertEquals(2000, account.getBalance());
    }

    @Test
    public void shouldPayAmountMoreThanBalance() {   //покупка на сумму больше баланса, баланс не может уйти в минус.
        CreditAccount account = new CreditAccount(2000, 5000, 15);

        account.pay(4000);
        Assertions.assertEquals(2000, account.getBalance());
    }

    @Test
    public void shouldPayAmountNegativeBalance() {   //покупка при отрицательном балансе, баланс не должен поменяться
        CreditAccount account = new CreditAccount(-2000, 5000, 15);

        account.pay(4000);
        Assertions.assertEquals(-2000, account.getBalance());
    }

    @Test
    public void shouldAddToNegativeBalance() {   //пополнение при отрицательном балансе
        CreditAccount account = new CreditAccount(-1000, 5000, 15);

        account.add(3000);

        Assertions.assertEquals(2000, account.getBalance());
    }

    @Test
    public void shouldMethodYearsChangeWithNegativeBalance() {   //расчет процентной ставки при отрицательном балансе
        CreditAccount account = new CreditAccount(-200, 0, 15);

        Assertions.assertEquals(-30, account.yearChange());
    }

    @Test
    public void shouldMethodYearsChangeWithBalance() {   //расчет процентной ставки при положительном балансе
        CreditAccount account = new CreditAccount(200, 0, 15);

        Assertions.assertEquals(0, account.yearChange());
    }
}