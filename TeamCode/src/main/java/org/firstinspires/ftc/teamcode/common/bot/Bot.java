package org.firstinspires.ftc.teamcode.common.bot;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.common.game.ArtifactColor;
import org.firstinspires.ftc.teamcode.common.hardware.Carousel;
import org.firstinspires.ftc.teamcode.common.hardware.ColorSensor;
import org.firstinspires.ftc.teamcode.common.hardware.Drive;
import org.firstinspires.ftc.teamcode.common.hardware.Intake;
import org.firstinspires.ftc.teamcode.common.hardware.Vision;
import org.firstinspires.ftc.teamcode.common.hardware.Yeeter;

public class Bot {
    public final HardwareMap hardwareMap;
    public final Telemetry telemetry;
    private final Drive drive;
    private final Vision vision;
    private final ColorSensor colorSensor;
    private final Carousel carousel;
    private final Intake intake;
    private final Yeeter yeeter;

    private BotState state;
    private BotMode mode;

    public Bot(HardwareMap hMap, Telemetry telemetry) {
        this.hardwareMap = hMap;
        this.telemetry = telemetry;

        this.drive = new Drive(this);
        this.vision = new Vision(this);
        this.colorSensor = new ColorSensor(this);
        this.carousel = new Carousel(this);
        this.intake = new Intake(this);
        this.yeeter = new Yeeter(this);

//        this.drive.init();
//        this.vision.init();
        this.colorSensor.init();
//        this.carousel.init();
//        this.intake.init();
//        this.yeeter.init();

        this.state = BotState.INIT;
        this.mode = BotMode.RUN;
    }

    public void setDebug() {
        this.mode = BotMode.DEBUG;
    }

    public BotMode getMode() {
        return this.mode;
    }

    public boolean isDebugMode() {
        return this.mode == BotMode.DEBUG;
    }

    public void setState(BotState state) {
        this.state = state;
    }

    public BotState getState() {
        return this.state;
    }

    public void visionTest() {
        this.vision.telemetryAprilTag();
        this.vision.telemetryBallDetector();
    }

    public void setDrivePower(double axial, double lateral, double yaw) {
        this.drive.setPower(axial, lateral, yaw);
    }

    public void colorSensorTest() {
        ArtifactColor color = this.colorSensor.classify();
        this.telemetry.addData("Classification (Bot)", color);
    }
}
