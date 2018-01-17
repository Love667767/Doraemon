package com.elson.basecore.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.regex.Pattern;

/**
 *
 * 该类要重写
 * Created by elson on 2018/1/17.
 */
@Deprecated
public class BigDecimalUtil {

    final static BigDecimal ONE_HUNDRED = new BigDecimal(100);

    private static final DecimalFormat twoPointsDecimalFormat = new DecimalFormat("#0.00");


    // **********************************分转元********************************************
    /**
     * 转换成元
     * @param cent:long cent
     * @return
     */
    public static final String toYuan(long cent) {
        return yuan(String.valueOf(cent)).toString();
    }

    /**
     * 转换成元
     * @param cent : string cent
     * @return
     */
    public static final String toYuan(String cent) {
        return yuan(cent).toString();
    }

    /**
     * 转换成元
     * @param cent:string cent
     * @return
     */
    private static BigDecimal yuan(String cent) {
        if (GenericUtil.isEmpty(cent)) {
            cent = "0";
        }
        BigDecimal decimal = new BigDecimal(cent);
        if (decimal.intValue() == 0) {//BigInteger division by zero
            return decimal;
        }
        return decimal.divide(ONE_HUNDRED).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    // **********************************元转分********************************************
    /**
     * 金额单位换算，将元转换成分
     *
     * @param num : 元
     * @return： 分
     */
    public static final long unitary2Cent(String num) {
        if (GenericUtil.isEmpty(num)) {
            return 0;
        }
        return new BigDecimal(num)
                .setScale(2, BigDecimal.ROUND_HALF_UP)
                .multiply(ONE_HUNDRED)
                .setScale(2, BigDecimal.ROUND_HALF_UP)
                .longValue();
    }

    // ############################## BigDecimal运算 ################################################


    // ******************************************************************************

    /**
     * 数字格式化100000 => 100,000.00
     * @param num
     * @return
     */
    public static String format(double num) {
        NumberFormat format = DecimalFormat.getNumberInstance();
        format.setMinimumFractionDigits(2);//最少2位小数
        format.setMaximumFractionDigits(2);//最多两位小数
        return format.format(num);
    }

    public static String format(String num) {
        String temp = GenericUtil.checkNotNull(num, "0");
        return format(new BigDecimal(temp).doubleValue());
    }

    public static String format(BigDecimal num) {
        return format(num.doubleValue());
    }

    /**
     * 数字格式化100000 => ￥100,000.00
     * @param num
     * @return
     */
    public static String formatCash(double num) {
        NumberFormat format = DecimalFormat.getCurrencyInstance();
        format.setMinimumFractionDigits(2);//最少2位小数
        format.setMaximumFractionDigits(2);//最多两位小数
        return format.format(num);
    }

    public static String formatCash(String num) {
        String temp = GenericUtil.checkNotNull(num, "0");
        return formatCash(new BigDecimal(temp).doubleValue());
    }

    public static String formatCash(BigDecimal num) {
        return formatCash(num.doubleValue());
    }

    public static BigDecimal createBigDecimal(String num) {
        String temp = GenericUtil.checkNotNull(num, "0");
        return new BigDecimal(temp);
    }


    // **********************************判断是否为数字********************************************
    static Pattern DOUBLE_PATTERN = Pattern.compile("^[-+]?[0-9]+(\\.[0-9]+)?$");
    static Pattern INT_PATTERN = Pattern.compile("^[-+]?[0-9]");

    /**
     * 是否为数字
     * @param numberStr
     * @return
     */
    public static boolean isNumber(String numberStr) {
        return isInteger(numberStr) || isDoubleNumber(numberStr);
    }

    /***
     * 判断 String 是否为int
     *
     * @param intStr
     * @return
     */
    public static boolean isInteger(String intStr) {
        return INT_PATTERN.matcher(intStr).matches();
    }

    /**
     * 判断 String 是否为double
     * @param doubleStr
     * @return
     */
    public static boolean isDoubleNumber(String doubleStr){
        return DOUBLE_PATTERN.matcher(doubleStr).matches();
    }

    // **********************************2位小数********************************************
    public static String format2TwoPoint(String str){
        if (GenericUtil.isEmpty(str) || !isNumber(str)) {
            str = "0";
        }
        BigDecimal decimal = new BigDecimal(str);
        if (decimal.intValue() == 0) {//BigInteger division by zero
            return decimal.toString();
        }
        return decimal.setScale(2, BigDecimal.ROUND_DOWN).toString();
    }
}
