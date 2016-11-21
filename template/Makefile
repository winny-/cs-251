# Edit these
PKG=Template
MAIN=Template.Template
TESTS=Template.TemplateTests
# And edit these. Order matters. Don't use -j.
SRC=Template/Template.java

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

all: ${JARFILE} test

${JARFILE}: $(CLS)
	jar cfe ./${JARFILE} ${MAIN} ./${PKG}

test: ${JARFILE}
	java -cp ${CLASSPATH} org.junit.runner.JUnitCore ${TESTS}

run: ${JARFILE}
	java -jar ${JARFILE}

clean:
	rm -f ${JARFILE}
	rm -f $(CLS)

archive: all
	cd .. && rm -f ${ZIPFILE} && zip -r ${ZIPFILE} ${BUILDDIR}

.PHONY: clean test all archive run

.SUFFIXES: .class .java
.java.class:
	$(JAVAC) $(JAVACFLAGS) $<
