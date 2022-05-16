package com.java.ex.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.java.ex.db.MemberDao;
import com.java.ex.db.MemberDto;

public class ManagerRegisterFrame extends JFrame {
	private JPanel rPanel;
	private JLabel rLabel;
	private JLabel rCode, rName, rId, rPw, rGender, rAge, rTel;
	private JTextField tCode, tName, tId, tPw, tAge, tTel;
	private JRadioButton men, women;
	private JButton rButton;

	public ManagerRegisterFrame() {
		super("회원등록");
		super.setResizable(true);
		setSize(500, 600);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		rPanel = new JPanel();
		rPanel.setLayout(new BorderLayout());
		setContentPane(rPanel);

		rLabel = new JLabel("회원등록");
		rLabel.setHorizontalAlignment(SwingConstants.CENTER);
		rLabel.setFont(new Font("Serif", Font.BOLD, 40));
		rPanel.add(rLabel, BorderLayout.NORTH);

		JPanel main = new JPanel(new GridLayout(8, 2, 10, 10));

		rCode = new JLabel("회원 코드 : ");
		rCode.setHorizontalAlignment(SwingConstants.CENTER);
		main.add(rCode);

		tCode = new JTextField();
		main.add(tCode);

		rId = new JLabel("아이디 : ");
		rId.setHorizontalAlignment(SwingConstants.CENTER);
		main.add(rId);

		tId = new JTextField();
		main.add(tId);

		rPw = new JLabel("비밀번호 : ");
		rPw.setHorizontalAlignment(SwingConstants.CENTER);
		main.add(rPw);

		tPw = new JPasswordField();
		main.add(tPw);

		rName = new JLabel("성명 : ");
		rName.setHorizontalAlignment(SwingConstants.CENTER);
		main.add(rName);

		tName = new JTextField();
		main.add(tName);

		rAge = new JLabel("나이 : ");
		rAge.setHorizontalAlignment(SwingConstants.CENTER);
		main.add(rAge);

		tAge = new JTextField();
		main.add(tAge);

		rTel = new JLabel("전화번호 : ");
		rTel.setHorizontalAlignment(SwingConstants.CENTER);
		main.add(rTel);

		tTel = new JTextField();
		main.add(tTel);

		rGender = new JLabel("성별");

		rGender.setHorizontalAlignment(SwingConstants.CENTER);
		main.add(rGender);

		ButtonGroup bg = new ButtonGroup();
		JPanel gender = new JPanel();

		men = new JRadioButton("남자");
		gender.add(men);
		bg.add(men);

		women = new JRadioButton("여자");
		gender.add(women);
		bg.add(women);

		main.add(gender);

		rPanel.add(main, BorderLayout.CENTER);

		JPanel sMain = new JPanel();
		rPanel.add(sMain, BorderLayout.EAST);

		JPanel jp = new JPanel(new FlowLayout());
		rButton = new JButton("등록하기");
		JButton btnno = new JButton("취소"); 
		rButton.setPreferredSize(new Dimension(100, 50));
		btnno.setPreferredSize(new Dimension(100, 50));
		jp.add(rButton);
		jp.add(btnno);
		rPanel.add(jp, BorderLayout.SOUTH);
		
		btnno.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Manager();
			}
		});
		
		rButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int code = Integer.parseInt(tCode.getText());
				int age = Integer.parseInt(tAge.getText());
				if (men.isSelected()) {
					String men = "남자";
				} else if (women.isSelected()) {
					String women = "여자";
				}
				MemberDto mdto = new MemberDto();
				mdto.setM_code(code);
				mdto.setM_name(tName.getText());
				mdto.setM_id(tId.getText());
				mdto.setM_pw(tPw.getText());
				if (men.isSelected())
					mdto.setM_sex(men.getText());
				else
					mdto.setM_sex(women.getText());
				mdto.setM_age(age);
				mdto.setM_tel(tTel.getText());
				
				MemberDao mdao = new MemberDao();
				int result = 0;
				try {
					result = mdao.insertMember(mdto);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				if (result == 1) {
					JOptionPane.showMessageDialog(null, "회원등록 완료");
					dispose();
					new Manager();
				} else {
					JOptionPane.showMessageDialog(null, "회원동록 실패");
					dispose();
					new Manager();
				}
			}
		});
	}
}
