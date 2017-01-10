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
package com.kinegratii.lexer.core;

import com.kinegratii.lexer.token.DotToken;
import com.kinegratii.lexer.token.DoubleToken;
import com.kinegratii.lexer.token.IdentifierToken;
import com.kinegratii.lexer.token.IntegerToken;
import com.kinegratii.lexer.token.OperateToken;
import com.kinegratii.lexer.token.ReservedToken;


/**
 * 回调接口
 * 
 * @author kinegratii
 * 
 */
public interface LexerAnalysisProccessCallback {

	/**
	 * 开始解析
	 */
	public void onStart();

	/**
	 * 解析成功
	 * 
	 * @param totalSeconds
	 *            用時，单位秒
	 */
	public void onSuccess(double totalSeconds);

	/**
	 * 源代码为空
	 */
	public void onEmptyContent();

	/**
	 * 出错
	 * 
	 * @param ex
	 */
	public void onFail(Exception ex);

	/**
	 * 新的一行
	 * 
	 * @param newLineIndex
	 *            行序号，从1开始
	 */
	public void onNewLine(int newLineIndex);

	/**
	 * 一般符号
	 * 
	 * @param token
	 */
	public void onSymToken(OperateToken token);

	/**
	 * 整型数字
	 * 
	 * @param token
	 * @param isNewSymbol
	 *            是否新的符号
	 */
	public void onIntToken(IntegerToken token, boolean isNewSymbol);

	/**
	 * 浮点数字
	 * 
	 * @param token
	 * @param isNewSymbol
	 *            是否新的符号
	 */
	public void onDoubleToken(DoubleToken token, boolean isNewSymbol);

	/**
	 * 保留关键字
	 * 
	 * @param token
	 */
	public void onReservedToken(ReservedToken token);

	/**
	 * 标识符
	 * 
	 * @param token
	 * @param isNewSymbol
	 *            是否新的符号
	 */
	public void onIdentifierToken(IdentifierToken token, boolean isNewSymbol);

	/**
	 * 分割符号
	 * 
	 * @param token
	 */
	public void onDotToken(DotToken token);
}