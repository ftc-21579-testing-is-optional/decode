package org.firstinspires.ftc.teamcode.opmode.auto;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.common.bot.Bot;
import org.firstinspires.ftc.teamcode.common.hardware.CarouselDirection;
import org.firstinspires.ftc.teamcode.common.hardware.IntakeDirection;
import org.firstinspires.ftc.teamcode.common.hardware.ScooperState;
import org.firstinspires.ftc.teamcode.common.hardware.YeeterMode;
import org.firstinspires.ftc.teamcode.common.util.CommandExecutor.CommandExecutor;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;

public class NewCloseAutoBase extends LinearOpMode {
    private Bot bot;
    private ElapsedTime time;

    protected int allianceID = 0;
    protected int goalPosX = 0;
    protected int goalPosY = 0;
    private AprilTagDetection goalTag;
    private double currentDistance;

    @Override
    public void runOpMode() throws InterruptedException {
        this.bot = new Bot(this.hardwareMap, this.telemetry);
        this.bot.setDebug();
        this.bot.initSubSystems();

        CommandExecutor cmdExec = new CommandExecutor();

        // delay to allow for driving to shooting distance
        cmdExec.add(2.1, () -> {
            bot.setYeeterMode(YeeterMode.AUTO_LESS);
        });

        // shoot 3 artifacts
        // shoot preloaded artifact
        cmdExec.add(0.5, () -> {
            bot.setScooperState(ScooperState.CATCH);
        });
        cmdExec.add(0.5, () -> {
            bot.setScooperState(ScooperState.YEET);
        });

        // allow yeeter to spin back up
        cmdExec.add(2.0, () -> {
            bot.setYeeterMode(YeeterMode.AUTO);
        });

        // 2nd shot
        cmdExec.add(0.5, () -> {
            bot.setScooperState(ScooperState.CATCH);
        });
        cmdExec.add(0.80, () -> {
            bot.setCarouselDirection(CarouselDirection.UP);
        });
        cmdExec.add(0.25, () -> {
            bot.setCarouselDirection(CarouselDirection.STOP);
        });
        cmdExec.add(0.5, () -> {
            bot.setScooperState(ScooperState.YEET);
        });

        // allow yeeter to spin back up
        cmdExec.add(1.0, () -> {});

        // 3rd shoot
        cmdExec.add(0.5, () -> {
            bot.setScooperState(ScooperState.CATCH);
        });
        cmdExec.add(1.25, () -> { // run this for longer than 2nd shot
            bot.setCarouselDirection(CarouselDirection.UP);
        });
        cmdExec.add(0.25, () -> {
            bot.setCarouselDirection(CarouselDirection.STOP);
        });
        cmdExec.add(0.5, () -> {
            bot.setScooperState(ScooperState.YEET);
        });

        // get more artifacts
        // move laterally and turn on carousel + set scooper to catch
        cmdExec.add(0.75, () -> {
            bot.setDrivePower(0.0, 0.70 * Math.signum(this.goalPosY), 0.0);
            bot.setScooperState(ScooperState.CATCH);
            bot.setCarouselDirection(CarouselDirection.UP);
        });

        // turn to face line of artifacts
        cmdExec.add(0.75, () -> {
            double multiplier = (this.goalPosY < 0) ? 1 : 1.25; // fix inconsistencies

            bot.setDrivePower(0.0, 0.0, 0.35 * Math.signum(this.goalPosY) * multiplier);
        });

        // move laterally again
        cmdExec.add(0.5, () -> {
            double multiplier = (this.goalPosY < 0) ? 1.6 : 0; // fix inconsistencies

            bot.setDrivePower(0.0, -0.25 * Math.signum(this.goalPosY) * multiplier, 0.0);
        });

        // drive forward to attempt to pick up 3 artifacts
        cmdExec.add(3.0, () -> {
            bot.setDrivePower(0.35, 0.0, 0.0);
        });

        // stop carousel and drive back to shooting zone
        cmdExec.add(2.0, () -> {
            bot.setDrivePower(-0.40, 0.0, 0.0);
            bot.setCarouselDirection(CarouselDirection.STOP);
        });

        // turn to face goal
        cmdExec.add(0.75, () -> {
            bot.setDrivePower(0.0, 0.0, -0.40 * Math.signum(this.goalPosY));
        });

        // shoot newly loaded artifacts
        // 1st shot
        cmdExec.add(0.5, () -> {
            bot.setScooperState(ScooperState.CATCH);
        });
        cmdExec.add(1.00, () -> {
            bot.setCarouselDirection(CarouselDirection.UP);
        });
        cmdExec.add(0.25, () -> {
            bot.setCarouselDirection(CarouselDirection.STOP);
        });
        cmdExec.add(0.5, () -> {
            bot.setScooperState(ScooperState.YEET);
        });

        // allow yeeter to spin back up
        cmdExec.add(2.0, () -> {});

        // 2nd shot
        cmdExec.add(0.5, () -> {
            bot.setScooperState(ScooperState.CATCH);
        });
        cmdExec.add(1.00, () -> {
            bot.setCarouselDirection(CarouselDirection.UP);
        });
        cmdExec.add(0.25, () -> {
            bot.setCarouselDirection(CarouselDirection.STOP);
        });
        cmdExec.add(0.5, () -> {
            bot.setScooperState(ScooperState.YEET);
        });

        // allow yeeter to spin back up
        cmdExec.add(1.0, () -> {});

        // 3rd shot
        cmdExec.add(0.5, () -> {
            bot.setScooperState(ScooperState.CATCH);
        });
        cmdExec.add(0.75, () -> {
            bot.setCarouselDirection(CarouselDirection.UP);
        });
        cmdExec.add(0.25, () -> {
            bot.setCarouselDirection(CarouselDirection.STOP);
        });
        cmdExec.add(0.5, () -> {
            bot.setScooperState(ScooperState.YEET);
        });

        // park at end
        cmdExec.add(0.75, () -> {
            bot.setDrivePower(0.0, 0.0, 0.325 * Math.signum(this.goalPosY));
        });
        cmdExec.add(2.5, () -> {
            bot.setDrivePower(0.25, 0.75 * Math.signum(this.goalPosY), 0.0);
        });
        cmdExec.add(0.5, () -> {
            bot.setDrivePower(0.0, 0.0, 0.0);
        });

        this.waitForStart();

        this.time = new ElapsedTime();

        this.bot.setDrumIntakeDirection(IntakeDirection.IN);
        this.bot.setAssistIntakeDirection(IntakeDirection.IN);
        this.bot.setYeeterMode(YeeterMode.AUTO);
//        this.bot.setCarouselPower(0.5);

        while (this.opModeIsActive()) {
            for (AprilTagDetection tag : bot.getAprilTagDetections()) {
                if (tag == null) continue;
                if (tag.metadata == null) continue;
                if (tag.robotPose == null) continue;

                this.telemetry.addData("name", tag.metadata.name);
                this.telemetry.addData("id", tag.metadata.id);
                this.telemetry.addData("yaw", tag.robotPose.getOrientation().getYaw(AngleUnit.DEGREES));

                if (tag.metadata.id == this.allianceID) {
                    this.goalTag = tag;
                }
            }

            if (this.goalTag != null) { // TODO: maybe try persistence counting
                this.currentDistance = Math.sqrt(Math.pow(this.goalPosX - this.goalTag.robotPose.getPosition().x, 2) + Math.pow(this.goalPosY - this.goalTag.robotPose.getPosition().y, 2)) - 18; // 2D distance between bot and 18in from corner
            }

            this.telemetry.addData("distance", this.currentDistance);

            if (this.currentDistance < 38) { // drive to what is supposed to be 38 inches
                this.bot.setDrivePower(-0.45, 0.0, 0.0);
            }
            if (this.currentDistance >= 38 || (time.seconds() > 2 && time.seconds() <= 2.1)) { // stop once at 38 inches or after 2 seconds
                this.bot.setDrivePower(0.0, 0.0, 0.0);
            }

            cmdExec.run(time.seconds()); // run commands based on the elapsed time

            this.telemetry.update();

            this.sleep(10);
            this.idle();
        }
    }
}
