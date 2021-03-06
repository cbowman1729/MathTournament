package data;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;

import objects.College;
import objects.Student;
import objects.Team;

public class Tracker
{
    private ArrayList<College> colleges = new ArrayList<College>();
    private ArrayList<Student> students = new ArrayList<Student>();
    private ArrayList<Team> teams = new ArrayList<Team>();

    public ArrayList<College> getColleges ()
    {
        return colleges;
    }

    public ArrayList<Student> getStudents ()
    {
        return students;
    }

    public ArrayList<Team> getTeams ()
    {
        return teams;
    }

    public void addCollege (College c)
    {
        colleges.add(c);
    }

    public void popStuds ()
    {
        for (College c : colleges) {
            students.addAll(c.getStudents());
        }
    }

    public void popTeams ()
    {
        for (College c : colleges) {
            teams.addAll(c.getTeams());
        }
    }

    public String determineCollegeWinners ()
    {
        Collections.sort(colleges);
        String s = "";
        int len = colleges.size();
        s += colleges.get(len - 1).getName() + "\n";
        s += colleges.get(len - 2).getName() + "\n";
        s += colleges.get(len - 3).getName();
        return s;
    }

    public String determineMorningWinners ()
    {
        Collections.sort(students);
        String s = "";
        int len = students.size();
        Student s1 = students.get(len - 1);
        Student s2 = students.get(len - 2);
        Student s3 = students.get(len - 3);
        s += s1.getFirst() + " " + s1.getLast() + "\n";
        s += s2.getFirst() + " " + s2.getLast() + "\n";
        s += s3.getFirst() + " " + s3.getLast();
        return s;
    }

    public String determineTeamWinners ()
    {
        Collections.sort(teams);
        String s = "";
        int len = teams.size();
        Team t1 = teams.get(len - 1);
        Team t2 = teams.get(len - 2);
        Team t3 = teams.get(len - 3);
        s += t1.getCollege() + " " + t1.getNumber() + "\n";
        s += t2.getCollege() + " " + t2.getNumber() + "\n";
        s += t3.getCollege() + " " + t3.getNumber() + "\n";
        return s;
    }

    public void printColleges ()
    {
        for (College c : colleges)
            System.out.println(c);
    }

    public void printStudents ()
    {
        for (Student s : students)
            System.out.println(s);
    }

    public void setStudents (ArrayList<Student> students)
    {
        this.students = students;
    }

    public void saveCollegesAndStudents ()
    {
        popStuds();
        try {
            FileOutputStream fos = new FileOutputStream("colleges.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeInt(colleges.size());
            for (College c : colleges) {
                oos.writeObject(c);
            }
            fos.close();
            oos.close();
            fos = new FileOutputStream("students.ser");
            oos = new ObjectOutputStream(fos);
            oos.writeInt(students.size());
            for (Student s : students) {
                oos.writeObject(s);
            }
            oos.close();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void importData ()
    {
        try {
            FileInputStream fis = new FileInputStream("colleges.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            int collsize = ois.readInt();
            for (int i = 0; i < collsize; i++) {
                College c = (College) ois.readObject();
                colleges.add(c);
            }
            ois.close();
            fis.close();
            fis = new FileInputStream("students.ser");
            ois = new ObjectInputStream(fis);
            int studsize = ois.readInt();
            for (int i = 0; i < studsize; i++) {
                Student s = (Student) ois.readObject();
                students.add(s);
            }
            ois.close();
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
