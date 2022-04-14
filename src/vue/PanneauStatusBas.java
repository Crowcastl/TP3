package vue;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import modele.PlanDeJeu;
import observer.MonObserver;

public class PanneauStatusBas extends JPanel {

	//Général
	Dimension dimention;
	PlanDeJeu planDejeu = PlanDeJeu.getInstance();
	

	private JTextArea jTexte;
	private JScrollPane jScroll;
	
	public PanneauStatusBas(Dimension dimention) {
		this.dimention = new Dimension(dimention);
		this.dimention.setSize(dimention.width,dimention.height);
		
		jTexte = new JTextArea(dimention.height/50,(dimention.width/12));
		jTexte.setSize(this.dimention);
	
		jTexte.setEditable(false);
		
		jScroll = new JScrollPane(jTexte);
		jScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		jScroll.setSize(this.dimention);
		
		this.add(jScroll);		
	}
	
	
	
public void mettreAJoursInfo() {
		
	}
	

}
