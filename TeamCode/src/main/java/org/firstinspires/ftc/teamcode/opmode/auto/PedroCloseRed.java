package org.firstinspires.ftc.teamcode.opmode.auto;

import com.bylazar.configurables.annotations.Configurable;
import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.opmode.auto.paths.ClosePathsRed;

@Autonomous(name = "PedroCloseRed", group = "Autonomous")
@Configurable // Panels
public class PedroCloseRed extends PedroCloseAutoBase {
    public PedroCloseRed() {
        this.startPose = new Pose(120.5, 126.5, Math.toRadians(36));
    }

    @Override
    protected void initPaths() {
        this.paths = new ClosePathsRed(this.follower); // Build paths
    }
}
