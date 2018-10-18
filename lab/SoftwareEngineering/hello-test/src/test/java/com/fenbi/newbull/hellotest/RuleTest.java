package com.fenbi.newbull.hellotest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;
import org.junit.rules.TestName;

import static java.math.BigDecimal.ONE;
import static java.util.Arrays.asList;
import static com.fenbi.newbull.hellotest.DecimalMatcher.equalsToNumber;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

/**
 * @author fankai
 */
public class RuleTest {

    @Rule
    public TestName name= new TestName();

    @Rule
    public ErrorCollector collector = new ErrorCollector();

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testWithEx() {
        exception.expect(RuntimeException.class);
        exception.expectMessage("expected");
        exception.expectCause(isA(IllegalArgumentException.class));
        throw new RuntimeException("expected", new IllegalArgumentException());
    }

    @Test
    public void testWithAssert() {
        assertThat(name.getMethodName(), is("test"));
        assertThat("add",20 + 3, is(22));
    }

    @Test
    public void testWithCollector() {
        collector.checkThat(name.getMethodName(), is("testWithCollector"));
        collector.checkThat("add",20 + 3, is(22));
        collector.checkThat("minus",20 - 3, is(18));
    }

    @Test
    public void testBigDecimal() {
        assertThat(1, equalsToNumber(ONE));
        assertThat(1, equalsToNumber("1"));
        assertThat("1", equalsToNumber(ONE));
        assertThat(ONE, equalsToNumber(1));
        assertThat(1, allOf(equalsToNumber(ONE), is(3-2)));
        assertThat(asList(1, 2, 3), hasItem(equalsToNumber(ONE)));
    }

}