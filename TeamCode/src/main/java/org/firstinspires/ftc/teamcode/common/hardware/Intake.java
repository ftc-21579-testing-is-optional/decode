package org.firstinspires.ftc.teamcode.common.hardware;

import org.firstinspires.ftc.teamcode.common.Bot;

public class Intake {
    private final Bot bot;
    private boolean active;

    public Intake(Bot bot) {
        this.bot = bot;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
