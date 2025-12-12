package org.firstinspires.ftc.teamcode.opmode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.common.bot.Bot;
import org.firstinspires.ftc.teamcode.common.game.ArtifactColor;
import org.firstinspires.ftc.teamcode.common.hardware.CarouselDirection;
import org.firstinspires.ftc.teamcode.common.hardware.ControlMapping;
import org.firstinspires.ftc.teamcode.common.hardware.IntakeDirection;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;

import java.util.List;

@Autonomous(name = "100% (not) working auto")
public class AutoBase extends LinearOpMode {
    private Bot bot;

    @Override
    public void runOpMode() throws InterruptedException {
        this.bot = new Bot(this.hardwareMap, this.telemetry);
        this.bot.setDebug();
        this.bot.initSubSystems();

        ControlMapping controls = new ControlMapping(gamepad1);

        this.waitForStart();

//        this.bot.setIntakeDirection(IntakeDirection.IN);

        while (this.opModeIsActive()) {
            // rotate carousel until there is a purple ball
//            this.bot.setCarouselDirection(CarouselDirection.UP);

//            this.waitForColor(ArtifactColor.PURPLE);

//            this.bot.setCarouselDirection(CarouselDirection.STOP);

            bot.visionTest();

            // ~ -27, 18 in

//             List<AprilTagDetection> tags = bot.getAprilTagDetections();
//
//            for (AprilTagDetection tag : tags) {
//                if (tag.metadata.id == 24) {
//
//                }
//            }

            this.sleep(10
            );
            this.idle();

            telemetry.update();
        }
    }

    public void waitForColor(ArtifactColor color) {
        ArtifactColor classification;

        do {
            classification = this.bot.classifyArtifact();

            this.telemetry.addData("Classification", classification);
            this.telemetry.update();

            this.sleep(10);
            this.idle();
        } while (classification != color);
    }
}
