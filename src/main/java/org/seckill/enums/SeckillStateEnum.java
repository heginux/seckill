package org.seckill.enums;

/**
 * 使用枚举表示数字字典
 *
 * @author hgx
 */
public enum SeckillStateEnum {

    /**
     * 秒杀成功
     */
    SUCCESS(1, "秒杀成功"),
    /**
     * 秒杀结束
     */
    END(0, "秒杀结束"),
    /**
     * 重复秒杀
     */
    REPEAT_KILL(-1, "重复秒杀"),
    /**
     * 系统异常
     */
    INNER_ERROR(-2, "系统异常"),
    /**
     * 数据篡改
     */
    DATA_REWRITE(-3, "数据篡改");

    /**
     * 状态
     */
    private int state;

    /**
     * 状态信息
     */
    private String stateInfo;

    SeckillStateEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public static SeckillStateEnum stateOf(int index) {
        for (SeckillStateEnum state : values()) {
            if (state.getState() == index) {
                return state;
            }
        }
        return null;
    }
}
