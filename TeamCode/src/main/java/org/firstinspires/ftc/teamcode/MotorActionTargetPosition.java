package org.firstinspires.ftc.teamcode;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.DcMotor;

public class MotorActionTargetPosition implements Action {
    private DcMotor motor;
    private double power;
    private int targetPosition;
    private boolean initialized = false;
    private double maxError;
    MotorActionTargetPosition(DcMotor motor, int targetPosition, double power, double maxError) {
        this.motor = motor;
        this.targetPosition = targetPosition;
        this.power = power;
        this.maxError = maxError;
    }

    @Override
    public boolean run(@NonNull TelemetryPacket telemetryPacket) {
        if (!initialized) {
            initialized = true;
            motor.setTargetPosition(targetPosition);
            motor.setPower(power);
        }
        return Math.abs(motor.getCurrentPosition()- motor.getTargetPosition())<this.maxError;
    }
}
