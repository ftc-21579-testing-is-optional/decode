package org.firstinspires.ftc.teamcode.opmode.test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.common.bot.Bot;
import org.firstinspires.ftc.teamcode.common.hardware.ControlMapping;
import org.firstinspires.ftc.teamcode.common.hardware.IntakeDirection;
import org.firstinspires.ftc.teamcode.common.hardware.ScooperState;
import org.firstinspires.ftc.teamcode.common.hardware.YeeterMode;

@TeleOp(name = "teleopYeeterTest")
public class teleopYeeterTest extends LinearOpMode {

    private Bot bot;

    @Override
    public void runOpMode() throws InterruptedException {
        bot = new Bot(this.hardwareMap, this.telemetry);
        bot.initSubSystems();
        bot.setDebug();

        ControlMapping controls = new ControlMapping(gamepad1);

        this.waitForStart();

        boolean prevLeft = false;
        boolean prevRight = false;
        double power = 0.0;

        while (opModeIsActive()) {
            // Drive control
            double axial = controls.getMotionAxial();
            double lateral = controls.getMotionLateral();
            double yaw = controls.getMotionYaw();

            bot.setDrivePower(axial, lateral, yaw);

            // Carousel and intake
            bot.setCarouselDirection(controls.getCarouselDirection());

            if (controls.getIntakeToggleState()) {
                bot.setDrumIntakeDirection(IntakeDirection.IN); // turn intake on
                bot.setAssistIntakeDirection(IntakeDirection.IN);
            } else {
                bot.setDrumIntakeDirection(IntakeDirection.STOP); // turn intake off
                bot.setAssistIntakeDirection(IntakeDirection.STOP);
            }

            // SCOOPER CONTROL
            ScooperState scooperState = controls.getScooperState();
            bot.setScooperState(scooperState);

            // YEETER CONTROL
            if (this.gamepad1.dpad_left && !prevLeft) {
                power += 0.1;
            }

            prevLeft = this.gamepad1.dpad_left;

            if (this.gamepad1.dpad_right && !prevRight) {
                power -= 0.1;
            }

            prevRight = this.gamepad1.dpad_right;

            bot.setYeeterPower(power);
//
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
            telemetry.addData("Power", power);
            telemetry.update();

            // Loop delay
            sleep(10);
            idle();
        }
    }
}