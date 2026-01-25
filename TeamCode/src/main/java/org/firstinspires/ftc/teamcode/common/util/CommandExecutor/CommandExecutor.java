package org.firstinspires.ftc.teamcode.common.util.CommandExecutor;

import java.util.ArrayList;
import java.util.List;

public class CommandExecutor {
    public List<Command> commands = new ArrayList<>();

    public CommandExecutor() {

    }

    public void add(Command command) {
        this.commands.add(command);
    }

    public void add(double duration, CommandFunction function) {
        this.commands.add(new Command(duration, function));
    }

    public void run(double currentTime) {
        double startTime = 0;

        for (Command command : this.commands) {
            if (currentTime > startTime && currentTime < startTime + command.duration) {
                command.run();
                return;
            }

            startTime += command.duration;
        }
    }
}
