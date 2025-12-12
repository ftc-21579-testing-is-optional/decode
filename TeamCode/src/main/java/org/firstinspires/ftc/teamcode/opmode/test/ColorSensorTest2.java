package org.firstinspires.ftc.teamcode.opmode.test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.common.bot.Bot;

@TeleOp(name = "ColorSensorTest2", group = "Test")
public class ColorSensorTest2 extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        Bot bot = new Bot(hardwareMap, telemetry);
        bot.initColorSensorDebug();
        bot.setDebug();

        this.waitForStart();

        while (this.opModeIsActive()) {
            bot.colorSensorTest();

            telemetry.update();

            sleep(10);
            idle();
        }
    }
}
