package com.xuzhihao;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.xuzhihao.mbg.mapper.UmsAdminAnnotationMapper;
import com.xuzhihao.mbg.mapper.UmsAdminMapper;
import com.xuzhihao.mbg.model.UmsAdmin;

/**
 CREATE TABLE `ums_demo`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `version` int(255) NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`) USING BTREE
 ) 
 */
public class Appliaction {

	public static void main(String[] args) throws IOException {
		String resource = "mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		// 解析xml
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		// 解析获取具体使用哪个执行器
		SqlSession sqlSession = sqlSessionFactory.openSession();
		UmsAdminMapper umsAdminMapper = sqlSession.getMapper(UmsAdminMapper.class);
		UmsAdmin umsAdmin = sqlSession.selectOne("com.xuzhihao.mbg.mapper.UmsAdminMapper.selectByPrimaryKey", 1L);
		System.out.println(umsAdmin);
		System.out.println(umsAdminMapper.selectByPrimaryKey(3L));
		UmsAdminAnnotationMapper umsAdminAnnotationMapper = sqlSession.getMapper(UmsAdminAnnotationMapper.class);
		// mapper注解设置sql
		System.out.println(umsAdminAnnotationMapper.selectByPrimaryKey(4L));
		sqlSession.commit();
		sqlSession.flushStatements();
		sqlSession.close();

	}

}
