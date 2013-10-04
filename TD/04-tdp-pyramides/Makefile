TARGET=$(shell ls *.tex |grep -v enonce.tex|grep -v correction.tex|sed 's/\.tex$$//')
DISTDIR=/home/mquinson/wiki/Teaching/TOP/

all: pdf

### Publication

publish: $(TARGET)-enonce.pdf $(TARGET)-correction.pdf
	cp $^ $(DISTDIR)


### LaTeX

enonce:
	echo \\documentclass[10pt]{article}\\usepackage[nu]{esial} > $(TARGET)-enonce.tex
	grep -v documentclass $(TARGET).tex >> $(TARGET)-enonce.tex
	$(MAKE) $(TARGET)-enonce.pdf
	rm -rf `ls $(TARGET)-enonce.*|grep -v '^$(TARGET)-enonce.pdf$$'`

correction:
	echo \\documentclass[10pt]{article}\\usepackage[correction,nu]{esial} > $(TARGET)-correction.tex
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

### Sources

enonce-src:
	rm -rf dist
	mkdir -p dist/pyramide
	iconv -f utf8 -t latin1 pyramide/Pyramide.java | ./killparts > dist/pyramide/Pyramide.java
	iconv -f utf8 -t latin1 pyramide/Test.java | ./killparts > dist/pyramide/Test.java
	cd dist ; tar cvfz pyramide-enonce.tar.gz pyramide
	mv dist/pyramide-enonce.tar.gz .

#publish:: enonce-src
#	cp pyramide-enonce.tar.gz $(DISTDIR)

%.class: %.java
	jmlc -Q $<

test: pyramide/Test.class pyramide/Pyramide.class
	time jmlrac pyramide.Test 5
	time jmlrac pyramide.Test 6

clean:: 
	rm -f pyramide/*.class pyramide/*~