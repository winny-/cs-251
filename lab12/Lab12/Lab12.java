package Lab12;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;    // for EmptyBorder

// The frame to hold all GUI objects
class JobAppFrame extends JFrame { 
    private static final long serialVersionUID = 5995615621534068977L;
    private static final int WIDTH = 300;
    private static final int HEIGHT = 400;
    
    JobAppFrame() {
        setTitle("Job Application Form");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE); 
        setVisible(true);
    }
}

public class Lab12  {
    // check box arrays for software skills and system skills
    private JCheckBox[] softwareBoxes = {
        new JCheckBox("Java programming"),
        new JCheckBox("Web programming"),
        new JCheckBox("Database system")
    }; 
    private JCheckBox[] systemBoxes = {
        new JCheckBox("Operating system"),
        new JCheckBox("Internetworking"),
        new JCheckBox("System programming")
    }; 
    // radio buttons for choice between software and system engineer
    private JRadioButton softwareEngineer, systemEngineer;
    // salary choice
    private JComboBox<String> salary;
    // system panel holds skill check boxes for system engineer
    // software panel is similar
    // center panel holds everything together
    private JPanel systemPanel = addSkills(systemBoxes), 
        softwarePanel = addSkills(softwareBoxes), 
        centerPanel = new JPanel(); 

    // add skills in the "boxes" array to a skill panel and return the panel
    // the panel should use a grid layout so that it holds all skills as check boxes plus a label on top that says "Skills (check all that apply):"
    JPanel addSkills(JCheckBox[] boxes) { 
        JPanel skillPanel = new JPanel(new GridLayout(boxes.length+1, 1));
		
        skillPanel.add(new JLabel("Skills (check all that apply):"));
        for (JCheckBox box: boxes) {
            skillPanel.add(box);
        }
        return skillPanel;
    } 
	
    // return the number of check boxes that are selected in the "boxes" array
    int selectedSkills(JCheckBox[] boxes) {
        int ret = 0;
        for (JCheckBox box: boxes) {
            if (box.isSelected()) {
                ret = ret + 1;
            }
        }
        return ret;
    }
	
    // return true under the following conditions
    // 1. salary requirement is not above 100,000
    // 2. if software engineer position is selected, then at least 2 software skills are checked
    // 3. if system engineer position is selected, then at least 2 system skills are checked
    boolean checkSkills() {
        return ((softwareEngineer.isSelected() && selectedSkills(softwareBoxes) >= 2) ||
                (systemEngineer.isSelected() && selectedSkills(systemBoxes) >= 2))
            && (!salary.getSelectedItem().equals("above $100,000"));
    }
	
    // add two radio buttons that represent software/system engineer positions to a panel and return the panel
    // add action listeners to the radio button so that 
    //        if software engineer position is selected, then the software skills are displayed as check boxes
    //        if system engineer position is selected, then the system skills are displayed as check boxes
    JPanel addPositions() {
        JPanel p = new JPanel(new GridLayout(2, 1));
        softwareEngineer = new JRadioButton("Software Engineer");
        softwareEngineer.setSelected(true);
        systemEngineer = new JRadioButton("System Engineer");
        ButtonGroup bg = new ButtonGroup();
        bg.add(softwareEngineer);
        bg.add(systemEngineer);
        ActionListener listener = new ActionListener() {
            Lab12 self = Lab12.this;
            public void actionPerformed(ActionEvent evt) {
                //                System.out.println(evt);
                System.out.println(evt.getSource());
                JPanel theSkillPanel;
                if (evt.getSource() == self.softwareEngineer) {
                    theSkillPanel = self.softwarePanel;
                } else {
                    theSkillPanel = self.systemPanel;
                }
                if (self.centerPanel.getComponent(1) == theSkillPanel) {
                    System.out.println("Not replacing.");
                    return;
                }
                System.out.println("Replacing.");
                self.centerPanel.remove(1);
                self.centerPanel.add(theSkillPanel, 1);
                self.centerPanel.revalidate();
                self.centerPanel.repaint();
            }
        };
        softwareEngineer.addActionListener(listener);
        systemEngineer.addActionListener(listener);

        p.add(softwareEngineer);
        p.add(systemEngineer);
        return p;
    }
	
    // add salary combobox
    JPanel addSalary() {
        // centerPanel holds all components except button
        JPanel salaryPanel = new JPanel(new GridLayout(2, 1));
        String[] salaryOptions = {"$20,000-$59,000", "$60,000-$100,000", "above $100,000"};
        salary = new JComboBox<>(salaryOptions);
        salaryPanel.add(new JLabel("Salary requirements:"));
        salaryPanel.add(salary);
        return salaryPanel;
    }
	
    public Lab12() {   
        JPanel windowPanel = new JPanel(new BorderLayout(0, 10));
        windowPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        JPanel southPanel = new JPanel(new FlowLayout());

        centerPanel.add(addPositions()); 
        centerPanel.add(softwarePanel); 
        centerPanel.add(addSalary());
        windowPanel.add(centerPanel, BorderLayout.CENTER);
		
        JButton submit = new JButton("submit"); 
        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (checkSkills()) {
                    JOptionPane.showMessageDialog(null, "Thank you for your application submission.\n" +
                                                  "We'll contact you after we process your information.");
                }
                else {
                    JOptionPane.showMessageDialog(null, "Sorry, no jobs at this time.");
                }
            }  
        } );
        southPanel.add(submit);
        windowPanel.add(southPanel, BorderLayout.SOUTH);
        new JobAppFrame().add(windowPanel);
    }  

    public static void main(String[] args) {
        new Lab12();
    }
}  
