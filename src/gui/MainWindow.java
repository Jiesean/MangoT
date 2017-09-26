package gui;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame{
    private Dimension screenSize;

    public MainWindow(){
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setTitle("MangoT");
        this.setSize((int)screenSize.getWidth(), (int)screenSize.getHeight());
        getContentPane().setLayout(new BorderLayout());

        JPanel toolbarPanel = new JPanel();
        toolbarPanel.setBackground(Color.GREEN);
        JPanel projectPanel = new JPanel();
        projectPanel.setBackground(Color.YELLOW);
        JPanel editorPanel = new JPanel();
        JPanel oututPanel = new JPanel();
        oututPanel.setBackground(Color.BLUE);

        getContentPane().add(toolbarPanel,BorderLayout.NORTH);
        getContentPane().add(oututPanel,BorderLayout.SOUTH);
        getContentPane().add(projectPanel,BorderLayout.WEST);


        JTextPane textPane = new JTextPane();
        textPane.setBackground(Color.PINK);
        textPane.setMinimumSize(new Dimension((int)(screenSize.getWidth()/3),(int)(screenSize.getHeight()/3)));
        textPane.setEditable(true);

        JScrollPane js=new JScrollPane(textPane);
        editorPanel.setLayout(new BorderLayout());
        editorPanel.add(js);
;
        getContentPane().add(editorPanel,BorderLayout.CENTER);

        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
