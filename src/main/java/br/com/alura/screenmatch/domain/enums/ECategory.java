package br.com.alura.screenmatch.domain.enums;

import lombok.Getter;

public enum ECategory {
    ACTION("Action", "Ação"),
    ROMANCE("Romance", "Romance"),
    COMEDY("Comedy", "Comédia"),
    CRIME("Crime", "Crime"),
    DRAMA("Drama", "Drama");

    @Getter
    private String categoryOMDb;
    private String categoryPortuguese;

    ECategory(String categoryOMDb, String categoryPortuguese) {
        this.categoryOMDb = categoryOMDb;
        this.categoryPortuguese = categoryPortuguese;
    }

    public static ECategory fromString(String text) {
        for (ECategory categoria : ECategory.values()) {
            if (categoria.categoryOMDb.equalsIgnoreCase(text)) {
                return categoria;
            }
        }
        throw new IllegalArgumentException("No category found for the given string: " + text);
    }

    public static ECategory fromPortuguese(String text) {
        for (ECategory categoria : ECategory.values()) {
            if (categoria.categoryPortuguese    .equalsIgnoreCase(text)) {
                return categoria;
            }
        }
        throw new IllegalArgumentException("No category found for the given string: " + text);
    }
}
