package org.firstinspires.ftc.teamcode.common.util.CommandExecutor;

public class Command {
    public double duration;
    public CommandFunction function;

    public Command(double duration, CommandFunction function) {
        this.duration = duration;
        this.function = function;
    }

    public void run() {
        this.function.run();
    }
}
