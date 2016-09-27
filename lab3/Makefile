JUNIT = /usr/share/java/junit.jar
HAMCREST = /usr/share/java/hamcrest-core.jar
CLASSPATH = .:${JUNIT}:${HAMCREST}

JAVAC = javac
JAVACFLAGS = -cp ${CLASSPATH}
#SRC=$(wildcard Lab3/*.java)
SRC=Lab3/Lab3.java \
	Lab3/Lab3Testing.java
CLS=$(SRC:.java=.class)

all: Lab3.jar test

Lab3.jar: $(CLS)
	jar cfe ./Lab3.jar Lab3.Lab3 ./Lab3

test: Lab3.jar
	java -cp ${CLASSPATH} org.junit.runner.JUnitCore Lab3.Lab3Testing

clean:
	rm -f Lab3.jar
	rm -f $(CLS)

.PHONY: clean test

.SUFFIXES : .class .java
.java.class :
	$(JAVAC) $(JAVACFLAGS) $<
