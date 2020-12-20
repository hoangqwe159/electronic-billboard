package BillboardServer.Database.Schema.BillboardSchema;

import java.util.ArrayList;
import java.util.HashMap;

public interface BillboardSchemaIF {
    ArrayList<HashMap<String,String>> getAllBillboards();
    ArrayList<HashMap<String,String>> getBillboardById(String billboard_id);
    int createBillboard(String user_id, String message, String information, String backgroundColor, String messageColor, String picture, String infoColor);
    int editBillboard(String billboard_id, String message, String information, String backgroundColor, String messageColor, String picture, String infoColor);
    int deleteBillboard(String billboard_id);
}
