package org.firstinspires.ftc.teamcode.common.hardware;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.common.Bot;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

public class Vision {
    private final Bot bot;
    private AprilTagProcessor aprilTag;
    private VisionPortal visionPortal;

    public Vision(Bot bot) {
        this.bot = bot;

        this.init();
    }

    public void init() {
        this.aprilTag = new AprilTagProcessor.Builder()
                .build();

        VisionPortal.Builder builder = new VisionPortal.Builder();

        builder.setCamera(this.bot.hardwareMap.get(WebcamName.class, "Webcam 1"));

        builder.addProcessor(aprilTag);

        this.visionPortal = builder.build();
    }
}
