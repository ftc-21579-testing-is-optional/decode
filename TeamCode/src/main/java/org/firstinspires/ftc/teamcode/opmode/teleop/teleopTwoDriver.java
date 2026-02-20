package org.firstinspires.ftc.teamcode.opmode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.common.bot.Bot;
import org.firstinspires.ftc.teamcode.common.hardware.ControlMapping;
import org.firstinspires.ftc.teamcode.common.hardware.ControlMappingTwoDriver;
import org.firstinspires.ftc.teamcode.common.hardware.IntakeDirection;
import org.firstinspires.ftc.teamcode.common.hardware.ScooperState;
import org.firstinspires.ftc.teamcode.common.hardware.YeeterMode;

@TeleOp(name = "teleop two driver")
public class teleopTwoDriver extends LinearOpMode {

    private Bot bot;

    @Override
    public void runOpMode() throws InterruptedException {
        bot = new Bot(this.hardwareMap, this.telemetry);
        bot.initSubSystems();
        bot.setDebug();

        ControlMappingTwoDriver controls = new ControlMappingTwoDriver(this.gamepad1, this.gamepad2);

        this.waitForStart();

        while (opModeIsActive()) {
            // Drive control
            double axial = controls.getMotionAxial();
            double lateral = controls.getMotionLateral();
            double yaw = controls.getMotionYaw();

            bot.setDrivePower(axial, lateral, yaw);

            // Carousel and intake
            bot.setCarouselDirection(controls.getCarouselDirection());

            // INTAKE CONTROL
            IntakeDirection intakeDirection = controls.getIntakeToggleState();
            bot.setDrumIntakeDirection(intakeDirection);
            bot.setAssistIntakeDirection(intakeDirection);

            // SCOOPER CONTROL
            ScooperState scooperState = controls.getScooperState();
            bot.setScooperState(scooperState);

            // YEETER CONTROL
            YeeterMode yeeterMode = controls.getYeeterMode();
            bot.setYeeterMode(yeeterMode);

            // Telemetry
            telemetry.update();
            telemetry.addData("Power", "power");

            // Loop delay
            sleep(10);
            idle();
        }
    }
}