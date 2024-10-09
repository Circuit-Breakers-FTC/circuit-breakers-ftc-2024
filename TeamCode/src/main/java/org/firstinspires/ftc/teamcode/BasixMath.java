package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class BasixMath extends OpMode {
    @Override
    public void init() {
        telemetry.addData("left stick Y", gamepad1.left_stick_y);
    }

    @Override
    public void loop() {
        double speedForward = -gamepad1.left_stick_y / 2.0;
        telemetry.addData("left stick Y", gamepad1.left_stick_y);
        telemetry.addData("Speed Forward", speedForward);
        telemetry.addData("right stick X", gamepad1.right_stick_x);
        telemetry.addData("right stick Y", gamepad1.right_stick_y);
        telemetry.addData("B button", gamepad1.b);
    }
}
