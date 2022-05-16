package com.java.ex.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.java.ex.db.MemberDao;
import com.java.ex.db.MemberDto;

public class ManagerMemberList extends JFrame {
	JFrame frame = new JFrame();
	private DefaultTableModel tModel;

	public ManagerMemberList() {
		frame.setTitle("회원 리스트");
		frame.setSize(1300, 800);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(panel);

		JLabel jl = new JLabel("회원 리스트");
		jl.setFont(new Font("Serif", Font.BOLD, 50));
		jl.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(jl, BorderLayout.NORTH);
		
		MemberDao mdao = MemberDao.getInstance();
		List<MemberDto> list = null;
		try {
			list = mdao.managementList();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		String[] member = { "회원번호", "이름", "아이디", "비밀번호", "성별", "나이", "전화번호" };

		tModel = new DefaultTableModel(member, 0);

		for (int i = 0; i < list.size(); i++) {
			int code = list.get(i).getM_code();
			String name= list.get(i).getM_name();
			String id = list.get(i).getM_id();
			String pw = list.get(i).getM_pw();
			String gender = list.get(i).getM_sex();
			int age = list.get(i).getM_age();
			String tel = list.get(i).getM_tel();

			Object[] data = { code, name, id, pw, gender, age, tel};

			tModel.addRow(data);
		}
		
		JTable table = new JTable(tModel);
		table.setFont(new Font("돋움", Font.PLAIN, 20));
	    table.setRowHeight(30);
	    table.setEnabled(false);
	    
	    JScrollPane scrollPane = new JScrollPane(table);
	    panel.add(scrollPane, BorderLayout.CENTER);

		JPanel jp = new JPanel(new FlowLayout());
		JButton manager = new JButton("회원 관리");
		JButton close = new JButton("닫기");
		close.setPreferredSize(new Dimension(100, 50));
		manager.setPreferredSize(new Dimension(100, 50));
		jp.add(manager);
		jp.add(close);
		panel.add(jp, BorderLayout.SOUTH);

		manager.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				Manager ma = new Manager();
			}
		});

		close.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				MainFrame mf = new MainFrame();
			}
		});
	}
}
