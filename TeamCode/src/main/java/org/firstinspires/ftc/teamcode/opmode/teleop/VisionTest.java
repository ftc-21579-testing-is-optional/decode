package org.firstinspires.ftc.teamcode.opmode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.common.Bot;

@TeleOp(name = "VisionTest")
public class VisionTest extends LinearOpMode {
    @Override
    public void runOpMode() {
        Bot bot = new Bot(hardwareMap, telemetry);

        waitForStart();

        while (opModeIsActive()) {
            bot.aprilTagTest();

            telemetry.update();

            sleep(20);
            idle();
        }
    }
}
