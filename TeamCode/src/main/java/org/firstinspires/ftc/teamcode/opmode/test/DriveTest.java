package org.firstinspires.ftc.teamcode.opmode.test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.common.bot.Bot;

@TeleOp(name = "DriveTest", group = "Test")
public class DriveTest extends LinearOpMode {
    @Override
    public void runOpMode() {
        Bot bot = new Bot(hardwareMap, telemetry);
        bot.setDebug();

        waitForStart();

        while (opModeIsActive()) {
            double axial   = -gamepad1.left_stick_y;
            double lateral =  Math.abs(gamepad1.left_stick_x) > 0.15 ? gamepad1.left_stick_x : 0;
            double yaw     =  gamepad1.right_stick_x;

            bot.setDrivePower(axial, lateral, yaw);

            sleep(20);
            idle();
        }
    }
}
