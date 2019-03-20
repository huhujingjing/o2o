package com.hj.o2o.enums;

/**
 * @Description:
 * @Author: HUJING
 * @Date: 2019/3/20 20:49
 */
public enum HeadLineStateEnum {
    SUCCESS(0, "创建成功"), INNER_ERROR(-1001, "操作失败"), EMPTY(-1002, "头条信息为空");

    private int state;

    private String stateInfo;

    private HeadLineStateEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public static HeadLineStateEnum stateOf(int index) {
        for (HeadLineStateEnum state : values()) {
            if (state.getState() == index) {
                return state;
            }
        }
        return null;
    }
}
