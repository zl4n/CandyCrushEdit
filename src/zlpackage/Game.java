package zlpackage;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable, MouseListener, MouseMotionListener {
	
	public static final int WIDTH = 288, HEIGHT = 288;
	public static  final int SCALE = 2;
	public static  final int FPS = 1000/60;
	
	public BufferedImage Image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
	
	public Tabuleiro tabuleiro;
	
	public static boolean selected = false;
	public static int previousX = 0, previousY = 0;
	public static int nextX = -1, nextY = -1;  
	
	public static int points = 0;
	public static JFrame frame;
	
	public  Game() {
		this.setPreferredSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		tabuleiro = new Tabuleiro();
	}

	public static void main(String[] args){
		
		Game.frame = new JFrame("Candy Crush Edit");
		Game game    = new Game();
		frame.add(game);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		new Thread(game).start();
	}
	
	public void update() {
		
		tabuleiro.update();
		
		
		if(Game.selected && (Game.nextX != -1 && Game.nextY != -1)) {
			
			
			if(Game.nextX < 0 || Game.nextX >= Tabuleiro.GRID_SIZE * Tabuleiro.WIDTH
					|| Game.nextY < 0 || Game.nextY >= Tabuleiro.GRID_SIZE * Tabuleiro.HEIGHT	
						) {
					Game.nextX = -1;
					Game.nextY = -1;
					Game.selected = false;
				}
			
			
			
			int posX1 = Game.previousX/48;
			int posY1 = Game.previousY/48;
			
			int posX2 = Game.nextX/48;
			int posY2 = Game.nextY/48;
			
			if((posX2 == posX1 + 1 || posX2 == posX1 - 1) &&
					posY2 == posY1 || posY2 == posY1 -1 ||  posY2 == posY1 +1 ) {
							
							 // DESCOMENTE SE QUER EVITAR MOVIMENTOS NA DIAGONAL
							if((posX2 >= posX1 + 1 || posX2 <= posX1 - 1) &&
									(posY2 >= posY1 + 1 || posY2 <= posY1 - 1)	) {
								
								System.out.println("nao pode mover");
								return;
							}
							
						
							int T1 = Tabuleiro.TABULEIRO[posX2][posY2];
							int T2 = Tabuleiro.TABULEIRO[posX1][posY1];
							Tabuleiro.TABULEIRO[posX1][posY1] = T1;
							Tabuleiro.TABULEIRO[posX2][posY2] = T2;
							Game.nextX = -1;
							Game.nextY = -1;
							Game.selected = false;
							System.out.println("moveu");
						}else {
							System.out.println("nao pode mover");
						}
					}else {
						
						//System.out.println("nao pode mover");
					}
					
				}	
	
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
			if(bs == null) {
				this.createBufferStrategy(3);
				return;
			}
			
			Graphics g = Image.getGraphics();
			
			g.setColor(new Color(28,201,148));
			g.fillRect(0, 0, WIDTH, HEIGHT);
			tabuleiro.render(g);
			
			g = bs.getDrawGraphics();
			g.drawImage(Image, 0, 0,WIDTH*SCALE,HEIGHT*SCALE,null);
			
			bs.show();
	}
	
public void run() {
		
		double fps = 60.0;

		while(true){
		
				update();
				render();
					try {
						Thread.sleep((int)(1000/fps));
					}catch(InterruptedException e) {}
			}
			
		}



		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		
		
		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		
		
		@Override
		public void mouseClicked(MouseEvent e) {
			if(Game.selected == false) {
				Game.selected = true;
				Game.previousX = e.getX()/2 - 24;
				Game.previousY = e.getY()/2 - 24;
			}else {
				Game.nextX = e.getX()/2 - 24;
				Game.nextY = e.getY()/2 - 24;
			}
			
		}
		
		
		
		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		
		
		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		
		
		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		
		
		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}


	




}

