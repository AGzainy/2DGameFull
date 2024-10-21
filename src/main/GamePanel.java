package main;


import java.awt.Color;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JPanel;

import ai.PathFinder;
import data.SaveLoad;
import entity.Entity;
import entity.Player;
import environment.EnvironmentManager;
import tile_interactive.InteractiveTile;
import tiles.MiniMap;
import tiles.TileManager;

public class GamePanel extends JPanel implements Runnable {
	
	// screen settings
	final int originalTileSize = 16; // 16x16 tile
	final int scale = 3;
	
	public final int tileSize = originalTileSize * scale; //40X40 tile
	public final int maxScreenCol = 20;
	public final int maxScreenRow = 12;
	public final int screenWidth = tileSize * maxScreenCol; //768 pixels
	public final int screenHeight = tileSize * maxScreenRow;//576 pixels
	
	//world settings
	public int maxWorldCol =80;
	public int maxWorldRow =80;
	public final int maxMap = 10;
	public int currentMap = 0;
	
	// for full screen
	int screenWidth2 = screenWidth;
	int screenHeight2 = screenHeight;
	BufferedImage tempScreen;
	Graphics2D g2;
	public boolean fullScreenOn = false;
	
	//frame per second		
	int fps = 60;
	
	//system
	public KeyHandler keyH = new KeyHandler(this);
	Sound music = new Sound();
	Sound se = new Sound();
	public CollisionChecker cChecker = new CollisionChecker(this);
	public AssetsSetter aSetter = new AssetsSetter(this);
	public TileManager tileM = new TileManager(this);
	public UI ui = new UI(this);
	public EventHandler eHandler = new EventHandler(this);
	Config config = new Config(this);
	public PathFinder pFinder = new PathFinder(this);
	public EnvironmentManager eManager = new EnvironmentManager(this);
	MiniMap map = new MiniMap(this);
	SaveLoad saveLoad = new SaveLoad(this);
	public EntityGenerator eGenerator = new EntityGenerator(this);
	public CutsceneManager csManager = new CutsceneManager(this);
	Thread gameThread;
	
	// entity and objects
	public Player player = new Player(this, keyH);
	public Entity obj[][] = new Entity[maxMap][50];
	public Entity npc[][] = new Entity[maxMap][10];
	public Entity monster[][] = new Entity[maxMap][20];
	public InteractiveTile iTile[][] = new InteractiveTile[maxMap][50];
	//public  ArrayList<Entity> projectileList = new ArrayList<>();
	public Entity projectile[][] = new Entity[maxMap][20];
	public ArrayList<Entity> particleList = new ArrayList<>();
	ArrayList<Entity> entityList = new ArrayList<>();
	
	// game state
	public int gameState;
	public final int titleState = 0;
	public final int playState =1;
	public final int pauseState = 2;
	public final int dialogueState = 3; 
	public final int characterState = 4;
	public final int optionsState = 5;
	public final int gameOverState = 6;
	public final int transitionState = 7;
	public final int tradeState = 8;
	public final int sleepState = 9;
	public final int mapState = 10;
	public final int confirmationState = 11;
	public final int cutsceneState = 12;
	
	//Area
	public int currentArea;
	public final int outside = 50;
	public final int indoor = 51;
	public final int dungeon = 52;
	public int nextArea;
	
	//others
	public int keyLife = 2;
	public boolean bossBattleOn = false;
	
	public GamePanel() {
		
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true); //enabling this can improve game's rendering performance. 
		this.addKeyListener(keyH);
		this.setFocusable(true);//gamePanel can be focused to receive key input.
		
		
	}
	//i call this method in main before starting the game thread
	public void setupGame() {
		
		aSetter.setObject();
		aSetter.setNPC();
		aSetter.setMonster();
		aSetter.setInterActiveTile();
		eManager.setup();
		
		gameState = titleState;
		currentArea = outside;
		
		tempScreen = new BufferedImage(screenWidth,screenHeight, BufferedImage.TYPE_INT_ARGB_PRE);
		g2 = (Graphics2D)tempScreen.getGraphics();
		
		if(fullScreenOn == true) {
		setFullScreen();
		}
	}
	public void resetGame(boolean restart) {

		currentArea = outside;
		removeTempEntity();
		bossBattleOn = false;
		player.setDefaultPosition();
		player.restoreStatus();
		aSetter.setNPC();
		aSetter.setMonster();
		player.resetCounter();
		
		if(restart == true) {
			player.setDefaultValues();
			aSetter.setObject();
			aSetter.setInterActiveTile();
			eManager.lighting.resetDay();
		}
	}
	public void setFullScreen() {
		
		//get local screen device
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice gd = ge.getDefaultScreenDevice();
		gd.setFullScreenWindow(Main.window);
		
		//get full screen width and height
		screenWidth2 = Main.window.getWidth();
		screenHeight2 = Main.window.getHeight();
	}
	public void startGameThread() {
		
		gameThread = new Thread(this);
		gameThread.start();
	}

	@Override
	public void run() {
		
		double drawInterval = 1000000000/fps;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		
		while(gameThread != null) {
			
			currentTime = System.nanoTime();
			
			delta += (currentTime - lastTime)/drawInterval;
			lastTime = currentTime;
			
			if(delta >= 1) {
				update(); 
	//			repaint();
				drawToTempScreen(); //draw everything on the buffered image
				drawToScreen();//draw the buffered image on the screen.
				delta --;
				
			}
			
		}
		
	}
	
	//or
//	public void run() {
//		
//		
//		double drawInterval = 1000000000/fps; //0.01666bseconds
//		double nextDrawTime = System.nanoTime() + drawInterval;
//		
//		while(gameThread != null) {
//
//		//1- updates info "player's position". 
//		update();
//		//2- draws the screen with the updates info
//		repaint(); //the way to call paintComponent.
//		
//		
//		try {
//			double remainingTime = nextDrawTime - System.nanoTime();
//			remainingTime = remainingTime/1000000;
//			
//			if(remainingTime < 0) {
//				remainingTime = 0;
//			}
//
//			Thread.sleep((long) remainingTime);
//			
//			nextDrawTime += drawInterval;
//			
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		
//	}}

	public void update() {
		
		if(gameState == playState) {
		//player
		player.update();
		//npc
		for(int i =0; i < npc[1].length; i++) {
			if(npc[currentMap][i] != null) {
				npc[currentMap][i].update();
			}
		}
		//monster
		for(int i =0; i< monster[1].length; i++) {
			if(monster[currentMap][i]!= null) {
				if(monster[currentMap][i].alive == true && monster[currentMap][i].dying == false) {
					monster[currentMap][i].update();
				}
				if(monster[currentMap][i].alive == false) {
					monster[currentMap][i].checkDrop();
					monster[currentMap][i] = null;
				}
			}
		}
		//projectile
		for(int i =0; i< projectile[1].length; i++) {
			if(projectile[currentMap][i] != null) {
				if(projectile[currentMap][i].alive == true) {
					projectile[currentMap][i].update();
				}
				if(projectile[currentMap][i].alive == false) {
					projectile[currentMap][i] = null;
				}
			}
		}
		//particles
		for(int i =0; i< particleList.size(); i++) {
			if(particleList.get(i) != null) {
				if(particleList.get(i).alive == true) {
					particleList.get(i).update();
				}
				if(particleList.get(i).alive == false) {
					particleList.remove(i);
				}
			}
		}
		//interactive tiles
		for(int i = 0; i < iTile[1].length; i++) {
			if(iTile[currentMap][i] != null) {
				iTile[currentMap][i].update();
			}
		}
		eManager.update();
		
		}
		if(playState == pauseState) {
			//nothing
		}
	}
	public void drawToTempScreen() {
		  // debug:
        long drawStart = 0;

        if(keyH.checkDrawTime == true) {
        drawStart = System.nanoTime();
        }
        
        //title screen
        if(gameState == titleState) {
        	ui.draw(g2);
        }
        else if(gameState == mapState){
        	map.drawFullMapScreen(g2);
        }
        //others
        else {
        //tile:
        tileM.draw(g2);
        
        //iteractive tiles
        for(int i = 0; i < iTile[1].length; i++) {
        	if(iTile[currentMap][i] != null) {
        		iTile[currentMap][i].draw(g2);
        	}
        }
        
        //add entity to the list
        entityList.add(player);
        
        for(int i = 0; i < npc[1].length; i++) {
        	if(npc[currentMap][i] != null) {
        		entityList.add(npc[currentMap][i]);
        	}
        }
        for(int i = 0; i < obj[1].length; i++) {
            if(obj[currentMap][i] != null) {
            	entityList.add(obj[currentMap][i]);
            	}
        }
        for(int i = 0; i < monster[1].length; i++) {
            if(monster[currentMap][i] != null) {
            	entityList.add(monster[currentMap][i]);
            	}
        }
        for(int i = 0; i < projectile[1].length; i++) {
            if(projectile[currentMap][i] != null) {
            	entityList.add(projectile[currentMap][i]);
            	}
        }
        for(int i = 0; i < particleList.size(); i++) {
            if(particleList.get(i) != null) {
            	entityList.add(particleList.get(i));
            	}
        }
        
        //sort
        Collections.sort(entityList, new Comparator<Entity>() {

			@Override
			public int compare(Entity e1, Entity e2) {
				
				int result =  Integer.compare(e1.worldY, e2.worldY);
				return result;
			}
        	
        });
        
        //draw entities
        for(int i = 0; i < entityList.size(); i++) {
        	entityList.get(i).draw(g2);
        }
        //empty entity list
        entityList.clear();
        
        //environment
        eManager.draw(g2);
        
        //miniMap
        map.drawMinimap(g2);
        
        //cutscene
        csManager.draw(g2);
        
        //UI
        ui.draw(g2);
        }
        //debug:
        if(keyH.checkDrawTime == true) {

        long drawEnd = System.nanoTime();
        long passed = drawEnd - drawStart;
        g2.setColor(Color.white);
        g2.drawString("Draw Time: " + passed, 10, 400);
        g2.drawString("Col: " +( player.worldX + player.solidArea.x)/tileSize , 10, 450);
        g2.drawString("Row: " +( player.worldY + player.solidArea.y)/tileSize , 10, 480);
        //System.out.println("draw Time "+ passed);
        }
	}
	
       
       // g2.dispose(); 
        //dispose of this graphics context and release any system resources that it is using.
	public void drawToScreen() {
		Graphics g = getGraphics();
		g.drawImage(tempScreen, 0, 0, screenWidth2, screenHeight2, null);
		g.dispose(); 
	}
	public void playMusic(int i) {
		music.setFile(i);
		music.play();
		music.loop();
	}
	public void stopMusic() {
		music.stop();
	}
	public void playSE(int i) {
		se.setFile(i);
		se.play();
	}
	public void changeArea() {
		
		if(nextArea != currentArea) {
			
			stopMusic();
			
			if(nextArea == outside) {
				playMusic(0);
			}
			if(nextArea == indoor) {
				playMusic(23);
			}
			if(nextArea == dungeon) {
				playMusic(22);
			}
			aSetter.setNPC();
			
		}
		currentArea = nextArea;
		aSetter.setMonster();
		
	}
	public void removeTempEntity() {
		
		for(int mapNum = 0; mapNum < maxMap; mapNum++) {
			
			for(int i = 0; i < obj[1].length; i++) {
				if(obj[mapNum][i] != null && obj[mapNum][i].temp == true) {
					obj[mapNum][i] = null;
				}
			}
		}
	}
	

	
	
}
