package org.firstinspires.ftc.teamcode.common.hardware;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.PwmControl;
import org.firstinspires.ftc.teamcode.common.bot.Bot;

public class Scooper {

    private final Servo servo;
    private int lastState = -1; // track last position to prevent jitter

    public Scooper(Bot bot) {
        this.servo = bot.hardwareMap.get(Servo.class, "scooper_servo");
    }

    public void init() {
        if (servo instanceof PwmControl) {
            ((PwmControl) servo).setPwmRange(new PwmControl.PwmRange(400, 2300));
        }
        servo.setPosition(0.1);
        lastState = 0;
    }

    /**
     * Set servo state via toggle buttons:
     * 0 -> X, 1 -> Circle, 2 -> Triangle
     */
    public void setState(int state) {
        if (state == lastState) return; // only move if new command

        switch (state) {
            case 0: servo.setPosition(0.1); break;
            case 1: servo.setPosition(0.4); break;
            case 2: servo.setPosition(1.0); break;
            default: return;
        }

        lastState = state;
    }
}