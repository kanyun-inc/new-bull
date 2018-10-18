package com.fenbi.newbull.hellotest;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DecimalMatcher extends BaseMatcher {

    private BigDecimal expect;

    private final int DEFAULT_SCALE = 2;

    public DecimalMatcher(BigDecimal expect) {
        this.expect = expect.setScale(DEFAULT_SCALE, RoundingMode.HALF_UP);
    }

    @Override
    public boolean matches(Object o) {
        BigDecimal actual = new BigDecimal(o.toString()).setScale(DEFAULT_SCALE, RoundingMode.HALF_UP);
        return expect.equals(actual);
    }

    @Override
    public void describeTo(Description description) {
        description.appendText(expect.toString());
    }


    public static DecimalMatcher equalsToNumber(BigDecimal expect) {
        return new DecimalMatcher(expect);
    }

    public static DecimalMatcher equalsToNumber(double expect) {
        return new DecimalMatcher(BigDecimal.valueOf(expect));
    }

    public static DecimalMatcher equalsToNumber(String expect) {
        return new DecimalMatcher(new BigDecimal(expect));
    }
}
