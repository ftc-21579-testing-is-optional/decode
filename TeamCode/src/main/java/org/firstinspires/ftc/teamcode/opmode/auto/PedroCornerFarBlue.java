package org.firstinspires.ftc.teamcode.opmode.auto;

import com.bylazar.configurables.annotations.Configurable;
import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.opmode.auto.paths.FarCornerPathsBlue;
import org.firstinspires.ftc.teamcode.opmode.auto.paths.FarPathsBlue;

@Autonomous(name = "PedroCornerFarBlue", group = "Autonomous")
@Configurable // Panels
public class PedroCornerFarBlue extends PedroCornerFarAutoBase {
    public PedroCornerFarBlue() {
        this.startPose = new Pose(56.67, 8.47, Math.toRadians(90));
    }

    @Override
    protected void initPaths() {
        this.paths = new FarCornerPathsBlue(this.follower); // Build paths
    }
}
