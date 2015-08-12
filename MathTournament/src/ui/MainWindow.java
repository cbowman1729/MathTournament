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

    class AddCollegeFrame extends JFrame
    {
        public AddCollegeFrame() {
            this.getContentPane().setBackground(new Color(0, 150, 255));
            this.getRootPane().setBorder(new EmptyBorder(20, 20, 20, 20) {
                public boolean isBorderOpaque ()
                {
                    return false;
                }
            });
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
            JTable table = new JTable(50, 2);
            table.getColumnModel().getColumn(0).setHeaderValue("Name");
            table.getColumnModel().getColumn(1).setHeaderValue("Team");
            table.getColumnModel().getColumn(0).setMinWidth(400);
            table.getColumnModel().getColumn(0).setMaxWidth(400);
            table.getColumnModel().getColumn(1).setMinWidth(50);
            table.getColumnModel().getColumn(1).setMaxWidth(50);
            table.setRowHeight(25);
            JTextField exampleField = new JTextField();
            exampleField.setFont(new Font("Consolas", Font.PLAIN, 16));
            DefaultCellEditor dce = new DefaultCellEditor(exampleField);
            table.getColumnModel().getColumn(0).setCellEditor(dce);
            table.getColumnModel().getColumn(1).setCellEditor(dce);
            JScrollPane scroll = new JScrollPane(table);
            JPanel tablePanel = new JPanel(new FlowLayout());
            tablePanel.add(scroll);
            tablePanel.setOpaque(false);
            panel.setOpaque(false);
            this.add(tablePanel, BorderLayout.CENTER);
            this.add(panel, BorderLayout.NORTH);
            JPanel buttonPanel = new JPanel(new FlowLayout());
            JButton submit = new JButton("save") {
                public Dimension getPreferredSize ()
                {
                    return new Dimension(100, 25);
                }
            };
            buttonPanel.add(submit);
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

    public MainWindow() {
        init();
    }

    public void init ()
    {
        this.setTitle("Main Tournament Window");
        this.setLayout(new GridBagLayout());

        JButton addStudent = new JButton("Add Student");
        addStudent.addActionListener(new ActionListener() {
            public void actionPerformed (ActionEvent e)
            {
                AddCollegeFrame asf = new AddCollegeFrame();
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
