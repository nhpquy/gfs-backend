package com.gfs.domain.constant;

import java.math.BigDecimal;
import java.math.BigInteger;

public class CurrencyConstant {
    public static final BigInteger DEFAULT_SYSTEM_UNIT = new BigInteger("100000000");

    public static final String DEFAULT_SYSTEM_DECIMAL_FORMAT = "#.########";

    public static final BigDecimal USD_SYSTEM_UNIT = new BigDecimal("100");

    public static final String USD_SYSTEM_DECIMAL_FORMAT = "#,###.##";


    public enum CRYPTO_CURRENCY {
        W("W", "Beowulf", "#.#####", 5L, "100000"),
        USD("USD", "USD", "#.##", 0L, "1");

        private final String currencyCode;
        private final String format;
        private final long decimal;
        private final String name;
        private final BigInteger unit;

        CRYPTO_CURRENCY(String currencyCode, String name, String format, long decimal, String unit) {
            this.currencyCode = currencyCode;
            this.name = name;
            this.format = format;
            this.decimal = decimal;
            this.unit = new BigInteger(unit);
        }

        public String getCurrencyCode() {
            return currencyCode;
        }

        public String getName() {
            return name;
        }

        public String getFormat() {
            return format;
        }

        public long getDecimal() {
            return decimal;
        }

        public BigInteger getUnit() {
            return unit;
        }
    }
}
