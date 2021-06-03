package ro.pub.cs.systems.eim.practicaltest02;

import java.util.Date;

public class CurrencyExchange {
    private String currency;
    private String rate;
    private Date update;

    public CurrencyExchange(String currency, String rate) {
        this.currency = currency;
        this.rate = rate;
        this.update = new Date();
    }

    public String getCurrency() {
        return currency;
    }

    public String getExchangeRate() {
        return rate;
    }

    public Date getUpdate() {
        return update;
    }
}
