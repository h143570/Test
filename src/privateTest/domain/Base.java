package privateTest.domain;

public class Base {

    private String userName;
    private String password;

    public Base(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String stealPassword(Base base) {
        return base.password;
    }

    public String stealUserName(Base base) {
        return base.userName;
    }

}
