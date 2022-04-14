package vue;

import java.awt.Dimension;

import javax.swing.JPanel;

import modele.PlanDeJeu;
import observer.MonObserver;

public class PanneauStatusBas extends JPanel implements MonObserver{

	//Général
	Dimension dimention;
	PlanDeJeu planDejeu = PlanDeJeu.getInstance();
	
	@Override
	public void avertir() {
		// TODO Auto-generated method stub
		
	}

}
