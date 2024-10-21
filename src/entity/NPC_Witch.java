package entity;

import java.util.Random;

import main.GamePanel;

public class NPC_Witch extends Entity{
	public NPC_Witch(GamePanel gp) {
		
		super(gp);
		name = "Witch";
		direction = "down";
		speed = 0;
		solidArea.x =8;
		solidArea.y = 16;
		solidArea.width = 32;
		solidArea.height = 32;
		solidAreaDefaultX =  solidArea.x;
		solidAreaDefaultY =  solidArea.y;
		dialogueSet = 0;
		
		getImage();
		setDialogue();
		
		
	}
	public void getImage() {
		
		if(speed == 0) {
		up1 = setup("/npc/witch_up1", gp.tileSize, gp.tileSize);
		up2 = setup("/npc/witch_up2", gp.tileSize, gp.tileSize);
		down1 = setup("/npc/witch_up1", gp.tileSize, gp.tileSize);
		down2 = setup("/npc/witch_up2", gp.tileSize, gp.tileSize);
		left1 = setup("/npc/witch_up1", gp.tileSize, gp.tileSize);
		left2 = setup("/npc/witch_up2", gp.tileSize, gp.tileSize);
		right1 = setup("/npc/witch_up1", gp.tileSize, gp.tileSize);
		right2 = setup("/npc/witch_up2", gp.tileSize, gp.tileSize);
		}else {
			up1 = setup("/npc/witch_up1", gp.tileSize, gp.tileSize);
			up2 = setup("/npc/witch_up2", gp.tileSize, gp.tileSize);
			down1 = setup("/npc/witch_up1", gp.tileSize, gp.tileSize);
			down2 = setup("/npc/witch_up2", gp.tileSize, gp.tileSize);
			left1 = setup("/npc/witch_up1", gp.tileSize, gp.tileSize);
			left2 = setup("/npc/witch_up2", gp.tileSize, gp.tileSize);
			right1 = setup("/npc/witch_right1", gp.tileSize, gp.tileSize);
			right2 = setup("/npc/witch_right2", gp.tileSize, gp.tileSize);
		}
}
	public void setDialogue() {
		
		dialogues[0][0] = "I'm here to protect my flowers.";
		dialogues[0][1] = "However, I wouldn't mind a bite from the \nforbbiden tree..";	
		dialogues[1][0] = "Thank you for the fruit!";
		dialogues[1][1] = "Take as many flowers as you like..";

	}
	
	//npc AI
	public void setAction() {
		
		if(onPath == true) {
			
			//set a goal location
     		int goalCol = 22;
			int goalRow = 40;
			
			
			speed = 1;
			getImage();
			searchPath(goalCol,goalRow);
		}
		else {
			speed = 0;
	}
			
	}
	
	public void speak() {
		
		if(dialogueSet == 1) {
			onPath= true;
		}
		
		facePlayer();
		startDialogue(this, dialogueSet);
		
		
		
	}
}
