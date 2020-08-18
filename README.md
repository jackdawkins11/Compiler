
# Pascal to mips compiler

A compiler, written in java, that compiles programs written in a subset of pascal to
mips assembly.

# Running it

It only runs on linux due to differences in how text files are encoded across operating systems. On linux, you can compile a program by running:

	java -jar production.jar program.pas

This will create a file called "output.asm" that can be run on a mips simulator/machine.
