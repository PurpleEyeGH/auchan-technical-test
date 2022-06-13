package technical.test.api.data;

public enum Gender {

    MALE("Male"), FEMALE("Female");

    public final String genderName;

    Gender(String gender) {
        this.genderName = gender;
    }

}
