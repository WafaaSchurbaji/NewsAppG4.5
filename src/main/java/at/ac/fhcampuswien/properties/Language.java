package at.ac.fhcampuswien.properties;

public enum Language implements ApiProperties {
    ARABIC("ar"),
    GERMAN("de"),
    ENGLISH("en"),
    SPANISH("es"),
    FRENCH("fr"),
    ITALIAN("it"),
    HEBREW("he"),
    DUTCH("nl"),
    NORWEGIAN("no"),
    PORTUGUESE("pt"),
    RUSSIAN("ru"),
    NORTHERN_SAMI("se"),
    CHINESE("zh"),
    DEFAULT("");

    private final String value;

    Language(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return this.value;
    }

    public static Language getLanguageByCode(String code) {
        for (Language language : Language.values()) {
            if (language.value.equalsIgnoreCase(code)) return language;
        }
        return null;
    }

    public static Language getLanguageByPrettyName(String name) {
        for (Language language : Language.values()) {
            if (language.getPrettyName().equalsIgnoreCase(name)) return language;
        }
        return null;
    }

    public String getPrettyName() {
        String prettyName = name().toLowerCase().replaceAll("_", " ");
        return prettyName.substring(0, 1).toUpperCase() + prettyName.substring(1);
    }

}
