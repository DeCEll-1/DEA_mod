SafariJohn’s Rules Tool (SRT) is an editor for modifying dialog rules (rules.csv) for Starsector, an RPG. It makes creating dialog trees easy with powerful features like automatic link detection.

Forum Thread: http://fractalsoftworks.com/forum/index.php?topic=13976.0

----------------
Installation
----------------
Windows:
Simply extract the .zip file to wherever you like. Double click SRT.exe to run the program.

If the program doesn't find a Java Runtime Environment, it will prompt you to download one. Alternatively, you can copy the "jre" folder from your Starsector installation to SRT's folder.


Linux:
Extract the .zip file to wherever you like. Set SRT.sh executable. You can now run SRT.sh to run the program.

If you need to customize SRT.sh, note that the program assumes the JRE's working directory to be SRT's folder, not the jar's folder.


Mac:
Installing on Mac is not officially supported yet, however, it should be the same as for Linux.


-------------------
Getting Started
-------------------
The first time you start SafariJohn's Rules Tool, it will ask you where your Starsector/mods folder is. This is for your convenience only – cancelling will not harm anything.

To get started, you need to create a new ruleset or load your rules.csv file. If you've used SRT before version 2.0, you can convert your saved rulesets to the new format instead.

Create a New Ruleset:
To create a new, empty set of rules, click the File menu, then click New. The program will prompt you to name the new ruleset, then it will appear below the Vanilla folder.

Load rules.csv:
To load an existing rules.csv file, click the File Menu, click Open, then find and select the desired rules.csv. If successful, the program will prompt you to name the ruleset. It will then appear below the Vanilla folder.

Convert Pre-2.0 Ruleset:
To convert a saved pre-2.0 ruleset, click the File menu, then click Convert. The program should open a dialog showing your SRT/rules folder - select one of the folders and click Open. The program will then ask you where to save the rules as a .csv file. Finally, SRT will load the ruleset.


Now that you have something to work with, you can start adding, removing, and editing rules. If you are unfamiliar with how rules work, click the View menu, then Scripting Documentation. You should also study the vanilla rules, as well as other mods' rules.

Adding:
To add a rule/folder, first select a rule or folder on the left side, then press the respective button (R for rules, D for directories/folders) at the top to add a rule/folder to the folder or rule's folder.

Editing:
Editing rules is as easy as selecting the rule on the left, then typing in the boxes in the center. You can switch to a specific tab (trigger/conditions/etc.) to see more tools for editing that part of the rule.

Reordering/Copying:
You can reorder rules/folders and move them to different folders by dragging and dropping on the Rules tree. To copy instead of move, hold down the control key when you release the mouse button.

Deleting:
To delete a rule or folder, select it and press the "delete" key on your keyboard.


That's the basics, have fun!


------------------------
Spellcheck Languages
------------------------
The following languages are supported for spellchecking:
Arabic
Dutch
English
French
German
Italian
Polish
Russian
Spanish

You can download dictionaries for these languages at:
https://sourceforge.net/projects/jortho/files/Dictionaries/2013-03/

Copy dicitonary_**.ortho from the zip file and place it in your SRT/spellcheck folder.


-------------------
Troubleshooting
-------------------
There are many ways a program can go wrong. If one of the sections below doesn't help you, post your problem on the forum thread.

Silent crash:
Check SRT.log and post on the forum thread with the last few dozen lines and an explanation of what you were doing when the program crashed.

Failed to Load (Illegal Arguments):
If you are lucky, you simply clicked the wrong file. Otherwise, your rules.csv is formatted incorrectly.

If the error message said something about columns, you have extra columns in your rules.csv. Open it in a spreadsheet editor like Excel, fix any of your rules that have offset themselves past the notes column, then delete/clear the extra columns.

Failed to Load (Nesting):
If you see this error, your rules.csv has too many comment lines in a row. The program treats each comment as the beginning of a folder, and each blank line before a comment as the end of a folder. Just add a blank line before each offending comment to solve the problem.


----------------
Legal Notice
----------------
Copyright (C) 2018-2022 SafariJohn
 
SafariJohn's Rules Tool is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.

SafariJohn's Rules Tool is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.

You should have received a copy of the GNU General Public License along with SafariJohn's Rules Tool.  If not, see <https://www.gnu.org/licenses/>.