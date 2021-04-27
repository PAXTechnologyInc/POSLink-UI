package com.pax.us.pay.ui.message;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by Kim.L 2020/01/28
 */
public class CurrencyConverterTest {

    @Test
    public void testCurrencyConvert(){

        long amount = 1234567;

        for(CurrencyCode i : CurrencyCode.values()){
            CurrencyConverter.setDefCurrency(i.getCurrencyName());
            String str = CurrencyConverter.convert(amount);
            System.out.println(i.getCurrencyName() + ":" + str);
            assertNotEquals("", str);

            long value = CurrencyConverter.parse(str);
            System.out.println(i.getCurrencyName() + "[long]:" + value);
            assertEquals(amount, value);
        }
    }

    @Test
    public void testCurrencyEuro(){

        long amount = 1234567;

        CurrencyConverter.setDefCurrency(CurrencyCode.EU.getCurrencyName());
        String str = CurrencyConverter.convert(amount);
        System.out.println(CurrencyCode.EU.getCurrencyName() + ":" + str);
        assertNotEquals("", str);

        long value = CurrencyConverter.parse(str);
        System.out.println(CurrencyCode.EU.getCurrencyName() + "[long]:" + value);
        assertEquals(amount, value);
    }
}