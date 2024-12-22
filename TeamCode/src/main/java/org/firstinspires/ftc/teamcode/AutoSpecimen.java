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
    public static int PAUSE_FOR_ARM_TO_SCORING=600;
    public static int PAUSE_FOR_ARM_RESET=900;
    public static int ARM_POSTION_RESET = 0;
    public static int GET_OFF_WALL = 400;
    public static int DRIVE_TO_CHAMBER = 300;
    public static int ARM_POSISION_CHAMBER = -1700;
    public static int PULL_OFF_WALL = 100;
    public static int TURN_NINTY_DEGREES = 750;
    public static int ARM_POSTION_ABOVE_CHAMBER = -1600;
    public static double INTAKE_SPECIMEN = -0.15;



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
        moveToPark();


    }


    private void moveOffWall () {
        frontLeft.setPower(0.5);
        backLeft.setPower(0.5);
        frontRight.setPower(0.5);
        backRight.setPower(0.5);
        sleep(GET_OFF_WALL);
        stopMoving();
    }

    private void turnNintyDegrees () {
        frontLeft.setPower(0.5);
        backLeft.setPower(0.5);
        frontRight.setPower(-0.5);
        backRight.setPower(-0.5);
        sleep(TURN_NINTY_DEGREES);
        stopMoving();
    }

    private void moveAwayFromChamber () {
        frontLeft.setPower(-0.5);
        backLeft.setPower(-0.5);
        frontRight.setPower(-0.5);
        backRight.setPower(-0.5);
        sleep(GET_OFF_WALL);
        stopMoving();
    }

    private void moveToChamber () {
        frontLeft.setPower(0.2);
        backLeft.setPower(0.2);
        frontRight.setPower(0.2);
        backRight.setPower(0.2);
        sleep(DRIVE_TO_CHAMBER);
        stopMoving();
    }

    private void armToChamber(){
        armTurn.setTargetPosition(ARM_POSTION_ABOVE_CHAMBER);
        armTurn.setPower(0.5);
        sleep(PULL_OFF_WALL);
    }

    private void armOnChamber(){
        armTurn.setTargetPosition(ARM_POSISION_CHAMBER);
        armTurn.setPower(0.2);
        sleep(PAUSE_FOR_ARM_TO_SCORING);
    }
    private void strafeOffWall () {
        frontLeft.setPower(0.5);
        backLeft.setPower(-0.5);
        frontRight.setPower(-0.5);
        backRight.setPower(0.5);
        sleep(1000);
        stopMoving();
    }

    private void moveToPark (){
        frontLeft.setPower(0.5);
        backLeft.setPower(-0.5);
        frontRight.setPower(-0.5);
        backRight.setPower(0.5);
        sleep(3500);
        stopMoving();
    }
    private void intakeToHoldSpecimen(){
        intake.setPower(INTAKE_SPECIMEN);
    }
    private void armToStartingPosition(){
        armTurn.setTargetPosition(ARM_POSTION_RESET);
        armTurn.setPower(0.25);
        sleep(PAUSE_FOR_ARM_RESET);
    }
    private void stopMoving (){
        frontLeft.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
        frontRight.setPower(0);


    }




}







































































//package org.firstinspires.ftc.teamcode;
//import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.hardware.CRServo;
//import com.qualcomm.robotcore.hardware.DcMotor;
//import com.qualcomm.robotcore.hardware.DcMotorSimple;
//import com.qualcomm.robotcore.util.ElapsedTime;
//@Autonomous(name = "RightAutonomous")
//public class AutoSpecimen extends LinearOpMode {
//    private DcMotor frontRight;
//    private DcMotor frontLeft;
//    private DcMotor backLeft;
//    private DcMotor backRight;
//    private DcMotor lift;
//    private DcMotor armTurn;
//    private CRServo intake;
//
//    private ElapsedTime runtime = new ElapsedTime();
//
//    private final int ARM_MOTOR_POSITION_ABOVE_CHAMBER = -1290;
//    private final int ARM_MOTOR_POSITION_TOUCHING_CHAMBER = -1295;
//    private final double POWER_MID = 0.5;
//    private final int sleepTime=1000;
//
//    @Override
//    public void runOpMode() {
//        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
//        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
//        backRight = hardwareMap.get(DcMotor.class, "backRight");
//        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
//        lift = hardwareMap.get(DcMotor.class, "lift");
//        lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        lift.setTargetPosition(0);
//        lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        armTurn = hardwareMap.get(DcMotor.class, "armTurn");
//        armTurn.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        armTurn.setTargetPosition(0);
//        armTurn.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        armTurn.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        intake = hardwareMap.get(CRServo.class, "intake");
//
//        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
//        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
//        waitForStart();
//        runtime.reset();
//        if (opModeIsActive()) {
//            raiseArmAboveChamber();
//            sleep(sleepTime);
//            driveForward();
//            sleep(sleepTime);
//            armDown();
//            sleep(sleepTime);
//            driveBack();
//            sleep(sleepTime);
//            reset();
//            strafe();
//
//           /* strafe();
//            moveAbit();
//            moveArm(-1290);
//            sleep(2000);
//            raiseLinearSlider();
//            sleep(2000);
//            scoringPosition();
//            sleep(2000);
//            scoreSample();
//            sleep(2000);
//            return1();
//            sleep(2000);
//            return2();
//            sleep(2000);
//            return3();
//            sleep(2000);
//            return4();
//            sleep(2000);
//
//            */
//     //          move(-0.25,2000);
//
//
//        }
//    }
//
//    private void raiseArmAboveChamber() {
//        moveArm(ARM_MOTOR_POSITION_ABOVE_CHAMBER);
//    }
//    private void driveForward() {
//        frontLeft.setPower(POWER_MID);
//        frontRight.setPower(POWER_MID);
//        backLeft.setPower(POWER_MID);
//        backRight.setPower(POWER_MID);
//    }
//    private void armDown(){
//        moveArm(ARM_MOTOR_POSITION_TOUCHING_CHAMBER);
//    }
//    private void driveBack() {
//        frontLeft.setPower(-0.5);
//        frontRight.setPower(-0.5);
//        backLeft.setPower(-0.5);
//        backRight.setPower(-0.5);
//    }
//    private void reset() {
//        armTurn.setTargetPosition(0);
//        armTurn.setPower(0.5);
//    }
//
//    private void moveArm(int extendAmount) {
//        armTurn.setTargetPosition(extendAmount);
//        armTurn.setPower(0.5);
//    }
//
//    private void raiseLinearSlider() {
//        lift.setTargetPosition(-1850);
//        lift.setPower(0.5);
//    }
//    private void scoringPosition(){
//        armTurn.setTargetPosition(-1125);
//        armTurn.setPower(0.5);
//    }
//
//    private void scoreSample(){
//        intake.setPower(0.5);
//    }
//    private void return1(){
//        intake.setPower(0);
//    }
//    private void return2(){
//        armTurn.setTargetPosition(-890);
//        armTurn.setPower(0.5);
//    }
//    private void return3(){
//        lift.setTargetPosition(0);
//        armTurn.setPower(0.5);
//    }
//    private void return4(){
//        armTurn.setTargetPosition(0);
//        armTurn.setPower(0.25);
//    }
//
//
//    private void strafe (){
//        frontLeft.setPower(0.5);
//        backLeft.setPower(-0.5);
//        frontRight.setPower(-0.5);
//        backRight.setPower(0.5);
//        sleep(800);
//        frontLeft.setPower(0);
//        backLeft.setPower(0);
//        backRight.setPower(0);
//        frontRight.setPower(0);
//
//    }
//
//    private void moveAbit (){
//        frontLeft.setPower(0.5);
//        backLeft.setPower(0.5);
//        frontRight.setPower(0.5);
//        backRight.setPower(0.5);
//        sleep(220);
//        frontLeft.setPower(0);
//        backLeft.setPower(0);
//        backRight.setPower(0);
//        frontRight.setPower(0);
//
//    }
//}