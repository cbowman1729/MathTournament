package objects;

import java.io.Serializable;
import java.util.ArrayList;

public class Team implements Comparable<Team>, Serializable
{
    ArrayList<Student> students = new ArrayList <Student> ();
    String college;
    int score;
    int number;

    /**
     * @param students students on the team
     * @param score score for the team
     * @param number the team number
     */
    public Team(String college, int number) {
        this.college = college;
        this.number = number;
    }

    /**
     * @return the students
     */
    public ArrayList<Student> getStudents ()
    {
        return students;
    }

    /**
     * @param students the students to set
     */
    public void setStudents (ArrayList<Student> students)
    {
        this.students = students;
    }

    public String getCollege ()
    {
        return college;
    }

    public void setCollege (String college)
    {
        this.college = college;
    }

    /**
     * @return the score
     */
    public int getScore ()
    {
        return score;
    }

    /**
     * @param score the score to set
     */
    public void setScore (int score)
    {
        this.score = score;
    }

    /**
     * @return the number
     */
    public int getNumber ()
    {
        return number;
    }

    /**
     * @param number the number to set
     */
    public void setNumber (int number)
    {
        this.number = number;
    }

    public void addStudent (Student s) {
    	students.add(s);
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString ()
    {
        return "Team [college=" + college + ", students=" + students + ", score=" + score + ", number=" + number + "]";
    }

    @Override
    public int compareTo (Team t)
    {
        if (score > t.getScore()) return 1;
        else if (score < t.getScore()) return -1;
        else return 0;
    }

}
