<div align="center" id="top"> 
  <img src="./res/banner.png" alt="Playerstatistics" />
</div>

<p align="center">
  <a href="https://discord.gg/p8Brtwj"><img alt="Discord" src="https://img.shields.io/discord/435431724831211522?color=%237289DA&label=%20%E2%80%8E%20%E2%80%8E%20%E2%80%8EDiscord%20%E2%80%8E&logo=Discord&logoColor=%237289DA&style=flat-square"></a>
  <a href="https://spigotmc.org/"><img alt="Spigot" src="https://img.shields.io/github/v/release/dream-n-delight/playerstatistics?color=%23EF8D1D&label=Spigot&style=flat-square"></a>
  <a href="https://github.com/Dream-n-Delight/PlayerStatistics/releases"><img alt="Releases" src="https://img.shields.io/github/v/release/dream-n-delight/playerstatistics?color=%2354f95f&label=Latest%20Release&logo=GitHub&logoColor=%2354f95f&style=flat-square"></a>
  <a href="https://en.wikipedia.org/wiki/MIT_License"><img alt="Discord" src="https://img.shields.io/github/license/dream-n-delight/playerstatistics?color=%23f9a154&label=License&style=flat-square"></a>
</p>
<p align="center">
  <a href="https://github.com/Dream-n-Delight/PlayerStatistics/blob/pilot/README.md#dependencies">Dependencies</a> &#xa0; | &#xa0;
  <a href="https://github.com/Dream-n-Delight/PlayerStatistics/blob/pilot/README.md#implementation">Implementation</a> &#xa0; | &#xa0;
  <a href="https://github.com/Dream-n-Delight/PlayerStatistics/tree/pilot#getting-started">Getting Started</a> &#xa0;
</p>

*PlayerStatistics API makes storing and retrieving player statistics for player on a spigot/bukkit server alot easier. At its core, it stores every statistic for every player ever joined on a server onto your desired sql database. It handles all tables, sql functions and queries. You wanted to see how many diamond axes you've broken? We got it. You can either choose to [download](https://github.com/Dream-n-Delight/PlayerStatistics/releases) it as a spigot/bukkit plugin or use it as a library by following the steps down below.*

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
2. Open your preferred IDE and add a new external jar file to the project structure

<br>
<br>

### Getting Started ###

*Jump right in to using the your freshly added PlayerStatisticsAPI*
```java
/*Example : using the StatisticsController object*/

import org.dreamndelight.playerstatistics.controllers.StatisticsController;

StatisticsManager manager = PlayerStatistics.get().getStatisticsManager();
PlayerStatistic statistic = manager.getStatistic("61d1c15c-98db-4662-b696-1668daeef4f0");

```

<br>
<br>

### License ###

This project is under license from MIT. For more details, see the [LICENSE](LICENSE.md) file.


Made with :heart: by <a href="https://github.com/overdodo" target="_blank">overdodo</a> and <a href="https://github.com/dehys" target="_blank">dehys</a>
