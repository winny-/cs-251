# Edit these
PKG=Lab10
MAIN=Lab10.Lab10
TESTS=Template.TemplateTests
# And edit these. Order matters. Don't use -j.
SRC=${PKG}/Lab10.java \
	${PKG}/Keypad.java

##############################################################################

JUNIT = /usr/share/java/junit.jar
HAMCREST = /usr/share/java/hamcrest-core.jar
CLASSPATH = .:${JUNIT}:${HAMCREST}

JAVAC = javac
JAVACFLAGS = -g -cp ${CLASSPATH}

BUILDDIR = $(notdir $(shell pwd))
ZIPFILE = ${BUILDDIR}.zip

JARFILE=${PKG}.jar

CLS=$(SRC:.java=.class)

all: ${JARFILE} run #test

${JARFILE}: $(CLS)
	jar cfe ./${JARFILE} ${MAIN} ./${PKG}

test: ${JARFILE}
	java -cp ${CLASSPATH} org.junit.runner.JUnitCore ${TESTS}

run: ${JARFILE}
	java -cp ${CLASSPATH} -jar ${JARFILE}

clean:
	rm -f ${JARFILE}
	rm -f $(CLS)

archive: all
	cd .. && rm -f ${ZIPFILE} && zip -r ${ZIPFILE} ${BUILDDIR}

.PHONY: clean test all archive run

.SUFFIXES: .class .java
.java.class:
	$(JAVAC) $(JAVACFLAGS) $<
