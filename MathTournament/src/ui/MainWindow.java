package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

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

public class MainWindow extends JFrame
{

    public MainWindow() {
        System.out.println("Initialize Window");
        init();
    }

    public void init ()
    {
        this.setTitle("Main Tournament Window");
        this.setLayout(new GridBagLayout());

        JButton addStudent = new JButton("Add Student");
        JButton enterScores = new JButton("Enter Scores");
        addStudent.addActionListener(new ActionListener() {
            public void actionPerformed (ActionEvent e)
            {
                AddCollegeFrame asf = new AddCollegeFrame();
            }
        });

        enterScores.addActionListener(new ActionListener() {
            public void actionPerformed (ActionEvent e)
            {
                EnterScoresFrame esf = new EnterScoresFrame();
            }
        });

        this.add(addStudent, setConstraints(0, 0));
        this.setSize(1100, 600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getContentPane().setBackground(new Color(0, 150, 255));
        this.setVisible(true);
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

class Student
{

    private String first;
    private String last;
    private int score = 0;
    private int team;
    private String college;

    public Student(String f, String l, int t, String c) {
        first = f;
        last = l;
        team = t;
        college = c;
    }

    public String toXML ()
    {
        String s = "<student>\r\n";
        s += "\t<college>" + college + "</college>\r\n";
        s += "\t<first>" + first + "</first>\r\n";
        s += "\t<last>" + last + "</last>\r\n";
        s += "\t<team>" + team + "</team>\r\n";
        s += "\t<score>" + score + "</score>\r\n";
        s += "</student>";
        return s;
    }

    public String toString ()
    {
        return first + " " + last;
    }
}

class EnterScoresFrame extends JFrame
{
    public EnterScoresFrame() {
        this.getContentPane().setBackground(new Color(0, 150, 255));
        this.getRootPane().setBorder(new EmptyBorder(20, 20, 20, 20));
        this.getRootPane().setBackground(new Color(0, 150, 255));
        this.setTitle("Enter Student Scores");
        this.setLayout(new BorderLayout());
        this.setLocation(500, 200);
        this.setSize(900, 600);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }
}

class AddCollegeFrame extends JFrame
{
    public AddCollegeFrame() {
        this.getContentPane().setBackground(new Color(0, 150, 255));
        this.getRootPane().setBorder(new EmptyBorder(20, 20, 20, 20));
        this.getRootPane().setBackground(new Color(0, 150, 255));
        this.setTitle("Add College Information");
        this.setLayout(new BorderLayout());
        this.setLocation(500, 200);
        this.setSize(900, 600);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setVisible(true);
        JPanel panel = new JPanel(new GridBagLayout());
        JLabel collegeLabel = new JLabel("Enter the college name");
        collegeLabel.setFont(new Font("Consolas", Font.BOLD, 14));
        JTextField college = new JTextField(50);
        college.setFont(new Font("Consolas", Font.PLAIN, 16));
        panel.add(collegeLabel, setConstraints(0, 0));
        panel.add(college, setConstraints(1, 0));
        JTable table = new JTable(50, 3);
        table.getColumnModel().getColumn(0).setHeaderValue("First");
        table.getColumnModel().getColumn(1).setHeaderValue("Last");
        table.getColumnModel().getColumn(2).setHeaderValue("Team");
        table.getColumnModel().getColumn(0).setMinWidth(250);
        table.getColumnModel().getColumn(0).setMaxWidth(250);
        table.getColumnModel().getColumn(1).setMinWidth(250);
        table.getColumnModel().getColumn(1).setMaxWidth(250);
        table.getColumnModel().getColumn(2).setMinWidth(67);
        table.getColumnModel().getColumn(2).setMaxWidth(67);
        table.setRowHeight(25);
        table.setFont(new Font("Consolas", Font.PLAIN, 16));
        JTextField exampleField = new JTextField();
        exampleField.setFont(new Font("Consolas", Font.PLAIN, 16));
        DefaultCellEditor dce = new DefaultCellEditor(exampleField);
        table.getColumnModel().getColumn(0).setCellEditor(dce);
        table.getColumnModel().getColumn(1).setCellEditor(dce);
        JScrollPane scroll = new JScrollPane(table) {
            public Dimension getPreferredSize ()
            {
                return new Dimension(600, 400);
            }
        };
        JPanel tablePanel = new JPanel(new FlowLayout());
        scroll.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.black, 4),
                BorderFactory.createLineBorder(new Color(180, 180, 180), 4)));
        // tablePanel.setBorder(BorderFactory.createLineBorder(Color.black,
        // 5));
        tablePanel.add(scroll);
        tablePanel.setOpaque(false);
        panel.setOpaque(false);
        this.add(tablePanel, BorderLayout.CENTER);
        this.add(panel, BorderLayout.NORTH);
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton save = new JButton("save") {
            public Dimension getPreferredSize ()
            {
                return new Dimension(100, 25);
            }
        };
        buttonPanel.add(save);
        save.addActionListener(new ActionListener() {
            public void actionPerformed (ActionEvent ae)
            {
                try {
                    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(new File("test.txt"))));
                    String coll = college.getText();
                    for (int i = 0; i < 50; i++) {
                        String first = (String) table.getValueAt(i, 0);
                        if (first != null) {
                            String last = (String) table.getValueAt(i, 1);
                            int team = Integer.parseInt((String) table.getValueAt(i, 2));
                            Student s = new Student(first, last, team, coll);
                            pw.println(s.toXML());
                        }
                    }
                    pw.flush();
                    pw.close();
                    dispose();
                } catch (Exception e) {
                    e.printStackTrace();
                }
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
