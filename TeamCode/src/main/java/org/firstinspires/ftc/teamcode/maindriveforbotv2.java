package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;


@Config
@TeleOp
public class maindriveforbotv2 extends LinearOpMode {


    private DcMotor armRotate;
    ;
    private Servo openCloseClaw;
    private Servo rotateClaw;
    private Servo lowerClaw;

    public static double CLAW_UP = 0.85;
    public static double CLAW_DOWN = 0.6;
    public static double CLAW_OPEN = 0.65;
    public static double CLAW_CLOSE = 0.0;
    public static double CLAW_ONE = 0.5;
    public static double CLAW_TWO = 0.325;
    public static double CLAW_THREE = 0.15;
    public static double CLAW_FOUR = 0.675;
    /**
     * This function is executed when this Op Mode is selected from the Driver Station.
     */
    @Override
    public void runOpMode() {
        openCloseClaw = hardwareMap.get(Servo.class, "openCloseClaw");
        rotateClaw = hardwareMap.get(Servo.class, "rotateClaw");
        lowerClaw = hardwareMap.get(Servo.class, "lowerClaw");
        armRotate = hardwareMap.get(DcMotor.class, "armRotate");
        waitForStart();
        while (opModeIsActive()) {
            telemetry.addData("lower.position", lowerClaw.getPosition());
            telemetry.update();
            if (gamepad2.y) {
                lowerClaw.setPosition(CLAW_UP);
            }
            if (gamepad2.a) {
                lowerClaw.setPosition(CLAW_DOWN);
            }
            if (gamepad2.x) {
                openCloseClaw.setPosition(CLAW_OPEN);
            }
            if (gamepad2.b) {
                openCloseClaw.setPosition(CLAW_CLOSE);
            }
            if (gamepad2.dpad_up) {
                rotateClaw.setPosition(CLAW_ONE);
            }
            if (gamepad2.dpad_right) {
                rotateClaw.setPosition(CLAW_TWO);
            }
            if (gamepad2.dpad_down) {
                rotateClaw.setPosition(CLAW_THREE);
            }
            if (gamepad2.dpad_left) {
                rotateClaw.setPosition(CLAW_FOUR);
            }

        }

    }
}