package org.firstinspires.ftc.teamcode.opmode.auto;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.common.bot.Bot;
import org.firstinspires.ftc.teamcode.common.hardware.ScooperState;

public class AutoBase extends LinearOpMode {
    private Bot bot;
    ElapsedTime time;

    @Override
    public void runOpMode() throws InterruptedException {
        this.bot = new Bot(this.hardwareMap, this.telemetry);
        this.bot.initSubSystems();

        this.waitForStart();

        this.time = new ElapsedTime();

        bot.activateYeeter();
        bot.setIntakeDirection(1);

        while (this.opModeIsActive()) {
            if (time.seconds() < 0.5) {
                bot.setDrivePower(0.5, 0, 0);
            } else {
                bot.setDrivePower(0, 0, 0);
            }

            // START 1 shoot cycle
            if (time.seconds() > 2 && time.seconds() < 4) {
                bot.getScooper().setPos(0.25);
            }

            if (time.seconds() > 4 && time.seconds() < 5) {
                bot.setCarouselDirection(-1);
            }

            if (time.seconds() > 5 && time.seconds() < 8) {
                bot.setCarouselDirection(0);
            }

            if (time.seconds() > 8 && time.seconds() < 10) {
                bot.getScooper().setPos(1.0);
            }
            // END 1 shoot cycle

            // START 1 shoot cycle
            if (time.seconds() > 10 && time.seconds() < 12) {
                bot.getScooper().setPos(0.25);
            }

            if (time.seconds() > 12 && time.seconds() < 13) {
                bot.setCarouselDirection(-1);
            }

            if (time.seconds() > 13 && time.seconds() < 16) {
                bot.setCarouselDirection(0);
            }

            if (time.seconds() > 16 && time.seconds() < 18) {
                bot.getScooper().setPos(1.0);
            }
            // END 1 shoot cycle

            // START 1 shoot cycle
            if (time.seconds() > 20 && time.seconds() < 22) {
                bot.getScooper().setPos(0.25);
            }

            if (time.seconds() > 22 && time.seconds() < 23) {
                bot.setCarouselDirection(-1);
            }

            if (time.seconds() > 23 && time.seconds() < 26) {
                bot.setCarouselDirection(0);
            }

            if (time.seconds() > 26 && time.seconds() < 28) {
                bot.getScooper().setPos(1.0);
            }
            // END 1 shoot cycle

            if (time.seconds() > 28 && time.seconds() < 29) {
                bot.setDrivePower(0.5, 0, 0);
            } else {
                bot.setDrivePower(0, 0, 0);
            }

            this.telemetry.update();

            sleep(10);
            idle();
        }
    }
}
