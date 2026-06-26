package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class welcomePage extends JPanel implements ActionListener {

    private final int width = 9 * 32;  // 288
    private final int height = 10 * 32; // 320

    private double pacX = -30;
    private int mouthAngle = 0;
    private boolean mouthOpening = true;

    private final int pacSize = 30;
    private final int pacY = height / 2 - pacSize / 2;

    private boolean[] pelletEaten;
    private int[] pelletX;
    private final int pelletY = pacY + pacSize / 2 - 3;
    private final int pelletSpacing = 24;
    private final int pelletSize = 5;

    private Timer animationTimer;
    private Runnable onStart;

    public welcomePage(Runnable onStart) {
        this.onStart = onStart;

        setPreferredSize(new Dimension(width, height));
        setBackground(Color.BLACK);
        setBorder(BorderFactory.createLineBorder(Color.YELLOW, 2)); // helps it read as an overlay box
        setLayout(null);

        JLabel title = new JLabel("PAC-MAN");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setForeground(Color.YELLOW);
        title.setBounds(0, 15, width, 30);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        add(title);

        JButton startButton = new JButton("Start");
        startButton.setFont(new Font("Arial", Font.PLAIN, 14));
        startButton.setBounds(width / 2 - 50, height - 60, 100, 30);
        startButton.addActionListener(e -> {
            animationTimer.stop();
            onStart.run();
        });
        add(startButton);

        initPellets();

        animationTimer = new Timer(40, this);
        animationTimer.start();
    }

    private void initPellets() {
        int count = width / pelletSpacing + 2;
        pelletX = new int[count];
        pelletEaten = new boolean[count];
        for (int i = 0; i < count; i++) {
            pelletX[i] = i * pelletSpacing;
            pelletEaten[i] = false;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(Color.WHITE);
        for (int i = 0; i < pelletX.length; i++) {
            if (!pelletEaten[i]) {
                g2.fillOval(pelletX[i], pelletY, pelletSize, pelletSize);
            }
        }

        g2.setColor(Color.YELLOW);
        int startAngle = mouthAngle / 2;
        int arcAngle = 360 - mouthAngle;
        g2.fillArc((int) pacX, pacY, pacSize, pacSize, startAngle, arcAngle);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        pacX += 3;

        for (int i = 0; i < pelletX.length; i++) {
            if (!pelletEaten[i] && pelletX[i] < pacX + pacSize / 2 && pelletX[i] > pacX - pacSize / 2) {
                pelletEaten[i] = true;
            }
        }

        if (pacX > width) {
            pacX = -pacSize;
            for (int i = 0; i < pelletEaten.length; i++) {
                pelletEaten[i] = false;
            }
        }

        if (mouthOpening) {
            mouthAngle += 6;
            if (mouthAngle >= 45) mouthOpening = false;
        } else {
            mouthAngle -= 6;
            if (mouthAngle <= 0) mouthOpening = true;
        }

        repaint();
    }
}