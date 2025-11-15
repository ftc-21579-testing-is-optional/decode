package org.firstinspires.ftc.teamcode.common.hardware;

import com.qualcomm.robotcore.hardware.Gamepad;

public class ControlMapping {
    private boolean prevSquare = false;
    private boolean prevCircle = false;
    private boolean prevTriangle = false;
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
        return this.applyDeadzone(this.gamepad.right_stick_x);
    }

    public int getCarouselDirection() {
        return (this.gamepad.left_bumper ? -1 : 0) + (this.gamepad.right_bumper ? 1 : 0);
    }

    private boolean prevLeftStick = false;  // track previous press
    private boolean intakeState = false;    // current toggle state

    /** Call each loop; returns true if intake should be active */
    public boolean getIntakeToggleState() {
        if (gamepad.left_stick_button && !prevLeftStick) {
            intakeState = !intakeState;  // flip toggle
        }
        prevLeftStick = gamepad.left_stick_button;
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

    private boolean prevCross = false;  // track previous Cross press
    private boolean yeeterState = false; // current toggle state

    /** Call each loop; returns true if Yeeter should be active */
    public boolean getYeeterToggleState() {
        // Detect new press (Cross = gamepad.cross on PS)
        if (gamepad.cross && !prevCross) {
            yeeterState = !yeeterState; // flip toggle
        }

        // Update previous state
        prevCross = gamepad.cross;

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
