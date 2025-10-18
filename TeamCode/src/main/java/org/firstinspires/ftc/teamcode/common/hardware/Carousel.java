package org.firstinspires.ftc.teamcode.common.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.common.Bot;
import org.firstinspires.ftc.teamcode.common.game.Artifact;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Carousel {
    private final Bot bot;
    private final ArrayList<Artifact> artifacts = new ArrayList<>(3);

    DcMotor carouselMotor;

    public Carousel(Bot bot) {
        this.bot = bot;
        this.carouselMotor = this.bot.hardwareMap.get(DcMotor.class, "motor0");
    }

    public Artifact removeArtifact() {
        return this.artifacts.remove(this.artifacts.size() - 1);
    }

    public void addArtifact(Artifact artifact) {
        this.artifacts.add(0, artifact);
    }

    public void rotateCarousel() {
        Collections.rotate(this.artifacts, -1);
    }
}
