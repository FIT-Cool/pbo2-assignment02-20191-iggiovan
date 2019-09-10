package com.ignatius.controller;

import com.ignatius.entity.Category;
import com.ignatius.entity.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class tokoController implements Initializable {
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtPrice;
    @FXML
    private TextField txtCatName;
    @FXML
    private TableView table1;
    @FXML
    private TableColumn col1;
    @FXML
    private TableColumn col3;
    @FXML
    private TableColumn col2;
    @FXML
    private ComboBox downCat;
    @FXML
    private Button saveBtn;
    @FXML
    private Button resetBtn;
    @FXML
    private Button updateBtn;
    private String ini;
    private ObservableList<Item> items;
    private ObservableList<Category> categories;
    public void btnSave(ActionEvent actionEvent) {
        Item item = new Item();
        item.setNama(txtName.getText());
        item.setPrice(Double.valueOf(txtPrice.getText()));
//        item.setCategory(categories.);
//        item.setCategory(downCat.);
        items.add(item);
    }

    public void btnReset(ActionEvent actionEvent) {
    }

    public void btnUpdate(ActionEvent actionEvent) {
    }

    public void btnSaveCat(ActionEvent actionEvent) {
        Category cat = new Category();
        cat.setNameCat(txtCatName.getText().trim());
        categories.add(cat);
//        downCat.
    }

    @FXML
    private void tabClicked(MouseEvent mouseEvent) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        categories = FXCollections.observableArrayList();
        downCat.setItems(categories);


    }
}
