 package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.AnalogInput;
 import com.qualcomm.robotcore.hardware.ColorSensor;
 import com.qualcomm.robotcore.hardware.DcMotor;
 import com.qualcomm.robotcore.hardware.DigitalChannel;
 import com.qualcomm.robotcore.hardware.DistanceSensor;
 import com.qualcomm.robotcore.hardware.HardwareMap;
 import com.qualcomm.robotcore.hardware.Servo;
 import com.qualcomm.robotcore.util.Range;
 import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
@TeleOp(name ="felix3")

 public class felix3 extends OpMode {
    private DigitalChannel touchSensor;
    private DcMotor motor;
    private double ticksPerRotation;
    private Servo servo;
    private AnalogInput pot;
    private ColorSensor colorSensor;
    private DistanceSensor distanceSensor;

    @Override
    public void loop() {
        telemetry.addData("Amount red", getAmountRed());
        telemetry.addData("Amount blue", getAmountBlue());
        //telemetry.addData("Distance (IN)", getDistance(DistanceUnit.INCH));
    }



    public void init(){
         //touchSensor = hardwareMap.get(DigitalChannel.class, "touchSensor");
         //touchSensor.setMode(DigitalChannel.Mode.INPUT);
        // motor = hardwareMap.get(DcMotor.class, "motor");
         //motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
         //ticksPerRotation = motor.getMotorType().getTicksPerRev();
         //servo = hardwareMap.get(Servo.class, "servo");
         //pot = hardwareMap.get(AnalogInput.class, "pot");

         colorSensor = hardwareMap.get(ColorSensor.class, "colorSensor");
         //distanceSensor = hardwareMap.get(DistanceSensor.class, "colorSensor");
         }
 /*public boolean isTouchSensorPressed() {
         return !touchSensor.getState();
         }

         public void setMotorSpeed(double speed){
         motor.setPower(speed);
         }
 public double getMotorRotations(){
         return motor.getCurrentPosition() / ticksPerRotation;
         }
 public void setServoPosition(double position){
         servo.setPosition(position);
         }
 public double getPotAngle(){
         return Range.scale(pot.getVoltage(), 0, pot.getMaxVoltage(), 0, 270);
         }

  */

 public int getAmountRed(){return colorSensor.red();
         }
         public int getAmountBlue() {return colorSensor.blue();
 }
         /*
 public double getDistance(DistanceUnit du){
         return distanceSensor.getDistance(du);
         }*/

 }

