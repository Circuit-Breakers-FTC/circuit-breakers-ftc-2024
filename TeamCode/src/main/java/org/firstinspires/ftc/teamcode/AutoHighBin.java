package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
@Autonomous(name = "AutoHighBin")
public class AutoHighBin extends LinearOpMode {
    private DcMotor frontRight;
    private DcMotor frontLeft;
    private DcMotor backLeft;
    private DcMotor backRight;
    private DcMotor lift;
    private DcMotor armTurn;
    private CRServo intake;
    private int SLEEP_TIME = 1000;
    private ElapsedTime runtime = new ElapsedTime();

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

        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        waitForStart();
        runtime.reset();
        if (opModeIsActive()) {
            strafe();
            moveAbit();
            extendArm();
            sleep(SLEEP_TIME);
            raiseLinearSlider();
            sleep(SLEEP_TIME);
            scoringPosition();
            sleep(SLEEP_TIME);
            scoreSample();
            sleep(2000);
            return1();
            sleep(SLEEP_TIME);
            return2();
            sleep(SLEEP_TIME);
            return3();
            sleep(SLEEP_TIME);
            return4();
            sleep(SLEEP_TIME);
//            move(-0.25,2000);


        }
    }

    private void extendArm() {
        armTurn.setTargetPosition(-1490);
        armTurn.setPower(0.5);
    }

    private void raiseLinearSlider() {
        lift.setTargetPosition(-1625);
        lift.setPower(0.5);
    }
    private void scoringPosition(){
        armTurn.setTargetPosition(-1490);
        armTurn.setPower(0.5);
    }

    private void scoreSample(){
        intake.setPower(1);
    }
    private void return1(){
        intake.setPower(0);
    }
    private void return2(){
        armTurn.setTargetPosition(-1490);
        armTurn.setPower(0.5);
    }
    private void return3(){
        lift.setTargetPosition(0);
        armTurn.setPower(0.5);
    }
    private void return4(){
        armTurn.setTargetPosition(0);
        armTurn.setPower(0.25);
    }


    private void strafe (){
        frontLeft.setPower(0.5);
        backLeft.setPower(-0.5);
        frontRight.setPower(-0.5);
        backRight.setPower(0.5);
        sleep(1000);
        frontLeft.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
        frontRight.setPower(0);

    }

    private void moveAbit (){
        frontLeft.setPower(0.5);
        backLeft.setPower(0.5);
        frontRight.setPower(0.5);
        backRight.setPower(0.5);
        sleep(220);
        frontLeft.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
        frontRight.setPower(0);

    }
}