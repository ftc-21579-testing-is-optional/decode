package org.firstinspires.ftc.teamcode.opmode.auto.paths;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;

public class ClosePathsRed extends ClosePathsBase {
    public ClosePathsRed(Follower follower) {
        Path1 = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(120.500, 126.500),

                                new Pose(87.835, 95.802)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(36), Math.toRadians(36))

                .build();

        Path2 = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(87.835, 95.802),

                                new Pose(100.744 - 3, 85.339 - 1)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(36), Math.toRadians(0))

                .build();

        Path3 = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(100.744 - 3, 85.339 - 1),

                                new Pose(122.116, 85.281 - 1)
                        )
                ).setTangentHeadingInterpolation()

                .build();

        Path4 = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(122.116, 85.281 - 1),

                                new Pose(87.909, 95.579)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(36))

                .build();

        Path5 = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(87.909, 95.579),

                                new Pose(101.484 - 3, 61.626 - 1)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(36), Math.toRadians(0))

                .build();

        Path6 = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(101.484 - 3, 61.626 - 1),

                                new Pose(123.149, 61.570 - 1)
                        )
                ).setTangentHeadingInterpolation()

                .build();

        Path7 = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(123.149, 61.570 - 1),

                                new Pose(87.967, 95.438)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(36))

                .build();

        Path8 = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(87.967, 95.438),

                                new Pose(101.066 - 3, 37.198 - 1)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(36), Math.toRadians(0))

                .build();

        Path9 = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(101.066 - 3, 37.198 - 1),

                                new Pose(122.860, 36.934 - 1)
                        )
                ).setTangentHeadingInterpolation()

                .build();

        Path10 = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(122.860, 36.934 - 1),

                                new Pose(87.860 + 24, 95.636 - 36)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(36))

                .build();

        Path11 = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(87.860 + 24, 95.636 - 36),

                                new Pose(101.694, 82.298)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(36), Math.toRadians(36))

                .build();
    }
}
