package ru.kinopoisk.utils.search.data;

import ru.kinopoisk.utils.search.enums.Gender;

public class PersonForSearch {

    private String name = "Эмилия Кларк";
    private String yearOfBirth = "1986";
    private String placeOfBirth = "Великобритания";
    private Gender gender = Gender.FEMALE;
    private String personURL = "/name/1830611";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(String yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public String getGender() {
        return gender.toString();
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getPersonURL() {
        return personURL;
    }

    public void setPersonURL(String personURL) {
        this.personURL = personURL;
    }

    @Override
    public String toString() {
        return "PersonForSearch{" +
                "name='" + name + '\'' +
                ", yearOfBirth='" + yearOfBirth + '\'' +
                ", placeOfBirth='" + placeOfBirth + '\'' +
                ", gender=" + gender +
                ", personURL='" + personURL + '\'' +
                '}';
    }
}
