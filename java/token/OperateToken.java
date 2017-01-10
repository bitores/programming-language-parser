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

import java.util.HashMap;

/**
 * 操作符
 * 
 * @author kinegratii
 **/
public class OperateToken extends Token {

	private static HashMap<String, String> TOKEN_NAME_MAP;

	public static void load() {
		TOKEN_NAME_MAP = new HashMap<>();
		TOKEN_NAME_MAP.put("&&", "与关系运算符");
		TOKEN_NAME_MAP.put("&", "与逻辑运算符");
		TOKEN_NAME_MAP.put("||", "或关系运算符");
		TOKEN_NAME_MAP.put("|", "或逻辑运算符");
		TOKEN_NAME_MAP.put("!=", "非关系运算符");
		TOKEN_NAME_MAP.put("!", "非逻辑运算符");

		TOKEN_NAME_MAP.put("<=", "小于等于关系运算符");
		TOKEN_NAME_MAP.put("<", "小于关系运算符");
		TOKEN_NAME_MAP.put(">=", "大于等于关系运算符");
		TOKEN_NAME_MAP.put(">", "大于关系运算符");
		TOKEN_NAME_MAP.put("==", "等于关系运算符");
		TOKEN_NAME_MAP.put("=", "赋值运算符");
		TOKEN_NAME_MAP.put("+=", "复加运算符");
		TOKEN_NAME_MAP.put("+", "加运算符");
		TOKEN_NAME_MAP.put("-=", "复减运算符");
		TOKEN_NAME_MAP.put("-", "减运算符");
		TOKEN_NAME_MAP.put("*=", "复乘运算符");
		TOKEN_NAME_MAP.put("*", "乘运算符");
		TOKEN_NAME_MAP.put("/=", "复除运算符");
		TOKEN_NAME_MAP.put("/", "除运算符");
	}

	public OperateToken(String raw) {
		super(raw, Token.OPERATOR);
	}

	public String getOpDisplay() {
		return TOKEN_NAME_MAP.get(raw);
	}

	public String toString() {
		return String.format("<%s>", raw);
	}

	public String fullString() {
		return String.format("%s%s", TOKEN_NAME_MAP.get(raw), this.toString());
	}

	public static boolean isMatchPrefix(char c) {
		String s = "&|=!<>+-*/";
		return s.indexOf(c) != -1;

	}
}
