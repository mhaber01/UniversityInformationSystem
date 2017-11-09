# UniversityInformationSystem
Java program utilizing an abstract class, interface, and public classes

public abstract class Person 
{
    private String id;
    private String name;
    private String address;
    private String phone;
    
    public Person(String id, String name, String address, String phone)
    {
        this.id=id;
        this.name=name;
        this.address=address;
        this.phone=phone;
    }
    
    public String getId()
    {
        return id;
    }
    public String getName()         
    {
        return name;
    }
    public String getAddress()
    {
        return address;
    }
    public void setAddress(String address)
    {
        this.address=address;
    }
    public String getPhone()
    {
        return phone;
    }
    public void setPhone(String phone)
    {
        this.phone=phone;
    }
    
    @Override
    public String toString()
    {
        return("ID #" + getId()+", " + getName()+ ", "+ getAddress()+", " + getPhone());
    }
    
}

public interface Employed
{
	public boolean isEmployed();
  
  	public void setRate(double rate);
  
  	public double getRate();
  	
  	public void setPosition(String position);

	public String getPosition(); 
}

public class Student extends Person implements Employed, Comparable<Student> 
{
    private String school;
    private String major;
    private String minor;
    private boolean resident;
    
    private int year;
    private int gradYear;
    
    private boolean goodStanding;
    private double gpa;
    private int[] grades;
    private String[] courses;
    private int numCourses;
    private final int MAX_COURSES = 50;

    private boolean employed;
    private double rate;
    private String position;
    
    public Student(String id, String name, String address, String phone, String school, boolean resident, int year)
    {
        super(id, name, address, phone);
        this.school=school;
        this.resident=resident;
        this.year=year;
        gradYear=4;
        numCourses=0;
        grades = new int[MAX_COURSES];
        courses= new String[MAX_COURSES];
        major="Undecided";
        minor=null;
        gpa=0;
        goodStanding=true;
        employed=false;
        rate = 0.0;
        position = null;
    }
    
    public String getSchool()
    {
        return school;
    }
   
    public void setSchool(String school)
    {
        this.school=school;
    }
    
    public String getMajor()
    {
        return major;
    }
    
    public void setMajor(String major)
    {
        this.major=major;
    }
    
    public String getMinor()
    {
        return minor;
    }
    
    public void setMinor(String minor)
    {
        this.minor=minor;
    }
    
    public double getGpa()
    {
        return gpa;
    }
    
    public void setGpa()
    {
        double sum=0.0;
        for(int i=0; i<numCourses; i++)
        {
            sum+=grades[i];
        }
        gpa=sum/numCourses;
        
        if(gpa<65)
            goodStanding=false;
        else
            goodStanding=true;
    }
    
    public int getYear()
    {
       return year;
    }
    
    public int getGradYear()
    {
        return gradYear;
    }
    
    public void setGradYear(int gradYear)
    {
        this.gradYear=gradYear;
    }
    
    public void newHire(String position, double rate)
    {
         employed = true;
         this.position = position;
         this.rate=rate;
    }

    public void endEmployment()
    {
        employed = false;
        position = null;
        rate = 0.0;
     }

    public void newSemester()
    {
        setGpa();
        year++;
        
        if(!goodStanding)
        {
            System.out.println("Warning! Student has failed to meet requirements for good academic standing. Please see advisor.");
            gradYear++;
            System.out.println("Your eligibility for employment and graduation may be effected by this.");
        }

        if(goodStanding && isEmployed())
       {
            rate+=1.5;
       }
        
        if(year==gradYear)
            System.out.println("Congratulations! You are eligible for graduation! :)");
        
    }
    
    public void addCourse(String course)
    {
        courses[numCourses] = course;
        numCourses++;
    }
    
    public void dropCourse(String course)
    {
        for(int i=0; i<numCourses; i++)
        {
            if(courses[i].equals(course))
            {
                courses[i]=null;
                for(int j=i+1; j<numCourses; j++)
                {
                    courses[j]=courses[j-1];
                }
            }
        }
        numCourses--;
    }
    
    public void updateGrade(String course, int grade)
    {
        for(int i=0; i<numCourses; i++)
        {
            if(courses[i].equals(course))
            {
                grades[i]=grade;
            }
        }
    }
    
    public void displayReportCard()
    {
        System.out.println(toString());
        
        System.out.println("Student Grades: ");
        for(int i=0;i<numCourses; i++)
        {
            System.out.println(courses[i]+": " + grades[i]);
        }
        System.out.println("GPA: "+ getGpa());
        
        if(goodStanding)
            System.out.println("Student is in good academic standing.");
        else
            System.out.println("Student has failed to meet good academic standing.");
        
    }
    
    @Override
    public boolean isEmployed()
    {
        return employed;
    }
  
    @Override
    public void setRate(double rate)
    {
        this.rate=rate;
     }
  
     @Override
      public double getRate()
      {
         return rate;
      }
  	
   @Override
    public void setPosition(String position){
        this.position = position;
    }
    
    @Override
    public String getPosition(){
        return position;
    } 

    @Override
    public int compareTo(Student s)
    {
        if(this.gpa>s.gpa)
        {
            return 1;
        }
        else if(this.gpa<s.gpa)
        {
            return -1;
        }
        else
            return 0;
    }

    @Override
    public String toString()
    {
        return "Student: " + super.toString() + "\n" + "School: " + getSchool()+ "\nMajor: " + getMajor() + "\nMinor: " + getMinor() + "\nYear: " + getYear() + "\nWill graduate after " + getGradYear() + " years total";
    }

    
}

public class Faculty extends Person implements Employed {
    
    private String department;
    private String officeNum;
    private String position;
    private String[] courses;
    private final int MAX_COURSES = 5;
    private int numCourses;
    private double rate;
    
    Faculty(String id, String name, String address, String phone, String department, String officeNum, String position){
        super(id, name, address, phone);
        this.department = department;
        this.officeNum = officeNum;
        this.position = position;
        courses = new String[MAX_COURSES];
        numCourses = 0;
    }
    
    public String getDepartment(){
        return department;
    }
    
    public void setOfficeNum(String officeNum){
        this.officeNum = officeNum;
    }
    
    public String getOfficeNum(){
        return officeNum;
    }

    public void addCourse(String course)
    {
        courses[numCourses] = course;
        numCourses++;
    }
    
    public void newSemester(String course){
        for(int i = 0; i < numCourses; i++){
            courses[i] = null;
        }
        numCourses = 0;
    }
    
    public void displayCourses(){
        System.out.println("Courses: ");
        for(int i = 0; i < numCourses; i++){
            System.out.println(courses[i] + " ");
        }
    }
    
    @Override
    public void setPosition(String position){
        this.position = position;
    }
    
    @Override
    public String getPosition(){
        return position;
    }
    
    @Override
    public boolean isEmployed()
    {
          return true;
    }  
  
    @Override	
    public void setRate(double rate)
    {
        this.rate=rate;
    }

    @Override
    public double getRate()
    {
        return rate;
    }
    
    @Override
    public String toString(){
        return "Faculty Member: " + super.toString() + "\nDepartment: " + getDepartment() + "\nOffice #" + getOfficeNum() + "\nPosition: " + getPosition();
    }
    
}

public class TestUniversity {
    public static void main(String[] args){
        
        Student s1 = new Student("000441485", "Megan Haber", "3 Abate Court", "991-4737", "Science", true, 2);
        s1.addCourse("CMPT 360");
        s1.addCourse("CMPT 241");
        s1.addCourse("PHYS 102");
        s1.dropCourse("PHYS 102");
        s1.setMajor("Computer Science");
        s1.setMinor("Management");
        s1.updateGrade("CMPT 360", 92);
        s1.updateGrade("CMPT 241", 92);
        s1.setGpa();
        s1.displayReportCard();
        s1.newHire("Client Services", 100);
        System.out.println("Position: " + s1.getPosition());
        System.out.println("Pay Rate: " + s1.getRate());
        s1.newSemester();
        System.out.println("Pay Rate: " + s1.getRate());
        
        Student s2 = new Student("000443458", "Samantha Morrison", "120 Manhattan College Parkway", "873-7870", "Science", true, 1);
        s2.addCourse("CMPT 360");
        s2.addCourse("CMPT 241");
        s2.setMajor("Math");
        s2.setMinor("Computer Science");
        s2.updateGrade("CMPT 360", 95);
        s2.updateGrade("CMPT 241", 95);
        s2.setGpa();
        if (s2.compareTo(s1) == 1){
            System.out.println(s2.getName() + " has a greater GPA than " + s1.getName() + ".");
        }
        else if (s2.compareTo(s1) == -1){
            System.out.println(s2.getName() + " has a lower GPA than " + s1.getName()+ ".");
        }
        else {
            System.out.println(s2.getName() + " has the same GPA as " + s1.getName()+ ".");
        }
        
        System.out.println();
        
        Faculty f1 = new Faculty("000221223", "Professor Bob", "5 Sunshine Road", "991-4856", "Computer Science", "203A", "Professor");
        f1.addCourse("CMPT 360");
        f1.addCourse("CMPT 241");
        f1.addCourse("CMPT 101");
        System.out.println(f1.toString());
        f1.displayCourses();
        f1.setRate(100000.00);
        
        System.out.println();
        
        Person p1 = new Student("000443458", "Samantha Morrison", "120 Manhattan College Parkway", "873-7870", "Science", true, 1);
        Person p2 = new Faculty("000332331", "Professor Smith", "5 Tibett Road", "220-3258", "Computer Science", "203B", "Professor");
        displayPerson(p1);
        displayPerson(p2);
        
    }
    
    public static void displayPerson(Person person){
        if (person instanceof Student){
            System.out.println(((Student)person).getName());
        }
        else if (person instanceof Faculty){
            System.out.println(((Faculty)person).getName());
            System.out.println(((Faculty)person).getId());
        }
    }
}
