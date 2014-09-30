#               TD/01-td-complexite/01-td-complexite-enonce.pdf 
TOP-repro.pdf: Lecture/TOP-handout.4up.pdf \
	       TD/02-td-recursivite/02-td-recursivite-enonce.pdf \
	       TD/03-tdp-dicho-knapsack/03-td-dicho-knapsack-enonce.pdf \
	       TD/04-tdp-pyramides/04-tdp-pyramide-enonce.pdf \
	       TD/05-tdp-recipients/05-tdp-recipients-enonce.pdf \
	       TD/06-td-derecursivation/06-td-transfo-enonce.pdf \
	       TD/07-td-preuve/07-td-preuve-enonce.pdf
	pdfjoin $^ --outfile TOP-repro.pdf
	       
CM/%:
	pwd;make -C $(@D) $*
TD/%:
	make -C $(@D) $*
		
	       
	       



						  