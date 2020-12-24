package com.xuzhihao.shop.portal.service;

import org.springframework.data.domain.Page;

import com.xuzhihao.shop.portal.domain.MemberProductCollection;

/**
 * 会员收藏Service
 */
public interface MemberCollectionService {
	int add(MemberProductCollection productCollection);

	int delete(Long productId);

	Page<MemberProductCollection> list(Integer pageNum, Integer pageSize);

	MemberProductCollection detail(Long productId);

	void clear();
}
