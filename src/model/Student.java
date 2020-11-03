package model;
public class Student {
   private String ID;
   private String name;
   private int cw;
   private int exam;
   private int overall;
   public Student(String ID, String name, int cw, int exam)
   {	   this.ID = ID;
	   this.name = name;
	   this.cw = cw;
	   this.exam = exam;
   }
   public int computeOverall()
   {   overall = (cw + exam)/2;
	   return overall;
   }
   public String getID()
   {   return ID;
   }
   public String getName()
   {   return name;
   }
   public double getCW()
   {   return cw;
   }   
   public double getExam()
   {   return exam;
   } 
   public double getOverall()
   {   return computeOverall();
   } 
}
