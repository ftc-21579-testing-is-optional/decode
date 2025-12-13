package org.firstinspires.ftc.teamcode.common.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.common.bot.Bot;

public class Carousel {
    private final Bot bot;

    private double rotationPower;
    private DcMotor carouselMotor;

    private CarouselDirection lastDirection;

    public Carousel(Bot bot, double rotationPower) {
        this.bot = bot;
        this.rotationPower = rotationPower;
    }

    public void init() {
        this.carouselMotor = this.bot.hardwareMap.get(DcMotor.class, "motor4");

        if (this.bot.isDebugMode()) {
            this.bot.telemetry.addData("Carousel", "Initialized");
        }
    }

    public void setRotationDirection(int direction) {
        this.carouselMotor.setPower(direction * this.rotationPower);
    }

    public void setRotationDirection(CarouselDirection direction) {
        bot.telemetry.addData("carouselDirection", direction.name());

        if (this.lastDirection == direction) {
            return;
        }

        switch (direction) {
            case STOP:
                this.setRotationDirection(0);
                break;

            case UP:
                this.setRotationDirection(1);
                break;

            case DOWN:
                this.setRotationDirection(-1);
                break;
        }

        this.lastDirection = direction;
    }

    public void setRotationPower(double power) {
        this.rotationPower = power;
    }
}
