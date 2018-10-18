package com.fenbi.newbull.hellojetty;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;

/**
 * @author fankai
 */
public class MainTest {

    @Test
    public void test() {
        Assert.assertThat(true, is(true));
    }
}
