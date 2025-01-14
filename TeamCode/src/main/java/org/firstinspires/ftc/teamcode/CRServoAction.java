package org.firstinspires.ftc.teamcode;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.CRServo;

public class CRServoAction implements Action {
    private CRServo servo;
    private double power;
    private boolean initialized = false;

    CRServoAction(CRServo servo, double power) {
        this.servo = servo;
        this.power = power;
    }
    @Override
    public boolean run(@NonNull TelemetryPacket telemetryPacket) {
        if (!initialized) {
            initialized = true;
            servo.setPower(power);
        }
        return false;
    }
}
