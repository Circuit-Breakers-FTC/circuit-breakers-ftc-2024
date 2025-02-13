package org.firstinspires.ftc.teamcode;
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
@Autonomous(name = "AutoSpecimen")
@Config
public class AutoSpecimen extends LinearOpMode {
    private DcMotor frontRight;
    private DcMotor frontLeft;
    private DcMotor backLeft;
    private DcMotor backRight;
    private DcMotor lift;
    private DcMotor armTurn;
    private CRServo intake;
    private int SLEEP_TIME = 0;
    public static int PAUSE_FOR_ARM_TO_SCORING = 600;
    public static int PAUSE_FOR_ARM_RESET = 900;
    public static int ARM_POSTION_RESET = 0;
    public static int GET_OFF_WALL = 400;
    public static int DRIVE_TO_CHAMBER = 260;
    public static int ARM_POSISION_CHAMBER = -1750;
    public static int PULL_OFF_WALL = 100;
    public static int TURN_NINTY_DEGREES = 750;
    public static int ARM_POSTION_ABOVE_CHAMBER = -1600;
    public static double INTAKE_SPECIMEN = -0.1;

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
//            sampleToHighBin();
            specimenToHighBar();
        }
    }

    ///// AUTONOMOUS SPECIMEN //////

    private void specimenToHighBar() {
        // TODO: Write me!
        strafeOffWall();
        armToChamber();
        turnNintyDegrees();
        intakeToHoldSpecimen();
        sleep(500);
        moveToChamber();
        armOnChamber();
        moveAwayFromChamber();
        sleep(1000);
        armToStartingPosition();
        sleep(1000);
        moveToPark();
        backup();
        stopMoving();


    }


    private void moveOffWall() {
        frontLeft.setPower(0.5);
        backLeft.setPower(0.5);
        frontRight.setPower(0.5);
        backRight.setPower(0.5);
        sleep(GET_OFF_WALL);
        stopMoving();
    }

    private void turnNintyDegrees() {
        frontLeft.setPower(0.5);
        backLeft.setPower(0.5);
        frontRight.setPower(-0.5);
        backRight.setPower(-0.5);
        sleep(TURN_NINTY_DEGREES);
        stopMoving();
    }

    private void moveAwayFromChamber() {
        frontLeft.setPower(-0.5);
        backLeft.setPower(-0.5);
        frontRight.setPower(-0.5);
        backRight.setPower(-0.5);
        sleep(GET_OFF_WALL);
        stopMoving();
    }

    private void moveToChamber() {
        frontLeft.setPower(0.2);
        backLeft.setPower(0.2);
        frontRight.setPower(0.2);
        backRight.setPower(0.2);
        sleep(DRIVE_TO_CHAMBER);
        stopMoving();
    }

    private void armToChamber() {
        armTurn.setTargetPosition(ARM_POSTION_ABOVE_CHAMBER);
        armTurn.setPower(0.5);
        sleep(PULL_OFF_WALL);
    }

    private void armOnChamber() {
        armTurn.setTargetPosition(ARM_POSISION_CHAMBER);
        armTurn.setPower(0.2);
        sleep(PAUSE_FOR_ARM_TO_SCORING);
    }

    private void strafeOffWall() {
        frontLeft.setPower(0.5);
        backLeft.setPower(-0.5);
        frontRight.setPower(-0.5);
        backRight.setPower(0.5);
        sleep(1000);
        stopMoving();
    }

    private void moveToPark() {
        frontLeft.setPower(0.5);
        backLeft.setPower(-0.5);
        frontRight.setPower(-0.5);
        backRight.setPower(0.5);
        sleep(3500);
        stopMoving();
    }

    private void intakeToHoldSpecimen() {
        intake.setPower(INTAKE_SPECIMEN);
    }

    private void armToStartingPosition() {
        armTurn.setTargetPosition(ARM_POSTION_RESET);
        armTurn.setPower(0.25);
        sleep(PAUSE_FOR_ARM_RESET);
    }

    private void stopMoving() {
        frontLeft.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
        frontRight.setPower(0);
    }

    private void backup() {
        frontLeft.setPower(-0.1);
        backLeft.setPower(-0.1);
        backRight.setPower(-0.1);
        frontRight.setPower(-0.1);
        sleep(2000);
    }





}