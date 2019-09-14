package com.ignatius.controller;

import com.ignatius.entity.Category;
import com.ignatius.entity.Item;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;

//PBO2P02-1772003-IgnatiusGiovan
//Membuat tampilan untuk toko kecil

public class tokoController implements Initializable {
    @FXML
    private TextField txtName;  //menyimpan value nama item yang diisi pada textField
    @FXML
    private TextField txtPrice; //menyimpan value harga item yang diisi pada textField
    @FXML
    private TextField txtCatName; //menyimpan value nama kategori item yang diisi pada textField
    @FXML
    private TableView<Item> table1;
    @FXML
    private TableColumn<Item, String> col1; //Digunakan untuk mengisi data nama item pada column 1
    @FXML
    private TableColumn<Item, String> col3; //Digunakan untuk mengisi data nama kategori item pada column 3
    @FXML
    private TableColumn<Item, String> col2; //Digunakan untuk mengisi data harga item pada column 2
    @FXML
    private ComboBox<Category> downCat; //Digunakan untuk menyimpan list kategori yang sudah disave
    @FXML
    private Button saveBtn;
    @FXML
    private Button resetBtn;
    @FXML
    private Button updateBtn;

    private ObservableList<Item> items;     //List penyimpan nama, harga, dan nama kategori item
    private ObservableList<Category> categories; //List penyimpan nama kategori item
    Alert alert = new Alert(Alert.AlertType.ERROR); //Declare alert error

    //btnSave digunakan pada saat ingin menyimpan nama, harga, nama kategori item ke dalam tabel
    public void btnSave(ActionEvent actionEvent) {
        Item item = new Item();
        if(txtName.getText().isEmpty() || downCat.getValue() == null || txtPrice.getText().isEmpty()){
            alert.setContentText("Please fill name/ price/ category");
        } else{
            boolean ada1 = false;
            item.setNama(txtName.getText());
            for (Item i : items){
                if(i.getNama().equals(item.getNama())){
                    ada1 = true;
                    alert.setContentText("Duplicate item found");
                    alert.showAndWait();
                    break;
                }
            }if(ada1==false){
                item.setPrice(Double.valueOf(txtPrice.getText()));
                item.setCategory(downCat.getValue());
                items.add(item);
            }
        }
    }
    //btnReset digunakan pada saat ingin meng-clear textfield dan combobox
    public void btnReset(ActionEvent actionEvent) {
        downCat.setValue(null);
        txtName.setText("");
        txtPrice.setText("");
        txtCatName.setText("");
        saveBtn.setDisable(false);
        updateBtn.setDisable(true);
    }
    //btnUpdate digunakan pada saat ingin meng-update nama, harga, nama kategori item yang sudah tercantum pada tabel
    public void btnUpdate(ActionEvent actionEvent) {
        saveBtn.setDisable(true);
        updateBtn.setDisable(false);
        Item item = table1.getSelectionModel().getSelectedItem();
        item.setNama(txtName.getText());
        item.setPrice(Double.valueOf(txtPrice.getText()));
        item.setCategory(downCat.getValue());
        table1.refresh();
    }
    //btnSaveCat digunakan untuk menyimpan nama kategori item
    public void btnSaveCat(ActionEvent actionEvent) {
        Category category = new Category();
        category.setNameCat(txtCatName.getText().trim());
        if (category.getNameCat().equals("")){
            alert.setContentText("Please fill category name");
            alert.showAndWait();
        } else{
            boolean ada2 = false;
            for (Category kategori : categories){
                if(kategori.getNameCat().equals(category.getNameCat())){
                    ada2 = true;
                    alert.setContentText("Duplicate category name");
                    alert.showAndWait();
                }
            }
            if(ada2==false){
                categories.add(category);
                txtCatName.clear();
            }
        }
    }

    //tabClicked digunakan saat data pada tabel ingin di update
    @FXML
    private void tabClicked(MouseEvent mouseEvent) {
        Item item = table1.getSelectionModel().getSelectedItem();
        txtName.setText(item.getNama());
        txtPrice.setText(String.valueOf(item.getPrice()));
        downCat.setValue(item.getCategory());
        updateBtn.setDisable(false);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        items = FXCollections.observableArrayList();
        categories = FXCollections.observableArrayList();
        updateBtn.setDisable(true);
        downCat.setItems(categories);
        table1.setItems(items);
        col1.setCellValueFactory(data ->{
            Item item = data.getValue();
            return new SimpleStringProperty(item.getNama());
        });
        col2.setCellValueFactory(data->{
            Item item2 = data.getValue();
            return new SimpleStringProperty(String.valueOf(item2.getPrice()));
        });
        col3.setCellValueFactory(data->{
            Item item3 = data.getValue();
            return new SimpleStringProperty(item3.getCategory().getNameCat());
        });
    }
}
