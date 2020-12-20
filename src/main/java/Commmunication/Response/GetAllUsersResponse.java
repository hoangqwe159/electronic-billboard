package Commmunication.Response;

import Commmunication.Message;

import java.nio.ByteBuffer;

/**
 * Represent a get all user response (contains only one entry each response)
 */
public class GetAllUsersResponse extends Response {
    private String userId;
    private String username;
    private String permission;
    private String createdAt;
    private String updatedAt;

    @Override
    public void fromBytes(ByteBuffer buffer) {
        String payload = stringFromMsg(buffer);
        String[] result = payload.split("%%%%");
        userId = result[0];
        username = result[1];
        permission = result[2];
        createdAt = result[3];
        updatedAt = result[4];
    }
    @Override
    public void toBytes(ByteBuffer buffer) {
        String payload = userId + "%%%%" +
                username + "%%%%" +
                permission + "%%%%" +
                createdAt + "%%%%" +
                updatedAt
                ;
        stringToMsg(buffer, payload);
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
