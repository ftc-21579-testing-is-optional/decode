package org.firstinspires.ftc.teamcode.opmode.auto.paths;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;

public class FarCornerPathsRed extends FarCornerPathsBase {
    public FarCornerPathsRed(Follower follower) {
        Path1 = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(87.521, 8.479),

                                new Pose(87.352, 12.942)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(90), Math.toRadians(67))

                .build();

        Path2 = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(87.352, 12.942),

                                new Pose(100.000, 35.504)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(67), Math.toRadians(0))

                .build();

        Path3 = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(100.000, 35.504),

                                new Pose(123.798, 35.554)
                        )
                ).setTangentHeadingInterpolation()

                .build();

        Path4 = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(123.798, 35.554),

                                new Pose(87.463, 13.017)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(67))

                .build();

        Path5 = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(87.463, 13.017),

                                new Pose(129.898, 8.965)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(67), Math.toRadians(0))

                .build();

        Path6 = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(129.898, 8.965),

                                new Pose(133.413, 12.331)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(27))

                .build();

        Path7 = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(133.413, 12.331),

                                new Pose(133.785, 40.694)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(27), Math.toRadians(27))

                .build();

        Path8 = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(133.785, 40.694),

                                new Pose(87.372, 12.959)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(27), Math.toRadians(67))

                .build();
    }
}
