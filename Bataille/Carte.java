package Bataille;


public class Carte {
	
	//Attributs - Une carte a : un nom, une couleur et une valeur //
	private String nom; // nom de la carte //
    private String couleur; // couleur de la carte //
    private int valeur; // valeur de la carte //
	
	//Tableaux appellés lors de la création d'une carte //
    //ex : Carte carte1 = new Carte(Carte.noms[0], Carte.couleurs[0], Carte.valeurs[0]); // 2 de Coeur //
	public static String[] noms = new String[] {"2","3","4","5","6","7","8","9","10","Valet","Dame","Roi","As"}; //13 noms de cartes différents //
	public static String[] couleurs = new String[]{"Trèfle","Coeur","Carreau","Pique"}; //Il y a 4 couleurs //
	public static int[]valeurs = new int[]{2,3,4,5,6,7,8,9,10,11,12,13,14}; //Il existe 13 valeurs possible de 2 à 14 (As) //
	
	
	
	//Constructeur de Carte //
	public Carte (String unNom, String laCouleur, int uneValeur) {
		this.nom = unNom;
		this.couleur = laCouleur;
		this.valeur = uneValeur;
	}
	
	
	//Getters //
	public String getNom() { return nom; }
	public String getCouleurs() { return couleur; }
	public int getValeur() { return valeur; }
	
	//Setters //
	public void setNom(String nvNom) { this.nom = nvNom; }
	public void setCouleurs(String nvCouleur) { this.couleur = nvCouleur; }
	public void setValeur(int nvValeur) { this.valeur = nvValeur; }
	
	//Comparateur //
	public int compareTo(Carte carteAdv) { //ex : carte1.compareTo(carte2) //
        int carte1Valeur = this.getValeur();
        int carteAdvValeur = carteAdv.getValeur();
        
        // Comparaison des valeurs //
        return Integer.compare(carte1Valeur, carteAdvValeur);
    }
	
	// On réécrit la méthode toString pour la Classe Carte //
	@Override
    public String toString() {
        return (nom + " de " + couleur); //ex : 2 de Coeur //
    }
}
