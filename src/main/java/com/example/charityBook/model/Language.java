package com.example.charityBook.model;

public enum Language {

    EN("English"),
    UA("Ukrainian");

    public final String name;

    Language(String name) {
        this.name = name;
    }

    public static Language valueOfLabel(String label) {
        for (Language e : values()) {
            if (e.name.equals(label)) {
                return e;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }
}
