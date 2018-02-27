package ru.kinopoisk.enums;

public enum PageTransitions {
    TODAY_AT_THE_CINEMA("Афиша", "Сегодня в кино", "https://www.kinopoisk.ru/afisha/new/"),
    CINEMAS("Афиша", "Кинотеатры", "https://www.kinopoisk.ru/cinemas/"),
    PREMIER_SCHEDULE("Афиша", "График премьер", "https://www.kinopoisk.ru/premiere/"),

    NEWS("Журнал", "Новости", "https://www.kinopoisk.ru/news/"),
    INTERVIEW("Журнал", "Интервью", "https://www.kinopoisk.ru/interview/"),
    ARTICLE("Журнал", "Статьи", "https://www.kinopoisk.ru/article/"),
    VIDEOS_AND_PHOTOS("Журнал", "Видео и фото", "https://www.kinopoisk.ru/photostory/"),
    PODCASTS("Журнал", "Подкасты", "https://www.kinopoisk.ru/photostory/keyword/%ef%ee%e4%ea%e0%f1%f2%fb/"),

    FILMS_NAVIGATOR("Фильмы", "Навигатор по фильмам", "https://www.kinopoisk.ru/top/navigator/"),
    RECOMMENDATIONS("Фильмы", "Рекомендации", "https://www.kinopoisk.ru/recommend/"),
    DVD_AND_BLU_RAY("Фильмы", "DVD и Blu-ray", "https://www.kinopoisk.ru/dvd/"),
    BOX_OFFICES_GATHERING("Фильмы", "Кассовые сборы", "https://www.kinopoisk.ru/box/"),
    TRAILERS("Фильмы", "Трейлеры", "https://www.kinopoisk.ru/video/"),
    SOUNDTRACKS("Фильмы", "Саундтреки", "https://www.kinopoisk.ru/tracks/"),
    POSTERS_AND_FRAMES("Фильмы", "Постеры и кадры", "https://www.kinopoisk.ru/posters/"),

    EXPECTED_FILMS("Рейтинги", "Ожидаемые фильмы", "https://www.kinopoisk.ru/comingsoon/"),
    TOP_250("Рейтинги", "Топ 250", "https://www.kinopoisk.ru/top/"),
    FILMS_LISTS("Рейтинги", "Списки фильмов", "https://www.kinopoisk.ru/top/lists/"),
    POPULAR_FILMS("Рейтинги", "Популярные фильмы", "https://www.kinopoisk.ru/popular/"),
    CASH_RECORDS("Рейтинги", "Кассовые рекорды", "https://www.kinopoisk.ru/box/best_usa/"),
    AWARDS_AND_PRIZES("Рейтинги", "Награды и премии", "https://www.kinopoisk.ru/awards/"),
    USER_RATINGS("Рейтинги", "Оценки пользователей", "https://www.kinopoisk.ru/votes/");

    private String parentTabRusName;
    private String linkRusName;
    private String link;

    PageTransitions(String parentTabRusName, String linkRusName, String link) {
        this.parentTabRusName = parentTabRusName;
        this.linkRusName = linkRusName;
        this.link = link;
    }

    public String getParentTabRusName() {
        return parentTabRusName;
    }

    public String getLinkRusName() {
        return linkRusName;
    }

    public String getLink() {
        return link;
    }

    @Override
    public String toString() {
        return "Link: on tab - [" + parentTabRusName + "], name - [" + linkRusName + "], with link - [" + link + "].";
    }
}