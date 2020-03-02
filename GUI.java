/**User inferface 
  *@Author NokSam Leong
  *@version 02/28/2020
  */

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JCheckBox;

public class GUI implements ActionListener{
    private Backend be = new Backend();
    private ArrayList<File> current = be.getNext();
    private JCheckBox cb1 = new JCheckBox();
    private JCheckBox cb2 = new JCheckBox();
    private JCheckBox cb3 = new JCheckBox();
    private JCheckBox cb4 = new JCheckBox();
    private JCheckBox none = new JCheckBox("none");
    private JLabel key = new JLabel(be.getKey());
    private JTextField text = new JTextField(be.getKey());
    /**constructor
      */
    public GUI(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        //create frame
        JFrame frame = new JFrame();

        key.setBounds(300,10,300,20);
        frame.add(key);
        //button
        JButton b1 = new JButton("Open " );
        b1.setBounds(screenSize.width/4-105, 40,100,30);
        JButton b2 = new JButton("Next");
        b2.setBounds(screenSize.width/4+5,40,100,30);
        b2.setActionCommand("next");
        b2.addActionListener(this);
        frame.add(b1);
        frame.add(b2);

        //checkbox
        cb1.setBounds(20,100,300,15);
        cb1.addActionListener(this);
        cb1.setActionCommand("0");
        cb2.setBounds(340,100,300,15);
        cb2.addActionListener(this);
        cb2.setActionCommand("1");
        cb3.setBounds(20,130,300,15);
        cb3.addActionListener(this);
        cb3.setActionCommand("2");
        cb4.setBounds(340,130,300,15);
        ActionListener actionListener = new ActionHandler();
        changeLabel();
        cb1.addActionListener(actionListener);
        cb2.addActionListener(actionListener);
        cb3.addActionListener(actionListener);
        cb4.addActionListener(actionListener);
        none.addActionListener(actionListener);
        none.setBounds(20,160,100,15);
        frame.add(cb1);
        frame.add(cb2);
        frame.add(cb3);
        frame.add(cb4);
        frame.add(none);

        //text field for commend 
        text.setBounds(20, 190,screenSize.width/2-40,20);
        frame.add(text);
        //frame setting 
        frame.setSize(screenSize.width/2, screenSize.height/2);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setVisible(true);
    }
    /**create an interface
      *@param default param 
      */
    public static void main(String[] args) throws IOException{
        GUI Uinterface = new GUI();
    }
    private Image readImage(File f) throws IOException{
        return ImageIO.read(f);
    }
    private void changeLabel(){
        if(current.size()>=1 && current.get(0)!=null) {      cb1.setText(current.get(0).getName()); }
        if(current.size()>=2 && current.get(1)!=null) {      cb2.setText(current.get(1).getName()); }
        if(current.size()>=3 && current.get(2)!=null) {      cb3.setText(current.get(2).getName()); }
        if(current.size()>=4 && current.get(3)!=null) {      cb4.setText(current.get(3).getName()); }
    }
    private class ActionHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            JCheckBox checkbox = (JCheckBox) event.getSource();
            if (checkbox.isSelected()) {
                if (checkbox == cb1)   be.checkboxB[0] = true;
                else if (checkbox == cb2) be.checkboxB[1] = true;
                else if (checkbox == cb3) be.checkboxB[2] = true;
                else if (checkbox == cb4) be.checkboxB[3] = true;
                else{ be.checkboxB[4] = true; }
            } else {
                if (checkbox == cb1)  be.checkboxB[0] = false;
                else if (checkbox == cb2) be.checkboxB[1] = false;
                else if (checkbox == cb3) be.checkboxB[2] = false;
                else if (checkbox == cb4) be.checkboxB[3] = false;
                else{ be.checkboxB[4] = false; }
            }
        }
    }
    /**
      *@Override
      */
    public void actionPerformed(ActionEvent a){
        String action = a.getActionCommand();
        if(action.equals("next")){
            //saving choose to excel
            try {
                String commend = text.getText();
                be.writeIn(commend);
            }catch (IOException e){}
            //getting next set
            cb1.setSelected(false);
            cb2.setSelected(false);
            cb3.setSelected(false);
            cb4.setSelected(false);
            none.setSelected(false);
            current = be.getNext();
            text.setText(be.getKey());
            if(current.isEmpty()){
                key.setText("Finish");
                cb1.setText("");
                cb2.setText("");
                cb3.setText("");
                cb4.setText("");
                text.setText("finish");
            }
            else            key.setText(be.getKey());
            changeLabel();
        }
    }

}
