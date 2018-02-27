package ru.kinopoisk.beans.main;

public class BoxOfficeBean {
    private String textInHeader;
    private String currencyType;
    private String href;

    public BoxOfficeBean(String textInHeader, String currencyType, String href) {
        this.textInHeader = textInHeader;
        this.currencyType = currencyType;
        this.href = href;
    }

    public String getTextInHeader() {
        return textInHeader;
    }

    public String getCurrencyType() {
        return currencyType;
    }

    public String getHref() {
        return href;
    }

    @Override
    public String toString() {
        return "Box-office: header - [" + textInHeader + "], currency - [" + currencyType + "], href - [" + href + "].";
    }
}
