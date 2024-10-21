package main;

import entity.Entity;
import object.OBJ_Apple;
import object.OBJ_Axe;
import object.OBJ_Boat;
import object.OBJ_Boots;
import object.OBJ_Chain;
import object.OBJ_Chest;
import object.OBJ_Coin_Bronze;
import object.OBJ_Coin_Gold;
import object.OBJ_Door;
import object.OBJ_Door_Iron;
import object.OBJ_Fireball;
import object.OBJ_Heart;
import object.OBJ_Key;
import object.OBJ_Lantern;
import object.OBJ_ManaCrystal;
import object.OBJ_Paint_Blue;
import object.OBJ_Paint_Material;
import object.OBJ_Paint_Red;
import object.OBJ_Paint_Yellow;
import object.OBJ_Pear;
import object.OBJ_Pickaxe;
import object.OBJ_Potion_Blue;
import object.OBJ_Potion_Red;
import object.OBJ_Rock;
import object.OBJ_Shield;
import object.OBJ_Shield_Diamond;
import object.OBJ_Shovel;
import object.OBJ_Sword;
import object.OBJ_Tent;
import object.OBJ_Wood;

public class EntityGenerator {

	GamePanel gp;
	
	public EntityGenerator(GamePanel gp) {
		this.gp = gp;
	}
	public Entity getObject(String itemName) {
		
     Entity obj = null;
		
		switch(itemName) {
		case OBJ_Tent.objName: obj = new OBJ_Tent(gp);break;
		case OBJ_Lantern.objName: obj = new OBJ_Lantern(gp);break;
		case OBJ_Boat.objName: obj = new OBJ_Boat(gp);break;
		case OBJ_Shield_Diamond.objName: obj = new OBJ_Shield_Diamond(gp);break;
		case OBJ_Shield.objName: obj = new OBJ_Shield(gp);break;
		case OBJ_Boots.objName: obj = new OBJ_Boots(gp);break;
		case OBJ_Fireball.objName: obj = new OBJ_Fireball(gp);break;
		case OBJ_Rock.objName: obj = new OBJ_Rock(gp);break;
		case OBJ_Sword.objName: obj = new OBJ_Sword(gp);break;
		case OBJ_Axe.objName: obj = new OBJ_Axe(gp);break;
		case OBJ_Pickaxe.objName: obj = new OBJ_Pickaxe(gp);break;
		case OBJ_Shovel.objName: obj = new OBJ_Shovel(gp);break;
		case OBJ_Door.objName: obj = new OBJ_Door(gp);break;
		case OBJ_Door_Iron.objName: obj = new OBJ_Door_Iron(gp);break;
		case OBJ_Chain.objName: obj = new OBJ_Chain(gp);break;
		case OBJ_Chest.objName: obj = new OBJ_Chest(gp);break;
		case OBJ_Key.objName: obj = new OBJ_Key(gp);break;
		case OBJ_Coin_Gold.objName: obj = new OBJ_Coin_Gold(gp);break;
		case OBJ_Coin_Bronze.objName: obj = new OBJ_Coin_Bronze(gp);break;
		case OBJ_Apple.objName: obj = new OBJ_Apple(gp);break;
		case OBJ_Pear.objName: obj = new OBJ_Pear(gp);break;
		case OBJ_Potion_Red.objName: obj = new OBJ_Potion_Red(gp);break;
		case OBJ_Potion_Blue.objName: obj = new OBJ_Potion_Blue(gp);break;
		case OBJ_Heart.objName: obj = new OBJ_Heart(gp);break;
		case OBJ_ManaCrystal.objName: obj = new OBJ_ManaCrystal(gp);break;
		case OBJ_Wood.objName: obj = new OBJ_Wood(gp);break;
		case OBJ_Paint_Blue.objName: obj = new OBJ_Paint_Blue(gp);break;
		case OBJ_Paint_Red.objName: obj = new OBJ_Paint_Red(gp);break;
		case OBJ_Paint_Yellow.objName: obj = new OBJ_Paint_Yellow(gp);break;
		case OBJ_Paint_Material.objName: obj = new OBJ_Paint_Material(gp);break;
		}
		
		return obj;
		
		
	}
}
