int puiss(int u, int r) {
    int x = u;
    int n = r;
    int s = 1;
    while (n != 0) {
	if (n % 2 == 0) { /* pair */
	    s = s * x;
	} 
	x = x * x;
	n = n / 2;
    }
    return s;
}
