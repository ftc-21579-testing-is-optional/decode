package org.firstinspires.ftc.teamcode.common.bot;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;
import org.firstinspires.ftc.teamcode.common.config.Config;
import org.firstinspires.ftc.teamcode.common.game.ArtifactColor;
import org.firstinspires.ftc.teamcode.common.hardware.AssistIntake;
import org.firstinspires.ftc.teamcode.common.hardware.Carousel;
import org.firstinspires.ftc.teamcode.common.hardware.CarouselDirection;
import org.firstinspires.ftc.teamcode.common.hardware.ColorSensor;
import org.firstinspires.ftc.teamcode.common.hardware.Drive;
import org.firstinspires.ftc.teamcode.common.hardware.CarWashIntake;
import org.firstinspires.ftc.teamcode.common.hardware.DrumIntake;
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
    private final CarWashIntake carWashIntake;
    private final DrumIntake drumIntake;
    private final AssistIntake assistIntake;
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
        this.carWashIntake = new CarWashIntake(this, Config.INTAKE_ROTATION_POWER);
        this.drumIntake = new DrumIntake(this, Config.INTAKE_ROTATION_POWER);
        this.assistIntake = new AssistIntake(this, Config.ASSIST_INTAKE_ROTATION_POWER);
        this.scooper = new Scooper(this);
        this.yeeter = new Yeeter(
                this,
                Config.YEETER_ROTATION_POWER_FULL,
                Config.YEETER_ROTATION_POWER_MEDIUM,
                Config.YEETER_ROTATION_POWER_LESS,
                Config.YEETER_ROTATION_POWER_AUTO,
                Config.YEETER_ROTATION_POWER_AUTO_LESS,
                Config.YEETER_ROTATION_POWER_AUTO_FAR
        );

        this.state = BotState.INIT;
        this.mode = BotMode.RUN;
    }

    public void initSubSystems() {
        this.drive.init();
        this.vision.init();
//        this.colorSensor.init();
        this.carousel.init();
//        this.carWashIntake.init();
        this.drumIntake.init();
        this.assistIntake.init();
        this.scooper.init();
        this.yeeter.init();
    }

    public void initVisionSystemsDebug() {
        this.vision.init();
    }

    public void initColorSensorDebug() {
        this.colorSensor.init();
    }

    public void initMotionSystemsDebug() {
        this.drive.init();
        this.carousel.init();
        this.drumIntake.init();
        this.assistIntake.init();
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

    public void setDrivePowerField(double x, double y, double robotYaw, double turnYaw) {
        double cos = Math.cos(Math.toRadians(robotYaw));
        double sin = Math.sin(Math.toRadians(robotYaw));

        double axial = y * cos - x * sin;
        double lateral = y * sin + x * cos;

        this.drive.setPower(axial, lateral * Config.AUTO_FIELD_LATERAL_CORRECTION, turnYaw - (lateral * Config.AUTO_FIELD_YAW_CORRECTION));
    }

    public void setCarouselDirection(CarouselDirection direction) {
        this.carousel.setRotationDirection(direction);
    }

    public void setCarouselDirection(int direction) {
        this.carousel.setRotationDirection(direction);
    }

    public void setCarWashIntakeDirection(IntakeDirection direction) {
        this.carWashIntake.setRotationDirection(direction);
    }

    public void setDrumIntakeDirection(IntakeDirection direction) {
        this.drumIntake.setRotationDirection(direction);
    }

    public void setAssistIntakeDirection(IntakeDirection direction) {
        this.assistIntake.setRotationDirection(direction);
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

    public Pose3D getRobotPose() {
        return this.vision.getRobotPose();
    }

    public ArtifactColor classifyArtifact() {
        return this.colorSensor.classify();
    }

    public void visionTest() {
        this.vision.telemetryRobotPose();
        this.vision.telemetryAprilTag();
//        this.vision.telemetryBallDetector();
    }

    public void colorSensorTest() {
        ArtifactColor color = this.colorSensor.classify();
        this.telemetry.addData("Classification (Bot)", color);
    }

    public boolean isThereABall() {
        float[] colors = this.colorSensor.getColorRGB();

        float totalColor = 0;

        // 0.00x
        for (float color : colors) {
            totalColor += color;
        }

        telemetry.addData("totalColor", totalColor);

        return totalColor > 0.003;
    }

    public void setCarouselPower(double power) {
        this.carousel.setRotationPower(power);
    }
}
