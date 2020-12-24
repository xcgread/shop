package com.xuzhihao.config;

import java.io.IOException;

import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

/**
 * 设置排除扫描规则
 */
public class MyComponentScan implements TypeFilter {

	@SuppressWarnings("unused")
	@Override
	public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory)
			throws IOException {
		AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();// 获取当前类的注解信息
		ClassMetadata classMetadata = metadataReader.getClassMetadata();// 获取当前类信息
		String classPath = metadataReader.getResource().toString();// 获取当前类资源信息
		String className = classMetadata.getClassName();
		if (className.indexOf("OrderSerice")>-1) {
			return true;//true表示排除此bean
		}
		return false;
	}

}
