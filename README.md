
This software is a compiler. It takes as input
a file containing pascal code and outputs a file
containing mips code.

For example, to compile a file containing
pascal code called "program.pas":

	java -jar production.jar program.pas

This will create a file called "output.asm"
that can be run on a mips simulator/machine.

I use the QtSpim simulator(https://sourceforge.net/projects/spimsimulator/files/).
This simulator is a little weird to use. Here are the steps I take when I use it:
(1) Run the QtSpim application. This will open two windows. One titled "Console" and
one titled "QtSpim".
(2) Click the "Load File" button in the "File" drop down menu and then select the
file with mips code you want to run.
(3) In the "simulator" drop down menu, click "run/continue". This will run the program,
which will use the "Console" window for input and output.