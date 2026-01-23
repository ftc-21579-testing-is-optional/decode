package org.firstinspires.ftc.teamcode.opmode.test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.common.bot.Bot;

@Autonomous(name = "autoRed", group = "Test")
@Disabled
public class testAutoRed extends LinearOpMode {
    Bot bot;

    ElapsedTime time;

    @Override
    public void runOpMode() throws InterruptedException {
        this.bot = new Bot(this.hardwareMap, this.telemetry);
        this.bot.initSubSystems();

        this.time = new ElapsedTime();

        this.waitForStart();

        while (this.opModeIsActive()) {
            //List<AprilTagDetection> detections = this.bot.getAprilTagDetections();


            if (time.seconds() <= 3) {
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
