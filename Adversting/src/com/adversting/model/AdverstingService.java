package com.adversting.model;

import java.util.List;

public class AdverstingService {
	private AdverstingDAO_Interface dao;

	public AdverstingService() {
		dao = new AdverstingDAO();
	}

	public List<AdverstingVO> getAll() {
		return dao.getAll();
	}

	public AdverstingVO getOneDept(String adv_no) {
		return dao.findByPrimaryKey(adv_no);
	}

	public void deleteDept(String adv_no) {
		dao.delete(adv_no);
	}
}
