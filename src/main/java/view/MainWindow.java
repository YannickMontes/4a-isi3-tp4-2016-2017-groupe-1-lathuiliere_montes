package view;

import controller.GlobalController;
import model.Turtle;
import services.Behavior;
import services.FlockingBehavior;
import model.Shape;
import services.RandomBehavior;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by yannick on 26/04/17.
 */
public class MainWindow extends JFrame implements ActionListener
{
    public static final Dimension VGAP = new Dimension(1,5);
    public static final Dimension HGAP = new Dimension(5,1);

    private DrawingSheet feuille;
    private Turtle currentTurtle;
    private JTextField inputValue;
    private GlobalController controller;
    private boolean flocking;
    private boolean random;
    private boolean controlled;
    private int currentColor;
    private Behavior currentBehavior;
    private Shape currentShape;


    /**
     * @param args
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){

                MainWindow fenetre = new MainWindow();
                fenetre.setVisible(true);
            }
        });

    }

    public void quitter() {
        System.exit(0);
    }

    public MainWindow() {
        super("TORTUGA");
        initWindow();
        controller = new GlobalController(this.currentTurtle, this, this.feuille.getTurtles());

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent arg0) {
                super.windowClosing(arg0);
                System.exit(0);
            }
        });
    }

    public void initWindow()
    {
        getContentPane().setLayout(new BorderLayout(10,10));

        initButtons();

        initMenu();

        feuille = new DrawingSheet(this);

        getContentPane().add(feuille,"Center");

        Turtle turtle = new Turtle();

        // Deplacement de la turtle au centre de la feuille
        turtle.setPosition(500/2, 400/2);

        currentTurtle = turtle;
        feuille.addTurtleView(new TurtleView(turtle, this.feuille));

        pack();
        setVisible(true);
    }

    public void initMenu()
    {
        JMenuBar menubar=new JMenuBar();
        setJMenuBar(menubar);	// on installe le menu bar
        JMenu menuFile=new JMenu("File"); // on installe le premier menu
        menubar.add(menuFile);

        addMenuItem(menuFile, "Créer tortue", "AddTurtle", -1);
        addMenuItem(menuFile, "Effacer", "Effacer", KeyEvent.VK_N);
        addMenuItem(menuFile, "Quitter", "Quitter", KeyEvent.VK_Q);

        JMenu menuCommandes=new JMenu("Commandes"); // on installe le premier menu
        menubar.add(menuCommandes);
        addMenuItem(menuCommandes, "Avancer", "Avancer", -1);
        addMenuItem(menuCommandes, "Droite", "Droite", -1);
        addMenuItem(menuCommandes, "Gauche", "Gauche", -1);
        addMenuItem(menuCommandes, "Lever Crayon", "Lever", -1);
        addMenuItem(menuCommandes, "Baisser Crayon", "Baisser", -1);

        JMenu menuHelp=new JMenu("Aide"); // on installe le premier menu
        menubar.add(menuHelp);
        addMenuItem(menuHelp, "Aide", "Help", -1);
        addMenuItem(menuHelp, "A propos", "About", -1);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void initButtons()
    {
        // Boutons
        JToolBar toolBar = new JToolBar();
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(toolBar);

        getContentPane().add(buttonPanel,"North");

        addButton(toolBar,"Effacer","Nouveau dessin","/icons/index.png");

        toolBar.add(Box.createRigidArea(HGAP));
        inputValue=new JTextField("45",5);
        toolBar.add(inputValue);
        addButton(toolBar, "Avancer", "Avancer 50", null);
        addButton(toolBar, "Droite", "Droite 45", null);
        addButton(toolBar, "Gauche", "Gauche 45", null);
        addButton(toolBar, "Lever", "Lever Crayon", null);
        addButton(toolBar, "Baisser", "Baisser Crayon", null);

        String[] colorStrings = {"noir", "bleu", "cyan","gris fonce","rouge",
                "vert", "gris clair", "magenta", "orange",
                "gris", "rose", "jaune"};

        final String[] shapes = {"Triangle", "Circle"};

        // Create the combo box
        toolBar.add(Box.createRigidArea(HGAP));
        JLabel colorLabel = new JLabel("   Couleur: ");
        toolBar.add(colorLabel);
        JComboBox colorList = new JComboBox(colorStrings);
        toolBar.add(colorList);

        colorList.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JComboBox cb = (JComboBox)e.getSource();
                currentColor = cb.getSelectedIndex();
            }
        });

        // Create the other combo box
        toolBar.add(Box.createRigidArea(HGAP));
        JLabel shapeLabel = new JLabel("   Forme: ");
        toolBar.add(colorLabel);
        JComboBox shapeList = new JComboBox(shapes);
        toolBar.add(shapeList);

        colorList.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JComboBox cb = (JComboBox)e.getSource();
                currentColor = cb.getSelectedIndex();
            }
        });

        shapeList.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JComboBox cb = (JComboBox)e.getSource();
                int index = cb.getSelectedIndex();
                currentShape = Shape.valueOf(shapes[index]);
            }
        });

        this.controlled = true;

        this.flocking = false;
        final JCheckBox randomCheckBox = new JCheckBox("Random");
        final JCheckBox flockingCheckbox = new JCheckBox("Flocking");
        flockingCheckbox.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if(currentBehavior != null)
                    currentBehavior.interrupt();
                flocking = flockingCheckbox.isSelected();
                if(flocking)
                {
                    randomCheckBox.setSelected(false);
                    random = false;
                    controlled = false;
                    effacer();
                    startFlocking(100);
                }
                else
                {
                    flocking = false;
                    controlled = true;
                    effacer();
                }
            }
        });
        toolBar.add(flockingCheckbox);

        this.random = false;

        randomCheckBox.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e)
             {
                 if(currentBehavior != null)
                     currentBehavior.interrupt();
                random = randomCheckBox.isSelected();
                if(random)
                {
                    flockingCheckbox.setSelected(false);
                    effacer();
                    flocking = false;
                    controlled = false;
                    startRandom(100);
                }
                else
                {
                    effacer();
                    flocking = false;
                    controlled = true;
                }
             }
         });

        toolBar.add(randomCheckBox);
    }


    /** la gestion des actions des boutons */
    public void actionPerformed(ActionEvent e)
    {
        controller.actionPerformed(e);
        feuille.repaint();
    }

    /** les procedures Logo qui combine plusieurs commandes..*/
    public void proc1() {
        currentTurtle.carre();
    }

    public void proc2() {
        currentTurtle.poly(60,8);
    }

    public void proc3() {
        currentTurtle.spiral(50,40,6);
    }

    // efface tout et reinitialise la feuille
    public void effacer() {
        feuille.reset();
        feuille.repaint();
    }

    public void startFlocking(int turtleNumber)
    {
        for(int i=0; i<turtleNumber; i++)
        {
            this.addRandomTurtle();
        }
        this.currentBehavior = FlockingBehavior.getInstance();
        this.setAttributesToBehavior();
        if(this.currentBehavior.isAlive())
            this.currentBehavior.resume();
        else
            this.currentBehavior.start();
    }

    public void startRandom(int turtleNumber)
    {
        for(int i=0; i<turtleNumber; i++)
        {
            this.addRandomTurtle();
        }
        this.currentBehavior = RandomBehavior.getInstance();
        this.setAttributesToBehavior();
        if(this.currentBehavior.isAlive())
            this.currentBehavior.resume();
        else
            this.currentBehavior.start();
    }

    private void setAttributesToBehavior()
    {
        this.currentBehavior.setDrawingSheet(this.feuille);
        this.currentBehavior.setTurtles(this.feuille.getTurtles());
    }

    private void addRandomTurtle()
    {
        int posX = (int) (Math.random() * this.feuille.getSize().getWidth());
        int posY = (int) (Math.random() * this.feuille.getSize().getHeight());

        int coul = (int) (Math.random() * 12);

        int direction = (int) (Math.random() * 180);

        double speed = (Math.random() * 10)+1;

        Turtle toAdd = new Turtle();
        toAdd.setDirection(direction);
        toAdd.setPosition(posX, posY);
        toAdd.setColor(coul);
        toAdd.setSpeed(speed);

        this.feuille.addTurtleView(new TurtleView(toAdd, this.feuille));
    }

    //utilitaires pour installer des boutons et des menus
    public void addButton(JComponent p, String name, String tooltiptext, String imageName) {
        JButton b;
        if ((imageName == null) || (imageName.equals(""))) {
            b = (JButton)p.add(new JButton(name));
        }
        else {
            java.net.URL u = this.getClass().getResource(imageName);
            if (u != null) {
                ImageIcon im = new ImageIcon (u);
                b = (JButton) p.add(new JButton(im));
            }
            else
                b = (JButton) p.add(new JButton(name));
            b.setActionCommand(name);
        }

        b.setToolTipText(tooltiptext);
        b.setBorder(BorderFactory.createRaisedBevelBorder());
        b.setMargin(new Insets(0,0,0,0));
        b.addActionListener(this);
    }

    public void addMenuItem(JMenu m, String label, String command, int key) {
        JMenuItem menuItem;
        menuItem = new JMenuItem(label);
        m.add(menuItem);

        menuItem.setActionCommand(command);
        menuItem.addActionListener(this);
        if (key > 0) {
            if (key != KeyEvent.VK_DELETE)
                menuItem.setAccelerator(KeyStroke.getKeyStroke(key, Event.CTRL_MASK, false));
            else
                menuItem.setAccelerator(KeyStroke.getKeyStroke(key, 0, false));
        }
    }

    public String getInputValue(){
        String s = inputValue.getText();
        return(s);
    }

    public void addNewTurtle()
    {
        Turtle tmp = new Turtle();
        tmp.setColor(this.currentColor);
        tmp.setShape(this.currentShape);
        tmp.setPosition(500/2,  400/2);
        feuille.addTurtleView(new TurtleView(tmp, this.feuille));
    }

    public void setCourante(Turtle turtle)
    {
        this.currentTurtle = turtle;
        this.controller.setCurrentTurtle(turtle);
    }

    public Turtle getCourante()
    {
        return this.currentTurtle;
    }

    public boolean canControl()
    {
        return this.controlled;
    }
}
