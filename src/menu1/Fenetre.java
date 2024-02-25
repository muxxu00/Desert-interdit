package menu1 ;

import javax.swing.*;
import java.awt.*;

public class Fenetre extends JFrame
{
    private JPanel elements;

    public Fenetre(final String nom, final Dimension minSize) {
        super(nom);
        this.setMinimumSize(minSize);
        this.add(this.elements = new JPanel());
    }

    public void ajouteElement(final JComponent element) {
        this.elements.add(element);
    }

    public void dessineFenetre() {
        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(3);
    }
}