package com.fenbi.newbull.hellotest;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

/**
 * @author fankai
 */
@RunWith(Parameterized.class)
public class ParameterizedTest {

    @Parameterized.Parameter(0)
    public int a;

    @Parameterized.Parameter(1)
    public int b;

    @Parameterized.Parameter(2)
    public int result;

    // creates the test data
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][] {
            {1, 2, 3},
            {0x7FFFFFFA, 5, 0x7FFFFFFF},
            {2, -2, 0},
            {0, 2, 2},
            {2, 0, 2}
        };
        return Arrays.asList(data);
    }

    @Test
    public void testAdd() {
        Assert.assertTrue(result == a + b);
    }
}