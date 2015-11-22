package cx.domain;

// todo: read from a resource, with CODE=Symbol;Name
public enum Currency {

    AUD("$", "Australian Dollar"),
    BRL("R$", "Brazilian Real"),
    CHF("CHF", "Swiss Franc"),
    EUR("\u20ac", "Euro"),
    GBP("\u00a3", "United Kingdom Pound"),
    HUF("Ft", "Hungarian Forint"),
    JPY("\u00a5", "Japanese Yen"),
    NZD("$", "New Zealand Dollar"),
    USD("$", "United States Dollar");

    private final String symbol;
    private final String description;

    Currency(String symbol, String description) {
        this.symbol = symbol;
        this.description = description;
    }

    public String getCode() {
        return name();
    }

    public String getDescription() {
        return description;
    }

    public String getFullDescription() {
        return symbol + " - " + description;
    }
}
