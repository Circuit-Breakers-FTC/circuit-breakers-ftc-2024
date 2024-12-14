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

    //Set the motors

    private Servo openCloseClaw;
    private Servo rotateClaw;
    private Servo lowerClaw;
    private DcMotor frontLeft;
    private DcMotor frontRight;
    private DcMotor backLeft;
    private DcMotor backRight;
    private DcMotor specimanLifter;
    private Servo specimanArm;
    private Servo specimanClaw;

    //Changable Variables

    public static double CLAW_UP = 0.85;
    public static double CLAW_DOWN = 0.6;
    public static double CLAW_OPEN = 0.65;
    public static double CLAW_CLOSE = 0.0;
    public static double CLAW_ONE = 0.5;
    public static double CLAW_TWO = 0.325;
    public static double CLAW_THREE = 0.15;
    public static double CLAW_FOUR = 0.675;
    public static double SPECIMANCLAWCLOSE = 0.5;
    public static double SPECIMANARMUP = 0;

    //Function is Executed when OpMode is initiated

    @Override
    public void runOpMode() throws InterruptedException {

        //Set Drive Motors

        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        frontRight.setDirection(DcMotor.Direction.FORWARD);
        backLeft.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.FORWARD);

        //Set Sample Arm Motors

        openCloseClaw = hardwareMap.get(Servo.class, "openCloseClaw");
        rotateClaw = hardwareMap.get(Servo.class, "rotateClaw");
        lowerClaw = hardwareMap.get(Servo.class, "lowerClaw");
        armRotate = hardwareMap.get(DcMotor.class, "armRotate");

        //Set Speciman Arm Motors

        specimanLifter = hardwareMap.get(DcMotor.class,"specimanLifter");
        specimanClaw = hardwareMap.get(Servo.class,"specimanClaw");
        specimanArm = hardwareMap.get(Servo.class, "specimanArm");

        waitForStart(); //Wait for Opmode Activation

        while (opModeIsActive()) {

            telemetry.addData("lower.position", lowerClaw.getPosition());
            telemetry.update();

            //Main Driving Options
                boolean fastMode = false;
                if (gamepad1.left_stick_button) {
                    fastMode = true;
                } else {
                    fastMode = false;
                  telemetry.addLine("right bumper for fast mode!");
                }

                if (gamepad1.y) {
                    lowerClaw.setPosition(CLAW_UP);
                }

                if (gamepad1.a) {
                    lowerClaw.setPosition(CLAW_DOWN);
                }

                if (gamepad1.x) {
                    openCloseClaw.setPosition(CLAW_OPEN);
                }

                if (gamepad1.b) {
                    openCloseClaw.setPosition(CLAW_CLOSE);
                }

                if (gamepad1.dpad_up) {
                    rotateClaw.setPosition(CLAW_ONE);
                }

                if (gamepad1.dpad_right) {
                    rotateClaw.setPosition(CLAW_TWO);
                }

                if (gamepad1.dpad_down) {
                    rotateClaw.setPosition(CLAW_THREE);
                }

                if (gamepad1.dpad_left) {
                    rotateClaw.setPosition(CLAW_FOUR);
                }

                if (gamepad1.left_bumper) {
                    specimanArm.setPosition(SPECIMANCLAWCLOSE);
                }

                if (gamepad1.right_bumper) {
                    specimanClaw.setPosition(SPECIMANARMUP);
                }

                    //Driving Options

                    double x = gamepad1.left_stick_x; // Strafe left/right
                    double y = -gamepad1.left_stick_y; // Forward/backward
                    double rotation = gamepad1.right_stick_x; // Rotate

                    // Calculate motor powers

                    double frontLeftPower = y + x + rotation;
                    double frontRightPower = y - x - rotation;
                    double backLeftPower = y - x + rotation;
                    double backRightPower = y + x - rotation;
                    double slowness = (.5);

                    //Set Motor Power Limits to 1

                    double maxPower = Math.max(Math.abs(frontLeftPower), Math.max(Math.abs(frontRightPower),
                            Math.max(Math.abs(backLeftPower), Math.abs(backRightPower))));

                    if (maxPower > 1.0) {
                        frontLeftPower /= maxPower;
                        frontRightPower /= maxPower;
                        backLeftPower /= maxPower;
                        backRightPower /= maxPower;
                    }

                    if (fastMode) {
                        slowness = 1.0;
                    }

                    // Set motor powers

                    frontLeft.setPower(frontLeftPower * slowness);
                    frontRight.setPower(frontRightPower * slowness);
                    backLeft.setPower(backLeftPower * slowness);
                    backRight.setPower(backRightPower * slowness);


            }
        }
    }
