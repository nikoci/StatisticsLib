package com.devflask.statisticslib.lib.main;

import com.devflask.statisticslib.lib.data.ConfigManager;
import com.devflask.statisticslib.lib.data.SQL;
import com.devflask.statisticslib.lib.data.StatisticsManager;
import com.devflask.statisticslib.lib.data.UserData;

public class PlayerStatisticsLib {

    private UserData userData;
    private final ConfigManager configManager;
    private StatisticsManager statisticsManager;

    public PlayerStatisticsLib(PlayerStatistics statistics) {
        configManager = new ConfigManager(statistics);
        if (!SQL.connect(statistics)) return;
        SQL.setupTables();
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
