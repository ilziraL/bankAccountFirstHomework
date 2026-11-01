public final class Balance {
    private final long amountInKop;
    private final Currency currency;

    public long getAmountInKop() {
        return amountInKop;
    }
    public Currency getCurrency() {
        return currency;
    }

    public Balance(long amountInKop, Currency currency) {
        if (currency == null) {
            throw new IllegalArgumentException("Валюта не может быть null");
        }
        if (amountInKop < 0) {
            throw new IllegalArgumentException("Сумма баланса не может быть отрицательной");
        }

        this.amountInKop = amountInKop;
        this.currency = currency;
    }

    public Balance add(long amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Нельзя добавить отрицательную сумму");
        }
        return new Balance(this.amountInKop + amount, this.currency);
    }

    public Balance subtract(long amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Нельзя вычесть отрицательную сумму");
        }
        if (amount > this.amountInKop) {
            throw new IllegalArgumentException("Недостаточно средств на балансе");
        }
        return new Balance(this.amountInKop - amount, this.currency);
    }

    public Balance convertation(Currency newCurrency) {
        if (newCurrency == null) {
            throw new IllegalArgumentException("Целевая валюта не может быть null");
        }
        if (this.currency == newCurrency) {
            return this;
        }

        long amountInNewKop = (this.amountInKop * this.currency.getPriceInRublesKopecks()) / newCurrency.getPriceInRublesKopecks();

        return new Balance(amountInNewKop, newCurrency);
    }

    @Override
    public String toString() {
        return String.format("%.2f %s", amountInKop / 100.0, currency.getSymbol());
    }
}
