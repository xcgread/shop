package com.xuzhihao.shop.common.component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ch.qos.logback.classic.pattern.MessageConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;

/**
 * @author 日志脱敏插件
 * @description 日志打印进行相应脱敏处理
 * @date 2019/4/25 11:06
 */
public class SensitiveDataConverter extends MessageConverter {
	/**
	 * 日志脱敏开关
	 */
	private static Boolean converterCanRun = true;
	/**
	 * 日志脱敏关键字
	 */
	private static String sensitiveDataKeys = "realName,idCard,fixedPhone,bankCard,mobilePhone,address,email,cnapsCode";
	private static Pattern pattern = Pattern.compile("[0-9a-zA-Z]");

	@Override
	public String convert(ILoggingEvent event) {
		String oriLogMsg = event.getFormattedMessage();
		// 获取脱敏后的日志
		String afterLogMsg = invokeMsg(oriLogMsg);
		return afterLogMsg;
	}

	/**
	 * 处理日志字符串，返回脱敏后的字符串
	 * 
	 * @param msg
	 * @return
	 */
	public String invokeMsg(final String oriMsg) {
		String tempMsg = oriMsg;
		if (converterCanRun) {
			// 处理字符串
			if (sensitiveDataKeys != null && sensitiveDataKeys.length() > 0) {
				String[] keysArray = sensitiveDataKeys.split(",");
				for (String key : keysArray) {
					int index = -1;
					do {
						index = tempMsg.indexOf(key, index + 1);
						if (index != -1) {
							// 判断key是否为单词字符
							if (isWordChar(tempMsg, key, index)) {
								continue;
							}
							// 寻找值的开始位置
							int valueStart = getValueStartIndex(tempMsg, index + key.length());

							// 查找值的结束位置（逗号，分号）........................
							int valueEnd = getValuEndEIndex(tempMsg, valueStart);

							// 对获取的值进行脱敏
							String subStr = tempMsg.substring(valueStart, valueEnd);
							subStr = tuomin(subStr, key);
							///
							tempMsg = tempMsg.substring(0, valueStart) + subStr + tempMsg.substring(valueEnd);
						}
					} while (index != -1);
				}
			}
		}
		return tempMsg;
	}

	/**
	 * 判断从字符串msg获取的key值是否为单词 ， index为key在msg中的索引值
	 * 
	 * @return
	 */
	private boolean isWordChar(String msg, String key, int index) {

		// 必须确定key是一个单词............................
		if (index != 0) { // 判断key前面一个字符
			char preCh = msg.charAt(index - 1);
			Matcher match = pattern.matcher(preCh + "");
			if (match.matches()) {
				return true;
			}
		}
		// 判断key后面一个字符
		char nextCh = msg.charAt(index + key.length());
		Matcher match = pattern.matcher(nextCh + "");
		if (match.matches()) {
			return true;
		}
		return false;
	}

	/**
	 * 获取value值的开始位置
	 * 
	 * @param msg        要查找的字符串
	 * @param valueStart 查找的开始位置
	 * @return
	 */
	private int getValueStartIndex(String msg, int valueStart) {
		// 寻找值的开始位置.................................
		do {
			char ch = msg.charAt(valueStart);
			if (ch == ':' || ch == '=') { // key与 value的分隔符
				valueStart++;
				ch = msg.charAt(valueStart);
				if (ch == '"') {
					valueStart++;
				}
				break; // 找到值的开始位置
			} else {
				valueStart++;
			}
		} while (true);
		return valueStart;
	}

	/**
	 * 获取value值的结束位置
	 * 
	 * @return
	 */
	private int getValuEndEIndex(String msg, int valueEnd) {
		do {
			if (valueEnd == msg.length()) {
				break;
			}
			char ch = msg.charAt(valueEnd);

			if (ch == '"') { // 引号时，判断下一个值是结束，分号还是逗号决定是否为值的结束
				if (valueEnd + 1 == msg.length()) {
					break;
				}
				char nextCh = msg.charAt(valueEnd + 1);
				if (nextCh == ';' || nextCh == ',') {
					// 去掉前面的 \ 处理这种形式的数据
					while (valueEnd > 0) {
						char preCh = msg.charAt(valueEnd - 1);
						if (preCh != '\\') {
							break;
						}
						valueEnd--;
					}
					break;
				} else {
					valueEnd++;
				}
			} else if (ch == ';' || ch == ',' || ch == '}') {
				break;
			} else {
				valueEnd++;
			}

		} while (true);
		return valueEnd;
	}

	private String tuomin(String submsg, String key) {
		if ("realName".equals(key)) {
			return SensitiveInfoUtils.chineseName(submsg);// 姓名
		}
		if ("idCard".equals(key)) {
			return SensitiveInfoUtils.idCard(submsg);// 身份证号
		}
		if ("fixedPhone".equals(key)) {
			return SensitiveInfoUtils.fixedPhone(submsg);// 固定电话
		}
		if ("bankCard".equals(key)) {
			return SensitiveInfoUtils.bankCard(submsg);// 银行卡号
		}
		if ("mobilePhone".equals(key)) {
			return SensitiveInfoUtils.mobilePhone(submsg);// 手机号
		}
		if ("address".equals(key)) {
			return SensitiveInfoUtils.address(submsg, 6);// 地址
		}
		if ("email".equals(key)) {
			return SensitiveInfoUtils.email(submsg);// email
		}
		if ("cnapsCode".equals(key)) {
			return SensitiveInfoUtils.cnapsCode(submsg);// 公司开户银行联号
		}
		return "";
	}
}