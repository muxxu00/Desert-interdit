package menu1 ;

import javax.swing.*;
import java.awt.*;

public class Jouer extends JButton {
    private JPanel root;

    public Jouer(final JPanel root){
        this.root = root;
        setText("Jouer");
        final CardLayout[] cards = new CardLayout[1];
        this.addActionListener(e -> {
            cards[0] = (CardLayout)this.root.getLayout();
            cards[0].show(root, "menu_joueur");
        });
    }
}
