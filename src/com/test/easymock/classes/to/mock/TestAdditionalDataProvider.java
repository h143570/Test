package com.test.easymock.classes.to.mock;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class TestAdditionalDataProvider {

    public List<Long> getLongList(Map<Long, Long> map) {
        List<Long> result = new ArrayList<Long>();
        for (Entry<Long, Long> element : map.entrySet()) {
            result.add(element.getKey() * element.getValue());
        }

        return result;
    }
}
