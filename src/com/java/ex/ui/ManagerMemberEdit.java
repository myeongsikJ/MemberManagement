package com.java.ex.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.java.ex.db.MemberDao;
import com.java.ex.db.MemberDto;

public class ManagerMemberEdit extends JFrame {
	JFrame frame = new JFrame();
	private JPanel jp;
	private JLabel jl;
	private JLabel name, id, pw, age, tel, gender;
	private JTextField tname, tid, tpw, tage, ttel, tgender;
	private JButton bname, bid, bpw, bage, btel, bgender;

	public ManagerMemberEdit(int index) {
		frame.setTitle("회원 수정");
		frame.setResizable(true);
		frame.setSize(500, 600);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		jp = new JPanel();
		jp.setLayout(new BorderLayout());
		frame.setContentPane(jp);

		jl = new JLabel("회원 수정");
		jl.setHorizontalAlignment(SwingConstants.CENTER);
		jl.setFont(new Font("Serif", Font.BOLD, 40));
		jp.add(jl, BorderLayout.NORTH);

		JPanel main = new JPanel(new GridLayout(6, 3, 10, 10));
		jp.add(main, BorderLayout.CENTER);

		name = new JLabel("이름 : ");
		name.setHorizontalAlignment(SwingConstants.CENTER);
		main.add(name);

		tname = new JTextField();
		tname.setEditable(false);
		main.add(tname);

		bname = new JButton("회원 이름");
		main.add(bname);

		id = new JLabel("아이디 : ");
		id.setHorizontalAlignment(SwingConstants.CENTER);
		main.add(id);

		tid = new JTextField();
		tid.setEditable(false);
		main.add(tid);

		bid = new JButton("회원 아이디");
		main.add(bid);

		pw = new JLabel("비밀번호 : ");
		pw.setHorizontalAlignment(SwingConstants.CENTER);
		main.add(pw);

		tpw = new JTextField();
		tpw.setEditable(false);
		main.add(tpw);

		bpw = new JButton("회원 비밀번호");
		main.add(bpw);

		gender = new JLabel("성별 : ");
		gender.setHorizontalAlignment(SwingConstants.CENTER);
		main.add(gender);

		tgender = new JTextField();
		tgender.setEditable(false);
		main.add(tgender);

		bgender = new JButton("회원 성별");
		main.add(bgender);

		age = new JLabel("나이 : ");
		age.setHorizontalAlignment(SwingConstants.CENTER);
		main.add(age);

		tage = new JTextField();
		tage.setEditable(false);
		main.add(tage);

		bage = new JButton("회원 나이");
		main.add(bage);

		tel = new JLabel("전화번호 : ");
		tel.setHorizontalAlignment(SwingConstants.CENTER);
		main.add(tel);

		ttel = new JTextField();
		ttel.setEditable(false);
		main.add(ttel);

		btel = new JButton("회원 전화번호");
		main.add(btel);

		JPanel bt = new JPanel(new FlowLayout());
		JButton btnok = new JButton("확인");
		JButton btnno = new JButton("취소");
		btnok.setPreferredSize(new Dimension(100, 50));
		btnno.setPreferredSize(new Dimension(100, 50));
		bt.add(btnok);
		bt.add(btnno);
		jp.add(bt, BorderLayout.SOUTH);

		MemberDao mdao = MemberDao.getInstance();
		MemberDto mdto = new MemberDto();
		List<MemberDto> list = null;
		try {
			list = mdao.managementList();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		index -= 1;
		int i = list.get(index).getM_age();
		int code = list.get(index).getM_code();
		String ag = Integer.toString(list.get(index).getM_age());
		tname.setText(list.get(index).getM_name());
		tid.setText(list.get(index).getM_id());
		tpw.setText(list.get(index).getM_pw());
		tgender.setText(list.get(index).getM_sex());
		tage.setText(ag);
		ttel.setText(list.get(index).getM_tel());
		
		bname.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int check;
				if (e.getSource() == bname) {
					check = JOptionPane.showConfirmDialog(null, "수정하시겠습니까?\n" + "회원 이름 : " + tname.getText(), "메시지",
							JOptionPane.INFORMATION_MESSAGE);
					if (check == 0) {
						String name = JOptionPane.showInputDialog("수정할 이름을 입력하세요");
						tname.setText(name);
					}
				}
			}
		});
		
		bid.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int check;
				if (e.getSource() == bid) {
					check = JOptionPane.showConfirmDialog(null, "수정하시겠습니까?\n" + "회원 아이디 : " + tid.getText(), "메시지",
							JOptionPane.INFORMATION_MESSAGE);
					if (check == 0) {
						String id = JOptionPane.showInputDialog("수정할 아이디를 입력하세요");
						tid.setText(id);
					}
				}
			}
		});
		
		bpw.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int check;
				if (e.getSource() == bpw) {
					check = JOptionPane.showConfirmDialog(null, "수정하시겠습니까?\n" + "회원 비밀번호 : " + tpw.getText(), "메시지",
							JOptionPane.INFORMATION_MESSAGE);
					if (check == 0) {
						String pw = JOptionPane.showInputDialog("수정할 비밀번호를 입력하세요");
						tpw.setText(pw);
					}
				}
			}
		});
		
		bgender.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int check;
				if (e.getSource() == bgender) {
					check = JOptionPane.showConfirmDialog(null, "수정하시겠습니까?\n" + "회원 성별 : " + tgender.getText(), "메시지",
							JOptionPane.INFORMATION_MESSAGE);
					if (check == 0) {
						String gender = JOptionPane.showInputDialog("수정할 성별을 입력하세요");
						tgender.setText(gender);
					}
				}
			}
		});
		
		bage.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int check;
				if (e.getSource() == bage) {
					check = JOptionPane.showConfirmDialog(null, "수정하시겠습니까?\n" + "회원 나이 : " + tage.getText(), "메시지",
							JOptionPane.INFORMATION_MESSAGE);
					if (check == 0) {
						String age = JOptionPane.showInputDialog("수정할 나이를 입력하세요");
						tage.setText(age);
					}
				}
			}
		});

		btel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int check;
				if (e.getSource() == btel) {
					check = JOptionPane.showConfirmDialog(null, "수정하시겠습니까?\n" + "회원 전화번호 : " + ttel.getText(), "메시지",
							JOptionPane.INFORMATION_MESSAGE);
					if (check == 0) {
						String tel = JOptionPane.showInputDialog("수정할 전화번호를 입력하세요");
						ttel.setText(tel);
					}
				}
			}
		});

		btnok.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int age = Integer.parseInt(tage.getText());
				mdto.setM_code(code);
				mdto.setM_name(tname.getText());
				mdto.setM_id(tid.getText());
				mdto.setM_pw(tpw.getText());
				mdto.setM_sex(tgender.getText());
				mdto.setM_age(age);
				mdto.setM_tel(ttel.getText());
				int result = 0;
				MemberDao mdao = MemberDao.getInstance();
				try {
					result = mdao.updateMember(mdto);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				if (result == 1) {
					JOptionPane.showMessageDialog(null, "회원수정 완료");
				} else {
					JOptionPane.showMessageDialog(null, "회원수정 실패");
				}
				frame.dispose();
				Manager ma = new Manager();
			}
		});
		
		btnno.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				Manager ma = new Manager();
			}
		});
	}
}
