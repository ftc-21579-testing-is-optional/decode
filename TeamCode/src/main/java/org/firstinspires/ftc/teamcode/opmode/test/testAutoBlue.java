package org.firstinspires.ftc.teamcode.opmode.test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.common.bot.Bot;

@Autonomous(name = "autoBlue")
@Disabled
public class testAutoBlue extends LinearOpMode {
    Bot bot;

    ElapsedTime time;

    @Override
    public void runOpMode() throws InterruptedException {
        this.bot = new Bot(this.hardwareMap, this.telemetry);
        this.bot.initSubSystems();

        this.time = new ElapsedTime();

        this.waitForStart();

        //bot.getYeeter().activate();
        //bot.getScooper().setState(0);

        while (this.opModeIsActive()) {
            //List<AprilTagDetection> detections = this.bot.getAprilTagDetections();

            if (time.seconds() <= 3) {
                bot.setDrivePower(0.5, 0, 0);
            } else {
                bot.setDrivePower(0, 0, 0);
            }

            //if (time.seconds() >= 3) {
                //bot.getScooper().setState(1);

                //bot.setCarouselDirection(1);

                //bot.setCarouselDirection(0);

                //bot.getScooper().setState(2);
            //}



            this.telemetry.update();

            sleep(10);
            idle();
        }
    }
}
