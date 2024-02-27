package com.halt.myapplication;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class ShellCommandActivity extends Activity {

    private Button button1, button2, button3, button4;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shell_command);

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    executeCommand("echo 1 > /sys/devices/platform/soc/soc:qcom,dsi-display-primary/hbm");
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    executeCommand("echo 0 > /sys/devices/platform/soc/soc:qcom,dsi-display-primary/hbm");
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    executeCommand("echo 1 > /sys/devices/platform/soc/soc:qcom,dsi-display-primary/msm_fb_ea_enable");
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    executeCommand("echo 0 > /sys/devices/platform/soc/soc:qcom,dsi-display-primary/msm_fb_ea_enable");
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    private void executeCommand(String command) throws Exception {
        try{
            Process su = Runtime.getRuntime().exec("su");
            DataOutputStream outputStream = new DataOutputStream(su.getOutputStream());

            outputStream.writeBytes(command + "\n");
            outputStream.flush();

            outputStream.writeBytes("exit\n");
            outputStream.flush();
            su.waitFor();
        }catch(IOException e){
            throw new Exception(e);
        }catch(InterruptedException e){
            throw new Exception(e);
        }
    }
}

