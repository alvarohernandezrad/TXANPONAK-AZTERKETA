package ehu.isad.controllers.ui;

import ehu.isad.controllers.db.TxanponakDB;
import ehu.isad.model.Txanpona;
import ehu.isad.utils.Sarea;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class TxanponakKud {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Txanpona> table;

    @FXML
    private TableColumn<Txanpona, Integer> idCol;

    @FXML
    private TableColumn<Txanpona, String> txanponCol;

    @FXML
    private TableColumn<Txanpona, String> noizCol;

    @FXML
    private TableColumn<Txanpona, Float> zenbatCol;

    @FXML
    private TableColumn<Txanpona, Float> bolumenCol;

    @FXML
    private TableColumn<Txanpona, Image> portaeraCol;

    @FXML
    private Button sartu;

    @FXML
    private Button gorde;

    @FXML
    private ComboBox<String> combo;

    private TxanponakDB txanponakDB = TxanponakDB.getInstance();

    @FXML
    void onClick(ActionEvent event) {
        Button btn = (Button)event.getSource();
        if(btn.equals(sartu)){
            sartu();
        }else{
            gorde();
        }
    }

    private void sartu(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        try {
            Txanpona model = Sarea.getNireSarea().liburuarenDatuakHasieratu(combo.getValue());
            ObservableList<Txanpona> l = table.getItems();
            model.setId(l.get(l.size()-1).getId()+1);
            model.setData(dateFormat.format(date).replace("/","-"));
            model.setTxanpona(combo.getValue());
            model.setIrudia(txanponakDB.getPortaera(model));
            l.add(model);
            table.setItems(l);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    private void gorde(){
        txanponakDB.deleteDB();
        ObservableList<Txanpona> list = table.getItems();
        for(int i=0; i < list.size(); i++){
            txanponakDB.addToDB(list.get(i));
        }
    }

    private void setItems(){
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        txanponCol.setCellValueFactory(new PropertyValueFactory<>("txanpona"));
        noizCol.setCellValueFactory(new PropertyValueFactory<>("data"));
        zenbatCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        bolumenCol.setCellValueFactory(new PropertyValueFactory<>("volume"));
        portaeraCol.setCellValueFactory(new PropertyValueFactory<>("irudia"));
        portaeraCol.setCellFactory(p -> new TableCell<>() {
            public void updateItem(Image image, boolean empty) {
                if (image != null && !empty){
                    final ImageView imageview = new ImageView();
                    imageview.setFitHeight(15);
                    imageview.setFitWidth(20);
                    imageview.setImage(image);
                    setGraphic(imageview);
                    setAlignment(Pos.CENTER);
                    // tbData.refresh();
                }else{
                    setGraphic(null);
                    setText(null);
                }
            };
        });
    }

    @FXML
    void initialize() {
        setItems();
        ObservableList<Txanpona> lista = txanponakDB.getFromDB();
        table.setItems(lista);
        combo.setEditable(false);
        ObservableList<String> l = FXCollections.observableArrayList();
        l.add("ETH");
        l.add("BTC");
        l.add("LTC");
        combo.setItems(l);
    }
}
