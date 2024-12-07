#===========================================================================================#
# NanoShell, Simple Java UNIX shell.
# Simple, but useful!
#=[Options]=================================================================================#
JAVAC=javac
M_PATH=com/HappyCow
MAIN_CLASS=com.HappyCow.NanoShell.NanoShell
#===========================================================================================#

.PHONY: build run runJar clean

build:
	@$(info Compiling NanoShell and building JAR...)
	@$(JAVAC) $(M_PATH)/MiniNanoShell/*.java

	@jar cfe NanoShell.jar \
	$(M_PATH)/MiniNanoShell/MiniNanoShell \
	$(M_PATH)/MiniNanoShell/*.class \
	$(M_PATH)/ShellUtilities/*.class

run:
	$(info Running...)
	@java $(MAIN_CLASS)

runJar:
	$(info Running JAR...)
	@java -jar NanoShell.jar

clean:
	$(info Cleaning up...)
	@rm -f $(M_PATH)/NanoShell/*.class $(M_PATH)/ShellUtilities/*.class NanoShell.jar
