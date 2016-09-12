PROGRAM = Homework1

${PROGRAM}.jar: ${PROGRAM}.class
	jar cfe ${PROGRAM}.jar ${PROGRAM} ${PROGRAM}.class

%.class: %.java
	javac $<

clean:
	rm -f ${PROGRAM}.jar *.class

.PHONY: clean
