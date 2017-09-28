package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MainWindow extends JFrame implements ActionListener{
    private Dimension screenSize;
    private StringBuffer keywordString =new StringBuffer();

    private JButton settingBtn;

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
        JPanel outputPanel = new JPanel();
        outputPanel.setBackground(Color.BLUE);


        // toolbaranel
        JButton runBtn = new JButton("Run");
        runBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("runBtn clicked");
            }
        });
        JButton debugBtn = new JButton("Debug");
        JButton openBtn = new JButton("Open");
        JButton saveBtn = new JButton("Save");
        JButton stopBtn = new JButton("Stop");
        JButton settingBtn = new JButton("Setting");

        toolbarPanel.add(openBtn);
        toolbarPanel.add(saveBtn);
        toolbarPanel.add(runBtn);
        toolbarPanel.add(debugBtn);
        toolbarPanel.add(stopBtn);
        toolbarPanel.add(settingBtn);

        // editorPanel
        CodeEditor codeEditor = new CodeEditor();
        codeEditor.setBackground(Color.PINK);
//        codeEditor.setMinimumSize(new Dimension((int)(screenSize.getWidth()/3),(int)(screenSize.getHeight()/3)));
        codeEditor.setEditable(true);

        JScrollPane js=new JScrollPane(codeEditor);
        editorPanel.setLayout(new BorderLayout());
        editorPanel.add(js);

        JTextPane outputPane = new JTextPane();
        JScrollPane outputScrollPane=new JScrollPane(outputPane);
        outputPanel.setMinimumSize(new Dimension((int)screenSize.getWidth()/2,(int)(screenSize.getHeight()/4)));
        outputPanel.setLayout(null);
        outputPanel.add(outputScrollPane);


        getContentPane().add(toolbarPanel,BorderLayout.NORTH);
        getContentPane().add(outputPanel,BorderLayout.SOUTH);
        getContentPane().add(projectPanel,BorderLayout.WEST);
        getContentPane().add(editorPanel,BorderLayout.CENTER);

        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(settingBtn)) {

        }
    }
}
