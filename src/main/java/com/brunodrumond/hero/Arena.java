package com.brunodrumond.hero;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;
import java.util.*;

public class Arena {
    private int width_;
    private int height_;
    private List<Wall> walls;
    public Arena(int width, int height){
        width_ = width;
        height_ = height;
        this.walls = createWalls();
    }
    private List<Wall> createWalls() {
        List<Wall> walls = new ArrayList<>();
        for (int c = 0; c < width_; c++) {
            walls.add(new Wall(c, 0));
            walls.add(new Wall(c, height_- 1));
        }
        for (int r = 1; r < height_ - 1; r++) {
            walls.add(new Wall(0, r));
            walls.add(new Wall(width_ - 1, r));
        }
        return walls;
    }

    public boolean canHeroMove(Position position){
        for (Wall wall : walls){
            if(wall.getX_() == position.getX() && wall.getY_() == position.getY()) return false;
        }
        return true;
    }

    public void moveHero(Position position){
        if (canHeroMove(position))
            hero.setPosition(position);
    }
    private Hero hero = new Hero(10, 10);
    public void processKey(KeyStroke key) throws IOException {

        if(key.getKeyType() == KeyType.ArrowUp){
            moveHero(hero.moveUp());
        }
        else if(key.getKeyType() == KeyType.ArrowDown){
            moveHero(hero.moveDown());
        }
        else if(key.getKeyType() == KeyType.ArrowLeft){
            moveHero(hero.moveLeft());
        }
        else if(key.getKeyType() == KeyType.ArrowRight){
            moveHero(hero.moveRight());
        }
    }
    public void draw(TextGraphics graphics){
        graphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width_, height_), ' ');
        hero.draw(graphics);
        for (Wall wall : walls)
            wall.draw(graphics);
    }
}
