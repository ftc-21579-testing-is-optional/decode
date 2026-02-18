package org.firstinspires.ftc.teamcode.pedro;

import com.pedropathing.control.PIDFCoefficients;
import com.pedropathing.follower.Follower;
import com.pedropathing.follower.FollowerConstants;
import com.pedropathing.ftc.FollowerBuilder;
import com.pedropathing.ftc.drivetrains.MecanumConstants;
import com.pedropathing.ftc.localization.constants.PinpointConstants;
import com.pedropathing.paths.PathConstraints;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class Constants {
    public static FollowerConstants followerConstants = new FollowerConstants()
            .mass(11.34)
            .forwardZeroPowerAcceleration(-36.0453) // -36.9887 -35.0783 -31.1947 -40.9196
            .lateralZeroPowerAcceleration(-68.5808) // -68.1747 -76.9064 -67.7540 -61.4882
            .translationalPIDFCoefficients(new PIDFCoefficients(0.08, 0, 0.005, 0.01))
            .headingPIDFCoefficients(new PIDFCoefficients(1.25, 0.0001, 0.06, 0.01))
            .centripetalScaling(0.0005);

    public static MecanumConstants driveConstants = new MecanumConstants()
            .maxPower(1)
            .xVelocity(75.6963) // 73.6216 74.1566 78.3046 76.7024
            .yVelocity(51.0001) // 49.5123 50.1629 52.1852 52.1398
            .rightFrontMotorName("motor2")
            .rightRearMotorName("motor3")
            .leftRearMotorName("motor1")
            .leftFrontMotorName("motor0")
            .leftFrontMotorDirection(DcMotorSimple.Direction.FORWARD)
            .leftRearMotorDirection(DcMotorSimple.Direction.REVERSE)
            .rightFrontMotorDirection(DcMotorSimple.Direction.REVERSE)
            .rightRearMotorDirection(DcMotorSimple.Direction.FORWARD);

//    public static PinpointConstants localizerConstants = new PinpointConstants()
//            .forwardPodY(6.45)
//            .strafePodX(5.87)
//            .distanceUnit(DistanceUnit.INCH)
//            .hardwareMapName("pinpoint")
//            .encoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD)
//            .forwardEncoderDirection(GoBildaPinpointDriver.EncoderDirection.FORWARD)
//            .strafeEncoderDirection(GoBildaPinpointDriver.EncoderDirection.FORWARD);

    public static PinpointConstants localizerConstants = new PinpointConstants()
            .forwardPodY(3.25)
            .strafePodX(1.375)
            .distanceUnit(DistanceUnit.INCH)
            .hardwareMapName("pinpoint")
            .encoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD)
            .forwardEncoderDirection(GoBildaPinpointDriver.EncoderDirection.FORWARD)
            .strafeEncoderDirection(GoBildaPinpointDriver.EncoderDirection.REVERSED);

    public static PathConstraints pathConstraints = new PathConstraints(0.99, 100, 1, 1);

    public static Follower createFollower(HardwareMap hardwareMap) {
        return new FollowerBuilder(followerConstants, hardwareMap)
                .pinpointLocalizer(localizerConstants)
                .pathConstraints(pathConstraints)
                .mecanumDrivetrain(driveConstants)
                .build();
    }
}