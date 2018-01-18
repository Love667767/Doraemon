package com.elson.basecore.utils;

import android.support.annotation.StringRes;
import android.widget.TextView;

import com.elson.basecore.exception.BizRunException;
import com.elson.basecore.utils.common.EmptyUtil;


/**
 * 通用的业务处理
 * Created by elson on 2017/11/6
 */

public class ComLogicUtil {

    private ComLogicUtil() {}

    public static String getViewContent(TextView view) {
        return view.getText().toString().trim();
    }

    /**
     * 获取控件中的文本内容
     * @param view：指定的控件
     * @param msg：控件内容为空时，提示的内容
     * @return
     * @throws BizRunException
     */
    public static String getViewContent(TextView view, String msg) throws BizRunException {
        String content = getViewContent(view);
        if (EmptyUtil.isEmpty(content)) {
            throw new BizRunException(msg);
        }
        return content;
    }

    public static String getViewContent(TextView view, @StringRes int msgId) throws BizRunException {
        return getViewContent(view, view.getContext().getString(msgId));
    }

    /**
     * 获取控件当中的不为 0 的数字
     * @param view
     * @param msg
     * @return
     * @throws BizRunException
     */
    public static String getViewNum(TextView view, @StringRes int msg) throws BizRunException {
        return getViewNum(view, view.getContext().getString(msg));
    }

    public static String getViewNum(TextView view, String msg) throws BizRunException {
        String num = getViewContent(view, msg);
        if (Double.valueOf(num) == 0) {
            throw new BizRunException(msg);
        }
        return num;
    }



    //***************************************** 校验数据 **************************************************

    public static String checkString(String str, String msg) throws BizRunException {
        if (str == null || EmptyUtil.isEmpty(str.trim())) {
            throw new BizRunException(msg);
        }
        return str;
    }

    /**
     * 根据给定的flag，判断是否要抛出异常
     * @param flag
     * @param msg
     * @throws BizRunException
     */
    public static void checkByFlag(boolean flag, String msg) throws BizRunException {
        if (flag) {
            throw new BizRunException(msg);
        }
    }


}
