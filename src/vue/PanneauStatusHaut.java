package vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;

import modele.PlanDeJeu;
import observer.MonObserver;

public class PanneauStatusHaut extends JPanel {

	//Général
	private Dimension dimention;
	private PlanDeJeu planDejeu = PlanDeJeu.getInstance();
	
	
	private JPanel panneauHaut;
	private JLabel lable = new JLabel("Leeroy Jenkins", SwingConstants.CENTER);
	private JProgressBar barreVie = new JProgressBar(0, planDejeu.getJoueur().getPointDeVieMax());
	private JLabel numNiveau = new JLabel("Niveau: "+ planDejeu.getNiveau(), SwingConstants.CENTER);
	private JLabel nbEnnemieAff = new JLabel("NB Ennemis Tues: 0", SwingConstants.CENTER);
	private JLabel tempsEcou = new JLabel("Temps de jeu: 0 secondes", SwingConstants.CENTER);
	long tempsAvant = System.currentTimeMillis();
	long tempsApres;
	int tempsDifference;
	
	
	
	public PanneauStatusHaut() {
		//Initialisation du panneau
				
				this.setLayout(new GridLayout(5,1));
				this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				
				
				
				//Spécification components
				Font styleTxt = new Font("Times New Roman",Font.BOLD | Font.ITALIC,24);
				lable.setFont(styleTxt);
				
				barreVie.setForeground(Color.GREEN);
				barreVie.setBackground(Color.RED);
				barreVie.setValue(planDejeu.getJoueur().getPointDeVie());
				
				//Ajout components au panneau
				this.add(lable);
				this.add(barreVie);
				this.add(numNiveau);
				this.add(nbEnnemieAff);
				this.add(tempsEcou);
				
	}
	
	public void mettreAJoursInfo(int ennemisMort) {
		tempsApres = System.currentTimeMillis();
		tempsDifference =  (int) ((tempsApres - tempsAvant)/1000);
		barreVie.setValue(planDejeu.getJoueur().getPointDeVie());
		numNiveau.setText("Niveau: "+ planDejeu.getNiveau());
		nbEnnemieAff.setText("NB Ennemis Tues: "+ ennemisMort);
		tempsEcou.setText("Temps de jeu: "+tempsDifference+" secondes");
	}
	

}

























