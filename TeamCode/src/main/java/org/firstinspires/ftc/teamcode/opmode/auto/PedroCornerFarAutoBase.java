package org.firstinspires.ftc.teamcode.opmode.auto;


import com.bylazar.telemetry.PanelsTelemetry;
import com.bylazar.telemetry.TelemetryManager;
import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.Pose;
import com.pedropathing.util.Timer;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.common.bot.Bot;
import org.firstinspires.ftc.teamcode.common.hardware.CarouselDirection;
import org.firstinspires.ftc.teamcode.common.hardware.IntakeDirection;
import org.firstinspires.ftc.teamcode.common.hardware.ScooperState;
import org.firstinspires.ftc.teamcode.common.hardware.YeeterMode;
import org.firstinspires.ftc.teamcode.common.util.CommandExecutor.CommandExecutor;
import org.firstinspires.ftc.teamcode.opmode.auto.paths.FarCornerPathsBase;
import org.firstinspires.ftc.teamcode.pedro.Constants;

//@Autonomous(name = "PedroCloseAuto", group = "Autonomous")
//@Configurable // Panels
public class PedroCornerFarAutoBase extends OpMode {
    private TelemetryManager panelsTelemetry; // Panels Telemetry instance
    public Follower follower; // Pedro Pathing follower instance
    private int pathState; // Current autonomous path state (state machine)
    protected FarCornerPathsBase paths; // Paths defined in the Paths class
    protected Pose startPose;

    private Timer cmdExecutorTimer;
    private Bot bot;
    private CommandExecutor cmd;

    protected void initPaths() {}

    @Override
    public void init() {
        this.bot = new Bot(this.hardwareMap, this.telemetry);
        this.bot.initSubSystems();

        this.cmdExecutorTimer = new Timer();
        this.cmd = new CommandExecutor();

        panelsTelemetry = PanelsTelemetry.INSTANCE.getTelemetry();

        follower = Constants.createFollower(hardwareMap);
        follower.setStartingPose(this.startPose);

        this.initPaths();

        panelsTelemetry.addData("Status", "Initialized");
        panelsTelemetry.update(telemetry);
    }

    @Override
    public void start() {
        cmdExecutorTimer.resetTimer();
        bot.setYeeterMode(YeeterMode.AUTO_FAR);
    }

    @Override
    public void loop() {
        follower.update(); // Update Pedro Pathing
        this.autonomousPathUpdate(); // Update autonomous state machine

        // Log values to Panels and Driver Station
        panelsTelemetry.addData("Path State", this.pathState);
        panelsTelemetry.addData("X", follower.getPose().getX());
        panelsTelemetry.addData("Y", follower.getPose().getY());
        panelsTelemetry.addData("Heading", follower.getPose().getHeading());
        panelsTelemetry.addData("# Commands", cmd.commands.size());
        panelsTelemetry.addData("Command Time", cmdExecutorTimer.getElapsedTime());
        panelsTelemetry.update(telemetry);
    }

    public void setPathState(int pState) {
        this.pathState = pState;
//        pathTimer.resetTimer();
    }

    boolean firstShootingSequenceDone = false;
    boolean secondShootingSequenceDone = false;
    boolean thirdShootingSequenceDone = false;

    public void autonomousPathUpdate() {
        switch (this.pathState) {
            case 0:
                follower.followPath(this.paths.Path1);
                this.setPathState(1);
                break;
            case 1:
                // shoot 3 pre load artifacts
                if (follower.isBusy()) {
                    break;
                }

                if (!this.firstShootingSequenceDone) {
                    this.cmd.reset();
                    this.cmdExecutorTimer.resetTimer();

                    // shoot 3 artifacts
                    // shoot preloaded artifact
                    this.cmd.add(0.50, () -> {
                        bot.setScooperState(ScooperState.CATCH);
                    });
                    this.cmd.add(0.25, () -> {
                        bot.setScooperState(ScooperState.YEET);
                    });

                    // allow yeeter to spin back up
                    this.cmd.add(2.0, () -> {});

                    // 2nd shot
                    this.cmd.add(0.25, () -> {
                        bot.setScooperState(ScooperState.CATCH);
                    });
                    this.cmd.add(0.50, () -> {
                        bot.setCarouselDirection(CarouselDirection.UP);
                    });
                    this.cmd.add(0.50, () -> {
                        bot.setCarouselDirection(CarouselDirection.STOP);
                    });
                    this.cmd.add(0.25, () -> {
                        bot.setScooperState(ScooperState.YEET);
                    });

                    // allow yeeter to spin back up
                    this.cmd.add(2.0, () -> {});

                    // 3rd shoot
                    this.cmd.add(0.5, () -> {
                        bot.setScooperState(ScooperState.CATCH);
                    });
                    this.cmd.add(0.7, () -> { // run this for longer than 2nd shot
                        bot.setCarouselDirection(CarouselDirection.UP);
                    });
                    this.cmd.add(0.50, () -> {
                        bot.setCarouselDirection(CarouselDirection.STOP);
                    });
                    this.cmd.add(0.25, () -> {
                        bot.setScooperState(ScooperState.YEET);
                    });
                    this.cmd.add(0.25, () -> {
                        this.bot.setDrumIntakeDirection(IntakeDirection.IN);
                        this.bot.setAssistIntakeDirection(IntakeDirection.IN);
                        this.bot.setScooperState(ScooperState.CATCH);
                        this.bot.setCarouselDirection(CarouselDirection.UP_SLOW);
                        this.setPathState(2);
                    });

                    this.firstShootingSequenceDone = true;
                }
                else {
                    this.panelsTelemetry.addData("Command Status", "running sequence");
                    this.cmd.run((double) this.cmdExecutorTimer.getElapsedTime() / 1000);
                }

                break;
            case 2:
                /* You could check for
                - Follower State: "if(!follower.isBusy()) {}"
                - Time: "if(pathTimer.getElapsedTimeSeconds() > 1) {}"
                - Robot Position: "if(follower.getPose().getX() > 36) {}"
                */

                /* This case checks the robot's position and will wait until the robot position is close (1 inch away) from the scorePose's position */
                if (!follower.isBusy()) {
                    follower.followPath(this.paths.Path2, true);
                    this.setPathState(3);
                }
                break;
            case 3:
                /* This case checks the robot's position and will wait until the robot position is close (1 inch away) from the pickup1Pose's position */
                if (!follower.isBusy()) {
                    /* Grab Sample */

                    /* Since this is a pathChain, we can have Pedro hold the end point while we are scoring the sample */
                    follower.followPath(this.paths.Path3, 0.35, true); // 3 6 9 less power
                    this.setPathState(4);
                }
                break;
            case 4:
                if (!follower.isBusy()) {
                    this.bot.setCarouselDirection(CarouselDirection.STOP);
                    follower.followPath(this.paths.Path4, true);
                    this.setPathState(5);
                }
                break;
            case 5:
                if (follower.isBusy()) {
                    break;
                }

                if (!this.secondShootingSequenceDone) {
                    this.cmd.reset();
                    this.cmdExecutorTimer.resetTimer();

                    // shoot newly loaded artifacts
                    // 1st shot
                    this.cmd.add(0.5, () -> {
                        bot.setScooperState(ScooperState.CATCH);
                        this.bot.setDrumIntakeDirection(IntakeDirection.STOP);
                        this.bot.setAssistIntakeDirection(IntakeDirection.STOP);
                    });
//                    this.cmd.add(1.00, () -> {
//                        bot.setCarouselDirection(CarouselDirection.UP);
//                    });
//                    this.cmd.add(0.25, () -> {
//                        bot.setCarouselDirection(CarouselDirection.STOP);
//                    });
                    this.cmd.add(0.25, () -> {
                        bot.setScooperState(ScooperState.YEET);
                    });

                    // allow yeeter to spin back up
                    this.cmd.add(2.0, () -> {});

                    // 2nd shot
                    this.cmd.add(0.25, () -> {
                        bot.setScooperState(ScooperState.CATCH);
                    });
                    this.cmd.add(0.50, () -> {
                        bot.setCarouselDirection(CarouselDirection.UP);
                    });
                    this.cmd.add(0.05, () -> {
//                        bot.setCarouselDirection(CarouselDirection.DOWN);
                    });
                    this.cmd.add(0.50, () -> {
                        bot.setCarouselDirection(CarouselDirection.STOP);
                    });
                    this.cmd.add(0.25, () -> {
                        bot.setScooperState(ScooperState.YEET);
                    });

                    // allow yeeter to spin back up
                    this.cmd.add(2.0, () -> {});

                    // 3rd shot
                    this.cmd.add(0.25, () -> {
                        bot.setScooperState(ScooperState.CATCH);
                    });
                    this.cmd.add(0.50, () -> {
                        bot.setCarouselDirection(CarouselDirection.UP);
                    });
                    this.cmd.add(0.05, () -> {
//                        bot.setCarouselDirection(CarouselDirection.DOWN);
                    });
                    this.cmd.add(0.50, () -> {
                        bot.setCarouselDirection(CarouselDirection.STOP);
                    });
                    this.cmd.add(0.25, () -> {
                        bot.setScooperState(ScooperState.YEET);
                    });
                    this.cmd.add(0.25, () -> {
                        this.bot.setDrumIntakeDirection(IntakeDirection.IN);
                        this.bot.setAssistIntakeDirection(IntakeDirection.IN);
                        this.bot.setScooperState(ScooperState.CATCH);
                        this.bot.setCarouselDirection(CarouselDirection.UP_SLOW);
                        this.setPathState(6);
                    });

                    this.secondShootingSequenceDone = true;
                }
                else {
                    this.panelsTelemetry.addData("Command Status", "running sequence");
                    this.cmd.run((double) this.cmdExecutorTimer.getElapsedTime() / 1000);
                }

                break;
            case 6:
                if (!follower.isBusy()) {
                    follower.followPath(this.paths.Path5, true);
                    this.setPathState(7);
                }
                break;
            case 7:
                if (!follower.isBusy()) {
                    follower.followPath(this.paths.Path6, true);

                    this.bot.setScooperState(ScooperState.RECYCLE);
                    this.bot.setCarouselDirection(CarouselDirection.UP_SLOW);
                    this.bot.setDrumIntakeDirection(IntakeDirection.IN);
                    this.bot.setAssistIntakeDirection(IntakeDirection.IN);

                    this.setPathState(8);
                }
                break;
            case 8:
                if (!follower.isBusy()) {
                    follower.followPath(this.paths.Path7, true);
                    this.setPathState(9);
                }
                break;
            case 9:
                if (!follower.isBusy()) {
                    follower.followPath(this.paths.Path8, true);
                    this.setPathState(10);
                }
                break;
            case 10:
                if (follower.isBusy()) {
                    break;
                }

                if (!this.thirdShootingSequenceDone) {
                    this.cmd.reset();
                    this.cmdExecutorTimer.resetTimer();

                    // shoot newly loaded artifacts
                    // 1st shot
                    this.cmd.add(0.25, () -> {
                        bot.setScooperState(ScooperState.CATCH);
                        this.bot.setDrumIntakeDirection(IntakeDirection.STOP);
                        this.bot.setAssistIntakeDirection(IntakeDirection.STOP);
                    });
//                    this.cmd.add(1.00, () -> {
//                        bot.setCarouselDirection(CarouselDirection.UP);
//                    });
//                    this.cmd.add(0.25, () -> {
//                        bot.setCarouselDirection(CarouselDirection.STOP);
//                    });
                    this.cmd.add(0.25, () -> {
                        bot.setScooperState(ScooperState.YEET);
                    });

                    // allow yeeter to spin back up
                    this.cmd.add(2.0, () -> {});

                    // 2nd shot
                    this.cmd.add(0.25, () -> {
                        bot.setScooperState(ScooperState.CATCH);
                    });
                    this.cmd.add(0.50, () -> {
                        bot.setCarouselDirection(CarouselDirection.UP);
                    });
                    this.cmd.add(0.05, () -> {
//                        bot.setCarouselDirection(CarouselDirection.DOWN);
                    });
                    this.cmd.add(0.50, () -> {
                        bot.setCarouselDirection(CarouselDirection.STOP);
                    });
                    this.cmd.add(0.25, () -> {
                        bot.setScooperState(ScooperState.YEET);
                    });

                    // allow yeeter to spin back up
                    this.cmd.add(2.0, () -> {});

                    // 3rd shot
                    this.cmd.add(0.25, () -> {
                        bot.setScooperState(ScooperState.CATCH);
                    });
                    this.cmd.add(0.50, () -> {
                        bot.setCarouselDirection(CarouselDirection.UP);
                    });
                    this.cmd.add(0.05, () -> {
//                        bot.setCarouselDirection(CarouselDirection.DOWN);
                    });
                    this.cmd.add(0.50, () -> {
                        bot.setCarouselDirection(CarouselDirection.STOP);
                    });
                    this.cmd.add(0.25, () -> {
                        bot.setScooperState(ScooperState.YEET);
                    });
                    this.cmd.add(0.25, () -> {
                        this.bot.setDrumIntakeDirection(IntakeDirection.IN);
                        this.bot.setAssistIntakeDirection(IntakeDirection.IN);
                        this.bot.setScooperState(ScooperState.CATCH);
                        this.bot.setCarouselDirection(CarouselDirection.UP_SLOW);
                        this.setPathState(11);
                    });

                    this.thirdShootingSequenceDone = true;
                }
                else {
                    this.panelsTelemetry.addData("Command Status", "running sequence");
                    this.cmd.run((double) this.cmdExecutorTimer.getElapsedTime() / 1000);
                }
                break;
            case 11:
                /* This case checks the robot's position and will wait until the robot position is close (1 inch away) from the scorePose's position */
                if (!follower.isBusy()) {
                    /* Set the state to a Case we won't use or define, so it just stops running an new paths */
                    this.setPathState(-1);
                }
                break;
        }
    }
}
