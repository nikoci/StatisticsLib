package com.dehys.norbecore.data;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@SuppressWarnings({"unused", "RedundantSuppression"})
public class UserData {

    private HashMap<UUID, String> players;


    public UserData() {
        this.players = new HashMap<>();
    }

    public UserData(HashMap<UUID, String> players) {
        this.players = players;
    }

    public static UserData retrieveData() {
        try {
            PreparedStatement preparedStatement = SQL.prepareStatement("SELECT * FROM userdata");
            ResultSet resultSet = preparedStatement.executeQuery();
            HashMap<UUID, String> players = new HashMap<>();
            while (resultSet.next()) {
                try {
                    UUID uuid = UUID.fromString(resultSet.getString("uuid"));
                    String playerName = resultSet.getString("username");
                    players.put(uuid, playerName);
                } catch (NullPointerException exception) {
                    exception.printStackTrace();
                }
            }
            return new UserData(players);
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

    public UUID getUUID(String playerName) {
        for (Map.Entry<UUID, String> entry : players.entrySet()) {
            if (entry.getValue().equalsIgnoreCase(playerName)) return entry.getKey();
        }
        return null;
    }

    public String getName(UUID uuid) {
        return players.getOrDefault(uuid, null);
    }

    public void registerPlayer(UUID uuid, String playerName) {
        players.put(uuid, playerName);
    }

    public void saveData() {
        try {
            PreparedStatement preparedStatement = SQL.prepareStatement("INSERT INTO userdata (uuid, username) VALUES (?,?) ON DUPLICATE KEY UPDATE username = ?");
            for (Map.Entry<UUID, String> entry : players.entrySet()) {
                preparedStatement.setString(1, entry.getKey().toString());
                preparedStatement.setString(2, entry.getValue());
                preparedStatement.setString(3, entry.getValue());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
