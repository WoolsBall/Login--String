package com.sw.daoimpl;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import com.sw.dao.AdminDao;
import com.sw.entity.Admin;

@Repository
public class AdminDaoImpl implements AdminDao, Serializable {

	@Resource
	private DataSource ds;

	@Override
	public Admin findByCode(String adminCode) {
		if (adminCode == null) {
			return null;
		}
		Connection conn = null;
		try {
			conn = ds.getConnection();
			String sql = "select * from admin_info where admin_code=?";
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setString(1, adminCode);
			ResultSet rs = psmt.executeQuery();
			if (rs.next()) {
				Admin admin = new Admin();
				admin.setAdminId(rs.getInt("admin_id"));
				admin.setAdminCode(rs.getString("admin_code"));
				admin.setPassword(rs.getString("password"));
				admin.setName(rs.getString("name"));
				admin.setTelephone(rs.getString("telephone"));
				admin.setEmail(rs.getString("email"));
				return admin;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("根据编码查询管理员失败", e);
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return null;
	}

}
