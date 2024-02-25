package menu1 ;

import javax.swing.*;
import java.awt.*;

public class menuP extends JPanel {
    private Jouer jouer;
    private  JButton quitter;

    public menuP(final JPanel rootPanel) {
        jouer = new Jouer(rootPanel);
        quitter = new JButton("Quitter");
        setLayout(new BorderLayout());
        final JPanel tmp = new JPanel();
        tmp.add(jouer);
        tmp.add(quitter);
        add(tmp, "Center");
    }

}
