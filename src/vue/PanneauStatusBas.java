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
	Dimension dimention;
	PlanDeJeu planDejeu = PlanDeJeu.getInstance();
	
	private JTextArea jTexte = new JTextArea(20,20);
	private JScrollPane jScroll = new JScrollPane(jTexte);
	
	public PanneauStatusBas() {
		this.dimention = new Dimension();
		
		jTexte.setSize(dimention.width/3,dimention.height/3);
		jTexte.setEditable(false);
		
		jScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		
		this.add(jScroll);		
	}
	
	
	
	
	@Override
	public void avertir() {
		// TODO Auto-generated method stub
		
	}

}
