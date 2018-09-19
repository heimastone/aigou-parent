package com.pinyougou.sellergoods.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pinyougou.mapper.TbBrandMapper;
import com.pinyougou.pojo.TbBrand;
import com.pinyougou.pojo.TbBrandExample;
import com.pinyougou.pojo.TbBrandExample.Criteria;
import com.pinyougou.sellergoods.service.BrandService;

import entity.PageResult;

@Service
public class BrandServiceImpl implements BrandService {

	@Autowired
	private TbBrandMapper tbBrandMapper;


	public List<TbBrand> findAll() {

		return tbBrandMapper.selectByExample(null);
	}

	/*
	 * 分页查询
	 */
	public PageResult findPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		Page<TbBrand> page = (Page<TbBrand>) tbBrandMapper.selectByExample(null);
		return new PageResult(page.getTotal(), page.getResult());
	}


	public void add(TbBrand tbBrand) {
		tbBrandMapper.insert(tbBrand);
	}


	public TbBrand findOne(Long id) {

		return tbBrandMapper.selectByPrimaryKey(id);
	}


	public void update(TbBrand tbBrand) {
		tbBrandMapper.updateByPrimaryKey(tbBrand);

	}


	public void delete(Long[] ids) {
		for (Long id : ids) {
			tbBrandMapper.deleteByPrimaryKey(id);
		}

	}


	public PageResult findPage(TbBrand tbBrand, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		TbBrandExample example=new TbBrandExample();
		Criteria criteria=	example.createCriteria();
		
		if(tbBrand!=null) {
			if(tbBrand.getName()!=null&&tbBrand.getName().length()>0) {
				criteria.andNameLike("%"+tbBrand.getName()+"%");
			}
			if(tbBrand.getFirstChar()!=null&&tbBrand.getFirstChar().length()>0) {
				criteria.andFirstCharLike("%"+tbBrand.getFirstChar()+"%");
			}
		}
		Page<TbBrand> page = (Page<TbBrand>) tbBrandMapper.selectByExample(example);
		return new PageResult(page.getTotal(), page.getResult());
	}

	
	/*
	 * 下拉列表的显示
	 *

	*/

	public List<Map> selectOptionList(){
		return	tbBrandMapper.selectOptionList();
		}
	


}
