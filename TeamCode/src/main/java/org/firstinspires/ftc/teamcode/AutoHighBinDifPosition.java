//Import

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

    //Define Motors

    private DcMotor frontRight;
    private DcMotor frontLeft;
    private DcMotor backLeft;
    private DcMotor backRight;
    private DcMotor lift;
    private DcMotor armTurn;
    private CRServo intake;
    public static double LOWPOWER = 0.25;
    public static double MIDPOWER = 0.5;
    public static double HIGHPOWER = 1;
    private ElapsedTime runtime = new ElapsedTime();
    public static int rightStrafe1 = 1600;
    public static int forwardSubDodge = 500;
    public static int rightStrafe2 = 900;
    public static int forwardToBasket = 790;


    @Override
    public void runOpMode() {

        //Set Motors

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

        //Main Auto Code

        if (opModeIsActive()) {
            rightStrafe1();

            sleep(1000); //Wait between actions

            forwardSubDodge();

            sleep(1000); //Wait between actions

            rightStrafe2();

            sleep(1000); //Wait between actions

            forwardToBasket();

            sleep(1000); //Wait between actions

            //extendArm();
            intakeArmUp();

            sleep(1000); //Wait between actions

            //raiseLinearSlider();
            raiseLinearSlide();

            sleep(1000); //Wait between actions

            //scoringPosition();
            intakeArmSLantBasket();

            sleep(1000); //Wait between actions

            //scoreSample();
            intakeOut();

            sleep(1000); //Wait between actions

            //return1();
            intakeOff();

            sleep(1000); //Wait between actions

            //return2();
            intakeArmLowerPosition();

            sleep(1000); //Wait between actions

            //return3();
            linearSlideDown();

            sleep(1000); //Wait between actions

            //return4();
            intakeArmStartPosition();

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

    private void rightStrafe1() {
        setDriveMotors(-MIDPOWER, MIDPOWER, MIDPOWER, -MIDPOWER); //Strafe
        sleep(rightStrafe1);
        driveMotorsOff();
    }

    private void forwardSubDodge() {
        setDriveMotors(MIDPOWER, MIDPOWER, MIDPOWER, MIDPOWER); //Forward
        sleep(forwardSubDodge);
        driveMotorsOff();
    }

    private void rightStrafe2() {
        setDriveMotors(-MIDPOWER, MIDPOWER, MIDPOWER, -MIDPOWER); //Strafe
        sleep(rightStrafe2);
        driveMotorsOff();
    }

    private void forwardToBasket() {
        setDriveMotors(MIDPOWER, MIDPOWER, MIDPOWER, MIDPOWER); //Forward
        sleep(forwardToBasket);
        driveMotorsOff();
    }

    private void intakeArmUp() {
        armTurn.setTargetPosition(-890);
        armTurn.setPower(MIDPOWER);
    }

    private void raiseLinearSlide() {
        lift.setTargetPosition(-1850);
        lift.setPower(MIDPOWER);
    }
    private void intakeArmSLantBasket(){
        armTurn.setTargetPosition(-1125);
        armTurn.setPower(MIDPOWER);
    }

    private void intakeOut(){
        intake.setPower(MIDPOWER);
    }

    private void intakeOff(){
        intake.setPower(0);
    }

    private void intakeArmLowerPosition(){
        armTurn.setTargetPosition(-890);
        armTurn.setPower(MIDPOWER);
    }

    private void linearSlideDown(){
        lift.setTargetPosition(0);
        armTurn.setPower(MIDPOWER);
    }

    private void intakeArmStartPosition(){
        armTurn.setTargetPosition(0);
        armTurn.setPower(0.25);
    }
}