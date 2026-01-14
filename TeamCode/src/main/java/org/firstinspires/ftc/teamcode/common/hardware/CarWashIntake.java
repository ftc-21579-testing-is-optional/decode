package org.firstinspires.ftc.teamcode.common.hardware;

import com.qualcomm.robotcore.hardware.CRServo;

import org.firstinspires.ftc.teamcode.common.bot.Bot;

public class CarWashIntake {
    private final Bot bot;

    private double rotationPower;
    private CRServo leftServo;
    private CRServo rightServo;

    public CarWashIntake(Bot bot, double rotationPower) {
        this.bot = bot;
        this.rotationPower = rotationPower;
    }

    public void init() {
        this.leftServo = this.bot.hardwareMap.get(CRServo.class, "left_intake_servo");
        this.rightServo = this.bot.hardwareMap.get(CRServo.class, "right_intake_servo");

        if (this.bot.isDebugMode()) {
            this.bot.telemetry.addData("Intake", "Initialized");
        }
    }

    private void setRotationDirection(int direction) {
        this.leftServo.setPower(direction * this.rotationPower);
        this.rightServo.setPower(-direction * this.rotationPower);
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
