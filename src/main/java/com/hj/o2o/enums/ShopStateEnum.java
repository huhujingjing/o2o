package com.hj.o2o.enums;

/**
 * @Author: HUJING
 * @Date: 2019/3/5 22:54
 * @Version 1.0
 * @Description:
 */

/**
 * 使用枚举表述常量数据字典
 */
public enum ShopStateEnum {

    CHECK(0, "审核中"), OFFLINE(-1, "非法商铺"), SUCCESS(1, "操作成功"), PASS(2, "通过认证"), INNER_ERROR(
            -1001, "操作失败"), NULL_SHOPID(-1002, "ShopId为空"), NULL_SHOP_INFO(
            -1003, "传入了空的信息");

    private int state;

    private String stateInfo;

    /**
     * 依据传入的state返回相应的enum值
     */
    private ShopStateEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public static ShopStateEnum stateOf(int index) {
        for (ShopStateEnum state : values()) {
            if (state.getState() == index) {
                return state;
            }
        }
        return null;
    }

}