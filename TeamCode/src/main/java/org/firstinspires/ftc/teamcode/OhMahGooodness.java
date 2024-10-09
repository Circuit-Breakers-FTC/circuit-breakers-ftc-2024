package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp()
public class OhMahGooodness extends OpMode {
    boolean initDone;

    @Override
    public void init(){
        telemetry.addData("Init done", initDone);
        initDone = true;
    }

    @Override
    public void loop() {
        telemetry.addData("Init done", initDone);
    }
}