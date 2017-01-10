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

import java.util.HashSet;
import java.util.Set;

import com.kinegratii.lexer.token.DotToken;
import com.kinegratii.lexer.token.DoubleToken;
import com.kinegratii.lexer.token.IdentifierToken;
import com.kinegratii.lexer.token.IntegerToken;
import com.kinegratii.lexer.token.OperateToken;
import com.kinegratii.lexer.token.ReservedToken;

/**
 * 词法分析器
 * 
 * @author kinegratii
 * 
 */
public class Analyzer {

	// 单例模式

	private static Analyzer mAnalyzer = null;

	/**
	 * 获取默认的词法分析器
	 * 
	 * @return
	 */
	public static Analyzer getDefaultAnalyzer() {
		if (mAnalyzer == null) {
			mAnalyzer = new Analyzer();
		}
		return mAnalyzer;
	}

	/**
	 * 要解析的字符串
	 */
	private String content = null;
	/**
	 * 该字符长度
	 */
	private int contentLen = 0;
	/**
	 * 解析游标
	 */
	private int curIndex = 0;
	/**
	 * 是否已经完成
	 */
	private boolean isComplete;
	/**
	 * 解析行数
	 */
	private int line = 0;
	/**
	 * 当前字符
	 */
	private char peek;

	private Set<String> symbolSet = null;

	private Analyzer() {
		reset();
		ReservedToken.load();
		OperateToken.load();
	}

	/**
	 * 重置
	 */
	private void reset() {
		this.content = null;
		this.contentLen = 0;
		this.curIndex = 0;
		this.isComplete = false;
		if (symbolSet == null) {
			symbolSet = new HashSet<>();
		} else {
			symbolSet.clear();
		}
	}

	private boolean addNewSymbol(Object obj) {
		String s = String.valueOf(obj);
		if (symbolSet.contains(s)) {
			return false;
		} else {
			symbolSet.add(s);
			return true;
		}
	}

	/**
	 * 读取一个字符
	 * 
	 */
	private void readch() {
		if (curIndex < contentLen) {
			peek = content.charAt(curIndex);
			curIndex++;
		} else {
			isComplete = true;
		}
	}

	/**
	 * 读取一个字符并判定
	 * 
	 * @param c
	 * @return
	 */
	private boolean readch(char c) {
		readch();
		if (peek != c) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 设置源代码字符串
	 * 
	 * @param content
	 * @return
	 */
	public boolean setContent(String content) {
		if (content != null) {
			String tmp = content.trim();
			if (tmp.length() > 0) {
				this.content = tmp;
				this.contentLen = tmp.length();
				this.curIndex = 0;
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	/**
	 * 解析
	 * 
	 * @param content
	 * @param callback
	 */
	public void analyse(String content, LexerAnalysisProccessCallback callback) {
		long st = System.currentTimeMillis();
		this.reset();
		if (!this.setContent(content)) {
			callback.onEmptyContent();
			return;
		}
		callback.onStart();
		boolean isRead = true;
		line = 1;
		callback.onNewLine(line);
		for (int i = 0; i < 8000; i++) {
			if (isComplete) {
				break;
			}
			if (isRead) {
				readch();
			}
			isRead = true;
			if ((int) peek == 65535) {
				break;
			}
			if (peek == ' ' || peek == '\t') {
				continue;
			}
			if (peek == '\n') {
				line++;
				callback.onNewLine(line);
			}
			if (OperateToken.isMatchPrefix(peek)) {
				switch (peek) {
				case '&':
					if (readch('&')) {
						callback.onSymToken(new OperateToken("&&"));
					} else {
						callback.onSymToken(new OperateToken("&"));
						isRead = false;
					}
					break;
				case '|':
					if (readch('|')) {
						callback.onSymToken(new OperateToken("||"));
					} else {
						callback.onSymToken(new OperateToken("|"));
						isRead = false;
					}
					break;
				case '!':
					if (readch('=')) {
						callback.onSymToken(new OperateToken("!="));
					} else {
						callback.onSymToken(new OperateToken("!"));
						isRead = false;
					}
					break;
				case '<':
					if (readch('=')) {
						callback.onSymToken(new OperateToken("<="));
					} else {
						callback.onSymToken(new OperateToken("<"));
						isRead = false;
					}
					break;
				case '>':
					if (readch('=')) {
						callback.onSymToken(new OperateToken(">="));
					} else {
						callback.onSymToken(new OperateToken(">"));
						isRead = false;
					}
					break;
				case '+':
					if (readch('=')) {
						callback.onSymToken(new OperateToken("+="));
					} else {
						callback.onSymToken(new OperateToken("+"));
						isRead = false;
					}
					break;
				case '-':
					if (readch('=')) {
						callback.onSymToken(new OperateToken("-="));
					} else {
						callback.onSymToken(new OperateToken("-"));
						isRead = false;
					}
					break;
				case '*':
					if (readch('=')) {
						callback.onSymToken(new OperateToken("*="));
					} else {
						callback.onSymToken(new OperateToken("*"));
						isRead = false;
					}
					break;
				case '/':
					readch();
					if (peek == '=') {
						callback.onSymToken(new OperateToken("/="));
					} else if (peek == '/') {
						do {
							readch();
							if (peek == '\n') {
								break;
							}
						} while (true);
						isRead = false;
					} else {
						callback.onSymToken(new OperateToken("/"));
						isRead = false;
					}
					break;
				case '=':
					if (readch('=')) {
						callback.onSymToken(new OperateToken("=="));
					} else {
						callback.onSymToken(new OperateToken("="));
						isRead = false;
					}
					break;
				default:
					break;
				}
			}
			if (Character.isDigit(peek)) {
				boolean isZeroLead = false;
				int v = 0;
				if (peek == '0') {
					isZeroLead = true;
				}
				do {
					v = 10 * v + Character.digit(peek, 10);
					readch();
				} while (Character.isDigit(peek));
				if (peek != '.') {
					callback.onIntToken(new IntegerToken(v, isZeroLead),
							addNewSymbol(v));
				} else {
					float x = v;
					float d = 10;
					for (;;) {
						readch();
						if (!Character.isDigit(peek)) {
							break;
						}
						x = x + Character.digit(peek, 10) / d;
						d = d * 10;
					}
					callback.onDoubleToken(new DoubleToken(x), addNewSymbol(x));
				}
				isRead = false;
			}
			if (Character.isLetter(peek) || peek == '_') {
				StringBuffer b = new StringBuffer();
				do {
					b.append(peek);
					readch();
				} while (Character.isLetterOrDigit(peek) || peek == '_');
				String s = b.toString();
				if (ReservedToken.isMatch(s)) {
					callback.onReservedToken(new ReservedToken(s));
				} else {
					callback.onIdentifierToken(new IdentifierToken(s),
							addNewSymbol(s));
				}
				isRead = false;
			}
			if (DotToken.isMatch(peek)) {
				String temp = Character.toString(peek);
				callback.onDotToken(new DotToken(temp));
				isRead = true;
			}
		}
		double totalSeconds = (System.currentTimeMillis() - st) / 1000.0;
		callback.onSuccess(totalSeconds);

	}

}
