package org.seckill.entiy;

import java.util.Date;

/**
 * 秒杀成功实体
 *
 * @author heguixing
 */
public class SuccessKilled {

    private Long id;

    /**
     * 秒杀id
     */
    private Long seckillId;

    /**
     * 用户手机号
     */
    private Long userPhone;

    /**
     * 状态
     */
    private Short state;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 修改时间
     */
    private Date modifiedTime;

    /**
     * 多对一,多维护
     */
    private Seckill seckill;


    @Override
    public String toString() {
        return "SuccessKilled{" +
                "id=" + id +
                ", seckillId=" + seckillId +
                ", userPhone=" + userPhone +
                ", state=" + state +
                ", createdTime=" + createdTime +
                ", modifiedTime=" + modifiedTime +
                ", seckill=" + seckill +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(Long seckillId) {
        this.seckillId = seckillId;
    }

    public Long getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(Long userPhone) {
        this.userPhone = userPhone;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Seckill getSeckill() {
        return seckill;
    }

    public void setSeckill(Seckill seckill) {
        this.seckill = seckill;
    }

    public Short getState() {
        return state;
    }

    public void setState(Short state) {
        this.state = state;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }
}
