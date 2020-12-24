package com.xuzhihao.service;

import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

import com.xuzhihao.conditional.WindowsConditional;

import lombok.Data;

@Data
@Conditional(value = { WindowsConditional.class })
@Component
public class Windows {

}
