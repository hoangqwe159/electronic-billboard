package BillboardServer.Database.Database;

import java.sql.Connection;
import java.sql.Statement;

public abstract class DatabaseAbs {
    public Statement stmt;
    public Connection connection;
    public abstract void connect();
    public abstract void initialize() throws Exception;
}
