package org.firstinspires.ftc.teamcode.opmode.test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.common.bot.Bot;
import org.firstinspires.ftc.teamcode.common.hardware.ControlMapping;
import org.firstinspires.ftc.teamcode.common.hardware.IntakeDirection;
import org.firstinspires.ftc.teamcode.common.hardware.ScooperState;
import org.firstinspires.ftc.teamcode.common.hardware.YeeterMode;

@TeleOp(name = "YeetTest")
public class YeetTest extends LinearOpMode {

    private Bot bot;
    private DcMotor leftMotor;
    private DcMotor rightMotor;

    double power = 1;

    @Override
    public void runOpMode() throws InterruptedException {
        bot = new Bot(this.hardwareMap, this.telemetry);
        bot.initSubSystems();
        bot.setDebug();

        this.leftMotor = this.bot.hardwareMap.get(DcMotor.class, "motor5");
        this.rightMotor = this.bot.hardwareMap.get(DcMotor.class, "motor6");

        if (this.bot.isDebugMode()) {
            this.bot.telemetry.addData("Yeeter", "Initialized");
        }

        ControlMapping controls = new ControlMapping(gamepad1);

        this.waitForStart();

        double scooperPos = 0.0;

        boolean prevLeft = false;
        boolean prevRight = false;

        while (opModeIsActive()) {
            // Drive control
            double axial = controls.getMotionAxial();
            double lateral = controls.getMotionLateral();
            double yaw = controls.getMotionYaw();
            bot.setDrivePower(axial, lateral, yaw);

            // Carousel and intake
            bot.setCarouselDirection(controls.getCarouselDirection());

            if (controls.getIntakeToggleState()) {
                bot.setIntakeDirection(IntakeDirection.IN); // turn intake on
            } else {
                bot.setIntakeDirection(IntakeDirection.STOP); // turn intake off
            }

            // SCOOPER CONTROL
            ScooperState scooperState = controls.getScooperState();
            bot.setScooperState(scooperState);

            if (gamepad1.dpad_right && !prevRight) {
                power += 0.025;
                leftMotor.setPower(power);
                rightMotor.setPower(-power);
            }
            if (gamepad1.dpad_left && !prevLeft) {
                power -= 0.025;
                leftMotor.setPower(power);
                rightMotor.setPower(-power);
            }
            if (gamepad1.dpad_up){
                leftMotor.setPower(0);
                rightMotor.setPower(0);
            }

            if (power >= 1) {
                power = 1;
            }

            prevRight = gamepad1.dpad_right;
            prevLeft = gamepad1.dpad_left;

            // Telemetry
            telemetry.update();
            telemetry.addData("Power", power);

            // Loop delay
            sleep(10);
            idle();
        }
    }
}