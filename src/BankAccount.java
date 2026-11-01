public class BankAccount {
    private String bankName;
    private String ownerName;
    private Balance balance;
    private long withdrawalLimit;
    private boolean isActive;

    public String getOwnerName() {
        return ownerName;
    }
    public Balance getBalance() {
        return balance;
    }
    public long getWithdrawalLimit() {
        return withdrawalLimit;
    }

    public BankAccount(String ownerName) {
        this(ownerName, 100000, new Balance(0, Currency.RUB));
    }

    public BankAccount(String ownerName, long withdrawalLimit, Balance balansic) {
        if (ownerName == null || balansic == null) {
            throw new IllegalArgumentException("Имя владельца и баланс не могут быть null");
        }
        if (withdrawalLimit < 0) {
            throw new IllegalArgumentException("Лимит не может быть отрицательным");
        }

        this.ownerName = ownerName;
        this.bankName = "KFU Cash";
        this.isActive = true;
        this.withdrawalLimit = withdrawalLimit;
        this.balance = balansic;
    }

    public void replenishment(long amount) {
        checkActive();
        this.balance = this.balance.add(amount);
    }

    public void withdrawl(long amount) {
        checkActive();
        if (amount > withdrawalLimit) {
            throw new IllegalArgumentException("Превышен лимит на снятие. Максимум: " + (withdrawalLimit / 100.0));
        }
        this.balance = this.balance.subtract(amount);
    }

    public void transfer(BankAccount payee, long amount) {
        checkActive();
        if (payee == null) {
            throw new IllegalArgumentException("Счёт получателя не может быть null");
        }
        if (payee == this) {
            throw new IllegalArgumentException("Нельзя перевести деньги самому себе");
        }
        if (this.balance.getCurrency() != payee.getBalance().getCurrency()) {
            throw new IllegalArgumentException("Нельзя переводить между счетами без конвертации");
        }
        if (!payee.is_Active()) {
            throw new IllegalStateException("Счет получателя заблокирован"); // Проверяем счет получателя
        }

        this.withdrawl(amount);
        payee.replenishment(amount);
    }

    public void blockAccount() {
        this.isActive = false;
        System.out.println("Счёт " + ownerName + " заблокирован успешно");
    }

    public void unblockAccount() {
        this.isActive = true;
        System.out.println("Счёт " + ownerName + " разблокирован успешно");
    }

    public boolean is_Active() {
        return isActive;
    }

    private void checkActive() {
        if (!isActive) {
            throw new IllegalStateException("Операция отклонена: счет " + ownerName + " заблокирован");
        }
    }

    @Override
    public String toString() {
        return "Счёт " + ownerName + ": " + balance.toString();
    }
}