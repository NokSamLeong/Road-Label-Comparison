Road Label Comparison UserInterface 
by Nok Sam Leong 
First version 02/29/2020
Last update 03/04/2020

Make sure JDK and Java are installed
You can find it in the install file
If error occures when you try to run the roadLabeling.jar, reinstall JDK12 (\install\jdk-12.0.2_windows-x64_bin) 

Program path: src\roadLabeling.jar
Image path: pic

The class files are hidden in the src file. 
To review it, go to View(top bar) and check the hidden items.
The program was specifically designed for the road label comparison task.
At least 1 and at most 4 different images for each key. 

Instruction
Non-labeled checkbox is considered to be an invalid selection
Put images in the pic file. Recommand to put less than 200 images each time.
Open button opens the first image in the current set in the default application.
Maximum selection for each key is 2 labels.
If None is selected, no labels will be marked even if they are selected.
Do not need to delete the key string in the comment field. It is there for convenience.
Make sure to delete the images which you already give judgement to.
The result is saved as txt file. data is seperated by tab.

Update
03/02/2020	corrected documentation
		added function
			-open image
			-stop writing in when finish
03/04/2020	next button is disabled when no valid selection.
		non-labeled checkbox is considered to be aninvalid selection
to-do		go back button
		security
