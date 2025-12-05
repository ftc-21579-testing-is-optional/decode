package org.firstinspires.ftc.teamcode.opmode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.common.bot.Bot;
import org.firstinspires.ftc.teamcode.common.hardware.ControlMapping;
import org.firstinspires.ftc.teamcode.common.hardware.ScooperState;
import org.firstinspires.ftc.teamcode.common.hardware.YeeterMode;

@TeleOp(name = "teleop")
public class teleop extends LinearOpMode {

    private Bot bot;

    @Override
    public void runOpMode() throws InterruptedException {
        bot = new Bot(this.hardwareMap, this.telemetry);
        bot.initSubSystems();
        bot.setDebug();

        ControlMapping controls = new ControlMapping(gamepad1);

        this.waitForStart();

        double scooperPos = 0.0;

        while (opModeIsActive()) {
            // Drive control
            double axial = controls.getMotionAxial();
            double lateral = controls.getMotionLateral();
            double yaw = controls.getMotionYaw();
            bot.setDrivePower(axial, lateral, yaw);

            // Carousel and intake
            bot.setCarouselDirection(controls.getCarouselDirection());

            if (controls.getIntakeToggleState()) {
                bot.setIntakeDirection(1); // turn intake on
            } else {
                bot.setIntakeDirection(0); // turn intake off
            }

            // SCOOPER CONTROL
            ScooperState scooperState = controls.getScooperState();
            bot.setScooperState(scooperState);

            // YEETER CONTROL
            YeeterMode yeeterMode = controls.getYeeterMode();
            bot.setYeeterMode(yeeterMode);

//            if (controls.getYeeterToggleState()) {
//                bot.getYeeter().activate();   // motors on
//            } else {
//                bot.getYeeter().deactivate(); // motors off
//            }
//
//            if (controls.getYeeterToggleStateLess()) {
//                bot.getYeeter().activateLess();   // motors on
//            } else {
//                bot.getYeeter().deactivate(); // motors off
//            }

            // Telemetry
            telemetry.update();

            // Loop delay
            sleep(10);
            idle();
        }
    }
}