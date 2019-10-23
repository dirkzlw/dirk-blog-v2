package com.zlw.blog.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * 记录返回对象和返回的状态
 * @author Ranger
 * @create 2019-10-23 9:52
 */
@Getter
@Setter
public class ResultObj<T> {
    private T obj;
    private String rtn;

    public ResultObj() {
    }

    public ResultObj(T obj, String rtn) {
        this.obj = obj;
        this.rtn = rtn;
    }
}
