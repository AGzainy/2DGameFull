package entity;

import main.GamePanel;
import object.OBJ_Lantern;
import object.OBJ_Paint_Material;
import object.OBJ_Potion_Blue;
import object.OBJ_Potion_Red;
import object.OBJ_Shield_Diamond;
import object.OBJ_Tent;

public class NPC_Merchant extends Entity{

	public NPC_Merchant(GamePanel gp) {
		
		super(gp);
		
		name = "Merchant";
		direction = "down";
		speed = 0;
		solidArea.x =8;
		solidArea.y = 16;
		solidArea.width = 32;
		solidArea.height = 32;
		solidAreaDefaultX =  solidArea.x;
		solidAreaDefaultY =  solidArea.y;
		type = type_merchant;
		
		getImage();
		setDialogue();
		setItem();
		
		
	}
	public void getImage() {
		
		up1 = setup("/npc/merchant_down_1", gp.tileSize, gp.tileSize);
		up2 = setup("/npc/merchant_down_2", gp.tileSize, gp.tileSize);
		down1 = setup("/npc/merchant_down_1", gp.tileSize, gp.tileSize);
		down2 = setup("/npc/merchant_down_2", gp.tileSize, gp.tileSize);
		left1 = setup("/npc/merchant_down_1", gp.tileSize, gp.tileSize);
		left2 = setup("/npc/merchant_down_2", gp.tileSize, gp.tileSize);
		right1 = setup("/npc/merchant_down_1", gp.tileSize, gp.tileSize);
		right2 = setup("/npc/merchant_down_2", gp.tileSize, gp.tileSize);
}
	public void setDialogue() {
		
		dialogues[0][0] = "Hello, trveler.\nDo have something intresting to trade?";
		dialogues[1][0] = "Come again..";
		dialogues[2][0] = "You don't have enough coins.";
		dialogues[3][0] = "Your inventory is full!";
		dialogues[4][0] = "You can't sell an equipped item.";
		
	}
	public void setItem() {
		
		inventory.add(new OBJ_Potion_Red(gp));
		inventory.add(new OBJ_Potion_Blue(gp));
		inventory.add(new OBJ_Shield_Diamond(gp));
		inventory.add(new OBJ_Paint_Material(gp));
		inventory.add(new OBJ_Tent(gp));
		inventory.add(new OBJ_Lantern(gp));
		
	}
	public void speak() {
		
		facePlayer();
		gp.gameState = gp.tradeState;
		gp.ui.npc = this;
	}
}
