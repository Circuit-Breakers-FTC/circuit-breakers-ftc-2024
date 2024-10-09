package org.firstinspires.ftc.teamcode.tuning;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;

//programmed by gabi and theo
@TeleOp(name = "MSFS2024")
public class uwu extends LinearOpMode{

    private DcMotor frontRight;
    private DcMotor frontLeft;
    private DcMotor backLeft;
    private DcMotor backRight;
    private DcMotor lift;
    private DcMotor armTurn;
    private CRServo intake;
    private Servo extendArm;
    private Servo swivel;
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
        extendArm = hardwareMap.get(Servo.class,"extendArm");
        swivel = hardwareMap.get(Servo.class,"swivel");
        //     Initialization for NEW robot
       backRight.setDirection(DcMotorSimple.Direction.REVERSE);
       backLeft.setDirection(DcMotorSimple.Direction.REVERSE);


        boolean isLaunched = false;
        boolean fastMode = false;
        // that's the fast mode ;)
        waitForStart();
        while (opModeIsActive()) {
            boolean i = gamepad1.b;
            //   directions
            int target = 0;
            if (gamepad1.left_bumper) {
                fastMode = true;
            } else {
                fastMode = false;
                telemetry.addLine("left bumper for fast mode!");

            }

                if (gamepad2.right_bumper) {
                    lift.setPower(0.25);
                } else if (gamepad2.left_bumper && lift.getCurrentPosition() > 0) {
                    lift.setPower(-0.75);
                } else {
                    lift.setPower(0);
                }

                if (gamepad2.dpad_up) {
                    intake.setPower(1.0);
                }else if (gamepad2.dpad_down){
                    intake.setPower(-1);
                }else if (gamepad2.dpad_left){
                    intake.setPower(0);
                }


                if (gamepad2.y){ //basket
                    armTurn.setTargetPosition(-1100);
                    armTurn.setPower(0.5);
                    extendArm.setPosition(1);
                }
                else if (gamepad2.a) {//ground
                    armTurn.setTargetPosition(-1740);
                    armTurn.setPower(0.5);
                    extendArm.setPosition(0);
                }
                else if (gamepad2.b) {//drive
                    armTurn.setTargetPosition(-1625);
                    armTurn.setPower(0.5);
                    extendArm.setPosition(-1);
                }




            double x = -gamepad1.right_stick_x; // Remember, this is reversed!
            double y = gamepad1.right_stick_y * 1.1; // Counteract imperfect strafing
            double rx = gamepad1.left_stick_x;

            //  Denominator is the largest motor power (absolute value) or 1
            //This ensures all the powers maintain the same ratio, but only when
            //at least one is out of the range [-1, 1]
            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
            double frontLeftPower = (y + x + rx) / denominator;
            double backLeftPower = (y - x + rx) / denominator;
            double frontRightPower = (y - x - rx) / denominator;
            double backRightPower = (y + x - rx) / denominator;
            double slowness = (.3);


            if (fastMode) {
                slowness = .7;
            }
            frontLeft.setPower(frontLeftPower * slowness);
            backLeft.setPower(backLeftPower * slowness);
            frontRight.setPower(frontRightPower * slowness);
            backRight.setPower(backRightPower * slowness);
            telemetry.addData("y ", y);
            telemetry.addData("x ", x);
            telemetry.addData("rx ", rx);
            telemetry.addData("fastMode", fastMode);
            telemetry.addData("armLift Current Position", armTurn.getCurrentPosition());
            telemetry.addData("lift Current Position",lift.getCurrentPosition());
            telemetry.addData("Target Position",lift.getTargetPosition());
            telemetry.addData("Servo Current Position",extendArm.getPosition());
            telemetry.update();

        }


    }
}




