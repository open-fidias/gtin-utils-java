/**
 * gtin-utils-java: Utilities for GTIN (Global Trade Item Number) barcodes
 * Based on the <https://github.com/tmattsson/gs1utils>
 *
 * Copyright (C) 2020 Átila Camurça <camurca.home@gmail.com>
 * Fidias Free and Open Source Team <fidiascom@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */
package br.com.fidias.gtin.utils;

/**
 * Utility methods for calculating and validating check digits as used by GS1 in
 * data structures such as GTIN, GLN and SSCC.
 *
 * @link https://en.wikipedia.org/wiki/International_Article_Number#Calculation_of_checksum_digits
 */
public final class CheckDigit {

    /**
     * Calculates the check digit on a sequence of digits.
     *
     * @param s
     * @return
     * @throws NullPointerException if the input string is null
     * @throws IllegalArgumentException if the input string is not a sequence of
     * at least one digit
     */
    public static char calculate(String s) {
        if (s == null) {
            throw new NullPointerException();
        }
        if (!Internals.isDigits(s)) {
            throw new IllegalArgumentException("Invalid sequence, must be digits");
        }
        return checksum(s);
    }

    /**
     * Calculates the check digit on a sequence of digits and returns it
     * appended to the end of the sequence.
     *
     * @param s
     * @return
     * @throws NullPointerException if the input string is null
     * @throws IllegalArgumentException if the input string is not a sequence of
     * at least one digit
     */
    public static String calculateAndAppend(String s) {
        char checkDigit = calculate(s);
        return s + checkDigit;
    }

    /**
     * Calculates the check digit on a sequence of digits ignoring the last
     * digit which is assumed to already be a check digit.
     *
     * @param s
     * @return
     * @throws NullPointerException if the input string is null
     * @throws IllegalArgumentException if the input string is not a sequence of
     * at least two digits
     */
    public static char recalculate(String s) {
        if (s == null) {
            throw new NullPointerException();
        }
        if (!Internals.isDigits(s)) {
            throw new IllegalArgumentException("Invalid sequence, must be digits");
        }
        if (s.length() < 2) {
            throw new IllegalArgumentException("Invalid sequence, must be at least 2 digits");
        }
        return checksum(s.substring(0, s.length() - 1));
    }

    /**
     * Calculates the check digit on a sequence of digits ignoring the last
     * digit which is assumed to already be a check digit and returns the
     * sequence with the check digit replaced.
     *
     * @param s
     * @return
     * @throws NullPointerException if the input string is null
     * @throws IllegalArgumentException if the input string is not a sequence of
     * at least two digits
     */
    public static String recalculateAndApply(String s) {
        char checkDigit = recalculate(s);
        return s.substring(0, s.length() - 1) + checkDigit;
    }

    /**
     * Determines if the check digit in a digit sequence is correct.Returns null
     * if the input string is null or not a sequence of at least two digits.
     *
     * @param s
     * @return
     */
    public static boolean isValid(String s) {
        if (s == null || !Internals.isDigits(s) || s.length() < 2) {
            return false;
        }
        char calculated = checksum(s.substring(0, s.length() - 1));
        char actual = s.charAt(s.length() - 1);
        return actual == calculated;
    }

    /**
     * Checks if the check digit in a digit sequence is correct.
     *
     * @param s
     * @return
     * @throws IllegalArgumentException if the input string is null, not a
     * sequence of at least two digits or if the check digit is not correct
     */
    public static String validate(String s) {
        if (!isValid(s)) {
            throw new IllegalArgumentException("Check digit is not correct");
        }
        return s;
    }

    /**
     * Calculates a check digit for a sequence of digits where digits in odd
     * positions have weight 3 and even positions have a weight of 1.
     *
     * @param s
     * @return
     */
    private static char checksum(String s) {
        int sum = 0;
        for (int i = 0, position = s.length(); i < s.length(); i++, position--) {
            int n = s.charAt(i) - '0';
            sum += n + (n + n) * (position & 1);
        }
        return (char) ('0' + ((10 - (sum % 10)) % 10));
    }
}
