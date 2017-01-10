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
 * 
 * @author kinegratii
 * 
 */
public class Token {

	public final static int INTEGER = 0;
	public final static int DOUBLE = 1;
	public final static int RESERVED = 2;
	public final static int IDENTIFITER = 3;
	public final static int ERROR = 4;
	public final static int OPERATOR = 5;
	public final static int DOT = 6;

	public final static String[] TOKEN_DESCRIPTION = { "整型数字", "浮点型数字", "关键字",
			"标识符", "无效字符", "操作符", "字符" };
	/**
	 * 原始字符串
	 */
	protected String raw;
	/**
	 * 类型
	 */
	protected int type;

	public Token() {
	}

	public Token(String raw, int mType) {
		this.raw = raw;
		this.type = mType;
	}

	public String getRaw() {
		return raw;
	}

	public int getType() {
		return type;
	}

	public String getTypeDisplay() {
		return TOKEN_DESCRIPTION[type];
	}

	public String toString() {
		return String.format("<%s>", raw);
	}

	public String fullString() {
		return String.format("%s%s", TOKEN_DESCRIPTION[type], this.toString());
	}
}