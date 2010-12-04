package privateTest;

import privateTest.domain.Base;
import privateTest.domain.BaseDerived;

public class runner {


    public static void main(String[] args) {
       Base base = new Base("base", "basePassword");
       BaseDerived baseDerived = new BaseDerived("baseDerived", "baseDerivedPassword");

       System.out.println(base.stealUserName(base));
       System.out.println(base.stealPassword(base));
       System.out.println("------");
       System.out.println(base.stealUserName(baseDerived));
       System.out.println(base.stealPassword(baseDerived));
       System.out.println("------");
       System.out.println(baseDerived.stealUserName(base));
       System.out.println(baseDerived.stealPassword(base));
       System.out.println("------");
       System.out.println(baseDerived.stealUserName(baseDerived));
       System.out.println(baseDerived.stealPassword(baseDerived));
       System.out.println("------");
    }

}
