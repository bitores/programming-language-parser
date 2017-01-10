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

import java.awt.BorderLayout;
import java.awt.FileDialog;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import com.kinegratii.lexer.core.Analyzer;
import com.kinegratii.lexer.core.LexerAnalysisProccessCallback;
import com.kinegratii.lexer.token.DotToken;
import com.kinegratii.lexer.token.DoubleToken;
import com.kinegratii.lexer.token.IdentifierToken;
import com.kinegratii.lexer.token.IntegerToken;
import com.kinegratii.lexer.token.OperateToken;
import com.kinegratii.lexer.token.ReservedToken;
import com.kinegratii.lexer.utils.BareBonesBrowserLaunch;

/**
 * 主界面
 * 
 * @author kinegratii
 * 
 */
@SuppressWarnings("serial")
public class MainFrame extends JFrame implements ActionListener {

	// 菜单栏，北区
	private JMenuBar jm = new JMenuBar();
	private JMenu startMenu = new JMenu("开始");
	private JMenu runMenu = new JMenu("运行");
	private JMenu optionMenu = new JMenu("选项");
	private JMenuItem openItem = new JMenuItem("打开");
	private JMenuItem saveItem = new JMenuItem("保存");
	private JMenuItem clearItem = new JMenuItem("清除");
	private JMenuItem quitItem = new JMenuItem(" 退出");
	private JMenuItem analyItem = new JMenuItem("分析");
	private JMenuItem helpItem = new JMenuItem("帮助");
	private JMenuItem projectItem = new JMenuItem("主页");
	private JMenuItem aboutItem = new JMenuItem("关于");

	// 按钮面板，南区
	private JButton[] jb = { new JButton("打开"), new JButton("清除"),
			new JButton("保存"), new JButton("分析"), new JButton("退出") };
	private JPanel buttonJPane = new JPanel(new FlowLayout());

	// 选项卡0，西区
	private JTabbedPane jtab0 = new JTabbedPane();
	JTextArea sourceTextArea = new JTextArea(19, 40);
	JScrollPane sourceJSP = new JScrollPane(sourceTextArea);

	// 选项卡1,东区
	private JTabbedPane jtab1 = new JTabbedPane(SwingConstants.TOP);
	private JTextArea reportTextArea = new JTextArea("", 19, 40);
	private JTextArea tableTextArea = new JTextArea("", 19, 40);
	private JTextArea ruleTextArea = new JTextArea(SoftwareInfo.join("\n",
			SoftwareInfo.README), 19, 40);
	private JScrollPane reportJSP = new JScrollPane(reportTextArea);
	private JScrollPane tableJSP = new JScrollPane(tableTextArea);
	private JScrollPane ruleJSP = new JScrollPane(ruleTextArea);

	public MainFrame() {
		this.setTitle(SoftwareInfo.FULL_NAME);
		this.setLayout(new BorderLayout());
		this.setBounds(100, 100, 960, 593);
		this.setVisible(true);
		this.setResizable(false);

		// 菜单栏设计，北区
		startMenu.add(openItem);
		openItem.addActionListener(this);
		startMenu.add(saveItem);
		saveItem.addActionListener(this);
		startMenu.add(quitItem);
		quitItem.addActionListener(this);
		runMenu.add(clearItem);
		clearItem.addActionListener(this);
		runMenu.add(analyItem);
		analyItem.addActionListener(this);
		optionMenu.add(helpItem);
		helpItem.addActionListener(this);
		optionMenu.add(projectItem);
		projectItem.addActionListener(this);
		optionMenu.add(aboutItem);
		aboutItem.addActionListener(this);
		jm.add(startMenu);
		jm.add(runMenu);
		jm.add(optionMenu);
		this.add(jm, BorderLayout.NORTH);

		// 按钮设计，南区
		for (int i = 0; i < jb.length; i++) {
			buttonJPane.add(jb[i]);
			jb[i].addActionListener(this);
		}
		this.add(buttonJPane, BorderLayout.SOUTH);

		// 源程序输入，西区
		jtab0.add("源程序", sourceJSP);
		this.add(jtab0, BorderLayout.WEST);

		// 结果输出，东区
		reportTextArea.setEditable(false);
		tableTextArea.setEditable(false);
		ruleTextArea.setEditable(false);
		jtab1.add("词法单元序列", reportJSP);
		jtab1.add("符号表", tableJSP);
		jtab1.add("帮助", ruleJSP);
		this.add(jtab1, BorderLayout.EAST);

	}

	public void actionPerformed(ActionEvent e) {
		Object thissource = e.getSource();
		if (thissource instanceof JButton) {
			JButton thisjb = (JButton) thissource;
			if (thisjb == jb[0]) {
				this.openFile();
			} else if (thisjb == jb[1]) {
				this.clearContent();
			} else if (thisjb == jb[2]) {
				this.saveFile();
			} else if (thisjb == jb[3]) {
				this.startAnaylise();
			} else if (thisjb == jb[4]) {
				this.quitPragram();
			}
		} else if (thissource instanceof JMenuItem) {
			JMenuItem thisjt = (JMenuItem) thissource;
			if (thisjt == aboutItem) {
				JOptionPane.showMessageDialog(this, SoftwareInfo.ABOUT_INFO,
						"软件信息", JOptionPane.INFORMATION_MESSAGE);
			} else if (thisjt == openItem) {
				this.openFile();
			} else if (thisjt == clearItem) {
				this.clearContent();
			} else if (thisjt == quitItem) {
				this.quitPragram();
			} else if (thisjt == saveItem) {
				this.saveFile();
			} else if (thisjt == helpItem) {
				this.loadRuleFile();
			} else if (thisjt == analyItem) {
				this.startAnaylise();
			} else if (thisjt == projectItem) {
				BareBonesBrowserLaunch.openURL(SoftwareInfo.PROJECT_HOME);
			}
		}
	}

	/**
	 * 打开文件
	 */
	private void openFile() {
		FileDialog fd = new FileDialog(this, "打开文件", FileDialog.LOAD);
		fd.setVisible(true);
		try {
			sourceTextArea.setText("");

			if (fd.getFile() != null) {
				File myFile = new File(fd.getDirectory(), fd.getFile());
				InputStream is = new FileInputStream(myFile);
				InputStreamReader isr = new InputStreamReader(is, "utf8");
				BufferedReader bf = new BufferedReader(isr);
				String temp = null;
				while ((temp = bf.readLine()) != null) {
					sourceTextArea.append(temp + "\n");
				}
				bf.close();
			}
		} catch (IOException ioe) {
			JOptionPane.showMessageDialog(this, "文件读取发生错误！", "错误",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * 保存文件
	 */
	private void saveFile() {
		try {
			FileDialog fd = new FileDialog(this, "保存文件", FileDialog.SAVE);
			fd.setVisible(true);
			if (fd.getFile() != null) {
				File myFile = new File(fd.getDirectory(), fd.getFile() + ".cpp");
				OutputStream os = new FileOutputStream(myFile);
				OutputStreamWriter osw = new OutputStreamWriter(os, "utf8");
				BufferedWriter bw = new BufferedWriter(osw);
				String temp = sourceTextArea.getText();
				bw.write(temp);
				bw.close();
			}
		} catch (IOException ioe) {
			JOptionPane.showMessageDialog(this, "保存文件失败！");
		}
	}

	/**
	 * 开始分析
	 */
	private void startAnaylise() {
		String src = sourceTextArea.getText();
		LexerAnalysisProccessCallback callback = new ProccessCallback();
		Analyzer.getDefaultAnalyzer().analyse(src, callback);
		jtab1.setSelectedIndex(0);
	}

	/**
	 * 清除
	 */
	public void clearContent() {
		sourceTextArea.setText("");
		reportTextArea.setText("");
		tableTextArea.setText("");
	}

	/**
	 * 加载帮助文件
	 */
	private void loadRuleFile() {
		jtab1.setSelectedIndex(2);
	}

	/**
	 * 退出程序
	 */
	private void quitPragram() {
		int pd = JOptionPane.showConfirmDialog(this, "确认退出?", "提示",
				JOptionPane.YES_NO_OPTION);
		if (pd == JOptionPane.YES_OPTION) {
			this.dispose();
		}
	}

	public void addReportLine(String s) {
		reportTextArea.append(s + "\n");
	}

	public void addTableLine(String symbol, String raw) {
		tableTextArea.append(String.format("%s\t%s\n", symbol, raw));
	}

	class ProccessCallback implements LexerAnalysisProccessCallback {

		@Override
		public void onStart() {
			MainFrame.this.reportTextArea.setText("");
			MainFrame.this.tableTextArea.setText("");
		}

		@Override
		public void onSuccess(double totalSeconds) {
			JOptionPane.showMessageDialog(MainFrame.this,
					String.format("分析完成，用时%.3f秒", totalSeconds));

		}

		@Override
		public void onEmptyContent() {
			JOptionPane.showMessageDialog(MainFrame.this, "源程序为空！", "错误",
					JOptionPane.ERROR_MESSAGE);
		}

		@Override
		public void onFail(Exception ex) {
			JOptionPane.showMessageDialog(MainFrame.this, "分析过程中出现错误！", "错误",
					JOptionPane.ERROR_MESSAGE);

		}

		@Override
		public void onNewLine(int newLineIndex) {
			MainFrame.this.addReportLine("Line:" + newLineIndex + "------");

		}

		@Override
		public void onSymToken(OperateToken token) {
			MainFrame.this.addReportLine(token.fullString());

		}

		@Override
		public void onIntToken(IntegerToken token, boolean isNewSymbol) {
			MainFrame.this.addReportLine(token.fullString());
			if (isNewSymbol) {
				MainFrame.this
						.addTableLine(IntegerToken.SYMBOL, token.getRaw());
			}

		}

		@Override
		public void onDoubleToken(DoubleToken token, boolean isNewSymbol) {
			MainFrame.this.addReportLine(token.fullString());
			if (isNewSymbol) {
				MainFrame.this.addTableLine(DoubleToken.SYMBOL, token.getRaw());
			}

		}

		@Override
		public void onReservedToken(ReservedToken token) {
			MainFrame.this.addReportLine(token.fullString());

		}

		@Override
		public void onIdentifierToken(IdentifierToken token, boolean isNewSymbol) {
			MainFrame.this.addReportLine(token.fullString());
			if (isNewSymbol) {
				MainFrame.this.addTableLine(IdentifierToken.SYMBOL,
						token.getRaw());
			}

		}

		@Override
		public void onDotToken(DotToken token) {
			MainFrame.this.addReportLine(token.fullString());

		}

	}
}
