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
    private ArrayList<File> current = new ArrayList<>();
    private boolean stopRecord = false;
    /**checkbox array
      */
    public ArrayList<Boolean> checkboxBoo = new ArrayList<>();
    public boolean isNone = false;
    /**constructor, call method to read images
     */
    public Backend(){   run();  }

    private void run() {
        File targetFile = new File("..\\pic");
        for( File f: targetFile.listFiles()) {
            theList.add(f);
        }
    }

    /**move on to next set of data
     *
     * @return pic ArrayList of files that share the same key
     */
    public ArrayList<File> getNext(){
        isNone = false;
        current = new ArrayList<>();
        checkboxBoo = new ArrayList<>();
        if(!theList.isEmpty()) {
            currentKey = theList.getFirst().getName().substring(0, 16);
            while (!theList.isEmpty() && theList.peek().getName().substring(0, 16).equals(currentKey)) {
                File temp = theList.remove();
                current.add(temp);
                readFile.add(temp);
            }
        }else   stopRecord = true;
        for(File f: current){
            checkboxBoo.add(false);
        }
        return current;
    }

    /**getter method for the stopRecord
     *
     * @return  stopRecord false if there is more data set, true if finish all
     */
    public boolean isStopRecord(){
        return stopRecord;
    }
    /**getter method for the current key
     *
     * @return  currentKey The key that represents current dataset
     */
    public String getKey(){  return currentKey; }

    /**check if any check boxs is checked
     * @return  result true if one or more check boxs are selected otherwise false
     */
    public boolean isAnyChecked(){
        boolean result = isNone;
        for(int i=0; i<checkboxBoo.size(); i++){
            result = result || checkboxBoo.get(i);
        }
        return result;
    }
    /** record the choice and comment , then write it into a .txt file using the tab delimited format
     *
     * @param comment comment about the choice or dataset
     * @throws IOException when file does not exist
     */
    public void writeIn(String comment) throws IOException {
        FileWriter writer = new FileWriter("..\\Result.txt", true);
        BufferedWriter bwrite = new BufferedWriter(writer);
        String result = currentKey;
        if(comment.equals(getKey()))    comment = "";
        File[] temp = new File[2];
        if(!isNone){     //if none is selected
            for(int i =0; i<checkboxBoo.size(); i++){
                if(temp[0] == null && checkboxBoo.get(i)) temp[0] = current.get(i);
                else if(temp[1] == null && checkboxBoo.get(i)) temp[1] = current.get(i);
            }
            for(int i =0; i<2; i++){
                String name ="";
                if(temp[i] != null)  name = temp[i].getName().substring(0,28);
                result += "\t"+ name;
            }
        }
        else{   result+="\t"+"none"+"\t";}  //if none is not selected
        result +="\t"+comment;
        System.out.println(result);
        bwrite.write(result);
        bwrite.newLine();
        bwrite.close();
    }

    /**open the first image of the current set of data
     *
     * @throws IOException when file does not exist
     */
    public void openImage() throws IOException{
        String fileName = "..\\pic\\" + current.get(0).getName();
        String [] commands = {
                "cmd.exe" , "/c", "start" , "\"DummyTitle\"", "\"" + fileName + "\""
        };
        Runtime.getRuntime().exec(commands);
    }

}

