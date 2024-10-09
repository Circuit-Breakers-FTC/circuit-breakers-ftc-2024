package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

//programmed by gabi and theo
@TeleOp(name = "felix4")
public class Ethanissped extends LinearOpMode {

    private DcMotor frontRight;
    private DcMotor frontLeft;
    private DcMotor backLeft;
    private DcMotor backRight;


    static final double COUNTS_PER_MOTOR_REV = 537.6;    // eg: TETRIX Motor Encoder

    /**
     * This function is executed when this Op Mode is selected from the Driver Station.
     */
    @Override
    public void runOpMode() {
        frontRight = hardwareMap.get(DcMotor.class, "front_right");
        frontLeft = hardwareMap.get(DcMotor.class, "front_left");
        backRight = hardwareMap.get(DcMotor.class, "back_right");
        backLeft = hardwareMap.get(DcMotor.class, "back_left");

        //     Initialization for NEW robot
        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
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


            // REMEMBER, "i" = intake!!!
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


        }


    }
}




