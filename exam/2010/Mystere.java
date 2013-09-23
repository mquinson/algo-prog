public int mystification(int[ ] tab) {
    return mystification(tab, 0);
}

public int mystification(int[ ] tab, int i) {
    if (i == tab.length-1) {
        return 0;
    } else {
        if (tab[i] > tab[tab.length-1]) {
            return 1+mystification(tab, i+1);
        } else  {
            return mystification(tab, i+1);
	}
    }
}
