package org.firstinspires.ftc.teamcode.common.util;

public class Clamp {
    public static double clamp(double min, double max, double value) {
        return Math.max(min, Math.min(max, value));
    }
}
