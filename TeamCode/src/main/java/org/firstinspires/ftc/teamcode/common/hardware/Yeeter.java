package org.firstinspires.ftc.teamcode.common.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.common.bot.Bot;

public class Yeeter {
    private final Bot bot;

    private double rotationPower;
    private DcMotor leftMotor;
    private DcMotor rightMotor;
    private boolean active;

    private boolean activeLess;

    public Yeeter(Bot bot, double rotationPower) {
        this.bot = bot;
        this.rotationPower = rotationPower;
    }

    public void init() {
        this.leftMotor = this.bot.hardwareMap.get(DcMotor.class, "motor5");
        this.rightMotor = this.bot.hardwareMap.get(DcMotor.class, "motor6");

        if (this.bot.isDebugMode()) {
            this.bot.telemetry.addData("Yeeter", "Initialized");
        }
    }

    private void setActive(boolean active) {
        this.active = active;

        this.leftMotor.setPower(active ? this.rotationPower : 0);
        this.rightMotor.setPower(active ? -this.rotationPower : 0);
    }

    private void setActiveLess(boolean activeLess) {
        this.activeLess = activeLess;

        this.leftMotor.setPower(activeLess ? this.rotationPower * 0.75 : 0);
        this.rightMotor.setPower(activeLess ? -this.rotationPower * 0.75 : 0);
    }

    public void activate() {
        this.setActive(true);
    }
    public void activateLess() {
        this.setActiveLess(true);
    }

    public void deactivate() {
        if (this.active) {
            this.leftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
            this.rightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

            this.setActive(false);
        }
    }

    public void brake() {
        if (this.active) {
            this.leftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            this.rightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

            this.setActive(false);
        }
    }



}
