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

        this.time = new ElapsedTime();

        this.waitForStart();

        bot.activateYeeter();
        bot.getScooper().setState(0);

        while (this.opModeIsActive()) {
            if (time.seconds() < 2) {
                bot.setDrivePower(0.5, 0, 0);
            } else {
                bot.setDrivePower(0, 0, 0);
            }

            // START 1 shoot cycle
            if (time.seconds() > 2 && time.seconds() < 4) {
                bot.getScooper().setState(ScooperState.CATCH.ordinal());
            }

            if (time.seconds() > 4 && time.seconds() < 6) {
                bot.setCarouselDirection(1);
            }

            if (time.seconds() > 6 && time.seconds() < 8) {
                bot.setCarouselDirection(0);
            }

            if (time.seconds() > 8 && time.seconds() < 10) {
                bot.getScooper().setState(ScooperState.YEET.ordinal());
            }
            // END 1 shoot cycle

            // START 1 shoot cycle
            if (time.seconds() > 12 && time.seconds() < 14) {
                bot.getScooper().setState(ScooperState.CATCH.ordinal());
            }

            if (time.seconds() > 14 && time.seconds() < 16) {
                bot.setCarouselDirection(1);
            }

            if (time.seconds() > 16 && time.seconds() < 18) {
                bot.setCarouselDirection(0);
            }

            if (time.seconds() > 18 && time.seconds() < 20) {
                bot.getScooper().setState(ScooperState.YEET.ordinal());
            }
            // END 1 shoot cycle

            // START 1 shoot cycle
            if (time.seconds() > 20 && time.seconds() < 22) {
                bot.getScooper().setState(ScooperState.CATCH.ordinal());
            }

            if (time.seconds() > 22 && time.seconds() < 24) {
                bot.setCarouselDirection(1);
            }

            if (time.seconds() > 24 && time.seconds() < 26) {
                bot.setCarouselDirection(0);
            }

            if (time.seconds() > 26 && time.seconds() < 28) {
                bot.getScooper().setState(ScooperState.YEET.ordinal());
            }
            // END 1 shoot cycle

            this.telemetry.update();

            sleep(10);
            idle();
        }
    }
}
