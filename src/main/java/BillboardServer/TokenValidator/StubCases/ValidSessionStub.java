package BillboardServer.TokenValidator.StubCases;

import BillboardServer.Session.Session;

import java.util.Date;

/**
 * A stub return valid token
 */
public class ValidSessionStub extends Session {
    private String token = "abcd";
    private Date expiry = new Date(new Date().getTime() + 24 * 60 * 60 * 1000);

    @Override
    public String getToken() {
        return this.token;
    }

    @Override
    public Date getExpiry() {
        return this.expiry;
    }
}
