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
	Dimension dimension;
	PlanDeJeu planDejeu = PlanDeJeu.getInstance();
	
	private JTextArea jTexte;
	private JScrollPane jScroll;
	
	public PanneauStatusBas(Dimension dimention) {
		dimension = new Dimension();
		dimension.setSize(dimention.width/3,dimention.height/3);
		
		
		jTexte = new JTextArea(dimension.width,dimension.height);
		jTexte.setEditable(false);
		System.out.println(dimention.width);
		jScroll = new JScrollPane(jTexte);
		jScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		jScroll.setSize(dimension);
		
		this.add(jScroll);		
	}
	
	
	
	
	@Override
	public void avertir() {
		// TODO Auto-generated method stub
		
	}

}
