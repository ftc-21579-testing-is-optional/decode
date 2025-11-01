package org.firstinspires.ftc.teamcode.common.hardware;

import org.firstinspires.ftc.teamcode.common.bot.Bot;

public class Yeeter {
    private final Bot bot;

    private boolean active;
    private float power;
    private float angle;

    public Yeeter(Bot bot) {
        this.bot = bot;
    }

    public void init() {

    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isActive() {
        return active;
    }

    public void setPower(float power) {
        this.power = power;
    }

    public float getPower() {
        return power;
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public void calculateRequired() {}
}
