import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.time.LocalDate;
import java.util.Optional;
import java.util.logging.Level;

public class Main extends Application {
    private TableView<BonusMember> table;
    private ObservableList<BonusMember> observableList = FXCollections.observableArrayList();
    private Personals tove = new Personals("Hansen", "Tove", "tove.hansen@dot.com", "tove");
    private Personals herman = new Personals("Herman", "Aagaard", "herman.aagaard@gmail.com", "herman");
    private Personals ole = new Personals("Ole", "Olsen", "ole.olsen@dot.com", "ole");
    private SilverMember toveMember = new SilverMember(167, tove, LocalDate.of(2008, 11, 23), 30000);
    private GoldMember hermanMember = new GoldMember(120, herman, LocalDate.of(2010, 8, 11), 78000);
    private BasicMember oleMember = new BasicMember(450, ole, LocalDate.of(2006, 2, 19));


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        createObservableList();
        showTable(stage);
    }

    private void showTable(Stage stage){
        Scene scene = new Scene(new Group());
        stage.setTitle("Bonusmembers");
        stage.setWidth(500);
        stage.setHeight(600);

        final Label label = new Label("Bonusmembers");
        label.setFont(new Font("Arial", 20));

        TableColumn<BonusMember, Integer> memberNoCol = new TableColumn<>("MemberNo");
        memberNoCol.setMinWidth(100);
        memberNoCol.setCellValueFactory(new PropertyValueFactory<>("memberNo"));
        TableColumn<BonusMember, Personals> nameCol = new TableColumn<>("Name");
        nameCol.setMinWidth(100);
        nameCol.setCellValueFactory(new PropertyValueFactory<>("personals"));
        TableColumn<BonusMember, LocalDate> enrolledDateCol = new TableColumn<>("Enrolled date");
        enrolledDateCol.setMinWidth(100);
        enrolledDateCol.setCellValueFactory(new PropertyValueFactory<>("enrolledDate"));
        TableColumn<BonusMember, Integer> pointsCol = new TableColumn<>("Points");
        pointsCol.setMinWidth(100);
        pointsCol.setCellValueFactory(new PropertyValueFactory<>("points"));

        table = new TableView<>();
        table.setEditable(true);
        table.setItems(getObservableList());
        table.getColumns().addAll(memberNoCol, nameCol, enrolledDateCol, pointsCol);

        VBox vbox = new VBox();
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(10, 0, 0, 70));

        Button addBtn = new Button();
        addBtn.setText("Add bonusmember");
        addBtn.setOnAction(e -> showAddBonusMember());

        Button removeBtn = new Button();
        removeBtn.setText("Remove bonusmember");
        removeBtn.setOnAction(e -> {
            try {
                removeMember(table.getSelectionModel().getSelectedItem(), stage);
            } catch (NullPointerException ne){
                LoggerLocator.logger.log(Level.SEVERE, ne.toString(), ne);
            }
        });

        Button updateMemberTypeBtn = new Button();
        updateMemberTypeBtn.setText("Update selected memberType");
        updateMemberTypeBtn.setOnAction(e -> {
            try {
                updateMemberTypes(stage, table.getSelectionModel().getSelectedItem());
            } catch (NullPointerException ne){
                LoggerLocator.logger.log(Level.SEVERE, ne.toString(), ne);
            }
        });
        Button infoBtn = new Button();
        infoBtn.setText("Show information");
        infoBtn.setOnAction(e -> {
            try {
                showInfo(table.getSelectionModel().getSelectedItem(), stage);
            } catch (NullPointerException ne){
                LoggerLocator.logger.log(Level.SEVERE, ne.toString(), ne);
            }
        });



        GridPane btnPane = new GridPane();
        btnPane.setVgap(10);
        btnPane.setHgap(10);
        btnPane.add(addBtn, 1, 1);
        btnPane.add(removeBtn, 2, 1);
        btnPane.add(updateMemberTypeBtn, 1, 2);
        btnPane.add(infoBtn, 2, 2);

        vbox.getChildren().addAll(label, table, btnPane);

        ((Group) scene.getRoot()).getChildren().addAll(vbox);

        stage.setScene(scene);
        stage.show();
    }

    private void createObservableList(){
        observableList.add(toveMember);
        observableList.add(hermanMember);
        observableList.add(oleMember);

    }
    private ObservableList<BonusMember> getObservableList(){
        return observableList;
    }

    /**
     * Throws exception if the user has not selected any bonusmember before pressing the show info button
     *
     * @param bonusMember object of BonusMember
     * @param parentStage the main stage started when application runs
     */
    private void showInfo(BonusMember bonusMember, Stage parentStage){
        if (bonusMember == null){
            throw new NullPointerException();
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.setTitle("More information");
        alert.setHeaderText(bonusMember.getPersonals().toString());

        int memberType = memberType(bonusMember);
        String message = bonusMember.getPoints() + " points and has been a member since " + bonusMember.getEnrolledDate();

        if (memberType == 0) {
            alert.setContentText(bonusMember.getPersonals().toString() + " is a basicmember and has " + message);
        }
        else if (memberType == 1){
            alert.setContentText(bonusMember.getPersonals().toString() + " is a silvermember and has " + message);
        }
        else if (memberType == 2){
            alert.setContentText(bonusMember.getPersonals().toString() + " is a goldmember and has " + message);
        }

        ButtonType okBtn = new ButtonType("Ok", ButtonBar.ButtonData.CANCEL_CLOSE);
        ButtonType addPointsBtn = new ButtonType("Add points");
        alert.getButtonTypes().setAll(addPointsBtn, okBtn);

        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == addPointsBtn){
            showAddPoints(bonusMember, parentStage);
        }
    }

    private int memberType(BonusMember bonusMember){
        int memberType = 0;

        if (bonusMember instanceof SilverMember){
            memberType = 1;
        }
        else if (bonusMember instanceof GoldMember){
            memberType = 2;
        }

        return memberType;
    }

    private void showAddPoints(BonusMember bonusMember, Stage parentStage){
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Add points");
        dialog.setHeaderText("Add points to " + bonusMember.getPersonals().toString());
        dialog.setContentText("Amount of points:");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            int points = Integer.parseInt(result.get());
            bonusMember.registerPoints(points);
            showTable(parentStage);
        }
    }

    private void showAddBonusMember(){
        Dialog dialog = new Dialog();
        dialog.setTitle("Add basic member");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        //Creates text inputs for Personals attributes
        TextField firstName = new TextField();
        firstName.setPromptText("Firstname");
        TextField surname = new TextField();
        surname.setPromptText("Surname");
        TextField ePostAdr = new TextField();
        ePostAdr.setPromptText("Email");
        PasswordField password = new PasswordField();
        password.setPromptText("Password");
        Label label = new Label("Fill all the fields");
        label.setVisible(false);

        Button btn = new Button("Add member");
        btn.setOnAction(e ->
        {
            if (firstName.getText().equals("") || surname.getText() == null || ePostAdr.getText() == null || password.getText() == null) {
                label.setVisible(true);
            }
            else{
                newMember(firstName, surname, ePostAdr, password);
                label.setVisible(false);
            }
        });


        grid.add(firstName, 0, 0);
        grid.add(surname, 1, 0);
        grid.add(ePostAdr, 0, 1);
        grid.add(password, 1, 1);
        grid.add(btn, 2, 0);
        grid.add(label, 0, 2);

        dialog.getDialogPane().setContent(grid);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        dialog.showAndWait();
    }

    private ObservableList<BonusMember> newMember(TextField firstName, TextField surname, TextField ePostAdr, PasswordField password){
        observableList.add(new BasicMember(MemberArchive.findAvailableMemberNo(), new Personals(firstName.getText(), surname.getText(), ePostAdr.getText(), password.getText()), LocalDate.now()));
        firstName.clear();
        surname.clear();
        ePostAdr.clear();
        password.clear();

        return observableList;
    }

    /**
     * Throws exception if the user has not selected any bonusmember before clicking remove button
     *
     * @param bonusMember object of BonusMember
     * @param parentStage the main stage started when application runs
     */
    private void removeMember(BonusMember bonusMember, Stage parentStage) {
        if (bonusMember.getPersonals() == null || bonusMember.getEnrolledDate() == null || bonusMember.getMemberNo() < 1){
            throw new NullPointerException();
        }
        for (int i = 0; i < observableList.size(); i++) {
            if (observableList.get(i).getPersonals().equals(bonusMember.getPersonals())){
                observableList.remove(i);
            }
        }
        showTable(parentStage);
    }

    /**
     * Throws exception if the user has not selected any bonusmember before clicking update selected bonusmember button
     *
     * @param parentStage the main stage staretd when application runs
     * @param bonusMember object of BonusMember
     */
    private void updateMemberTypes(Stage parentStage, BonusMember bonusMember){
        ObservableList observableList = getObservableList();
        for (int i = 0; i < observableList.size(); i++) {
            if (observableList.get(i) == bonusMember){
                if (bonusMember.getPoints() >= 25000 && bonusMember.getPoints() < 75000){
                    observableList.set(i, new SilverMember(bonusMember.getMemberNo(), bonusMember.getPersonals(), bonusMember.getEnrolledDate(), bonusMember.getPoints()));
                }
                else if (bonusMember.getPoints() >= 75000) {
                    observableList.set(i, new GoldMember(bonusMember.getMemberNo(), bonusMember.getPersonals(), bonusMember.getEnrolledDate(), bonusMember.getPoints()));
                }
            }
        }
        showTable(parentStage);
    }
}
