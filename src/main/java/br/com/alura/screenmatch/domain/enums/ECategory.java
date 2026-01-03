package br.com.alura.screenmatch.domain.enums;

public enum ECategory {
    ACTION("Action"),
    ROMANCE("Romance"),
    COMEDY("Comedy"),
    CRIME("Crime"),
    DRAMA("Drama");

    private String categoryOMDb;

    public String getCategoryOMDb() {
        return categoryOMDb;
    }

    ECategory(String categoryOMDb) {
        this.categoryOMDb = categoryOMDb;
    }

    public static ECategory fromString(String text) {
        for (ECategory categoria : ECategory.values()) {
            if (categoria.categoryOMDb.equalsIgnoreCase(text)) {
                return categoria;
            }
        }
        throw new IllegalArgumentException("No category found for the given string: " + text);
    }
}
