package org.firstinspires.ftc.teamcode.opmode.auto.paths;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;

public class ClosePathsBlue extends ClosePathsBase {
    public ClosePathsBlue(Follower follower) {
        Path1 = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(23.500, 126.446),

                                new Pose(59.421, 95.950)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(144), Math.toRadians(144))

                .build();

        Path2 = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(59.421, 95.950),

                                new Pose(44.215 + 3, 86.380 - 1)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(144), Math.toRadians(180))

                .build();

        Path3 = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(44.215 + 3, 86.380 - 1),

                                new Pose(21.405, 85.727 - 1)
                        )
                ).setTangentHeadingInterpolation()

                .build();

        Path4 = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(21.405, 85.727 - 1),

                                new Pose(59.496, 96.025)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(144))

                .build();

        Path5 = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(59.496, 96.025),

                                new Pose(43.765 + 3, 61.775 - 1)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(144), Math.toRadians(180))

                .build();

        Path6 = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(43.765 + 3, 61.775 - 1),

                                new Pose(22.140, 61.570 - 1)
                        )
                ).setTangentHeadingInterpolation()

                .build();

        Path7 = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(22.140, 61.570 - 1),

                                new Pose(59.256, 96.033)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(144))

                .build();

        Path8 = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(59.256, 96.033),

                                new Pose(43.050 + 3, 37.793 - 1)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(144), Math.toRadians(180))

                .build();

        Path9 = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(43.050 + 3, 37.793 - 1),

                                new Pose(21.256, 37.826 - 1)
                        )
                ).setTangentHeadingInterpolation()

                .build();

        Path10 = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(21.256, 37.826 - 1),

                                new Pose(59.446 - 24, 95.934 - 36)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(144))

                .build();

        Path11 = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(59.446 - 24, 95.934 - 36),

                                new Pose(43.116, 78.860)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(144), Math.toRadians(144))

                .build();
    }
}
