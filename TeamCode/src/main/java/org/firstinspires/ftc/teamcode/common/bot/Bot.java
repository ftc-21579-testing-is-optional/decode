package org.firstinspires.ftc.teamcode.common.bot;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.common.game.ArtifactColor;
import org.firstinspires.ftc.teamcode.common.hardware.Carousel;
import org.firstinspires.ftc.teamcode.common.hardware.ColorSensor;
import org.firstinspires.ftc.teamcode.common.hardware.Drive;
import org.firstinspires.ftc.teamcode.common.hardware.Intake;
import org.firstinspires.ftc.teamcode.common.hardware.Scooper;
import org.firstinspires.ftc.teamcode.common.hardware.Vision;
import org.firstinspires.ftc.teamcode.common.hardware.Yeeter;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;

import java.util.List;

public class Bot {
    public final HardwareMap hardwareMap;
    public final Telemetry telemetry;

    private final Drive drive;
    private final Vision vision;
    private final ColorSensor colorSensor;
    private final Carousel carousel;
    private final Intake intake;
    private final Scooper scooper;
    private final Yeeter yeeter;

    private BotState state;
    private BotMode mode;

    public Bot(HardwareMap hMap, Telemetry telemetry) {
        this.hardwareMap = hMap;
        this.telemetry = telemetry;

        this.drive = new Drive(this);
        this.vision = new Vision(this);
        this.colorSensor = new ColorSensor(this);
        this.carousel = new Carousel(this, 1.00);
        this.intake = new Intake(this, 1.00);
        this.scooper = new Scooper(this);
        this.yeeter = new Yeeter(this, 1.0);

        this.state = BotState.INIT;
        this.mode = BotMode.RUN;
    }

    public void initSubSystems() {
        this.drive.init();
//        this.vision.init();
//        this.colorSensor.init();
        this.carousel.init();
        this.intake.init();
        this.scooper.init();
        this.yeeter.init();
    }

    public void setDebug() {
        this.mode = BotMode.DEBUG;
    }

    public boolean isDebugMode() {
        return this.mode == BotMode.DEBUG;
    }

    public BotMode getMode() {
        return this.mode;
    }

    public void setState(BotState state) {
        this.state = state;
    }

    public BotState getState() {
        return this.state;
    }

    public void setDrivePower(double axial, double lateral, double yaw) {
        this.drive.setPower(axial, lateral, yaw);
    }

    public void setCarouselDirection(int direction) {
        this.carousel.setRotationDirection(direction);
    }

    public void setIntakeDirection(int direction) {
        this.intake.setRotationDirection(direction);
    }

    public List<AprilTagDetection> getAprilTagDetections() {
        return this.vision.getAprilTagDetections();
    }

    public void visionTest() {
        this.vision.telemetryAprilTag();
        this.vision.telemetryBallDetector();
    }

    public void colorSensorTest() {
        ArtifactColor color = this.colorSensor.classify();
        this.telemetry.addData("Classification (Bot)", color);
    }

    public Scooper getScooper() {
        return this.scooper;
    }
    public Yeeter getYeeter() {
        return this.yeeter;
    }

}
