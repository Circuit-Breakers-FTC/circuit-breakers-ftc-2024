package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;

@TeleOp()
public class Skibidi extends OpMode {
    @Override
    public void init(){

    }
    @Override
    public void loop() {
        telemetry.addData("Left stick X",gamepad1.left_stick_x);
        telemetry.addData("Left stick Y",gamepad1.left_stick_y);
        telemetry.addData("Right stick Y",gamepad1.right_stick_y);
        telemetry.addData("Right stick X",gamepad1.right_stick_x);
        telemetry.addData("B button", gamepad1.b);
        telemetry.addData("X button", gamepad1.x);
        telemetry.addData("Y button", gamepad1.y);
        telemetry.addData("A button", gamepad1.a);
        telemetry.addData("Arrow up", gamepad1.dpad_up);
        telemetry.addData("Arrow down", gamepad1.dpad_down);
        telemetry.addData("Arrow left", gamepad1.dpad_left);
        telemetry.addData("Arrow right", gamepad1.dpad_right);

    }
}
