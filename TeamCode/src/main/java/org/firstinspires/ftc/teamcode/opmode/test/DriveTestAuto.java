package org.firstinspires.ftc.teamcode.opmode.test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;
import org.firstinspires.ftc.teamcode.common.bot.Bot;
import org.firstinspires.ftc.teamcode.common.util.Clamp;

@Autonomous(name = "DriveTestAuto", group = "Test")
public class DriveTestAuto extends LinearOpMode {
    @Override
    public void runOpMode() {
        Bot bot = new Bot(hardwareMap, telemetry);
        bot.initSubSystems();
        bot.setDebug();

        waitForStart();

        Pose3D desiredPose = new Pose3D(
                new Position(DistanceUnit.INCH, 0, 0, 0, 0),
                new YawPitchRollAngles(AngleUnit.DEGREES, 45, 0, 0, 0)
        );

        double distThreshold = 1.5;
        double angleThreshold = 5;
        double speedMultiplier = 0.1;

        while (opModeIsActive()) {
            Pose3D robotPose = bot.getRobotPose();
            bot.visionTest();

            double dx = 0, dy = 0, dz = 0;
            double dpitch = 0, droll = 0, dyaw = 0;

            dx = desiredPose.getPosition().x - robotPose.getPosition().x;
            dy = desiredPose.getPosition().y - robotPose.getPosition().y;
            dz = desiredPose.getPosition().z - robotPose.getPosition().z; // dont care

            dpitch = desiredPose.getOrientation().getPitch() - robotPose.getOrientation().getPitch(); // dont care
            droll = desiredPose.getOrientation().getRoll() - robotPose.getOrientation().getPitch(); // dont care
            dyaw = desiredPose.getOrientation().getYaw() - robotPose.getOrientation().getYaw();

            telemetry.addLine(String.format("dXYZ %6.1f %6.1f %6.1f  (inch)",
                    dx,
                    dy,
                    dz));
            telemetry.addLine(String.format("dPRY %6.1f %6.1f %6.1f  (deg)",
                    dpitch,
                    droll,
                    dyaw));

            if (!(Math.abs(dx) < distThreshold && Math.abs(dy) < distThreshold && Math.abs(dyaw) < angleThreshold)) {
                bot.setDrivePowerField(Clamp.clamp(-1, 1, dx) * speedMultiplier, Clamp.clamp(-1, 1, dy) * speedMultiplier, robotPose.getOrientation().getYaw(AngleUnit.DEGREES), -Clamp.clamp(-1, 1, dyaw) * speedMultiplier);
            }
            else {
                bot.setDrivePower(0, 0, 0);
            }

            telemetry.update();

            sleep(20);
            idle();
        }
    }
}
