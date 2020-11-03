package ManageStudent;

import java.util.Map;
import java.util.TreeMap;

import model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.*;

public class StudentController {
	private StudentList sl;

    @FXML private BarChart<?, ?> bc1;
    @FXML private CategoryAxis xaxis1;
    @FXML private NumberAxis yaxis1;
    @FXML private BarChart<?, ?> bc2;
    @FXML private CategoryAxis xaxis2;
    @FXML private NumberAxis yaxis2;
    @FXML private TextField txtID;
    @FXML private TextField txtName;
    @FXML private TextField txtCW;
    @FXML private TextField txtExam;
    @FXML private Label labOverall;

    @FXML
    public void addStudent(ActionEvent evt)
    {  String ID = txtID.getText();
    	   if (sl.get(ID) != null && ((Button)evt.getSource()).getText().compareTo("Add Student")== 0)
    	   {		Alert alert = new Alert(AlertType.ERROR);
    		    alert.setTitle("Error Dialog");
    		    alert.setHeaderText("Student with this ID already exist");
    		    alert.setContentText("To change existing student details choose Alter");
    		    alert.showAndWait();
    	   }
    	   else if (sl.get(ID) == null && ((Button)evt.getSource()).getText().compareTo("Alter Student")== 0)
    	   {    Alert alert = new Alert(AlertType.ERROR);
    		    alert.setTitle("Error Dialog");
    		    alert.setHeaderText("You can only alter an existing record");
    		    alert.setContentText("To add a new student choose Add");
    		    alert.showAndWait();
    	   }
    	   else {
    	      String name = txtName.getText();
    	      try {
      	     int cw = Integer.parseInt(txtCW.getText());
    	         int exam = Integer.parseInt(txtExam.getText());
    	         sl.put(ID,new Student(ID,name,cw,exam));
    	      }
    	      catch(NumberFormatException nfe)
    	      {	    Alert alert = new Alert(AlertType.ERROR);
    	    		    alert.setTitle("Exception Dialog");
    	    		    alert.setHeaderText("Invalid Type");
    	    		    alert.setContentText("Coursework and Exam marks must be integers");
    	    		    alert.showAndWait();
    	      }
    	   }
    }


    @FXML
    public void getStudent(ActionEvent evt)
    {
    	   String ID = txtID.getText();
    	   Student student = sl.get(ID);
    	   if (student == null)
    	   {
    		   Alert alert = new Alert(AlertType.ERROR);
    		   alert.setTitle("Error Dialog");
    		   alert.setHeaderText("Student with this ID does not exist");
    		   alert.setContentText("Enter an existing ID");
    		   alert.showAndWait();
    	   }
    	   else {
           txtName.setText(student.getName());
           txtCW.setText(" " + student.getCW());
           txtExam.setText(" " + student.getExam());
    	   }
    }

	@FXML
	public void initialize() {

           sl = new StudentList();
           sl.setController(this);
	       xaxis1.setLabel("Exam Mark Distribution");
	       yaxis1.setLabel("Number of students");
	       xaxis2.setLabel("Overall Mark Distribution");
	       yaxis2.setLabel("Number of students");
           update();
	}

	public void update()
	{	   Map<String,Integer> series1 = new TreeMap<String,Integer>();
	       series1.put("0-49",sl.getExamCount(0,49));
	       series1.put("50-59",sl.getExamCount(50,59));
	       series1.put("60-69",sl.getExamCount(60,69));
	       series1.put("70-79",sl.getExamCount(70,79));
	       series1.put("80-100",sl.getExamCount(80,100));

	       Map<String,Integer> series2 = new TreeMap<String,Integer>();
	       series2.put("0-49",sl.getOverallCount(0,49));
	       series2.put("50-59",sl.getOverallCount(50,59));
	       series2.put("60-69",sl.getOverallCount(60,69));
	       series2.put("70-79",sl.getOverallCount(70,79));
	       series2.put("80-100",sl.getOverallCount(80,100));
	       XYChart.Series dataSeries1  = new XYChart.Series();


	       dataSeries1.setName("PF 2020 sem 2 Charles Theva");
	       for (String c : series1.keySet() )
	          dataSeries1.getData().add(new XYChart.Data(c, series1.get(c)));
	       bc1.getData().clear();
	       bc1.getData().add(dataSeries1);

	       XYChart.Series dataSeries2 =  new XYChart.Series();
	       dataSeries2.setName("PF 2020 sem 2 Charles Theva");
	       for (String c : series2.keySet() )
	          dataSeries2.getData().add(new XYChart.Data(c, series2.get(c)));
	       bc2.getData().clear();
	       bc2.getData().add(dataSeries2);
	}
}
