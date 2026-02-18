package org.firstinspires.ftc.teamcode.opmode.auto;

import com.bylazar.configurables.annotations.Configurable;
import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.opmode.auto.paths.ClosePathsBlue;
import org.firstinspires.ftc.teamcode.opmode.auto.paths.FarPathsBlue;

@Autonomous(name = "PedroFarBlue", group = "Autonomous")
@Configurable // Panels
public class PedroFarBlue extends PedroFarAutoBase {
    public PedroFarBlue() {
        this.startPose = new Pose(56.67, 8.47, Math.toRadians(90));
    }

    @Override
    protected void initPaths() {
        this.paths = new FarPathsBlue(this.follower); // Build paths
    }
}
