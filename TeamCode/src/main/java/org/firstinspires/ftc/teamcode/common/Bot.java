package org.firstinspires.ftc.teamcode.common;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.common.hardware.Carousel;
import org.firstinspires.ftc.teamcode.common.hardware.Drive;
import org.firstinspires.ftc.teamcode.common.hardware.Intake;
import org.firstinspires.ftc.teamcode.common.hardware.Vision;
import org.firstinspires.ftc.teamcode.common.hardware.Yeeter;

public class Bot {
    public final HardwareMap hardwareMap;
    public final Telemetry telemetry;
    private final Drive drive;
    private final Vision vision;
    private final Carousel carousel;
    private final Intake intake;
    private final Yeeter yeeter;

    public Bot(HardwareMap hMap, Telemetry telemetry) {
        this.hardwareMap = hMap;
        this.telemetry = telemetry;

        this.drive = new Drive(this);
        this.vision = new Vision(this);
        this.carousel = new Carousel(this);
        this.intake = new Intake(this);
        this.yeeter = new Yeeter(this);
    }

    public void visionTest() {
        this.vision.telemetryAprilTag();
        this.vision.telemetryBallDetector();
    }
}
