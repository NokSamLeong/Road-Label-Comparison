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
import java.util.*;

public class GUI implements ActionListener{
    private Backend be = new Backend();
    private ArrayList<File> current = be.getNext();
    private ArrayList<JCheckBox> checkboxList = new ArrayList<>(  
                                                Arrays.asList(new JCheckBox(), new JCheckBox(), new JCheckBox(), new JCheckBox()));
    private JCheckBox none = new JCheckBox("none");
    private JLabel key = new JLabel(be.getKey());
    private JTextField textField = new JTextField(be.getKey());

    /**create an interface
     *@param default param
     */
    public static void main(String[] args) throws IOException{
        GUI Uinterface = new GUI();
    }
    /**constructor
      */
    public GUI(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        //create frame
        JFrame frame = new JFrame();

        key.setBounds(340,10,300,20);
        frame.add(key);
        //button
        JButton b1 = new JButton("Open " );
        b1.setBounds(screenSize.width/4-105, 40,100,30);
        b1.setActionCommand("open");
        b1.addActionListener(this);
        JButton b2 = new JButton("Next");
        b2.setBounds(screenSize.width/4+5,40,100,30);
        b2.setActionCommand("next");
        b2.addActionListener(this);
        frame.add(b1);
        frame.add(b2);

        //checkbox
        ActionListener actionListener = new ActionHandler();
        for(int i=0; i < checkboxList.size(); i++){
            checkboxList.get(i).setBounds(20,i*30+100, 300, 15);
            checkboxList.get(i).setActionCommand(i+"");
            checkboxList.get(i).addActionListener(actionListener);
            frame.add(checkboxList.get(i));
        }
        changeLabel();
        none.addActionListener(actionListener);
        none.setBounds(20,220,300,15);
        none.setActionCommand("none");
        frame.add(none);

        //text field for comment
        textField.setBounds(20, 250,screenSize.width/2-40,20);
        frame.add(textField);
        //frame setting 
        frame.setSize(screenSize.width/2, screenSize.height/2);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    private void changeLabel(){
        for(int i=0; i<checkboxList.size() ; i++){
            if(i < current.size())   checkboxList.get(i).setText(current.get(i).getName().substring(0,28));
            else checkboxList.get(i).setText("");
        }
    }
    private void errorFrame(String alert){
                JFrame fail = new JFrame();
                JLabel temp = new JLabel(alert);
                temp.setBounds(20,20, 300,40);
                fail.setSize(500,150);
                fail.setLocation(100,100);
                fail.setLayout(null);
                fail.add(temp);
                fail.setVisible(true);
    }

    /**
      *@Override
      */
    public void actionPerformed(ActionEvent a){
        String action = a.getActionCommand();
        if(action.equals("next") && !be.isStopRecord()){
            if(!be.isAnyChecked())  {// if no checkbox including none is checked
                errorFrame("Please select none or at least one image");
            }else{
            //saving choose to excel
                  try {
                      String comment = textField.getText();
                      be.writeIn(comment);
                  }catch (IOException e){}
                  //reset checkboxs
                  for(JCheckBox cb:checkboxList){
                     cb.setSelected(false);
                  }
                  none.setSelected(false);
                  //getting next set
                  current = be.getNext();
                  if(current.isEmpty()){
                      key.setText("Finish");
                      for(JCheckBox cb:checkboxList){
                           cb.setText("");
                      }
                      textField.setText("finish");
                  }
                  else{
                      key.setText(be.getKey());
                      textField.setText(be.getKey());
                      changeLabel();
                  }
            }
        }
        else if(action.equals("open")){
            try {
                be.openImage();
            }catch (Exception e){
                errorFrame("Fail to open");
            }
        }
    }
    private class ActionHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            JCheckBox checkbox = (JCheckBox) event.getSource();
            if (checkbox.isSelected()){
               if(checkbox.getActionCommand().equals("none"))   be.isNone = true;
               else if(Integer.parseInt(checkbox.getActionCommand()) < be.checkboxBoo.size())
                  be.checkboxBoo.set(Integer.parseInt(checkbox.getActionCommand()), true);
            }
            else{
               if(checkbox.getActionCommand().equals("none"))   be.isNone = false;
               else if(Integer.parseInt(checkbox.getActionCommand()) < be.checkboxBoo.size())
                  be.checkboxBoo.set(Integer.parseInt(checkbox.getActionCommand()), false);
            }
        }
    }
}
