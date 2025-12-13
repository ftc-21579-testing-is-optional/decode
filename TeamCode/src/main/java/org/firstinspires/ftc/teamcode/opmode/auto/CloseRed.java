package org.firstinspires.ftc.teamcode.opmode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "AutoRedClose")
public class CloseRed extends CloseAutoBase {
    public CloseRed() {
        this.allianceID = 24;
        this.goalPosX = -72;
        this.goalPosY = 72;
    }
}
