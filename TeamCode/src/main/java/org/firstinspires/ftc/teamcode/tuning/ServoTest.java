package org.firstinspires.ftc.teamcode.tuning;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.Servo;
//programmed by gabi
@TeleOp(name = "ServoTest")
public class ServoTest extends LinearOpMode {

    private Servo extendArm;
    private DcMotor armTurn;
    private DcMotor lift;

    static final double     COUNTS_PER_MOTOR_REV    = 537.6 ;    // eg: TETRIX Motor Encoder

    /**
     * This function is executed when this Op Mode is selected from the Driver Station.
     */
    @Override
    public void runOpMode() {

        extendArm = hardwareMap.get(Servo.class, "extendArm");
        armTurn = hardwareMap.get(DcMotor.class, "armTurn");
        armTurn.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        lift = hardwareMap.get(DcMotor.class, "lift");
        lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        // Initialization for NEW robot
        // frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        // frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);



        boolean isLaunched = false;
        boolean fastMode=false;
        //that's the fast mode ;)
        waitForStart();
        while (opModeIsActive()) {

            if (gamepad1.a){
                extendArm.setPosition(0.5);
            }
            else if (gamepad1.b) {
                extendArm.setPosition(0.75);
            }

            else if (gamepad1.x) {
                extendArm.setPosition(0.25);
            }
                else if (gamepad1.y) {
                extendArm.setPosition(0.6);
            }
                    else if (gamepad1.dpad_left) {
                extendArm.setPosition(1);
            }
         if (gamepad1.left_bumper){
             lift.setPower(0.5);
         }
         else if (gamepad1.right_bumper) {
             lift.setPower(-0.5);
         }
         else  {
             lift.setPower(0);
         }

            if (gamepad1.y) { //basket
                armTurn.setTargetPosition(-1125);
                armTurn.setPower(0.5);

            } else if (gamepad1.a) {//ground
                armTurn.setTargetPosition(-1940);
                armTurn.setPower(0.25);
                //too low, changed it -6
            } else if (gamepad1.b) {//drive
                armTurn.setTargetPosition(-1650);
                armTurn.setPower(0.5);

            }
         else {
             armTurn.setPower(0);
         }


            telemetry.addData("fastMode",fastMode);
            telemetry.addData("Unfold Arm Current Position",extendArm.getPosition());
            telemetry.addData(" arm turn Current Position", armTurn.getCurrentPosition());
            telemetry.addData("lift current position", lift.getCurrentPosition());
            telemetry.update();

        }


    }
}
