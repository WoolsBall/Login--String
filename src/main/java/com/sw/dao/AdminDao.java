package com.sw.dao;

import com.sw.entity.Admin;

public interface AdminDao {

	 Admin findByCode(String adminCode);
}
