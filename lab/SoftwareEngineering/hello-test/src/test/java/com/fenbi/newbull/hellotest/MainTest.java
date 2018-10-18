package com.fenbi.newbull.hellotest;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.is;

/**
 * @author fankai
 */
@RunWith(MyRunner.class)
public class MainTest {

    @Test
    public void test() {
        Assert.assertThat(true, is(true));
    }
}
