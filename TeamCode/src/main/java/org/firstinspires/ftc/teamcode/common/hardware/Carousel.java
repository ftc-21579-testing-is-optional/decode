package org.firstinspires.ftc.teamcode.common.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.common.bot.Bot;

public class Carousel {
    private final Bot bot;

    private double rotationPower;
    private double rotationPowerSlow;
    private DcMotor carouselMotor;

    private CarouselDirection lastDirection;

    public Carousel(Bot bot, double rotationPower, double rotationPowerSlow) {
        this.bot = bot;
        this.rotationPower = rotationPower;
        this.rotationPowerSlow = rotationPowerSlow;
    }

    public void init() {
        this.carouselMotor = this.bot.hardwareMap.get(DcMotor.class, "motor4");

        if (this.bot.isDebugMode()) {
            this.bot.telemetry.addData("Carousel", "Initialized");
        }
    }

    private void setPower(double power) {
        this.carouselMotor.setPower(power);
    }

//    public void setRotationDirection(int direction) {
//        this.carouselMotor.setPower(direction * this.rotationPower);
//    }

    public void setRotationDirection(CarouselDirection direction) {
        if (this.bot.isDebugMode()) {
            bot.telemetry.addData("carouselDirection", direction.name());
        }

        if (this.lastDirection == direction) {
            return;
        }

        switch (direction) {
            case STOP:
                this.setPower(0);
                break;

            case UP:
                this.setPower(this.rotationPower);
                break;

            case UP_SLOW:
                this.setPower(this.rotationPowerSlow);
                break;

            case DOWN:
                this.setPower(-this.rotationPower);
                break;

            case DOWN_SLOW:
                this.setPower(-this.rotationPowerSlow);
                break;
        }

        this.lastDirection = direction;
    }

//    public void setRotationPower(double power) {
//        this.rotationPower = power;
//    }
}
