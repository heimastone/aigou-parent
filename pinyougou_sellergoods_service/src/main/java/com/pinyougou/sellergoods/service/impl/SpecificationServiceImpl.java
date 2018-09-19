package com.pinyougou.sellergoods.service.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pinyougou.mapper.TbSpecificationMapper;
import com.pinyougou.mapper.TbSpecificationOptionMapper;
import com.pinyougou.pojo.TbSpecification;
import com.pinyougou.pojo.TbSpecificationExample;
import com.pinyougou.pojo.TbSpecificationExample.Criteria;
import com.pinyougou.pojo.TbSpecificationOption;
import com.pinyougou.pojo.TbSpecificationOptionExample;
import com.pinyougou.pojogroup.Specification;
import com.pinyougou.sellergoods.service.SpecificationService;

import entity.PageResult;
import org.springframework.transaction.annotation.Transactional;

/**
 * 服务实现层
 * @author Administrator
 *
 */
@Service
@Transactional
public class SpecificationServiceImpl implements SpecificationService {

	@Autowired
	private TbSpecificationMapper specificationMapper;
	@Autowired
	private TbSpecificationOptionMapper specificationOptionMapper;
	
	/**
	 * 查询全部
	 */

	public List<TbSpecification> findAll() {
		return specificationMapper.selectByExample(null);
	}

	/**
	 * 按分页查询
	 */

	public PageResult findPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);		
		Page<TbSpecification> page=   (Page<TbSpecification>) specificationMapper.selectByExample(null);
		return new PageResult(page.getTotal(), page.getResult());
	}

	/**
	 * 增加
	 */
	
	

	public void add(Specification specification) {
		//获取实体对象
	TbSpecification tbSpecification=specification.getSpecification();
	specificationMapper.insert(tbSpecification);
	List<TbSpecificationOption> tbSpecificationOption= specification.getSpecificationOptionList();
	for(TbSpecificationOption option:tbSpecificationOption) {
		option.setSpecId(tbSpecification.getId());
		specificationOptionMapper.insert(option);
		}
		
	}


	
	/**
	 * 修改
	 */

	public void update(Specification specification){
		//获取实体对象
		TbSpecification tbSpecification=specification.getSpecification();
		specificationMapper.updateByPrimaryKey(tbSpecification);
		
		//删除清空原有规格
		TbSpecificationOptionExample example=new TbSpecificationOptionExample();
		example.createCriteria().andSpecIdEqualTo(tbSpecification.getId());
		specificationOptionMapper.deleteByExample(example);
		//再添加改后的规格
		List<TbSpecificationOption> tbSpecificationOption= specification.getSpecificationOptionList();
		for(TbSpecificationOption option:tbSpecificationOption) {
			option.setSpecId(tbSpecification.getId());
			specificationOptionMapper.insert(option);
			}
	}	
	
	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */

	public Specification findOne(Long id){
		Specification specification=new Specification();
	TbSpecification tbSpecification=specificationMapper.selectByPrimaryKey(id);
	specification.setSpecification(tbSpecification);
TbSpecificationOptionExample	example=new TbSpecificationOptionExample();
     example.createCriteria().andSpecIdEqualTo(id);
List<TbSpecificationOption> specificationOptionList=specificationOptionMapper.selectByExample(example);
specification.setSpecificationOptionList(specificationOptionList);
	
		return specification;
	}

	/**
	 * 批量删除
	 */

	public void delete(Long[] ids) {
		for(Long id:ids){
			specificationMapper.deleteByPrimaryKey(id);
			//删除清空原有规格
			TbSpecificationOptionExample example=new TbSpecificationOptionExample();
			example.createCriteria().andSpecIdEqualTo(id);
			specificationOptionMapper.deleteByExample(example);
		}		
	}
	
	

	public PageResult findPage(TbSpecification specification, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		
		TbSpecificationExample example=new TbSpecificationExample();
		Criteria criteria = example.createCriteria();
		
		if(specification!=null){			
						if(specification.getSpecName()!=null && specification.getSpecName().length()>0){
				criteria.andSpecNameLike("%"+specification.getSpecName()+"%");
			}
	
		}
		
		Page<TbSpecification> page= (Page<TbSpecification>)specificationMapper.selectByExample(example);		
		return new PageResult(page.getTotal(), page.getResult());
	}
		/*
		 * 下拉列表的显示
		 *

		*/

		public List<Map> selectOptionList(){
			return	specificationMapper.selectOptionList();
			}
		
	
}
