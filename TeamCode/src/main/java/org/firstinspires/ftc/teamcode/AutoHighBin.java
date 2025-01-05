package org.firstinspires.ftc.teamcode;
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
@Autonomous(name = "AutoHighBin")
@Config
public class AutoHighBin extends LinearOpMode {
    private DcMotor frontRight;
    private DcMotor frontLeft;
    private DcMotor backLeft;
    private DcMotor backRight;
    private DcMotor lift;
    private DcMotor armTurn;
    private CRServo intake;
    private int SLEEP_TIME = 0;
    public static int PAUSE_FOR_OUTAKE = 2000;
    public static int PAUSE_FOR_ADJUSTMENT=250;
    public static int PAUSE_FOR_ARM_TO_SCORING=600;
    public static int PAUSE_FOR_ARM_RESET=900;
    public static int PAUSE_FOR_SLIDER_UP=1000;
    public static int ARM_POSTION_SCORING = -1490;
    public static int ARM_POSTION_RESET = 0;
    public static int LIFTER_POSTION_RESET = 0;
    public static int LIFTER_POSTION_UP = -1500;
    public static int GET_OFF_WALL = 400;
    public static int DRIVE_TO_CHAMBER = 300;
    public static int ARM_POSISION_CHAMBER = -1700;
    public static int PULL_OFF_WALL = 100;
    public static int TURN_NINTY_DEGREES = 750;
    public static int ARM_POSTION_ABOVE_CHAMBER = -1600;
    public static double INTAKE_SPECIMEN = -0.2;



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
            sampleToHighBin();
        }
    }


    ///// AUTONOMOUS SAMPLE //////

    private void sampleToHighBin() {
        strafe();
        moveAbit();
        raiseLinearSlider();
        scoringPosition();
        scoreSample();
        stopIntake();
        armDown();
        sliderDown();
        armToStartingPosition();
    }
    private void raiseLinearSlider() {
        lift.setTargetPosition(LIFTER_POSTION_UP);
        lift.setPower(0.5);
        sleep(PAUSE_FOR_SLIDER_UP);
    }
    private void scoringPosition(){
        armTurn.setTargetPosition(ARM_POSTION_SCORING);
        armTurn.setPower(0.5);
        sleep(PAUSE_FOR_ARM_TO_SCORING);
    }

    private void scoreSample(){
        intake.setPower(1);
        sleep(PAUSE_FOR_OUTAKE);
    }
    private void stopIntake(){
        intake.setPower(0);
    }
    private void armDown(){
        armTurn.setTargetPosition(ARM_POSTION_SCORING);
        armTurn.setPower(0.5);
    }
    private void sliderDown(){
        lift.setTargetPosition(LIFTER_POSTION_RESET);
        armTurn.setPower(0.5);
    }
    private void armToStartingPosition(){
        armTurn.setTargetPosition(ARM_POSTION_RESET);
        armTurn.setPower(0.25);
        sleep(PAUSE_FOR_ARM_RESET);
    }


    private void strafe (){
        frontLeft.setPower(0.5);
        backLeft.setPower(-0.5);
        frontRight.setPower(-0.5);
        backRight.setPower(0.5);
        sleep(1000);
        stopMoving();
    }


    private void moveAbit (){
        frontLeft.setPower(0.5);
        backLeft.setPower(0.5);
        frontRight.setPower(0.5);
        backRight.setPower(0.5);
        sleep(PAUSE_FOR_ADJUSTMENT);
        stopMoving();

    }
    private void stopMoving (){
        frontLeft.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
        frontRight.setPower(0);


    }




}