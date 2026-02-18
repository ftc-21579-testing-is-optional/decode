package org.firstinspires.ftc.teamcode.opmode.auto.paths;


import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierCurve;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;

public class TestPaths {
    public PathChain Path1;
    public PathChain Path2;
    public PathChain Path3;

    public TestPaths(Follower follower) {
        Path1 = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(72.066, 71.669),

                                new Pose(96.017, 71.702)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(90), Math.toRadians(36))

                .build();

        Path2 = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(96.017, 71.702),

                                new Pose(96.041, 95.636)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(36), Math.toRadians(0))

                .build();

        Path3 = follower.pathBuilder().addPath(
                        new BezierCurve(
                                new Pose(96.041, 95.636),
                                new Pose(71.632, 96.512),
                                new Pose(71.950, 71.537)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(90))

                .build();
    }
}
