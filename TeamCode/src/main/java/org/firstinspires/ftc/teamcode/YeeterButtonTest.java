package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "YeeterButtonTest")
public class YeeterButtonTest extends LinearOpMode {
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

        boolean crossLast = false, crossCurr = false;
        boolean squareLast = false, squareCurr = false;
        boolean circleLast = false, circleCurr = false;

        // Get motor power from left stick
        double power = 0.0;

        // Ramp motor speeds till stop pressed.
        while (opModeIsActive()) {
            // Bat.- voltage => 13.58

            // Cross button is press => stop all motors
            crossLast = crossCurr;
            crossCurr = gamepad1.cross;

            if (crossCurr && !crossLast) {
                power = 0.0;
            }

            // Square button is press => power to -1.0
            squareLast = squareCurr;
            squareCurr = gamepad1.square;

            if (squareCurr && !squareLast) {
                power = 1.0;
            }

            // Circle button is press => power to 1.0
            circleLast = circleCurr;
            circleCurr = gamepad1.circle;

            if (circleCurr && !circleLast) {
                power = -1.0;
            }

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
