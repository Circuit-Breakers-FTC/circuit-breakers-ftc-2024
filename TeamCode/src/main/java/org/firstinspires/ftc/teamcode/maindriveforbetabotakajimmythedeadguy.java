package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;



@Config
@TeleOp
public class maindriveforbotv2 extends LinearOpMode {


    private DcMotor armRotate;
    private Servo openCloseClaw;
    private Servo rotateClaw;
    private Servo lowerClaw;
    private DcMotor frontLeft;
    private DcMotor frontRight;
    private DcMotor backLeft;
    private DcMotor backRight;


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
        frontLeft = hardwareMap.get(DcMotor.class, "backRight");
        frontRight = hardwareMap.get(DcMotor.class, "backLeft");
        backLeft = hardwareMap.get(DcMotor.class, "frontRight");
        backRight = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight.setDirection(DcMotor.Direction.REVERSE);
        waitForStart();
        while (opModeIsActive()) {
            telemetry.addData("lower.position", lowerClaw.getPosition());
            telemetry.addData("rotate claw", rotateClaw.getPosition());
            telemetry.addData("openclose", openCloseClaw.getPosition());
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

            double x = gamepad1.left_stick_x; // Strafe left/right
            double y = -gamepad1.left_stick_y; // Forward/backward
            double rotation = gamepad1.right_stick_x; // Rotate

            // Calculate motor powers
            double frontLeftPower = y + x + rotation;
            double frontRightPower = y - x - rotation;
            double backLeftPower = y - x + rotation;
            double backRightPower = y + x - rotation;
            double slowness = (.5);
            // Normalize motor powers to ensure they don't exceed 1.0
            double maxPower = Math.max(Math.abs(frontLeftPower), Math.max(Math.abs(frontRightPower),
                    Math.max(Math.abs(backLeftPower), Math.abs(backRightPower))));
            if (maxPower > 1.0) {
                frontLeftPower /= maxPower;
                frontRightPower /= maxPower;
                backLeftPower /= maxPower;
                backRightPower /= maxPower;
            }

            // Set motor powers
            frontLeft.setPower(frontLeftPower*slowness);
            frontRight.setPower(frontRightPower*slowness);
            backLeft.setPower(backLeftPower*slowness);
            backRight.setPower(backRightPower*slowness);

        }

    }
}