package org.firstinspires.ftc.teamcode.opmode.test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;
import org.firstinspires.ftc.teamcode.common.bot.Bot;
import org.firstinspires.ftc.teamcode.common.hardware.ControlMapping;

@TeleOp(name = "DriveTestField", group = "Test")
public class DriveTestField extends LinearOpMode {
    @Override
    public void runOpMode() {
        Bot bot = new Bot(hardwareMap, telemetry);
        bot.initSubSystems();
        bot.setDebug();

        ControlMapping controls = new ControlMapping(gamepad1);

        waitForStart();

        while (opModeIsActive()) {
            Pose3D robotPose = bot.getRobotPose();

            double axial = controls.getMotionAxial();
            double lateral = controls.getMotionLateral();
            double yaw = controls.getMotionYaw();

            bot.setDrivePowerField(axial, lateral, robotPose.getOrientation().getYaw(AngleUnit.RADIANS), yaw);

            bot.visionTest();

            telemetry.update();

            sleep(20);
            idle();
        }
    }
}
