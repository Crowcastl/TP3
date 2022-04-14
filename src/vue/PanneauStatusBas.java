package vue;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import modele.PlanDeJeu;
import observer.MonObserver;

public class PanneauStatusBas extends JPanel implements MonObserver{

	//Général
	
	PlanDeJeu planDejeu = PlanDeJeu.getInstance();
	private int width;
	private int height;
	
	private JTextArea jTexte;
	private JScrollPane jScroll;
	
	public PanneauStatusBas(Dimension dimention) {
		
		jTexte = new JTextArea(20,60);
	
		jTexte.setEditable(false);
		
		jScroll = new JScrollPane(jTexte);
		jScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		
		this.add(jScroll);		
	}
	
	
	
	
	@Override
	public void avertir() {
		// TODO Auto-generated method stub
		
	}

}
