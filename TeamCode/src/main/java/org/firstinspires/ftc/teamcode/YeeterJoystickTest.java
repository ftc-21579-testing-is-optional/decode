package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "YeeterJoystickTest")
public class YeeterJoystickTest extends LinearOpMode {
    DcMotor motor1;
    DcMotor motor2;

    @Override
    public void runOpMode() {
        motor1 = hardwareMap.get(DcMotor.class, "motor0");
        motor2 = hardwareMap.get(DcMotor.class, "motor1");

        // Wait for the start button
        telemetry.addData(">", "Press Start to run Motors." );
        telemetry.update();
        waitForStart();

        // Ramp motor speeds till stop pressed.
        while (opModeIsActive()) {
            // Get motor power from left stick
            double power = gamepad1.left_stick_y;

            // Display the current value
            telemetry.addData("Motor Power", "%5.2f", power);
            telemetry.addData(">", "Press Stop to end test." );
            telemetry.update();

            // Set the motor to the new power and pause;
            motor1.setPower(-power);
            motor2.setPower(power);

            sleep(50);
            idle();
        }

        // Turn off motor and signal done;
        motor1.setPower(0);
        motor2.setPower(0);
        telemetry.addData(">", "Done");
        telemetry.update();
    }
}
