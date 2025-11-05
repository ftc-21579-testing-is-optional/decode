package org.firstinspires.ftc.teamcode.common.hardware;

import com.qualcomm.robotcore.hardware.Gamepad;

public class ControlMapping {
    private Gamepad gamepad;
    private double deadzone;

    public ControlMapping(Gamepad gamepad, double deadzone) {
        this.gamepad = gamepad;
        this.deadzone = deadzone;
    }

    private double applyDeadzone(double value) {
        return Math.abs(value) > this.deadzone ? value : 0;
    }

    public double getMotionAxial() {
        return -this.applyDeadzone(this.gamepad.left_stick_y);
    }

    public double getMotionLateral() {
        return this.applyDeadzone(this.gamepad.left_stick_x);
    }

    public double getMotionYaw() {
        return -this.applyDeadzone(this.gamepad.right_stick_x);
    }
}
