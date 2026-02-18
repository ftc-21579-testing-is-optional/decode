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

    private boolean prevCIRCLE = false;  // track previous press
    private boolean prevSQUARE = false;  // track previous press
    private boolean prevCROSS = false;  // track previous press
    private IntakeDirection intakeState = IntakeDirection.STOP;   // current toggle state

    /** Call each loop; returns true if intake should be active */
    public IntakeDirection getIntakeToggleState() { //Driver controls intake
        if (this.gamepad1.circle && !this.prevCIRCLE) {
            this.intakeState = IntakeDirection.OUT;
        }

        if (this.gamepad1.square && !this.prevSQUARE) {
            this.intakeState = IntakeDirection.IN;
        }

        if (this.gamepad1.cross && !this.prevCROSS) {
            this.intakeState = IntakeDirection.STOP;
        }

        this.prevCIRCLE = this.gamepad1.circle;
        this.prevSQUARE = this.gamepad1.square;
        this.prevCROSS = this.gamepad1.cross;

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

//    private boolean prevDpadUp = false;
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
//    private boolean prevDpadLeft = false;
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
