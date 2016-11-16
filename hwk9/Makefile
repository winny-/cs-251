# Edit these
PKG=Homework9
MAIN=Homework9.Homework9
TESTS=Homework9.Testing
# And edit these. Order matters. Don't use -j.
SRC=${PKG}/Homework9.java \
	${PKG}/Testing.java

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

all: ${JARFILE} #test

${JARFILE}: $(CLS)
	jar cfe ./${JARFILE} ${MAIN} ./${PKG}

test: ${JARFILE}
	java -cp ${CLASSPATH} org.junit.runner.JUnitCore ${TESTS}

clean:
	rm -f ${JARFILE}
	rm -f $(CLS)

run: ${JARFILE}
	java -cp ${CLASSPATH} -jar ${JARFILE}

archive: all
	cd .. && rm -f ${ZIPFILE} && zip -r ${ZIPFILE} ${BUILDDIR}

.PHONY: clean test all archive run

.SUFFIXES: .class .java
.java.class:
	$(JAVAC) $(JAVACFLAGS) $<
