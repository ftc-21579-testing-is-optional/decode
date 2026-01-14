package org.firstinspires.ftc.teamcode.opmode.test;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;

@TeleOp(name = "LimelightTest", group = "Test")
public class LimelightTest extends LinearOpMode {
    private Limelight3A limelight;

    @Override
    public void runOpMode() throws InterruptedException {
        this.limelight = this.hardwareMap.get(Limelight3A.class, "limelight");
        this.limelight.setPollRateHz(100);
        this.limelight.start();
        this.limelight.pipelineSwitch(0);

        this.waitForStart();

        while (this.opModeIsActive()) {
            LLResult result = limelight.getLatestResult();

            if (result != null && result.isValid()) {
                Pose3D pose = result.getBotpose();

                if (pose != null) {
                    this.telemetry.addLine(String.format("dXYZ %6.1f %6.1f %6.1f  (cm)",
                            pose.getPosition().x * 100,
                            pose.getPosition().y * 100,
                            pose.getPosition().z * 100
                    ));
                    this.telemetry.addLine(String.format("dPRY %6.1f %6.1f %6.1f  (deg)",
                            pose.getOrientation().getPitch(),
                            pose.getOrientation().getRoll(),
                            pose.getOrientation().getYaw()
                    ));
                }
            }

            this.telemetry.update();
        }
    }
}
