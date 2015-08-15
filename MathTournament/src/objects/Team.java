package objects;

import java.util.ArrayList;

public class Team implements Comparable<Team>
{
    private int id;
    ArrayList<Student> students;
    String college;
    int score;
    int number;

    /**
     * @param students students on the team
     * @param score score for the team
     * @param number the team number
     */
    public Team(int id, ArrayList<Student> students, String college, int score, int number) {
        this.id = id;
        this.students = students;
        this.college = college;
        this.score = score;
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

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString ()
    {
        return "Team [students=" + students + ", score=" + score + ", number=" + number + "]";
    }

    @Override
    public int compareTo (Team t)
    {
        if (score > t.getScore()) return 1;
        else if (score < t.getScore()) return -1;
        else return 0;
    }

}
