package org.firstinspires.ftc.teamcode.opmode.auto.paths;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;

public class FarPathsBlue extends FarPathsBase {
    public FarPathsBlue(Follower follower) {
        Path1 = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(56.674, 8.479),

                                new Pose(59.421, 95.950)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(90), Math.toRadians(144))

                .build();

        Path2 = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(59.421, 95.950),

                                new Pose(44.215, 86.380)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(144), Math.toRadians(180))

                .build();

        Path3 = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(44.215, 86.380),

                                new Pose(21.405, 85.727)
                        )
                ).setTangentHeadingInterpolation()

                .build();

        Path4 = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(21.405, 85.727),

                                new Pose(59.496, 96.025)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(144))

                .build();

        Path5 = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(59.496, 96.025),

                                new Pose(43.765, 61.775)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(144), Math.toRadians(180))

                .build();

        Path6 = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(43.765, 61.775),

                                new Pose(22.140, 61.570)
                        )
                ).setTangentHeadingInterpolation()

                .build();

        Path7 = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(22.140, 61.570),

                                new Pose(59.256, 96.033)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(144))

                .build();
    }
}
