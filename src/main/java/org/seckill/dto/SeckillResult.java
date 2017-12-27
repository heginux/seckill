package org.seckill.dto;

/**
 * 所有ajax返回类型,封装json结果
 *
 * @author heguixing
 */
public class SeckillResult<T> {

    /**
     * 请求结果
     */
    private boolean success;

    /**
     * 泛型数据
     */
    private T data;

    /**
     * 消息
     */
    private String error;

    public SeckillResult(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    public SeckillResult(boolean success, String error) {
        this.success = success;
        this.error = error;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
