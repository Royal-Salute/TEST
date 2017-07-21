package com.adversting.model;

import java.io.*;
import java.sql.*;
import java.util.*;
import javax.naming.*;
import javax.sql.DataSource;

public class AdverstingJNDIDAO implements AdverstingDAO_Interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/ProjectDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "insert into adversting (adv_no, com_no, startday, endday, price, text, img, vdo, status) values (ltrim(to_char(adv_no_seq.nextval,'0009')), ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "select adv_no, com_no, startday, endday, price, text, img, vdo, status from adversting order by adv_no";
	private static final String GET_ONE_STMT = "select adv_no, com_no, startday, endday, price, text, img, vdo, status from adversting where adv_no = ?";
	private static final String DELETE = "delete from adversting where adv_no = ?";
	private static final String UPDATE = "update adversting set com_no=?, startday=?, endday=?, price=?, text=?, img=?, vdo=?, status=? where adv_no = ?";

	@Override
	public void insert(AdverstingVO adverstingVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			String[] cols = { "adv_no" };
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT, cols);
			pstmt.setString(1, adverstingVO.getCom_no());
			pstmt.setTimestamp(2, adverstingVO.getStartDay());
			pstmt.setTimestamp(3, adverstingVO.getEndDay());
			pstmt.setInt(4, adverstingVO.getPrice());

			Clob clob = con.createClob();
			String str = adverstingVO.getText();
			clob.setString(1, str);
			pstmt.setClob(5, clob);

			Blob blob1 = con.createBlob();
			blob1.setBytes(1, adverstingVO.getImg());
			pstmt.setBlob(6, blob1);

			Blob blob2 = con.createBlob();
			blob2.setBytes(1, adverstingVO.getVdo());
			pstmt.setBlob(7, blob2);

			pstmt.setString(8, adverstingVO.getStatus());
			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public void update(AdverstingVO adverstingVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, adverstingVO.getCom_no());
			pstmt.setTimestamp(2, adverstingVO.getStartDay());
			pstmt.setTimestamp(3, adverstingVO.getEndDay());
			pstmt.setInt(4, adverstingVO.getPrice());

			Clob clob = con.createClob();
			String str = adverstingVO.getText();
			clob.setString(1, str);
			pstmt.setClob(5, clob);

			Blob blob1 = con.createBlob();
			blob1.setBytes(1, adverstingVO.getImg());
			pstmt.setBlob(6, blob1);

			Blob blob2 = con.createBlob();
			blob2.setBytes(1, adverstingVO.getVdo());
			pstmt.setBlob(7, blob2);
			pstmt.setString(8, adverstingVO.getStatus());
			pstmt.setString(9, adverstingVO.getAdv_no());
			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public void delete(String adv_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, adv_no);
			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public AdverstingVO findByPrimaryKey(String adv_no) {
		AdverstingVO adverstingVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, adv_no);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				adverstingVO = new AdverstingVO();
				adverstingVO.setAdv_no(rs.getString("adv_no"));
				adverstingVO.setCom_no(rs.getString("com_no"));
				adverstingVO.setStartDay(rs.getTimestamp("startday"));
				adverstingVO.setEndDay(rs.getTimestamp("endday"));
				adverstingVO.setPrice(rs.getInt("price"));

				Clob clob = rs.getClob("text");
				StringBuilder sb = new StringBuilder();
				BufferedReader br = new BufferedReader(clob.getCharacterStream());
				String str;
				while ((str = br.readLine()) != null) {
					sb.append(str);
					sb.append("\n");
				}
				br.close();
				adverstingVO.setText(sb.toString());

				Blob blob1 = rs.getBlob("img");
				int blob1Length = (int) blob1.length();
				adverstingVO.setImg(blob1.getBytes(1, blob1Length));

				Blob blob2 = rs.getBlob("vdo");
				int blob2Length = (int) blob2.length();
				adverstingVO.setVdo(blob2.getBytes(1, blob2Length));

				adverstingVO.setStatus(rs.getString("status"));
			}
		} catch (IOException e) {
			System.out.println(e);
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return adverstingVO;
	}

	@Override
	public List<AdverstingVO> getAll() {
		List<AdverstingVO> list = new ArrayList<AdverstingVO>();
		AdverstingVO adverstingVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				adverstingVO = new AdverstingVO();
				adverstingVO.setAdv_no(rs.getString("adv_no"));
				adverstingVO.setCom_no(rs.getString("com_no"));
				adverstingVO.setStartDay(rs.getTimestamp("startday"));
				adverstingVO.setEndDay(rs.getTimestamp("endday"));
				adverstingVO.setPrice(rs.getInt("price"));

				Clob clob = rs.getClob("text");
				StringBuilder sb = new StringBuilder();
				BufferedReader br = new BufferedReader(clob.getCharacterStream());
				String str;
				while ((str = br.readLine()) != null) {
					sb.append(str);
					sb.append("\n");
				}
				br.close();
				adverstingVO.setText(sb.toString());

				Blob blob1 = rs.getBlob("img");
				int blob1Length = (int) blob1.length();
				adverstingVO.setImg(blob1.getBytes(1, blob1Length));

				Blob blob2 = rs.getBlob("vdo");
				int blob2Length = (int) blob2.length();
				adverstingVO.setVdo(blob2.getBytes(1, blob2Length));

				adverstingVO.setStatus(rs.getString("status"));
				list.add(adverstingVO);
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} catch (IOException ie) {
			System.out.println(ie);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
}