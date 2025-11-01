package org.firstinspires.ftc.teamcode.common.hardware;

import android.graphics.Color;

import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;
import com.qualcomm.robotcore.hardware.SwitchableLight;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.common.bot.Bot;
import org.firstinspires.ftc.teamcode.common.game.ArtifactColor;

public class ColorSensor {
    private Bot bot;
    private NormalizedColorSensor colorSensor;

    private float gain = 7;
    private int purpleColor = 300;
    private int greenColor = 145;
    private int maxError = 30;

    public ColorSensor(Bot bot) {
        this.bot = bot;
    }

    public void init() {
        this.colorSensor = this.bot.hardwareMap.get(NormalizedColorSensor.class, "sensor_color");
        this.colorSensor.setGain(this.gain);
    }

    public float[] getColor() {
        float[] hsvValues = new float[3];

        NormalizedRGBA colors = colorSensor.getNormalizedColors();

        Color.colorToHSV(colors.toColor(), hsvValues);

        if (this.bot.isDebugMode()) {
            this.bot.telemetry.addLine()
                    .addData("Hue", "%.3f", hsvValues[0])
                    .addData("Saturation", "%.3f", hsvValues[1])
                    .addData("Value", "%.3f", hsvValues[2]);
        }

        return hsvValues;
    }
    public ArtifactColor classify() {
        float[] hsvValues = this.getColor();

        float purpleError = Math.abs(hsvValues[0] - purpleColor);
        float greenError = Math.abs(hsvValues[0] - greenColor);

        if (this.bot.isDebugMode()) {
            this.bot.telemetry.addData("Purple Error", purpleError);
            this.bot.telemetry.addData("Green Error", greenError);
        }

        ArtifactColor classification = ArtifactColor.UNKNOWN;

        if (purpleError < maxError && greenError < maxError) {
            // This should probably never happen
        }
        else if (purpleError < maxError) {
            classification = ArtifactColor.PURPLE;
        }
        else if (greenError < maxError) {
            classification = ArtifactColor.GREEN;
        }

        if (this.bot.isDebugMode()) {
            this.bot.telemetry.addData("Classification", classification);
        }

        return classification;
    }
}
