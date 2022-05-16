package com.java.ex.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class MainFrame extends JFrame {
	JFrame frame = new JFrame();

	public MainFrame() {
		frame.setTitle("회원관리 프로그램");
		frame.setSize(500, 360);
		frame.setLocation(500, 300);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setLayout(null);

		JButton bt1 = new JButton("회원등록");
		bt1.setBounds(22, 232, 133, 49);
		frame.add(bt1);
		bt1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				RegisterFrame rm = new RegisterFrame();
			}
		});

		JButton bt2 = new JButton("회원목록");
		bt2.setBounds(174, 232, 133, 49);
		frame.add(bt2);
		bt2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				MemberList ml = new MemberList();
			}
		});

		JButton bt3 = new JButton("관리자전용");
		bt3.setBounds(324, 232, 133, 49);
		frame.add(bt3);
		bt3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				ManagerMemberList mml = new ManagerMemberList();
			}
		});
	}
}
