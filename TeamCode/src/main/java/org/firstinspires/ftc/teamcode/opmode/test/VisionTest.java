package org.firstinspires.ftc.teamcode.opmode.test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.common.bot.Bot;

@TeleOp(name = "VisionTest", group = "Test")
public class VisionTest extends LinearOpMode {
    @Override
    public void runOpMode() {
        Bot bot = new Bot(hardwareMap, telemetry);
        bot.setDebug();
        bot.initVisionSystemsDebug();

        this.waitForStart();

        while (this.opModeIsActive()) {
            bot.visionTest();

            telemetry.update();

            sleep(20);
            idle();
        }
    }
}
