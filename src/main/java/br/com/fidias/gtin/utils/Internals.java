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

final class Internals {

    static boolean startsWithNZeroes(String s, int n) {
        if (s == null || n < 0 || s.length() < n) {
            return false;
        }
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) != '0') {
                return false;
            }
        }
        return true;
    }

    static int countLeadingZeroes(String s) {
        if (s == null) {
            return 0;
        }
        int n = 0;
        for (int i = 0; i < s.length() && s.charAt(i) == '0'; i++) {
            n++;
        }
        return n;
    }

    static String leftPadWithZeroes(String s, int n) {
        if (s == null || n < 0 || s.length() >= n) {
            return s;
        }
        char[] chars = new char[n];
        for (int i = 0; i < n - s.length(); i++) {
            chars[i] = '0';
        }
        s.getChars(0, s.length(), chars, n - s.length());
        return new String(chars);
    }

    static boolean isDigits(String s) {
        if (s == null || s.length() < 1) {
            return false;
        }
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch < '0' || ch > '9') {
                return false;
            }
        }
        return true;
    }

    static String validateFormat(String type, int length, String s) {
        if (s == null) {
            throw new NullPointerException(type + " must not be null");
        }
        if (!isDigits(s)) {
            throw new IllegalArgumentException("Invalid " + type + " " + s + ", must be digits");
        }
        if (s.length() != length) {
            throw new IllegalArgumentException("Invalid " + type + " " + s + ", must be " + length + " digits long");
        }
        return s;
    }
}
