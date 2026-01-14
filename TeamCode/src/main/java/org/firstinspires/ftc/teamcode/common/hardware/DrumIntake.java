package org.firstinspires.ftc.teamcode.common.hardware;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.common.bot.Bot;

public class DrumIntake {
    private final Bot bot;

    private double rotationPower;
    private DcMotor motor;

    public DrumIntake(Bot bot, double rotationPower) {
        this.bot = bot;
        this.rotationPower = rotationPower;
    }

    public void init() {
        this.motor = this.bot.hardwareMap.get(DcMotor.class, "");

        if (this.bot.isDebugMode()) {
            this.bot.telemetry.addData("Intake", "Initialized");
        }
    }

    private void setRotationDirection(int direction) {
        this.motor.setPower(direction * this.rotationPower);
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
