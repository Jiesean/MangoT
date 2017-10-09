package gui;

import javax.swing.*;
import javax.swing.text.Element;
import java.awt.*;

/**
 * @author Jie
 * @version V1.0
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}(用一句话描述该文件做什么)
 * @date ${date} ${time}
 */
public class MLineNumPane extends JPanel{
    private JTextPane lineNumShowPane;
    private JPanel operationPanel;
    private StringBuffer rowContent;

    public MLineNumPane(){
        GridLayout gridLayout1 = new GridLayout(1,2);
        this.setLayout(gridLayout1);
        lineNumShowPane = new JTextPane();
        this.add(lineNumShowPane);
        operationPanel = new JPanel();
//        GridLayout gridLayout = new GridLayout(0,1);
        FlowLayout flowLayout = new FlowLayout();
//        flowLayout.setAlignment(FlowLayout.);
        operationPanel.setLayout(flowLayout);
        this.add(operationPanel);
    }

    public void setLineNum(int lineNum){
        rowContent=new StringBuffer();
//        Element map = lineNumShowPane.getDocument().getDefaultRootElement();
//        int count=map.getElementCount();
        lineNumShowPane.setText("");
        operationPanel.removeAll();
        for(int i=0;i<lineNum;i++)
        {
            rowContent.append((i+1)+"\n");
            JButton button = new JButton(i+"");
            button.setMargin(new Insets(0,0,0,0));
            button.setBorder(null);
            operationPanel.add(button);
        }
        lineNumShowPane.setText(rowContent.toString());
    }
}
