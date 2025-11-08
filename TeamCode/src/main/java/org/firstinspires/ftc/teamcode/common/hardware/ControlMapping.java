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

    public int getCarouselDirection() {
        return (this.gamepad.left_bumper ? -1 : 0) + (this.gamepad.right_bumper ? 1 : 0);
    }

    public int getIntakeDirection() {
        return (this.gamepad.dpad_down ? -1 : 0) + (this.gamepad.dpad_up ? 1 : 0);
    }

    public float getScooperPosition() {
        return this.scooperPos;
    }

    public float getYeeterPower() {
        return this.gamepad.left_trigger - this.gamepad.right_trigger;
    }

    public void updateStatefulInput() {
        this.updateScooperPos();
    }

    private boolean padLeftCurrent = false, padLeftPrevious = false;
    private boolean padRightCurrent = false, padRightPrevious = false;
    private float scooperPos;
    private void updateScooperPos() {
        this.padLeftPrevious = this.padLeftCurrent;
        this.padLeftCurrent = this.gamepad.dpad_up;

        this.padRightPrevious = this.padRightCurrent;
        this.padRightCurrent = this.gamepad.dpad_down;

        if (this.padLeftCurrent && !this.padLeftPrevious) {
            this.scooperPos += (this.scooperPos < 1) ? 0.1f : 0;
        }

        if (this.padRightCurrent && !this.padRightPrevious) {
            this.scooperPos -= (this.scooperPos > 0) ? 0.1f : 0;
        }
    }
}
