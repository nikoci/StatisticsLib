package org.dreamndelight.playerstatistics.lib.main;

import org.dreamndelight.playerstatistics.lib.data.ConfigManager;
import org.dreamndelight.playerstatistics.lib.data.SQL;
import org.dreamndelight.playerstatistics.lib.data.StatisticsManager;
import org.dreamndelight.playerstatistics.lib.data.UserData;

public class PlayerStatisticsLib {

    private UserData userData;
    private ConfigManager configManager;
    private StatisticsManager statisticsManager;

    public PlayerStatisticsLib(PlayerStatistics statistics) {
        configManager = new ConfigManager(statistics);
        if (!SQL.connect(statistics)) return;
        SQL.setupTables();
        userData = UserData.retrieveData();
        statisticsManager = new StatisticsManager();
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
