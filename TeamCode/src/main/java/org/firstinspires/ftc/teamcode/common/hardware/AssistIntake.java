package org.firstinspires.ftc.teamcode.common.hardware;

import com.qualcomm.robotcore.hardware.CRServo;

import org.firstinspires.ftc.teamcode.common.bot.Bot;

public class AssistIntake {
    private final Bot bot;

    private double rotationPower;
    private CRServo servo;

    public AssistIntake(Bot bot, double rotationPower) {
        this.bot = bot;
        this.rotationPower = rotationPower;
    }

    public void init() {
        this.servo = this.bot.hardwareMap.get(CRServo.class, "assist_intake_servo");

        if (this.bot.isDebugMode()) {
            this.bot.telemetry.addData("AssistIntake", "Initialized");
        }
    }

    private void setRotationDirection(int direction) {
        this.servo.setPower(direction * this.rotationPower);
    }

    public void setRotationDirection(IntakeDirection direction) {
        switch (direction) {
            case STOP:
                this.setRotationDirection(0);
                break;

            case IN:
                this.setRotationDirection(1);
                break;

            case OUT:
                this.setRotationDirection(-1);
                break;
        }
    }
}
