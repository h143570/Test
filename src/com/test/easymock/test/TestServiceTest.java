package com.test.easymock.test;

import static org.easymock.EasyMock.*;

import java.util.Map;

import org.easymock.Capture;
import org.easymock.IMocksControl;
import org.testng.annotations.Test;

import com.test.easymock.classes.to.mock.TestAdditionalDataProvider;

@Test
public class TestServiceTest {

    public void test() {
        IMocksControl control = createControl();

        //        TestDataProvider mockedTestDataProvider = control.createMock(TestDataProvider.class);


        Capture<Map<Long, Long>> capture = new Capture<Map<Long,Long>>();
//        capture.

        TestAdditionalDataProvider mockedAdditionalTestDataProvider = control.createMock(TestAdditionalDataProvider.class);
        expect(mockedAdditionalTestDataProvider.getLongList(capture(capture)));

    }

}
