package org.gesart.gesart.config;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

@SuppressWarnings("ALL")
@Slf4j
public class Utils {
    /**
     * Return null if bigDecimalZero.
     *
     * @param bigDecimal
     * @return BigDecimal
     */
    public static BigDecimal zeroIfNull(final BigDecimal bigDecimal) {
        return bigDecimal == null ? BigDecimal.ZERO : bigDecimal;
    }
}
