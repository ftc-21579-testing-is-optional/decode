package org.firstinspires.ftc.teamcode.opmode.test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.common.bot.Bot;
import org.firstinspires.ftc.teamcode.common.hardware.ControlMapping;
import org.firstinspires.ftc.teamcode.common.hardware.IntakeDirection;

@TeleOp(name = "DrumIntakeTest", group = "Test")
public class DrumIntakeTest extends LinearOpMode {
    private Bot bot;

    @Override
    public void runOpMode() throws InterruptedException {
        bot = new Bot(this.hardwareMap, this.telemetry);
        bot.initMotionSystemsDebug();
        bot.setDebug();

        ControlMapping controls = new ControlMapping(this.gamepad1);

        this.waitForStart();

        while (this.opModeIsActive()) {
            if (this.gamepad1.circle) {
                this.bot.setDrumIntakeDirection(IntakeDirection.IN);
            }
            if (this.gamepad1.square) {
                this.bot.setDrumIntakeDirection(IntakeDirection.OUT);
            }
            if (this.gamepad1.triangle) {
                this.bot.setDrumIntakeDirection(IntakeDirection.STOP);
            }

            this.telemetry.update();
        }
    }
}
