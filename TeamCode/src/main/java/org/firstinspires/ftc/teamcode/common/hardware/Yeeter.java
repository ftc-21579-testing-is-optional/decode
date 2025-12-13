package org.firstinspires.ftc.teamcode.common.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.common.bot.Bot;

public class Yeeter {
    private final Bot bot;

    private double rotationPowerFull;
    private double rotationPowerLess;
    private double rotationPowerAuto;
    private DcMotor leftMotor;
    private DcMotor rightMotor;


    private YeeterMode lastMode = YeeterMode.UNKNOWN;

//    private boolean active;
//    private boolean activeLess;

    public Yeeter(Bot bot, double rotationPowerFull, double rotationPowerLess, double rotationPowerAuto) {
        this.bot = bot;
        this.rotationPowerFull = rotationPowerFull;
        this.rotationPowerLess = rotationPowerLess;
        this.rotationPowerAuto = rotationPowerAuto;
    }

    public void init() {
        this.leftMotor = this.bot.hardwareMap.get(DcMotor.class, "motor5");
        this.rightMotor = this.bot.hardwareMap.get(DcMotor.class, "motor6");

        if (this.bot.isDebugMode()) {
            this.bot.telemetry.addData("Yeeter", "Initialized");
        }
    }

//    private void setActive_old(boolean active) {
//        this.active = active;
//
//        this.leftMotor.setPower(active ? this.rotationPower : 0);
//        this.rightMotor.setPower(active ? -this.rotationPower : 0);
//    }
//
//    private void setActiveLess_old(boolean activeLess) {
//        this.activeLess = activeLess;
//
//        this.leftMotor.setPower(activeLess ? this.rotationPower * 0.75 : 0);
//        this.rightMotor.setPower(activeLess ? -this.rotationPower * 0.75 : 0);
//    }
//
//    public void activate_old() {
//        this.setActive(true);
//    }
//    public void activateLess_old() {
//        this.setActiveLess(true);
//    }
//
//    public void deactivate_old() {
//        if (this.active) {
//            this.leftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
//            this.rightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
//
//            this.setActive(false);
//        }
//    }
//
//    public void brake_old() {
//        if (this.active) {
//            this.leftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//            this.rightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//
//            this.setActive(false);
//        }
//    }

    private void stop() {
        this.leftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        this.rightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        this.setPower(0.0);
    }

    private void brake() {
        this.leftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        this.rightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        this.setPower(0.0);
    }

    public void setPower(double power) {
        this.leftMotor.setPower(power);
        this.rightMotor.setPower(-power);
    }

    public void setMode(YeeterMode mode) {
        if (mode == this.lastMode) {
            return;
        }

        switch (mode) {
            case STOP:
                this.stop();
                break;

            case BRAKE:
                this.brake();
                break;

            case LESS:
                this.setPower(this.rotationPowerLess);
                break;

            case FULL:
                this.setPower(this.rotationPowerFull);
                break;

            case AUTO:
                this.setPower(this.rotationPowerAuto);
                break;
        }

        this.lastMode = mode;
    }
}
