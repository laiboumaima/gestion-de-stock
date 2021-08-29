package sample.controllers;

import com.sun.glass.ui.MenuItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import javafx.scene.paint.Color;
import javafx.util.Callback;
import sample.Connection.Databaseconnection;
import sample.entities.product;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    public Label addsuce;
    @FXML
    public TableColumn totalpricec;
    @FXML
    public TableColumn winner;
    @FXML
    public Label currenttime;
    @FXML
    public TextField tfsearch;
    @FXML
    public TableColumn  <product,Void> colaction;
    @FXML
    public Label labeldelete;
    //edit fields
    @FXML
    public TextField tfpnedit;
    @FXML
    public TextField tffpriceedit;
    @FXML
    public TextField tfpriceedit;
    @FXML
    public TextField tfqedit;
    @FXML
    public Button btneditproduct;
    @FXML
    public TextField tfcategoryedit;
    @FXML
    public Pane editpage;
    @FXML
    public Label idproductedit;
    @FXML
    public Label addsuceedit;
    ///////sell
    @FXML
    public Pane sellproductpane;
    @FXML
    public TextField tfqsell;
    @FXML
    public Label categorysell;
    @FXML
    public TextField tfpricesell;
    @FXML
    public Label firstpricesell;
    @FXML
    public Label namesell;
    @FXML
    public Label pricesell;
    @FXML
    public Label idsell;
    @FXML
    public Label sellsuce;
    @FXML
    public Label quantityselllabel;

    Databaseconnection connectenow = new Databaseconnection();
    @FXML
    public Button btnallproducts;
    @FXML
    public Pane addnewwproduct;
    @FXML
    public Button btngoaddnewproduct;
    @FXML
    public BorderPane pagehome;
    @FXML
    public GridPane table;
    @FXML
    public TextField tfcategory;

    @FXML
    public TextField tfpn;
    @FXML
    public TextField tffprice;
    @FXML
    public TextField tfprice;
    @FXML
    public TextField tfq;

    @FXML
    public Button btnadd;
    @FXML
    public TableView  <product> tableview;
    @FXML
    public TableColumn  <product,Integer> quantity;
    @FXML
    public TableColumn  <product,Float>  firstprice;
    @FXML
    public TableColumn <product,String>  name;
    @FXML
    public TableColumn <product,Integer>  ID;
    @FXML
    public TableColumn  <product,Float>   price;
   //show product
    public ObservableList<product> getproducts(){
       ObservableList <product> productlist = FXCollections.observableArrayList();
       Connection conn = connectenow.getconnention();
       String query = "SELECT * FROM product";
        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            rs =st.executeQuery(query);
            product products;
            while (rs.next()){

                float totalprice   = rs.getFloat("price") *rs.getInt("quantity") ;
                float firsttotalprice   = rs.getFloat("firstprice") *rs.getInt("quantity") ;
                float winner = totalprice -firsttotalprice;
                products =  new product(rs.getInt("id"),rs.getString("name"),rs.getFloat("firstprice"),rs.getFloat("price"),rs.getInt("quantity"),rs.getString("category"), totalprice, winner);
                 productlist.add(products);
            }

        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return productlist;
    }
        public  void  showproducts(){

            ObservableList <product> list = getproducts();
            ID.setCellValueFactory(new PropertyValueFactory<product,Integer>("id"));
            name.setCellValueFactory(new PropertyValueFactory<product,String>("name"));
          firstprice.setCellValueFactory(new PropertyValueFactory<product,Float>("firstprice"));
            price.setCellValueFactory(new PropertyValueFactory<product,Float>("price"));
            quantity.setCellValueFactory(new PropertyValueFactory<product,Integer>("quantity"));
            totalpricec.setCellValueFactory(new PropertyValueFactory<product,Float>("totalprice"));
            winner.setCellValueFactory(new PropertyValueFactory<product,Float>("winner") );

            Callback<TableColumn<product, Void>, TableCell<product, Void>> cellFactory = new Callback<TableColumn<product, Void>, TableCell<product, Void>>() {

                public TableCell<product, Void> call(final TableColumn<product, Void> param) {
                    final TableCell<product, Void> cell = new TableCell<product, Void>() {
                        private final Button sellButton = new Button("بيع");
                        private final Button editButton = new Button("تعديل");


                        private final Button deleteButton = new Button("حذف");
                        private final HBox pane = new HBox(sellButton,deleteButton, editButton);



                        { pane.getStyleClass().add("space");
                            editButton.getStyleClass().add("edit");
                            deleteButton.getStyleClass().add("delete");
                            sellButton.getStyleClass().add("sell");
                            //sellbutto action

                            sellButton.setOnAction((ActionEvent event) -> {
                                product data = getTableView().getItems().get(getIndex());
                                                sellproductshow(data);
                            });

                            //editbutton action
                            editButton.setOnAction((ActionEvent event) -> {
                                product data = getTableView().getItems().get(getIndex());
                                editproductshow(data);
                                sellsuce.setText("");
                            });
                            deleteButton.setOnAction((ActionEvent event) -> {
                                product data = getTableView().getItems().get(getIndex());
                                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                alert.setTitle("تنبيه ");

                                alert.setContentText(" هل تريد حذف هذا المنتج " +data.getName() +"؟");

                                Optional<ButtonType> result = alert.showAndWait();
                                if (result.get() == ButtonType.OK){
                                    String query  = "DELETE FROM product WHERE id ="+data.getId()+";";
                                    executeQuery(query);
                                    labeldelete.setText("تم حذف "+ data.getName()+" بنجاح");

                                    showproducts();

                                }

                                System.out.println("deletedata: " + data.getId());


                            });

                        }

                        @Override
                        public void updateItem(Void item, boolean empty) {
                            super.updateItem(item, empty);
                            setGraphic(empty ? null : pane);
                        }
                    };
                    return cell;
                }
            };
            colaction.setCellFactory(cellFactory);
            tableview.setItems(list);

        }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showproducts();


    }





    public void pageallprocuts(MouseEvent mouseEvent) {
        pagehome.setCenter(table);
        showproducts();
        labeldelete.setText("");

    }
  //methode go to
    public void gotoaddproductpage(MouseEvent mouseEvent)  {

        pagehome.setCenter(addnewwproduct);

    }

    
    //insert data
    private  void  insertRecord(){
        if (tfpn.getText().isEmpty() || tffprice.getText().isEmpty() || tfprice.getText().isEmpty() || tfq.getText().isEmpty()|| tfcategory.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("ادخل المعلومات من فضلك");
            alert.showAndWait();
        }else{
            String query = " INSERT INTO product VALUES ( null ,'"+ tfpn.getText() +"'," +tffprice.getText()+ ","+tfprice.getText()+","+tfq.getText()+",'"+tfcategory.getText() +"');";
            executeQuery(query);
            addsuce.setText("تمت الاضافة بنجاح");
            candelmethod();
        }
    }
    //update data
    private  void  updateRecord(){
        if (tfpnedit.getText().isEmpty() || tffpriceedit.getText().isEmpty() || tfpriceedit.getText().isEmpty() || tfqedit.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("ادخل كل المعلومات من فضلك");
            alert.showAndWait();
        }else{
            String query = " UPDATE product SET name = '"+ tfpnedit.getText() +"',firstprice =" +tffpriceedit.getText()+ ",price ="+tfpriceedit.getText()+",quantity ="+tfqedit.getText()+",category ='"+tfcategoryedit.getText() +"' WHERE id ="+idproductedit.getText()+";";
            executeQuery(query);

        }
    }

    private void executeQuery(  String query) {
        Connection conn = connectenow.getconnention();
        Statement st;
        try {
            st  = conn.createStatement();
            st.executeUpdate(query);

        }catch ( Exception ex){
            ex.printStackTrace();

        }
    }
// insert button
    public void insertproduct(MouseEvent mouseEvent) {
        insertRecord();

    }

    public void cancel(MouseEvent mouseEvent) {
      candelmethod();


    }
    private  void candelmethod() {
        tfpn.setText(null);
        tfcategory.setText(null);
        tffprice.setText(null);
        tfq.setText(null);
        tfprice.setText(null);



    }

    public void search(MouseEvent mouseEvent) {

        ObservableList <product> list =   searchble();
        ID.setCellValueFactory(new PropertyValueFactory<product,Integer>("id"));
        name.setCellValueFactory(new PropertyValueFactory<product,String>("name"));
        firstprice.setCellValueFactory(new PropertyValueFactory<product,Float>("firstprice"));
        price.setCellValueFactory(new PropertyValueFactory<product,Float>("price"));
        quantity.setCellValueFactory(new PropertyValueFactory<product,Integer>("quantity"));
        totalpricec.setCellValueFactory(new PropertyValueFactory<product,Float>("totalprice"));
        winner.setCellValueFactory(new PropertyValueFactory<product,Float>("winner") );
        tableview.setItems(list);

    }
    private ObservableList<product> searchble() {
        ObservableList <product> productlist = FXCollections.observableArrayList();
        Connection conn = connectenow.getconnention();
        String key  =tfsearch.getText();
        String query  = "SELECT *FROM PRODUCT WHERE NAME  ='"+key+"' or CATEGORY  ='"+key+"';";
        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            rs =st.executeQuery(query);
            product products;
            while (rs.next()){

                float totalprice   = rs.getFloat("price") *rs.getInt("quantity") ;
                float firsttotalprice   = rs.getFloat("firstprice") *rs.getInt("quantity") ;
                float winner = totalprice -firsttotalprice;
                products =  new product(rs.getInt("id"),rs.getString("name"),rs.getFloat("firstprice"),rs.getFloat("price"),rs.getInt("quantity"),rs.getString("category"), totalprice, winner);
                productlist.add(products);
            }

        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return productlist;

    }

public void editproductshow(product product){
        pagehome.setCenter(editpage);
        idproductedit.setText(String.valueOf(product.getId()));
        tfpnedit.setText(product.getName());
        tfcategoryedit.setText(product.getCategory());
      tfqedit.setText(String.valueOf(product.getQuantity()));
        tffpriceedit.setText(String.valueOf(product.getFirstprice()) );
        tfpriceedit.setText(String.valueOf(product.getPrice()));


}

//sell product
public void sellproductshow(product product){
    pagehome.setCenter(sellproductpane);
    quantityselllabel.setText(String.valueOf(product.getQuantity()));
    idsell.setText(String.valueOf(product.getId()));
    tfpricesell.setText(String.valueOf(product.getPrice()));
    namesell.setText(product.getName());
    categorysell.setText(product.getCategory());
    firstpricesell.setText(String.valueOf(product.getFirstprice()) );
    pricesell.setText(String.valueOf(product.getPrice()));


}
//add to selttable
public void addsellproduct( ){
if (Integer.parseInt(quantityselllabel.getText())>0){
    if (tfqsell.getText().isEmpty()) {
        String query = "INSERT INTO seltedproduct (id,name,created,firstprice,price,pricesell,quantity,category) VALUES (null,'"+namesell.getText()+"',now(),"+firstpricesell.getText()+","+pricesell.getText()+","+pricesell.getText()+", 1 ,'"+categorysell.getText()+ "');";

        executeQuery(query);
  String query1 = "UPDATE productSET quantity = quantity-1 WHERE id  ="+idsell.getText()+";";
        executeQuery(query1);

    }else{
        String query = "INSERT INTO seltedproduct (id,name,created,firstprice,price,pricesell,quantity,category)VALUES (null,'"+namesell.getText()+"',now(),"+firstpricesell.getText()
                +","+pricesell.getText()+","+tfpricesell.getText()+","+tfqsell.getText()+",'"+categorysell.getText()+ "');";

        executeQuery(query);
        String query1 = "UPDATE product SET quantity = quantity - "+tfqsell.getText() +" WHERE id  ="+idsell.getText()+";";
        executeQuery(query1);
    }


    sellsuce.setText("تم البيع "+namesell.getText());
    tfqsell.setText("");
}
else {
    sellsuce.setTextFill(Color.RED);
    sellsuce.setText("نفذت الكمية");

}
}

    public void editproduct(MouseEvent mouseEvent) {
        updateRecord();
        showproducts();
        pagehome.setCenter(table);

    }

    public void canceledit(MouseEvent mouseEvent) {
        pagehome.setCenter(table);
        showproducts();
        labeldelete.setText("");
    }

    public void searchmethode(KeyEvent keyEvent) {
        ObservableList <product> list =   searchble();
        ID.setCellValueFactory(new PropertyValueFactory<product,Integer>("id"));
        name.setCellValueFactory(new PropertyValueFactory<product,String>("name"));
        firstprice.setCellValueFactory(new PropertyValueFactory<product,Float>("firstprice"));
        price.setCellValueFactory(new PropertyValueFactory<product,Float>("price"));
        quantity.setCellValueFactory(new PropertyValueFactory<product,Integer>("quantity"));
        totalpricec.setCellValueFactory(new PropertyValueFactory<product,Float>("totalprice"));
        winner.setCellValueFactory(new PropertyValueFactory<product,Float>("winner") );
        tableview.setItems(list);
    }

    public void cancelsell(MouseEvent mouseEvent) {
        pagehome.setCenter(table);
        showproducts();
        labeldelete.setText("");
        sellsuce.setText("");
    }

    public void addproductsell(MouseEvent mouseEvent) {
        if (Integer.parseInt(quantityselllabel.getText())>Integer.parseInt(tfqsell.getText()) || Integer.parseInt(quantityselllabel.getText())==  0  ){
        addsellproduct( );
    }else {
            sellsuce.setTextFill(Color.RED);
            sellsuce.setText(" الكمية  غير كافية");
        }

    }
}
