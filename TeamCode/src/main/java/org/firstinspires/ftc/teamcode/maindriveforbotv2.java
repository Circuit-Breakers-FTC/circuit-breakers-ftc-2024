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




    //Set the motors

    private DcMotor armRotate;
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

    public static double CLAW_UP = 0.5;
    public static double CLAW_DOWN = 0.12;
    public static double CLAW_OPEN = 0.70;
    public static double CLAW_CLOSE = 0.3;
    public static double CLAW_ONE = 0.5;
    public static double CLAW_TWO = 0.325;
    public static double CLAW_THREE = 0.15;
    public static double CLAW_FOUR = 0.675;
    public static double SPECIMANCLAWCLOSE = 0.485;
    public static double SPECIMANCLAWOPEN = 0.2;
    public static double SPECIMANARMUP = 0.85;
    public static double SPECIMANARMDOWN = 0.2;
    public static int SPECIMANLIFTERMIDDLE = -1315;
    public static int SPECIMANLIFTERUP = -1850;
    public static int SPECIMANLIFTERDOWN = -150;
    public static int SAMPLEARMRESTING = 0;
    public static int SAMPLEARMOUT = -890;
    public static double GAMEPAD_2_SPEED = 0.25;
    //Function is Executed when OpMode is initiated


    @Override
    public void runOpMode() throws InterruptedException {

        //Set Drive Motor
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        frontLeft.setDirection(DcMotor.Direction.FORWARD);
        frontRight.setDirection(DcMotor.Direction.FORWARD);
        backLeft.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.FORWARD);

        //Set Sample Arm Motors

        openCloseClaw = hardwareMap.get(Servo.class, "openCloseClaw");
        rotateClaw = hardwareMap.get(Servo.class, "rotateClaw");
        lowerClaw = hardwareMap.get(Servo.class, "lowerClaw");
        armRotate = hardwareMap.get(DcMotor.class, "armRotate");
        armRotate.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        armRotate.setTargetPosition(0);
        armRotate.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        armRotate.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        //Set Speciman Arm Motors

        specimanLifter = hardwareMap.get(DcMotor.class,"specimanLifter");
        specimanLifter.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        specimanLifter.setTargetPosition(0);
        specimanLifter.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        specimanLifter.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        specimanClaw = hardwareMap.get(Servo.class,"specimanClaw");
        specimanArm = hardwareMap.get(Servo.class, "specimanArm");

        specimanClaw.setPosition(SPECIMANCLAWCLOSE);
        rotateClaw.setPosition(CLAW_ONE);
        lowerClaw.setPosition(CLAW_DOWN);
        openCloseClaw.setPosition(CLAW_CLOSE);
        waitForStart(); //Wait for Opmode Activation
        while (opModeIsActive()) {
            telemetry.addData("height",specimanLifter.getCurrentPosition());
            telemetry.addData("SpecimanArmAngle:", specimanArm.getPosition());
            telemetry.addData("SpecimanClawAngle:",specimanClaw.getPosition());
            telemetry.addData("SampleArmAngle:", armRotate.getCurrentPosition());
            telemetry.addData("SampleArm Target Angle:", armRotate.getTargetPosition());
            telemetry.addData("lower.position", lowerClaw.getPosition());

            //Main Driving Options
            boolean fastMode = false;
            if (gamepad1.left_trigger > 0.5) {
                fastMode = true;
            } else {
                telemetry.addLine("left_trigger for fast mode!");
            }

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

            if (gamepad2.right_bumper) {
                armRotate.setTargetPosition(SAMPLEARMRESTING);
                armRotate.setPower(0.4);
            }

            if (gamepad2.left_bumper) {
                armRotate.setTargetPosition(SAMPLEARMOUT);
                armRotate.setPower(0.4);
                lowerClaw.setPosition(CLAW_UP);
                openCloseClaw.setPosition(CLAW_OPEN);
                rotateClaw.setPosition(CLAW_ONE);
            }

            if (gamepad1.a) {
                specimanArm.setPosition(SPECIMANARMUP);
            }

            if (gamepad1.b) {
                specimanArm.setPosition(SPECIMANARMDOWN);
            }

            if (gamepad1.y) {
                specimanClaw.setPosition(SPECIMANCLAWCLOSE);
            }
            if (gamepad1.x) {
                specimanClaw.setPosition(SPECIMANCLAWOPEN);
            }

            if (gamepad1.dpad_right) {
                specimanLifter.setTargetPosition(SPECIMANLIFTERMIDDLE);
                specimanLifter.setPower(0.4);
            }

            if (gamepad1.left_bumper) {
                specimanLifter.setTargetPosition(SPECIMANLIFTERDOWN);
                specimanLifter.setPower(0.4);
                specimanArm.setPosition(SPECIMANARMDOWN);
                specimanClaw.setPosition(SPECIMANCLAWOPEN);
            }

            if (gamepad1.right_bumper) {
                specimanLifter.setTargetPosition(SPECIMANLIFTERUP);
                specimanLifter.setPower(0.4);
            }
            if (gamepad1.right_trigger > 0.5) {
                specimanLifter.setTargetPosition(SPECIMANLIFTERMIDDLE);
                specimanLifter.setPower(0.4);
                specimanArm.setPosition(SPECIMANARMUP);
            }



            //Driving Options

            double x = gamepad1.left_stick_x+GAMEPAD_2_SPEED*gamepad2.left_stick_x; // Strafe left/right
            double y = -gamepad1.left_stick_y-GAMEPAD_2_SPEED*gamepad2.left_stick_y; // Forward/backward
            double rotation = gamepad1.right_stick_x+GAMEPAD_2_SPEED*gamepad2.right_stick_x; // Rotate

            // Calculate motor powers

            double frontLeftPower = y + x + rotation;
            double frontRightPower = y - x - rotation;
            double backLeftPower = y - x + rotation;
            double backRightPower = y + x - rotation;
            double slowness = (.3979676676897995);

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
            telemetry.update();


        }
    }
}
