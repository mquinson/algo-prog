#include <stdio.h>

int tab[] = { 2,8,7,4,3,6,5,1};
const int size= 8;
void swap(int i, int j) 
{
   printf("swap %d %d\n",i,j);
   int tmp = tab[i];
   tab[i]=tab[j];
   tab[j]=tmp;
}

int mystere(int cle) 
{
   
     int d,f;
   
     d=0 ;f=size-1;
     do 
     {
	
	    while (d<size && tab[d] <= cle) 
	  {
	     
	           d++;
	  }
	
	    while (f>=0 && tab[f]>cle) 
	  {
	     
	           f--;
	  }
	
	    if (d<f) 
	  {
	     
	           swap(d,f);
	           d++;
	           f--;
	  }
	
     }
    while (d<f);
     return f;
}

int main() 
{
   int i;
   printf("res:%d\n",mystere(6));
   for (i=0;i<size;i++) 
     printf("%d ",tab[i]);
   printf("\n");
}
