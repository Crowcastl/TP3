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

public class PanneauStatusHaut extends JPanel implements MonObserver{

	//Général
	Dimension dimention;
	PlanDeJeu planDejeu = PlanDeJeu.getInstance();
	
	
	JPanel panneauHaut;
	JLabel lable = new JLabel("Leeroy Jenkins", SwingConstants.CENTER);
	JProgressBar barreVie = new JProgressBar();
	JLabel numNiveau = new JLabel("Niveau: X", SwingConstants.CENTER);
	JLabel nbEnnemieAff = new JLabel("NB Ennemis Tues: X", SwingConstants.CENTER);
	JLabel tempsEcou = new JLabel("Temps de jeu: X secondes", SwingConstants.CENTER);
	
	
	public PanneauStatusHaut() {
		//Initialisation du panneau
				
				this.setLayout(new GridLayout(5,1));
				this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				
				
				
				//Spécification components
				Font styleTxt = new Font("Times New Roman",Font.BOLD | Font.ITALIC,24);
				lable.setFont(styleTxt);
				
				barreVie.setForeground(Color.GREEN);
				barreVie.setBackground(Color.RED);
				
				//Ajout components au panneau
				this.add(lable);
				this.add(barreVie);
				this.add(numNiveau);
				this.add(nbEnnemieAff);
				this.add(tempsEcou);
				
	}
	
	@Override
	public void avertir() {
		// TODO Auto-generated method stub
		
	}

}
