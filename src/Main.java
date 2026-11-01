public class Main{
    public static void main(String[] args) {
        Balance balance1 = new Balance(100000, Currency.RUB);
        Balance balance2 = new Balance(70000, Currency.RUB);

        BankAccount bankAccount1 = new BankAccount("Рома", 30000, balance1);
        BankAccount bankAccount2 = new BankAccount("Маша", 50000, balance2);

        System.out.println(bankAccount1);
        System.out.println(bankAccount2);

        System.out.println("\nТестируем успешные операции:\n");

        System.out.println("\nПополнение счёта:");
        bankAccount1.replenishment(10000);
        System.out.println(bankAccount1);

        System.out.println("\nСнятие со счёта:");
        bankAccount2.withdrawl(10000);
        System.out.println(bankAccount2);

        System.out.println("\nПеревод между счетами:");
        bankAccount1.transfer(bankAccount2, 10000);
        System.out.println(bankAccount1);
        System.out.println(bankAccount2);

        System.out.println("\nКонвертация валют:");
        Balance USD_Balance = new Balance(10000, Currency.USD);
        System.out.println("Баланс в долларах: " + USD_Balance);
        Balance RUB_Balance = USD_Balance.convertation(Currency.RUB);
        System.out.println("Баланс в рублях: " + RUB_Balance);

        System.out.println("\nТестируем неуспешные операции:\n");

        System.out.println("Снятие со счёта суммы выше лимита:");
        try {
            bankAccount2.withdrawl(100000);
            System.out.println(bankAccount2);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        System.out.println("\nПеревод между счетами отрицательной суммы:");
        try {
            bankAccount1.transfer(bankAccount2, -10000);
            System.out.println(bankAccount1);
            System.out.println(bankAccount2);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        System.out.println("\nПополнение заблокированного счёта:");
        bankAccount1.blockAccount();
        try {
            bankAccount1.replenishment(1000);
            System.out.println(bankAccount1);
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}