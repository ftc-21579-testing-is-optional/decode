package org.firstinspires.ftc.teamcode.common.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.common.bot.Bot;

public class Carousel {
    private final Bot bot;

    private double rotationPower;
    private DcMotor carouselMotor;

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
}
