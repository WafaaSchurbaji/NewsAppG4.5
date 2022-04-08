package at.ac.fhcampuswien.properties;

public enum Country implements ApiProperties {
    UNITED_ARAB_EMIRATES("ae"),
    ARGENTINA("ar"),
    AUSTRIA("at"),
    AUSTRALIA("au"),
    BELGIUM("be"),
    BULGARIA("bg"),
    BRAZIL("br"),
    CANADA("ca"),
    SWITZERLAND("ch"),
    CHINA("cn"),
    COLOMBIA("co"),
    CUBA("cu"),
    CZECH_REPUBLIC("cz"),
    GERMANY("de"),
    EGYPT("eg"),
    FRANCE("fr"),
    UNITED_KINGDOM("gb"),
    GREECE("gr"),
    HONG_KONG("hk"),
    HUNGARY("hu"),
    INDONESIA("id"),
    IRELAND("ie"),
    PALESTINE("il"),
    INDIA("in"),
    ITALY("it"),
    JAPAN("jp"),
    KOREA("kr"),
    LITHUANIA("lt"),
    LATVIA("lv"),
    MOROCCO("ma"),
    MEXICO("mx"),
    MALAYSIA("my"),
    NIGERIA("ng"),
    NETHERLANDS("nl"),
    NORWAY("no"),
    NEW_ZEALAND("nz"),
    PHILIPPINES("ph"),
    POLAND("pl"),
    PORTUGAL("pt"),
    ROMANIA("ro"),
    SERBIA("rs"),
    RUSSIA("ru"),
    SAUDI_ARABIA("sa"),
    SWEDEN("se"),
    SINGAPORE("sg"),
    SLOVENIA("si"),
    SLOVAKIA("sk"),
    THAILAND("th"),
    TURKEY("tr"),
    TAIWAN("tw"),
    UKRAINE("ua"),
    UNITED_STATES("us"),
    VENEZUELA("ve"),
    SOUTH_AFRICA("za"),
    DEFAULT("");

    private final String value;

    Country(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return this.value;
    }
}
