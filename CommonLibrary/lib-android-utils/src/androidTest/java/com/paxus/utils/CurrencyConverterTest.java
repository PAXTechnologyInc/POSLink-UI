package com.paxus.utils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by Kim.L 2020/01/28
 */
public class CurrencyConverterTest {

    @Test
    public void testCurrencyConvert() {

        long amount = 1234567;

        for (CurrencyCode i : CurrencyCode.values()) {
            String str = CurrencyConverter.convert(amount, "", i.getCurrencyName());
            System.out.println(i.getCurrencyName() + ":" + str);
            assertNotEquals("", str);
            long ret = CurrencyConverter.parse(str, i.getCurrencyName());
            assertEquals(i.getCurrencyName(), amount, ret);
        }
    }
}