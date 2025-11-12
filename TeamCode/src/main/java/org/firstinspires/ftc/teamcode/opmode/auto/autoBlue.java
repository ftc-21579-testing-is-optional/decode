package org.firstinspires.ftc.teamcode.opmode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.common.bot.Bot;
import org.firstinspires.ftc.teamcode.common.hardware.Yeeter;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;

import java.util.List;

@Autonomous(name = "autoBlue")
public class autoBlue extends LinearOpMode {
    Bot bot;



    ElapsedTime time;

    @Override
    public void runOpMode() throws InterruptedException {
        this.bot = new Bot(this.hardwareMap, this.telemetry);
        this.bot.initSubSystems();

        this.time = new ElapsedTime();

        this.waitForStart();

        bot.getYeeter().activate();
        bot.getScooper().setState(0);

        while (this.opModeIsActive()) {
            //List<AprilTagDetection> detections = this.bot.getAprilTagDetections();

            if (time.seconds() <= 3) {
                bot.setDrivePower(0.5, 0, 0);
            } else {
                bot.setDrivePower(0, 0, 0);
            }

            if (time.seconds() >= 3) {
                bot.getScooper().setState(1);

                bot.setCarouselDirection(1);

                bot.setCarouselDirection(0);

                bot.getScooper().setState(2);
            }



            this.telemetry.update();

            sleep(10);
            idle();
        }
    }
}
