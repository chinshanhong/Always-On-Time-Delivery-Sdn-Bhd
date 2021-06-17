//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package LinZanPart;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import JavaApplication115.NewFXMain;
import ShanHongPart.GUI_BFS;
import ShanHongPart.GUI_GREEDY;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import javafx.stage.FileChooser.ExtensionFilter;

public class FXMLController implements Initializable {
    @FXML
    private Button EnterBtn;
    @FXML
    private Button RunBtn;
    @FXML
    private Button ClearBtn;
    @FXML
    private Button OpenAFile;
    @FXML
    private ComboBox comb;
    @FXML
    private Label ComboLabel;
    @FXML
    private Label TipLabel;
    @FXML
    private TextField NumOfCustomer;
    @FXML
    private TextField MaxCapacity;
    @FXML
    private TextField DepotX;
    @FXML
    private TextField DepotY;
    @FXML
    private TextField DemandSizeOfDepot;
    @FXML
    private TextArea InformationOfCustomer;
    private ArrayList<String> PathOfFile = new ArrayList();


    public FXMLController() {
    }

    @FXML
    void Enter(ActionEvent event) throws IOException {
        try {
            File writename = new File("C:\\Users\\15-P248TX\\Desktop\\Information Of Delivery.txt");
            writename.createNewFile();
            BufferedWriter out = new BufferedWriter(new FileWriter(writename));
            out.write(this.NumOfCustomer.getText() + " " + this.MaxCapacity.getText() + "\r\n" + this.DepotX.getText() + " " + this.DepotY.getText() + " " + this.DemandSizeOfDepot.getText() + "\r\n" + this.InformationOfCustomer.getText() + "\r\n");
            this.PathOfFile.add("C:\\Users\\15-P248TX\\Desktop\\Information Of Delivery.txt");
            out.flush();
            out.close();
        } catch (IOException var4) {
            System.out.print("Exception");
        }

    }

    @FXML
    void Clear(ActionEvent event) throws IOException {
        this.NumOfCustomer.clear();
        this.MaxCapacity.clear();
        this.DepotX.clear();
        this.DepotY.clear();
        this.InformationOfCustomer.clear();
    }

    @FXML
    void Select(ActionEvent event) throws IOException {
        this.ComboLabel.setText(this.comb.getValue().toString() + " is selected!");
    }

    @FXML
    void OpenAFile(ActionEvent event) throws IOException {
        Window window = ((Node)((Node)event.getSource())).getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new ExtensionFilter[]{new ExtensionFilter("Text Files", new String[]{"*.txt"}), new ExtensionFilter("All Files", new String[]{"*.*"})});
        File file = fileChooser.showOpenDialog(window);
        event.consume();
        if (file != null) {
            this.PathOfFile.add(file.getAbsolutePath());
            System.out.println(this.PathOfFile);
        }

    }

    @FXML
    void RunProgram(ActionEvent event) throws IOException, InterruptedException {
        if(this.comb.getValue().toString().equals("Basic simulation")) {
            try{
                GUI_BFS BFSGui = new GUI_BFS();
                BFSGui.setFilePath(this.PathOfFile.get(PathOfFile.size() - 1));
                BFSGui.display();
            }catch(Exception e){
                System.out.println("Please press enter");
            }
        }
        else if(this.comb.getValue().toString().equals("Greedy simulation")){
            try{
                GUI_GREEDY GreedyGui = new GUI_GREEDY();
                GreedyGui.setFilePath(this.PathOfFile.get(PathOfFile.size() - 1));
                GreedyGui.display();
            }catch(Exception e){
                System.out.println("Please press enter");
            }
        }
        else if(this.comb.getValue().toString().equals("MCTS simulation")){
            try{
                NewFXMain MCTSGUI = new NewFXMain();
                MCTSGUI.setFilepathupdated(this.PathOfFile.get(PathOfFile.size() - 1));
                MCTSGUI.display();
            }catch(Exception e){
                System.out.println("Please press enter");
            }
        }
    }

    public void initialize(URL url, ResourceBundle rb) {
        Tooltip tooltipCapacity = new Tooltip();
        tooltipCapacity.setText("No more than 10!");
        this.MaxCapacity.setTooltip(tooltipCapacity);
        Tooltip tooltipCustomer = new Tooltip();
        tooltipCustomer.setText("Please input the x coordinate, y coordinate and demand size of customers respectively!");
        this.InformationOfCustomer.setTooltip(tooltipCustomer);
        this.comb.getItems().addAll(new Object[]{"Basic simulation", "Greedy simulation", "MCTS simulation"});
    }

    public Button getEnterBtn() {
        return this.EnterBtn;
    }

    public void setEnterBtn(Button EnterBtn) {
        this.EnterBtn = EnterBtn;
    }

    public Button getRunBtn() {
        return this.RunBtn;
    }

    public void setRunBtn(Button RunBtn) {
        this.RunBtn = RunBtn;
    }

    public Button getClearBtn() {
        return this.ClearBtn;
    }

    public void setClearBtn(Button ClearBtn) {
        this.ClearBtn = ClearBtn;
    }

    public Button getOpenAFile() {
        return this.OpenAFile;
    }

    public void setOpenAFile(Button OpenAFile) {
        this.OpenAFile = OpenAFile;
    }

    public ComboBox getComb() {
        return this.comb;
    }

    public void setComb(ComboBox comb) {
        this.comb = comb;
    }

    public Label getComboLabel() {
        return this.ComboLabel;
    }

    public void setComboLabel(Label ComboLabel) {
        this.ComboLabel = ComboLabel;
    }

    public Label getTipLabel() {
        return this.TipLabel;
    }

    public void setTipLabel(Label TipLabel) {
        this.TipLabel = TipLabel;
    }

    public TextField getNumOfCustomer() {
        return this.NumOfCustomer;
    }

    public void setNumOfCustomer(TextField NumOfCustomer) {
        this.NumOfCustomer = NumOfCustomer;
    }

    public TextField getMaxCapacity() {
        return this.MaxCapacity;
    }

    public void setMaxCapacity(TextField MaxCapacity) {
        this.MaxCapacity = MaxCapacity;
    }

    public TextField getDepotX() {
        return this.DepotX;
    }

    public void setDepotX(TextField DepotX) {
        this.DepotX = DepotX;
    }

    public TextField getDepotY() {
        return this.DepotY;
    }

    public void setDepotY(TextField DepotY) {
        this.DepotY = DepotY;
    }

    public TextField getDemandSizeOfDepot() {
        return this.DemandSizeOfDepot;
    }

    public void setDemandSizeOfDepot(TextField DemandSizeOfDepot) {
        this.DemandSizeOfDepot = DemandSizeOfDepot;
    }

    public TextArea getInformationOfCustomer() {
        return this.InformationOfCustomer;
    }

    public void setInformationOfCustomer(TextArea InformationOfCustomer) {
        this.InformationOfCustomer = InformationOfCustomer;
    }

    public ArrayList<String> getPathOfFile() {
        return this.PathOfFile;
    }

    public void setPathOfFile(ArrayList<String> PathOfFile) {
        this.PathOfFile = PathOfFile;
    }
}
