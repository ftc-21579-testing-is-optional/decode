package org.firstinspires.ftc.teamcode.common.hardware;

import com.qualcomm.robotcore.hardware.Gamepad;

public class ControlMapping {
    private boolean prevSquare = false;
    private boolean prevCircle = false;
    private boolean prevTriangle = false;
    private Gamepad gamepad;
    private double deadzone;
    private double controlCurvePower;

//    private boolean padUpCurrent = false, padUpPrevious = false;
//    private boolean padDownCurrent = false, padDownPrevious = false;

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
    private boolean intakeState = false;    // current toggle state

    /** Call each loop; returns true if intake should be active */
    public boolean getIntakeToggleState() {
        if (gamepad.dpad_down && !prevDpadDown) {
            intakeState = !intakeState;  // flip toggle
        }
        prevDpadDown = gamepad.dpad_down;
        return intakeState;
    }

    public void updateStatefulInput() {
//        this.updateIntakeState();
    }

    public int getScooperToggleCommand() {
        int command = -1;

        if (gamepad.square) command = 0;
        if (gamepad.circle) command = 1;
        if (gamepad.triangle) command = 2;

        // Update previous states
        prevSquare = gamepad.square;
        prevCircle = gamepad.circle;
        prevTriangle = gamepad.triangle;

        return command;
    }

    private boolean prevDpadUp = false;  // track previous Cross press
    private boolean yeeterState = false; // current toggle state

    /** Call each loop; returns true if Yeeter should be active */
    public boolean getYeeterToggleState() {
        // Detect new press
        if (gamepad.dpad_up && !prevDpadUp) {
            yeeterState = !yeeterState; // flip toggle
        }

        // Update previous state
        prevDpadUp = gamepad.dpad_up;

        // Return current toggle state
        return yeeterState;
    }

    private boolean prevDpadLeft = false;  // track previous Cross pres
    public boolean getYeeterToggleStateLess() {
        // Detect new press
        if (gamepad.dpad_left && !prevDpadLeft) {
            yeeterState = !yeeterState; // flip toggle
        }

        // Update previous state
        prevDpadLeft = gamepad.dpad_left;

        // Return current toggle state
        return yeeterState;
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
