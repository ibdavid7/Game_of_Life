package life;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameOfLife extends JFrame {

    public final int boxSize = 15;
    Universe universe;
    int size;
    int generation;
    JLabel GenerationLabel;
    JLabel AliveLabel;
    JPanel gridPanel;
    boolean isPaused;
    boolean isReset;

    /*
    public GameOfLife(Universe universe) {
        super();
        this.universe = universe;
        this.size = universe.size;
        this.generation = 0;
    }
     */

    public GameOfLife() {
        super("Game of Life");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setSize(300, 300);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        GenerationLabel = new JLabel();
        GenerationLabel.setName("GenerationLabel");
        GenerationLabel.setText("  Generation #" + generation);

        AliveLabel = new JLabel();
        AliveLabel.setName("AliveLabel");
        AliveLabel.setText("  Alive: " + 0);

        menuPanel();

        gridPanel = new gridPanel();
        add(gridPanel);

        setLocationRelativeTo(null);
        setResizable(true);
        this.pack();
        setVisible(true);
    }

    public void menuPanel() {
        //statsPanel
        var statsPanel = new JPanel();
        statsPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
        statsPanel.add(GenerationLabel);
        statsPanel.add(AliveLabel);
        add(statsPanel);

        //buttonsPanel
        var buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        var pauseButton = new JToggleButton();
        pauseButton.setName("PlayToggleButton");
        pauseButton.setText("Pause/Play");
        pauseButton.addActionListener(actionEvent -> isPaused = !isPaused);
        buttonsPanel.add(pauseButton);

        var resetButton = new JButton();
        resetButton.setName("ResetButton");
        resetButton.setText("Reset");
        resetButton.addActionListener(e -> isReset = !isReset);
        buttonsPanel.add(resetButton);

        add(buttonsPanel);

    }

    public void reset() {
        isReset = false;
        int generation = 0;
    }

    public boolean isReset() {
        return isReset;
    }

    public boolean isPaused() {
        return isPaused;
    }

    private class gridPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            super.paintComponent(g2);
            super.paintComponent(g2);
            for (int i = 0; i <= size; i++) {
                //Horizontal lines
                g2.drawLine(i * boxSize, 0, i * boxSize, boxSize * size);
                //Vertical lines
                g2.drawLine(0, i * boxSize, boxSize * size, i * boxSize);
            }

            //Fill Rectangles
            g2.setColor(Color.BLACK);
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (universe.matrix[i][j] == 'O') {
                        g2.fillRect(j * boxSize, i * boxSize, boxSize, boxSize);
                    }
                }
            }
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(300, 300);
        }
    }

    /*
    private void initComponents() {


        JPanel textPanel = new JPanel();
        JLabel GenerationLabel = new JLabel("  Generation #" + generation);
        JLabel AliveLabel = new JLabel("  Alive: " + universe.getAlive());
        add(GenerationLabel);
        add(AliveLabel);
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        add(textPanel);
        textPanel.setVisible(true);


        JPanel gridPanel = new JPanel() {

            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                super.paintComponent(g2);
                for (int i = 0; i <= size; i++) {
                    //Horizontal lines
                    g2.drawLine(i * boxSize, 0, i * boxSize, boxSize * size);
                    //Vertical lines
                    g2.drawLine(0, i * boxSize,boxSize * size,i * boxSize);

                }

                //Fill Rectangles
                g2.setColor(Color.BLACK);
                for (int i = 0; i < size; i++) {
                    for (int j = 0; j < size; j++) {
                        if (universe.matrix[i][j] == 'O') {
                            g2.fillRect(j * boxSize, i * boxSize, boxSize, boxSize);
                        }
                    }
                }
            }

            @Override
            public Dimension getPreferredSize() {
                return new Dimension(size * boxSize + 2, size * boxSize + 2);
                //return new Dimension(300, 300);
            }
        };

        gridPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        add(gridPanel);

    }
     */

    public void evolve(int generation) throws InterruptedException {
        this.generation = generation;
        this.size = this.universe.size;

        final int interval = 300;

        var timer = new Timer(interval, new ActionListener() {

            int counter = 0;

            @Override
            public void actionPerformed(ActionEvent evt) {

                GameOfLife.this.revalidate();
                GameOfLife.this.repaint();
                GenerationLabel.setText("  Generation #" + GameOfLife.this.generation);
                AliveLabel.setText("  Alive: " + GameOfLife.this.universe.getAlive());
                //gridPanel.repaint();
                counter++;

            }
        });

        timer.start();

    }

    /*
    public static void main(final String[] args) {
        EventQueue.invokeLater(() -> {
            var frame = new GameOfLife();
            });

    }

     */
}

