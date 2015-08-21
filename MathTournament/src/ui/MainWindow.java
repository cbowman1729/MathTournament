package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableColumn;

import data.Tracker;
import objects.College;
import objects.Student;
import objects.Team;

public class MainWindow extends JFrame
{

    private final int WIDTH = 1100;
    private final int HEIGHT = 600;
    private Tracker tracker = new Tracker();

    public MainWindow() {
        System.out.println("Initialize Window");
        init();
    }

    public void init ()
    {
        File fcoll = new File("colleges.ser");
        File fstud = new File("students.ser");
        if (fcoll.exists() && fstud.exists()) {
            tracker.importData();
        }
        this.setTitle("Main Tournament Window");
        this.setLayout(new GridBagLayout());

        JButton addStudent = new MyButton("Add Student");
        JButton enterScores = new MyButton("Enter Morning Scores");
        JButton addColleges = new MyButton("Enter College Names");
        JButton enterTeamScores = new MyButton("Enter Team Scores");
        JButton showTopScores = new MyButton("Show Top Scores");
        JButton saveStudents = new MyButton("Save Students to File");
        JButton verifyScores = new MyButton("Verify Morning Scores");
        addStudent.addActionListener(new ActionListener() {
            public void actionPerformed (ActionEvent e)
            {
                AddStudentsFrame asf = new AddStudentsFrame(tracker);
            }
        });

        enterScores.addActionListener(new ActionListener() {
            public void actionPerformed (ActionEvent e)
            {
                EnterScoresFrame esf = new EnterScoresFrame(tracker);
            }
        });

        addColleges.addActionListener(new ActionListener() {
            public void actionPerformed (ActionEvent e)
            {
                AddCollegesFrame acf = new AddCollegesFrame(tracker);
            }
        });
        saveStudents.addActionListener(new ActionListener() {
            public void actionPerformed (ActionEvent e)
            {
                tracker.saveCollegesAndStudents();
            }
        });
        verifyScores.addActionListener(new ActionListener() {
            public void actionPerformed (ActionEvent e)
            {
                VerifyScoresFrame vsf = new VerifyScoresFrame(tracker);
            }
        });
        enterTeamScores.addActionListener(new ActionListener() {
            public void actionPerformed (ActionEvent e)
            {
                EnterTeamScores ets = new EnterTeamScores(tracker);
            }
        });
        this.add(addColleges, setConstraints(0, 0));
        this.add(addStudent, setConstraints(0, 1));
        this.add(saveStudents, setConstraints(0, 2));
        this.add(enterScores, setConstraints(0, 3));
        this.add(verifyScores, setConstraints(1, 0));
        this.add(enterTeamScores, setConstraints(1, 1));
        this.add(showTopScores, setConstraints(1, 2));

        this.setSize(WIDTH, HEIGHT);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getContentPane().setBackground(new Color(0, 150, 255));
        this.setVisible(true);
        this.setLocation(getWindowPoint());
        this.setResizable(false);
    }

    public Point getWindowPoint ()
    {
        int screenWidth = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        int screenHeight = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        int x = (screenWidth >> 1) - (WIDTH >> 1);
        int y = (screenHeight >> 1) - (HEIGHT >> 1);
        return new Point(x, y);
    }

    public GridBagConstraints setConstraints (int x, int y)
    {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.insets = new Insets(20, 20, 20, 20);
        return gbc;
    }

    public static void main (String[] args)
    {
        MainWindow mw = new MainWindow();
    }
}

class MyButton extends JButton
{

    public MyButton(String name) {
        super(name);
        setFont(new Font("Consolas", Font.BOLD, 14));
        setBorder(BorderFactory.createRaisedBevelBorder());
    }

    public Dimension getPreferredSize ()
    {
        return new Dimension(220, 30);
    }
}

class AddCollegesFrame extends JFrame
{
    public AddCollegesFrame(Tracker tracker) {
        this.getContentPane().setBackground(new Color(0, 150, 255));
        this.getRootPane().setBorder(new EmptyBorder(20, 20, 20, 20));
        this.getRootPane().setBackground(new Color(0, 150, 255));
        this.setTitle("Enter College Names");
        JTable table = new JTable(20, 2);
        table.setRowHeight(25);
        table.getColumnModel().getColumn(0).setHeaderValue("Full Name");
        table.getColumnModel().getColumn(1).setHeaderValue("Abbreviation");
        table.getColumnModel().getColumn(0).setPreferredWidth(400);
        table.getColumnModel().getColumn(1).setPreferredWidth(100);
        JScrollPane scrollTable = new JScrollPane(table);
        scrollTable.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK, 4),
                BorderFactory.createLineBorder(Color.GRAY, 4)));
        JPanel tablePanel = new JPanel(new FlowLayout());
        tablePanel.add(scrollTable);
        tablePanel.setOpaque(false);
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton save = new MyButton("Save") {
            public Dimension getPreferredSize ()
            {
                return new Dimension(100, 25);
            }
        };
        buttonPanel.add(save);
        buttonPanel.setOpaque(false);
        this.setLayout(new BorderLayout());
        this.add(tablePanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
        this.setLocation(500, 200);
        this.setSize(900, 600);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }
}

class EnterScoresFrame extends JFrame
{
    public EnterScoresFrame(Tracker tracker) {
        this.getContentPane().setBackground(new Color(0, 150, 255));
        this.getRootPane().setBorder(new EmptyBorder(20, 20, 20, 20));
        this.getRootPane().setBackground(new Color(0, 150, 255));
        this.setTitle("Enter Student Scores");
        this.setLayout(new BorderLayout());
        this.setLocation(500, 200);
        this.setSize(900, 600);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setVisible(true);
        ArrayList<College> coll = tracker.getColleges();
        ArrayList<Student> stud = new ArrayList<Student>();
        for (College c : coll) {
            stud.addAll(c.getStudents());
        }
        JPanel scrollPanel = new JPanel(new FlowLayout());
        int rownum = (stud.size() > 0) ? stud.size() : 5;
        JTable table = new JTable(rownum, 2);
        for (int i = 0; i < stud.size(); i++) {
            Student s = stud.get(i);
            String name = s.getLast() + ", " + s.getFirst();
            table.setValueAt(name, i, 0);
        }
        table.getColumnModel().getColumn(0).setHeaderValue("Name");
        table.getColumnModel().getColumn(1).setHeaderValue("Score");
        table.getColumnModel().getColumn(0).setMinWidth(400);
        table.getColumnModel().getColumn(0).setMaxWidth(400);
        table.getColumnModel().getColumn(1).setMinWidth(100);
        table.getColumnModel().getColumn(1).setMaxWidth(100);
        table.setRowHeight(25);
        table.setFont(new Font("Consolas", Font.PLAIN, 16));
        JTextField exampleField = new JTextField();
        exampleField.setFont(new Font("Consolas", Font.PLAIN, 16));
        DefaultCellEditor dce = new DefaultCellEditor(exampleField);
        table.getColumnModel().getColumn(0).setCellEditor(dce);
        table.getColumnModel().getColumn(1).setCellEditor(dce);
        int rowmargin = table.getRowMargin();
        JScrollPane scroll = new JScrollPane(table) {
            public Dimension getPreferredSize ()
            {
                int h = (25 + rowmargin) * (rownum + 1);
                return new Dimension(518, (h > 400) ? 400 : h);
            }
        };
        scroll.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK, 3),
                BorderFactory.createLineBorder(Color.GRAY, 3)));
        scrollPanel.add(scroll);
        scrollPanel.setOpaque(false);
        this.add(scrollPanel, BorderLayout.CENTER);
        JButton save = new MyButton("Save");
        save.addActionListener(new ActionListener() {
            public void actionPerformed (ActionEvent e)
            {
                for (int i = 0; i < stud.size(); i++) {
                    Student s = stud.get(i);
                    int score = Integer.parseInt((String) table.getValueAt(i, 1));
                    s.setScore(score);
                }
                tracker.setStudents(stud);
                tracker.printColleges();
                System.out.println();
                tracker.printStudents();
                dispose();
            }
        });
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(save);
        buttonPanel.setOpaque(false);
        this.add(buttonPanel, BorderLayout.SOUTH);
    }
}

class VerifyScoresFrame extends JFrame
{
    public VerifyScoresFrame(Tracker tracker) {
        this.getContentPane().setBackground(new Color(0, 150, 255));
        this.getRootPane().setBorder(new EmptyBorder(20, 20, 20, 20));
        this.getRootPane().setBackground(new Color(0, 150, 255));
        this.setTitle("Score Verification");
        this.setLayout(new BorderLayout());
        this.setLocation(500, 200);
        this.setSize(900, 600);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setVisible(true);
        JPanel tablePanel = new JPanel(new FlowLayout());
        ArrayList<Student> stud = tracker.getStudents();
        JTable table = new JTable(stud.size() + 1, 2);
        int rownum = (stud.size() > 0) ? stud.size() : 5;
        for (int i = 0; i < stud.size(); i++) {
            Student s = stud.get(i);
            String name = s.getLast() + ", " + s.getFirst();
            int score = s.getScore();
            table.setValueAt(name, i, 0);
            table.setValueAt(score, i, 1);
        }
        table.getColumnModel().getColumn(0).setHeaderValue("Name");
        table.getColumnModel().getColumn(1).setHeaderValue("Score");
        table.getColumnModel().getColumn(0).setMinWidth(400);
        table.getColumnModel().getColumn(0).setMaxWidth(400);
        table.getColumnModel().getColumn(1).setMinWidth(100);
        table.getColumnModel().getColumn(1).setMaxWidth(100);
        table.setRowHeight(25);
        table.setFont(new Font("Consolas", Font.PLAIN, 16));
        JTextField exampleField = new JTextField();
        exampleField.setFont(new Font("Consolas", Font.PLAIN, 16));
        DefaultCellEditor dce = new DefaultCellEditor(exampleField);
        table.getColumnModel().getColumn(0).setCellEditor(dce);
        table.getColumnModel().getColumn(1).setCellEditor(dce);
        int rowmargin = table.getRowMargin();
        JScrollPane scroll = new JScrollPane(table) {
            public Dimension getPreferredSize ()
            {
                int h = (25 + rowmargin) * (stud.size() + 1);
                return new Dimension(518, (h > 400) ? 400 : h);
            }
        };
        scroll.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK, 3),
                BorderFactory.createLineBorder(Color.GRAY, 3)));
        tablePanel.add(scroll);
        tablePanel.setOpaque(false);
        this.add(tablePanel, BorderLayout.CENTER);
        JButton close = new MyButton("Close Window");
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setOpaque(false);
        buttonPanel.add(close);
        this.add(buttonPanel, BorderLayout.SOUTH);
        close.addActionListener(new ActionListener() {
            public void actionPerformed (ActionEvent e)
            {
                dispose();
            }
        });
    }
}

class EnterTeamScores extends JFrame
{
    public EnterTeamScores(Tracker tracker) {
        this.getContentPane().setBackground(new Color(0, 150, 255));
        this.getRootPane().setBorder(new EmptyBorder(20, 20, 20, 20));
        this.getRootPane().setBackground(new Color(0, 150, 255));
        this.setTitle("Enter Team Scores");
        this.setLayout(new BorderLayout());
        this.setLocation(500, 200);
        this.setSize(900, 500);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setVisible(true);
        if (tracker.getTeams().size() == 0) tracker.popTeams();
        ArrayList<Team> teams = tracker.getTeams();
        int rownum = (teams.size() > 0) ? teams.size() : 5;
        JTable table = new JTable(rownum, 12);

        JTextField exampleField = new JTextField();
        exampleField.setFont(new Font("Consolas", Font.PLAIN, 16));
        DefaultCellEditor dce = new DefaultCellEditor(exampleField);
        for (int i = 0; i < 12; i++) {
            TableColumn tc = table.getColumnModel().getColumn(i);
            if (i == 0) {
                tc.setHeaderValue("Team Name");
                tc.setMaxWidth(200);
                tc.setMinWidth(200);
            } else if (i == 11) {
                tc.setHeaderValue("Total");
                tc.setMaxWidth(50);
                tc.setMinWidth(50);
            } else {
                tc.setHeaderValue("Q" + i);
                tc.setMaxWidth(40);
                tc.setMinWidth(40);
            }
            tc.setCellEditor(dce);
        }
        for (int i = 0; i < rownum; i++) {
            Team t = teams.get(i);
            table.setValueAt(t.getCollege() + "-" + t.getNumber(), i, 0);
        }
        int colmarg = table.getColumnModel().getColumnMargin();
        int rowmarg = table.getRowMargin();
        table.setRowHeight(25);
        table.setFont(new Font("Consolas", Font.PLAIN, 16));
        JScrollPane scroll = new JScrollPane(table) {
            public Dimension getPreferredSize ()
            {
                this.getVerticalScrollBar().setPreferredSize(new Dimension(17, 0));
                int h = (26 + rowmarg) * (rownum + 1);
                return new Dimension(11 * colmarg + 10 * 40 + 250 + 22, (h > 400) ? 400 : h);
            }
        };
        JPanel tablePanel = new JPanel(new FlowLayout());
        scroll.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.black, 4),
                BorderFactory.createLineBorder(new Color(180, 180, 180), 4)));
        tablePanel.add(scroll);
        tablePanel.setOpaque(false);
        this.add(tablePanel, BorderLayout.CENTER);
        JButton report = new MyButton("Create Score Report");
        report.addActionListener(new ActionListener() {
            public void actionPerformed (ActionEvent e)
            {
                // ArrayList <Team> teams = tracker.getTeams();
                try {
                    for (int i = 0; i < rownum; i++) {
                        Team t = teams.get(i);
                        for (int j = 1; j < 11; j++) {
                            String q = (String) table.getValueAt(i, j);
                            int qq = (q != null) ? Integer.parseInt(q) : 0;
                            t.setQScore(j - 1, qq);
                        }
                        int total = Integer.parseInt((String) table.getValueAt(i, 11));
                        t.setScore(total);
                    }
                    Collections.sort(teams);
                    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(new File("TeamReport.html"))));
                    pw.println("<html><head><title>Team Report</title>");
                    pw.println("<style>");
                    pw.println("body {");
                    pw.println("background-color: #0096FF;");
                    pw.println("}");
                    pw.println("th, td{");
                    pw.println("border: 1px solid black;");
                    pw.println("font-family:\"Consolas\";");
                    pw.println("font-size: 24px;");
                    pw.println("text-align: center;");
                    pw.println("padding-top: 5px; padding-bottom: 5px; padding-left: 10px; padding-right: 10px;");
                    pw.println("}");
                    pw.println("tr:nth-child(even){background-color: #CCC;}");
                    pw.println("tr:nth-child(odd){background-color: #FFF;}");
                    pw.println("</style></head>");
                    pw.println("<body><table><tr><th>Team Name</th>");
                    for (int i = 1; i < 11; i++) {
                        pw.println("<th>Q" + i + "</th>");
                    }
                    pw.println("<th>Total</th>");
                    pw.println("</tr>");
                    for (int i = 0; i < 5; i++) {
                        Team t = teams.get(i);
                        String name = t.getCollege() + "-" + t.getNumber();
                        int[] qScores = t.getQScores();
                        int total = t.getScore();
                        pw.println("<tr>");
                        pw.println("<td>" + name + "</td>");
                        for (int j = 0; j < qScores.length; j++) {
                            int q = qScores[j];
                            String s = "";
                            if (q > 0) s += q;
                            pw.println("<td>" + s + "</td>");
                        }
                        pw.println("<td>" + t.getScore() + "</td>");
                        pw.println("</tr>");
                    }
                    pw.println("</table>");
                    pw.println("</body></html>");
                    pw.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(report);
        this.add(buttonPanel, BorderLayout.SOUTH);
    }
}

class AddStudentsFrame extends JFrame
{
    public AddStudentsFrame(Tracker tracker) {
        this.getContentPane().setBackground(new Color(0, 150, 255));
        this.getRootPane().setBorder(new EmptyBorder(20, 20, 20, 20));
        this.getRootPane().setBackground(new Color(0, 150, 255));
        this.setTitle("Add Students");
        this.setLayout(new BorderLayout());
        this.setLocation(500, 200);
        this.setSize(900, 600);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setVisible(true);
        JPanel panel = new JPanel(new GridBagLayout());
        JLabel nameLabel = new JLabel("Enter College Name:");
        nameLabel.setFont(new Font("Consolas", Font.BOLD, 14));
        nameLabel.setForeground(Color.WHITE);
        JTextField nameField = new JTextField(250) {
            public Dimension getPreferredSize ()
            {
                return new Dimension(250, 20);
            }
        };
        GridBagConstraints gbc = setConstraints(0, 0);
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(nameLabel, gbc);
        panel.add(nameField, setConstraints(1, 0));
        JLabel abbrLabel = new JLabel("Enter College Abbreviation:");
        abbrLabel.setFont(new Font("Consolas", Font.BOLD, 14));
        abbrLabel.setForeground(Color.WHITE);
        JTextField abbrField = new JTextField(250) {
            public Dimension getPreferredSize ()
            {
                return new Dimension(250, 20);
            }
        };
        gbc = setConstraints(0, 1);
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(abbrLabel, gbc);
        panel.add(abbrField, setConstraints(1, 1));
        JTable table = new JTable(80, 3);
        table.getColumnModel().getColumn(0).setHeaderValue("First");
        table.getColumnModel().getColumn(1).setHeaderValue("Last");
        // table.getColumnModel().getColumn(2).setHeaderValue("College");
        table.getColumnModel().getColumn(2).setHeaderValue("Team");
        table.getColumnModel().getColumn(0).setMinWidth(240);
        table.getColumnModel().getColumn(0).setMaxWidth(240);
        table.getColumnModel().getColumn(1).setMinWidth(240);
        table.getColumnModel().getColumn(1).setMaxWidth(240);
        // table.getColumnModel().getColumn(2).setMinWidth(120);
        // table.getColumnModel().getColumn(2).setMaxWidth(120);
        table.getColumnModel().getColumn(2).setMinWidth(67);
        table.getColumnModel().getColumn(2).setMaxWidth(67);
        table.setRowHeight(25);
        table.setFont(new Font("Consolas", Font.PLAIN, 16));
        JTextField exampleField = new JTextField();
        exampleField.setFont(new Font("Consolas", Font.PLAIN, 16));
        DefaultCellEditor dce = new DefaultCellEditor(exampleField);
        table.getColumnModel().getColumn(0).setCellEditor(dce);
        table.getColumnModel().getColumn(1).setCellEditor(dce);
        table.getColumnModel().getColumn(2).setCellEditor(dce);
        JScrollPane scroll = new JScrollPane(table) {
            public Dimension getPreferredSize ()
            {
                return new Dimension(580, 400);
            }
        };
        JPanel tablePanel = new JPanel(new FlowLayout());
        scroll.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.black, 4),
                BorderFactory.createLineBorder(new Color(180, 180, 180), 4)));
        tablePanel.add(scroll);
        tablePanel.setOpaque(false);
        panel.setOpaque(false);
        this.add(tablePanel, BorderLayout.CENTER);
        this.add(panel, BorderLayout.NORTH);
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton save = new MyButton("save") {
            public Dimension getPreferredSize ()
            {
                return new Dimension(100, 25);
            }
        };
        buttonPanel.add(save);
        save.addActionListener(new ActionListener() {
            public void actionPerformed (ActionEvent ae)
            {
                String collName = nameField.getText();
                String collAbbr = abbrField.getText();
                College c = new College(collName, collAbbr);
                int team = 0;
                ArrayList<Integer> teamnums = new ArrayList<Integer>();
                ArrayList<Team> teams = new ArrayList<Team>();
                for (int i = 0; i < 80; i++) {
                    int id = i;
                    String first = (String) table.getValueAt(i, 0);
                    if (first != null) {
                        String last = (String) table.getValueAt(i, 1);
                        int nextteam = Integer.parseInt((String) table.getValueAt(i, 2));
                        Student s = new Student(id, first, last, nextteam);
                        teamnums.add(nextteam);
                        if (nextteam != team) {
                            team = nextteam;
                            Team t = new Team(collAbbr, team);
                            t.addStudent(s);
                            teams.add(t);
                        } else {
                            Team t = teams.get(team - 1);
                            t.addStudent(s);
                        }
                        c.addStudent(s);
                    } else break;
                }
                // teamnums.stream().collect(Collectors.toSet());
                teams.forEach(p -> c.addTeam(p));
                tracker.addCollege(c);
                tracker.printColleges();
                dispose();
            }
        });
        buttonPanel.setOpaque(false);
        this.add(buttonPanel, BorderLayout.SOUTH);
    }

    public GridBagConstraints setConstraints (int x, int y)
    {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.insets = new Insets(5, 5, 5, 5);
        return gbc;
    }
}
