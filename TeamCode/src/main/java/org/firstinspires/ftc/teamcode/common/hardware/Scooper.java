package org.firstinspires.ftc.teamcode.common.hardware;

import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.common.bot.Bot;

public class Scooper {
    private final Bot bot;
    private Servo servo;

    public Scooper(Bot bot) {
        this.bot = bot;
    }

    public void init() {
        this.servo = this.bot.hardwareMap.get(Servo.class, "scooper_servo");
    }

    public void setScooperPosition(float position) {
        this.servo.setPosition(position);
    }
}
