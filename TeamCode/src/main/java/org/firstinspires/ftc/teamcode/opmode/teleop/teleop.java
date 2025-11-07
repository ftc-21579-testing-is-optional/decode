package org.firstinspires.ftc.teamcode.opmode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.common.bot.Bot;
import org.firstinspires.ftc.teamcode.common.hardware.ControlMapping;

@TeleOp(name = "teleop")
public class teleop extends LinearOpMode {
    Bot bot;

    float deadzone = 0.15f;

    @Override
    public void runOpMode() throws InterruptedException {
        this.bot = new Bot(this.hardwareMap, this.telemetry);
        this.bot.initSubSystems();
        this.bot.setDebug();

        ControlMapping controls = new ControlMapping(gamepad1, deadzone);

        this.waitForStart();

        while (this.opModeIsActive()) {
//            double axial   = -gamepad1.left_stick_y;
//            double lateral =  Math.abs(gamepad1.left_stick_x) > this.deadzone ? gamepad1.left_stick_x : 0;
//            double yaw     =  gamepad1.right_stick_x;

            controls.updateStatefulInput();

            double axial   = controls.getMotionAxial();
            double lateral = controls.getMotionLateral();
            double yaw     = controls.getMotionYaw();

            bot.setDrivePower(axial, lateral, yaw);
            bot.setCarouselDirection(controls.getCarouselDirection());
            bot.setIntakeDirection(controls.getIntakeDirection());

            this.telemetry.update();

            sleep(10);
            idle();
        }
    }
}
