package com.xuzhihao.mbg.mapper;

import com.xuzhihao.interceptor.plugin.VersionLocker;
import com.xuzhihao.mbg.model.UmsDemo;
import com.xuzhihao.mbg.model.UmsDemoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmsDemoMapper {
    long countByExample(UmsDemoExample example);

    int deleteByExample(UmsDemoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UmsDemo record);

    int insertSelective(UmsDemo record);

    List<UmsDemo> selectByExample(UmsDemoExample example);

    UmsDemo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UmsDemo record, @Param("example") UmsDemoExample example);

    int updateByExample(@Param("record") UmsDemo record, @Param("example") UmsDemoExample example);

    int updateByPrimaryKeySelective(UmsDemo record);

//    @VersionLocker(false)
    int updateByPrimaryKey(UmsDemo record);
}