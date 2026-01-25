package org.firstinspires.ftc.teamcode.opmode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.common.bot.Bot;
import org.firstinspires.ftc.teamcode.common.game.Motif;
import org.firstinspires.ftc.teamcode.common.hardware.CarouselDirection;
import org.firstinspires.ftc.teamcode.common.hardware.IntakeDirection;
import org.firstinspires.ftc.teamcode.common.hardware.ScooperState;
import org.firstinspires.ftc.teamcode.common.hardware.YeeterMode;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;

@Autonomous(name = "100% (not) working auto")
public class FarAutoBase extends LinearOpMode {
    private Bot bot;
    private ElapsedTime time;

    protected int allianceID = 0;
    protected int goalPosX = 0;
    protected int goalPosY = 0;
    private AprilTagDetection goalTag;
    private double currentDistance;
    private Motif currentMotif = Motif.NONE;

    @Override
    public void runOpMode() throws InterruptedException {
        this.bot = new Bot(this.hardwareMap, this.telemetry);
        this.bot.setDebug();
        this.bot.initSubSystems();

        this.waitForStart();

        this.time = new ElapsedTime();

        this.bot.setDrumIntakeDirection(IntakeDirection.IN);
        this.bot.setAssistIntakeDirection(IntakeDirection.IN);
        this.bot.setYeeterMode(YeeterMode.FULL);
        this.bot.setCarouselPower(0.5);

        boolean shouldShoot = false;

        while (this.opModeIsActive()) {
            for (AprilTagDetection tag : bot.getAprilTagDetections()) {
                this.telemetry.addData("name", tag.metadata.name);
                this.telemetry.addData("id", tag.metadata.id);
                this.telemetry.addData("yaw", tag.robotPose.getOrientation().getYaw(AngleUnit.DEGREES));

                if (tag.metadata.id == this.allianceID) { // TODO: same as below, maybe add check to prevent mid-match false readings
                    this.goalTag = tag;
                }
                else { // TODO: maybe add condition here to check if motif is already set, not sure how bad a jittery motif could affect other code
                    switch (tag.metadata.id) { // TODO: this needs testing, currently motifs for each id case are guesses
                        case 21:
                            this.currentMotif = Motif.GPP;
                            break;

                        case 22:
                            this.currentMotif = Motif.PGP;
                            break;

                        case 23:
                            this.currentMotif = Motif.PPG;
                            break;
                    }
                }
            }

            if (this.goalTag != null) { // TODO: maybe try persistence counting
                this.currentDistance = Math.sqrt(Math.pow(this.goalPosX - this.goalTag.robotPose.getPosition().x, 2) + Math.pow(this.goalPosY - this.goalTag.robotPose.getPosition().y, 2)) - 18; // 2D distance between bot and 18in from corner
            }

            this.telemetry.addData("distance", this.currentDistance);

//            if (this.currentDistance < 38) {
//                this.bot.setDrivePower(-0.50, 0.0, 0.0); // TODO: probably increase speed, low speed just to start off with
//
//                shouldShoot = false;
//            }
//            else if (this.currentDistance > Config.MIN_SHOOTING_DISTANCE && this.currentDistance < Config.MAX_SHOOTING_DISTANCE) { // TODO: maybe remove upper limit and just go for shot no matter what?
//                this.bot.setDrivePower(0.0, 0.0, 0.0);
//
//                // Do shooting routine
//                // wait for color based on currentMotif? -> later once color sensor is functional
//            }

            if (this.currentDistance >= 38 || time.seconds() > 2) {
                shouldShoot = true;
            }

            if (shouldShoot) {
                this.bot.setDrivePower(0.0, 0.0, 0.0);

                // START 1 shoot cycle
                if (time.seconds() > 2 && time.seconds() < 3) {
                    bot.setScooperState(ScooperState.CATCH);
                }

//                if (time.seconds() > 4 && time.seconds() < 4.75) {
//                    bot.setCarouselDirection(CarouselDirection.UP);
//                }
//
//                if (time.seconds() > 4.75 && time.seconds() < 5) {
//                    bot.setCarouselDirection(CarouselDirection.DOWN);
//                }
//
//                if (time.seconds() > 5 && time.seconds() < 6) {
//                    bot.setCarouselDirection(CarouselDirection.STOP);
//                }

                if (time.seconds() > 3 && time.seconds() < 3.5) {
                    bot.setScooperState(ScooperState.YEET);
                }
                // END 1 shoot cycle

                // START 1 shoot cycle
                if (time.seconds() > 4.5 && time.seconds() < 5) {
                    bot.setScooperState(ScooperState.CATCH);
                }

                if (time.seconds() > 5 && time.seconds() < 5.75) {
                    bot.setCarouselDirection(CarouselDirection.UP);
                }

//                if (time.seconds() > 4.75 && time.seconds() < 5) {
//                    bot.setCarouselDirection(CarouselDirection.DOWN);
//                }

                if (time.seconds() > 6 && time.seconds() < 7) {
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

                if (time.seconds() > 10 && time.seconds() < 10.25) {
                    bot.setCarouselDirection(CarouselDirection.UP);
                }

//                if (time.seconds() > 15.25 && time.seconds() < 15) {
//                    bot.setCarouselDirection(CarouselDirection.DOWN);
//                }

                if (time.seconds() > 10.25 && time.seconds() < 12) {
                    bot.setCarouselDirection(CarouselDirection.STOP);
                }

                if (time.seconds() > 12 && time.seconds() < 13) {
                    bot.setScooperState(ScooperState.YEET);
                }
                // END 1 shoot cycle

//                 START 1 shoot cycle
                if (time.seconds() > 15 && time.seconds() < 16) {
                    bot.setScooperState(ScooperState.CATCH);
                }

                if (time.seconds() > 16 && time.seconds() < 16.75) {
                    bot.setCarouselDirection(CarouselDirection.UP);
                }

                if (time.seconds() > 16.75 && time.seconds() < 17) {
                    bot.setCarouselDirection(CarouselDirection.DOWN);
                }

                if (time.seconds() > 17 && time.seconds() < 18) {
                    bot.setCarouselDirection(CarouselDirection.STOP);
                }

                if (time.seconds() > 18 && time.seconds() < 19) {
                    bot.setScooperState(ScooperState.YEET);
                }
//                 END 1 shoot cycle

//                 START 1 shoot cycle
                if (time.seconds() > 23 && time.seconds() < 24) {
                    bot.setScooperState(ScooperState.CATCH);
                }

                if (time.seconds() > 24 && time.seconds() < 24.95) {
                    bot.setCarouselDirection(CarouselDirection.UP);
                }

                if (time.seconds() > 24.75 && time.seconds() < 25) {
                    bot.setCarouselDirection(CarouselDirection.DOWN);
                }

                if (time.seconds() > 25 && time.seconds() < 26) {
                    bot.setCarouselDirection(CarouselDirection.STOP);
                }

                if (time.seconds() > 26 && time.seconds() < 27) {
                    bot.setScooperState(ScooperState.YEET);
                }
//                 END 1 shoot cycle
            }

            this.telemetry.addData("shouldShoot", shouldShoot);

            this.telemetry.addData("currentMotif", this.currentMotif.name());

            this.telemetry.update();

            this.sleep(10);
            this.idle();
        }
    }

    public void waitForBall() {
        boolean detection;

        do {
            detection = this.bot.isThereABall();

            this.telemetry.addData("Detection", detection);
            this.telemetry.update();

            this.sleep(10);
            this.idle();
        } while (!detection);
    }
}
