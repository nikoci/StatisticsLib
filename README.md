<div align="center" id="top"> 
  <img src="https://i.imgur.com/mV0oK20.png" width="700px" alt="StatisticsLib" />
</div>

<p align="center">
  <a href="https://discord.gg/DdnayChh4g"><img alt="Discord" src="https://img.shields.io/discord/298480981441118208?color=%237289DA&label=%20%E2%80%8E%20%E2%80%8E%20%E2%80%8EDiscord%20%E2%80%8E&logo=Discord&logoColor=%237289DA&style=flat-square"></a>
  <a href="https://github.com/devflask/StatisticsLib/releases"><img alt="Releases" src="https://img.shields.io/github/v/release/devflask/StatisticsLib?color=%2354f95f&label=Latest%20Release&logo=GitHub&logoColor=%2354f95f&style=flat-square"></a>
  <a href="https://en.wikipedia.org/wiki/MIT_License"><img alt="Discord" src="https://img.shields.io/github/license/devflask/StatisticsLib?color=%23f9a154&label=License&style=flat-square"></a>
</p>
<p align="center">
  <a href="https://github.com/devflask/StatisticsLib/blob/pilot/README.md#dependencies">Dependencies</a> &#xa0; | &#xa0;
  <a href="https://github.com/devflask/StatisticsLib/blob/pilot/README.md#implementation">Implementation</a> &#xa0; | &#xa0;
  <a href="https://github.com/devflask/StatisticsLib/tree/pilot#getting-started">Getting Started</a> &#xa0;
</p>

*StatisticsLib makes storing and retrieving player statistics for players on a spigot/bukkit server alot easier. At its core, it stores every statistic for every player ever joined on a server onto your desired sql database. It handles all tables, sql functions and queries. You wanted to see how many diamond axes you've broken? We got it. You can either choose to [download](https://github.com/devflask/StatisticsLib/releases) it as a jar file or use it with maven*

**NOTE:** Maven support is coming soon. We are currently waiting for our project to be accepted to Maven Central.

<br>
<br>

### Dependencies ###
- [**Spigot API** - 1.17+](https://hub.spigotmc.org/jenkins/job/BuildTools/)<br>
  *Spigot-API is licensed under the GNU General Public License v3.0*<br>
- [**Java JDK** - 16+](https://www.oracle.com/java/technologies/javase/jdk16-archive-downloads.html)<br>
  *This software is licensed under the Oracle BSD License*
  
<br>
<br>

## Implementation ##

<h5>Maven</h5>

```xml
<repositories>
  <repository>
    <id>StatisticsLib</id>
    <url>https://maven.devfalsk.com/repository/StatisticsLib/</url>
  </repository>
</repositories>

<dependencies>
  <dependency>
    <groupId>com.devfalsk.statisticslib</groupId>
    <artifactId>statisticslib</artifactId>
    <version>${project.version}</version>
  </dependency>
</dependencies>
```

<br>


<h5>Raw Jar file</h5>

1. Download the latest jar file from [here](https://github.com/devflask/statisticslib/releases)
2. Add the jar file to your server's plugins folder
3. Open your preferred IDE and add a new external jar file to the project structure
4. Start coding!

<br>

### Getting Started ###

*Jump right in to using the your freshly added StatisticsLib!*
<br>
*Use this demo class to get started. More demo classes can also be found in [demo.me.dehys.myplugin](/src/demo/java/me/dehys/myplugin/MyPlugin.java)*

```java
public class MyPlugin extends JavaPlugin {

  private StatisticsLib lib;

  @Override
  public void onEnable() {
    super.onEnable();
    if (!setupStatisticsLib()) {
      getServer().getPluginManager().disablePlugin(this);
    }
  }

  /**
   * This method is used to fetch the active StatisticsLib instance from Bukkit's RegisteredServiceProvider
   *
   * @return true if an instance could be found, false if no instance could be found. In this case disable your statisticsPlugin.
   * You *need* the instance registered to the Provider, creating your own instance can lead to loss of data
   */
  private boolean setupStatisticsLib() {
    if (getServer().getPluginManager().getPlugin("StatisticsLib") == null) {
      return false;
    }
    RegisteredServiceProvider<StatisticsLib> serviceProvider = getServer().getServicesManager().getRegistration(StatisticsLib.class);
    if (serviceProvider == null) {
      return false;
    }
    lib = serviceProvider.getProvider();
    return true;
  }


  /**
   * Use this getter to access the StatisticsLib instance anywhere in your statisticsPlugin
   *
   * @return the active instance of StatisticsLib
   */
  public StatisticsLib getLib() {
    return lib;
  }


  /**
   * Use this getter to access the StatisticsManager instance.
   * The StatisticsManager provides all methods for handling statistics, most importantly, for retrieving them
   *
   * @return the active instance of StatisticsManager
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


Made with :heart: by <a href="https://github.com/overdodo" target="_blank">dodo</a> and <a href="https://github.com/dehys" target="_blank">dehys</a>
