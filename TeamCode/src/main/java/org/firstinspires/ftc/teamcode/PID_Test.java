package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "PID_Test")
public class PID_Test extends LinearOpMode {
    DcMotor motor0;
    DcMotor motor1;
    DcMotor motor2;

    @Override
    public void runOpMode() {
        ControlMapping controls = new ControlMapping(gamepad1);

        motor0 = hardwareMap.get(DcMotor.class, "motor0");
        motor1 = hardwareMap.get(DcMotor.class, "motor1");
        motor2 = hardwareMap.get(DcMotor.class, "motor2");

        waitForStart();

        float drivePower;
        float pid_p;

        while (opModeIsActive()) {
            drivePower = controls.getDrivePower();
            pid_p = controls.getPValue();

            motor0.setPower(drivePower);
            motor1.setPower(drivePower);

            motor2.setPower(drivePower * pid_p);

            telemetry.addData("drivePower", drivePower);
            telemetry.addData("pid_d", pid_p);
            telemetry.update();

            sleep(10);
            idle();
        }
    }
}