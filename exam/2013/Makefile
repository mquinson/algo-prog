TARGET=$(shell ls *.tex |grep -v enonce.tex|grep -v correction.tex|sed 's/\.tex$$//')
all: pdf

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

include /usr/include/LaTeX.mk 
