package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import entity.Entity;
import object.OBJ_Coin_Bronze;
import object.OBJ_Coin_Gold;
import object.OBJ_Heart;
import object.OBJ_ManaCrystal;
import object.OBJ_Paint_Blue;
import object.OBJ_Paint_Material;
import object.OBJ_Paint_Red;
import object.OBJ_Paint_Yellow;
import object.OBJ_Shield;
import object.OBJ_Wood;

public class UI {

	GamePanel gp;
	Graphics2D g2;
	public Font myFont, myFont80;
	BufferedImage  crystal_full, crystal_blank, coin,wood,flowerb,flowerr,flowery,materialP;
	public boolean messageOn = false;
//	public String message = "";
	ArrayList<String> message = new ArrayList<>();
	ArrayList<Integer> messageCounter = new ArrayList<>();
	// int messageCounter = 0;
	public boolean gameFinished = false;
	public String currentDialogue = "";
	public int commandNum = 0;
	public int maxcommandNum;
	public int titleScreenState = 0;
	public int playerSlotCol = 0; // cursor current position
	public int playerSlotRow = 0;
	public int npcSlotCol = 0;
	public int npcSlotRow = 0;
	int subState = 0;
	int shieldx;
	int shieldy;
	int counter = 0;
	public Entity npc;
	int charIndex = 0;
	String combinedText = "";


	public UI(GamePanel gp) {
		this.gp = gp;

		InputStream is = getClass().getResourceAsStream("/font/Purisa Bold.ttf");
		try {
			myFont = Font.createFont(Font.TRUETYPE_FONT, is);
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// create HUD object
//		Entity heart = new OBJ_Heart(gp);
//		heart_full = heart.image;
//		heart_half = heart.image2;
//		heart_blank = heart.image3;
		Entity crystal = new OBJ_ManaCrystal(gp);
		crystal_full = crystal.image;
		crystal_blank = crystal.image2;
		Entity bronzeCoin = new OBJ_Coin_Gold(gp);
		coin = bronzeCoin.down1;
		Entity woodO = new OBJ_Wood(gp);
		wood =  woodO.down1;
		Entity flowerBlue = new OBJ_Paint_Blue(gp);
		flowerb = flowerBlue.down1;
		Entity paintM = new OBJ_Paint_Material(gp);
		materialP = paintM.down1;
//		Entity flowerYellow = new OBJ_Paint_Yellow(gp);
//		flowery = flowerYellow.down1;
		Entity flowerRed = new OBJ_Paint_Red(gp);
		flowerRed.getImage();
		flowerr = flowerRed.down1;
	

	}

	public void addMessage(String text) {

		message.add(text);
		messageCounter.add(0);

	}

	public void draw(Graphics2D g2) {

		this.g2 = g2;

		g2.setFont(myFont);
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2.setColor(Color.white);

		// title state
		if (gp.gameState == gp.titleState) {
			drawTitleScreen();
		}

		// play state
		if (gp.gameState == gp.playState) {
			drawPlayerLife();
			drawMonsterLife();
			drawMessage();
		}
		// pause state
		if (gp.gameState == gp.pauseState) {
			drawPlayerLife();
			drawPausedScreen();
		}
		// dialogue state
		if (gp.gameState == gp.dialogueState) {
			drawDialogueScreen();
		}
		// character state
		if (gp.gameState == gp.characterState) {
			drawCharacterScreen();
			drawInventory(gp.player, true);
		}
		// options state
		if (gp.gameState == gp.optionsState) {
			drawOptionsScreen();
		}
		// game over state
		if (gp.gameState == gp.gameOverState) {
			drawGameOverScreen();
		}
		// transition state
		if (gp.gameState == gp.transitionState) {
			drawTransition();
		}
		// trade state
		if (gp.gameState == gp.tradeState) {
			drawTradeScreen();
		}
		// sleep state
		if (gp.gameState == gp.sleepState) {
			drawSleepScreen();
		}
		//confirmation state 
		if (gp.gameState == gp.confirmationState) {
			drawConfirmationScreen();
		}
		

	}

	public void drawPlayerLife() {

		int x = gp.tileSize / 2;
		int y = gp.tileSize / 2 -5;
		int i = 0;

		// draw max life
//		while (i < gp.player.maxLife / 2) {
//			g2.drawImage(heart_blank, x, y, null);
//			i++;
//			x += gp.tileSize;
//		}
		
		//draw defense
		double oneScale = (double)(gp.tileSize * gp.player.maxDefense)/gp.player.maxDefense;
		double hpBarValue = oneScale *gp.player.defense;
				
		g2.setColor(new Color(35, 35,35));
		g2.fillRect(gp.tileSize / 2,y, (int)(oneScale *gp.player.maxDefense), 7);
		
	   g2.setColor(new Color(0,247,255));
	   g2.fillRect(gp.tileSize / 2, y, (int)hpBarValue, 5);

	 //draw life
		y += 10 ;
		 oneScale = (double)(gp.tileSize * gp.player.maxLife/2)/(gp.player.maxLife);
		hpBarValue = oneScale*gp.player.life;
		
		g2.setColor(new Color(35, 35,35));
		g2.fillRect(gp.tileSize / 2,y,  (int)(oneScale *gp.player.maxLife), 7);
		
	   g2.setColor(new Color(231,27,34));
	   g2.fillRect(gp.tileSize / 2, y, (int)hpBarValue, 5);

		// draw current life
//		while (i < gp.player.life) {
//			g2.drawImage(heart_half, x, y, null);
//			
//			i++;
//			if (i < gp.player.life) {
//				g2.drawImage(heart_full, x, y, null);
//				
//			}
//			i++;
//			x += gp.tileSize;
//		}
		//x -=5;
		y += 10;
		
		// draw max mana
		while (i < gp.player.maxMana) {
			g2.drawImage(crystal_blank, x, y, null);
			i++;
			x += 35;
		}

		// draw mana
	    x = (gp.tileSize / 2);
		i = 0;
		while (i < gp.player.mana) {
			g2.drawImage(crystal_full, x, y, null);
			i++;
			x += 35;
		}

	}

	public void drawMonsterLife() {
		
		for(int i = 0; i< gp.monster[1].length; i++) {
			
			Entity monster = gp.monster[gp.currentMap][i];
			
			if(monster != null && monster.inCamera() == true) {
		
				if(monster.hpBarOn == true && monster.boss == false) {
			
			double oneScale = (double)gp.tileSize/monster.maxLife;
			double hpBarValue = oneScale * monster.life;
			
			g2.setColor(new Color(35, 35,35));
			g2.fillRect(monster.getScreenX() -1, monster.getScreenY() -16, gp.tileSize+2, 12);
			
		   g2.setColor(new Color(255,0,30));
		   g2.fillRect(monster.getScreenX(), monster.getScreenY() - 15, (int)hpBarValue, 10);
		   
		   monster.hpBarCounter++;
		   
		   if(monster.hpBarCounter > 400) {					   
			   monster.speed = monster.defaultSpeed;
			   monster.onPath = false;
			   monster.hpBarCounter = 0;
			   monster.hpBarOn = false;
		   }
		   }
				else if(monster.boss == true) {
					
					double oneScale = (double)gp.tileSize*8/monster.maxLife;
					double hpBarValue = oneScale * monster.life;
					
					int x = gp.screenWidth/2 - gp.tileSize*4;
					int y = gp.tileSize*10;
					
					g2.setColor(new Color(35, 35,35));
					g2.fillRect(x-1, y-1, gp.tileSize*8 + 2, 22);
					
				   g2.setColor(new Color(255,0,30));
				   g2.fillRect(x, y, (int)hpBarValue, 20);
				  
				   g2.setFont(g2.getFont().deriveFont(Font.BOLD,24f));
				   g2.setColor(Color.white);
				   g2.drawString(monster.name, x + 4, y - 10);
				}
		}
		}
	}
	public void drawMessage() {

		int messageX = gp.tileSize;
		int messageY = gp.tileSize * 4;
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 25F));

		for (int i = 0; i < message.size(); i++) {

			if (message.get(i) != null) {

				g2.setColor(Color.black);
				g2.drawString(message.get(i), messageX + 2, messageY + 2);
				g2.setColor(Color.white);
				g2.drawString(message.get(i), messageX, messageY);

				int counter = messageCounter.get(i) + 1; // massageCounter ++
				messageCounter.set(i, counter);// set counter to array
				messageY += 50;

				if (messageCounter.get(i) > 180) {
					message.remove(i);
					messageCounter.remove(i);
				}
			}
		}
	}

	public void drawTitleScreen() {

		if (titleScreenState == 0) {
			g2.setColor(new Color(0, 0, 0));
			g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

			// title name
			g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
			String text = "Take a Hike";
			int x = getXforCentredText(text);
			int y = gp.tileSize * 3;

			// shadow
			g2.setColor(Color.gray);
			g2.drawString(text, x + 5, y + 5);
			// main color
			g2.setColor(Color.white);
			g2.drawString(text, x, y);

			// player image
			x = gp.screenWidth / 2 - (gp.tileSize * 2) / 2;
			y += gp.tileSize * 2;
			g2.drawImage(gp.player.down1, x, y, gp.tileSize * 2, gp.tileSize * 2, null);

			// menu
			g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40F));

			text = "NEW GAME";
			x = getXforCentredText(text);
			y += gp.tileSize * 3.5;
			g2.drawString(text, x, y);
			if (commandNum == 0) {
				g2.drawString(">", x - gp.tileSize, y);
			}

			text = "LOAD GAME";
			x = getXforCentredText(text);
			y += gp.tileSize;
			g2.drawString(text, x, y);
			if (commandNum == 1) {
				g2.drawString(">", x - gp.tileSize, y);
			}

			text = "QUIT";
			x = getXforCentredText(text);
			y += gp.tileSize;
			g2.drawString(text, x, y);
			if (commandNum == 2) {
				g2.drawString(">", x - gp.tileSize, y);
			}
		}
		// class options menu
//			else if(titleScreenState == 1) {
//			//class selection screen
//			g2.setColor(Color.white);
//			g2.setFont(g2.getFont().deriveFont(42F));
//			
//			String text = "Select Your Class!";
//			int x = getXforCentredText(text);
//			int y = gp.tileSize*3;
//			g2.drawString(text, x, y);
//			
//			text = "Fighter";
//			x = getXforCentredText(text);
//			y += gp.tileSize*3;
//			g2.drawString(text, x, y);
//			if(commandNum == 0) {
//				g2.drawString(">", x-gp.tileSize, y);
//			}
//			
//			text = "Thief";
//			x = getXforCentredText(text);
//			y += gp.tileSize;
//			g2.drawString(text, x, y);
//			if(commandNum == 1) {
//				g2.drawString(">", x-gp.tileSize, y);
//			}
//			
//			text = "Sorcerer";
//			x = getXforCentredText(text);
//			y += gp.tileSize;
//			g2.drawString(text, x, y);
//			if(commandNum == 2) {
//				g2.drawString(">", x-gp.tileSize, y);
//			}
//			
//			text = "Back";
//			x = getXforCentredText(text);
//			y += gp.tileSize*2;
//			g2.drawString(text, x, y);
//			if(commandNum == 3) {
//				g2.drawString(">", x-gp.tileSize, y);
//			}
//		
//		}

	}

	public void drawPausedScreen() {

		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
		String text = "PAUSED";
		int x = getXforCentredText(text);
		int y = gp.screenHeight / 2;
		g2.drawString(text, x, y);
	}

	public void drawDialogueScreen() {
		// window
		int x = gp.tileSize * 3;
		int y = gp.tileSize / 2;
		int width = gp.screenWidth - (gp.tileSize * 6);
		int height = gp.tileSize * 4;

		drawSubWindow(x, y, width, height);

		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 19F));
		x += gp.tileSize;
		y += gp.tileSize;
		
		if(npc.dialogues[npc.dialogueSet][npc.dialogueIndex] != null) {
			
		//	currentDialogue = npc.dialogues[npc.dialogueSet][npc.dialogueIndex];
			
			char characters[] = npc.dialogues[npc.dialogueSet][npc.dialogueIndex].toCharArray();
			
			if(charIndex < characters.length) {
				
				gp.playSE(18);
				String s = String.valueOf(characters[charIndex]);
				combinedText = combinedText + s;
				currentDialogue = combinedText;
				charIndex++;
			}
			
			if(gp.keyH.enterPressed == true) {
				
				charIndex = 0;
				combinedText = "";
				
				if(gp.gameState == gp.dialogueState || gp.gameState == gp.cutsceneState) {
					
					npc.dialogueIndex ++;
					gp.keyH.enterPressed = false;
				}
			}
		}
		else {//if there's no text in the array
			npc.dialogueIndex = 0;
			
			if(gp.gameState == gp.dialogueState) {
				gp.gameState = gp.playState;
			}
			if(gp.gameState == gp.cutsceneState) {
				gp.csManager.scenePhase++;
			}
			
		}

		for (String line : currentDialogue.split("\n")) {
			g2.drawString(line, x, y);
			y += 40;

		}
	}

	public void drawCharacterScreen() {
		// create a frame
		final int frameX = gp.tileSize * 2;
		final int frameY = gp.tileSize;
		final int frameWidth = gp.tileSize * 5;
		final int frameHeight = gp.tileSize * 10;
		drawSubWindow(frameX, frameY, frameWidth, frameHeight);

		// text
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(20F));

		int textX = frameX + 20;
		int textY = frameY + gp.tileSize;
		final int lineHeight = 34;

		// names
		g2.drawString("Level", textX, textY);
		textY += lineHeight;
		g2.drawString("Life", textX, textY);
		textY += lineHeight;
		g2.drawString("Mana", textX, textY);
		textY += lineHeight;
		g2.drawString("Strength", textX, textY);
		textY += lineHeight;
		g2.drawString("Dexterity", textX, textY);
		textY += lineHeight;
		g2.drawString("Attack", textX, textY);
		textY += lineHeight;
		g2.drawString("Defense", textX, textY);
		textY += lineHeight;
		g2.drawString("Exp", textX, textY);
		textY += lineHeight;
		g2.drawString("NextLevel", textX, textY);
		textY += lineHeight;
		g2.drawString("Coin", textX, textY);
		textY += lineHeight + 10;
		g2.drawString("Weapon", textX, textY);
		textY += lineHeight + 15;
		g2.drawString("Shield", textX, textY);
		textY += lineHeight;

		// values
		int tailX = (frameX + frameWidth) - 30;
		// reset textY
		textY = frameY + gp.tileSize;
		String value;

		value = String.valueOf(gp.player.level);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;

		value = String.valueOf(gp.player.life + "/" + gp.player.maxLife);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;

		value = String.valueOf(gp.player.mana + "/" + gp.player.maxMana);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;

		value = String.valueOf(gp.player.strength);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;

		value = String.valueOf(gp.player.dexterity);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;

		value = String.valueOf(gp.player.attack);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;

		value = String.valueOf(gp.player.defense);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;

		value = String.valueOf(gp.player.exp);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;

		value = String.valueOf(gp.player.nextLevelExp);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;

		value = String.valueOf(gp.player.coin);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;

		
		g2.drawImage(gp.player.currentWeapon.down1, tailX - gp.tileSize, textY - 24, null);
		textY += gp.tileSize;
		shieldx = tailX - gp.tileSize;
		shieldy = textY - 23;
		drawShield();
		//g2.drawImage(gp.player.currentShield.down1, tailX - gp.tileSize, textY - 23, null);
		
	}
	public void drawShield() {
		if(gp.player.currentShield.life > 0) {
			g2.drawImage(gp.player.currentShield.down1, shieldx, shieldy, null);
			}
	}
	public void drawInventory(Entity entity, boolean cursor) {

		// frame
		int frameX = 0;
		int frameY = 0;
		int frameWidth = 0;
		int frameHeight = 0;
		int slotCol = 0;
		int slotRow = 0;

		if(entity == gp.player) {
	    frameX = gp.tileSize * 12;
		frameY = gp.tileSize;
		frameWidth = gp.tileSize * 6;
		frameHeight = gp.tileSize * 5;
		slotCol = playerSlotCol;
		slotRow = playerSlotRow;
		}
		else {
			frameX = gp.tileSize * 2;
			frameY = gp.tileSize;
			frameWidth = gp.tileSize * 6;
			frameHeight = gp.tileSize * 5;
			slotCol = npcSlotCol;
			slotRow = npcSlotRow;
		}
		// frame

		drawSubWindow(frameX, frameY, frameWidth, frameHeight);

		// slots
		final int slotXstart = frameX + 20;
		final int slotYstart = frameY + 20;
		int slotX = slotXstart;
		int slotY = slotYstart;
		int slotSize = gp.tileSize + 3;

		// draw player's items
		for (int i = 0; i < entity.inventory.size(); i++) {

			// equip cursor
			if (entity.inventory.get(i) == entity.currentWeapon
					|| entity.inventory.get(i) == entity.currentShield ||
					entity.inventory.get(i) == entity.currentLight) {
				g2.setColor(new Color(240, 190, 90));
				g2.fillRoundRect(slotX, slotY, gp.tileSize, gp.tileSize, 10, 10);
			}

			g2.drawImage(entity.inventory.get(i).down1, slotX, slotY, null);
			
			//display amount
			if(entity == gp.player && entity.inventory.get(i).amount > 1) {
				
				g2.setFont(g2.getFont().deriveFont(30f));
				int amountX;
				int amountY;
				
				String s = "" + entity.inventory.get(i).amount;
				amountX = getXforAlignToRightText(s, slotX + 53);
				amountY = slotY + gp.tileSize;
				
				//shadow
				g2.setColor(new Color(60,60,60));
				g2.drawString(s, amountX, amountY);
				//number
				g2.setColor(Color.white);
				g2.drawString(s, amountX-3, amountY-3);
			}

			slotX += slotSize;

			if (i == 4 || i == 9 || i == 14) {
				slotX = slotXstart;
				slotY += slotSize;
			}
		}

		// cursor
		if(cursor == true) {
		int cursorX = slotXstart + (slotSize * slotCol);
		int cursorY = slotYstart + (slotSize * slotRow);
		int cursorWidth = gp.tileSize;
		int cursorHeight = gp.tileSize;

		// draw cursor
		g2.setColor(Color.white);
		g2.setStroke(new BasicStroke(3));
		g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);

		// description frame
		int dFrameX = frameX;
		int dFrameY = frameY + frameHeight ;
		int dFrameWidth = frameWidth;
		int dFrameHeight = gp.tileSize * 5;

		// draw description text
		int textX = dFrameX + 20;
		int textY = dFrameY + gp.tileSize;
		g2.setFont(g2.getFont().deriveFont(15F));

		int itemIndex = getItemIndexOnSlot(slotCol, slotRow);

		if (itemIndex < entity.inventory.size()) {

			drawSubWindow(dFrameX, dFrameY, dFrameWidth, dFrameHeight);

			for (String line : entity.inventory.get(itemIndex).description.split("\n")) {
				g2.drawString(line, textX, textY);
				textY += 32;
			}
		}
		}
		
	}

	public void drawGameOverScreen() {
		
		g2.setColor(new Color(0, 0, 0,150));
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
		
		int x;
		int y;
		String text;
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 100f));
		
		text = "Game Over";
		//shadow
		g2.setColor(Color.black);
		x = getXforCentredText(text);
		y = gp.tileSize*4;
		g2.drawString(text, x, y);
		//main
		g2.setColor(Color.white);
		g2.drawString(text, x-4, y);
		
		//retry
		g2.setFont(g2.getFont().deriveFont(45f));
		text = "Retry";
		x = getXforCentredText(text);
		y += gp.tileSize*4;
		g2.drawString(text, x, y);
		if(commandNum == 0) {
			g2.drawString(">", x-40, y);
		}
		

		//back to title screen
		text = "Quit";
		x = getXforCentredText(text);
		y += 55;
		g2.drawString(text, x, y);
		if(commandNum == 1) {
			g2.drawString(">", x-40, y);
		}
		
	}
	public void drawOptionsScreen() {

		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(21F));

		// sub window
		int frameX = gp.tileSize * 6;
		int frameY = gp.tileSize;
		int frameWidth = gp.tileSize * 8;
		int frameHeight = gp.tileSize * 10;
		drawSubWindow(frameX, frameY, frameWidth, frameHeight);

		switch (subState) {
		case 0:
			options_top(frameX, frameY);
			break;
		case 1:
			options_fullScreenNotify(frameX, frameY);
			break;
		case 2:
			options_control(frameX, frameY);
			break;
		case 3: 
			options_endGameConfirmation(frameX, frameY); break;
		}
		
		gp.keyH.enterPressed = false;
	}

	public void options_top(int frameX, int frameY) {

		int textX;
		int textY;

		// title
		String text = "Options";
		textX = getXforCentredText(text);
		textY = frameY + gp.tileSize;
		g2.drawString(text, textX, textY);

		// full screen on/off
		textX = frameX + gp.tileSize;
		textY += gp.tileSize * 2;
		g2.drawString("Full screen", textX, textY);
		if(commandNum == 0) {
			g2.drawString(">", textX-25, textY);
			if(gp.keyH.enterPressed == true) {
				if(gp.fullScreenOn == false) {
					gp.fullScreenOn = true;
				}
				else if(gp.fullScreenOn == true) {
					gp.fullScreenOn = false;
				}
				subState = 1;
			}
		}

		// music
		textY += gp.tileSize;
		g2.drawString("Music", textX, textY);
		if(commandNum == 1) {
			g2.drawString(">", textX-25, textY);
		}

		// SE
		textY += gp.tileSize;
		g2.drawString("Sound Effects", textX, textY);
		if(commandNum == 2) {
			g2.drawString(">", textX-25, textY);
		}
		
		// control
		textY += gp.tileSize;
		g2.drawString("Control", textX, textY);
		if(commandNum == 3) {
			g2.drawString(">", textX-25, textY);
			if(gp.keyH.enterPressed == true) {
				subState = 2;
				commandNum = 0;
			}
		}
		
		// end game
		textY += gp.tileSize;
		g2.drawString("End Game", textX, textY);
		if(commandNum == 4) {
			g2.drawString(">", textX-25, textY);
			if(gp.keyH.enterPressed == true) {
				subState = 3;
				commandNum = 0;
			}
		}

		// back
		textY += gp.tileSize*2;
		g2.drawString("Back", textX, textY);
		if(commandNum == 5) {
			g2.drawString(">", textX-25, textY);
			if(gp.keyH.enterPressed == true) {
    			gp.gameState = gp.playState;
    			commandNum = 0;
    		}
		}
		
		//full screen check box
		textX = frameX + (int)(gp.tileSize*5);
		textY = frameY + gp.tileSize*2 + 26;
		g2.setStroke(new BasicStroke(3));
		g2.drawRect(textX, textY, 24, 24);
		if(gp.fullScreenOn == true) {
			g2.fillRect(textX, textY, 24, 24);
		}
		
		//music volume
		textY += gp.tileSize;
		g2.drawRect(textX, textY, 120, 24);// 120/5 = 24
		int volumeWidth = 24 * gp.music.volumeScale;
		g2.fillRect(textX, textY, volumeWidth, 24);
		
		//SE volume
		textY += gp.tileSize;
		g2.drawRect(textX, textY, 120, 24);
		volumeWidth = 24 * gp.se.volumeScale;
		g2.fillRect(textX, textY, volumeWidth, 24);
		
		gp.config.saveConfig();
	}
    public void options_fullScreenNotify(int frameX, int frameY) {
    	
    	 int textX = frameX + gp.tileSize;
    	 int textY = frameY + gp.tileSize*3;
    	 
    	 currentDialogue = "Restart the game \nto change screen mode.";
    	 
    	 for(String line: currentDialogue.split("\n")) {
    		 g2.drawString(line, textX, textY);
    		 textY += 40;
    	 }
    	 
    	 //back
    	 textY = frameY + gp.tileSize*9;
    	 g2.drawString("Back", textX, textY);
    	 if(commandNum == 0) {
    		 g2.drawString(">", textX-25, textY);
    		 if(gp.keyH.enterPressed == true) {
    			 subState = 0;
    		 }
    	 }
    }
    public void options_control(int frameX, int frameY) {
    	
    	int textX;
    	int textY;
    	
    	//title
    	String text = "Control";
    	textX = getXforCentredText(text);
    	textY = frameY + gp.tileSize;
    	g2.drawString(text, textX, textY);
    	
    	textX = frameX + gp.tileSize;
    	textY += gp.tileSize;
    	g2.drawString("Move", textX, textY); textY += gp.tileSize;
    	g2.drawString("Confirm/Attack", textX, textY); textY += gp.tileSize;
    	g2.drawString("Shoot", textX, textY); textY += gp.tileSize;
    	g2.drawString("Character Screen", textX, textY); textY += gp.tileSize;
    	g2.drawString("Pause", textX, textY); textY += gp.tileSize;
    	g2.drawString("Options", textX, textY); textY += gp.tileSize;
    	
    	textX = frameX + gp.tileSize*6;
    	textY = frameY + gp.tileSize*2;
    	g2.drawString("WASD", textX, textY); textY += gp.tileSize;
    	g2.drawString("Enter", textX, textY); textY += gp.tileSize;
    	g2.drawString("F", textX, textY); textY += gp.tileSize;
    	g2.drawString("C", textX, textY); textY += gp.tileSize;
    	g2.drawString("P", textX, textY); textY += gp.tileSize;
    	g2.drawString("ESC", textX, textY); textY += gp.tileSize;
    	
    	//back
    	textX = frameX + gp.tileSize;
    	textY = frameY + gp.tileSize*9;
    	g2.drawString("Back", textX, textY);
    	if(commandNum == 0) {
    		g2.drawString(">", textX-25, textY);
    		if(gp.keyH.enterPressed == true) {
    			subState = 0;
    			commandNum = 3;
    		}
    	}
    }
    public void options_endGameConfirmation(int frameX, int frameY) {
    	
    	
    	int textX = frameX + gp.tileSize*2;
    	int textY = frameY + gp.tileSize*3;
    	
    	currentDialogue = "Quit the game?";
    	g2.drawString(currentDialogue, textX, textY);
    	
    	//yes 
    	String text = "Yes";
    	textX = getXforCentredText(text);
    	textY += gp.tileSize*3;
    	g2.drawString(text, textX, textY);
    	if(commandNum == 0 ) {
    		g2.drawString(">", textX-25, textY);
    		if(gp.keyH.enterPressed == true) {
    			subState = 0;
    			gp.gameState = gp.titleState;
    			gp.resetGame(true);
    		}
    	}
    	
    	//no
    	text = "No";
    	textX = getXforCentredText(text);
    	textY += gp.tileSize;
    	g2.drawString(text, textX, textY);
    	if(commandNum == 1 ) {
    		g2.drawString(">", textX-25, textY);
    		if(gp.keyH.enterPressed == true) {
    			subState = 0;
    			commandNum = 4;
    		}
    	}
    	
    }
    public void drawConfirmationScreen() {
    	
    	g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(21F));

		// sub window
		int frameX = gp.tileSize * 6;
		int frameY = gp.tileSize*2;
		int frameWidth = gp.tileSize * 8;
		int frameHeight = gp.tileSize * 5;
		drawSubWindow(frameX, frameY, frameWidth, frameHeight);
    	
    	
    	int textX =  gp.tileSize*8;
    	int textY =  gp.tileSize*3+15;
    	
    	//int itemI = getItemIndexOnSlot(playerSlotCol, playerSlotRow);
    	
  
    	
    	g2.drawString("Use?", textX, textY);
    	
    	//yes 
    	String text = "Yes";
    	textX = getXforCentredText(text);
    	textY += gp.tileSize*2;
    	g2.drawString(text, textX, textY);
    	if(commandNum == 0 ) {
    		g2.drawString(">", textX-25, textY);
//    		if(gp.keyH.enterPressed == true) {
//    		
//    			
//    		}
    	}
    	
    	//no
    	text = "No";
    	textX = getXforCentredText(text);
    	textY += gp.tileSize;
    	g2.drawString(text, textX, textY);
    	if(commandNum == 1 ) {
    		g2.drawString(">", textX-25, textY);
//    		if(gp.keyH.enterPressed == true) {
//    			commandNum = 0;
//    			
//    		}
    	}
    	}
  
    	
    
	public void drawTransition() {
		
		counter++;
		g2.setColor(new Color(0,0,0,counter*5));
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
		
		if(counter == 50) {
			counter = 0;
			gp.gameState = gp.playState;
			gp.currentMap = gp.eHandler.tempMap;
			gp.player.worldX = gp.tileSize * gp.eHandler.tempCol;
			gp.player.worldY = gp.tileSize * gp.eHandler.tempRow;
			gp.eHandler.previousEventX = gp.player.worldX;
			gp.eHandler.previousEventY = gp.player.worldY;
			gp.changeArea();
		}
	}
	
    public void drawTradeScreen() {
    	switch(subState) {
    	case 0: trade_select(); break;
    	case 1: trade_buy(); break;
    	case 2: trade_sell(); break;
    	}
    	gp.keyH.enterPressed = false;
    	
    }
	public void trade_select() {
		
		npc.dialogueSet = 0;
		drawDialogueScreen();
		
		//draw window
		int x = gp.tileSize * 15;
		int y = gp.tileSize * 4;
		int width = gp.tileSize * 3;
		int height = (int)(gp.tileSize * 3.5);
		drawSubWindow(x, y, width, height);
		
		//draw text
		x += gp.tileSize;
		y += gp.tileSize;
		
		
		g2.drawString("Yes", x, y);
		if(commandNum == 0) {
			g2.drawString(">", x -24, y);
			if(gp.keyH.enterPressed == true) {
				subState = 1;
			}
		}
		if(npc.type == npc.type_merchant) {
			maxcommandNum = 2;
		y += gp.tileSize;
		
		g2.drawString("Sell", x, y);
		if(commandNum == 1) {
			g2.drawString(">", x -24, y);
			if(gp.keyH.enterPressed == true) {
				subState = 2;
			}
		}
		}
		else {
			maxcommandNum = 1;
		}
		y += gp.tileSize;
		
		g2.drawString("Leave", x, y);
		if(commandNum == maxcommandNum) {
			g2.drawString(">", x -24, y);
			if(gp.keyH.enterPressed == true) {
				commandNum = 0;
				npc.startDialogue(npc,1);
			}
		}
		
		
	}
	public void trade_buy() {
		
		//draw player inventory
		drawInventory(gp.player, false);
		//draw npc inventory
		drawInventory(npc, true);
		
		//draw hint window
		g2.setFont(g2.getFont().deriveFont(22F));
		int x = gp.tileSize*2;
		int y = gp.tileSize*9;
		int width = gp.tileSize*6;
		int height = gp.tileSize;
		//drawSubWindow(x, y, width, height);
		g2.drawString("[ESC] Back", x +24, y+60);
		
		//draw player's coin window
		x = gp.tileSize*12;
		y = gp.tileSize*9;
		width = gp.tileSize*6;
		height = gp.tileSize*2;
		drawSubWindow(x, y, width, height);
		g2.drawString("Coins: "+ gp.player.coin, x +24, y+60);
		
		//draw price window
		int itemIndex = getItemIndexOnSlot(npcSlotCol, npcSlotRow);
		
		if(itemIndex < npc.inventory.size()){
			
			if(npc.type == npc.type_maker){
				
				maker(itemIndex,gp.player.searchItemInInventory("Wood"),wood,"Wood");				
			
			}else if(npc.type == npc.type_creator) {
			creator(itemIndex);
				
			}else {
				merchant(itemIndex);
			}
			
			}
		
		
	}
	public void merchant(int itemIndex) {
		
		int x = (gp.tileSize * 2)-20;
		int y = ((gp.tileSize)/2)-15;
		int width = (int)(gp.tileSize * 2.5);
		int height = gp.tileSize;
		
		drawSubWindow(x, y, width, height);
		g2.drawImage(coin, x+10, y+8, 32, 32,null);
					
		int price = npc.inventory.get(itemIndex).price;
		String text = "" + price;
		x = getXforAlignToRightText(text, gp.tileSize*4-20);
		g2.drawString(text, x, y+34);
		
		
		//buy an item
		if(gp.keyH.enterPressed == true) {
			if(npc.inventory.get(itemIndex).price > gp.player.coin) {
				subState = 0;
				npc.startDialogue(npc,2);					
			}
			else if(gp.player.canObtainItem(npc.inventory.get(itemIndex)) == true) {
				gp.player.coin -= npc.inventory.get(itemIndex).price;									
			}
			else {
				subState = 0;
				npc.startDialogue(npc,3);	
			}
		}
		
	}
	public void maker(int itemIndex,int playervalue,BufferedImage image ,String objectName) {
		
		int x = (gp.tileSize * 2)-20;
		int y = ((gp.tileSize)/2)-15;
		int width = (int)(gp.tileSize * 2.5);
		int height = gp.tileSize;
		
		drawSubWindow(x, y, width, height);
		g2.drawImage(coin, x+10, y+8, 32, 32,null);
		
		drawSubWindow(x+width+5, y, width, height);
		g2.drawImage(image, x+width+20, y+5, 32, 32,null);
		
		
		int price = npc.inventory.get(itemIndex).price;
		String text = "" + price;
		x = getXforAlignToRightText(text, gp.tileSize*4-20);
		g2.drawString(text, x, y+34);
		
		int value = npc.inventory.get(itemIndex).value;
		String text2 = "" + value;
		x = getXforAlignToRightText(text, gp.tileSize*4-20);
		g2.drawString(text2, x+width+5, y+34);
		
		if(gp.keyH.enterPressed == true) {
			if(gp.player.searchItemInInventory(objectName) != 999) {
			if(price > gp.player.coin ||
					value > gp.player.inventory.get(playervalue).amount) {
				
				subState = 0;
				npc.startDialogue(npc,2);					
			}
			else if(gp.player.canObtainItem(npc.inventory.get(itemIndex)) == true) {
				gp.player.coin -= price;	
				gp.player.inventory.get(playervalue).amount -= value;
				if(gp.player.inventory.get(playervalue).amount <= 0) {
					gp.player.inventory.remove(playervalue);
				}
			}
			else {
				subState = 0;
				npc.startDialogue(npc,4);	
			}
		}else {
			subState = 0;
			npc.startDialogue(npc,3);	
		}
			}
	}
	public void creator(int itemIndex) {
		
		int x = (gp.tileSize * 2) - 20;
		int y = ((gp.tileSize) / 2) - 15;
		int width = (int) (gp.tileSize * 2.5);
		int height = gp.tileSize;

		

		int value1 = npc.inventory.get(itemIndex).value;
		String text = "" + value1;
		

		int value2 = npc.inventory.get(itemIndex).value2;
		String text2 = "" + value2;			
			drawSubWindow(x, y, width, height);
			g2.drawImage(flowerb, x + 10, y + 8, 32, 32, null);

			drawSubWindow(x + width + 5, y, width, height);
			g2.drawImage(materialP, x + width + 20, y + 10, 32, 32, null);
			
			x = getXforAlignToRightText(text, gp.tileSize * 4 - 20);
			g2.drawString(text, x, y + 34);
			
			x = getXforAlignToRightText(text, gp.tileSize * 4 - 20);
			g2.drawString(text2, x + width + 5, y + 34);
			
			
		if (gp.keyH.enterPressed == true) {

			if (npc.inventory.get(itemIndex).name.equals("Paint Blue")) {

				if (gp.player.searchItemInInventory("Paint Blue") != 999
						&& gp.player.searchItemInInventory("Paint Material") != 999) {

					if (value1 > gp.player.inventory.get(gp.player.searchItemInInventory("Paint Blue")).amount
							|| value2 > gp.player.inventory.get(gp.player.searchItemInInventory("Paint Material")).amount) {

						subState = 0;
						npc.startDialogue(npc, 2);
					} else if (gp.player.canObtainItem(npc.inventory.get(itemIndex)) == true) {

						gp.player.inventory.get(gp.player.searchItemInInventory(npc.inventory.get(itemIndex).name))
								.getImage();
						gp.player.paintb++;

						gp.player.inventory.get(gp.player.searchItemInInventory("Paint Blue")).amount -= value1;
						gp.player.inventory.get(gp.player.searchItemInInventory("Paint Material")).amount -= value2;
						
						if (gp.player.inventory.get(gp.player.searchItemInInventory("Paint Blue")).amount <= 0) {
							gp.player.inventory.remove(gp.player.searchItemInInventory("Paint Blue"));
						}
						if (gp.player.inventory.get(gp.player.searchItemInInventory("Paint Material")).amount <= 0) {
							gp.player.inventory.remove(gp.player.searchItemInInventory("Paint Material"));
						}
					} else {
						subState = 0;
						npc.startDialogue(npc, 3);
					}
				} else {
					subState = 0;
					npc.startDialogue(npc, 2);
				}
			} else if (npc.inventory.get(itemIndex).name.equals("Paint Yellow")) {

				if (gp.player.searchItemInInventory("Paint Yellow") != 999
						&& gp.player.searchItemInInventory("Paint Material") != 999) {


					if (value1 > gp.player.inventory.get(gp.player.searchItemInInventory("Paint Yellow")).amount
							|| value2 > gp.player.inventory.get(gp.player.searchItemInInventory("Paint Material")).amount) {

						subState = 0;
						npc.startDialogue(npc, 2);
					} else if (gp.player.canObtainItem(npc.inventory.get(itemIndex)) == true) {

						gp.player.inventory.get(gp.player.searchItemInInventory(npc.inventory.get(itemIndex).name))
								.getImage();

						gp.player.inventory.get(gp.player.searchItemInInventory("Paint Yellow")).amount -= value1;
						gp.player.inventory.get(gp.player.searchItemInInventory("Paint Material")).amount -= value2;
						
						if (gp.player.inventory.get(gp.player.searchItemInInventory("Paint Yellow")).amount <= 0) {
							gp.player.inventory.remove(gp.player.searchItemInInventory("Paint Yellow"));
						}
						if (gp.player.inventory.get(gp.player.searchItemInInventory("Paint Material")).amount <= 0) {
							gp.player.inventory.remove(gp.player.searchItemInInventory("Paint Material"));
						}
					} else {
						subState = 0;
						npc.startDialogue(npc, 3);
					}
				} else {
					subState = 0;
					npc.startDialogue(npc, 2);
				}
			} else {
				if (gp.player.searchItemInInventory("Paint Red") != 999
						&& gp.player.searchItemInInventory("Paint Material") != 999) {

					

					if (value1 > gp.player.inventory.get(gp.player.searchItemInInventory("Paint Red")).amount
							|| value2 > gp.player.inventory.get(gp.player.searchItemInInventory("Paint Material")).amount) {

						subState = 0;
						npc.startDialogue(npc, 2);
					} else if (gp.player.canObtainItem(npc.inventory.get(itemIndex)) == true) {

						gp.player.inventory.get(gp.player.searchItemInInventory(npc.inventory.get(itemIndex).name))
								.getImage();
						gp.player.paintr++;

						gp.player.inventory.get(gp.player.searchItemInInventory("Paint Red")).amount -= value1;
						gp.player.inventory.get(gp.player.searchItemInInventory("Paint Material")).amount -= value2;
						
						if (gp.player.inventory.get(gp.player.searchItemInInventory("Paint Red")).amount <= 0) {
							gp.player.inventory.remove(gp.player.searchItemInInventory("Paint Red"));
						}
						if (gp.player.inventory.get(gp.player.searchItemInInventory("Paint Material")).amount <= 0) {
							gp.player.inventory.remove(gp.player.searchItemInInventory("Paint Material"));
						}
					} else {
						subState = 0;
						npc.startDialogue(npc, 3);
					}
				} else {
					subState = 0;
					npc.startDialogue(npc, 2);
				}
			}
			
			
			
			}		
	}
	public void trade_sell(){
		
		//draw player inventory
		drawInventory(gp.player, true);
		
		int x;
		int y;
		int width;
		int height;

		// draw hint window
		g2.setFont(g2.getFont().deriveFont(22F));
		x = gp.tileSize * 2;
		y = gp.tileSize * 9;
		width = gp.tileSize * 6;
		height = gp.tileSize * 2;
		drawSubWindow(x, y, width, height);
		g2.drawString("[ESC] Back", x + 24, y + 60);

		// draw player's coin window
		x = gp.tileSize * 12;
		y = gp.tileSize * 9;
		width = gp.tileSize * 6;
		height = gp.tileSize * 2;
		drawSubWindow(x, y, width, height);
		g2.drawString("Coins: " + gp.player.coin, x + 24, y + 60);

		// draw price window
		int itemIndex = getItemIndexOnSlot(playerSlotCol, playerSlotRow);

		if (itemIndex < gp.player.inventory.size()) {

			x = (gp.tileSize * 16) - 23;
			y = ((gp.tileSize) / 2) - 15;
			width = (int) (gp.tileSize * 2.5);
			height = gp.tileSize;
			drawSubWindow(x, y, width, height);
			g2.drawImage(coin, x + 10, y + 8, 32, 32, null);

			int price = gp.player.inventory.get(itemIndex).price/2;
			String text = "" + price;
			x = getXforAlignToRightText(text, gp.tileSize * 18 - 20);
			g2.drawString(text, x, y + 34);

			// sell an item
			if (gp.keyH.enterPressed == true) {

				if (gp.player.inventory.get(itemIndex) == gp.player.currentWeapon
						|| gp.player.inventory.get(itemIndex) == gp.player.currentShield) {
					commandNum = 0;
					subState = 0;
					npc.startDialogue(npc,4);
				} else {
					if (gp.player.inventory.get(itemIndex).amount > 1) {
						gp.player.inventory.get(itemIndex).amount--;
					} else {
						gp.player.inventory.remove(itemIndex);
					}
					gp.player.coin += price;
				}
			}
		}
	}
	public void drawSleepScreen() {
		
		counter ++;
		
		if(counter < 120) {
			gp.eManager.lighting.filterAlpha += 0.01f;
			if(gp.eManager.lighting.filterAlpha > 1f) {
				gp.eManager.lighting.filterAlpha = 1f;
			}
		}
		if(counter >= 120) {
			gp.eManager.lighting.filterAlpha -= 0.01f;
			if(gp.eManager.lighting.filterAlpha <= 0f) {
				gp.eManager.lighting.filterAlpha = 0f;
				counter = 0;
				gp.eManager.lighting.dayState = gp.eManager.lighting.day;
				gp.eManager.lighting.dayCounter = 0;
				gp.gameState = gp.playState;
				gp.player.getPlayerImage();
			}
		}
	}
	
    public int getItemIndexOnSlot(int slotCol, int slotRow) {
		int itemIndex = slotCol + (slotRow * 5);
		return itemIndex;
	}

	public void drawSubWindow(int x, int y, int width, int height) {

		Color c = new Color(0, 0, 0, 200);
		g2.setColor(c);
		g2.fillRoundRect(x, y, width, height, 35, 35);

		c = new Color(255, 255, 255);
		g2.setColor(c);
		g2.setStroke(new BasicStroke(5));
		g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
	}

	public int getXforCentredText(String text) {
		int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = gp.screenWidth / 2 - length / 2;
		return x;
	}

	public int getXforAlignToRightText(String text, int tailX) {
		int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = tailX - length;
		return x;
	}
}
