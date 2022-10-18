package zlpackage;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

public class Tabuleiro {
	
	public static BufferedImage spritesheet;
	
	public static int WIDTH = 6, HEIGHT = 6;
	public static int [][] TABULEIRO;
	
	public static int GRID_SIZE = 40;
	
	public static int DOCE_0 = 0, DOCE_1 = 1, DOCE_2 = 2;
	
	public BufferedImage sprtDoce_0 = Tabuleiro.getSprite(279,919,153,134);
	public BufferedImage sprtDoce_1 = Tabuleiro.getSprite(276,1087,155,122);
	public BufferedImage sprtDoce_2 = Tabuleiro.getSprite(254,1257,147,122);
	
	public Tabuleiro() {
		TABULEIRO = new int[WIDTH][HEIGHT];
		
		for(int x = 0; x < WIDTH; x++ ) {
			for(int y = 0; y < HEIGHT; y++ ) {
				TABULEIRO[x][y] = new Random().nextInt(3);
			}
		}
	}
	
	public static BufferedImage getSprite(int x,int y,int width,int height){
		if(spritesheet == null) {
			try {
				spritesheet = ImageIO.read(Tabuleiro.class.getResource("/spritesheet.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
			return  spritesheet.getSubimage(x, y, width, height);
	}
	
	
	public void update() {
		ArrayList<Candy>combos = new ArrayList<Candy>();
		for(int yy = 0; yy< HEIGHT ; yy++) {
			if(combos.size() == 3) {
				for(int i = 0; i< combos.size(); i++) {
					int Xtemp = combos.get(i).x;
					int Ytemp = combos.get(i).y;
					TABULEIRO[Xtemp][Ytemp] = new Random().nextInt(3);
				}
				combos.clear();
				Game.points++;
				Game.frame.setTitle("Candy Crush - Pontos: "+Game.points);
				System.out.println("Ponto!");
				return;
			}
					combos.clear();
			for(int xx = 0; xx< WIDTH ; xx++) {
				int cor = TABULEIRO[xx][yy];
				if(combos.size() == 3) {
					for(int i = 0; i< combos.size(); i++) {
						int Xtemp = combos.get(i).x;
						int Ytemp = combos.get(i).y;
						TABULEIRO[Xtemp][Ytemp] = new Random().nextInt(3);
					}
					combos.clear();
					Game.points++;
					Game.frame.setTitle("Candy Crush - Pontos: "+Game.points);
					System.out.println("Ponto!");
					return;
				}
					if(combos.size() == 0) {
						combos.add(new Candy(xx,yy,cor));
					}else if(combos.size() > 0) {
							if(combos.get(combos.size() - 1).candyTYPE == cor) {
								combos.add(new Candy(xx,yy,cor));
							}else {
								combos.clear();
								combos.add(new Candy(xx,yy,cor));
							}
					}
			}
		}
		
		combos = new ArrayList<Candy>();
		for(int xx = 0; xx< WIDTH ; xx++) {
			if(combos.size() == 3) {
				for(int i = 0; i< combos.size(); i++) {
					int Xtemp = combos.get(i).x;
					int Ytemp = combos.get(i).y;
					TABULEIRO[Xtemp][Ytemp] = new Random().nextInt(3);
				}
				combos.clear();
				System.out.println("Ponto!");
				return;
			}
					combos.clear();
			for(int yy = 0; yy< HEIGHT ; yy++) {
				int cor = TABULEIRO[xx][yy];
				if(combos.size() == 3) {
					for(int i = 0; i< combos.size(); i++) {
						int Xtemp = combos.get(i).x;
						int Ytemp = combos.get(i).y;
						TABULEIRO[Xtemp][Ytemp] = new Random().nextInt(3);
					}
					combos.clear();
					System.out.println("Ponto!");
					return;
				}
					if(combos.size() == 0) {
						combos.add(new Candy(xx,yy,cor));
					}else if(combos.size() > 0) {
							if(combos.get(combos.size() - 1).candyTYPE == cor) {
								combos.add(new Candy(xx,yy,cor));
							}else {
								combos.clear();
								combos.add(new Candy(xx,yy,cor));
							}
					}
			}
		}
	}
	
	public void render(Graphics g) {
		for(int x = 0; x < WIDTH; x++ ) {
			for(int y = 0; y < HEIGHT; y++ ) {
				g.setColor(Color.white);
				g.drawRect(x*GRID_SIZE + 24, y*GRID_SIZE + 24, GRID_SIZE, GRID_SIZE);
					int doce = TABULEIRO[x][y];
						if(doce == DOCE_0) {
							//g.setColor(Color.green);
							//g.fillRect(x*48+12, y*48+12, 25, 25);
							g.drawImage(sprtDoce_0, x*GRID_SIZE + 12 + 24, y*GRID_SIZE + 12 + 24, 25, 25,null);
						}
						
						if(doce == DOCE_1) {
							//g.setColor(Color.yellow);
							//g.fillRect(x*48+12, y*48+12, 25, 25);
							g.drawImage(sprtDoce_1, x*GRID_SIZE + 12 + 24, y*GRID_SIZE + 12 + 24, 25, 25,null);
						}
						
						if(doce == DOCE_2) {
							//g.setColor(Color.red);
							//g.fillRect(x*48+12, y*48+12, 25, 25);
							g.drawImage(sprtDoce_2, x*GRID_SIZE + 12 + 24, y*GRID_SIZE + 12 + 24, 25, 25,null);
						}
						
						if(Game.selected) {
							int posX = Game.previousX/GRID_SIZE;
							int posY = Game.previousY/GRID_SIZE;
							g.setColor(Color.black);
							g.drawRect(posX*GRID_SIZE + 24,posY*GRID_SIZE+ 24,GRID_SIZE,GRID_SIZE);
						}
			}
		}
	}

}
