package ru.kinopoisk.pages.profile;

import ru.kinopoisk.models.User;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 *class contains data for tests
 */

public class Data {

    private static final int MAX_SYMB_NUMBER = 300;

    private User user = new User("Z3axap@mail.ru", "123poi456");

    private List<String> textFields = Arrays.asList("name", "Lastname", "about myself", "my interests", "city");
    private String vkText = "http://vk.com/durovec";
    private String fbText = "http://facebook.com/zuckovec";
    private String twitText = "http://twitter.com/evgev";
    private String icqText = "213567";
    private String skypeText = "asdjkh34";
    private String day = "5";
    private String month = "Март";
    private String year = "1996";
    private String sex = "женский";
    private String country = "Украина";


    public static String getTooBigString(){
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < MAX_SYMB_NUMBER+1; i++) {
            result.append("a");
        }
        return result.toString();
    }

    public static String getRndMovieName(){
        Random rnd = new Random();
        char rndChar1 = (char) (rnd.nextInt(26) + 'a');
        char rndChar2 = (char) (rnd.nextInt(26) + 'a');
        StringBuilder sb = new StringBuilder();
        sb.append(rndChar1);
        sb.append(rndChar2);
        return sb.toString();
    }

//----getters-----

    public User getUser() {
        return user;
    }

    public List<String> getTextFields() {
        return textFields;
    }

    public String getVkText() {
        return vkText;
    }

    public String getFbText() {
        return fbText;
    }

    public String getTwitText() {
        return twitText;
    }

    public String getIcqText() {
        return icqText;
    }

    public String getSkypeText() {
        return skypeText;
    }

    public String getDay() {
        return day;
    }

    public String getMonth() {
        return month;
    }

    public String getYear() {
        return year;
    }

    public String getSex() {
        return sex;
    }

    public String getCountry() {
        return country;
    }
}
