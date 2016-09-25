JAVAC = javac
JAVACFLAGS =
SRC=$(wildcard TestPackage/*.java)
CLS=$(SRC:.java=.class)

TestPackage.jar: $(CLS)
	jar cfe ./TestPackage.jar TestPackage.TestModule ./TestPackage

clean:
	rm -f TestPackage.jar
	rm -f $(CLS)

.SUFFIXES : .class .java
.java.class :
	$(JAVAC) $(JAVACFLAGS) $<
