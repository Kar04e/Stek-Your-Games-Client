package pl.monopoly.view;

import pl.monopoly.model.Cubes;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class CubesView extends CustomButtonView {
    private final Cubes cubes;
    private final int heightOneCube = 130;
    private final int widthOneCube = 200;

    // create
    public CubesView(Cubes cubes) {
        super(240, 150, 460, 550);
        this.cubes = cubes;
    }

    // methods
    @Override
    public void render(Graphics g) {
        int borderWidth = 6;

        int innerX = positionX + borderWidth / 2;
        int innerY = positionY + borderWidth / 2;
        int innerWidth = width - borderWidth;
        int innerHeight = height - borderWidth;

        // Load background image using class loader
        ImageIcon backgroundImage = new ImageIcon(Objects.requireNonNull(getClass().getResource("/img/cubesBackground.jpg")));
        g.drawImage(backgroundImage.getImage(), positionX, positionY, width, height, null);

        g.setColor(new Color(0, 51, 102));
        g.setFont(new Font("Roboto", Font.BOLD, 17));
        g.drawString("Click to randomize!", positionX + 37, positionY - 5);

        g.setColor(Color.BLACK);
        g.drawRect(positionX, positionY, width, height);

        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(new Color(0, 51, 102));
        g2.setStroke(new BasicStroke(borderWidth));
        g2.drawRect(innerX, innerY, innerWidth, innerHeight);

        showCube(cubes.getRoll1(), 0, g);
        showCube(cubes.getRoll2(), 72, g);
    }

    public void showCube(int number, int gap, Graphics g) {
        // Load cube image using class loader
        ImageIcon imageIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/img/cubesAllImages/cube" + number + "pImage.png")));
        if (number != 0) {
            g.drawImage(imageIcon.getImage(), positionX + 35 + gap, positionY + 27, 100, 100, null);
            return;
        }
        g.drawImage(imageIcon.getImage(), positionX - 10, positionY + 15, widthOneCube, heightOneCube, null);
        g.drawImage(imageIcon.getImage(), positionX + 50, positionY + 20, widthOneCube, heightOneCube, null);
    }

    @Override
    public void click() {
        SoundPlayer.playSound(Sound.DRAW_CLICK);
        cubes.rollTheDice();
    }
}
