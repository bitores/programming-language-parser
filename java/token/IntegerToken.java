/*
*
* Copyright (C) 2014  kinegratii(kinegratii@gmail.com)
*
* This program is free software; you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation; either version 2 of the License, or
* (at your option) any later version.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License along
* with this program; if not, write to the Free Software Foundation, Inc.,
* 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
*/
package com.kinegratii.lexer.token;

/**
 * 整型
 * 
 * @author kinegratii
 * 
 */
public class IntegerToken extends Token {
	public static final String SYMBOL = "num";
	private int intValue;
	private int radio;

	public IntegerToken(int x, boolean isZeroLead) {
		super(Integer.toString(x), Token.INTEGER);
		String ms = this.raw;
		if (isZeroLead == true && ms.indexOf('8') == -1
				&& ms.indexOf('9') == -1) {
			int xx, i, l;
			l = ms.length();
			intValue = 0;
			for (i = l - 1; i >= 0; i--) {
				xx = ms.charAt(i) - '0';
				intValue += xx * Math.pow(8, l - i - 1);
			}
			radio = 8;
		} else {
			intValue = x;
			radio = 10;
		}
	}

	public int getIntValue() {
		return intValue;
	}

	public int getRadio() {
		return radio;
	}

	public String toString() {
		return String.format("<%s,%d>", SYMBOL, intValue);
	}
}