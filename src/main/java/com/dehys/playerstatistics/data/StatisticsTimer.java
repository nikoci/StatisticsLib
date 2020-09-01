package com.dehys.playerstatistics.data;

import com.dehys.playerstatistics.main.Main;

import java.util.TimerTask;

public class StatisticsTimer extends TimerTask {


    @Override
    public void run() {
        Main.getInstance().getStatisticsManager().saveStatistics(Main.getInstance().getConfigManager().clearCacheOnSave);
    }
}
