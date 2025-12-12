package org.firstinspires.ftc.teamcode.opmode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "AutoRed")
public class Red extends AutoBase {
    public Red() {
        this.allianceID = 24;
        this.goalPosX = -72;
        this.goalPosY = 72;
    }
}
