package org.firstinspires.ftc.teamcode.opmode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.common.bot.Bot;

@TeleOp(name = "ColorSensorTest2", group = "Test")
public class ColorSensorTest2 extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        Bot bot = new Bot(hardwareMap, telemetry);
        bot.setDebug();

        waitForStart();

        while (opModeIsActive()) {
            bot.colorSensorTest();
            telemetry.update();
        }
    }
}
