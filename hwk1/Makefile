PACKAGE = Homework1
PROGRAM = Homework1
MAIN = ${PACKAGE}.${PROGRAM}

JUNIT = /usr/share/java/junit.jar
HAMCREST = /usr/share/java/hamcrest-core.jar
CLASSPATH = .:${JUNIT}:${HAMCREST}
JAVAC_FLAGS = -cp ${CLASSPATH}

BUILDDIR = $(notdir $(shell pwd))
ZIPFILE = ${BUILDDIR}.zip

all: ${PROGRAM}.jar test

${PROGRAM}.jar: ${PACKAGE}/${PROGRAM}.class
	jar cfe ${PROGRAM}.jar ${MAIN} ${PROGRAM}

${PACKAGE}/%.class: %.java
	javac -d . ${JAVAC_FLAGS} $<

test: ${PACKAGE}/${PROGRAM}Test.class
	java -cp ${CLASSPATH} org.junit.runner.JUnitCore ${PACKAGE}.${PROGRAM}Test

archive: all
	cd .. && rm -f ${ZIPFILE} && zip -r ${ZIPFILE} ${BUILDDIR}

clean:
	rm -rf ${PROGRAM}.jar ${PACKAGE}

.PHONY: clean archive test

