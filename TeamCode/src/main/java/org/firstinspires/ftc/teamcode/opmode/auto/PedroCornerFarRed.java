package org.firstinspires.ftc.teamcode.opmode.auto;

import com.bylazar.configurables.annotations.Configurable;
import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.opmode.auto.paths.FarCornerPathsRed;
import org.firstinspires.ftc.teamcode.opmode.auto.paths.FarPathsRed;

@Autonomous(name = "PedroCornerFarRed", group = "Autonomous")
@Configurable // Panels
public class PedroCornerFarRed extends PedroCornerFarAutoBase {
    public PedroCornerFarRed() {
        this.startPose = new Pose(87.5, 8.5, Math.toRadians(90));
    }

    @Override
    protected void initPaths() {
        this.paths = new FarCornerPathsRed(this.follower); // Build paths
    }
}
