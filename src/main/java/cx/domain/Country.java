package cx.domain;

// todo: make it a class, load from a resource with >> ISOcode=Country name
public enum Country {

    ARGENTINA("ARG", "Argentina"),
    AUSTRALIA("AUS", "Australia"),
    AUSTRIA("AUT", "Austria"),
    BELGIUM("BEL", "Belgium"),
    BRAZIL("BRA", "Brazil"),
    CANADA("CAN", "Canada"),
    CHINA("CHN", "China"),
    FRANCE("FRA", "France"),
    INDONESIA("IDN", "Indonesia"),
    ITALY("ITA", "Italy"),
    GERMANY("DEU", "Germany"),
    MACAO("MAC", "Macao"),
    PERU("PER", "Peru"),
    RUSSIA("RUS", "Russia"),
    TUVALU("TUV", "Tuvalu"),
    UK("GBR", "United Kingdom"),
    USA("USA", "United States");

    private final String code;
    private final String text;

    Country(final String code, final String text) {
        this.code = code;
        this.text = text;
    }

    public String getCode() {
        return code;
    }

    public String getText() {
        return text;
    }
}
