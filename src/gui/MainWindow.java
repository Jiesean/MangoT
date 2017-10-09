package gui;

import tools.FileList;
import tools.MLog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MainWindow extends JFrame implements ActionListener{
    private Dimension screenSize;
    private StringBuffer keywordString =new StringBuffer();

    private JButton settingBtn;
    private JButton openBtn;
    public static JTabbedPane tabbedPane;
    private JMenuItem createItem;
    public  JButton debugBtn;
    private JButton saveBtn;
    private JButton runBtn;

    public static MTextPane outputPane;
    private JPanel editorPanel;
    private Map<String,MCodeEditor> openedCodeEditorInfoMap ;

    public static boolean isContentChanged = false;

    public MainWindow(){
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setTitle("MangoT");
        this.setSize((int)screenSize.getWidth(), (int)screenSize.getHeight());

        initWindow();
        initMenuBar();
        openedCodeEditorInfoMap = new HashMap<>();

        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void initWindow(){

        getContentPane().setLayout(new BorderLayout());

        JPanel toolbarPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        toolbarPanel.setBackground(Color.GREEN);
        JPanel projectPanel = new JPanel();
        projectPanel.setBackground(Color.YELLOW);
        editorPanel = new JPanel();
        JPanel outputPanel = new JPanel();

        // toolbaranel
        runBtn = new JButton("Run");
        runBtn.addActionListener(this);
        debugBtn = new JButton("Debug");
        debugBtn.addActionListener(this);
        openBtn = new JButton("Open");
        openBtn.addActionListener(this);
        saveBtn = new JButton("Save");
        saveBtn.addActionListener(this);
        JButton stopBtn = new JButton("Stop");
        JButton settingBtn = new JButton("Setting");

        toolbarPanel.add(openBtn);
        toolbarPanel.add(saveBtn);
        toolbarPanel.add(runBtn);
        toolbarPanel.add(debugBtn);
        toolbarPanel.add(stopBtn);
        toolbarPanel.add(settingBtn);


        tabbedPane=new JTabbedPane();
        editorPanel.setLayout(new BorderLayout());
        editorPanel.add(tabbedPane);

        outputPane = new MTextPane();
        JScrollPane outputScrollPane = new JScrollPane(outputPane);
        outputPanel.setLayout(new BorderLayout());
        outputPanel.add(outputScrollPane,BorderLayout.CENTER);
        outputPanel.setPreferredSize(new Dimension(300,150));

        JTree projectPane = new JTree();
        FileList fileList=new FileList();
        projectPane = fileList.getTree();

        projectPanel.setPreferredSize(new Dimension(200,400));
        projectPanel.setLayout(new BorderLayout());
        projectPanel.add(projectPane,BorderLayout.CENTER);



        getContentPane().add(toolbarPanel,BorderLayout.NORTH);
        getContentPane().add(outputPanel,BorderLayout.SOUTH);
        getContentPane().add(projectPanel,BorderLayout.WEST);
        getContentPane().add(editorPanel,BorderLayout.CENTER);

    }

    private void initMenuBar(){
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("file");
        createItem = new JMenuItem("create");
        createItem.addActionListener(this);
        JMenuItem openItem = new JMenuItem("open");
        JMenuItem saveItem = new JMenuItem("save");
        JMenuItem quitItem = new JMenuItem("quit");

        fileMenu.add(createItem);
        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.add(quitItem);

        menuBar.add(fileMenu);
        this.setJMenuBar(menuBar);
    }

    private void createFileAndCodeEditor(){
        String fileName = JOptionPane.showInputDialog("请输入文件名：");
        System.out.println(fileName);

        if(fileName == null) return;


        File file = new File("C:\\"+fileName+".t");
        if (file.exists()) {
            JOptionPane.showMessageDialog(editorPanel,"文件已经存在");
        }
        else
        {
            try {
                file.createNewFile();
                MCodeEditor codeEditor = new MCodeEditor(fileName);
                tabbedPane.addTab(fileName+".t",null,codeEditor,fileName+".t");
                openedCodeEditorInfoMap.put(fileName+".t",codeEditor);
            } catch (IOException e1) {
                e1.printStackTrace();
                JOptionPane.showMessageDialog(editorPanel,"文件创建失败");
            }
        }
    }

    private void updateContentState() {
        isContentChanged = false;
        String tempFileName = tabbedPane.getTitleAt(tabbedPane.getSelectedIndex());
        String fileName = tempFileName.substring(0,tempFileName.length()-1);
        tabbedPane.setTitleAt(tabbedPane.getSelectedIndex(),fileName);
    }

    private void saveFile() {
        String fileName = tabbedPane.getTitleAt(tabbedPane.getSelectedIndex());
        String content = openedCodeEditorInfoMap.get(fileName).getChangedContent();
        saveContentToFile(fileName,content);
    }

    private void saveContentToFile(final String strFilename, final String strBuffer)
    {
        try
        {
            // 创建文件对象
            File file = new File("C:\\"+strFilename);
            if (!file.exists()) {
                System.out.println("saveContentToFile : file not exist");
            }
            // 向文件写入对象写入信息
            FileWriter fileWriter = new FileWriter(file);

            // 写文件
            fileWriter.write(strBuffer);
            // 关闭
            fileWriter.close();
        }
        catch (IOException e)
        {
            //
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(openBtn)||e.getSource().equals(createItem)) {
            createFileAndCodeEditor();
        }
        if (e.getSource().equals(saveBtn)){
            updateContentState();
            saveFile();
        }
        if (e.getSource().equals(runBtn)){
        }
        if (e.getSource().equals(debugBtn)){
        }


    }


}
