package com.devflask.statisticslib.lib.main;

import com.devflask.statisticslib.lib.data.ConfigManager;
import com.devflask.statisticslib.lib.data.StatisticsManager;
import com.devflask.statisticslib.lib.data.UserData;

public class PlayerStatisticsLib {

    private final UserData userData;
    private final ConfigManager configManager;
    private final StatisticsManager statisticsManager;

    public PlayerStatisticsLib(PlayerStatistics statistics) {
        configManager = new ConfigManager(statistics);
        userData = UserData.retrieveData();
        statisticsManager = new StatisticsManager(statistics);
    }

    public UserData getUserData() {
        return userData;
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }

    public StatisticsManager getStatisticsManager() {
        return statisticsManager;
    }
}
