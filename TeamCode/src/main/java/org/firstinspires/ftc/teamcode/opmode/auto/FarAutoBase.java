package org.firstinspires.ftc.teamcode.opmode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.common.bot.Bot;
import org.firstinspires.ftc.teamcode.common.hardware.CarouselDirection;
import org.firstinspires.ftc.teamcode.common.hardware.ScooperState;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;

public class FarAutoBase extends LinearOpMode {
    private Bot bot;
    ElapsedTime time;

    protected int allianceID = 0;
    protected int goalPosX = 0;
    protected int goalPosY = 0;
    private AprilTagDetection goalTag;
    private double currentDistance = 1000;

    @Override
    public void runOpMode() throws InterruptedException {
        this.bot = new Bot(this.hardwareMap, this.telemetry);
        this.bot.initSubSystems();

        this.waitForStart();

        this.time = new ElapsedTime();

        boolean shouldShoot = false;

        while (this.opModeIsActive()) {
            for (AprilTagDetection tag : bot.getAprilTagDetections()) {
                this.telemetry.addData("name", tag.metadata.name);
                this.telemetry.addData("id", tag.metadata.id);
                this.telemetry.addData("yaw", tag.robotPose.getOrientation().getYaw(AngleUnit.DEGREES));

                if (tag.metadata.id == this.allianceID) { // TODO: same as below, maybe add check to prevent mid-match false readings
                    this.goalTag = tag;
                }
            }

            if (this.goalTag != null) { // TODO: maybe try persistence counting
                this.currentDistance = Math.sqrt(Math.pow(this.goalPosX - this.goalTag.robotPose.getPosition().x, 2) + Math.pow(this.goalPosY - this.goalTag.robotPose.getPosition().y, 2)) - 18; // 2D distance between bot and 18in from corner
            }

            this.telemetry.addData("distance", this.currentDistance);

            if (this.currentDistance > 32) {
                if (time.seconds() > 0 && time.seconds() < 2) {
                    bot.setDrivePower(0.75, 0.0, 0.0);
                }
                if (time.seconds() > 2 && time.seconds() < 3) {
                    bot.setDrivePower(0.0, 0.0, 0.15 * Math.signum(this.goalPosY));
                }
                if (time.seconds() > 3 && time.seconds() < 4) {
                    bot.setDrivePower(0.5, 0.0, 0.0);
                }

                shouldShoot = false;
            }

            if (this.currentDistance <= 32) {
                shouldShoot = true;
            }

            if (shouldShoot) {
                this.bot.setDrivePower(0.0, 0.0, 0.0);

                // START 1 shoot cycle
                if (time.seconds() > 4 && time.seconds() < 5) {
                    bot.setScooperState(ScooperState.CATCH);
                }

                if (time.seconds() > 5 && time.seconds() < 5.5) {
                    bot.setCarouselDirection(CarouselDirection.UP);
                }

                if (time.seconds() > 5.5 && time.seconds() < 7) {
                    bot.setCarouselDirection(CarouselDirection.STOP);
                }

                if (time.seconds() > 7 && time.seconds() < 8) {
                    bot.setScooperState(ScooperState.YEET);
                }
                // END 1 shoot cycle

                // START 1 shoot cycle
                if (time.seconds() > 9 && time.seconds() < 10) {
                    bot.setScooperState(ScooperState.CATCH);
                }

                if (time.seconds() > 10 && time.seconds() < 10.5) {
                    bot.setCarouselDirection(CarouselDirection.UP);
                }

                if (time.seconds() > 10.5 && time.seconds() < 12) {
                    bot.setCarouselDirection(CarouselDirection.STOP);
                }

                if (time.seconds() > 12 && time.seconds() < 13) {
                    bot.setScooperState(ScooperState.YEET);
                }
                // END 1 shoot cycle

                // START 1 shoot cycle
                if (time.seconds() > 14 && time.seconds() < 15) {
                    bot.setScooperState(ScooperState.CATCH);
                }

                if (time.seconds() > 15 && time.seconds() < 15.5) {
                    bot.setCarouselDirection(CarouselDirection.UP);
                }

                if (time.seconds() > 15.5 && time.seconds() < 17) {
                    bot.setCarouselDirection(CarouselDirection.STOP);
                }

                if (time.seconds() > 17 && time.seconds() < 18) {
                    bot.setScooperState(ScooperState.YEET);
                }
                // END 1 shoot cycle

                // START 1 shoot cycle
                if (time.seconds() > 19 && time.seconds() < 20) {
                    bot.setScooperState(ScooperState.CATCH);
                }

                if (time.seconds() > 20 && time.seconds() < 20.5) {
                    bot.setCarouselDirection(CarouselDirection.UP);
                }

                if (time.seconds() > 20.5 && time.seconds() < 22) {
                    bot.setCarouselDirection(CarouselDirection.STOP);
                }

                if (time.seconds() > 22 && time.seconds() < 23) {
                    bot.setScooperState(ScooperState.YEET);
                }
                // END 1 shoot cycle

                // START 1 shoot cycle
                if (time.seconds() > 24 && time.seconds() < 25) {
                    bot.setScooperState(ScooperState.CATCH);
                }

                if (time.seconds() > 25 && time.seconds() < 25.5) {
                    bot.setCarouselDirection(CarouselDirection.UP);
                }

                if (time.seconds() > 25.5 && time.seconds() < 27) {
                    bot.setCarouselDirection(CarouselDirection.STOP);
                }

                if (time.seconds() > 27 && time.seconds() < 28) {
                    bot.setScooperState(ScooperState.YEET);
                }
                // END 1 shoot cycle
            }
        }

        this.telemetry.addData("shouldShoot", shouldShoot);

        this.telemetry.update();

        this.sleep(10);
        this.idle();
    }
}
