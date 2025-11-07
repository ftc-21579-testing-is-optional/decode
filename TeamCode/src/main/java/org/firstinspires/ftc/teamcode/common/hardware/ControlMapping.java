package org.firstinspires.ftc.teamcode.common.hardware;

import com.qualcomm.robotcore.hardware.Gamepad;

public class ControlMapping {
    private Gamepad gamepad;
    private double deadzone;
//    private boolean padUpCurrent = false, padUpPrevious = false;
//    private boolean padDownCurrent = false, padDownPrevious = false;

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

    public void updateStatefulInput() {
//        this.updateIntakeState();
    }

//    private void updateIntakeState() {
//        this.padUpPrevious = this.padUpCurrent;
//        this.padUpCurrent = this.gamepad.dpad_up;
//
//        this.padDownPrevious = this.padDownCurrent;
//        this.padDownCurrent = this.gamepad.dpad_down;
//
//        if (this.padUpCurrent && !this.padUpPrevious) {
//            this.intakeDirection += (this.intakeDirection < 1) ? 1 : 0;
//        }
//
//        if (this.padDownCurrent && !this.padDownPrevious) {
//            this.intakeDirection -= (this.intakeDirection > -1) ? 1 : 0;
//        }
//    }
}
