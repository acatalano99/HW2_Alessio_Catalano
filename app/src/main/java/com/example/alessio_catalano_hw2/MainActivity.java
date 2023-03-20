package com.example.alessio_catalano_hw2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements Handler.Callback{

    private Handler h = new Handler(this);

    public static final int TASK_10s_RUNNING = 10000;
    public static final int TASK_10s_END = 10001;
    public static final int TASK_5s_RUNNING = 5000;
    public static final int TASK_5s_END = 5001;
    public static final int TASK_2s_RUNNING = 2000;
    public static final int TASK_2s_END = 2001;
    public static final int TASK_1s_RUNNING = 1000;
    public static final int TASK_1s_END = 1001;
    public static final int TASK_500ms_RUNNING = 500;
    public static final int TASK_500ms_END = 501;

    //HMI var
    private Button btn_10s, btn_5s, btn_2s, btn_1s, btn_500ms, btn_StartAllThreads;
    private TextView tv_10s, tv_5s, tv_2s, tv_1s, tv_500ms;
    private boolean th10s_running, th5s_running, th2s_running, th1s_running, th500ms_running;

    //parametri per la simulazione di attività
    private int what=1;
    private String  task="";
    private int tempo=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //HMI var from UI
        getFromUI();

        //chiave valore ripreso da istanza precedente se è stata salvata qualcosa
        if (savedInstanceState != null){

            //recupero valori HMI
            String initMsg = "Task never started";
            tv_10s.setText(savedInstanceState.getString("tv_10s", initMsg));
            tv_5s.setText(savedInstanceState.getString("tv_5s", initMsg));
            tv_2s.setText(savedInstanceState.getString("tv_2s", initMsg));
            tv_1s.setText(savedInstanceState.getString("tv_1s", initMsg));
            tv_500ms.setText(savedInstanceState.getString("tv_500ms", initMsg));
            //recuper stato interblocchi
            th10s_running = savedInstanceState.getBoolean("th10s_running", false);
            th5s_running = savedInstanceState.getBoolean("th5s_running", false);
            th2s_running = savedInstanceState.getBoolean("th2s_running", false);
            th1s_running = savedInstanceState.getBoolean("th1s_running", false);
            th500ms_running = savedInstanceState.getBoolean("th500ms_running", false);

        }else{
            //altrimenti inizializza
            initHMI();
            initVar();
        }


    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        //callback chiamata quando l'activity sta per essere distrutta
        super.onSaveInstanceState(outState);



        //HMI
        outState.putString("tv_10s", tv_10s.getText().toString());
        outState.putString("tv_5s", tv_5s.getText().toString());
        outState.putString("tv_2s", tv_2s.getText().toString());
        outState.putString("tv_1s", tv_1s.getText().toString());
        outState.putString("tv_500ms", tv_500ms.getText().toString());
        //interblocchi
        outState.putBoolean("th10s_running", th10s_running);
        outState.putBoolean("th5s_running", th5s_running);
        outState.putBoolean("th2s_running", th2s_running);
        outState.putBoolean("th1s_running", th1s_running);
        outState.putBoolean("th500ms_running", th500ms_running);
    }

    public void getFromUI(){
        btn_10s = (Button) findViewById(R.id.btn_10s);
        tv_10s = (TextView) findViewById(R.id.tv_10s);
        btn_5s = (Button) findViewById(R.id.btn_5s);
        tv_5s = (TextView) findViewById(R.id.tv_5s);
        btn_2s = (Button) findViewById(R.id.btn_2s);
        tv_2s = (TextView) findViewById(R.id.tv_2s);
        btn_1s = (Button) findViewById(R.id.btn_1s);
        tv_1s = (TextView) findViewById(R.id.tv_1s);
        btn_500ms = (Button) findViewById(R.id.btn_500ms);
        tv_500ms = (TextView) findViewById(R.id.tv_500ms);
        btn_StartAllThreads = (Button) findViewById(R.id.btn_StartAllThreads);
    }

    public void initHMI(){
        String initMsg = "Task never started";
        tv_10s.setText(initMsg);
        tv_5s.setText(initMsg);
        tv_2s.setText(initMsg);
        tv_1s.setText(initMsg);
        tv_500ms.setText(initMsg);
    }

    public void initVar(){
        //interblocchi per far eseguire uno solo dei rispettivi thread
        th10s_running=false;
        th5s_running=false;
        th2s_running=false;
        th1s_running=false;
        th500ms_running=false;
    }

    public void startAllThreads(View view){
        onBtn10s_Click(view);
        onBtn5s_Click(view);
        onBtn2s_Click(view);
        onBtn1s_Click(view);
        onBtn500ms_Click(view);
    }

    public void onBtn10s_Click(View view) {
        if (th10s_running) return;
        th10s_running = true;

        System.out.println("10s");
        Runnable r = new mioRunnableTask(TASK_10s_RUNNING, "Task_10s", 10000, h);
        //creazione Thread per far eseguire il runnable

        Thread t = new Thread(r);
        //avvio idel thread
        t.start();

    }

    public void onBtn5s_Click(View view) {
        if (th5s_running) return;
        th5s_running = true;
        System.out.println("5s");
        Runnable r = new mioRunnableTask(TASK_5s_RUNNING, "Task_5s", 5000, h);
        //creazione Thread per far eseguire il runnable

        Thread t = new Thread(r);
        //avvio idel thread
        t.start();
    }

    public void onBtn2s_Click(View view) {
        if (th2s_running) return;
        th2s_running = true;
        System.out.println("2s");
        Runnable r = new mioRunnableTask(TASK_2s_RUNNING, "Task_2s", 2000, h);
        //creazione Thread per far eseguire il runnable

        Thread t = new Thread(r);
        //avvio idel thread
        t.start();
    }
    public void onBtn1s_Click(View view) {
        if (th1s_running) return;
        th1s_running = true;
        System.out.println("1s");
        Runnable r = new mioRunnableTask(TASK_1s_RUNNING, "Task_1s", 1000, h);
        //creazione Thread per far eseguire il runnable

        Thread t = new Thread(r);
        //avvio idel thread
        t.start();
    }
    public void onBtn500ms_Click(View view) {
        if (th500ms_running) return;
        th500ms_running = true;
        System.out.println("0.5s");
        Runnable r = new mioRunnableTask(TASK_500ms_RUNNING, "Task_0.5s", 500, h);
        //creazione Thread per far eseguire il runnable

        Thread t = new Thread(r);
        //avvio idel thread
        t.start();
    }




    @Override
    public boolean handleMessage(@NonNull Message message) {
        Bundle data = message.getData();
        String receivedMsg = data.getString("msg1");
        //gestione messaggi
        switch(message.what){
            case TASK_10s_RUNNING:
                tv_10s.setText("rcv: "+receivedMsg);
                break;
            case TASK_10s_END:
                tv_10s.setText("rcv: "+receivedMsg);
                th10s_running = false;
                break;
            case TASK_5s_RUNNING:
                tv_5s.setText("rcv: "+receivedMsg);
                break;
            case TASK_5s_END:
                tv_5s.setText("rcv: "+receivedMsg);
                th5s_running = false;
                break;
            case TASK_2s_RUNNING:
                tv_2s.setText("rcv: "+receivedMsg);;
                break;
            case TASK_2s_END:
                tv_2s.setText("rcv: "+receivedMsg);
                th2s_running = false;
                break;
            case TASK_1s_RUNNING:
                tv_1s.setText("rcv: "+receivedMsg);
                break;
            case TASK_1s_END:
                tv_1s.setText("rcv: "+receivedMsg);
                th1s_running = false;
                break;
            case TASK_500ms_RUNNING:
                tv_500ms.setText("rcv: "+receivedMsg);
                break;
            case TASK_500ms_END:
                tv_500ms.setText("rcv: "+receivedMsg);
                th500ms_running = false;
                break;
        }
        return true;
    }


}