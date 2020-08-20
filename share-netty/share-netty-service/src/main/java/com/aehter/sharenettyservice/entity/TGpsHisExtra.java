package com.aehter.sharenettyservice.entity;

/**
 * @author 我走路带风
 * @since 2020/8/19 15:21
 */
public class TGpsHisExtra extends TGpsHis {

    /**
     * 速度
     */
    private double speed;

    /**
     * 方位
     */
    private double direction;

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getDirection() {
        return direction;
    }

    public void setDirection(double direction) {
        this.direction = direction;
    }
}
