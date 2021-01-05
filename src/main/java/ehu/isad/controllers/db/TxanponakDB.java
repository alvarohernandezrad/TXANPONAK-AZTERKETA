package ehu.isad.controllers.db;

import ehu.isad.model.Txanpona;
import ehu.isad.utils.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;


public class TxanponakDB{

    private static final TxanponakDB instance = new TxanponakDB();
    private static final DBController dbcontroller = DBController.getController();
    private static DecimalFormat df2 = new DecimalFormat("#.##");
    private Txanpona modelBTC;
    private Txanpona modelETH;
    private Txanpona modelLTC;

    private TxanponakDB() {
        modelETH=null;
        modelBTC=null;
        modelLTC=null;
    }

    public static TxanponakDB getInstance() {
        return instance;
    }

    public void addToDB(Txanpona model){
        String q = "insert into txanponak values("+model.getId()+",'"+model.getData()+"','"+model.getPrice()+"','"+model.getTxanpona()+"','"+model.getVolume()+"')";
        dbcontroller.execSQL(q);
    }

    public void deleteDB(){
        dbcontroller.execSQL("delete from txanponak");
    }

    public ObservableList<Txanpona> getFromDB() {
        String query = "select * from txanponak;";
        ObservableList<Txanpona> list = FXCollections.observableArrayList();
        ResultSet rs = dbcontroller.execSQL(query);
        Integer id;
        String txanpona, data;
        Float price, volume;
        Txanpona model;
        try {
            while (rs.next()) {
                id = rs.getInt("id");
                txanpona = rs.getString("mota");
                data = rs.getString("data");
                price = rs.getFloat("balioa");
                volume = rs.getFloat("bolumena");
                model = new Txanpona(id, txanpona, data, price, volume, null);
                model.setIrudia(getPortaera(model));
                list.add(model);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public Image getPortaera(Txanpona model) {
        String imagePath = Utils.getProperties().getProperty("imagePath");
        Image result = null;
        try {
            if (model.getTxanpona().equals("BTC")) {
                if (modelBTC == null) result = new Image(new FileInputStream(imagePath + "equal.png"));
                else result = compare(modelBTC,model);
                modelBTC = model;

            } else if (model.getTxanpona().equals("ETH")) {
                if (modelETH == null) result = new Image(new FileInputStream(imagePath + "equal.png"));
                else result = compare(modelETH,model);
                modelETH = model;

            } else {
                if (modelLTC == null) result = new Image(new FileInputStream(imagePath + "equal.png"));
                else result = compare(modelLTC,model);
                modelLTC = model;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

    private Image compare(Txanpona atributo, Txanpona db){
        String imagePath = Utils.getProperties().getProperty("imagePath");
        try{
            if(atributo.getPrice().compareTo(db.getPrice())==0) return new Image(new FileInputStream(imagePath + "equal.png"));
            else if(atributo.getPrice() > db.getPrice()) return new Image(new FileInputStream(imagePath + "down.png"));
            else return new Image(new FileInputStream(imagePath + "up.png"));
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
        return null;
    }
}
