package com.java.ex.ui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import com.java.ex.db.MemberDao;
import com.java.ex.db.MemberDto;

public class Manager extends JFrame {
	JFrame frame = new JFrame();
	JButton edit = new JButton("회원 수정");
	JButton delete = new JButton("회원 삭제");
	JButton add = new JButton("회원 등록");
	JButton cancel = new JButton("취소");
	private JLabel select = new JLabel("회원 선택");
	JComboBox cb = new JComboBox();
	private final JTextArea memInfo = new JTextArea();
	private int selectedMember;
	private int selectIdx;

	public Manager() {
		frame.setTitle("회원 관리");
		frame.setSize(350, 300);
		frame.setLocation(500, 300);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setVisible(true);

		JPanel jp = new JPanel();
		jp.setLayout(null);
		frame.setContentPane(jp);

		select.setBounds(12, 14, 57, 15);
		jp.add(select);

		MemberDao mdao = MemberDao.getInstance();
		List<MemberDto> list = null;
		try {
			list = mdao.managementList();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		cb.setBounds(12, 39, 183, 21);
		cb.addItem("회원을 선택하세요");
		for (int i = 0; i < list.size(); i++) {
			cb.addItem(list.get(i).getM_code() + ". " + list.get(i).getM_name());
		}
		jp.add(cb);

		edit.setBounds(207, 40, 97, 32);
		jp.add(edit);

		delete.setBounds(207, 82, 97, 32);
		jp.add(delete);

		add.setBounds(207, 124, 97, 32);
		jp.add(add);
		
		cancel.setBounds(207, 166, 97, 32);
		jp.add(cancel);

		memInfo.setEditable(false);
		memInfo.setBounds(12, 80, 183, 152);
		jp.add(memInfo);
		
		edit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (cb.getSelectedIndex() == 0) {
					JOptionPane.showMessageDialog(null, "선택 실패");
				} else {
					frame.dispose();
					ManagerMemberEdit mme = new ManagerMemberEdit(cb.getSelectedIndex());
				}
			}
		});
		
		cb.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MemberDao mdao = MemberDao.getInstance();
				List<MemberDto> list = null;
				try {
					list = mdao.managementList();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				if (cb.getSelectedIndex() != 0) {
					int select = cb.getSelectedIndex() - 1;
					memInfo.setText("회원 코드 : " + list.get(select).getM_code() + "\n회원 이름 : "
							+ list.get(select).getM_name() + "\n회원 아이디 : " + list.get(select).getM_id() + "\n회원 비밀번호 : "
							+ list.get(select).getM_pw() + "\n회원 성별 : " + list.get(select).getM_sex() + "\n회원 나이 : "
							+ list.get(select).getM_age() + "\n회원 전화번호 : " + list.get(select).getM_tel());
					selectedMember = list.get(select).getM_code();
					selectIdx = cb.getSelectedIndex();
				} else {
					memInfo.setText(null);
				}
			}
		});
		
		delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MemberDao mdao = MemberDao.getInstance();
				MemberDto mdto = new MemberDto();
				mdto.setM_code(selectedMember);
				int delete = 0;
				try {
					delete = mdao.deleteMember(mdto);
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				if (cb.getSelectedIndex() != 0) {
					JOptionPane.showMessageDialog(null, "삭제 완료");
					selectedMember = -1;
					cb.removeItemAt(selectIdx);
				} else {
					JOptionPane.showMessageDialog(null, "삭제 실패");
				}
			}
		});
		
		add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				ManagerRegisterFrame mrf = new ManagerRegisterFrame();
			}
		});
		
		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				ManagerMemberList mml = new ManagerMemberList();
			}
		});
	}
}
