package com.xwintop.xJavaFxTool.controller.debugTools.redisTool;

import com.xwintop.xJavaFxTool.utils.JavaFxViewUtil;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RedisToolDialogController implements Initializable
{
    @FXML
    private TextField keyTextField;
    @FXML
    private TableView dialogTableView;
    @FXML
    private TableColumn dialogKeyTableColumn;
    @FXML
    private TableColumn dialogValueTableColumn;
    @FXML
    private Button enterButton;
    @FXML
    private Button cancelButton;

    private ObservableList<Map<String, String>> dialogTableData = FXCollections.observableArrayList();
    private boolean isEnter = false;

    private Alert alert = null;

    public static FXMLLoader getFXMLLoader() {
        URL url = Object.class.getResource("/fxml/debugTools/redisTool/RedisToolDialog.fxml");
        FXMLLoader fXMLLoader = new FXMLLoader(url);
        return fXMLLoader;
    }

    public static RedisToolDialogController getRedisToolDialogController(String title,boolean type){
        FXMLLoader fXMLLoader = RedisToolDialogController.getFXMLLoader();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        try
        {
            alert.setGraphic(fXMLLoader.load());
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        RedisToolDialogController redisToolDialogController = fXMLLoader.getController();
        redisToolDialogController.setDialogType(type);
        redisToolDialogController.setAlert(alert);
        return redisToolDialogController;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initView();
        initEvent();
        initService();
    }
    private void initView() {
        JavaFxViewUtil.setTableColumnMapValueFactory(dialogKeyTableColumn, "key");
        JavaFxViewUtil.setTableColumnMapValueFactory(dialogValueTableColumn, "value");
        dialogTableView.setItems(dialogTableData);
    }
    private void initEvent() {}
    private void initService() {}

    public void setDialogType(boolean type){
        if(type){
            dialogTableView.getColumns().remove(dialogKeyTableColumn);
        }
    }
    @FXML
    private void enterAction(ActionEvent event){
        isEnter = true;
        Platform.runLater(()->{
            alert.close();
        });
    }

    @FXML
    private void cancelAction(ActionEvent event){
        Platform.runLater(()->{
            alert.close();
        });
    }
}