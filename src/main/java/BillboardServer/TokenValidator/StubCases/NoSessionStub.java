package BillboardServer.TokenValidator.StubCases;

import BillboardServer.Session.Session;

/**
 * A stub return no token
 */
public class NoSessionStub extends Session {
    @Override
    public String getToken() {
        return null;
    }
}
