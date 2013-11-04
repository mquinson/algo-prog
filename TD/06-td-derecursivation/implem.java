public int puissance(int x, int n) {
    if (n==1)                 /* cas terminal */
	return x;
    if (n % 2 == 0)           /* pair */
	return puissance(x*x,n/2);
                              /* impair */
    return x*puissance(x*x,n/2);
}
