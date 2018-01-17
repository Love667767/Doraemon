package com.elson.basecore.utils;

import java.util.Collection;
import java.util.Map;

public class GenericUtil {

	private GenericUtil() {
	}

	public static <E extends Collection<?>> boolean isEmpty(final E list) {
		return list == null || list.size() == 0;
	}

	public static <E extends Map<?, ?>> boolean isEmpty(final E map) {
		return map == null || map.size() == 0;
	}

	public static <E> boolean isEmpty(final E[] array) {
		return array == null || array.length == 0;
	}


	public static boolean isEmpty(CharSequence str) {
		if (str == null || str.length() == 0) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isNotNull(final String str) {
		if (isEmpty(str)) {
			return false;
		} else {
			return !"null".equals(str);
		}
	}

	public static String getNotNullString(final String str){
		if(isNotNull(str)){
			return str;
		} else {
			return "";
		}
	}

	public static <E extends Collection<?>> boolean isEmpty(final E list,int position) {
		return isEmpty(list) || position <0 || position > list.size();
	}

	public static <E extends Map<?, ?>> boolean isEmpty(final E map,int position) {
		return isEmpty(map) || position < 0 || position>map.size();
	}

	public static <E> boolean isEmpty(final E[] array,int position) {
		return isEmpty(array) || position < 0 || position > array.length;
	}


	public static String checkNotNull(final String str) {
		return isEmpty(str) ? "" : str;
	}

	public static String checkNotNull(final String str, String defaultStr) {

		return isEmpty(str) ? defaultStr : str;
	}

	public static <T> T checkNotNull(final T obj, T defaultObj) {
		return obj == null ? defaultObj : obj;
	}


	/**
	 * 将需要拼接的字段用mark分隔开
	 * @param mark 分隔的符号
	 * @param list 待拼接的内容
	 * @param <T> 拼接类型
	 * @return 返回字符串类型
	 * example: expend(",", 1, 2, 3, "a") = "1,2,3,a"
	 */
	public static <T> String expend(String mark, T... list) {
		String tempMark = checkNotNull(mark, "");
		if (isEmpty(list)) {
			return "";
		}
		StringBuilder builder = new StringBuilder();
		for (int i = 0, len = list.length; i < len; i++) {
			builder.append(list[i]);
			if (i != len - 1) {
				builder.append(tempMark);
			}
		}
		return builder.toString();
	}

}
