package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp
public class Drive extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor frontLeftDrive = null;
    private DcMotor backLeftDrive = null;
    private DcMotor frontRightDrive = null;
    private DcMotor backRightDrive = null;
    private DcMotor spinner = null;
    private DcMotor yeeter1 = null;
    private DcMotor yeeter2 = null;
    private Servo pusher = null;
    private CRServo intake1 = null;
    private CRServo intake2 = null;

    @Override
    public void runOpMode() {

        frontLeftDrive = hardwareMap.get(DcMotor.class, "motor0");
        backLeftDrive = hardwareMap.get(DcMotor.class, "motor1");
        frontRightDrive = hardwareMap.get(DcMotor.class, "motor2");
        backRightDrive = hardwareMap.get(DcMotor.class, "motor3");

        spinner = hardwareMap.get(DcMotor.class, "expmotor0");
        yeeter1 = hardwareMap.get(DcMotor.class, "expmotor1");
        yeeter2 = hardwareMap.get(DcMotor.class, "expmotor2");

        pusher = hardwareMap.get(Servo.class, "servo0");
        intake1 = hardwareMap.get(CRServo.class, "servo1");
        intake2 = hardwareMap.get(CRServo.class, "servo2");

        frontLeftDrive.setDirection(DcMotor.Direction.FORWARD);
        backLeftDrive.setDirection(DcMotor.Direction.REVERSE);
        frontRightDrive.setDirection(DcMotor.Direction.REVERSE);
        backRightDrive.setDirection(DcMotor.Direction.FORWARD);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();
        runtime.reset();

        boolean Intake_on = false;

        while (opModeIsActive()) {

            double axial = -gamepad1.left_stick_y;
            double lateral = gamepad1.left_stick_x;
            double yaw = gamepad1.right_stick_x;

// Dead zone correction
            double DEADZONE = 0.15; // you can tweak this (0.05–0.15)
            if (Math.abs(axial) < DEADZONE) axial = 0;
            if (Math.abs(lateral) < DEADZONE) lateral = 0;
            if (Math.abs(yaw) < DEADZONE) yaw = 0;

            // Check if there is any joystick input
            if (Math.abs(axial) > 0.05 || Math.abs(lateral) > 0.05 || Math.abs(yaw) > 0.05) {
                // Normal drive control
                double frontLeftPower = axial + lateral + yaw;
                double frontRightPower = axial - lateral - yaw;
                double backLeftPower = axial - lateral + yaw;
                double backRightPower = axial + lateral - yaw;

                // Normalize powers so no value exceeds 1.0
                double max = Math.max(Math.abs(frontLeftPower), Math.abs(frontRightPower));
                max = Math.max(max, Math.abs(backLeftPower));
                max = Math.max(max, Math.abs(backRightPower));

                if (max > 1.0) {
                    frontLeftPower /= max;
                    frontRightPower /= max;
                    backLeftPower /= max;
                    backRightPower /= max;
                }

                // Apply power
                frontLeftDrive.setPower(frontLeftPower);
                frontRightDrive.setPower(frontRightPower);
                backLeftDrive.setPower(backLeftPower);
                backRightDrive.setPower(backRightPower);

            } else {
                // No input → stop all motors
                frontLeftDrive.setPower(0);
                frontRightDrive.setPower(0);
                backLeftDrive.setPower(0);
                backRightDrive.setPower(0);
            }


            //intake1.setPower(0);
            //intake2.setPower(0);
            //intake1.setDirection(CRServo.Direction.FORWARD);
            //intake2.setDirection(CRServo.Direction.REVERSE);


            if (gamepad1.square) {
                yeeter1.setPower(1.0);
                yeeter2.setPower(-1.0);

            } else if (gamepad1.circle) {
                yeeter1.setPower(-1.0);
                yeeter2.setPower(1.0);

            } else {
                yeeter1.setPower(0);
                yeeter2.setPower(0);
            }

            if (gamepad1.right_bumper) {
                spinner.setPower(-1.0);
            } else if (gamepad1.left_bumper){
                spinner.setPower(-1.0);
            } else {
                spinner.setPower(0);
            }

            if (gamepad1.dpad_down) {
                pusher.setPosition(0);

            } else if (gamepad1.dpad_right) {
                pusher.setPosition(0.5);

            } else if (gamepad1.dpad_up) {
                pusher.setPosition(1);
            }

            if (gamepad1.triangle) {
                Intake_on = !Intake_on;
            } if (Intake_on) {
                intake1.setPower(1.0);
                intake2.setPower(1.0);
            } if (!Intake_on) {
                intake1.setPower(0);
                intake2.setPower(0);
            }












            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("servoPos", pusher.getPosition());
            //telemetry.addData("Intake servo1 power", intake1.getPower());
            //telemetry.addData("Intake servo2 power", intake2.getPower());
            //telemetry.addData("Intake servo1 dir", intake1.getDirection());
            //telemetry.addData("Intake servo2 dir", intake2.getDirection());
            telemetry.addData("Joystick Axes", "axial=%.2f, lateral=%.2f, yaw=%.2f", axial, lateral, yaw);
            telemetry.addData("Left Stick X", gamepad1.left_stick_x);
            telemetry.update();
        }
    }
}