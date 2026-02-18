package org.firstinspires.ftc.teamcode.opmode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.common.bot.Bot;
import org.firstinspires.ftc.teamcode.common.hardware.IntakeDirection;
import org.firstinspires.ftc.teamcode.common.hardware.YeeterMode;
import org.firstinspires.ftc.teamcode.common.util.CommandExecutor.CommandExecutor;

@Autonomous(name = "demo auto")
public class Demo extends LinearOpMode {
    private Bot bot;
    private ElapsedTime time;

    @Override
    public void runOpMode() throws InterruptedException {
        this.bot = new Bot(this.hardwareMap, this.telemetry);
        this.bot.setDebug();
        this.bot.initSubSystems();

        CommandExecutor cmdExec = new CommandExecutor();

        // delay to allow for driving to shooting distance
        cmdExec.add(1.0, () -> {

        });

        this.waitForStart();

        this.time = new ElapsedTime();

        this.bot.setDrumIntakeDirection(IntakeDirection.IN);
        this.bot.setAssistIntakeDirection(IntakeDirection.IN);
        this.bot.setYeeterMode(YeeterMode.AUTO);
//        this.bot.setCarouselPower(0.5);

        while (this.opModeIsActive()) {
            cmdExec.run(time.seconds()); // run commands based on the elapsed time

            this.telemetry.update();

            this.sleep(10);
            this.idle();
        }
    }
}
