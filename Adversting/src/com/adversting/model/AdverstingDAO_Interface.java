package com.adversting.model;

import java.util.List;

public interface AdverstingDAO_Interface {
	public void insert(AdverstingVO adverstingVO);
	public void update(AdverstingVO adverstingVO);
	public void delete(String adv_no);
	public AdverstingVO findByPrimaryKey(String adv_no);
	public List<AdverstingVO> getAll();
}
