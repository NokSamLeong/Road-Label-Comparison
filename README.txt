Road Label Comparison UserInterface 
by Nok Sam Leong 
first version 02/29/2020
last update 03/02/2020

Make sure JDK and Java are installed
You can find it in the install file
if error occures when you try to run the roadLabeling.jar, reinstall JDK12 (\install\jdk-12.0.2_windows-x64_bin) 

program path: src\roadLabeling.jar

The class files are hidden in the src file. 
to review it, go to View(top bar) and check the hidden items.
The program was specifically designed for the road label comparison task.
and at most 4 different kind of labels for each key. 

Instruction
Open button opens the first image in the current set in the default application.
Maximum selection for each key is 2 labels.
if None is selected, no labels will be marked even if they are selected.
Do not need to delete the key string in the comment field. It is there for convenience.
Do not hit next if it shows finish, otherwise it will insert faulty data into the file.
Make sure to delete the images which you already give judgement to.
The result is saved as txt file. data is seperated by tab.

Update
03/02/2019	corrected documentation
		added function
			-open image
			-stop writing in when finish
		