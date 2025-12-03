package org.firstinspires.ftc.teamcode.common.hardware;

import com.qualcomm.robotcore.hardware.Gamepad;

public class ControlMapping {
    private Gamepad gamepad;
    private double deadzone;
    private double controlCurvePower;

    public ControlMapping(Gamepad gamepad, double deadzone, double controlCurveExponent) {
        this.gamepad = gamepad;
        this.deadzone = deadzone;
        this.controlCurvePower = controlCurveExponent;
    }

    private double applyCurve(double value) {
        return Math.signum(value) * Math.abs(Math.pow(value, this.controlCurvePower));
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
        return this.applyDeadzone(this.gamepad.right_stick_x);
    }

    public int getCarouselDirection() {
        return (this.gamepad.left_bumper ? -1 : 0) + (this.gamepad.right_bumper ? 1 : 0);
    }

    private boolean prevDpadDown = false;  // track previous press
    private boolean intakeState = false;   // current toggle state

    /** Call each loop; returns true if intake should be active */
    public boolean getIntakeToggleState() {
        if (this.gamepad.dpad_down && !this.prevDpadDown) {
            this.intakeState = !this.intakeState;  // flip toggle
        }

        this.prevDpadDown = gamepad.dpad_down;

        return this.intakeState;
    }

    public ScooperState getScooperState() {
        ScooperState command = ScooperState.UNKNOWN;

        if (gamepad.square) {
            command = ScooperState.RECYCLE;
        }
        else if (gamepad.circle) {
            command = ScooperState.CATCH;
        }
        else if (gamepad.triangle) {
            command = ScooperState.YEET;
        }

        return command;
    }

    private boolean prevDpadUp = false;  // track previous Cross press
    private boolean yeeterState = false; // current toggle state

    /** Call each loop; returns true if Yeeter should be active */
    public boolean getYeeterToggleState() {
        // Detect new press
        if (this.gamepad.dpad_up && !this.prevDpadUp) {
            this.yeeterState = !this.yeeterState; // flip toggle
        }

        // Update previous state
        this.prevDpadUp = this.gamepad.dpad_up;

        // Return current toggle state
        return this.yeeterState;
    }

    private boolean prevDpadLeft = false;  // track previous Cross pres
    public boolean getYeeterToggleStateLess() {
        // Detect new press
        if (this.gamepad.dpad_left && !this.prevDpadLeft) {
            this.yeeterState = !this.yeeterState; // flip toggle
        }

        // Update previous state
        this.prevDpadLeft = this.gamepad.dpad_left;

        // Return current toggle state
        return this.yeeterState;
    }
}
