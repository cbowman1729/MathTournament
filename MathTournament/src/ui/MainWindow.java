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

import data.Tracker;
import objects.Student;

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
        this.setTitle("Main Tournament Window");
        this.setLayout(new GridBagLayout());

        JButton addStudent = new MyButton("Add Student");
        JButton enterScores = new MyButton("Enter Morning Scores");
        JButton addColleges = new MyButton("Enter College Names");
        JButton enterTeamScores = new MyButton("Enter Team Scores");
        JButton showTopScores = new MyButton("Show Top Scores");
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

        this.add(addColleges, setConstraints(0, 0));
        this.add(addStudent, setConstraints(0, 1));
        this.add(enterScores, setConstraints(0, 2));
        this.add(enterTeamScores, setConstraints(1, 0));
        this.add(showTopScores, setConstraints(1, 1));
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
        JLabel nameLabel = new JLabel("Enter College Name");
        JTextField nameField = new JTextField(150) {
            public Dimension getPreferredSize ()
            {
                return new Dimension(150, 20);
            }
        };
        panel.add(nameLabel, setConstraints(0, 0));
        panel.add(nameField, setConstraints(1, 0));
        JLabel abbrLabel = new JLabel("Enter College Abbreviation");
        JTextField abbrField = new JTextField(150) {
            public Dimension getPreferredSize ()
            {
                return new Dimension(150, 20);
            }
        };
        panel.add(abbrLabel, setConstraints(0, 1));
        panel.add(abbrField, setConstraints(1, 1));
        JTable table = new JTable(300, 3);
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
                boolean done = false;
                int r = 0;
                while (!done) {
                    int id = r;
                    String first = (String) table.getValueAt(r, 0);
                    if (first != null) {
                        String last = (String) table.getValueAt(r, 1);
                        // String coll = (String) table.getValueAt(r, 2);
                        int team = Integer.parseInt((String) table.getValueAt(r, 2));
                        Student s = new Student(id, first, last, team);

                    }
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
