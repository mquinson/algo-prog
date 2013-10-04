/* pyramide_parallele.c
 * Programme implementant l'algo vu en TD (un peu ammeliore)
 * de maniere à distibuer les calculs sur plusieurs machines.
 * On découpe pour cela le problème en tranches. *
 * Jlg - Janvier 2006
 * **************************************
 * Idées Martin Quinson:
 *  - lever l'unicite
 *  - autoriser 2* l'intervalle de base
 * **************************************
 */

#include <stdio.h>
#include <stdlib.h>
#include <time.h>

/****************************************
 * Macros
 ***************************************/
#define	OUT_OF_PYR	nb_elem
#define	Indice(lig,diag)	(((diag)*((diag)-1))/2+(lig)-1)
#define	TRUE	1
#define	FALSE	0

/****************************************
 * Variables globales
 ***************************************/

int hauteur;	/* nombre de lignes de la pyramide */
int nb_elem;	/* nombre d'elements dans la pyramide */
int *elements;	/* liste des elements de la pyramide */
int *place;		/* liste des places des elements dans la pyramide */
int progres;	/* progression dans le remplissage de la pyramide */
time_t start;	/* timestamp du début du calcul */
time_t stop;	/* timestamp de la fin */

/****************************************
 * Initialisation de la pyramide
 ***************************************/
void Init(int h)
{
	/* Variables*/
	int i;
	
	/* h doit etre strictement positif */
	if(h < 1) exit(3);
	
	/* Nombre d'elements de la pyramide */
	nb_elem = (h*(h+1))/2;
	
	/* Allocation de l'espace memoire */
	elements = (int*)malloc(nb_elem * sizeof(int));
	if(elements == NULL) exit(4);
	
	place = (int*)malloc(nb_elem * sizeof(int));
	if(place == NULL)
	{
		free(elements);
		elements = NULL;
		exit(5);
	}

	/* Initialisation des variables globales */
	hauteur = h;
	progres = 0;
	
	/* Place possible: de 0 a nb_elem-1
	 * En initialisant a nb_elem le test est simplifie */
	for(i=0; i<nb_elem; elements[i] = 0, place[i++] = OUT_OF_PYR);
	
	printf("Initialisation terminee\n");
}


/****************************************
 * Procedure qui quitte le programme
 ***************************************/
void Quit(int code)
{
	/* Liberation de l'espace memoire */
	free(elements);
	elements = NULL;
	
	free(place);
	place = NULL;

	/* Retour du code d'erreur */
	exit(code);
}


/****************************************
 * Fonction de calcul de l'indice d'une
 * case dans le tableau
 ***************************************/
/*
int Indice(int lig, int diag)
{
	//printf("Ligne %d, Diagonale %d -> Indice %d (nb_elem %d)\n", lig, diag, (diag*(diag-1))/2+lig-1, nb_elem);
	return ((diag*(diag-1))/2 + lig - 1);
}
*/


/****************************************
 * Fonction d'acces a une valeur de la
 * pyramide (parcours par diagonale)
 ***************************************/
inline int ValueAt(int lig, int diag)
{
	return elements[Indice(lig, diag)];
}


/****************************************
 * Procedure qui stocke une valeur
 * dans la pyramide. Gere la liste des
 * places
 ***************************************/
inline void SetValueAt(int val, int lig, int diag)
{
	/* Variables */
	int t,ind;

	/* S'il y a deja un nombre a cette case, on le retire de la liste des pris */
	t = ValueAt(lig, diag);
	ind = Indice(lig, diag);
	if((t != 0) && place[t-1] == ind)
		place[t-1] = OUT_OF_PYR;

	/* On place l'element dans la pyramide */
	elements[ind] = val;

	/* On le marque comme etant pris */
	place[val-1] = ind;

	/* Maintien de la liste des places */
	if(progres < ind+1) progres = ind+1;
}


/****************************************
 * Procedure d'affichage de la pyramide
 ***************************************/
void Display(void)
{
	/* Variables */
	int ligne, colonne, i, nb_blancs;
	
	/* Affichage de la pyramide */
	printf("Pyramide de hauteur %d\n", hauteur);
	for(ligne = 1; ligne <= hauteur; ligne++)
	{
		for(colonne = ligne; colonne <= hauteur; colonne++)
		{
			printf("%d ", ValueAt(ligne, colonne));
		}
		printf("\n");
		for(nb_blancs = 1; nb_blancs <= ligne; nb_blancs++, printf(" "));
	}

	/* Affichage de la liste des places */
	printf("Liste des places : ");
	for(i = 0; i < nb_elem; printf(" %d",place[i++]));
	printf("\n");
	
	/* Affichage du taux de remplissage */
	printf("Taux de remplissage: %f %% (indice max: %d)\n", (progres*100.0/nb_elem), progres);
}


/****************************************
 * Renvoie TRUE si la diagonale diag est
 * correcte jusqu'a la ligne lig
 * (Pas utilisée dans la génération)
 ***************************************/
int DiagonaleCorrecte(int lig, int diag)
{
	if(lig == 1) return TRUE;	/* Condition d'arret */
	if(ValueAt(lig, diag) != abs(ValueAt(lig-1, diag-1) - ValueAt(lig-1, diag)))
		return FALSE;
	return DiagonaleCorrecte(lig-1, diag);
}


/****************************************
 * Renvoie TRUE si la pyramide est
 * correctement construite jusqu'a
 * la diagonale diag.
 * (Pas utilisée dans la génération)
 ***************************************/
int CorrecteH(int diag)
{
	/* Recursivite portant sur les diagonales de 1 a diag */
	if(diag == 0) return TRUE;
	if(diag == 1) return DiagonaleCorrecte(diag, diag);
	else if(DiagonaleCorrecte(diag, diag)) return CorrecteH(diag-1);
	else return FALSE;
}


/****************************************
 * Fonction qui teste la correction
 * cad respecte les deux contraintes :
 * 	- contient tous les entiers entre 1 et nb_elem
 * 	- une case contient le nombre egal a la difference en valeur absolue
 * 	  des deux cases situees juste au dessus d'elle
 * (Pas utilisée dans la génération)
 ***************************************/
int Correcte(void)
{
	return CorrecteH(hauteur);
}


/****************************************
 * Renvoie TRUE si val est dans la pyramide
 * avant la position (lig, diag)
 ***************************************/
inline int Contains(int val, int lig, int diag)
{
	/* On regarde dans la table si val est place avant
	 * 1 <= val <= nb_elem */
	return(place[val-1] <= Indice(lig, diag));
}


/****************************************
 * Fonction qui tente de propager un elem
 * sur toute la diagonale
 ***************************************/
inline int Propager(int diag, int val)
{
	/* Variables */
	int lig, t;
	
	/* On place val en haut de la diagonale */
	SetValueAt(val, 1, diag);

	/* Cas trivial */
	if(diag == 1) return TRUE;

	/* On essaie de propager sur la diagonale */
	for(lig = 2; lig <= diag; lig++)
	{
		val = abs(ValueAt(lig-1, diag-1) - ValueAt(lig-1, diag));
		if(Contains(val, lig, diag))
			return FALSE;
		else
		{
			/* On place l'element */
			SetValueAt(val, lig, diag);

			/* Maintien de la liste des places */
			/*
			t = Indice(lig, diag);
			if(progres < t) progres = t+1;
			*/
		}
	}
	/* On a reussi a propager jusqu'en bas */
	return TRUE;
}


/****************************************
 * Fonction recursive qui remplit a
 * partir de la diagonale diag
 ***************************************/
int RemplirH(int diag)
{
	/* Variable */
	int i;
	
	/* Condition d'arret */
	if(diag > hauteur) return TRUE;

	/* Recursion */
	for(i = nb_elem; i >= 1; i--)
	{
		/* On essaie avec chaque valeur */
		if(!Contains(i, 1, diag))
		{
			if(Propager(diag, i))
			{
				if(RemplirH(diag+1)) return TRUE;
			}
		}
	}
	return FALSE;
}


/****************************************
 * Procedure qui remplit la pyramide
 * de facon correcte
 ***************************************/
void Remplir(int valeur)
{
	/* On mémorise l'instant de début */
	start = time(NULL);
	/* On met la valeur dans la première case */
	SetValueAt(valeur, 1, 1);
	/* On commence par la deuxième diagonale */
	RemplirH(2);
	/* On mémorise l'instant de fin */
	stop = time(NULL);
}


/****************************************
 * Point d'entree du programme
 ***************************************/
int main(int argc, char* argv[])
{
	/* Declaration des variables */
	int hauteur, valeur;
	
	/* Traitement des parametres passes au programme */
	if(argc < 3)
	{
		printf("Usage: ./pyramidep <hauteur> <valeur>\n");
		exit(1);
	}
	if(sscanf(argv[1], "%i", &hauteur) == -1) exit(2);
	if(sscanf(argv[2], "%i", &valeur) == -1) exit(2);

	if((valeur < 1) || (valeur > (hauteur*(hauteur+1))/2))
	{
		printf("Erreur, la valeur de la première case doit être comprise\n");
		printf("entre 1 et hauteur*(hauteur+1)/2 (%i pour une hauteur de %i)\n",
				(hauteur*(hauteur+1))/2, hauteur);
		exit(2);
	}
	
	/* Creation de la pyramide */
	Init(hauteur);
	Remplir(valeur);

	/* Affichage */
	Display();
	printf("Temps de calcul: %is\n", stop-start);
	
	/* On sort proprement */
	Quit(0);	
}
