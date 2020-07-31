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

import org.junit.Test;

import static org.junit.Assert.*;

public class InternalsTests {

    @Test
    public void testStartsWithNZeroes() {
        assertFalse(Internals.startsWithNZeroes(null, -1));
        assertFalse(Internals.startsWithNZeroes(null, 0));
        assertFalse(Internals.startsWithNZeroes(null, 1));

        assertFalse(Internals.startsWithNZeroes("0", -1));
        assertTrue(Internals.startsWithNZeroes("0", 0));
        assertTrue(Internals.startsWithNZeroes("0", 1));
        assertFalse(Internals.startsWithNZeroes("0", 2));

        assertFalse(Internals.startsWithNZeroes("A", -1));
        assertTrue(Internals.startsWithNZeroes("A", 0));
        assertFalse(Internals.startsWithNZeroes("A", 1));
        assertFalse(Internals.startsWithNZeroes("A", 2));

        assertFalse(Internals.startsWithNZeroes("1", -1));
        assertTrue(Internals.startsWithNZeroes("1", 0));
        assertFalse(Internals.startsWithNZeroes("1", 1));
        assertFalse(Internals.startsWithNZeroes("1", 2));

        assertFalse(Internals.startsWithNZeroes("01", -1));
        assertTrue(Internals.startsWithNZeroes("01", 0));
        assertTrue(Internals.startsWithNZeroes("01", 1));
        assertFalse(Internals.startsWithNZeroes("01", 2));

        assertFalse(Internals.startsWithNZeroes("001", -1));
        assertTrue(Internals.startsWithNZeroes("001", 0));
        assertTrue(Internals.startsWithNZeroes("001", 1));
        assertTrue(Internals.startsWithNZeroes("001", 2));
        assertFalse(Internals.startsWithNZeroes("001", 3));

        assertFalse(Internals.startsWithNZeroes("010", -1));
        assertTrue(Internals.startsWithNZeroes("010", 0));
        assertTrue(Internals.startsWithNZeroes("010", 1));
        assertFalse(Internals.startsWithNZeroes("010", 2));

        assertFalse(Internals.startsWithNZeroes("0X0", -1));
        assertTrue(Internals.startsWithNZeroes("0X0", 0));
        assertTrue(Internals.startsWithNZeroes("0X0", 1));
        assertFalse(Internals.startsWithNZeroes("0X0", 2));
    }

    @Test
    public void testCountLeadingZeroes() {
        assertEquals(0, Internals.countLeadingZeroes(null));
        assertEquals(0, Internals.countLeadingZeroes(""));
        assertEquals(0, Internals.countLeadingZeroes("A"));
        assertEquals(1, Internals.countLeadingZeroes("0"));
        assertEquals(1, Internals.countLeadingZeroes("0A"));
        assertEquals(2, Internals.countLeadingZeroes("00"));
        assertEquals(0, Internals.countLeadingZeroes("A0"));
    }

    @Test
    public void testLeftPadWithZeroes() {
        assertNull(Internals.leftPadWithZeroes(null, -1));
        assertNull(Internals.leftPadWithZeroes(null, 0));
        assertNull(Internals.leftPadWithZeroes(null, 1));

        assertEquals("", Internals.leftPadWithZeroes("", -1));
        assertEquals("", Internals.leftPadWithZeroes("", 0));
        assertEquals("0", Internals.leftPadWithZeroes("", 1));
        assertEquals("00", Internals.leftPadWithZeroes("", 2));
        assertEquals("000", Internals.leftPadWithZeroes("", 3));

        assertEquals("ABC", Internals.leftPadWithZeroes("ABC", -1));
        assertEquals("ABC", Internals.leftPadWithZeroes("ABC", 0));
        assertEquals("ABC", Internals.leftPadWithZeroes("ABC", 1));
        assertEquals("ABC", Internals.leftPadWithZeroes("ABC", 2));
        assertEquals("ABC", Internals.leftPadWithZeroes("ABC", 3));
        assertEquals("0ABC", Internals.leftPadWithZeroes("ABC", 4));
        assertEquals("00ABC", Internals.leftPadWithZeroes("ABC", 5));
        assertEquals("000ABC", Internals.leftPadWithZeroes("ABC", 6));
    }

    @Test
    public void testIsDigits() {
        assertFalse(Internals.isDigits(null));
        assertFalse(Internals.isDigits(""));
        assertFalse(Internals.isDigits(" "));
        assertFalse(Internals.isDigits("A"));
        assertTrue(Internals.isDigits("1"));
        assertTrue(Internals.isDigits("123"));
        assertFalse(Internals.isDigits("123A"));
        assertFalse(Internals.isDigits("A123"));
    }

    @Test
    public void testValidateFormat() {

    }
}
