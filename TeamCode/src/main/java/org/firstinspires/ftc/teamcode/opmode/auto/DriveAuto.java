package org.firstinspires.ftc.teamcode.opmode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.common.bot.Bot;

@Autonomous(name = "DriveAuto")
public class DriveAuto extends LinearOpMode {
    private Bot bot;
    ElapsedTime time;

    @Override
    public void runOpMode() throws InterruptedException {
        this.bot = new Bot(this.hardwareMap, this.telemetry);
        this.bot.initSubSystems();

        this.waitForStart();

        this.time = new ElapsedTime();

        while (this.opModeIsActive()) {
            if (time.seconds() < 5.0) {
                bot.setDrivePower(0.5, 0, 0);
            } else {
                bot.setDrivePower(0, 0, 0);
            }
        }
    }
}
