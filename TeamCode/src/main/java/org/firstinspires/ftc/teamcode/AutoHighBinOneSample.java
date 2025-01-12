package org.firstinspires.ftc.teamcode;
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
@Autonomous(name = "AutoHighBinOneSample")
@Config
public class AutoHighBinOneSample extends LinearOpMode {
    private DcMotor frontRight;
    private DcMotor frontLeft;
    private DcMotor backLeft;
    private DcMotor backRight;
    private DcMotor lift;
    private DcMotor armTurn;
    private CRServo intake;
    private int SLEEP_TIME = 0;
    public static int PAUSE_FOR_OUTAKE = 2000;
    public static int PAUSE_FOR_ADJUSTMENT=280;
    public static double PAUSE_FOR_ADJUSTMENT_POWER=0.5;
    public static int PAUSE_FOR_ARM_TO_SCORING=600;
    public static int PAUSE_FOR_ARM_RESET=900;
    public static int PAUSE_FOR_SLIDER_UP=1000;
    public static int ARM_POSTION_SCORING = -1490;
    public static int ARM_POSTION_RESET = 0;
    public static int LIFTER_POSTION_RESET = 0;
    public static int LIFTER_POSTION_UP = -1550;
    public static int DRIVE_AWAY_FROM_BASKET = 1800;
    public static double DRIVE_AWAY_FROM_BASKET_POWER = -0.3;
    public static int USE_CAMERA = 1200;
    public static int ARM_POSTION_ALMOST_GROUND = -2725;
    public static int ARM_POSTION_GROUND = -2760;
    public static int STRAFE_TO_SAMPLE = 600;
    public static double INTAKE_SAMPLE = -1;
    public static double DRIVE_TO_SAMPLE_POWER = 0.3;
    public static int DRIVE_TO_SAMPLE = 500;





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
        strafeOffWall();
        moveAbit();
        raiseLinearSlider();
        scoringPosition();
        scoreSample();
        stopIntake();
        armDown();
        sliderDown();
        armToStartingPosition();
//        moveAwayFromBasket();
//        useCameraJK();
//        strafeToSample();
//        armToAlmostGround();
//        intakeIn();
//        driveToSample();
//        stopMoving();
//        armToGround();
//        scoringPosition();
//        driveAwayFromSample();
//        strafeAwayFromSample();
//        turnToBasket();
//        raiseLinearSlider();
//        moveToBasket();
//        scoreSample();
//        stopIntake();
//        moveABitBack();
//        armDown();
//        sliderDown();
//        armToStartingPosition();



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


    private void strafeOffWall (){
        frontLeft.setPower(0.5);
        backLeft.setPower(-0.5);
        frontRight.setPower(-0.5);
        backRight.setPower(0.5);
        sleep(1000);
        stopMoving();
    }


    private void moveAbit (){

        setPowerAndSleep(PAUSE_FOR_ADJUSTMENT_POWER, PAUSE_FOR_ADJUSTMENT);
        stopMoving();
    }
    private void stopMoving (){
        frontLeft.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
        frontRight.setPower(0);
    }

    private void moveAwayFromBasket() {
        setPowerAndSleep(DRIVE_AWAY_FROM_BASKET_POWER, DRIVE_AWAY_FROM_BASKET);

    }

    private void useCameraJK () {
        frontLeft.setPower(0.2);
        backLeft.setPower(0.2);
        frontRight.setPower(-0.2);
        backRight.setPower(-0.2);
        sleep(USE_CAMERA);

    }

    private void setPowerAndSleep(double power, int sleep_dur) {
        frontLeft.setPower(power);
        backLeft.setPower(power);
        backRight.setPower(power);
        frontRight.setPower(power);
        sleep(sleep_dur);
    }

    private  void armToGround(){
        armTurn.setTargetPosition(ARM_POSTION_GROUND);
        armTurn.setPower(1);
        sleep(1000);
    }

    private  void armToAlmostGround(){
        armTurn.setTargetPosition(ARM_POSTION_ALMOST_GROUND);
        armTurn.setPower(0.5);
        sleep(3000);
    }

    private void strafeToSample(){
        frontLeft.setPower(0.3);
        backLeft.setPower(-0.3);
        frontRight.setPower(-0.3);
        backRight.setPower(0.3);
        sleep(STRAFE_TO_SAMPLE);
        stopMoving();
    }

    private void intakeIn() {
        intake.setPower(INTAKE_SAMPLE);
        sleep(500);
    }

    private void driveToSample() {
        setPowerAndSleep(DRIVE_TO_SAMPLE_POWER, DRIVE_TO_SAMPLE);
    }

    private void driveAwayFromSample() {
        setPowerAndSleep(-DRIVE_TO_SAMPLE_POWER, DRIVE_TO_SAMPLE);

    }

    private void strafeAwayFromSample(){
        frontLeft.setPower(-0.3);
        backLeft.setPower(0.3);
        frontRight.setPower(0.3);
        backRight.setPower(-0.3);
        sleep(STRAFE_TO_SAMPLE);
        stopMoving();
    }

    private void turnToBasket () {
        frontLeft.setPower(-0.2);
        backLeft.setPower(-0.2);
        frontRight.setPower(0.2);
        backRight.setPower(0.2);
        sleep(USE_CAMERA-900);

    }

    private void moveToBasket() {
        setPowerAndSleep(-DRIVE_AWAY_FROM_BASKET_POWER, DRIVE_AWAY_FROM_BASKET);

    }

    private void moveABitBack() {
        setPowerAndSleep(0.3, 800);

    }

    private void turnToBar () {
        frontLeft.setPower(1);
        backLeft.setPower(1);
        frontRight.setPower(-1);
        backRight.setPower(-1);
        sleep(50);

    }

    private void strafeToBar() {
        frontLeft.setPower(1);
        backLeft.setPower(-1);
        frontRight.setPower(-1);
        backRight.setPower(1);
        sleep(50);
        stopMoving();

    }


}
