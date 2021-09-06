package sample.controllers;

import com.sun.glass.ui.MenuItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;
import sample.Connection.Databaseconnection;
import sample.entities.Seltproduct;
import sample.entities.product;
import sample.entities.profits;
import sample.entities.user;


import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
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
    public TextField tfusername;
    @FXML
    public PasswordField tfpw;
    @FXML
    public Button btnlogin;
    @FXML
    public Label textsec;
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
    public Label  allwin ;
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
    @FXML
    public ComboBox categorycombobox;
    @FXML
    public GridPane selltable;
    @FXML
    public ComboBox sellcategorycombobox;
    @FXML
    public Label selllabeldelete;
    @FXML
    public TextField selltfsearch;
    @FXML
    public TableColumn sellID;
    @FXML
    public TableColumn sellname;
    @FXML
    public TableColumn sellfirstprice;
    @FXML
    public TableColumn sellprice;
    @FXML
    public TableColumn sellpricesell;
    @FXML
    public TableColumn sellquantity;
    @FXML
    public TableColumn selltotalprice;
    @FXML
    public TableColumn sellwinner;
    @FXML
    public TableColumn sellcolaction;
    @FXML
    public TableView selltableview;
    @FXML
    public DatePicker selldayepicker;

    @FXML
    public Pane sellproductpaneedit;
    @FXML
    public TextField tfqselledit;
    @FXML
    public Label sellsuceedit;
    @FXML
    public TextField tfpriceselledit;
    @FXML
    public Label idselledit;
    @FXML
    public Label priceselledit;
    @FXML
    public Label quantityselllabeledit;
    @FXML
    public Label categoryselledit;
    @FXML
    public Label nameselledit;
    @FXML
    public Label firstpriceselledit;
    @FXML
    public TableColumn sellIDsell;
    @FXML
    public Label idselleditedit;
    @FXML
    public Label text;
    @FXML
    public GridPane profitspane;
    @FXML
    public TableView profitstable;
    @FXML
    public TableColumn action;
    @FXML
    public TableColumn proex;
    @FXML
    public TableColumn expenses;
    @FXML
    public TableColumn profits;
    @FXML
    public TableColumn created;
    @FXML
    public TableColumn idtable;
    @FXML
    public DatePicker date;
    @FXML
    public TextField expensestf;
    @FXML
    public Pane loginpane;
    @FXML
    public Pane sidet;
    @FXML
    public VBox tools;
    @FXML

    public Label fiald;
    @FXML
    public CheckBox checkbox;
    @FXML
    public TextField tfshowpw;

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
    //return list category
    public void getcategories() throws SQLException {
        ObservableList <String> categorylist = FXCollections.observableArrayList();
        Connection conn = connectenow.getconnention();

        String query1 = "SELECT DISTINCT category FROM product";
        PreparedStatement ps =conn.prepareStatement(query1);
        ResultSet rs = ps.executeQuery();

            while (rs.next() ){


                categorylist.add(rs.getString("category"));

            }
  categorycombobox.setItems(categorylist);
            sellcategorycombobox.setItems(categorylist);

        //

    }

    //get products from sell table
    public ObservableList<Seltproduct> getproductssell(){
        ObservableList <Seltproduct> productlist = FXCollections.observableArrayList();

        Connection conn = connectenow.getconnention();
        String query = "SELECT * FROM seltedproduct ";

        Statement st;

        ResultSet rs;

        try {
            st = conn.createStatement();
            rs =st.executeQuery(query);

            Seltproduct products;
            while (rs.next()){

                float totalprice   = rs.getFloat("pricesell") *rs.getInt("quantity") ;
                float firsttotalprice   = rs.getFloat("firstprice") *rs.getInt("quantity") ;
                float winner = totalprice -firsttotalprice;
                products =  new Seltproduct(rs.getInt("IDSELL"),rs.getInt("id"),rs.getString("name"),rs.getDate("created"),rs.getFloat("firstprice"),rs.getFloat("price"),rs.getFloat("pricesell"),rs.getInt("quantity"),rs.getString("category"), totalprice, winner);
                productlist.add(products);

            }


        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return productlist ;
    }
//showseltd products
public  void  showseltedproducts(){

    ObservableList <Seltproduct> list = getproductssell();
    sellIDsell.setCellValueFactory(new PropertyValueFactory<product,Integer>("idsell"));
    sellIDsell.setVisible(false);
    sellID.setCellValueFactory(new PropertyValueFactory<product,Integer>("id"));
    sellID.setVisible(false);
    sellname.setCellValueFactory(new PropertyValueFactory<product,String>("name"));
    sellfirstprice.setCellValueFactory(new PropertyValueFactory<product,Float>("firstprice"));
    sellfirstprice.setVisible(false);
    sellprice.setCellValueFactory(new PropertyValueFactory<product,Float>("price"));
    sellpricesell.setCellValueFactory(new PropertyValueFactory<product,Float>("sellprice"));
    sellquantity.setCellValueFactory(new PropertyValueFactory<product,Integer>("quantity"));
    selltotalprice.setCellValueFactory(new PropertyValueFactory<product,Float>("totalprice"));
    sellwinner.setCellValueFactory(new PropertyValueFactory<product,Float>("winner") );

    Callback<TableColumn<Seltproduct, Void>, TableCell<Seltproduct, Void>> cellFactory = new Callback<TableColumn<Seltproduct, Void>, TableCell<Seltproduct, Void>>() {

        public TableCell<Seltproduct, Void> call(final TableColumn<Seltproduct, Void> param) {
            final TableCell<Seltproduct, Void> cell = new TableCell<Seltproduct, Void>() {

                private final Button editButton = new Button("تعديل");


                private final Button deleteButton = new Button("حذف");
                private final HBox pane = new HBox(deleteButton, editButton);



                { pane.getStyleClass().add("space");
                    editButton.getStyleClass().add("edit");
                    deleteButton.getStyleClass().add("delete");


                    //editbutton action
                    editButton.setOnAction((ActionEvent event) -> {
                        Seltproduct data = getTableView().getItems().get(getIndex());
                        editsellproductshow(data);

                    });
                    deleteButton.setOnAction((ActionEvent event) -> {
                        Seltproduct data = getTableView().getItems().get(getIndex());
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("تنبيه ");

                        alert.setContentText(" هل تريد حذف هذا المنتج " +data.getName() +"؟");

                        Optional<ButtonType> result = alert.showAndWait();
                        if (result.get() == ButtonType.OK){
                            String query  = "DELETE FROM seltedproduct WHERE IDSELL ="+data.getIdsell()+";";
                            executeQuery(query);

                            showseltedproducts();

                        }




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
    sellcolaction.setCellFactory(cellFactory);
    selltableview.setItems(list);

}
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

        return productlist ;
    }
        public  void  showproducts(){

            ObservableList <product> list = getproducts();
            ID.setCellValueFactory(new PropertyValueFactory<product,Integer>("id"));
            ID.setVisible(false);
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
                                sellsuce.setText("");
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
                                    sellsuce.setText("");
                                }




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
        showseltedproducts();


        try {
            remplitable();
            getcategories();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


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
            String query1 = " UPDATE seltedproduct SET name = '"+ tfpnedit.getText() +"',firstprice =" +tffpriceedit.getText()+ ",price ="+tfpriceedit.getText()+",category ='"+tfcategoryedit.getText() +"' WHERE id ="+idproductedit.getText()+";";
            executeQuery(query1);
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
    //product by category table selted
    private ObservableList<Seltproduct> sellproductsbycategory(String key ) {
        ObservableList <Seltproduct> productlist = FXCollections.observableArrayList();
        Connection conn = connectenow.getconnention();

        String query  = "SELECT * FROM seltedproduct WHERE CATEGORY  ='"+key+"';";
        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            rs =st.executeQuery(query);
            Seltproduct products;
            while (rs.next()){

                float totalprice   = rs.getFloat("pricesell") *rs.getInt("quantity") ;
                float firsttotalprice   = rs.getFloat("firstprice") *rs.getInt("quantity") ;
                float winner = totalprice -firsttotalprice;
                products =  new Seltproduct(rs.getInt("IDSELL"),rs.getInt("id"),rs.getString("name"),rs.getDate("created"),rs.getFloat("firstprice"),rs.getFloat("price"),rs.getFloat("pricesell"),rs.getInt("quantity"),rs.getString("category"), totalprice, winner);
                productlist.add(products);
            }

        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return productlist;

    }
    // table product
    private ObservableList<product> productsbycategory(String key ) {
        ObservableList <product> productlist = FXCollections.observableArrayList();
        Connection conn = connectenow.getconnention();

        String query  = "SELECT * FROM PRODUCT WHERE CATEGORY  ='"+key+"'; ";
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

    private ObservableList<product> searchble() {
        ObservableList <product> productlist = FXCollections.observableArrayList();
        Connection conn = connectenow.getconnention();
        String key  =tfsearch.getText();
        String query  = "SELECT * FROM PRODUCT WHERE NAME  ='"+key+"' or CATEGORY  ='"+key+"' ;";
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
//////
public void editsellproductshow(Seltproduct product){
    pagehome.setCenter(sellproductpaneedit);
    quantityselllabeledit.setText(String.valueOf(product.getQuantity()));
    idselledit.setText(String.valueOf(product.getId()));
    tfpriceselledit.setText(String.valueOf(product.getSellprice()));
    nameselledit.setText(product.getName());
    tfqselledit.setText(String.valueOf(product.getQuantity()));
    categoryselledit.setText(product.getCategory());
    firstpriceselledit.setText(String.valueOf(product.getFirstprice()) );
    firstpriceselledit.setVisible(false);
    priceselledit.setText(String.valueOf(product.getPrice()));
    idselleditedit.setText(String.valueOf(product.getIdsell()));
    textsec.setText("");


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
    firstpricesell.setVisible(false);
    pricesell.setText(String.valueOf(product.getPrice()));


}

//add to selttable
public void addsellproduct( ){
if (Integer.parseInt(quantityselllabel.getText())>0){
    if (tfqsell.getText().isEmpty()) {
        String query = "INSERT INTO seltedproduct (id,name,created,firstprice,price,pricesell,quantity,category) VALUES ("+idsell.getText()+",'"+namesell.getText()+"',now(),"+firstpricesell.getText()+","+pricesell.getText()+","+pricesell.getText()+", 1 ,'"+categorysell.getText()+ "');";

        executeQuery(query);
  String query1 = "UPDATE productSET quantity = quantity-1 WHERE id  ="+idsell.getText()+";";

        executeQuery(query1);

    }else{
        String query = "INSERT INTO seltedproduct (id,name,created,firstprice,price,pricesell,quantity,category)VALUES ("+idsell.getText()+",'"+namesell.getText()+"',now(),"+firstpricesell.getText()
                +","+pricesell.getText()+","+tfpricesell.getText()+","+tfqsell.getText()+",'"+categorysell.getText()+ "');";

        executeQuery(query);
        String query1 = "UPDATE product SET quantity = quantity - "+tfqsell.getText() +" WHERE id  ="+idsell.getText()+";";
        executeQuery(query1);
    }


    sellsuce.setText("تم البيع "+namesell.getText());
    tfqsell.setText("");
}
else {

    sellsuce.setText("نفذت الكمية");
    sellsuce.setTextFill(Color.RED);

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
/////////////////////////////////////////////////////////////////////
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
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void cancelsell(MouseEvent mouseEvent) {
        pagehome.setCenter(table);
        showproducts();
        labeldelete.setText("");
        sellsuce.setText("");
    }

    public void addproductsell(MouseEvent mouseEvent) {



        if (Integer.parseInt(quantityselllabel.getText())>=Integer.parseInt(tfqsell.getText()) && Integer.parseInt(quantityselllabel.getText())!=  0   ){
       addsellproduct( );
    }else {
            sellsuce.setTextFill(Color.RED);
            sellsuce.setText(" الكمية  غير كافية");
        }


    }
///////////////////////////////////////////////////////////////////////////////////////////

    public void getproductbycategory(KeyEvent keyEvent) {
       String string= (String) categorycombobox.getSelectionModel().getSelectedItem();


        ObservableList <product> list = productsbycategory(string);
        ID.setCellValueFactory(new PropertyValueFactory<product,Integer>("id"));
        name.setCellValueFactory(new PropertyValueFactory<product,String>("name"));
        firstprice.setCellValueFactory(new PropertyValueFactory<product,Float>("firstprice"));
        price.setCellValueFactory(new PropertyValueFactory<product,Float>("price"));
        quantity.setCellValueFactory(new PropertyValueFactory<product,Integer>("quantity"));
        totalpricec.setCellValueFactory(new PropertyValueFactory<product,Float>("totalprice"));
        winner.setCellValueFactory(new PropertyValueFactory<product,Float>("winner") );
        tableview.setItems(list);
    }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void btngotoseltedproduct(MouseEvent mouseEvent) {
        pagehome.setCenter(selltable);
        showseltedproducts();
        try {
            getcategories();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }



    }
/////////////////////////////////////////////////////////////////////////////////////////////////////
    public void getsellproductbycategory(KeyEvent keyEvent) {
        String string= (String) sellcategorycombobox.getSelectionModel().getSelectedItem();
        ObservableList <Seltproduct> list = sellproductsbycategory(string);
        ID.setCellValueFactory(new PropertyValueFactory<product,Integer>("id"));
        name.setCellValueFactory(new PropertyValueFactory<product,String>("name"));
        firstprice.setCellValueFactory(new PropertyValueFactory<product,Float>("firstprice"));
        price.setCellValueFactory(new PropertyValueFactory<product,Float>("price"));
        quantity.setCellValueFactory(new PropertyValueFactory<product,Integer>("quantity"));
        totalpricec.setCellValueFactory(new PropertyValueFactory<product,Float>("totalprice"));
        winner.setCellValueFactory(new PropertyValueFactory<product,Float>("winner") );
        selltableview.setItems(list);
    }
//get product by date

    public    ObservableList<Seltproduct> getproductsbydate(LocalDate key ) {
        ObservableList <Seltproduct> productlist = FXCollections.observableArrayList();
        Connection conn = connectenow.getconnention();

        String query  = "SELECT * FROM seltedproduct WHERE created ='"+key+"';";
        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            rs =st.executeQuery(query);
            Seltproduct products;
            while (rs.next()){

                float totalprice   = rs.getFloat("pricesell") *rs.getInt("quantity") ;
                float firsttotalprice   = rs.getFloat("firstprice") *rs.getInt("quantity") ;
                float winner = totalprice -firsttotalprice;
                products =  new Seltproduct(rs.getInt("IDSELL"),rs.getInt("id"),rs.getString("name"),rs.getDate("created"),rs.getFloat("firstprice"),rs.getFloat("price"),rs.getFloat("pricesell"),rs.getInt("quantity"),rs.getString("category"), totalprice, winner);
                productlist.add(products);
            }

        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return productlist;

    }
/////////////////////

    public void getproductbydate(KeyEvent keyEvent) {
       LocalDate key =  selldayepicker.getValue();

        ObservableList <Seltproduct> list = getproductsbydate( key);
        sellIDsell.setCellValueFactory(new PropertyValueFactory<product,Integer>("idsell"));
        sellIDsell.setVisible(false);
        sellID.setCellValueFactory(new PropertyValueFactory<product,Integer>("id"));
        sellID.setVisible(false);
        sellname.setCellValueFactory(new PropertyValueFactory<product,String>("name"));
        sellfirstprice.setCellValueFactory(new PropertyValueFactory<product,Float>("firstprice"));
        sellfirstprice.setVisible(false);
        sellprice.setCellValueFactory(new PropertyValueFactory<product,Float>("price"));
        sellpricesell.setCellValueFactory(new PropertyValueFactory<product,Float>("sellprice"));
        sellquantity.setCellValueFactory(new PropertyValueFactory<product,Integer>("quantity"));
        selltotalprice.setCellValueFactory(new PropertyValueFactory<product,Float>("totalprice"));
        sellwinner.setCellValueFactory(new PropertyValueFactory<product,Float>("winner") );
        selltableview.setItems(list);
    }
    //search  table 2

    private ObservableList<Seltproduct> sellsearchble() {
        ObservableList <Seltproduct> productlist = FXCollections.observableArrayList();
        Connection conn = connectenow.getconnention();
        String key  =selltfsearch.getText();
        String query  = "SELECT * FROM seltedproduct WHERE NAME  ='"+key+"' or CATEGORY  ='"+key+"' ;";
        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            rs =st.executeQuery(query);
            Seltproduct products;
            while (rs.next()){

                float totalprice   = rs.getFloat("pricesell") *rs.getInt("quantity") ;
                float firsttotalprice   = rs.getFloat("firstprice") *rs.getInt("quantity") ;
                float winner = totalprice -firsttotalprice;
                products =  new Seltproduct(rs.getInt("IDSELL"),rs.getInt("id"),rs.getString("name"),rs.getDate("created"),rs.getFloat("firstprice"),rs.getFloat("price"),rs.getFloat("pricesell"),rs.getInt("quantity"),rs.getString("category"), totalprice, winner);
                productlist.add(products);
            }

        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return productlist;

    }
    public void sellsearch(MouseEvent mouseEvent) {
        ObservableList <Seltproduct> list = sellsearchble() ;
        sellIDsell.setCellValueFactory(new PropertyValueFactory<product,Integer>("idsell"));
        sellIDsell.setVisible(false);
        sellID.setCellValueFactory(new PropertyValueFactory<product,Integer>("id"));
        sellID.setVisible(false);
        sellname.setCellValueFactory(new PropertyValueFactory<product,String>("name"));
        sellfirstprice.setCellValueFactory(new PropertyValueFactory<product,Float>("firstprice"));
        sellfirstprice.setVisible(false);
        sellprice.setCellValueFactory(new PropertyValueFactory<product,Float>("price"));
        sellpricesell.setCellValueFactory(new PropertyValueFactory<product,Float>("sellprice"));
        sellquantity.setCellValueFactory(new PropertyValueFactory<product,Integer>("quantity"));
        selltotalprice.setCellValueFactory(new PropertyValueFactory<product,Float>("totalprice"));
        sellwinner.setCellValueFactory(new PropertyValueFactory<product,Float>("winner") );
        selltableview.setItems(list);
    }

    public void sellsearchmethode(KeyEvent keyEvent) {
        ObservableList <Seltproduct> list = sellsearchble() ;
        sellID.setCellValueFactory(new PropertyValueFactory<product,Integer>("id"));
        sellID.setVisible(false);
        sellname.setCellValueFactory(new PropertyValueFactory<product,String>("name"));
        sellfirstprice.setCellValueFactory(new PropertyValueFactory<product,Float>("firstprice"));
        sellfirstprice.setVisible(false);
        sellprice.setCellValueFactory(new PropertyValueFactory<product,Float>("price"));
        sellpricesell.setCellValueFactory(new PropertyValueFactory<product,Float>("sellprice"));
        sellquantity.setCellValueFactory(new PropertyValueFactory<product,Integer>("quantity"));
        selltotalprice.setCellValueFactory(new PropertyValueFactory<product,Float>("totalprice"));
        sellwinner.setCellValueFactory(new PropertyValueFactory<product,Float>("winner") );
        selltableview.setItems(list);
    }
////////////////////////////////////////////////////////////////////////////////////
private  void  updatesellRecord(){

    if (tfpriceselledit.getText().isEmpty() || tfqselledit.getText().isEmpty() ) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText("ادخل الكمية او السعر من فضلك  من فضلك");
        alert.showAndWait();


    }else{
        String query1 = " UPDATE seltedproduct SET pricesell = "+ tfpriceselledit.getText() +",quantity =" +tfqselledit.getText()+ "  WHERE IDSELL ="+idselleditedit.getText()+";";
        executeQuery(query1);

    }

}

    public void editproductsell(MouseEvent mouseEvent) throws SQLException {
        String query ="SELECT quantity from seltedproduct WHERE IDSELL ="+ idselleditedit.getText()+";";
        String query2 ="SELECT quantity from product WHERE id ="+ idselledit.getText()+";";
        Connection conn = connectenow.getconnention();

        PreparedStatement s = conn.prepareStatement(query);
        PreparedStatement s1 = conn.prepareStatement(query2);

        ResultSet rs = s.executeQuery();
        ResultSet rs1 = s1.executeQuery();
        int quantity = 0;
        int quantityp = 0;
        while(rs.next()) {
            quantity = rs.getInt("quantity");

        }
        while(rs1.next()) {
            quantityp = rs1.getInt("quantity");

        }
  int result = quantityp - Integer.parseInt(tfqselledit.getText()) +quantity;
        Boolean True=false;
        if (Integer.parseInt(tfqselledit.getText())<= quantity){
            if (True==false){

                String query1 = "UPDATE product SET quantity = quantity +"+
                        quantity+"-"+ tfqselledit.getText()+" WHERE id  ="+idselledit.getText()+";";

                executeQuery(query1);
                True =true;
            }
            if (True==true){
                updatesellRecord();
            }
            textsec.setText("تم تعديل بنجاح");
            textsec.getStyleClass().add("txtgreen");
        }else if (quantityp!=0 && result>=0 ){
        if (True==false){

                    String query1 = "UPDATE product SET quantity = quantity +"+
                            quantity+"-"+ tfqselledit.getText()+" WHERE id  ="+idselledit.getText()+";";

            executeQuery(query1);
            True =true;
        }
        if (True==true){
             updatesellRecord();
          }
          textsec.setText("تم تعديل بنجاح");
        textsec.getStyleClass().add("txtgreen");

  }
  else{
      textsec.getStyleClass().add("txtred");
      textsec.setText("نفذ المنتج  ");

  }


    }
    ////////////////////////////
    public void remplitable() throws SQLException {
        Connection conn = connectenow.getconnention();
        String query1 = "select sum(quantity*pricesell-seltedproduct.quantity*firstprice) as sum ,  created from seltedproduct group by  created";
        PreparedStatement ps1 =conn.prepareStatement(query1);


        ResultSet rs1 = ps1.executeQuery();

        while (rs1.next() ){

            String date  = rs1.getString("created");
            String query = "INSERT INTO profits ( `create` , dayprofits )\n" +
                    "VALUES (   DATE ' "+rs1.getDate("created")+"' , "+ rs1.getFloat("sum")  +")" +
            "ON DUPLICATE KEY UPDATE dayprofits = "+ rs1.getString("sum");
            executeQuery(query);

        }

    }
    public void getprofits() throws SQLException {
        remplitable();
        pagehome.setCenter(profitspane);
        allwinner();
        ObservableList <profits> list =   remplitableprofits();
        idtable.setCellValueFactory(new PropertyValueFactory<product,Integer>("id"));
        idtable.setVisible(false);
        created.setCellValueFactory(new PropertyValueFactory<product,Date>("create"));
        profits.setCellValueFactory(new PropertyValueFactory<product,Float>("dayprofits"));
        expenses.setCellValueFactory(new PropertyValueFactory<product,Float>("expenses"));

        proex.setCellValueFactory(new PropertyValueFactory<product,Float>("result") );



        Callback<TableColumn<profits, Void>, TableCell<profits, Void>> cellFactory = new Callback<TableColumn<profits, Void>, TableCell<profits, Void>>() {

            public TableCell<profits, Void> call(final TableColumn<profits, Void> param) {
                final TableCell<profits, Void> cell = new TableCell<profits, Void>() {

                    private final Button editButton = new Button("اضافة مصروف ");



                    private final HBox pane = new HBox(editButton);



                    { pane.getStyleClass().add("space");
                        editButton.getStyleClass().add("edit");



                        //editbutton action
                        editButton.setOnAction((ActionEvent event) -> {
                            profits data = getTableView().getItems().get(getIndex());
                            addprofits( data);
                            try {
                                getprofits();
                            } catch (SQLException throwables) {
                                throwables.printStackTrace();
                            }

                        });
                        expensestf.setText("");


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
        action.setCellFactory(cellFactory);




        profitstable.setItems(list);

    }



    ///////////////////////////////////////
    private  void  addprofits(profits data){

        if (expensestf.getText().isEmpty()  ) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("ادخل المصروف من فضلك  من فضلك");
            alert.showAndWait();


        }else{
            String query1 = " UPDATE profits SET expenses = "+ expensestf.getText() +"  WHERE id ="+data.getId()+";";
            executeQuery(query1);

        }

    }
/////////////////////////////////////////////////

    private ObservableList<profits> remplitableprofits() {
        ObservableList <profits> productlist = FXCollections.observableArrayList();
        Connection conn = connectenow.getconnention();

        String query  = "SELECT * FROM profits  ;";
        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            rs =st.executeQuery(query);
            profits profits;
            while (rs.next()){


                      Float proex=rs.getFloat("dayprofits")-rs.getFloat("expenses");
                      profits =  new profits(rs.getInt("id"),rs.getFloat("expenses"),rs.getFloat("dayprofits"),rs.getDate("create"),proex);
                productlist.add(profits);
            }

        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return productlist;

    }
////////////////////////////////////

    public void gotoprofits(MouseEvent mouseEvent) throws SQLException {
        remplitable();
        pagehome.setCenter(profitspane);
        allwinner();
        ObservableList <profits> list =   remplitableprofits();
        idtable.setCellValueFactory(new PropertyValueFactory<product,Integer>("id"));
        idtable.setVisible(false);
        created.setCellValueFactory(new PropertyValueFactory<product,Date>("create"));
        profits.setCellValueFactory(new PropertyValueFactory<product,Float>("dayprofits"));
        expenses.setCellValueFactory(new PropertyValueFactory<product,Float>("expenses"));

        proex.setCellValueFactory(new PropertyValueFactory<product,Float>("result") );



        Callback<TableColumn<profits, Void>, TableCell<profits, Void>> cellFactory = new Callback<TableColumn<profits, Void>, TableCell<profits, Void>>() {

            public TableCell<profits, Void> call(final TableColumn<profits, Void> param) {
                final TableCell<profits, Void> cell = new TableCell<profits, Void>() {

                    private final Button editButton = new Button("اضافة مصروف ");



                    private final HBox pane = new HBox( editButton);



                    { pane.getStyleClass().add("space");
                        editButton.getStyleClass().add("edit");



                        //editbutton action
                        editButton.setOnAction((ActionEvent event) -> {
                            profits data = getTableView().getItems().get(getIndex());
                            addprofits( data);
                            try {
                                getprofits();
                            } catch (SQLException throwables) {
                                throwables.printStackTrace();
                            }

                            });
                        expensestf.setText("");


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
        action.setCellFactory(cellFactory);




        profitstable.setItems(list);
    }
    ///
    public ObservableList<profits> searchbydate( LocalDate key) {
        ObservableList <profits> productlist = FXCollections.observableArrayList();
        Connection conn = connectenow.getconnention();

        String query  = "SELECT * FROM profits where `create`  =  DATE ' "+  key+" ' ;";
        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            rs =st.executeQuery(query);
            profits profits;
            while (rs.next()){


                Float proex=rs.getFloat("dayprofits")-rs.getFloat("expenses");
                profits =  new profits(rs.getInt("id"),rs.getFloat("expenses"),rs.getFloat("dayprofits"),rs.getDate("create"),proex);
                productlist.add(profits);
            }

        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return productlist;

    }

    public void getbydate(KeyEvent keyEvent) {

       LocalDate key =date.getValue();
               ObservableList <profits> list =   searchbydate(key);
        idtable.setCellValueFactory(new PropertyValueFactory<product,Integer>("id"));
        idtable.setVisible(false);
        created.setCellValueFactory(new PropertyValueFactory<product,Date>("create"));
        profits.setCellValueFactory(new PropertyValueFactory<product,Float>("dayprofits"));
        expenses.setCellValueFactory(new PropertyValueFactory<product,Float>("expenses"));

        proex.setCellValueFactory(new PropertyValueFactory<product,Float>("result") );
        profitstable.setItems(list);
    }

    public void deleteallproduct(MouseEvent mouseEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("تنبيه ");

        alert.setContentText(" هل تريد حذف الكل   "+"؟");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            String query  = "truncate  table  product";
            executeQuery(query);
            pagehome.setCenter(table);
            showproducts();


        }

    }

    public void deleteallsell(MouseEvent mouseEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("تنبيه ");

        alert.setContentText(" هل تريد حذف الكل   "+"؟");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            String query  = "truncate  table  seltedproduct";
            String query1 = "truncate  table  profits";
            executeQuery(query);
            executeQuery(query1);
            pagehome.setCenter(selltable);
            showseltedproducts();


        }
    }

    public void login(MouseEvent mouseEvent) {

        Connection conn = connectenow.getconnention();

        String query  = "SELECT * FROM users ";
        Statement st;
        ResultSet rs;

        try {
            st = conn.createStatement();
            rs =st.executeQuery(query);
            user user;
            while (rs.next()){



                user =  new user(rs.getString("username"),rs.getString("password"));
                 if (tfusername.getText().equalsIgnoreCase(user.getUsername())){
                if (tfpw.getText().equals(user.getPassword())|| tfprice.getText().equals(user.getPassword())){
                    showproducts();
                    pagehome.setCenter(table);
                    pagehome.setRight(tools);
                } else{
                    fiald.setText("كلمة المرور خاطئة");
                }
                 }else{
                     fiald.setText("اسم المستخدم غير صحيح");
                }

            }

        } catch (Exception exception) {
            exception.printStackTrace();
        }


    }


    public void showpw(MouseEvent mouseEvent) {
        if (checkbox.isSelected()) {
            tfshowpw.setText(tfpw.getText());
            tfshowpw.setVisible(true);
            tfpw.setVisible(false);
            return;
        }
        tfpw.setText(tfshowpw.getText());
        tfpw.setVisible(true);
        tfshowpw.setVisible(false);

    }
    public void allwinner(){
        Connection conn = connectenow.getconnention();

        String query  = "SELECT sum(dayprofits-profits.expenses) as allwin FROM profits ";
        Statement st;
        ResultSet rs;
       Float win ;
        try {
            st = conn.createStatement();
            rs =st.executeQuery(query);

            while (rs.next()){
                win=rs.getFloat("allwin");
                allwin.setText(String.valueOf(win));






            }

        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }

    public void loginin(KeyEvent keyEvent) {
        Connection conn = connectenow.getconnention();

        String query  = "SELECT * FROM users ";
        Statement st;
        ResultSet rs;

        try {
            st = conn.createStatement();
            rs =st.executeQuery(query);
            user user;
            while (rs.next()){



                user =  new user(rs.getString("username"),rs.getString("password"));
                if (tfusername.getText().equalsIgnoreCase(user.getUsername())){
                    if (tfpw.getText().equals(user.getPassword())|| tfprice.getText().equals(user.getPassword())){
                        showproducts();
                        pagehome.setCenter(table);
                        pagehome.setRight(tools);
                    } else{
                        fiald.setText("كلمة المرور خاطئة");
                    }
                }else{
                    fiald.setText("اسم المستخدم غير صحيح");
                }

            }

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}



