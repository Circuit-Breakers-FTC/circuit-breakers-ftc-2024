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
@Autonomous(name = "AutoSpecimen")
public class AutoSpecimen extends LinearOpMode {

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
    public static int raiseArmToBarHeight = -1000;
    public static int forwardToBars = 1250;
    public static int use1 = 900;
    public static int use2 = 790;


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

            raiseArmToBarHeight();

            while (armTurn.isBusy()) {
                forwardToBars();
            }

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

    private void raiseArmToBarHeight() {
        armTurn.setTargetPosition(raiseArmToBarHeight);
        armTurn.setPower(MIDPOWER);
       // while (armTurn.isBusy()) {
          //  sleep(10);
        //}
    }

    private void forwardToBars() {
        setDriveMotors(LOWPOWER, LOWPOWER, LOWPOWER, LOWPOWER);
        sleep(forwardToBars);
        setDriveMotors(0, 0, 0, 0);
    }

    private void lowerArmToHangHeight () {
        armTurn.setTargetPosition(-1100);
        armTurn.setPower(MIDPOWER);
    }

    private void backLeaveHanging() {
        setDriveMotors(LOWPOWER, LOWPOWER, LOWPOWER, LOWPOWER);
        sleep(500);
        driveMotorsOff();
    }

}