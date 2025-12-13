package org.firstinspires.ftc.teamcode.common.hardware;

import android.graphics.Color;

import com.qualcomm.hardware.andymark.AndyMarkColorSensor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;

import org.firstinspires.ftc.teamcode.common.bot.Bot;
import org.firstinspires.ftc.teamcode.common.game.ArtifactColor;

public class ColorSensor {
    private Bot bot;
    private AndyMarkColorSensor colorSensor;

    private float gain = 10;
    private int purpleColor = 300;
    private int greenColor = 130;
    private int maxError = 90;

    public ColorSensor(Bot bot) {
        this.bot = bot;
    }

    public void init() {
        this.colorSensor = this.bot.hardwareMap.get(AndyMarkColorSensor.class, "sensor_color");
        this.colorSensor.initialize();
        this.colorSensor.enableLed(true);
        this.colorSensor.setGain(this.gain);
    }

    public float[] getColorRGB() {
        NormalizedRGBA colors = colorSensor.getNormalizedColors();

        float[] rgbValues = {colors.red, colors.green, colors.blue};

        if (this.bot.isDebugMode()) {
            this.bot.telemetry.addLine()
                    .addData("R", "%.3f", colors.red)
                    .addData("G", "%.3f", colors.green)
                    .addData("B", "%.3f", colors.blue);
            this.bot.telemetry.addLine()
                    .addData("Hue", "%.3f", rgbValues[0])
                    .addData("Saturation", "%.3f", rgbValues[1])
                    .addData("Value", "%.3f", rgbValues[2]);
        }

        return rgbValues;
    }

    public float[] getColorHSV() {
        float[] hsvValues = new float[3];

        NormalizedRGBA colors = colorSensor.getNormalizedColors();

        Color.colorToHSV(colors.toColor(), hsvValues);

        if (this.bot.isDebugMode()) {
            this.bot.telemetry.addLine()
                    .addData("R", "%.3f", colors.red)
                    .addData("G", "%.3f", colors.green)
                    .addData("B", "%.3f", colors.blue);
            this.bot.telemetry.addLine()
                    .addData("Hue", "%.3f", hsvValues[0])
                    .addData("Saturation", "%.3f", hsvValues[1])
                    .addData("Value", "%.3f", hsvValues[2]);
        }

        return hsvValues;
    }
    public ArtifactColor classify() {
        float[] hsvValues = this.getColorHSV();

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

//        if (hsvValues[0] < maxError) {
//            classification = ArtifactColor.UNKNOWN;
//        }
//        else if (purpleError < greenError) {
//            classification = ArtifactColor.PURPLE;
//        }
//        else if (greenError < purpleError) {
//            classification = ArtifactColor.GREEN;
//        }

        if (this.bot.isDebugMode()) {
            this.bot.telemetry.addData("Classification", classification);
        }

        return classification;
    }
}
