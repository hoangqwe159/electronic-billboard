package BillboardServer.TokenValidator.StubCases;

import BillboardServer.Session.Session;

import java.util.Date;

/**
 * A stub return expired token
 */
public class ExpiredSessionStub extends Session {
    private String token = "abcd";
    private Date expiry = new Date(new Date().getTime() - 12351345);
    @Override
    public String getToken(){
        return this.token;
    }

    @Override
    public Date getExpiry() {
        return this.expiry;
    }

    @Override
    public void setToken(String token) {
        this.token = token;
    }
}
