<div align="center" id="top"> 
  <img src="./res/banner.png" alt="Playerstatistics" />
</div>

<p align="center">
  <a href="https://discord.gg/TvEffRs"><img alt="Discord" src="https://img.shields.io/discord/435431724831211522?color=%237289DA&label=%20%E2%80%8E%20%E2%80%8E%20%E2%80%8EDiscord%20%E2%80%8E&logo=Discord&logoColor=%237289DA&style=flat-square"></a>
  <a href="https://github.com/Dream-n-Delight/PlayerStatistics/releases"><img alt="Releases" src="https://img.shields.io/github/v/release/dream-n-delight/PlayerStatisticsLib?color=%2354f95f&label=Latest%20Release&logo=GitHub&logoColor=%2354f95f&style=flat-square"></a>
  <a href="https://en.wikipedia.org/wiki/MIT_License"><img alt="Discord" src="https://img.shields.io/github/license/dream-n-delight/PlayerStatisticsLib?color=%23f9a154&label=License&style=flat-square"></a>
</p>
<p align="center">
  <a href="https://github.com/Dream-n-Delight/PlayerStatistics/blob/pilot/README.md#dependencies">Dependencies</a> &#xa0; | &#xa0;
  <a href="https://github.com/Dream-n-Delight/PlayerStatistics/blob/pilot/README.md#implementation">Implementation</a> &#xa0; | &#xa0;
  <a href="https://github.com/Dream-n-Delight/PlayerStatistics/tree/pilot#getting-started">Getting Started</a> &#xa0;
</p>

*PlayerStatistics LIB makes storing and retrieving player statistics for players on a spigot/bukkit server alot easier. At its core, it stores every statistic for every player ever joined on a server onto your desired sql database. It handles all tables, sql functions and queries. You wanted to see how many diamond axes you've broken? We got it. You can either choose to [download](https://github.com/Dream-n-Delight/PlayerStatistics/releases) it as a jar file or use it with maven*

**NOTE:** Maven support is coming soon. We are currently waiting for our project to be accepted to Maven Central.

<br>
<br>

### Dependencies ###
- [**Spigot API** - 1.8+](https://hub.spigotmc.org/jenkins/job/BuildTools/)<br>
  *Spigot-API is licensed under the GNU General Public License v3.0*<br>
- [**Java JDK** - 1.8+](https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html)<br>
  *This software is licensed under the Oracle BSD License*
  
<br>
<br>

### Implementation ###

<h5>Maven</h5>

```xml
<description>
    Coming Soon
</description>
```

<br>


<h5>Raw Jar file</h5>

1. Download the latest jar file from [here](https://github.com/dream-n-delight/playerstatistics/releases)
2. Add the jar file to your server's plugins folder
3. Open your preferred IDE and add a new external jar file to the project structure

<br>
<br>

### Getting Started ###

*Jump right in to using the your freshly added PlayerStatisticsLib!*
<br>
*Use this demo class to get started.*

```java
package org.dreamndelight.playerstatistics.lib.main;

import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import StatisticsManager;
import PlayerStatisticsLib;

public class MyPlugin extends JavaPlugin {

  private PlayerStatisticsLib lib;

  @Override
  public void onEnable() {
    super.onEnable();
    if (!setupPlayerStatisticsLib()) {
      getServer().getPluginManager().disablePlugin(this);
    }
  }

  /**
   * This method is used to fetch the active {@link PlayerStatisticsLib} instance from Bukkit's {@link RegisteredServiceProvider}
   *
   * @return true if an instance could be found, false if no instance could be found. In this case disable your plugin. 
   * You *need* the instance registered to the Provider, creating your own instance can lead to loss of data
   */
  private boolean setupPlayerStatisticsLib() {
    if (getServer().getPluginManager().getPlugin("PlayerStatisticsLib") == null) {
      return false;
    }
    RegisteredServiceProvider<PlayerStatisticsLib> serviceProvider = getServer().getServicesManager().getRegistration(PlayerStatisticsLib.class);
    if (serviceProvider == null) {
      return false;
    }
    lib = serviceProvider.getProvider();
    return true;
  }


  /**
   * Use this getter to access the {@link PlayerStatisticsLib} instance anywhere in your plugin
   *
   * @return the active instance of {@link PlayerStatisticsLib}
   */
  public PlayerStatisticsLib getLib() {
    return lib;
  }


  /**
   * Use this getter to access the {@link StatisticsManager} instance. 
   * The {@link StatisticsManager} provides all methods for handling statistics, most importantly, for retrieving them
   *
   * @return the active instance of {@link StatisticsManager}
   */
  public StatisticsManager getStatisticsManager() {
    return lib.getStatisticsManager();
  }
}

```

<br>
<br>

### License ###

This project is under license from MIT. For more details, see the [LICENSE](LICENSE.md) file.


Made with :heart: by <a href="https://github.com/overdodo" target="_blank">overdodo</a> and <a href="https://github.com/dehys" target="_blank">dehys</a>
