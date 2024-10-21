package entity;

import java.util.Random;

import main.GamePanel;

public class NPC_Flower_Girl extends Entity{

	public NPC_Flower_Girl(GamePanel gp) {
		
		super(gp);
		
		name = "old";
		direction = "down";
		speed = 1;
		solidArea.x =8;
		solidArea.y = 20;
		solidArea.width = 29;
		solidArea.height = 29;
		solidAreaDefaultX =  solidArea.x;
		solidAreaDefaultY =  solidArea.y;
		dialogueSet = -1;
		
		getImage();
		setDialogue();
		
		
	}
	public void getImage() {
		
		up1 = setup("/npc/flower_girl_up1", gp.tileSize, gp.tileSize);
		up2 = setup("/npc/flower_girl_up2", gp.tileSize, gp.tileSize);
		down1 = setup("/npc/flower_girl_down1", gp.tileSize, gp.tileSize);
		down2 = setup("/npc/flower_girl_down2", gp.tileSize, gp.tileSize);
		left1 = setup("/npc/flower_girl_left1", gp.tileSize, gp.tileSize);
		left2 = setup("/npc/flower_girl_left2", gp.tileSize, gp.tileSize);
		right1 = setup("/npc/flower_girl_right1", gp.tileSize, gp.tileSize);
		right2 = setup("/npc/flower_girl_right2", gp.tileSize, gp.tileSize);
}
	public void setDialogue() {
		
		dialogues[0][0] = "Hello there..";
		dialogues[0][1] = "I love flowers!";
		dialogues[0][2] = "I only collect 16 everyday.";
		dialogues[0][3] = "16 flowers??";
		
		

	}
	
	//npc AI
	public void setAction() {
		
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
	
	public void speak() {
		
		facePlayer();
		startDialogue(this, dialogueSet);
		
		dialogueSet ++;
		
		if(dialogues[dialogueSet][0] == null) {
			dialogueSet = 0;
			//to display last text
			//dialogueSet --;
		}
	}
}
