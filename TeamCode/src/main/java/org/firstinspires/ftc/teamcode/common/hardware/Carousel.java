package org.firstinspires.ftc.teamcode.common.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.common.bot.Bot;
import org.firstinspires.ftc.teamcode.common.game.Artifact;
import org.firstinspires.ftc.teamcode.common.util.DcMotorEnc;

import java.util.ArrayList;
import java.util.Collections;

public class Carousel {
    private final Bot bot;
    private int capacity;
    private final ArrayList<Artifact> artifacts = new ArrayList<>(capacity);

//    DcMotor carouselMotor;
    DcMotorEnc carouselMotor;

    public Carousel(Bot bot) {
        this.bot = bot;
//        this.carouselMotor = this.bot.hardwareMap.get(DcMotor.class, "motor0");
        this.carouselMotor = new DcMotorEnc(this.bot.hardwareMap.get(DcMotor.class, "motor0"));
    }

    public Artifact removeArtifact() {
        return this.artifacts.remove(this.artifacts.size() - 1);
    }

    public void addArtifact(Artifact artifact) {
        this.artifacts.add(0, artifact);
    }

    public void rotateCarousel() {
        this.carouselMotor.rotateBy(360 * 2, 0.5f);
        Collections.rotate(this.artifacts, -1);
    }

//    public boolean isFull() {
//        this.artifacts.
//    }
}
