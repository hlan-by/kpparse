package ru.kinopoisk.models;

public class FilmReview {
    public enum reviewTypes {GOOD, BAD, NEUTRAL};
    private String title;
    private String reviewText;
    private String type;

    public FilmReview() {
        this.title = Long.toHexString(Double.doubleToLongBits(Math.random()));
        this.reviewText = Long.toHexString(Double.doubleToLongBits(Math.random()));
        this.type = "good";
    }

    public FilmReview(String title, String reviewText, String type) {
        this.title = title;
        this.reviewText = reviewText;
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public String getReviewText() {
        return reviewText;
    }

    public String getType() {
        return type;
    }
}
