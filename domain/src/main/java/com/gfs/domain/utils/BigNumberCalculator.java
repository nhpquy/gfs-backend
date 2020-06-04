package com.gfs.domain.utils;

import com.gfs.domain.constant.CurrencyConstant;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public class BigNumberCalculator {

    public static long add(long val1, long val2) {
        return new BigInteger(String.valueOf(val1)).add(new BigInteger(String.valueOf(val2))).longValueExact();
    }

    public static long adds(long... vals) {
        BigInteger result = new BigInteger("0");
        for (long val : vals) {
            result = result.add(new BigInteger(String.valueOf(val)));
        }
        return result.longValueExact();
    }

    public static long subtract(long val1, long val2) {
        return new BigInteger(String.valueOf(val1)).subtract(new BigInteger(String.valueOf(val2))).longValueExact();
    }

    public static long multiply(long val1, long val2) {
        return new BigInteger(String.valueOf(val1)).multiply(new BigInteger(String.valueOf(val2))).longValueExact();
    }

    public static long multiplySatoshi(long val1, long val2) {
        return new BigInteger(String.valueOf(val1)).multiply(new BigInteger(String.valueOf(val2))).divide(CurrencyConstant.DEFAULT_SYSTEM_UNIT).longValueExact();
    }

    public static long divide(long val1, long val2) {
        return new BigInteger(String.valueOf(val1)).divide(new BigInteger(String.valueOf(val2))).longValueExact();
    }

    public static long divideSatoshi(long val1, long val2, int scale) {
        return new BigDecimal(String.valueOf(val1))
                .divide(new BigDecimal(String.valueOf(val2)), scale, RoundingMode.FLOOR)
                .multiply(new BigDecimal(CurrencyConstant.DEFAULT_SYSTEM_UNIT)).longValueExact();
    }

}
