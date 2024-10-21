package entity;

import main.GamePanel;
import object.OBJ_Paint_Blue;
import object.OBJ_Paint_Red;
import object.OBJ_Paint_Yellow;

public class NPC_Table extends Entity{

public NPC_Table(GamePanel gp) {
		
		super(gp);
		
		name = "old";
		direction = "down";
		speed = 0;
		
		type = type_creator;
		
		getImage();
		setDialogue();
		setItem();
		
		
	}
	public void getImage() {
		if(direction == "down") {
			up1 = setup("/npc/paintTable1", gp.tileSize, gp.tileSize);
			up2 = setup("/npc/paintTable1", gp.tileSize, gp.tileSize);
			down1 = setup("/npc/paintTable1", gp.tileSize, gp.tileSize);
			down2 = setup("/npc/paintTable1", gp.tileSize, gp.tileSize);
			left1 = setup("/npc/paintTable1", gp.tileSize, gp.tileSize);
			left2 = setup("/npc/paintTable1", gp.tileSize, gp.tileSize);
			right1 = setup("/npc/paintTable1", gp.tileSize, gp.tileSize);
			right2 = setup("/npc/paintTable1", gp.tileSize, gp.tileSize);
		}
		else {
			up1 = setup("/npc/paintTable2", gp.tileSize, gp.tileSize);
			up2 = setup("/npc/paintTable2", gp.tileSize, gp.tileSize);
			down1 = setup("/npc/paintTable2", gp.tileSize, gp.tileSize);
			down2 = setup("/npc/paintTable2", gp.tileSize, gp.tileSize);
			left1 = setup("/npc/paintTable2", gp.tileSize, gp.tileSize);
			left2 = setup("/npc/paintTable2", gp.tileSize, gp.tileSize);
			right1 = setup("/npc/paintTable2", gp.tileSize, gp.tileSize);
			right2 = setup("/npc/paintTable2", gp.tileSize, gp.tileSize);
		}
}
	public void setDialogue() {
		
		dialogues[0][0] = "A table with tools, Do you want to use it?";
		dialogues[1][0] = "Done.";
		dialogues[2][0] = "You don't have enough material.";
		dialogues[3][0] = "Your inventory is full!";
		
	}
	public void setItem() {
		int i =0;
		inventory.add(new OBJ_Paint_Blue(gp));
		inventory.get(i).getImage();
		i++;
		inventory.add(new OBJ_Paint_Yellow(gp));
		inventory.get(i).getImage();
		i++;
		inventory.add(new OBJ_Paint_Red(gp));
		inventory.get(i).getImage();
		i++;
	
	}
	public void speak() {
		
		facePlayer();
		gp.gameState = gp.tradeState;
		gp.ui.npc = this;
	}
}
