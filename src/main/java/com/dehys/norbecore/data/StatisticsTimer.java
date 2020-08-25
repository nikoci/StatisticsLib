package com.dehys.norbecore.data;

import com.dehys.norbecore.main.Main;

import java.util.TimerTask;

public class StatisticsTimer extends TimerTask {


    @Override
    public void run() {
        Main.getInstance().getStatisticsManager().saveStatistics(Main.getInstance().getConfigManager().clearCacheOnSave);
    }
}
