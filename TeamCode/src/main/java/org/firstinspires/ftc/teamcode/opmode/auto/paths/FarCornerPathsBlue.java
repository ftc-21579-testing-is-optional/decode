package org.firstinspires.ftc.teamcode.opmode.auto.paths;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;

public class FarCornerPathsBlue extends FarCornerPathsBase {
    public FarCornerPathsBlue(Follower follower) {
        Path1 = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(56.430, 8.033),

                                new Pose(56.410, 13.835)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(90), Math.toRadians(113))

                .build();

        Path2 = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(56.410, 13.835),

                                new Pose(41.983, 36.099)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(113), Math.toRadians(180))

                .build();

        Path3 = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(41.983, 36.099),

                                new Pose(19.815, 36.149)
                        )
                ).setTangentHeadingInterpolation()

                .build();

        Path4 = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(19.815, 36.149),

                                new Pose(56.633, 13.726)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(113))

                .build();

        Path5 = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(56.633, 13.726),

                                new Pose(13.716, 9.114)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(113), Math.toRadians(180))

                .build();

        Path6 = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(13.716, 9.114),

                                new Pose(10.388, 13.372)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(153))

                .build();

        Path7 = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(10.388, 13.372),

                                new Pose(10.207, 40.909)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(153), Math.toRadians(153))

                .build();

        Path8 = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(10.207, 40.909),

                                new Pose(56.322, 13.669)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(153), Math.toRadians(113))

                .build();
    }
}
