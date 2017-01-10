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
package com.kinegratii.lexer;

/**
 * 常量定义
 * 
 * @author kinegratii
 * 
 */
public class SoftwareInfo {
	private SoftwareInfo() {

	}

	public static final String NAME = "C语言词法分析器";
	public static final String VERSION = "v1.3.1";
	public static final String FULL_NAME = NAME + VERSION;
	public static final String SHORT_DESCRIPTION = "一个由Java语言编写的C语言词法分析器";
	public static final String AUTHOR = "Kinegratii";
	public static final String PROJECT_HOME = "http://git.oschina.net/kinegratii/Lexer";
	public static final String LICENSE = "GPL授权协议";
	public static final String[] ABOUT_INFO = { FULL_NAME, SHORT_DESCRIPTION,
			AUTHOR, LICENSE };
	public static final String[] README = { FULL_NAME, "", "[概述]",
			"本软件是《编译原理》课程中关于词法分析器的一个实践作品", "输入：一个源程序的字符串。",
			"过程：按照C语言词法分析词素构成。", "输出：一个词法单元序列，每个词法单元对应一个词素。", "", "[特性]",
			"1 能够识别十进制数、八进制数、标识符、浮点数、关键字、操作符等", "2 支持源代码输入和文件导入两种输入方式",
			"3 能分析出词法单元序列和符号表", "", "[版本]", "版本：v1.3.1", "发布日期：2014-12-24", "",
			"[关于项目]", "作者：Kinegratii", "项目托管：Git@OSC",
			"本项目采用 GPL 授权协议，欢迎基于此项目进行改进" };

	public static String join(String join, String[] strAry) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < strAry.length; i++) {
			if (i == (strAry.length - 1)) {
				sb.append(strAry[i]);
			} else {
				sb.append(strAry[i]).append(join);
			}
		}
		return new String(sb);
	}
}
