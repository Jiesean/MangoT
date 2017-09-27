package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MainWindow extends JFrame{
    private Dimension screenSize;
    private StringBuffer keywordString =new StringBuffer();

    public MainWindow(){
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setTitle("MangoT");
        this.setSize((int)screenSize.getWidth(), (int)screenSize.getHeight());
        getContentPane().setLayout(new BorderLayout());

        JPanel toolbarPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        toolbarPanel.setBackground(Color.GREEN);
        JPanel projectPanel = new JPanel();
        projectPanel.setBackground(Color.YELLOW);
        JPanel editorPanel = new JPanel();
        JPanel oututPanel = new JPanel();
        oututPanel.setBackground(Color.BLUE);




        // toolbaranel
        JButton runBtn = new JButton("Run");
        JButton debugBtn = new JButton("Debug");
        JButton openBtn = new JButton("Open");
        JButton saveBtn = new JButton("Save");
        JButton stopBtn = new JButton("Stop");

        toolbarPanel.add(openBtn);
        toolbarPanel.add(saveBtn);
        toolbarPanel.add(runBtn);
        toolbarPanel.add(debugBtn);
        toolbarPanel.add(stopBtn);



        // editorPanel
        MyTextPane textPane = new MyTextPane();
        textPane.setBackground(Color.PINK);
        textPane.setMinimumSize(new Dimension((int)(screenSize.getWidth()/3),(int)(screenSize.getHeight()/3)));
        textPane.setEditable(true);
//        textPane.addKeyListener(new KeyListener() {
//            @Override
//            public void keyTyped(KeyEvent e) {
//
//            }
//
//            @Override
//            public void keyPressed(KeyEvent e) {
//                if (e.getKeyCode() == KeyEvent.VK_0) {
//                    System.out.println("pressed 0");
//                    System.out.println(keywordString.toString());
//                    keywordString.delete(0,keywordString.length());
//
//                }else{
//                    keywordString.append(e.getKeyChar());
//                }
//            }
//
//            @Override
//            public void keyReleased(KeyEvent e) {
//
//            }
//        });


        JScrollPane js=new JScrollPane(textPane);
        editorPanel.setLayout(new BorderLayout());
        editorPanel.add(js);


        getContentPane().add(toolbarPanel,BorderLayout.NORTH);
        getContentPane().add(oututPanel,BorderLayout.SOUTH);
        getContentPane().add(projectPanel,BorderLayout.WEST);
        getContentPane().add(editorPanel,BorderLayout.CENTER);

        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



    }
}
