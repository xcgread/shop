package com.xuzhihao.mapper;

import org.apache.ibatis.annotations.Select;

public interface UserMapper {
	@Select("select 'user'")
	String selectByPrimaryKey(Long id);

}
