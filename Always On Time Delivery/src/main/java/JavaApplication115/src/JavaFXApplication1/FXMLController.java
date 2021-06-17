/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Window;
;

/**
 * FXML Controller class
 *
 * @author Linzan Liu
 */
public class FXMLController implements Initializable {
     
    @FXML private Button EnterBtn;
    @FXML private Button RunBtn;
    @FXML private Button ClearBtn;
    @FXML private Button OpenAFile;
    
    @FXML private ComboBox comb;
    @FXML private Label ComboLabel;
    
    @FXML private Label TipLabel;
    @FXML private TextField NumOfCustomer;
    @FXML private TextField MaxCapacity;
    @FXML private TextField DepotX;
    @FXML private TextField DepotY;
    @FXML private TextField DemandSizeOfDepot;
    @FXML private TextArea InformationOfCustomer;
    
    private ArrayList<String> PathOfFile = new ArrayList<>();
    
    
    @FXML
    void Enter(ActionEvent event) throws IOException  {
           
        try{

            File writename = new File("C:\\Users\\Linzan Liu\\Desktop\\Information Of Delivery.txt");
             writename.createNewFile(); 
             BufferedWriter out = new BufferedWriter(new FileWriter(writename)); 
             out.write(NumOfCustomer.getText()+" "+ MaxCapacity.getText()+"\r\n" +DepotX.getText()+" "+
                     DepotY.getText()+" "+DemandSizeOfDepot.getText()+"\r\n"+InformationOfCustomer.getText()); 
            
             out.flush(); 
             out.close(); 
             
       }
        catch (IOException e) {
            System.out.print("Exception");
        }
        
       
    }
    
    @FXML
    void Clear(ActionEvent event) throws IOException  {
        
        NumOfCustomer.clear();
        MaxCapacity.clear();
        DepotX.clear();
        DepotY.clear();
        InformationOfCustomer.clear();
    }
    
    @FXML
    void Select(ActionEvent event) throws IOException {
       
        
        this.ComboLabel.setText(comb.getValue().toString()+" "+"is selected!");
        
        //Here is call an algorithm
        
  
    }
    
    
    @FXML
    void OpenAFile(ActionEvent event) throws IOException  {
        
        Window window = ((Node) (event.getSource())).getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"), new ExtensionFilter("All Files", "*.*"));
        File file = fileChooser.showOpenDialog(window);
        event.consume();
                if (file != null) {
                //System.out.println(file.getAbsolutePath());
                PathOfFile.add(file.getAbsolutePath());//Put path of file into an arraylist.
                     System.out.println(PathOfFile);
                  }
             
             }
         
    
    @FXML
    void RunProgram(ActionEvent event) throws IOException  {
        
            //Click the button, page jumping
       
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
        final Tooltip tooltipCapacity = new Tooltip();
        tooltipCapacity.setText("No more than 10!");
        MaxCapacity.setTooltip(tooltipCapacity);
        
        final Tooltip tooltipCustomer = new Tooltip();
        tooltipCustomer.setText("Please input the x coordinate, y coordinate and demand size of customers respectively!");
        InformationOfCustomer.setTooltip(tooltipCustomer);
        
        comb.getItems().addAll("Basic simulation","Greedy simulation","MCTS simulation"); 
    }    

    public Button getEnterBtn() {
        return EnterBtn;
    }

    public void setEnterBtn(Button EnterBtn) {
        this.EnterBtn = EnterBtn;
    }

    public Button getRunBtn() {
        return RunBtn;
    }

    public void setRunBtn(Button RunBtn) {
        this.RunBtn = RunBtn;
    }

    public Button getClearBtn() {
        return ClearBtn;
    }

    public void setClearBtn(Button ClearBtn) {
        this.ClearBtn = ClearBtn;
    }

    public Button getOpenAFile() {
        return OpenAFile;
    }

    public void setOpenAFile(Button OpenAFile) {
        this.OpenAFile = OpenAFile;
    }

    public ComboBox getComb() {
        return comb;
    }

    public void setComb(ComboBox comb) {
        this.comb = comb;
    }

    public Label getComboLabel() {
        return ComboLabel;
    }

    public void setComboLabel(Label ComboLabel) {
        this.ComboLabel = ComboLabel;
    }

    public Label getTipLabel() {
        return TipLabel;
    }

    public void setTipLabel(Label TipLabel) {
        this.TipLabel = TipLabel;
    }

    public TextField getNumOfCustomer() {
        return NumOfCustomer;
    }

    public void setNumOfCustomer(TextField NumOfCustomer) {
        this.NumOfCustomer = NumOfCustomer;
    }

    public TextField getMaxCapacity() {
        return MaxCapacity;
    }

    public void setMaxCapacity(TextField MaxCapacity) {
        this.MaxCapacity = MaxCapacity;
    }

    public TextField getDepotX() {
        return DepotX;
    }

    public void setDepotX(TextField DepotX) {
        this.DepotX = DepotX;
    }

    public TextField getDepotY() {
        return DepotY;
    }

    public void setDepotY(TextField DepotY) {
        this.DepotY = DepotY;
    }

    public TextField getDemandSizeOfDepot() {
        return DemandSizeOfDepot;
    }

    public void setDemandSizeOfDepot(TextField DemandSizeOfDepot) {
        this.DemandSizeOfDepot = DemandSizeOfDepot;
    }

    public TextArea getInformationOfCustomer() {
        return InformationOfCustomer;
    }

    public void setInformationOfCustomer(TextArea InformationOfCustomer) {
        this.InformationOfCustomer = InformationOfCustomer;
    }

    public ArrayList<String> getPathOfFile() {
        return PathOfFile;
    }

    public void setPathOfFile(ArrayList<String> PathOfFile) {
        this.PathOfFile = PathOfFile;
    }
    
}

