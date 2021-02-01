package com.xuzhihao.mapper;

import org.apache.ibatis.annotations.Select;

public interface OrderMapper {

	@Select("select 'order'")
	String selectByPrimaryKey(Long id);

}
