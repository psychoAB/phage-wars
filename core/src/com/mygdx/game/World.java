package com.mygdx.game;

import java.util.Timer;
import java.util.TimerTask;
import java.util.LinkedList;

public class World {

    public static final int TIME_INTERVAL = 1000;

    private Timer timer;

    private LinkedList<TimerListener> timerListener;
    
    public World() {
        timer = new Timer();
        timerListener = new LinkedList<TimerListener>();

        timer.schedule(new TimerTask() {
            @Override public void run() {
                notifyTimerListeners();
            }
        }, 0, TIME_INTERVAL);
    }

    public interface TimerListener {
        void notifyTimerListener();
    }

    public void registerTimerListener(TimerListener listener) {
        timerListener.add(listener);
    }

    public void notifyTimerListeners() {
        for(TimerListener listener : timerListener) {
            listener.notifyTimerListener();
        }
    }
}
