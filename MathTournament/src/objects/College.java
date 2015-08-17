package objects;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Casey Bowman
 *
 */
public class College implements Comparable<College>
{
    String name;
    String abbr;
    ArrayList<Student> students = new ArrayList <Student> ();
    ArrayList<Team> teams = new ArrayList <Team> ();
    double score;

    /**
     * @param name
     * @param students
     * @param teams
     */
    public College(String name, String abbr) {
        this.name = name;
        this.abbr = abbr;
    }

    /**
     * @return the name
     */
    public String getName ()
    {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName (String name)
    {
        this.name = name;
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

    /**
     * @return the teams
     */
    public ArrayList<Team> getTeams ()
    {
        return teams;
    }

    /**
     * @param teams the teams to set
     */
    public void setTeams (ArrayList<Team> teams)
    {
        this.teams = teams;
    }

    /**
     * @return the score
     */
    public double getScore ()
    {
        return score;
    }

    /**
     * @param score the score to set
     */
    public void setScore (double score)
    {
        this.score = score;
    }
    
    public void addStudent (Student s) {
    	students.add(s);
    }

    public void determineScore ()
    {
        Collections.sort(students);
        int len = students.size();
        int s1 = students.get(len - 1).getScore();
        int s2 = students.get(len - 2).getScore();
        int s3 = students.get(len - 3).getScore();
        Collections.sort(teams);
        len = teams.size();
        int t = teams.get(len - 1).getScore();
        this.setScore((s1 + s2 + s3) * 1.25 / 3.0 + t * .5);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString ()
    {
        return "College [name=" + name + ", students=" + students + ", teams=" + teams + ", score=" + score + "]";
    }

    @Override
    public int compareTo (College c)
    {
        if (score > c.getScore()) return 1;
        else if (score < c.getScore()) return -1;
        else return 0;
    }

}
