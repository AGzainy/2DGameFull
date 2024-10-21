package main;

import entity.NPC_BigRock;
import entity.NPC_Flower_Girl;
import entity.NPC_Merchant;
import entity.NPC_OldMan;
import entity.NPC_Smith;
import entity.NPC_Table;
import entity.NPC_Witch;
import monsters.MON_Bat;
import monsters.MON_Orc;
import monsters.MON_SkeletonLord;
import monsters.MON_Slime;
import object.OBJ_Axe;
import object.OBJ_Chain;
import object.OBJ_Chest;
import object.OBJ_Door_Iron;
import object.OBJ_Pickaxe;
import tile_interactive.IT_DestructibleWall;
import tile_interactive.IT_Dirt;
import tile_interactive.IT_DryTree;
import tile_interactive.IT_Flower_Blue;
import tile_interactive.IT_Flower_Red;
import tile_interactive.IT_Flower_Yellow;
import tile_interactive.IT_MetalPlate;

public class AssetsSetter {

	GamePanel gp ;
	
	public AssetsSetter(GamePanel gp) {
		this.gp = gp;
		
	}
	
	public void setObject() {
		
		int mapNum = 0;
		int i = 0;
//		gp.obj[mapNum][i] = new OBJ_Key(gp);
//		gp.obj[mapNum][i].worldX = gp.tileSize*25;
//		gp.obj[mapNum][i].worldY = gp.tileSize*29;
//		i++;
//		
//		gp.obj[mapNum][i] = new OBJ_Coin_Bronze(gp);
//		gp.obj[mapNum][i].worldX = gp.tileSize*21;
//		gp.obj[mapNum][i].worldY = gp.tileSize*26;
//		i++;
//		
//		gp.obj[mapNum][i] = new OBJ_Coin_Gold(gp);
//		gp.obj[mapNum][i].worldX = gp.tileSize*21;
//		gp.obj[mapNum][i].worldY = gp.tileSize*24;
//		i++;
//		
		gp.obj[mapNum][i] = new OBJ_Axe(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize*65;
		gp.obj[mapNum][i].worldY = gp.tileSize*27;
		i++;
//
//		
//		gp.obj[mapNum][i] = new OBJ_Potion_Blue(gp);
//		gp.obj[mapNum][i].worldX = gp.tileSize*15;
//		gp.obj[mapNum][i].worldY = gp.tileSize*29;
//		i++;
//		
//		gp.obj[mapNum][i] = new OBJ_Lantern(gp);
//		gp.obj[mapNum][i].worldX = gp.tileSize*17;
//		gp.obj[mapNum][i].worldY = gp.tileSize*27;
//		i++;
//		
//		
//		gp.obj[mapNum][i] = new OBJ_Tent(gp);
//		gp.obj[mapNum][i].worldX = gp.tileSize*35;
//		gp.obj[mapNum][i].worldY = gp.tileSize*15;
//		i++;
//		
//		gp.obj[mapNum][i] = new OBJ_Chest(gp);
//		gp.obj[mapNum][i].setLoot(new OBJ_Shield_Diamond(gp));
//		gp.obj[mapNum][i].worldX = gp.tileSize*34;
//		gp.obj[mapNum][i].worldY = gp.tileSize*10;
//		i++;
		gp.obj[mapNum][i] = new OBJ_Chain(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize*46;
		gp.obj[mapNum][i].worldY = gp.tileSize*62;
		i++;
		
		gp.obj[mapNum][i] = new OBJ_Chain(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize*45;
		gp.obj[mapNum][i].worldY = gp.tileSize*62;
		i++;
		
		mapNum = 1;
		i = 0;
		gp.obj[mapNum][i] = new OBJ_Chest(gp);
		gp.obj[mapNum][i].setLoot(new OBJ_Pickaxe(gp));
		gp.obj[mapNum][i].worldX = gp.tileSize*11;
		gp.obj[mapNum][i].worldY = gp.tileSize*19;
		i++;
		
		gp.obj[mapNum][i] = new OBJ_Door_Iron(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize*20;
		gp.obj[mapNum][i].worldY = gp.tileSize*52;
		i++;
		
		gp.obj[mapNum][i] = new OBJ_Door_Iron(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize*21;
		gp.obj[mapNum][i].worldY = gp.tileSize*52;
		i++;
		
		gp.obj[mapNum][i] = new OBJ_Door_Iron(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize*22;
		gp.obj[mapNum][i].worldY = gp.tileSize*52;
		i++;
		
		mapNum = 2;
		i = 0;
		
		gp.obj[mapNum][i] = new OBJ_Door_Iron(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize*39;
		gp.obj[mapNum][i].worldY = gp.tileSize*57;
		i++;
		
		gp.obj[mapNum][i] = new OBJ_Door_Iron(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize*40;
		gp.obj[mapNum][i].worldY = gp.tileSize*57;
		i++;
	}
	public void setNPC() {
		
		int mapNum = 0;
		int i = 0;
		
		//map 0
		gp.npc[mapNum][i] = new NPC_OldMan(gp);
		gp.npc[mapNum][i].worldX = gp.tileSize*63;
		gp.npc[mapNum][i].worldY = gp.tileSize*43;
		i++;
	
		gp.npc[mapNum][i] = new NPC_Merchant(gp);
		gp.npc[mapNum][i].worldX = gp.tileSize*56;
		gp.npc[mapNum][i].worldY = gp.tileSize*16;
		i++;
//		
		gp.npc[mapNum][i] = new NPC_Smith(gp);
		gp.npc[mapNum][i].worldX = gp.tileSize*69;
		gp.npc[mapNum][i].worldY = gp.tileSize*33;
		i++;
		
		gp.npc[mapNum][i] = new NPC_Witch(gp);
		gp.npc[mapNum][i].worldX = gp.tileSize*19;
		gp.npc[mapNum][i].worldY = gp.tileSize*40;
		i++;
		
		gp.npc[mapNum][i] = new NPC_Flower_Girl(gp);
		gp.npc[mapNum][i].worldX = gp.tileSize*26;
		gp.npc[mapNum][i].worldY = gp.tileSize*32;
		i++;
		
		gp.npc[mapNum][i] = new NPC_Table(gp);
		gp.npc[mapNum][i].worldX = gp.tileSize*18;
		gp.npc[mapNum][i].worldY = gp.tileSize*12;
		i++;
		
		gp.npc[mapNum][i] = new NPC_Table(gp);
		gp.npc[mapNum][i].direction = "up";
		gp.npc[mapNum][i].getImage();
		gp.npc[mapNum][i].worldX = gp.tileSize*19;
		gp.npc[mapNum][i].worldY = gp.tileSize*12;
		i++;
		//map 1
        mapNum = 1;
     	i = 0;
     	
     	gp.npc[mapNum][i] = new NPC_BigRock(gp);
		gp.npc[mapNum][i].worldX = gp.tileSize*20;
		gp.npc[mapNum][i].worldY = gp.tileSize*43;
		i++;
		
		gp.npc[mapNum][i] = new NPC_BigRock(gp);
		gp.npc[mapNum][i].worldX = gp.tileSize*39;
		gp.npc[mapNum][i].worldY = gp.tileSize*39;
		i++;
		
		gp.npc[mapNum][i] = new NPC_BigRock(gp);
		gp.npc[mapNum][i].worldX = gp.tileSize*55;
		gp.npc[mapNum][i].worldY = gp.tileSize*40;
		i++;
		
	}
	public void setMonster() {
		
		int mapNum = 0;		
		int i =0;
		gp.monster[mapNum][i] = new MON_Slime(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*15;
		gp.monster[mapNum][i].worldY = gp.tileSize*27;
		i++;
		
		gp.monster[mapNum][i] = new MON_Slime(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*15;
		gp.monster[mapNum][i].worldY = gp.tileSize*33;
		i++;
		
		gp.monster[mapNum][i] = new MON_Slime(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*25;
		gp.monster[mapNum][i].worldY = gp.tileSize*17;
		i++;
		
		gp.monster[mapNum][i] = new MON_Slime(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*66;
		gp.monster[mapNum][i].worldY = gp.tileSize*29;
		i++;
		
		gp.monster[mapNum][i] = new MON_Slime(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*62;
		gp.monster[mapNum][i].worldY = gp.tileSize*14;
		i++;
		
		gp.monster[mapNum][i] = new MON_Orc(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*14;
		gp.monster[mapNum][i].worldY = gp.tileSize*12;
		i++;
		
		mapNum = 1;
		i++;
		gp.monster[mapNum][i] = new MON_Bat(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*12;
		gp.monster[mapNum][i].worldY = gp.tileSize*12;
		i++;
		
		mapNum = 2;
		i++;
		gp.monster[mapNum][i] = new MON_SkeletonLord(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*41;
		gp.monster[mapNum][i].worldY = gp.tileSize*48;
		i++;
		
//		
		
	}
	public void setInterActiveTile() {
		
		int mapNum = 0;
		int i = 0;
		gp.iTile[mapNum][i] = new IT_DryTree(gp,63,25);i++;
		gp.iTile[mapNum][i] = new IT_DryTree(gp,63,24);i++;
		gp.iTile[mapNum][i] = new IT_DryTree(gp,65,26);i++;
		gp.iTile[mapNum][i] = new IT_DryTree(gp,62,28);i++;
		gp.iTile[mapNum][i] = new IT_DryTree(gp,60,33);i++;
//		
		gp.iTile[mapNum][i] = new IT_Dirt(gp,36,26);i++;
		gp.iTile[mapNum][i] = new IT_Dirt(gp,36,22);i++;
		gp.iTile[mapNum][i] = new IT_Dirt(gp,33,22);i++;
		gp.iTile[mapNum][i] = new IT_Dirt(gp,30,20);i++;
		gp.iTile[mapNum][i] = new IT_Dirt(gp,28,23);i++;
		gp.iTile[mapNum][i] = new IT_Dirt(gp,28,25);i++;
		gp.iTile[mapNum][i] = new IT_Dirt(gp,31,25);i++;
		gp.iTile[mapNum][i] = new IT_Dirt(gp,29,23);i++;
//		
		gp.iTile[mapNum][i] = new IT_Flower_Yellow(gp,22,45);i++;
		gp.iTile[mapNum][i] = new IT_Flower_Yellow(gp,20,42);i++;
		gp.iTile[mapNum][i] = new IT_Flower_Red(gp,22,42);i++;
		gp.iTile[mapNum][i] = new IT_Flower_Red(gp,19,45);i++;
		gp.iTile[mapNum][i] = new IT_Flower_Blue(gp,21,44);i++;
		gp.iTile[mapNum][i] = new IT_Flower_Blue(gp,16,45);i++;
		gp.iTile[mapNum][i] = new IT_Flower_Blue(gp,17,43);i++;
		gp.iTile[mapNum][i] = new IT_Flower_Red(gp,16,42);i++;
		
	    mapNum = 1;
		i = 0;
		
		gp.iTile[mapNum][i] = new IT_DestructibleWall(gp,25,15);i++;
		gp.iTile[mapNum][i] = new IT_DestructibleWall(gp,25,16);i++;
		gp.iTile[mapNum][i] = new IT_DestructibleWall(gp,37,9);i++;
		gp.iTile[mapNum][i] = new IT_DestructibleWall(gp,37,8);i++;
		
		gp.iTile[mapNum][i] = new IT_MetalPlate(gp,21,45);i++;
		gp.iTile[mapNum][i] = new IT_MetalPlate(gp,43,43);i++;
		gp.iTile[mapNum][i] = new IT_MetalPlate(gp,66,44);i++;
	}
	
	
	
	
}
