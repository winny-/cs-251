# Edit these
PKG=Homework6
MAIN=Homework6.Homework6
TESTS=Homework6.Testing
# And edit these. Order matters. Don't use -j.
SRC=${PKG}/Window.java \
	${PKG}/Room.java \
	${PKG}/Apartment.java \
	${PKG}/Building.java \
	${PKG}/Homework6.java \
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

all: clean ${JARFILE} test

${JARFILE}: $(CLS)
	jar cfe ./${JARFILE} ${MAIN} ./${PKG}

test: ${JARFILE}
	java -cp ${CLASSPATH} org.junit.runner.JUnitCore ${TESTS}

clean:
	rm -f ${JARFILE}
#	rm -f $(CLS)
	rm -f ${PKG}/*.class

archive: all
	cd .. && rm -f ${ZIPFILE} && zip -r ${ZIPFILE} ${BUILDDIR}

.PHONY: clean test all archive

.SUFFIXES: .class .java
.java.class:
	$(JAVAC) $(JAVACFLAGS) $<
