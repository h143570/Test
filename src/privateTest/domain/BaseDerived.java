package privateTest.domain;

public class BaseDerived extends Base {

    public BaseDerived(String userName, String password) {
        super(userName, password);
    }

    //    will not compile
    //    @Override
    //    public String stealPassword(Base base) {
    //        return base.password;
    //    }
    //
    //    @Override
    //    public String stealUserName(Base base) {
    //        return base.userName;
    //    }

}
