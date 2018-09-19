package com.pinyougou.sellergoods.service;

import java.util.List;
import java.util.Map;



import entity.PageResult;
import com.pinyougou.pojo.TbBrand;

public interface BrandService {
	public List<TbBrand> findAll();

	/*
	 * 分页查询所有
	 */
	public PageResult findPage(int pageNum, int pageSize);

	/*
	 * 
	 * 增加
	 */
	public void add(TbBrand tbBrand);

	/*
	 * 根据id查询
	 */
	public TbBrand findOne(Long id);

	/*
	 * 修改
	 */
	public void update(TbBrand tbBrand);

	/*
	 * 删除
	 */
	public void delete(Long[] ids);

	/*
	 * 删除
	 */
	public PageResult findPage(TbBrand tbBrand, int pageNum, int pageSize);
	/*
	 * 下拉列表的显示
	 * 
	*/
	public List<Map> selectOptionList();
}
