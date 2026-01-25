package org.firstinspires.ftc.teamcode.opmode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "AutoRedFar")
public class FarRed extends NewFarAutoBase {
    public FarRed() {
        this.allianceID = 24;
        this.goalPosX = -72;
        this.goalPosY = 72;
    }
}
