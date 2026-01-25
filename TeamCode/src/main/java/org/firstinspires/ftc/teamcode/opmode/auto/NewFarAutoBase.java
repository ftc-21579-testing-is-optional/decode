package org.firstinspires.ftc.teamcode.opmode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
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

@Autonomous(name = "new far auto base (don't run)")
public class NewFarAutoBase extends LinearOpMode {
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

        // shoot 3 artifacts
        // shoot preloaded artifact
        cmdExec.add(0.5, () -> {
            bot.setScooperState(ScooperState.CATCH);
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
        cmdExec.add(0.75, () -> {
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

        // allow yeeter to spin back up
        cmdExec.add(2.0, () -> {});

        // 4th shoot
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

        // allow yeeter to spin back up
        cmdExec.add(2.0, () -> {});

        // 5th shoot
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

        cmdExec.add(0.5, () -> {
            bot.setDrivePower(1.0, 0.0, 0.0);
        });

        cmdExec.add(1, () -> {
            bot.setDrivePower(0.0, 0.0, 0.0);
        });

        this.waitForStart();

        this.time = new ElapsedTime();

        this.bot.setDrumIntakeDirection(IntakeDirection.IN);
        this.bot.setAssistIntakeDirection(IntakeDirection.IN);
        this.bot.setYeeterMode(YeeterMode.AUTO_FAR);
        this.bot.setCarouselPower(0.5);

        while (this.opModeIsActive()) {
            for (AprilTagDetection tag : bot.getAprilTagDetections()) {
                if (tag == null) continue;
                if (tag.metadata == null) continue;
                if (tag.robotPose == null) continue;

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

            cmdExec.run(time.seconds());

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
