PACKAGE = Lab1
PROGRAM = Lab1
MAIN = ${PACKAGE}.${PROGRAM}

BUILDDIR = $(notdir $(shell pwd))
ZIPFILE = ${BUILDDIR}.zip

all: ${PROGRAM}.jar

${PROGRAM}.jar: ${PACKAGE}/${PROGRAM}.class
	jar cfe ${PROGRAM}.jar ${MAIN} ${PROGRAM}

${PACKAGE}/%.class: %.java
	javac -d . $<

archive: all
	cd .. && rm -f ${ZIPFILE} && zip -r ${ZIPFILE} ${BUILDDIR}

clean:
	rm -rf ${PROGRAM}.jar *.class Lab1

.PHONY: clean archive

