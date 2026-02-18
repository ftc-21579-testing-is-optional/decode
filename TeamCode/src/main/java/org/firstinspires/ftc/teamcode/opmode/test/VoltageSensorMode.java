package org.firstinspires.ftc.teamcode.opmode.test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.VoltageSensor;

@TeleOp(name = "Voltage Sensor", group = "Test")
public class VoltageSensorMode extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        VoltageSensor voltageSensor = this.hardwareMap.get(VoltageSensor.class, "Control Hub");

        this.waitForStart();

        while (this.opModeIsActive()) {
            this.telemetry.addData("voltage", voltageSensor.getVoltage());

            this.telemetry.update();

            this.sleep(10);
            this.idle();
        }
    }
}
