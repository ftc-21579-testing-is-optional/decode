package org.firstinspires.ftc.teamcode.opmode.test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;
import org.firstinspires.ftc.teamcode.common.bot.Bot;
import org.firstinspires.ftc.teamcode.common.hardware.ScooperState;

@Autonomous(name = "DriveTestAuto2", group = "Test")
public class DriveTestAuto2 extends LinearOpMode {
    @Override
    public void runOpMode() {
        Bot bot = new Bot(hardwareMap, telemetry);
        bot.initSubSystems();
        bot.setDebug();

        waitForStart();

        ElapsedTime time = new ElapsedTime();

        int yaw = 180;

        while (opModeIsActive()) {
            if (time.seconds() > 0 && time.seconds() < 2) {
                bot.setDrivePowerField(0.25, 0, yaw, 0);
            }
            if (time.seconds() > 2 && time.seconds() < 4) {
                bot.setDrivePowerField(0, 0.25, yaw, 0);
            }
            if (time.seconds() > 4 && time.seconds() < 6) {
                bot.setDrivePowerField(-0.25, 0, yaw, 0);
            }
            if (time.seconds() > 6 && time.seconds() < 8) {
                bot.setDrivePowerField(0, -0.25, yaw, 0);
            }
            if (time.seconds() > 8) {
                bot.setDrivePowerField(0, 0, yaw, 0);
            }

            telemetry.update();

            sleep(20);
            idle();
        }
    }
}
