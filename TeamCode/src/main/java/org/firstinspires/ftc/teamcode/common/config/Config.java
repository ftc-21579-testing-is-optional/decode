package org.firstinspires.ftc.teamcode.common.config;

import com.bylazar.configurables.annotations.Configurable;

@Configurable
public class Config {
    public static double
            CAROUSEL_ROTATION_POWER = 0.50;
    public static double
            CAROUSEL_ROTATION_POWER_SLOW = 0.40;
    public static double
            INTAKE_ROTATION_POWER = 1.00;
    public static double
            ASSIST_INTAKE_ROTATION_POWER = 1.00;
    public static double
            YEETER_ROTATION_POWER_FULL = 0.80;
    public static double
            YEETER_ROTATION_POWER_MEDIUM = 0.60;
    public static double
            YEETER_ROTATION_POWER_LESS = 0.35;
    public static double
            YEETER_ROTATION_POWER_AUTO = 0.33;
    public static double
            YEETER_ROTATION_POWER_AUTO_LESS = 0.35;
    public static double
            YEETER_ROTATION_POWER_AUTO_FAR = 0.475;
    public static double
            SCOOPER_RECYCLE_POSITION = 0.48; // 0.45; // 0.42
    public static double
            SCOOPER_CATCH_POSITION = 0.60; // 0.58; // 0.55
    public static double
            SCOOPER_YEET_POSITION = 0.85; // 0.82; // 0.80
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
    public static double
            AUTO_FIELD_LATERAL_CORRECTION = 1.5;
    public static double
            AUTO_FIELD_YAW_CORRECTION = 0.1;
}
