package gui;

import javax.swing.*;

public class MTextPane extends JTextPane{

    public MTextPane(){
        super();
        this.setText("output:\n");
        this.setEditable(false);
    }

    public void append(String text){
        this.setText(this.getText()+text);
    }
}
