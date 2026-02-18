package org.firstinspires.ftc.teamcode.opmode.auto.paths;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;

public class FarPathsRed extends FarPathsBase {
    public FarPathsRed(Follower follower) {
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

                                new Pose(103.570, 35.504)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(67), Math.toRadians(0))

                .build();

        Path3 = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(103.570, 35.504),

                                new Pose(125.137, 35.554)
                        )
                ).setTangentHeadingInterpolation()

                .build();

        Path4 = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(125.137, 35.554),

                                new Pose(87.463, 13.017)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(67))

                .build();

        Path5 = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(87.463, 13.017),

                                new Pose(102.972, 59.841)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(67), Math.toRadians(0))

                .build();

        Path6 = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(102.972, 59.841),

                                new Pose(124.339, 59.785)
                        )
                ).setTangentHeadingInterpolation()

                .build();

        Path7 = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(124.339, 59.785),

                                new Pose(96.595, 47.983)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(67))

                .build();
    }
}
