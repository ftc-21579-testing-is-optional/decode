package org.firstinspires.ftc.teamcode.opmode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.common.bot.Bot;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;

import java.util.List;

@Autonomous(name = "auto")
public class auto extends LinearOpMode {
    Bot bot;

    ElapsedTime time;

    @Override
    public void runOpMode() throws InterruptedException {
        this.bot = new Bot(this.hardwareMap, this.telemetry);
        this.bot.initSubSystems();

        this.time = new ElapsedTime();

        this.waitForStart();

        while (this.opModeIsActive()) {
            List<AprilTagDetection> detections = this.bot.getAprilTagDetections();

            this.telemetry.update();

            sleep(10);
            idle();
        }
    }
}
