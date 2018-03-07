package com.yoti.rh.util;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static com.yoti.rh.util.CoordinateUtils.isInBounds;

@RunWith(MockitoJUnitRunner.class)
public class CoordinateUtilsUTest {

    @Test
    public void shouldAcceptCoordinateInBounds() {
        Long[] lower = new Long[]{0L, 0L};
        Long[] upper = new Long[]{3L, 3L};
        Long[] point = new Long[]{2L, 2L};
        Assert.assertTrue(isInBounds(lower, upper, point));
    }

    @Test
    public void shouldAcceptCoordinateOnSwBound() {
        Long[] lower = new Long[]{0L, 0L};
        Long[] upper = new Long[]{3L, 3L};
        Long[] point = new Long[]{0L, 0L};
        Assert.assertTrue(isInBounds(lower, upper, point));
    }

    @Test
    public void shouldAcceptCoordinateOnNwBound() {
        Long[] lower = new Long[]{0L, 0L};
        Long[] upper = new Long[]{3L, 3L};
        Long[] point = new Long[]{0L, 3L};
        Assert.assertTrue(isInBounds(lower, upper, point));
    }

    @Test
    public void shouldAcceptCoordinateOnNeBound() {
        Long[] lower = new Long[]{0L, 0L};
        Long[] upper = new Long[]{3L, 3L};
        Long[] point = new Long[]{3L, 3L};
        Assert.assertTrue(isInBounds(lower, upper, point));
    }

    @Test
    public void shouldAcceptCoordinateOnSeBound() {
        Long[] lower = new Long[]{0L, 0L};
        Long[] upper = new Long[]{3L, 3L};
        Long[] point = new Long[]{3L, 0L};
        Assert.assertTrue(isInBounds(lower, upper, point));
    }

    @Test
    public void shouldRejectCoordinateBeyondSwBound() {
        Long[] lower = new Long[]{0L, 0L};
        Long[] upper = new Long[]{3L, 3L};
        Long[] point = new Long[]{-1L, -1L};
        Assert.assertFalse(isInBounds(lower, upper, point));
    }

    @Test
    public void shouldRejectCoordinateBeyondNwBound() {
        Long[] lower = new Long[]{0L, 0L};
        Long[] upper = new Long[]{3L, 3L};
        Long[] point = new Long[]{-1L, 4L};
        Assert.assertFalse(isInBounds(lower, upper, point));
    }

    @Test
    public void shouldRejectCoordinateBeyondNeBound() {
        Long[] lower = new Long[]{0L, 0L};
        Long[] upper = new Long[]{3L, 3L};
        Long[] point = new Long[]{4L, 4L};
        Assert.assertFalse(isInBounds(lower, upper, point));
    }

    @Test
    public void shouldRejectCoordinateBeyondSeBound() {
        Long[] lower = new Long[]{0L, 0L};
        Long[] upper = new Long[]{3L, 3L};
        Long[] point = new Long[]{4L, -1L};
        Assert.assertFalse(isInBounds(lower, upper, point));
    }

}
