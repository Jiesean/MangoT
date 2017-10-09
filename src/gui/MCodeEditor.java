package gui;

import tools.ColorKeyWords;
import tools.ContentChangeListener;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.*;
import javax.swing.text.rtf.RTFEditorKit;
import java.awt.*;
import java.awt.event.*;


public class MCodeEditor extends JPanel implements KeyListener,ChangeListener,MouseListener,CaretListener{

    protected StyleContext context;
    protected DefaultStyledDocument doc;

    private JTextPane codePane;
    private JTextPane operationPane;
    private MLineNumPane mLineNumPane;
    private JScrollPane operationScrollPane;
    private  JScrollPane codeScrollPane;

    private boolean isCodeChanged = false;

    private String fileName;
    StringBuffer rowContent;
    BoundedRangeModel model;
    ColorKeyWords colorKeyWord;

    SimpleAttributeSet keyAttr = new SimpleAttributeSet();


    public MCodeEditor(String fileName) {
        super();
        this.fileName = fileName;

        context = new StyleContext();
        doc = new DefaultStyledDocument(context);
        codePane = new JTextPane();
        colorKeyWord = new ColorKeyWords(codePane, new ContentChangeListener() {
            @Override
            public void contentChanged() {
                MainWindow.tabbedPane.setTitleAt(MainWindow.tabbedPane.getSelectedIndex(),fileName+".t*");
            }
        });
        codePane.setBackground(Color.PINK);
        codePane.setEditable(true);
        codePane.setDocument(doc);
        codePane.addKeyListener(this);
        codePane.addMouseListener(this);
//        codePane.addCaretListener(this);
        codePane.getDocument().addDocumentListener(colorKeyWord);
        codeScrollPane = new JScrollPane(codePane);
        codeScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        model = codeScrollPane.getVerticalScrollBar().getModel();
        model.addChangeListener(this);

        operationPane = new JTextPane();
        operationPane.setBackground(Color.MAGENTA);
        operationPane.setText("1 ");
        operationPane.setPreferredSize(new Dimension(32, getHeight()));
        operationPane.setEditable(false);
        operationPane.addMouseListener(this);
        mLineNumPane = new MLineNumPane();
        mLineNumPane.setPreferredSize(new Dimension(100, getHeight()));

        operationScrollPane=new JScrollPane(mLineNumPane);
        operationScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        operationScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);

        StyleConstants.setBackground(keyAttr, Color.GREEN);
        StyleConstants.setBold(keyAttr,true);

        this.setLayout(new BorderLayout());
        this.add(codeScrollPane,BorderLayout.CENTER);
        this.add(operationScrollPane, BorderLayout.WEST);
    }


    @Override
    public void keyTyped(KeyEvent e) {
        char x=e.getKeyChar();//»ñÈ¡µ±Ç°¼üÅÌÊäÈë·ûºÅ
        String s=getContent().replaceAll("\n", "");//»ñÈ¡µ±Ç°ÎÄ±¾ÄÚÈÝ
        int pos=codePane.getCaretPosition();
        if(x=='\n')
        {
            setRowContent();
//            tabTime();
            if(pos<s.length()&&s.charAt(pos)=='}')
            {
                StyledDocument doc = codePane.getStyledDocument();
                try {
                    doc.remove(pos+3, 3);
                } catch (BadLocationException e1) {
                    e1.printStackTrace();
                }
            }
            return ;
        }
        if(e.getKeyChar()=='}'&&s.charAt(pos-1)==' ')//µ±Î´»Ø³µ²åÈë}Ê±²»½øÐÐ´Ë²Ù×÷
        {
            StyledDocument doc = codePane.getStyledDocument();
            try {
                doc.remove(pos-3, 3);
            } catch (BadLocationException e1) {
                e1.printStackTrace();
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        StringBuffer s=new StringBuffer(codePane.getText());//»ñÈ¡µ±Ç°ÎÄ±¾ÄÚÈÝ
        if(e.getKeyCode()==9)//ÐÞ¸ÄtabËõ½øÖµ
        {
            int pos=codePane.getCaretPosition();
            StyledDocument doc = codePane.getStyledDocument();
            Style style = doc.addStyle("normalstyle", null);
            try {
                doc.remove(pos-1, 1);
                doc.insertString(pos-1, "   ", style);
            } catch (BadLocationException be) {
                be.printStackTrace();
            }
        }
    }

    private String getContent()
    {
        return codePane.getText();
    }


    public void tabTime() //Ëõ½ø´¦Àí
    {
        StyledDocument doc = codePane.getStyledDocument();
        Style style = doc.addStyle("normalstyle", null);
        int tabNum=0;
        String sx=getContent().replaceAll("\n", "");
        int pos=codePane.getCaretPosition();
        for(int i=0;i<pos;i++)
        {
            if(sx.charAt(i)=='{')
            {
                tabNum++;
            }
            if(sx.charAt(i)=='}')
            {
                tabNum--;
            }
        }
        for(int i=0;i<tabNum;i++)
        {
            try {
                doc.insertString(codePane.getCaretPosition(), "   ", style);
            } catch (BadLocationException e) {
                e.printStackTrace();
            }
        }
    }

    public void setRowContent()
    {
//        rowContent=new StringBuffer();
        Element map = codePane.getDocument().getDefaultRootElement();
        int count=map.getElementCount();
//        operationPane.setText("");
//        for(int i=0;i<count;i++)
//        {
//            rowContent.append((i+1)+"\n");
//        }
//        operationPane.setText(rowContent.toString());
        mLineNumPane.setLineNum(count);

    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if(e.getSource()==model)
        {
            JScrollBar sBar = codeScrollPane.getVerticalScrollBar();
            int x=sBar.getValue();
            JScrollBar sBar2 = operationScrollPane.getVerticalScrollBar();
            sBar2.setValue(x);
            operationScrollPane.setVerticalScrollBar(sBar2);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //左键点击
        if (e.getButton() == MouseEvent.BUTTON1 &&e.getClickCount() == 2) {
            Element root = doc.getDefaultRootElement();
            int cursorPos = codePane.getCaretPosition();
            int selectLineNum  = root.getElementIndex(cursorPos);
            System.out.println(selectLineNum);
            setBreakpoint(selectLineNum);
//            System.out.println(e.getY());
        }
    }

    private void setBreakpoint(int selectLineNum) {
        operationPane.setText(operationPane.getText().replace(selectLineNum+"\n",selectLineNum+"*\n"));
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void caretUpdate(CaretEvent e) {
        Element root = doc.getDefaultRootElement();
        int cursorPos = codePane.getCaretPosition();
        int line  = root.getElementIndex(cursorPos);
        System.out.println("curorPos"+cursorPos+"line"+line);
        if (line == 10) {
               hightlightLine(3);
        }
        else if(line == 12){
            hightlightLine(5);
        }

    }

    class MyHighlightPainter extends DefaultHighlighter.DefaultHighlightPainter {
        public MyHighlightPainter(Color color) {
            super(color);
        }
    }


    //highlight line with given num
    public void hightlightLine(int line){
        Element root = doc.getDefaultRootElement();
        Element para = root.getElement(line);

        Highlighter h = codePane.getHighlighter();
        MyHighlightPainter myh = new MyHighlightPainter(new Color(226, 239, 255));
        h.removeAllHighlights();
        try {
            h.addHighlight(para.getStartOffset(), para.getEndOffset() - 1, myh);
        } catch (BadLocationException e1) {
            e1.printStackTrace();
        }
    }

    //获得当前codeEditor的内容
    public String getChangedContent(){
        return codePane.getText();
    }

}
