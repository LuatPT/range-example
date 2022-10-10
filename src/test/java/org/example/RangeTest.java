package org.example;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.time.chrono.ChronoLocalDate;

/**
 * Unit test for Range.
 */
public class RangeTest {
    /**
     * Common Test
     */
    @Test
    public void testSuite1() {
        Range<Integer> open = Range.open(5, 7);
        Assert.assertEquals(open.contains(5), false);
        Assert.assertEquals(open.contains(10), false);

        Range<Integer> closed = Range.closed(5, 7);
        Assert.assertEquals(closed.contains(5), true);

        Range<Integer> openClosed = Range.openClosed(5, 7);
        Assert.assertEquals(openClosed.contains(5), false);
        Assert.assertEquals(openClosed.contains(7), true);

        Range<Integer> closedOpen = Range.closedOpen(5, 7);
        Assert.assertEquals(closedOpen.contains(5), true);
        Assert.assertEquals(closedOpen.contains(7), false);
    }

    /**
     * Special case
     */
    @Test
    public void testSuite2() {
        //Test with text
        Range<String> text = Range.open("abc", "xyz");

        Assert.assertEquals(text.contains("aaa"), false);
        Assert.assertEquals(text.contains("abe"), true);
        Assert.assertEquals(text.contains("aya"), true);
        Assert.assertEquals(text.contains("aba"), false);
        Assert.assertEquals(text.contains("xyz"), false);

        //Test with BigDecimal
        Range<BigDecimal> decimals = Range.open(new BigDecimal("1.123"), new BigDecimal("1.23456789"));
        Assert.assertEquals(decimals.contains(new BigDecimal("1.234567")), true);
        Assert.assertEquals(decimals.contains(new BigDecimal("1.33456789")), false);

        //Test with date
        Range<ChronoLocalDate> dates = Range.closed(LocalDate.of(2022, Month.SEPTEMBER, 1), LocalDate.of(2022, Month.SEPTEMBER, 30));

        Assert.assertEquals(dates.contains(LocalDate.of(2022, Month.SEPTEMBER, 15)), true);
        Assert.assertEquals(dates.contains(LocalDate.of(2022, Month.NOVEMBER, 15)), false);
    }
}
