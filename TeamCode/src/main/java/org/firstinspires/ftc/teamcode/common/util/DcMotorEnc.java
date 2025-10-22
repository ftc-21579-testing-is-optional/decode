package org.firstinspires.ftc.teamcode.common.util;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.configuration.typecontainers.MotorConfigurationType;

public class DcMotorEnc {
    public DcMotor motor;
    public int ticksForFullRotation = 538;
    public float fullRotAngle = (float) ticksForFullRotation / 360f;

    public DcMotorEnc(DcMotor motor) {
        this.motor = motor;
    }

    public int angleToTicks(float angle) {
        return (int) (this.fullRotAngle * angle);
    }

    public float ticksToAngle(int ticks) {
        return (1f / this.fullRotAngle) * ticks;
    }

    public void rotateTo(float angle, float power) {
        this.motor.setTargetPosition(this.angleToTicks(angle));

        this.motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        this.motor.setPower(power);

        while (this.motor.isBusy()) {}

        this.motor.setPower(0);
    }

    public void rotateBy(float angle, float power) {
        int currPosition = this.motor.getCurrentPosition();
        int newPosition = this.angleToTicks(angle) + currPosition;

        this.rotateTo(this.ticksToAngle(newPosition), power);
    }
}
