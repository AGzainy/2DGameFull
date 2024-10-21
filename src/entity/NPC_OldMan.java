package entity;

import java.util.Random;

import main.GamePanel;

public class NPC_OldMan extends Entity{
	
	public NPC_OldMan(GamePanel gp) {
	
		super(gp);
		name = "old";
		direction = "down";
		speed = 1;
		solidArea.x =8;
		solidArea.y = 16;
		solidArea.width = 32;
		solidArea.height = 32;
		solidAreaDefaultX =  solidArea.x;
		solidAreaDefaultY =  solidArea.y;
		dialogueSet = -1;
		
		getImage();
		setDialogue();
		
		
	}
	public void getImage() {
		
		up1 = setup("/npc/oldmanUp", gp.tileSize, gp.tileSize);
		up2 = setup("/npc/oldmanUp2", gp.tileSize, gp.tileSize);
		down1 = setup("/npc/oldmanDown", gp.tileSize, gp.tileSize);
		down2 = setup("/npc/oldmanDown2", gp.tileSize, gp.tileSize);
		left1 = setup("/npc/oldmanLeft", gp.tileSize, gp.tileSize);
		left2 = setup("/npc/oldmanLeft2", gp.tileSize, gp.tileSize);
		right1 = setup("/npc/oldmanRight", gp.tileSize, gp.tileSize);
		right2 = setup("/npc/oldmanRight2", gp.tileSize, gp.tileSize);
}
	public void setDialogue() {
		
		dialogues[0][0] = "Hello, lad.";
		dialogues[0][1] = "Hello sir..";
		dialogues[0][2] = "What are you doing on this island?";
		dialogues[0][3] = "I have come looking for my brother.\nI have heard that he was here..";
		dialogues[0][4] = "On this Island?!\nIt is a dengorous place kid!\nYou shouldn't be here.";
		dialogues[0][5] = "But sir.. I have no choice.\nI have to find my little brother.\nI think that he might be in danger.";
		dialogues[0][6] = "Oh.. I see. \nSo if You must.";
		dialogues[0][7] = "Beware of the monsters that run through \nthe island lad.\nAnd eat to stay strong.";
		dialogues[0][8] = "make sure to have a lantern at night...\nOr even better a tent to sleep.";
		dialogues[0][9] = "How can I leave?.";
		dialogues[0][10] = "The only way out of here is with a boat.\nonly the smith stopped making those..";
		dialogues[0][11] = "He has been looking for purple paint for \nfor his daughter.\nmaybe you could do him a favor.";
		

	}
	
	//npc AI
	public void setAction() {
		
	//	if(onPath == true) {
			
			//set a goal location
//			int goalCol = 22;
//			int goalRow = 40;
//			
			//make entity follow player
			//int goalCol = (gp.player.worldX + gp.player.solidArea.x)/gp.tileSize;
			//int goalRow = (gp.player.worldY + gp.player.solidArea.y)/gp.tileSize;
			
		//	searchPath(goalCol,goalRow);
	//	}
		//else {
		actionLockCounter++;
		
		if(actionLockCounter == 120) {
		Random random = new Random();
		int i = random.nextInt(100)+1;
		
		if(i <= 25) {
			direction = "up";
		}
		if(i > 25 && i <= 50) {
			direction = "down";
		}
		if(i > 50 && i <= 75) {
			direction = "left";
		}
		if(i > 75 && i <= 100) {
			direction = "right";
		}
		actionLockCounter = 0;
		}
		}
	//}
	
	public void speak() {
		
		facePlayer();
		startDialogue(this, dialogueSet);
		
		dialogueSet ++;
		
		if(dialogues[dialogueSet][0] == null) {
			dialogueSet = 0;
			//to display last text
			//dialogueSet --;
		}
		//onPath = true;
		
//		if(gp.player.life < gp.player.maxLife/3) {
//			dialogueSet = 1;
//		}
	}
}
