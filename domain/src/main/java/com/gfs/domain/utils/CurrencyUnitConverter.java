package com.gfs.domain.utils;

import com.gfs.domain.constant.CurrencyConstant;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.text.DecimalFormat;

public class CurrencyUnitConverter {

    public static String formatBalanceValue(long balanceValue, String format) {
        DecimalFormat decimalFormat = new DecimalFormat(format);
        double balanceFormatted = new BigDecimal(balanceValue)
                .divide(new BigDecimal(CurrencyConstant.DEFAULT_SYSTEM_UNIT)).doubleValue();
        return decimalFormat.format(balanceFormatted);
    }

    public static String formatBalanceValue(long balanceValue, String format, BigInteger unit) {
        DecimalFormat decimalFormat = new DecimalFormat(format);
        double balanceFormatted = new BigDecimal(balanceValue)
                .divide(new BigDecimal(unit)).doubleValue();
        return decimalFormat.format(balanceFormatted);
    }

    public static long getLongValueFromHexString(String hexValue) {
        if (hexValue.startsWith("0x")) {
            hexValue = hexValue.substring(2);
        }
        if (StringUtils.hasText(hexValue)) {
            long amount = new BigInteger(hexValue, 16).longValueExact();
            return amount;
        }
        return -1;
    }

    public static long convertAmountToSystemUnit(double amount) {
        return convertStringAmountToSystemUnit(String.valueOf(amount));
    }

    public static long convertStringAmountToSystemUnit(String amount) {
        return new BigDecimal(amount, MathContext.DECIMAL128)
                .multiply(new BigDecimal(CurrencyConstant.DEFAULT_SYSTEM_UNIT)).toBigInteger().longValueExact();
    }

    public static long convertStringAmountToUnit(String amount, BigInteger unit) {
        return new BigDecimal(amount, MathContext.DECIMAL128)
                .multiply(new BigDecimal(unit)).toBigInteger().longValueExact();
    }

    public static double parseAmountFromString(String amount) {
        BigInteger amountBigInt = new BigDecimal(amount, MathContext.DECIMAL128)
                .multiply(new BigDecimal(CurrencyConstant.DEFAULT_SYSTEM_UNIT)).toBigInteger();
        return new BigDecimal(amountBigInt).divide(new BigDecimal(CurrencyConstant.DEFAULT_SYSTEM_UNIT)).doubleValue();
    }

    public static BigInteger convertAmountHexToBigInteger(String amount) {
        return new BigInteger(amount.startsWith("0x") ? amount.substring(2) : amount, 16);
    }

    public static long convertFromUnitToUnit(long amount, BigInteger fromUnit, BigInteger toUnit) {
        BigDecimal amountBigDecimal = new BigDecimal(amount, MathContext.DECIMAL128)
                .divide(new BigDecimal(fromUnit));
        amountBigDecimal = amountBigDecimal.multiply(new BigDecimal(toUnit));
        return amountBigDecimal.toBigInteger().longValueExact();
    }
}
