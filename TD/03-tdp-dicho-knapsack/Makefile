TARGET=$(shell ls *.tex |grep -v enonce.tex|grep -v correction.tex|sed 's/\.tex$$//')
DISTDIR=/home/mquinson/wiki/Teaching/TOP/

all: pdf

publish: $(TARGET)-enonce.pdf $(TARGET)-correction.pdf scala/knapsack.jar scala/knapsack.scala
	cp $^ $(DISTDIR)
	chmod +x $(DISTDIR)/knapsack.jar
	scp $(TARGET)-enonce.pdf scala/knapsack.jar scala/knapsack.scala neptune:/home/depot/1A/TOP/TD3-knapsack
	ssh neptune "chmod +x /home/depot/1A/TOP/TD3-knapsack/knapsack.jar"

scala/knapsack.jar: scala/knapsack-solution.scala
	cd scala ; ant jar

scala/knapsack.scala: scala/knapsack-solution.scala
	cd scala ; ant template

enonce:
	echo \\documentclass[10pt]{article}\\usepackage{esial} > $(TARGET)-enonce.tex
	grep -v documentclass $(TARGET).tex >> $(TARGET)-enonce.tex
	$(MAKE) $(TARGET)-enonce.pdf
	rm -rf `ls $(TARGET)-enonce.*|grep -v '^$(TARGET)-enonce.pdf$$'`

correction:
	echo \\documentclass[10pt]{article}\\usepackage[correction]{esial} > $(TARGET)-correction.tex
	grep -v documentclass $(TARGET).tex >> $(TARGET)-correction.tex
	$(MAKE) $(TARGET)-correction.pdf
	rm -rf `ls $(TARGET)-correction.*|grep -v '^$(TARGET)-correction.pdf$$'`

%.pdf: %.txt
	@echo Rebuild $@ from $^
	a2ps $^ -o - | ps2pdf - $@

	
%_2.pdf: %.pdf	
	a2ps $^ -o - | ps2pdf - $@

%.ps: %.txt
	@echo Rebuild $@ from $^
	iconv -f utf8 -t latin1 $^ > $^.latin1
	a2ps  $^.latin1 -o $@
	rm $^.latin1

%_2.ps: %.ps
	a2ps $^ -o $@

include /usr/include/LaTeX.mk 

clean:: 
	cd scala ; ant clean
	rm -f *.class *~