package org.firstinspires.ftc.teamcode.opmode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.common.Bot;
import org.firstinspires.ftc.teamcode.common.hardware.Carousel;

@TeleOp(name = "CarouselTest")
public class CarouselTest extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        Bot bot = new Bot(hardwareMap, telemetry);
        Carousel carousel = new Carousel(bot);

        DcMotor carouselMotor = hardwareMap.get(DcMotor.class, "motor0");

        waitForStart();

        int i = 0;
        int fullRotation = 538;

        while (opModeIsActive()) {
            int position = carouselMotor.getCurrentPosition();
            int target = carouselMotor.getTargetPosition();

            telemetry.addData("position", position);
            telemetry.addData("target", target);
            telemetry.update();

            if (gamepad1.cross) {
                carouselMotor.setTargetPosition(0);
                i = 0;

                carouselMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                carouselMotor.setPower(0.5);

                while (carouselMotor.isBusy()) {}

                carouselMotor.setPower(0);
            }
            else if (gamepad1.circle) {
                i += 1;
                carouselMotor.setTargetPosition(fullRotation * i * 2);

                carouselMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                carouselMotor.setPower(0.5);

                while (carouselMotor.isBusy()) {}

                carouselMotor.setPower(0);
            }
            else if (gamepad1.square) {
                i -= 1;
                carouselMotor.setTargetPosition(fullRotation * i * 2);

                carouselMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                carouselMotor.setPower(0.5);

                while (carouselMotor.isBusy()) {}

                carouselMotor.setPower(0);
            }

            sleep(10);
            idle();
        }
    }
}
