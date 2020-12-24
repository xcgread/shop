package com.xuzhihao.service;

import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

import com.xuzhihao.conditional.LinuxConditional;

import lombok.Data;

@Data
@Conditional(value = { LinuxConditional.class })
@Component
public class Linux {
	
}
