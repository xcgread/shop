package com.xuzhihao.shop.search.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xuzhihao.shop.search.domain.EsProduct;

/**
 * 搜索系统中的商品管理自定义Dao
 */
public interface EsProductDao {
	List<EsProduct> getAllEsProductList(@Param("id") Long id);
}
