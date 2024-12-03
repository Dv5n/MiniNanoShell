#===========================================================================================#
# NanoShell, Simple Java UNIX shell.
# Simple, but useful!
#=[Options]=================================================================================#
JAVAC=javac
M_PATH=com/HappyCow
MAIN_CLASS=com.HappyCow.NanoShell.NanoShell
#===========================================================================================#

.PHONY: build run runJar clean

build: $(CLASS)
	@$(info Compiling NanoShell and building JAR...)
	@$(JAVAC) $(M_PATH)/NanoShell/*.java

	@jar cfe NanoShell.jar \
	$(M_PATH)/NanoShell/NanoShell \
	$(M_PATH)/NanoShell/*.class \
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
