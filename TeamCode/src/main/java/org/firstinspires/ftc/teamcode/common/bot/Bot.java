package org.firstinspires.ftc.teamcode.common.bot;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.common.config.Config;
import org.firstinspires.ftc.teamcode.common.game.ArtifactColor;
import org.firstinspires.ftc.teamcode.common.hardware.Carousel;
import org.firstinspires.ftc.teamcode.common.hardware.CarouselDirection;
import org.firstinspires.ftc.teamcode.common.hardware.ColorSensor;
import org.firstinspires.ftc.teamcode.common.hardware.Drive;
import org.firstinspires.ftc.teamcode.common.hardware.Intake;
import org.firstinspires.ftc.teamcode.common.hardware.IntakeDirection;
import org.firstinspires.ftc.teamcode.common.hardware.Scooper;
import org.firstinspires.ftc.teamcode.common.hardware.ScooperState;
import org.firstinspires.ftc.teamcode.common.hardware.Vision;
import org.firstinspires.ftc.teamcode.common.hardware.Yeeter;
import org.firstinspires.ftc.teamcode.common.hardware.YeeterMode;
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
        this.carousel = new Carousel(this, Config.CAROUSEL_ROTATION_POWER);
        this.intake = new Intake(this, Config.INTAKE_ROTATION_POWER);
        this.scooper = new Scooper(this);
        this.yeeter = new Yeeter(this, Config.YEETER_ROTATION_POWER_FULL, Config.YEETER_ROTATION_POWER_LESS);

        this.state = BotState.INIT;
        this.mode = BotMode.RUN;
    }

    public void initSubSystems() {
        this.drive.init();
        this.vision.init();
        this.colorSensor.init();
        this.carousel.init();
        this.intake.init();
        this.scooper.init();
        this.yeeter.init();
    }

    public void initVisionSystemsDebug() {
        this.vision.init();
    }

    public void initColorSensorDebug() {
        this.colorSensor.init();
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

    public void setCarouselDirection(CarouselDirection direction) {
        this.carousel.setRotationDirection(direction);
    }

    public void setIntakeDirection(IntakeDirection direction) {
        this.intake.setRotationDirection(direction);
    }

    public void setScooperState(ScooperState state) {
        this.scooper.setState(state);
    }

    public void setYeeterMode(YeeterMode mode) {
        this.yeeter.setMode(mode);
    }

    public List<AprilTagDetection> getAprilTagDetections() {
        return this.vision.getAprilTagDetections();
    }

    public ArtifactColor classifyArtifact() {
        return this.colorSensor.classify();
    }


    public void visionTest() {
        this.vision.telemetryAprilTag();
        this.vision.telemetryBallDetector();
    }

    public void colorSensorTest() {
        ArtifactColor color = this.colorSensor.classify();
        this.telemetry.addData("Classification (Bot)", color);
    }

//    public Scooper getScooper() {
//        return this.scooper;
//    }
//    public Yeeter getYeeter() {
//        return this.yeeter;
//    }
//
//    public void activateYeeter() {
//        this.yeeter.activate();
//    }

}
