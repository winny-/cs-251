# Edit these
PKG=Homework11
MAIN=Homework11.Homework11
TESTS=Homework11.Testing
# And edit these. Order matters. Don't use -j.
SRC=${PKG}/State.java \
	${PKG}/Cell.java \
	${PKG}/GameStatsPanel.java \
	${PKG}/Grid.java \
	${PKG}/GameControllerPanel.java \
	${PKG}/Homework11.java

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

all: ${JARFILE}

${JARFILE}: $(CLS)
	jar cfe ./${JARFILE} ${MAIN} ./${PKG}

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
