all : install build-compiler build-interpreter
	@echo "Build complete"
install: 
	mkdir bin
	mkdir input
	mkdir output
	touch input/input.spp

clean:
	rm input/input.spp
	rm -Rf bin
	rm -Rf output
	rm -Rf input

build-compiler: 
	@#mycp=$(printf '"' && find src -type d -exec printf '%s:' {} + && printf '"')
	find . -name "*.java" >sources.txt
	javac -d bin -cp lib/antlr4-4.6-complete.jar:src/ @sources.txt
	cd bin && \
	rm -f Compiler.mf ; \
	echo "Manifest-Version: 1.0" >>Compiler.mf && \
	echo "Main-Class: main.Compiler" >>Compiler.mf && \
	echo "Class-Path: ../lib/antlr4-4.6-complete.jar" >>Compiler.mf && \
	find . -name "*.class" >binaries.txt && \
	jar cfm Compiler.jar Compiler.mf @binaries.txt ; \
	rm -f binaries.txt ; \
	cd ..
	rm -f sources.txt

build-interpreter: 
	find . -name "*.java" >sources.txt
	javac -d bin -cp lib/antlr4-4.6-complete.jar:src/ @sources.txt
	cd bin && \
	rm -f Interpreter.mf ; \
	echo "Manifest-Version: 1.0" >>Interpreter.mf && \
	echo "Main-Class: main.VM" >>Interpreter.mf && \
	echo "Class-Path: ../lib/antlr4-4.6-complete.jar" >>Interpreter.mf && \
	find . -name "*.class" >binaries.txt && \
	jar cfm Interpreter.jar Interpreter.mf @binaries.txt ; \
	rm binaries.txt ; \
	cd ..
	rm sources.txt

help : 
	@#Char '@' suppress print of command itself
	@echo "SimplePlus Compiler and Interpreter v1.0"
	@echo "Run this Makefile from its own directory\n"
	@echo "To quick build the project run 'make all'"
	@echo "Then write the source in input/input.spp "
	@echo "Then run 'make run-compiler' to compile bytecode"
	@echo "Finally run 'make run-interpreter' to execute bytecode"
	@echo "\nOtherwise :\n"
	@echo "Run 'make install' to set the Environment"
	@echo "Run 'make build-compiler' to build Compiler"
	@echo "Run 'make build-interpreter' to build Interpreter"
	@echo "Run 'make clean' to clean up the Environment"
	@echo "Run 'make run-compiler' to compile input/input.spp to bytecode"
	@echo "Run 'make run-interpreter' to execute output/out.simple"
	@echo "Run 'make help' to print this help message"
run-compiler :
	cd bin && \
	java -jar Compiler.jar ../input/input.spp

run-interpreter:
	cd bin && \
	java -jar Interpreter.jar ../output/output.simple 2>../output/programERR