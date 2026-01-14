package org.firstinspires.ftc.teamcode.opmode.test;

import com.bylazar.telemetry.JoinedTelemetry;
import com.bylazar.telemetry.PanelsTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.common.hardware.ControlMapping;

@TeleOp(name = "SimpleYeetTest", group = "Test")
public class SimpleYeetTest extends LinearOpMode {
    private DcMotor leftMotor;
    private DcMotor rightMotor;

    @Override
    public void runOpMode() throws InterruptedException {
        JoinedTelemetry telem = new JoinedTelemetry(PanelsTelemetry.INSTANCE.getFtcTelemetry(), this.telemetry);

        this.leftMotor = hardwareMap.get(DcMotor.class, "motor0");
        this.rightMotor = hardwareMap.get(DcMotor.class, "motor1");

        ControlMapping controls = new ControlMapping(this.gamepad1);

        this.waitForStart();

        while (this.opModeIsActive()) {
            double power = controls.getMotionAxial();

            if (this.gamepad1.circle) {
                power = 1.0;
            }
            if (this.gamepad1.square) {
                power = -1.0;
            }
            if (this.gamepad1.triangle) {
                power = 0.0;
            }

            telem.addData("power", power);

            this.leftMotor.setPower(power);
            this.rightMotor.setPower(-power);

            telem.update();
        }
    }
}
