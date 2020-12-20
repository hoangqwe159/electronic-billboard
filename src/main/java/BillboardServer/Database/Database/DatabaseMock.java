package BillboardServer.Database.Database;

public class DatabaseMock extends DatabaseAbs {
    private boolean isConnected;
    public DatabaseMock(){
        isConnected = false;
    }

    @Override
    public void connect() {
        isConnected = true;
    }

    @Override
    public void initialize() throws Exception {
        if(!isConnected){
            throw new Exception("Database is not connected");
        }
    }
}
