/*
 * Nom projet: TP3
 * Auteurs: Annie Tremblay, Samuel Velasco et Jonathan Savard
 * Dates: 2022-04-17
 * Remise �: Abdelmoum�ne Toudeft
 */

package programme;


public class programmePrincipal {

	/**
	 * Programme principal, lance la vue du programme
	 * 
	 * @param args, inutilisé
	 */
    public static void main(String[] args){
    	
    	// lance le view-controller (à compléter)
    	Thread t = new Thread(new vue.CadrePrincipal());
    	t.start();
    	
    }
    
}

