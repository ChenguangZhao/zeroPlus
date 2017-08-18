package com.zpc.common.constants;

import java.util.HashMap;
import java.util.Map;

/**
 * @Title:
 * @Description:
 * @author: chenguang.zcg
 * @version:1.1.0
 * @date 2017/08/17
 */
public class CustomException {

    public static final int EXCEPTION = 100000;
    public static final int NULL_POINTER_EXCEPTION = 100001;
    public static final int HANDLE_ILLEGAL_ARGUMENT_EXCEPTION = 100002;
    public static final int MISSING_SERVLET_REQUEST_PARAMETER_EXCEPTION = 100003;
    public static final int ARITHMETIC_EXCEPTION = 100004;
    public static final int DATA_INTEGRITY_VIOLATION_EXCEPTION = 100005;
    public static final int UNCATEGORIZED_SQL_EXCEPTION = 100006;

    public static Map<Integer, String> msgMap = new HashMap<Integer, String>();

    static {
        msgMap.put(NULL_POINTER_EXCEPTION, "空指针错误");
        msgMap.put(HANDLE_ILLEGAL_ARGUMENT_EXCEPTION, "方法参数错误");
        msgMap.put(MISSING_SERVLET_REQUEST_PARAMETER_EXCEPTION, "参数错误");
        msgMap.put(EXCEPTION, "系统内部错误");
        msgMap.put(ARITHMETIC_EXCEPTION, "数学运算错误");
        msgMap.put(DATA_INTEGRITY_VIOLATION_EXCEPTION, "违反完整性错误");
        msgMap.put(UNCATEGORIZED_SQL_EXCEPTION, "非法数据类型");
    }
}
