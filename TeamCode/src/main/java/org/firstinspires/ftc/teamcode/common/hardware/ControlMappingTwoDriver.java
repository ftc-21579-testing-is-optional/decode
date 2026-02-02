package org.firstinspires.ftc.teamcode.common.hardware;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.common.config.Config;

public class ControlMappingTwoDriver {
    private Gamepad gamepad1;
    private Gamepad gamepad2;
    private double deadzone;
    private double controlCurvePower;

    public ControlMappingTwoDriver(Gamepad gamepad1, Gamepad gamepad2) {
        this.gamepad1 = gamepad1;
        this.gamepad2 = gamepad2;

        this.deadzone = Config.DEADZONE;
        this.controlCurvePower = Config.CONTROL_CURVE_EXP;
    }

    private double applyControlCurve(double value) {
        if (Config.USE_CONTROL_CURVE) {
            return Math.signum(value) * Math.abs(Math.pow(value, this.controlCurvePower));
        }

        return value;
    }

    private double applyDeadzone(double value) {
        return Math.abs(value) > this.deadzone ? value : 0;
    }

    private double applyMutations(double value) {
        return this.applyControlCurve(this.applyDeadzone(value));
    }

    public double getMotionAxial() {
        return -this.applyMutations(this.gamepad1.left_stick_y);
    }

    public double getMotionLateral() {
        return this.applyMutations(this.gamepad1.left_stick_x);
    }

    public double getMotionYaw() {
        return this.applyMutations(this.gamepad1.right_stick_x);
    }

    public CarouselDirection getCarouselDirection() {
//        return (this.gamepad.left_bumper ? -1 : 0) + (this.gamepad.right_bumper ? 1 : 0);

        if (this.gamepad2.left_bumper) {
            return CarouselDirection.DOWN;
        }
        else if (this.gamepad2.right_bumper) {
            return CarouselDirection.UP;
        }
        else {
            return CarouselDirection.STOP;
        }
    }

    private boolean prevDpadDown = false;  // track previous press
    private boolean intakeState = false;   // current toggle state

    /** Call each loop; returns true if intake should be active */
    public boolean getIntakeToggleState() {
        if (this.gamepad2.dpad_down && !this.prevDpadDown) {
            this.intakeState = !this.intakeState;  // flip toggle
        }

        this.prevDpadDown = this.gamepad2.dpad_down;

        return this.intakeState;
    }

    public ScooperState getScooperState() {
        ScooperState command = ScooperState.UNKNOWN;

        if (this.gamepad2.square) {
            command = ScooperState.RECYCLE;
        }
        else if (this.gamepad2.circle) {
            command = ScooperState.CATCH;
        }
        else if (this.gamepad2.triangle) {
            command = ScooperState.YEET;
        }

        return command;
    }

    public YeeterMode getYeeterMode() {
        YeeterMode mode = YeeterMode.UNKNOWN;

        if (this.gamepad2.left_stick_button) {
            mode = YeeterMode.STOP;
        }
        if (this.gamepad2.dpad_left) {
            mode = YeeterMode.MEDIUM;
        }
        else if (this.gamepad2.dpad_up) {
            mode = YeeterMode.FULL;
        }
        else if (this.gamepad2.dpad_right) {
            mode = YeeterMode.LESS;
        }

        return mode;
    }

//    private boolean prevDpadUp = false;  // track previous Cross press
//    private boolean yeeterState = false; // current toggle state
//
//    /** Call each loop; returns true if Yeeter should be active */
//    public boolean getYeeterToggleState() {
//        // Detect new press
//        if (this.gamepad2.dpad_up && !this.prevDpadUp) {
//            this.yeeterState = !this.yeeterState; // flip toggle
//        }
//
//        // Update previous state
//        this.prevDpadUp = this.gamepad2.dpad_up;
//
//        // Return current toggle state
//        return this.yeeterState;
//    }
//
//    private boolean prevDpadLeft = false;  // track previous Cross pres
//    public boolean getYeeterToggleStateLess() {
//        // Detect new press
//        if (this.gamepad2.dpad_left && !this.prevDpadLeft) {
//            this.yeeterState = !this.yeeterState; // flip toggle
//        }
//
//        // Update previous state
//        this.prevDpadLeft = this.gamepad2.dpad_left;
//
//        // Return current toggle state
//        return this.yeeterState;
//    }
}
