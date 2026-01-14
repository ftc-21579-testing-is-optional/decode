package org.firstinspires.ftc.teamcode.common.config;

import com.bylazar.configurables.annotations.Configurable;

@Configurable
public class Config {
    public static double
            CAROUSEL_ROTATION_POWER = 1.00;
    public static double
            INTAKE_ROTATION_POWER = 1.00;
    public static double
            YEETER_ROTATION_POWER_FULL = 1.00;
    public static double
            YEETER_ROTATION_POWER_LESS = 0.325;
    public static double
            YEETER_ROTATION_POWER_AUTO = 0.3;
    public static double
            SCOOPER_RECYCLE_POSITION = 0.0;
    public static double
            SCOOPER_CATCH_POSITION = 0.25;
    public static double
            SCOOPER_YEET_POSITION = 0.8;
    public static double
            DEADZONE = 0.15;
    public static boolean
            USE_CONTROL_CURVE = true;
    public static double
            CONTROL_CURVE_EXP = 2.0;
    public static double
            MIN_SHOOTING_DISTANCE = 4 * 12.0;
    public static double
            MAX_SHOOTING_DISTANCE = 5 * 12.0;
}
