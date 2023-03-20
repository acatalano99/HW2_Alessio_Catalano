package com.example.alessio_catalano_hw2;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;

public class mioRunnableTask implements Runnable{
    private int what;
    private String task;
    private int tempo;
    private Handler h;

    mioRunnableTask(int what, String task, int tempo, Handler h){
        this.what = what;
        this.task = task;
        this.tempo = tempo;
        this.h = h;
    }
    @Override
    public void run() {
        //preparazione dati
        Bundle data = new Bundle();
        data.putString("msg1", task+": running");
        //preparazione messaggio
        Message msg = Message.obtain(h, what);
        msg.setData(data);
        //invio messaggio
        h.sendMessage(msg);
        //simulazione grande tempo di elaborazione
        SystemClock.sleep(tempo);
        //preparazione messaggio
        Message msg2 = Message.obtain(h, what+1);
        Bundle data2 = new Bundle();
        data2.putString("msg1", task+": END");
        msg2.setData(data2);
        //invio messaggio
        h.sendMessage(msg2);
    }
}
