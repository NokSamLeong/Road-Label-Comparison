/**backend prgram for the road comparison task 
  *@Author NokSam Leong
  *@Version 02/28/2020
  */
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.io.BufferedWriter;

public class Backend {
    private String currentKey="";
    private LinkedList<File> theList = new LinkedList<>();
    private LinkedList<File> readFile = new LinkedList<>();
    private ArrayList<File> current = new ArrayList<>(5);
    /**checkbox array
      */
    public boolean[] checkboxB = new boolean[5];

    public void writeIn(String commend) throws IOException {
        FileWriter writer = new FileWriter("..\\Result.txt", true);
        BufferedWriter bwrite = new BufferedWriter(writer);
        String result = currentKey;
        if(commend.equals(getKey()))    commend = "";
        File[] temp = new File[2];
        for(int i =0; i<5; i++){
            if(temp[0] == null && checkboxB[i]) temp[0] = current.get(i);
            else if(temp[1] == null && checkboxB[i]) temp[1] = current.get(i);
        }
        for(int i =0; i<2; i++){
            String name ="";
            if(temp[i] != null)  name = temp[i].getName();
            result += "\t"+ name;
        }
        result +="\t"+commend;
        System.out.println(result);
        bwrite.write(result);
        bwrite.newLine();
        bwrite.close();
    }
    public Backend(){run();}
    public void run() {
        File targetFile = new File("..\\pic");
        for( File f: targetFile.listFiles()) {
            theList.add(f);
        }
    }
    public ArrayList<File> getNext(){
        ArrayList<File> pic = new ArrayList<>();
        if(!theList.isEmpty()) {
            currentKey = theList.getFirst().getName().substring(0, 16);
            while (!theList.isEmpty() && theList.peek().getName().substring(0, 16).equals(currentKey)) {
                File temp = theList.remove();
                pic.add(temp);
                readFile.add(temp);
            }
        }
        current = pic;
        checkboxB = new boolean[5];
        return pic;
    }
    public LinkedList<File> debug(){
        return theList;
    }
    public String getKey(){  return currentKey; }


}

