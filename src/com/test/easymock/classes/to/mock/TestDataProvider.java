package com.test.easymock.classes.to.mock;

import java.util.ArrayList;
import java.util.List;

public class TestDataProvider {

    public List<Long> getLongList() {
        List<Long> result = new ArrayList<Long>();
        result.add(1l);
        result.add(2l);
        result.add(3l);

        return result;

    }

}
