package com.java.ex.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberDao {
	Connection con = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;

	private String register = "INSERT INTO members(m_code, m_name, m_id, m_pw, m_sex, m_age, m_tel) VALUES(?, ?, ?, ?, ?, ?, ?)";
	private final String LIST = "select * FROM members";
	private String delete = "DELETE FROM members WHERE m_code = ?";
	private String update = "UPDATE members SET m_name=?, m_id=?, m_pw=?, m_sex=?, m_age=?, m_tel=? WHERE m_code=?";

	public MemberDao() {
	}

	private static MemberDao instance = new MemberDao();

	public static MemberDao getInstance() {
		return instance;
	}

	public int insertMember(MemberDto mdto) throws Exception {
		con = DBUtil.getConnection();
		try {
			stmt = con.prepareStatement(register);

			stmt.setInt(1, mdto.getM_code());
			stmt.setString(2, mdto.getM_name());
			stmt.setString(3, mdto.getM_id());
			stmt.setString(4, mdto.getM_pw());
			stmt.setString(5, mdto.getM_sex());
			stmt.setInt(6, mdto.getM_age());
			stmt.setString(7, mdto.getM_tel());
			stmt.executeUpdate();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
		return -1;
	}

	public int deleteMember(MemberDto mdto) throws Exception {
		int deletecount = 0;
		con = DBUtil.getConnection();
		try {
			stmt = con.prepareStatement(delete);
			stmt.setInt(1, mdto.getM_code());
			deletecount = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
		return deletecount;
	}

	public int updateMember(MemberDto mdto) throws Exception {
		int result = 0;
		try {
			con = DBUtil.getConnection();
			stmt = con.prepareStatement(update);

			stmt.setString(1, mdto.getM_name());
			stmt.setString(2, mdto.getM_id());
			stmt.setString(3, mdto.getM_pw());
			stmt.setString(4, mdto.getM_sex());
			stmt.setInt(5, mdto.getM_age());
			stmt.setString(6, mdto.getM_tel());
			stmt.setInt(7, mdto.getM_code());

			result = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
		return result;
	}

	public List<MemberDto> managementList() throws Exception {
		con = DBUtil.getConnection();
		List<MemberDto> list = new ArrayList<MemberDto>();
		try {
			stmt = con.prepareStatement(LIST);
			rs = stmt.executeQuery();
			while (rs.next()) {
				MemberDto mdto = new MemberDto();
				mdto.setM_code(rs.getInt("m_code"));
				mdto.setM_name(rs.getString("m_name"));
				mdto.setM_id(rs.getString("m_id"));
				mdto.setM_pw(rs.getString("m_pw"));
				mdto.setM_sex(rs.getString("m_sex"));
				mdto.setM_age(rs.getInt("m_age"));
				mdto.setM_tel(rs.getString("m_tel"));
				list.add(mdto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
		return list;
	}
}
