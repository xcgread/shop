package com.xuzhihao.mbg.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xuzhihao.interceptor.encrpyt.SensitiveConverter;
import com.xuzhihao.interceptor.encrpyt.SensitiveType;
import com.xuzhihao.interceptor.locker.VersionLocker;
import com.xuzhihao.mbg.model.UmsDemo;
import com.xuzhihao.mbg.model.UmsDemoExample;

public interface UmsDemoMapper {
	long countByExample(UmsDemoExample example);

	int deleteByExample(UmsDemoExample example);

	int deleteByPrimaryKey(Long id);

	int insert(UmsDemo record);

	int insertSelective(UmsDemo record);

	List<UmsDemo> selectByExample(UmsDemoExample example);

//	@SensitiveConverter(value = SensitiveType.SELECT)
	UmsDemo selectByPrimaryKey(Long id);

	int updateByExampleSelective(@Param("record") UmsDemo record, @Param("example") UmsDemoExample example);

	int updateByExample(@Param("record") UmsDemo record, @Param("example") UmsDemoExample example);

	
	int updateByPrimaryKeySelective(UmsDemo record);

	@VersionLocker()
	@SensitiveConverter(value = SensitiveType.UPDATE)
	int updateByPrimaryKey(UmsDemo record);
}