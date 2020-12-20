package BillboardServer.Database.Database;

import Utils.Hash.Hash;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class Database extends DatabaseAbs {
    private String user;
    private String url;
    private String password;
    public boolean isConnected;
    private Properties dbProps;



    public Database(){
        try{
            InputStream input = new FileInputStream("src/main/java/Properties/db.props");
            dbProps = new Properties();
            dbProps.load(input);
            //Get properties
            user = dbProps.getProperty("db.user");
            url = dbProps.getProperty("db.url");
            password = dbProps.getProperty("db.password");
            isConnected =false;
            this.stmt = null;
            connection = null;
        } catch (IOException e) {
            System.out.println("Cannot connect to the database...");
        }

    }

    /**
     * Connect to the database
     */
    @Override
    public void connect(){
        try{
            Class.forName(dbProps.getProperty("db.driver"));
            System.out.println("Connecting to a selected database...");
            connection = DriverManager.getConnection(url, user , password);
            System.out.println("Connected database successfully...");
            isConnected = true;
            stmt = connection.createStatement();
            String sql = "CREATE DATABASE IF NOT EXISTS billboardapp";
            stmt.executeUpdate(sql);
            sql = "USE billboardapp";
            stmt.executeUpdate(sql);
        } catch (ClassNotFoundException | SQLException e) {
            isConnected = false;
            System.out.println(e.toString());
            System.out.println("Cannot connect to the database...");
        }
    }

    /**
     * Initialize some queries like create tables and default user
     * @throws Exception - Database is not connected
     */
    @Override
    public void initialize() throws Exception{
        if(!isConnected){
            throw new Exception("Database is not connected");
        }else {
            try {
                //STEP 4: Execute a query
                /* Create users table */
                String sql = "CREATE TABLE IF NOT EXISTS users"
                        + "("
                        + " user_id INTEGER not null auto_increment,"
                        + " username VARCHAR(70) NOT NULL,"
                        + " password VARCHAR(255) NOT NULL,"
                        + " salt VARCHAR(255) NOT NULL,"
                        + " permission VARCHAR(12) NOT NULL,"
                        + " created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,"
                        + " updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,"
                        + " UNIQUE (username),"
                        + " PRIMARY KEY (user_id)"
                        + ")";

                stmt.executeUpdate(sql);

                /* Create billboards table */
                sql = "CREATE TABLE IF NOT EXISTS billboards"
                        + "(billboard_id INTEGER NOT NULL AUTO_INCREMENT,"
                        + " user_id INTEGER,"
                        + " message VARCHAR(255),"
                        + " information VARCHAR(255),"
                        + " background_color VARCHAR(7) DEFAULT '#FFFFFF',"
                        + " message_color VARCHAR(7) DEFAULT '#000000',"
                        + " info_color VARCHAR(7) DEFAULT '#000000',"
                        + " created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,"
                        + " updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,"
                        + " picture LONGBLOB,"
                        + " PRIMARY KEY (billboard_id),"
                        + " FOREIGN KEY (user_id) REFERENCES users(user_id)"
                        + ")";

                /* Create schedules table */
                stmt.executeUpdate(sql);
                sql = "CREATE TABLE IF NOT EXISTS schedules"
                        + "("
                        + " schedule_id INTEGER not null auto_increment,"
                        + " billboard_id INTEGER,"
                        + " start_time DATETIME not null,"
                        + " time_limit INTEGER not null,"
                        + " next_run INTEGER not null,"
                        + " created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,"
                        + " updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,"
                        + " PRIMARY KEY (schedule_id),"
                        + " FOREIGN KEY (billboard_id) REFERENCES billboards(billboard_id)"
                        + ")";
                stmt.executeUpdate(sql);

                /* Create default user */
                String defaultUserName = "admin";
                String defaultSalt = Hash.generateSalt();
                String password = Hash.hashString("admin");
                String defaultPassword = Hash.hashStringWithSalt(password, defaultSalt);
                String permission = "EB,EU,CB,SB";
                sql = "INSERT IGNORE INTO users (user_id, username, password, salt,permission) VALUES" +
                        "(" + "1,"+
                        "'" + defaultUserName +"'," +
                        "'" + defaultPassword + "'," +
                        "'" + defaultSalt + "'," +
                        "'" + permission + "')";
                stmt.executeUpdate(sql);

            }catch (SQLException e) {
                System.out.println("There is a problem initializing the database");
                System.exit(0);
            }
        }
    }
}
