# Edit these
PKG=CheckHomework8
MAIN=CheckHomework8.CheckHomework8
TESTS=CheckHomework8.CheckHomework8
# And edit these. Order matters. Don't use -j.
SRC=CheckHomework8/CheckHomework8.java

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

clean:
	rm -f ${JARFILE}
	rm -f $(CLS)

archive: all
	cd .. && rm -f ${ZIPFILE} && zip -r ${ZIPFILE} ${BUILDDIR}

.PHONY: clean test all archive

.SUFFIXES: .class .java
.java.class:
	$(JAVAC) $(JAVACFLAGS) $<
