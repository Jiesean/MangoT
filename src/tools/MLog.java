package tools;

import gui.MainWindow;

import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MLog {

    public static void D(String Tag,String message){
        //设置字体大小
        MainWindow.outputPane.append(new SimpleDateFormat("yyyy/MM/dd-HH:mm:ss:SSS").format(new Date()).toString()+"-> "+ Tag+" :" +message+"\n");
        System.out.println("me");
    }
}
