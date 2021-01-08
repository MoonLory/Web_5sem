package controller;

import java.util.ArrayList;

import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.beans.property.ReadOnlyDoubleWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.Appliances;
import model.factory.ConditionerFactory;
import model.factory.Factory;
import model.factory.FridgeFactory;
import model.factory.BakeFactory;
import model.factory.HairdryerFactory;
import model.factory.DishwasherFactory;
import model.factory.VacuumCleanerFactory;
import model.manager.Manager;

/**
 * Basic class demonstrates functions of classes
 *
 * @author Andrei Grishkin
 * @version 1.2
 */
public class Controller {

	@FXML
    private TableView<Appliances> tableView = new TableView<>();
	@FXML
	private TableColumn<Appliances, String> nameColumn = new TableColumn<>();
	@FXML
	private TableColumn<Appliances, Number> powerColumn = new TableColumn<>();
	@FXML
	private TableColumn<Appliances, Boolean> selectedColumn = new TableColumn<>();
	@FXML
	private TextArea appliancesDescriptionTextArea;
	@FXML
	private ChoiceBox selectedStatusBox;
	@FXML
	private ChoiceBox factoryBox;
	@FXML
	private Button selectPowerbutton;
	@FXML
	private Button selectSelectedButton;
	@FXML
	private Button resetButton;
	@FXML
	private Button produceButton;
	@FXML
	private TextField minPowertextField;
	@FXML
	private TextField maxPowertextField;

	private ArrayList<Appliances> appliances = new ArrayList<>();
	private ObservableList<String> selectedStatusBoxList = FXCollections.observableArrayList(
			"Connected","Disconnected");
	private ObservableList<String> factoryBoxList = FXCollections.observableArrayList(
			"Conditioner","Fridge","Bake","Hairdryer","Dishwasher","VacuumCleaner");
	Manager manager = new Manager();
	Factory df = new ConditionerFactory();
	Factory ff = new FridgeFactory();
	Factory irf= new BakeFactory();
	Factory pf = new HairdryerFactory();
	Factory sf = new DishwasherFactory();
	Factory vcf = new VacuumCleanerFactory();

	@FXML
	private void initialize() {
		selectedStatusBox.setValue("Connected");
		selectedStatusBox.setItems(selectedStatusBoxList);
		factoryBox.setValue("Conditioner");
		factoryBox.setItems(factoryBoxList);
		addTestData();
		configureTableViewWithColumns();
	}

	private void addTestData() {
		appliances.add(df.createAppliances());
		appliances.add(ff.createAppliances());
		appliances.add(irf.createAppliances());
		appliances.add(pf.createAppliances());
		appliances.add(sf.createAppliances());
		appliances.add(vcf.createAppliances());
	}

	private void configureTableViewWithColumns() {
		tableView.refresh();

		nameColumn.setCellValueFactory(cellData ->
			new ReadOnlyStringWrapper(cellData.getValue().getName()));
		powerColumn.setCellValueFactory(cellData ->
		new ReadOnlyDoubleWrapper(cellData.getValue().getPower()));
		selectedColumn.setCellValueFactory(cellData ->
		new ReadOnlyBooleanWrapper(cellData.getValue().getSocket()));
		tableView.setItems(FXCollections.observableArrayList(appliances));
		tableView.setEditable(false);
		tableView.setOnMouseClicked(click->{
			if(click == null)
				return;
			if(click.getClickCount()==1){
				appliancesDescriptionTextArea.setText(tableView.getSelectionModel().getSelectedItem().toString());
			}
		});
	}

	/**
     * handling tap drlrvt by price button
     * @param actionEvent some event
     */
	@FXML
    public void onSelectByPowerTapped(ActionEvent actionEvent) {
        String minPower = minPowertextField.getText();
        String maxPower = maxPowertextField.getText();
        if (maxPower.isEmpty() || minPower.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Enter power range", ButtonType.OK);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.OK) {
                alert.hide();
            }
        } else {
            System.out.println("Min" + minPower);
            System.out.println("Max" + maxPower);
            ArrayList<Appliances> appliancesList = new ArrayList<>();
            for (Appliances appliances : tableView.getItems()) {
				appliancesList.add(appliances);
			}
            tableView.getItems().clear();
            tableView.getItems().addAll(manager.findAppliancesPower(Double.valueOf(minPower), Double.valueOf(maxPower), appliancesList));
        }
    }

	@FXML
    public void onSelectBySelectedTapped(ActionEvent actionEvent) {
		String type = selectedStatusBox.getValue().toString();
		ArrayList<Appliances> appliancesList = new ArrayList<>();
        for (Appliances appliances : tableView.getItems()) {
			appliancesList.add(appliances);
		}
        tableView.getItems().clear();
        tableView.getItems().addAll(manager.findAppliancesSocket(type=="Connected"? true:false, appliancesList));
    }

	@FXML
    public void onProduceTapped(ActionEvent actionEvent) {
    	String type = factoryBox.getValue().toString();
    	switch (type) {
		case "Conditioner":
			appliances.add(df.createAppliances());
			tableView.getItems().add(df.createAppliances());
			break;
		case "Fridge":
			appliances.add(ff.createAppliances());
			tableView.getItems().add(ff.createAppliances());
			break;
		case "Bake":
			appliances.add(irf.createAppliances());
			tableView.getItems().add(irf.createAppliances());
			break;
		case "Hairdryer":
			appliances.add(pf.createAppliances());
			tableView.getItems().add(pf.createAppliances());
			break;
		case "Dishwasher":
			appliances.add(sf.createAppliances());
			tableView.getItems().add(sf.createAppliances());
			break;
		case "VacuumCleaner":
			appliances.add(vcf.createAppliances());
			tableView.getItems().add(vcf.createAppliances());
			break;
		default:
			break;
		}
    }

    /**
     * handling tap reset button
     * @param actionEvent some event
     */
    public void onResetButtonTapped(ActionEvent actionEvent) {
        tableView.getItems().clear();
        minPowertextField.setText(null);
        maxPowertextField.setText(null);
        tableView.getItems().addAll(appliances);
    }

}