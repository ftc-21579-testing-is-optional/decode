package org.firstinspires.ftc.teamcode.opmode.auto;

import com.bylazar.configurables.annotations.Configurable;
import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.opmode.auto.paths.ClosePathsBlue;
import org.firstinspires.ftc.teamcode.opmode.auto.paths.ClosePathsRed;

@Autonomous(name = "PedroCloseBlue", group = "Autonomous")
@Configurable // Panels
public class PedroCloseBlue extends PedroCloseAutoBase {
    public PedroCloseBlue() {
        this.startPose = new Pose(23.5, 126.5, Math.toRadians(144));
    }

    @Override
    protected void initPaths() {
        this.paths = new ClosePathsBlue(this.follower); // Build paths
    }
}
