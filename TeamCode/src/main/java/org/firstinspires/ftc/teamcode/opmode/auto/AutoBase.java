package org.firstinspires.ftc.teamcode.opmode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.common.bot.Bot;
import org.firstinspires.ftc.teamcode.common.config.Config;
import org.firstinspires.ftc.teamcode.common.game.ArtifactColor;
import org.firstinspires.ftc.teamcode.common.game.Motif;
import org.firstinspires.ftc.teamcode.common.hardware.IntakeDirection;
import org.firstinspires.ftc.teamcode.common.hardware.YeeterMode;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;

@Autonomous(name = "100% (not) working auto")
public class AutoBase extends LinearOpMode {
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

        this.bot.setIntakeDirection(IntakeDirection.IN);
        this.bot.setYeeterMode(YeeterMode.LESS);

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

            if (this.goalTag != null) { // TODO: maybe try rolling average if there is significant jitter
                this.currentDistance = Math.sqrt(Math.pow(this.goalPosX - this.goalTag.robotPose.getPosition().x, 2) + Math.pow(this.goalPosY - this.goalTag.robotPose.getPosition().y, 2)) - 18; // 2D distance between bot and 18in from corner
            }

            this.telemetry.addData("distance", this.currentDistance);

            if (this.currentDistance < 48) {
                this.bot.setDrivePower(-0.25, 0.0, 0.0); // TODO: probably increase speed, low speed just to start off with
            }
            else if (this.currentDistance > Config.MIN_SHOOTING_DISTANCE && this.currentDistance < Config.MAX_SHOOTING_DISTANCE) { // TODO: maybe remove upper limit and just go for shot no matter what?
                this.bot.setDrivePower(0.0, 0.0, 0.0);

                // Do shooting routine
                // wait for color based on currentMotif? -> later once color sensor is functional


            }
            else {
                this.bot.setDrivePower(0.0, 0.0, 0.0);
            }


            this.telemetry.update();

            this.sleep(10);
            this.idle();
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
