package org.firstinspires.ftc.teamcode;
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

@Config
@Autonomous(name = "AutoHighBinDifPosition")
public class AutoHighBinDifPosition extends LinearOpMode {
    private DcMotor frontRight;
    private DcMotor frontLeft;
    private DcMotor backLeft;
    private DcMotor backRight;
    private DcMotor lift;
    private DcMotor armTurn;
    private CRServo intake;
    public static double MIDPOWER = 0.5;
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
            goToBasket();
            extendArm();
            sleep(1000);
            raiseLinearSlider();
            sleep(1000);
            scoringPosition();
            sleep(1000);
            scoreSample();
            sleep(1000);
            return1();
            sleep(1000);
            return2();
            sleep(1000);
            return3();
            sleep(1000);
            return4();
            sleep(1000);


        }
    }

    private void driveMotorsOff() {
        setDriveMotors(0, 0, 0, 0);
    }

    private void setDriveMotors(double frontright, double frontleft, double backright, double backleft) {
        frontRight.setPower(frontright);
        frontLeft.setPower(frontleft);
        backRight.setPower(backright);
        backLeft.setPower(backleft);
    }

    private void goToBasket() {
        setDriveMotors(-MIDPOWER, MIDPOWER, MIDPOWER, -MIDPOWER); //Strafe
        sleep(1600);
        driveMotorsOff();

        sleep(1000);

        setDriveMotors(MIDPOWER, MIDPOWER, MIDPOWER, MIDPOWER); //Forward
        sleep(500);
        driveMotorsOff();

        sleep(1000);

        setDriveMotors(-MIDPOWER, MIDPOWER, MIDPOWER, -MIDPOWER); //Strafe
        sleep(1200);
        driveMotorsOff();

        setDriveMotors(MIDPOWER, MIDPOWER, MIDPOWER, MIDPOWER); //Forward
        sleep(790);
        driveMotorsOff();
}
    private void extendArm() {
        armTurn.setTargetPosition(-890);
        armTurn.setPower(MIDPOWER);
    }

    private void raiseLinearSlider() {
        lift.setTargetPosition(-1850);
        lift.setPower(MIDPOWER);
    }
    private void scoringPosition(){
        armTurn.setTargetPosition(-1125);
        armTurn.setPower(MIDPOWER);
    }

    private void scoreSample(){
        intake.setPower(MIDPOWER);
    }
    private void return1(){
        intake.setPower(0);
    }
    private void return2(){
        armTurn.setTargetPosition(-890);
        armTurn.setPower(MIDPOWER);
    }
    private void return3(){
        lift.setTargetPosition(0);
        armTurn.setPower(MIDPOWER);
    }
    private void return4(){
        armTurn.setTargetPosition(0);
        armTurn.setPower(0.25);
    }


    private void strafe (){
        setDriveMotors(-MIDPOWER, MIDPOWER, MIDPOWER, -MIDPOWER); //Strafe
        sleep(800);
        driveMotorsOff();

    }

    private void moveAbit (){
        setDriveMotors(MIDPOWER, MIDPOWER, MIDPOWER, MIDPOWER);
        sleep(220);
        driveMotorsOff();

    }
}