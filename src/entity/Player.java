package entity;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.GamePanel;
import main.KeyHandler;
import object.OBJ_Fireball;
import object.OBJ_Paint_Purple;
import object.OBJ_Shield;
import object.OBJ_Sword;

public class Player extends Entity {
	
	KeyHandler keyH;
	
	public final int screenX;
	public final int screenY;
	int standCounter = 0;
	public boolean attackCanceled = false;	
	public boolean lightUpdated = false;
	public int paintr = 0;
	public int paintb = 0;
	public boolean canUse;
	
	public Player(GamePanel gp, KeyHandler keyH) {
		
		super(gp);
		
		//this.gp = gp;
		this.keyH = keyH;
		
		screenX = gp.screenWidth/2 - (gp.tileSize/2);
		screenY = gp.screenHeight/2 - (gp.tileSize/2); //subtract a half tile length to be in middle
		
		// solid area
		solidArea = new Rectangle();
		solidArea.x = 8;
		solidArea.y = 16;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 32;
		solidArea.height = 32;
		
//		attackArea.width = 36;
//		attackArea.height = 36;
		
		setDefaultValues();
	}
	
	public void setDefaultValues() {
//		worldX = gp.tileSize * 37;
//		worldY = gp.tileSize * 9;
//		
		worldX = gp.tileSize * 55;
		worldY = gp.tileSize * 64;
		defaultSpeed = 4;
		speed = defaultSpeed;
		direction = "down";
		
		//player status
		maxLife = 6;
		maxDefense = 3;
		life = maxLife ;
		level = 1;
		maxMana = 0;
		mana = 0;//maxMana;
		ammo = 10;
		strength = 1;
		dexterity = 1;
		exp = 0;
		nextLevelExp = 5;
		coin = 100;
		currentWeapon = new OBJ_Sword(gp);
		currentShield = new OBJ_Shield(gp);
		currentLight = null;
		projectile = new OBJ_Fireball(gp);
		//projectile = new OBJ_Rock(gp);
		attack = getAttack();
		defense = 0;//getDefense();
		
		getPlayerImage();
		getPlayerAttackImage();
		getGuardImage();
		setItems();
		setDialogue();
		
		
	}
	public void setDefaultPosition() { 
		
		gp.currentMap = 0;
		worldX = gp.tileSize * 55;
		worldY = gp.tileSize * 64;
		speed = 4;
		direction = "down";
	}
	public void setDialogue() {
		
		dialogues[0][0] = "You are level "+level+" now!";
		
	}
	public void restoreStatus() {
		
		life = maxLife;
		mana = maxMana;
		invincible = false;
		transparenty = false;
		attacking = false;
		guarding = false;
		knockBack = false;
		lightUpdated = true;
		speed = defaultSpeed;
		
	}
 	public void setItems() {
 		
 		inventory.clear();
		inventory.add(currentWeapon);
		inventory.add(currentShield);
		
		
	}
	public int getAttack() {
		attackArea = currentWeapon.attackArea;
		motion1_duration = currentWeapon.motion1_duration;
		motion2_duration = currentWeapon.motion2_duration;
		return attack = strength * currentWeapon.attackValue;
	}
	public int getDefense() {
		return  currentShield.defenseValue;
	}
	public int getCurrentWeaponSlot() {
		int currentWeaponSlot = 0;
		for(int i = 0; i < inventory.size(); i++) {
			if(inventory.get(i) == currentWeapon) {
				currentWeaponSlot = i;
			}
		}
		return currentWeaponSlot;
	}
	public int getCurrentShieldSlot() {
		int currentShieldSlot = 0;
		for(int i = 0; i < inventory.size(); i++) {
			if(inventory.get(i) == currentShield) {
				currentShieldSlot = i;
			}
		}
		return currentShieldSlot;
	}
	public void getPlayerImage() {
		
			up1 = setup("/player/boyUp",gp.tileSize, gp.tileSize);
			up2 = setup("/player/boyUp2",gp.tileSize, gp.tileSize);
			down1 = setup("/player/boyDown",gp.tileSize, gp.tileSize);
			down2 = setup("/player/boyDown2",gp.tileSize, gp.tileSize);
			left1 = setup("/player/boyLeft",gp.tileSize, gp.tileSize);
			left2 = setup("/player/boyLeft2",gp.tileSize, gp.tileSize);
			right1 = setup("/player/boyRight",gp.tileSize, gp.tileSize);
			right2 = setup("/player/boyRight2",gp.tileSize, gp.tileSize);
	}
	public void getSleepingImage(BufferedImage image) {
		
		up1 = image;
		up2 = image;
		down1 = image;
		down2 = image;
		left1 = image;
		left2 = image;
		right1 = image;
	}
	public void getPlayerAttackImage() {
		
		if(currentWeapon.type == type_sword) {
		attackUp1 = setup("/player/boyAttackUp", gp.tileSize, gp.tileSize*2);
		attackUp2 = setup("/player/boyAttackUp2", gp.tileSize, gp.tileSize*2);
		attackDown1 = setup("/player/boyAttackDown", gp.tileSize, gp.tileSize*2);
		attackDown2 = setup("/player/boyAttackDown2", gp.tileSize, gp.tileSize*2);
		attackLeft1 = setup("/player/boyAttackLeft", gp.tileSize*2, gp.tileSize);
		attackLeft2 = setup("/player/boyAttackLeft2", gp.tileSize*2, gp.tileSize);
		attackRight1 = setup("/player/boyAttackRight", gp.tileSize*2, gp.tileSize);
		attackRight2 = setup("/player/boyAttackRight2", gp.tileSize*2, gp.tileSize);
		}
		if(currentWeapon.type == type_axe || currentWeapon.type == type_shovel) {
			attackUp1 = setup("/player/boyAxeUp", gp.tileSize, gp.tileSize*2);
			attackUp2 = setup("/player/boyAxeUp2", gp.tileSize, gp.tileSize*2);
			attackDown1 = setup("/player/boyAxeDown", gp.tileSize, gp.tileSize*2);
			attackDown2 = setup("/player/boyAxeDown2", gp.tileSize, gp.tileSize*2);
			attackLeft1 = setup("/player/boyAxeLeft", gp.tileSize*2, gp.tileSize);
			attackLeft2 = setup("/player/boyAxeLeft2", gp.tileSize*2, gp.tileSize);
			attackRight1 = setup("/player/boyAxeRight", gp.tileSize*2, gp.tileSize);
			attackRight2 = setup("/player/boyAxeRight2", gp.tileSize*2, gp.tileSize);
			}
		if(currentWeapon.type == type_pickaxe) {
			attackUp1 = setup("/player/boyAxeUp", gp.tileSize, gp.tileSize*2);
			attackUp2 = setup("/player/boyAxeUp2", gp.tileSize, gp.tileSize*2);
			attackDown1 = setup("/player/boyAxeDown", gp.tileSize, gp.tileSize*2);
			attackDown2 = setup("/player/boyAxeDown2", gp.tileSize, gp.tileSize*2);
			attackLeft1 = setup("/player/boyAxeLeft", gp.tileSize*2, gp.tileSize);
			attackLeft2 = setup("/player/boyAxeLeft2", gp.tileSize*2, gp.tileSize);
			attackRight1 = setup("/player/boyAxeRight", gp.tileSize*2, gp.tileSize);
			attackRight2 = setup("/player/boyAxeRight2", gp.tileSize*2, gp.tileSize);
			}

	}
	public void getGuardImage() {
		if(currentShield.life < 15) {
		guardUp = setup("/player/boy_guard_up", gp.tileSize, gp.tileSize);
		guardDown = setup("/player/boy_guard_down", gp.tileSize, gp.tileSize);
		guardRight = setup("/player/boy_guard_right", gp.tileSize, gp.tileSize);
		guardLeft = setup("/player/boy_guard_left", gp.tileSize, gp.tileSize);
		}
		else {
			guardUp = setup("/player/boy_guard_up_diamond", gp.tileSize, gp.tileSize);
			guardDown = setup("/player/boy_guard_down_diamond", gp.tileSize, gp.tileSize);
			guardRight = setup("/player/boy_guard_right_diamond", gp.tileSize, gp.tileSize);
			guardLeft = setup("/player/boy_guard_left_diamond", gp.tileSize, gp.tileSize);
			
		}

	}
	public void update() {
		
		if (knockBack == true) {
			
			//check collision
			collisionOn = false;
			gp.cChecker.checkTile(this);
			gp.cChecker.checkObject(this, true);
			gp.cChecker.checkEntity(this, gp.npc);
			gp.cChecker.checkEntity(this, gp.monster);
			gp.cChecker.checkEntity(this, gp.iTile);		 
			
			
			if (collisionOn == true) {
				knockBackCounter = 0;
				knockBack = false;
				speed = defaultSpeed;

			} 
			else if(collisionOn == false) {
    			switch(knockBackDirection) {
    			case "up": worldY -= speed; break;
    			case "down": worldY += speed; break;
    			case "left": worldX -= speed; break;
    			case "right": worldX += speed; break;
    			}
    		}
			
			knockBackCounter++;
			if (knockBackCounter == 10) {
				knockBackCounter = 0;
				knockBack = false;
				speed = defaultSpeed;
			}
    	}
		
		else if(attacking == true) {
			attacking();
		}
		else if(keyH.spacePressed == true) {
			guarding = true;
			guardCounter ++;
		}
		else if(keyH.upPressed == true || keyH.downPressed == true ||
				keyH.leftPressed == true || keyH.rightPressed == true || keyH.enterPressed == true) {
		
		if(keyH.upPressed == true) {
			direction = "up";
		}
		else if(keyH.downPressed == true) {
			direction = "down";
		}
		else if(keyH.leftPressed == true) {
			direction = "left";
		}
		else if(keyH.rightPressed == true) {
			direction = "right";
		}
		
		//check tile collision
		collisionOn = false;
		gp.cChecker.checkTile(this);
		
		//check object collision
		 int objIndex = gp.cChecker.checkObject(this, true);
		 pickUpObject(objIndex);
		 
		 //check NPC collision
		 int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
		 interactNPC(npcIndex);
		 
		 //check monster collision
		 int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
		 monsterContact(monsterIndex);
		 
		 //check interactive tile collision
		 int iTileIndex = gp.cChecker.checkEntity(this, gp.iTile);		 
		 
		 //check event
		 gp.eHandler.checkEvent();
		 
		
		//if collision is false player can move
		if(collisionOn == false && keyH.enterPressed == false) {
			
			switch(direction) {
			case "up": worldY -= speed; break;
			case "down": worldY += speed; break;
			case "left": worldX -= speed; break;
			case "right": worldX += speed; break;
			}
		}
		
		if(keyH.enterPressed == true && attackCanceled == false) {
			
			gp.playSE(7);
			attacking = true;
			spriteCounter = 0;
		}
		
		attackCanceled = false;
		gp.keyH.enterPressed = false;
		guarding = false;
		guardCounter = 0;

		spriteCounter ++;
		if(spriteCounter > 12 ) {
			if(spriteNum ==1) {
				spriteNum = 2;
			}else if(spriteNum == 2) {
				spriteNum = 1;
			}
			spriteCounter = 0;
		}
		}
		else {
			standCounter ++;
			
			if(standCounter == 20) {
			spriteNum = 1;
			standCounter =0;
		}
			guarding = false;
			guardCounter = 0;
		}
		
		if(gp.keyH.shoot == true && projectile.alive == false 
				&& shotAvailableCounter == 30 && projectile.hasResource(this) == true) {
			
			//set default coordinates, direction and user.
			projectile.set(worldX, worldY, direction, true, this);
			
			//subtract cost (mana, ammo, etc)
			projectile.subtractResource(this);
			
			//add it to list, check vacancy
			for(int i = 0; i < gp.projectile[1].length; i++) {
				if(gp.projectile[gp.currentMap][i] == null) {
					gp.projectile[gp.currentMap][i] = projectile;
					break;
				}
			}
			
			shotAvailableCounter = 0;
			
			gp.playSE(10);
		}
		
		if(mana < maxMana) {
			manaCounter ++;
			if(manaCounter > 280) {
				mana ++;
				manaCounter =0;
			}
		}
		
		//outside of if statement so player receive damage even when standing still.
		if(invincible == true) {
			invincibleCounter++;
			if(invincibleCounter > 60) {
				invincible = false;
				transparenty = false;
				invincibleCounter = 0;
			}
			
		}
		if(shotAvailableCounter < 30) {
			shotAvailableCounter ++;
		}
		if(life > maxLife) {
			life = maxLife;
		}
		if(mana > maxMana) {
			mana = maxMana;
		}
		if(life <= 0) {
			gp.gameState = gp.gameOverState;
			gp.ui.commandNum = -1;
			gp.stopMusic();
			gp.playSE(13);
		}
	}

	public void interactNPC(int i) {
				
		if(i != 999) {
			if(gp.keyH.enterPressed == true) {
			attackCanceled = true;
 			gp.npc[gp.currentMap][i].speak();
		}
			gp.npc[gp.currentMap][i].move(direction);
	  }
	}
	public void pickUpObject(int i) {
		if( i != 999) {
			
			//pickup items 
			if(gp.obj[gp.currentMap][i].type == type_pickup) {
				
				gp.obj[gp.currentMap][i].use(this);
				gp.obj[gp.currentMap][i] = null;
			}
			//obstacle
			else if(gp.obj[gp.currentMap][i].type == type_obstacle) {
				if(keyH.enterPressed == true) {
					attackCanceled = true;
					gp.obj[gp.currentMap][i].interact();
				}
			}
			else {
			//inventory items
			String text;
			
			if(canObtainItem(gp.obj[gp.currentMap][i]) == true) {				
				gp.playSE(1);
				text = "Got " + gp.obj[gp.currentMap][i].gotName +"!";
			}
			else {
				text = "Inventory is full.";
			}
			gp.ui.addMessage(text);
			gp.obj[gp.currentMap][i] = null;
		}
		}
		
	}
	public void monsterContact(int i) {
		if(i != 999) {
			
			if(invincible == false && gp.monster[gp.currentMap][i].dying == false) {
				gp.playSE(6);
			
				int damage = gp.monster[gp.currentMap][i].attack;
				if(damage < 0) {
					damage = 0;
				}
				if (defense <= 0) {
					life -= damage;
				}
				if(defense > 0) {
					defense -= damage;
					if(defense <= 0) {
						defense =0;
//						deleteItem(currentShield);
//						gp.ui.drawShield();
						
					}
				}
			invincible = true;
			transparenty = true;
		}}
	}
	public void damageMonster(int i, Entity attacker,int attack, int knockBackPower) {
		if(i != 999) {
			
			if(gp.monster[gp.currentMap][i].invincible == false && gp.monster[gp.currentMap][i].dying == false) {
				
				gp.playSE(5);
				if(knockBackPower > 0) {
				knockBack(gp.monster[gp.currentMap][i], attacker,knockBackPower);
				}
				
				if(gp.monster[gp.currentMap][i].offBalance == true) {
					attack *= 5;
				}
				
				int damage = attack - gp.monster[gp.currentMap][i].defense;
				if(damage < 0) {
					damage = 0;
				}
				gp.monster[gp.currentMap][i].life -= damage;
				gp.ui.addMessage(damage+" damage!");
				
				gp.monster[gp.currentMap][i].invincible = true;
				if(knockBack == false) {
				gp.monster[gp.currentMap][i].damageReaction();
				}
				if(gp.monster[gp.currentMap][i].life <= 0) {
					gp.monster[gp.currentMap][i].dying = true;
					gp.ui.addMessage(gp.monster[gp.currentMap][i].name+" killed!");
					gp.ui.addMessage("Exp "+gp.monster[gp.currentMap][i].exp);
					exp += gp.monster[gp.currentMap][i].exp;
					checkLevelUp();
				}
			}
		}
	}
    public void damageInteractiveTile(int i) {
		
		if(i != 999 && gp.iTile[gp.currentMap][i].destructible == true 
				&& gp.iTile[gp.currentMap][i].isCorrectItem(this) == true && gp.iTile[gp.currentMap][i].invincible == false) {
			
			gp.iTile[gp.currentMap][i].playSE();
			gp.iTile[gp.currentMap][i].life--;
			gp.iTile[gp.currentMap][i].invincible = true;
			
			//generate particle
			generateParticle(gp.iTile[gp.currentMap][i], gp.iTile[gp.currentMap][i]);
			
			if(gp.iTile[gp.currentMap][i].life == 0) {
			
			gp.iTile[gp.currentMap][i] = gp.iTile[gp.currentMap][i].getDestroyedForm();
			}
		}
	}
	public void damageProjectile(int i) {
		
		if(i != 999) {
			Entity projectile = gp.projectile[gp.currentMap][i];
			projectile.alive = false;
			generateParticle(projectile, projectile);
		}
	}
    public void checkLevelUp() {
		
		if(exp >= nextLevelExp) {
			
			level++;
			nextLevelExp = nextLevelExp*2;
			strength++;
			dexterity++;
			attack = getAttack();
			//defense = getDefense();
			
			gp.playSE(8);
			gp.gameState = gp.dialogueState;
			
			setDialogue();
			startDialogue(this,0);
		}
	}
	public void selectItem() {
		
		int itemIndex = gp.ui.getItemIndexOnSlot(gp.ui.playerSlotCol, gp.ui.playerSlotRow);
		
		if(itemIndex < inventory.size()) {
			
			Entity selectedItem = inventory.get(itemIndex);
			
			if(selectedItem.type == type_sword || selectedItem.type == type_axe
					|| selectedItem.type == type_shovel || selectedItem.type == type_pickaxe){
				
				currentWeapon = selectedItem;
				attack = getAttack();
				getPlayerAttackImage();
			}
			if(selectedItem.type == type_shield) {
				
				currentShield = selectedItem;
				getGuardImage();
				//defense = getDefense();
			}
			if(selectedItem.type == type_light) {
				
				if(currentLight == selectedItem) {
					currentLight = null;
				}
				else {
					currentLight = selectedItem;
				}
				lightUpdated = true;
			}
			if(selectedItem.type == type_consumable) {
				gp.gameState = gp.confirmationState;
				
				
					
					
					
				
			}
		}
	}
	public void consumable() {
		
		int itemIndex = gp.ui.getItemIndexOnSlot(gp.ui.playerSlotCol, gp.ui.playerSlotRow);
		
      if(itemIndex < inventory.size()) {
			
			Entity selectedItem = inventory.get(itemIndex);
			
		if(canUse == true) {
			
			if(selectedItem.use(this) == true) {
			selectedItem.playS();
			
		if(selectedItem.amount > 1) {
			selectedItem.amount--;
		}
		else {
	inventory.remove(itemIndex);
		}
	}
		canUse = false;
}
      }
		
	}
	public void makePaint(Entity paint) {
		
		
		paintb--;
		paintr--;
		if (paint.name.equals("Paint Blue")) {
			
			for (int i = 0; i < inventory.size(); i++) {
				if (inventory.get(i).name.equals("Paint Red") && inventory.get(i).price > 25) {
					inventory.remove(i);
					gp.player.inventory.add(new OBJ_Paint_Purple(gp));
					break;
				}
			}
		} else {

			for (int i = 0; i < inventory.size(); i++) {
				if (inventory.get(i).name.equals("Paint Blue") && inventory.get(i).price > 25) {
					inventory.remove(i);
					gp.player.inventory.add(new OBJ_Paint_Purple(gp));
					
					break;
				}
			}
		}
	}
	public void deleteItem(Entity entity) {
		
		for(int i = 0; i < inventory.size(); i++) {
			if(inventory.get(i) == entity ) {
				inventory.remove(i);
				break;
			}
		}
	}
	public int searchItemInInventory(String itemName) {
		int itemIndex = 999;
		
		for(int i = 0; i< inventory.size(); i++) {
			if(inventory.get(i).name.equals(itemName)) {
				itemIndex = i;
				break;
			}
		}
		
		return itemIndex;
	}
	public boolean canObtainItem(Entity item) {
		
		boolean canObtain = false;
		
		Entity newItem = gp.eGenerator.getObject(item.name);
		
		//check if stackable
		if(newItem.stackable == true) {
			
			//int index = searchItemInInventory(item.name);
			
			//if(index != 999) {
			for (int i = 0; i < inventory.size(); i++) {
				
				if (inventory.get(i).name.equals(newItem.name) && inventory.get(i).amount < maxAmount) {	
				inventory.get(i).amount++;
				canObtain = true;
				break;
			}
			}
		
		//	else {
				if(canObtain == false && inventory.size() != maxInventorySize) {					
						inventory.add(newItem);
				 
					canObtain = true;
				}
			}
		//}
	
		//if not stackable, check vacancy
		else {
			if(inventory.size() != maxInventorySize) {
				inventory.add(newItem);
					
				canObtain = true;
			}
		}
		return canObtain;
	}
	public void draw(Graphics2D g2) {
		
		BufferedImage image = null;
		int tempScreenX = screenX;
		int tempScreenY = screenY;
		
		switch(direction) {
		case "up":
			if(attacking == false) {
			   if(spriteNum == 1) {image = up1;}
			   if(spriteNum == 2) {image = up2;}
			}
			if(attacking == true) {
				tempScreenY = screenY - gp.tileSize;
				if(spriteNum == 1) {image = attackUp1;}
				if(spriteNum == 2) {image = attackUp2;}	
			}
			if(guarding == true) {
				image = guardUp;
			}
			break;
		case "down":
			if(attacking == false) {
			   if(spriteNum == 1) {image = down1;}
			   if(spriteNum == 2) {image = down2;}
			}
			if(attacking == true) {
				if(spriteNum == 1) {image = attackDown1;}
				if(spriteNum == 2) {image = attackDown2;}	
			}
			if(guarding == true) {
				image = guardDown;
			}
			break;
		case "left":
			if(attacking == false) {
			   if(spriteNum == 1) {image = left1;}
			   if(spriteNum == 2) {image = left2;}
			}
			if(attacking == true) {
				tempScreenX = screenX - gp.tileSize;
				if(spriteNum == 1) {image = attackLeft1;}
				if(spriteNum == 2) {image = attackLeft2;}	
			}
			if(guarding == true) {
				image = guardLeft;
			}
			break;
		case "right":
			if(attacking == false) {
			   if(spriteNum == 1) {image = right1;}
			   if(spriteNum == 2) {image = right2;}
			}
			if(attacking == true) {
				if(spriteNum == 1) {image = attackRight1;}
				if(spriteNum == 2) {image = attackRight2;}	
			}
			if(guarding == true) {
				image = guardRight;
			}
			break;
		}
		
		if(transparenty == true) {
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f ));		
		}
		if(drawing == true) {
		g2.drawImage(image, tempScreenX, tempScreenY, null);
		}
		//reset alpha
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f ));		
			       
		//debug
//		g2.setFont(new Font("Arial", Font.PLAIN,26));
//		g2.setColor(Color.white);
//		g2.drawString("invincible"+invincibleCounter, 10, 400);
//	
		 
	}

}
