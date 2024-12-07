package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
@Autonomous(name = "parkauto2")
public class parkauto extends LinearOpMode {
    private DcMotor frontRight;
    private DcMotor frontLeft;
    private DcMotor backLeft;
    private DcMotor backRight;

    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() {
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();
        runtime.reset();
        if (opModeIsActive()) {


            frontLeft.setPower(0.25);
            backLeft.setPower(0.25);
            frontRight.setPower(0.25);
            backRight.setPower(0.25);
            sleep(2500);
            frontLeft.setPower(0);
            backLeft.setPower(0);
            backRight.setPower(0);
            frontRight.setPower(0);
        }
    }
}