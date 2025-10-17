package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Gamepad;

public class ControlMapping {
    public Gamepad gamepad;

    public ControlMapping(Gamepad gamepad) {
        this.gamepad = gamepad;
    }

    public float getDrivePower() {
        return this.gamepad.left_stick_y;
    }

    public float getPValue() {
        return this.gamepad.left_stick_y;
    }
}
