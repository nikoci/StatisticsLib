package org.dreamndelight.playerstatistics.data;

import org.dreamndelight.playerstatistics.main.Main;

import java.util.TimerTask;

public class StatisticsTimer extends TimerTask {


    @Override
    public void run() {
        Main.getInstance().getStatisticsManager().saveStatistics(Main.getInstance().getConfigManager().clearCacheOnSave);
    }
}
