package org.firstinspires.ftc.teamcode.tuning;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.Gamepad;

//programmed by gabi and theo
@TeleOp(name = "MainDrive")
public class MainDrive extends LinearOpMode {

    private DcMotor frontRight;
    private DcMotor frontLeft;
    private DcMotor backLeft;
    private DcMotor backRight;
    private DcMotor lift;
    private DcMotor armTurn;
    private CRServo intake;
    private Servo extendArm;
    private double ticksPerRotation;


    static final double COUNTS_PER_MOTOR_REV = 537.6;    // eg: TETRIX Motor Encoder

    /**
     * This function is executed when this Op Mode is selected from the Driver Station.
     */
    @Override
    public void runOpMode() {
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        lift = hardwareMap.get(DcMotor.class, "lift");
        lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lift.setTargetPosition(0);
        lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        armTurn = hardwareMap.get(DcMotor.class, "armTurn");
        armTurn.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        armTurn.setTargetPosition(0);
        armTurn.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        armTurn.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        intake = hardwareMap.get(CRServo.class, "intake");
        extendArm = hardwareMap.get(Servo.class, "extendArm");
        //     Initialization for NEW robot
        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        extendArm.setPosition(0.4);

        boolean isLaunched = false;
        boolean fastMode = false;
        // that's the fast mode ;)
        waitForStart();
        while (opModeIsActive()) {
            boolean i = gamepad1.b;
            //   directions
            int target = 0;
            if (gamepad1.right_bumper) {
                fastMode = true;
            } else {
                fastMode = false;
                telemetry.addLine("left bumper for fast mode!");

            }
                //Lifter
            if (gamepad2.a) {//ground
                lift.setTargetPosition(0);
                lift.setPower(1);
            } else if (gamepad2.b) {//high basket
                lift.setTargetPosition(-1625);
                lift.setPower(1);
            } else if (gamepad2.y){//low basket
                lift.setTargetPosition(-1000);
                lift.setPower(1);
            }
                //Intake
            if (gamepad2.dpad_down) {
                intake.setPower(0.25);
                //push out
            } else if (gamepad2.dpad_up) {
                intake.setPower(-0.5);
                //suck in
            } else if (gamepad2.dpad_left) {
                intake.setPower(0);
                //stop
            }

                //Arm code
            if (gamepad1.y) { //basket
                armTurn.setTargetPosition(-1490);
                armTurn.setPower(0.5);

            } else if (gamepad1.b) {//ground
                armTurn.setTargetPosition(-2722);
                armTurn.setPower(0.25);
                //too low, changed it -6
            } else if (gamepad1.x) {//drive
                armTurn.setTargetPosition(-2440);
                armTurn.setPower(0.5);
            } else if (gamepad1.a) {//floor
                armTurn.setTargetPosition(-2775);
                armTurn.setPower(0.25);


            }
            if (gamepad1.dpad_up){
                frontLeft.setPower(1);
            } else {
                frontLeft.setPower(0);
            }
            if (gamepad1.dpad_right){
                frontRight.setPower(1);
            } else {
                frontRight.setPower(0);
            }
            if (gamepad1.dpad_left){
                backLeft.setPower(1);
            } else {
                backLeft.setPower(0);
            }if (gamepad1.dpad_down){
                backRight.setPower(1);
            } else {
                backRight.setPower(0);
            }






            double x = gamepad1.left_stick_x; // Strafe left/right
            double y = -gamepad1.left_stick_y; // Forward/backward
            double rotation = gamepad1.right_stick_x; // Rotate

            // Calculate motor powers
            double frontLeftPower = y + x + rotation;
            double frontRightPower = y - x - rotation;
            double backLeftPower = y - x + rotation;
            double backRightPower = y + x - rotation;
            double slowness = (.3);
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
            frontLeft.setPower(frontLeftPower);
            frontRight.setPower(frontRightPower);
            backLeft.setPower(backLeftPower);
            backRight.setPower(backRightPower);



            if (fastMode) {
                slowness = 1.0;
            }
            frontLeft.setPower(frontLeftPower * slowness);
            backLeft.setPower(backLeftPower * slowness);
            frontRight.setPower(frontRightPower * slowness);
            backRight.setPower(backRightPower * slowness);
            telemetry.addData("y ", y);
            telemetry.addData("x ", x);
            telemetry.addData("fastMode", fastMode);
            telemetry.addData("armLift Current Position", armTurn.getCurrentPosition());
            telemetry.addData("lift Current Position", lift.getCurrentPosition());
            telemetry.addData("Target Position", lift.getTargetPosition());
            telemetry.addData("Unfold Arm Current Position", extendArm.getPosition());
            telemetry.update();

        }
    }
}
