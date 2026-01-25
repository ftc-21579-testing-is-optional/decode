package org.firstinspires.ftc.teamcode.opmode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "AutoBlueClose")
public class CloseBlue extends NewCloseAutoBase {
    public CloseBlue() {
        this.allianceID = 20;
        this.goalPosX = -72;
        this.goalPosY = -72;
    }
}
