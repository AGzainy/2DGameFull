package monsters;

import java.util.Random;

import entity.Entity;
import main.GamePanel;
import object.OBJ_Coin_Bronze;
import object.OBJ_Heart;
import object.OBJ_Potion_Red;
import object.OBJ_Rock;
import object.OBJ_Shield_Diamond;

public class MON_Bat extends Entity{

	GamePanel gp;

	public MON_Bat(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		name = "Bat";
		type = type_monster;
		defaultSpeed = 4;
		speed = defaultSpeed;
		maxLife = 8;
		life = maxLife;
		attack = 4;
		defense = 0;
		exp = 7;
		
		solidArea.x =3;
		solidArea.y = 15;
		solidArea.width = 42;
		solidArea.height = 21;
		solidAreaDefaultX =  solidArea.x;
		solidAreaDefaultY =  solidArea.y;
		
		getImage();
	}
	public  void getImage() {
		
		up1 = setup("/monster/bat_down_1", gp.tileSize, gp.tileSize);
		up2 = setup("/monster/bat_down_2", gp.tileSize, gp.tileSize);
		down1 = setup("/monster/bat_down_1", gp.tileSize, gp.tileSize);
		down2 = setup("/monster/bat_down_2", gp.tileSize, gp.tileSize);
		left1 = setup("/monster/bat_down_1", gp.tileSize, gp.tileSize);
		left2 = setup("/monster/bat_down_2", gp.tileSize, gp.tileSize);
		right1 = setup("/monster/bat_down_1", gp.tileSize, gp.tileSize);
		right2 = setup("/monster/bat_down_2", gp.tileSize, gp.tileSize);
	}
	public void setAction() {

		if (onPath == true) {

//			// check if it should stop chasing
//			checkStopChasing(gp.player,15,100);
//
//			// search the direction to go
//			searchPath(getGoalCol(gp.player), getGoalRow(gp.player));
//
//			// check if it shoots a projectile
//			checkShoot(200, 30);
//		
		} else {
			// check if it should start chasing
//			checkStartChasing(gp.player, 3, 100);

			// get a random direction
			getRandomDirection(10);
		}
	}
	public void damageReaction() {
		
		actionLockCounter = 0;
		//direction = gp.player.direction;
	//	onPath = true;
//		if(knockBack == false) {
		if(speed <= defaultSpeed ) {
		speed +=2;
		}
//		}
	}
	public void checkDrop() {
		
		int i = new Random().nextInt(100)+1;
		
		//set monster drop
		if(i < 30) {
			dropItem(new OBJ_Coin_Bronze(gp));
		}
		if(i >= 30 && i < 40) {
			dropItem(new OBJ_Heart(gp));			
		}
		if(i >= 40 && i < 45) {
			dropItem(new OBJ_Potion_Red(gp));			
		}
		if(i == 90) {
			dropItem(new OBJ_Shield_Diamond(gp));
		}
	}


}
