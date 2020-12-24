package com.xuzhihao.shop.dubbo.service;

import java.util.HashMap;

public interface ProductService {

	HashMap<String, String> findByPid(Integer pid);
}
