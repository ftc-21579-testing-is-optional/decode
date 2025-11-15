package org.firstinspires.ftc.teamcode.opmode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.common.bot.Bot;
import org.firstinspires.ftc.teamcode.common.hardware.ControlMapping;

@TeleOp(name = "teleop")
public class teleop extends LinearOpMode {

    private Bot bot;
    private final float deadzone = 0.15f;
    private boolean yeeterActive = false;

    @Override
    public void runOpMode() throws InterruptedException {
        bot = new Bot(this.hardwareMap, this.telemetry);
        bot.initSubSystems();
        bot.setDebug();

        ControlMapping controls = new ControlMapping(gamepad1, deadzone);

        this.waitForStart();

        double scooperPos = 0.0;

        while (opModeIsActive()) {
            // Update input states
            controls.updateStatefulInput();

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
//            int scooperCommand = controls.getScooperToggleCommand();
//            if (scooperCommand != -1) {
//                bot.getScooper().setState(scooperCommand);
//            }

            if (gamepad1.square) {
                scooperPos -= (scooperPos - 0.01 > 0) ? 0.01 : 0.0;
            }

            if (gamepad1.triangle) {
                scooperPos += (scooperPos + 0.01 < 1.0) ? 0.01 : 0.0;
            }

            this.telemetry.addData("scooperPos", scooperPos);

            bot.getScooper().setPos(scooperPos);


            if (controls.getYeeterToggleState()) {
                bot.getYeeter().activate();   // motors on
            } else {
                bot.getYeeter().deactivate(); // motors off
            }

            // Telemetry
            telemetry.update();

            // Loop delay
            sleep(10);
            idle();
        }
    }
}