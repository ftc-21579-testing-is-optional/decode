package org.firstinspires.ftc.teamcode.common.util.CommandExecutor;

public class CommandOnce extends Command {
    public boolean done = false;

    public CommandOnce(double duration, CommandFunction function) {
        super(duration, function);
    }

    @Override
    public void run() {
        if (!this.done) {
            super.run();

            this.done = true;
        }
    }
}
