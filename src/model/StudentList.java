package model;
import java.util.*;
import ManageStudent.*;

public class StudentList {
   StudentController control = null;
   private HashMap<String,Student> students = new HashMap<String,Student>();
   public StudentList()
   {	  put("S1",new Student("S1","Ravi Subra",70,80));
	  put("S2",new Student("S2","Tony Blair",90,88));
	  put("S3",new Student("S3","Steven Tham",30,20));
	  put("S4",new Student("S4","Richard Cooper",40,70));
	  put("S5",new Student("S5","Joanne Bill",56,63));
	  put("S6",new Student("S6","Mel Tim",50,84));
	  put("S7",new Student("S7","Chris Thom",90,70));
	  put("S8",new Student("S8","Chandra Nehru",10,30));	  
	  put("S9",new Student("S9","Charles Henry",20,30));
	  put("S10",new Student("S10","John Nathan",30,40));
	  put("S11",new Student("S11","Jack Ryan",83,20));
	  put("S12",new Student("S12","Peter Finney",50,60));	   
   }
   public void put(String ID, Student s)
   {   students.put(ID,s);
	   if (control != null)
		   control.update();
   }
   public int getExamCount(int min, int max)
   {  return (int) students.values().stream()
      .filter(s->s.getExam()>=min && s.getExam()<=max)
      .count();
   }
   public int getOverallCount(int min, int max)
   {  return (int) students.values().stream()
      .filter(s->s.getOverall()>=min && s.getOverall()<=max)
      .count();
   }
   public Student get(String ID)
   {   return students.get(ID);
   }	
   public static void main(String args[])
   {   StudentList sl = new StudentList();
	   System.out.println(sl.getExamCount(0,50));
	   System.out.println(sl.getOverallCount(0,50));   
   }
   public void setController(StudentController control) // A handle to the controller
   {	   this.control = control;
   }
}