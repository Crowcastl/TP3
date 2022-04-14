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
	
	
	private JTextArea jTexte;
	private JScrollPane jScroll;
	
	public PanneauStatusBas(Dimension dimention) {
		this.dimention = new Dimension(dimention);
		this.dimention.setSize(dimention.width,dimention.height/3);
		
		jTexte = new JTextArea(16,20);
		jTexte.setSize(this.dimention);
	
		jTexte.setEditable(false);
		
		jScroll = new JScrollPane(jTexte);
		jScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		jScroll.setSize(this.dimention);
		
		this.add(jScroll);		
	}
	
	
	
	
	@Override
	public void avertir() {
		// TODO Auto-generated method stub
		
	}

}
