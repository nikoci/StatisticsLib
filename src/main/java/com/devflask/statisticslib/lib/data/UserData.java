package com.devflask.statisticslib.lib.data;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import com.devflask.statisticslib.plugin.Plugin;
import com.devflask.statisticslib.lib.main.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@SuppressWarnings({"unused", "RedundantSuppression"})
public class UserData {

    private final HashMap<UUID, String> players;
    private final HashMap<UUID, String> playerIDs;
    private Plugin plugin;

    public UserData(Plugin plugin) {
        this.players = new HashMap<>();
        this.playerIDs = new HashMap<>();
        this.plugin = plugin;
    }

    public UserData(HashMap<UUID, String> players, HashMap<UUID, String> playerIDs) {
        this.players = players;
        this.playerIDs = playerIDs;
    }

    public static UserData retrieveData() {
        try {
            PreparedStatement preparedStatement = SQL.prepareStatement("SELECT * FROM userdata");
            ResultSet resultSet = preparedStatement.executeQuery();
            HashMap<UUID, String> players = new HashMap<>();
            HashMap<UUID, String> playerIDs = new HashMap<>();
            while (resultSet.next()) {
                try {
                    UUID uuid = UUID.fromString(resultSet.getString("uuid"));
                    String playerName = resultSet.getString("username");
                    String playerID = resultSet.getString("userid");
                    players.put(uuid, playerName);
                    playerIDs.put(uuid, playerID);
                } catch (NullPointerException exception) {
                    exception.printStackTrace();
                }
            }
            return new UserData(players, playerIDs);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public void registerPlayer(Player player) {
        registerPlayer(player.getUniqueId(), player.getName());
    }

    public void registerPlayer(OfflinePlayer offlinePlayer) {
        registerPlayer(offlinePlayer.getUniqueId(), offlinePlayer.getName());
    }

    public Optional<UUID> getUUID(String playerName) {
        for (Map.Entry<UUID, String> entry : players.entrySet()) {
            if (entry.getValue().equalsIgnoreCase(playerName)) return Optional.of(entry.getKey());
        }
        return Optional.empty();
    }

    public Optional<String> getName(UUID uuid) {
        return Optional.ofNullable(players.getOrDefault(uuid, null));
    }

    public Optional<String> getPlayerID(UUID uuid) {
        return Optional.ofNullable(playerIDs.getOrDefault(uuid, null));
    }

    public void registerPlayer(UUID uuid, String playerName) {
        players.put(uuid, playerName);
        if (!playerIDs.containsKey(uuid)) {
            playerIDs.put(uuid, Util.generatePlayerID(plugin));
        }
    }

    public boolean isPlayerIDAvailable(String playerID) {
        return !playerIDs.containsValue(playerID);
    }

    public void saveData() {
        try {
            PreparedStatement preparedStatement = SQL.prepareStatement("INSERT INTO userdata (uuid, username, userid) VALUES (?,?,?) ON DUPLICATE KEY UPDATE username = ?");
            for (Map.Entry<UUID, String> entry : players.entrySet()) {
                preparedStatement.setString(1, entry.getKey().toString());
                preparedStatement.setString(2, entry.getValue());
                preparedStatement.setString(3, getPlayerID(entry.getKey()).orElseGet(() -> Util.generatePlayerID(plugin)));
                preparedStatement.setString(4, entry.getValue());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
