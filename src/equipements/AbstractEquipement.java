package equipements;

/**
 * Définition d'un équipement.
 * 
 * @author Fred Simard | ETS
 * @version ETE 2018 - TP3
 */

import java.util.Random;

import dongon.AbstractObjet;
import dongon.Case;
import physique.Position;

public class AbstractEquipement extends AbstractObjet {

	// generateur pseudo aleatoire
	protected static Random rand = new Random();
	
	// indique si l'equipement est au sol
	protected boolean auSol = true;
	
	// valeur de l'effet de l'equipement
	protected int valeur;
	

	/**
	 * constructeur
	 * @param pos, position I,J dans le labyrinthe
	 * @param valeur, valeur de l'effet de l'equipement
	 */
	public AbstractEquipement(Position pos, int valeur){
		this.pos = pos;
		this.valeur = valeur;
	}
	
	/**
	 * informatrice pour obtenir la valeur de l'équipement
	 * @return valeur
	 */
	public int getValeur(){
		return this.valeur;
	}	

	/**
	 * mutatrice pour définir si l'équipement est au sol
	 * @param auSol, true si l'équipement doit être au sol
	 */
	public void setAuSol(boolean auSol){
		this.auSol = auSol;
	}	

	/**
	 * informatrice pour savoir si l'équipement est au sol
	 * @return true si l'équipement est au sol
	 */
	public boolean estAusol(){
		return this.auSol;
	}	
}
