package org.firstinspires.ftc.teamcode.common;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.common.hardware.Carousel;
import org.firstinspires.ftc.teamcode.common.hardware.Intake;
import org.firstinspires.ftc.teamcode.common.hardware.Vision;
import org.firstinspires.ftc.teamcode.common.hardware.Yeeter;

public class Bot {
    public final HardwareMap hardwareMap;
    private Carousel carousel;
    private Intake intake;
    private Yeeter yeeter;
    private Vision vision;

    public Bot(HardwareMap hMap) {
        this.hardwareMap = hMap;
    }
}
