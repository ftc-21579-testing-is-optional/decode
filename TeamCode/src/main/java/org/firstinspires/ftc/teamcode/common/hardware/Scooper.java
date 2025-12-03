package org.firstinspires.ftc.teamcode.common.hardware;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.PwmControl;
import org.firstinspires.ftc.teamcode.common.bot.Bot;

public class Scooper {

    private final Bot bot;
    private  Servo servo;
    private ScooperState lastState = ScooperState.UNKNOWN; // track last position to prevent jitter

    public Scooper(Bot bot) {
        this.bot = bot;
    }

    public void init() {
        this.servo = this.bot.hardwareMap.get(Servo.class, "scooper_servo");

        if (servo instanceof PwmControl) {
            ((PwmControl) servo).setPwmRange(new PwmControl.PwmRange(400, 2500));
        }
    }

    public void setState(ScooperState state) {
        if (state == this.lastState) {
            return; // only move if new command
        }

        switch (state) {
            case RECYCLE:
                this.servo.setPosition(0.0);
                break;

            case CATCH:
                this.servo.setPosition(0.25);
                break;

            case YEET:
                this.servo.setPosition(0.8);
                break;
        }

        this.lastState = state;
    }
}