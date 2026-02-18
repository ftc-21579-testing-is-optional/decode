package org.firstinspires.ftc.teamcode.common.util.CommandExecutor;

public class Command {
    public double duration;
    public double offset;
    public CommandFunction function;

    public Command(double duration, double offset, CommandFunction function) {
        this.duration = duration;
        this.offset = offset;
        this.function = function;
    }

    public void run() {
        this.function.run();
    }
}
