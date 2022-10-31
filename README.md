## PaddleFake 
Fake — a very simple build system with an elementary support of incremental task execution.

### Example file

```
compile:
	dependencies:
		- main.c
	target: main.o
	run: gcc -c main.c -o main.o

build:
	dependencies:
		- compile
	target: main
	run: gcc main.o -o main
```

### Run examples

To run a default build process from file use

```
java -jar Fake.jar <filepath>
```

To run a specific task from a file use

```
java -jar Fake.jar <filepath> <taskname>
```

### Special tasks

```
clean
```

Executes the first. Also you can always execute it from cmd within it doesn't exist in file. Removes all targets from file.
