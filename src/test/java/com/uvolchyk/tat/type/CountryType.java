package com.uvolchyk.tat.type;

public enum CountryType {
    BELARUS("BLR|BY"),
    BELGIUM("BEL|BE"),
    RUSSIA("RUS|RU"),
    BIRMA("MMR|MM");

    private String tag;

    CountryType(String tag) {
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }
}
