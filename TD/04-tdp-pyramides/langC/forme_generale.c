/* pyramide_parallele.c
 * Programme implementant l'algo vu en TD (un peu ammeliore)
 * de maniere a distibuer les calculs sur plusieurs machines.
 * On decoupe pour cela le problème en tranches. *
 * Jlg - Janvier 2006
 * **************************************
 * Idees Martin Quinson:
 *  - lever l'unicite
 *  - autoriser 2* l'intervalle de base
 * **************************************
 * Solutions Ã  la fin
 */

#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <limits.h>

/****************************************
 * Macros
 ***************************************/
#define	OUT_OF_PYR	INT_MAX
#define	Indice(lig,diag)	(((diag)*((diag)-1))/2+(lig)-1)
#define	TRUE	1
#define	FALSE	0

/****************************************
 * Variables globales
 ***************************************/

int height;	/* line count in pyramid */
int elm_count;	/* number of the biggest element */
int pyr_size; /* count of positions in the pyramid */
int *elements;	/* liste des elements de la pyramide */
int *place;		/* liste des places des elements dans la pyramide */
int progres;	/* progression dans le remplissage de la pyramide */

/****************************************
 * Fonction d'acces a une valeur de la
 * pyramide (parcours par diagonale)
 ***************************************/
inline int ValueAt(int lig, int diag) {
   return elements[Indice(lig, diag)];
}


/****************************************
 * Procedure qui stocke une valeur
 * dans la pyramide. Gere la liste des
 * places
 ***************************************/
inline void SetValueAt(int val, int lig, int diag) {
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
void Display(void) {
   /* Variables */
   int ligne, colonne, i, nb_blancs;
   
   /* Affichage de la pyramide */
   printf("\n\nPyramid of height %d\n", height);
   for(ligne = 1; ligne <= height; ligne++)
     {
	for(colonne = ligne; colonne <= height; colonne++)
	  {
	     printf("%d ", ValueAt(ligne, colonne));
	  }
	printf("\n");
	for(nb_blancs = 1; nb_blancs <= ligne; nb_blancs++, printf(" "));
     }
   
   /* Affichage de la liste des places */
   printf("\nIgnored elements: ");
   for(i = 0; i < pyr_size; i++)
      if (place[i] == OUT_OF_PYR)
	printf(" %d",i+1);
      
   printf("\n");
}

/****************************************
 * Renvoie TRUE si val est dans la pyramide
 * avant la position (lig, diag)
 ***************************************/
inline int Contains(int val, int lig, int diag) {
   /* On regarde dans la table si val est placee avant la pos courante */
   return (place[val-1] <= Indice(lig, diag));
}

/****************************************
 * Fonction qui tente de propager un elem sur toute la diagonale
 ***************************************/
inline int Propager(int diag, int val) {
   int lig;
   
   /* On place val en haut de la diagonale */
   SetValueAt(val, 1, diag);
   
   /* Cas trivial */
   if(diag == 1) return TRUE;
   
   /* On essaie de propager sur la diagonale */
   for(lig = 2; lig <= diag; lig++) {
      val = abs(ValueAt(lig-1, diag-1) - ValueAt(lig-1, diag));
      if (Contains(val, lig, diag)) {	 
	 return FALSE;
      } else {
	 /* On place l'element */
	 SetValueAt(val, lig, diag);
      }
   }
   /* On a reussi a propager jusqu'en bas */
   return TRUE;
}


/****************************************
 * Fonction recursive qui remplit a
 * partir de la diagonale diag
 ***************************************/
int RemplirH(int diag) {
   int i;
	
   /* Condition d'arret */
   if (diag > height) return TRUE;

   /* Recursion */
   for(i = elm_count; i >= 1; i--) {
      /* On essaie avec chaque valeur */
      if (!Contains(i, 1, diag)) {
	 if (Propager(diag, i) && RemplirH(diag+1)) 
	   return TRUE;
      }
   }
   return FALSE;
}

/****************************************
 * Point d'entree du programme
 ***************************************/
int main(int argc, char* argv[]) {
   /* Declaration des variables */
   int valeur;
   time_t start,stop;	/* to bench the computation time */
   int found = 0;
   int tot_progress=0;
	
   /* Traitement des parametres passes au programme */
   if(argc < 2) {
      printf("Usage: ./pyramidep <height>\n");
      exit(1);
   }
   
   if ((sscanf(argv[1], "%i", &height) == -1)) {
      printf("Usage: ./pyramide <height>\n");
      exit(2);
   }
      
   /* Creation de la pyramide */
   int i;
   
   if (height < 1) {
      fprintf(stderr, "height must be strictly positive");
      exit(3);
   }   
   
   /* Nombre d'elements de la pyramide */
   pyr_size = (height*(height+1))/2;
   elm_count = pyr_size;
   
   /* mallocs */
   elements = (int*)malloc(pyr_size * sizeof(int));
   place = NULL;
   if (elements == NULL) {
      fprintf(stderr,"Out of memory\n");
      exit(5);
   }

   /* Let's get funky */
   start = time(NULL);
   while (!found) {	
      valeur=1;
      found=0;
      tot_progress=0;
      while (valeur<=elm_count && !found) {
	 if (place) free(place);
	 place = (int*)malloc(elm_count * sizeof(int));
	 if (!place) {	 
	    fprintf(stderr,"Out of memory\n");
	    exit(5);
	 }
	 
	 /* Place possible: de 0 a nb_elem-1
	  * En initialisant a +\infty le test contains est simplifie */
	 for (i=0; i<elm_count; i++) {      
	    place[i] = OUT_OF_PYR;
	 }
	 progres=0;
	 
	 SetValueAt(valeur, 1, 1);
	 found = RemplirH(2);
	 
	 if (tot_progress<progres) 
	   tot_progress = progres;
	 if (found)
	   Display();
	 else {	   
	    //printf("No solution found with %d at first pos (max progress on this %.2f%%, max progress overall %.2f%%)\n",
	    //valeur,((float)progres)/((float)pyr_size)*100.0, ((float)tot_progress)/((float)pyr_size)*100.0);
	 }
	 
	 valeur++;
      }
      if (!found) {
	 printf("(no solution with max=%d, max_progress=%.2f%%)\n",elm_count,((float)tot_progress)/((float)elm_count)*100.0);
	 elm_count++;
      }      
   }
   
   
   stop = time(NULL);
   


   /* Affichage */
   printf("Computation time: %is\n", (int)(stop-start));
	
   /* On sort proprement */
   free(elements);
   free(place);
   return 0;
}

#if 0

Pyramid of height 6
  6 20 22 3 21 13
   14 2 19 18 8
    12 17 1 10
     5 16 9
      11 7
       4
  
  Ignored elements:  15
  Computation time: 0s
  

Pyramid of height 7
14 31 5 33 32 8 19
 17 26 28 1 24 11
   9 2 27 23 13
    7 25 4 10
     18 21 6
       3 15
        12

Ignored elements:  16 20 22
Computation time: 4s

  
Pyramid of height 8
  7 33 42 3 44 43 6 29
   26 9 39 41 1 37 23
    17 30 2 40 36 14
     13 28 38 4 22
      15 10 34 18
       5 24 16
        19 8
         11
  
Ignored elements:  12 20 21 25 27 31 32 35
Computation time: 87s
  
  
#endif
