package objects;

import java.io.Serializable;

/**
 * @author Casey Bowman
 *
 */
public class Student implements Comparable<Student>, Serializable
{
    private static int count = 0;
    private int id;
    private String first;
    private String last;
    private int score;
    private int team;
    private String college;

    private static boolean compareScores = false;
    private static boolean compareIDs = false;

    /**
     * @param id
     * @param first
     * @param last
     * @param team
     * @param college
     */
    public Student(int id, String first, String last, int team) {
        count += 1;
        this.id = count;
        this.first = first;
        this.last = last;
        this.team = team;
    }

    public Student(int id, String first, String last, int team, String college) {
        this(id, first, last, team);
        this.college = college;
    }

    public int getID ()
    {
        return id;
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

    public void setCollege (String college)
    {
        this.college = college;
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

    public String getCollege ()
    {
        return college;
    }

    public static void setCompareScores (boolean b)
    {
        compareScores = b;
        compareIDs = !b;
    }

    public static void setCompareIDs (boolean b)
    {
        compareIDs = b;
        compareScores = !b;
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
        if (this.compareScores) {
            if (score > s.getScore()) return 1;
            else if (score < s.getScore()) return -1;
            else return 0;
        } else if (this.compareIDs) {
            if (id > s.getID()) return 1;
            else if (id < s.getID()) return -1;
            else return 0;
        } else {
            int c = this.getLast().compareTo(s.getLast());
            if (c == 0) return this.getFirst().compareTo(s.getFirst());
            else return c;
        }
    }

}
