package objects;

/**
 * @author Casey Bowman
 *
 */
public class Student implements Comparable<Student>
{
    private int id;
    private String first;
    private String last;
    private int score;
    private int team;
    // private String college;

    /**
     * @param id
     * @param first
     * @param last
     * @param team
     * @param college
     */
    public Student(int id, String first, String last, int team) {
        this.id = id;
        this.first = first;
        this.last = last;
        this.team = team;
    }

    /**
     * @return return the student's first name
     */
    public String getFirst ()
    {
        return first;
    }

    /**
     * @param first set the student's first name
     */
    public void setFirst (String first)
    {
        this.first = first;
    }

    /**
     * @return return the student's last name
     */
    public String getLast ()
    {
        return last;
    }

    /**
     * @param last set the student's last name
     */
    public void setLast (String last)
    {
        this.last = last;
    }

    /**
     * @return return the student's score
     */
    public int getScore ()
    {
        return score;
    }

    /**
     * @param score set the student's score
     */
    public void setScore (int score)
    {
        this.score = score;
    }

    /**
     * @return return the student's team number
     */
    public int getTeam ()
    {
        return team;
    }

    /**
     * @param team set the student's team number
     */
    public void setTeam (int team)
    {
        this.team = team;
    }

    /**
     * @return return student's college name
     * 
     *         public String getCollege () { return college; }
     * 
     *         /**
     * @param college set the student's college name
     * 
     *            public void setCollege (String college) { this.college =
     *            college; }
     */
    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString ()
    {
        return "Student [first=" + first + ", last=" + last + ", score=" + score + ", team=" + team + "]";
    }

    @Override
    public int compareTo (Student s)
    {
        if (score > s.getScore()) return 1;
        else if (score < s.getScore()) return -1;
        else return 0;
    }

}
