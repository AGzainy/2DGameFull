package monsters;

import java.util.Random;

import entity.Entity;
import main.GamePanel;
import object.OBJ_Coin_Gold;
import object.OBJ_Potion_Blue;

public class MON_SkeletonLord extends Entity{
	GamePanel gp;
	public static final String monName = "Skeleton Lord";

	public MON_SkeletonLord(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		name = monName;
		type = type_monster;
		boss = true;
		defaultSpeed = 1;
		speed = defaultSpeed;
		maxLife = 10;
		life = maxLife;
		attack = 8;
		defense = 0;
		exp = 4;
		knockBackPower = 5;
		sleep = true;
		
		int size = gp.tileSize*5;
		solidArea.x = 48;
		solidArea.y = 48;
		solidArea.width = size - 48*2;
		solidArea.height = size - 48;
		solidAreaDefaultX =  solidArea.x;
		solidAreaDefaultY =  solidArea.y;
		attackArea.width = 170;
		attackArea.height = 170;
		motion1_duration = 25;
		motion2_duration = 50;
		
		getImage(); 
		getAttackImage();
		setDialogue();
	}
	public void getImage() {
		int i = 5;
		
		if(inRage == false) {
		up1 = setup("/monster/skeletonlord_up_1", gp.tileSize*i, gp.tileSize*i);
		up2 = setup("/monster/skeletonlord_up_2", gp.tileSize*i, gp.tileSize*i);
		down1 = setup("/monster/skeletonlord_down_1", gp.tileSize*i, gp.tileSize*i);
		down2 = setup("/monster/skeletonlord_down_2", gp.tileSize*i, gp.tileSize*i);
		left1 = setup("/monster/skeletonlord_left_1", gp.tileSize*i, gp.tileSize*i);
		left2 = setup("/monster/skeletonlord_left_2", gp.tileSize*i, gp.tileSize*i);
		right1 = setup("/monster/skeletonlord_right_1", gp.tileSize*i, gp.tileSize*i);
		right2 = setup("/monster/skeletonlord_right_2", gp.tileSize*i, gp.tileSize*i);
		}
		if(inRage == true) {
			up1 = setup("/monster/skeletonlord_phase2_up_1", gp.tileSize*i, gp.tileSize*i);
			up2 = setup("/monster/skeletonlord_phase2_up_2", gp.tileSize*i, gp.tileSize*i);
			down1 = setup("/monster/skeletonlord_phase2_down_1", gp.tileSize*i, gp.tileSize*i);
			down2 = setup("/monster/skeletonlord_phase2_down_2", gp.tileSize*i, gp.tileSize*i);
			left1 = setup("/monster/skeletonlord_phase2_left_1", gp.tileSize*i, gp.tileSize*i);
			left2 = setup("/monster/skeletonlord_phase2_left_2", gp.tileSize*i, gp.tileSize*i);
			right1 = setup("/monster/skeletonlord_phase2_right_1", gp.tileSize*i, gp.tileSize*i);
			right2 = setup("/monster/skeletonlord_phase2_right_2", gp.tileSize*i, gp.tileSize*i);
		}
	}
	public void getAttackImage() {
		
		int i = 5;
		if(inRage == false) {
		attackUp1 = setup("/monster/skeletonlord_attack_up_1", gp.tileSize*i, gp.tileSize*i*2);
		attackUp2 = setup("/monster/skeletonlord_attack_up_2", gp.tileSize*i, gp.tileSize*i*2);
		attackDown1 = setup("/monster/skeletonlord_attack_down_1", gp.tileSize*i, gp.tileSize*i*2);
		attackDown2 = setup("/monster/skeletonlord_attack_down_2", gp.tileSize*i, gp.tileSize*i*2);
		attackLeft1 = setup("/monster/skeletonlord_attack_left_1", gp.tileSize*i*2, gp.tileSize*i);
		attackLeft2 = setup("/monster/skeletonlord_attack_left_2", gp.tileSize*i*2, gp.tileSize*i);
		attackRight1 = setup("/monster/skeletonlord_attack_right_1", gp.tileSize*i*2, gp.tileSize*i);
		attackRight2 = setup("/monster/skeletonlord_attack_right_2", gp.tileSize*i*2, gp.tileSize*i);
		
		}
		if(inRage == true) {
			attackUp1 = setup("/monster/skeletonlord_phase2_attack_up_1", gp.tileSize*i, gp.tileSize*i*2);
			attackUp2 = setup("/monster/skeletonlord_phase2_attack_up_2", gp.tileSize*i, gp.tileSize*i*2);
			attackDown1 = setup("/monster/skeletonlord_phase2_attack_down_1", gp.tileSize*i, gp.tileSize*i*2);
			attackDown2 = setup("/monster/skeletonlord_phase2_attack_down_2", gp.tileSize*i, gp.tileSize*i*2);
			attackLeft1 = setup("/monster/skeletonlord_phase2_attack_left_1", gp.tileSize*i*2, gp.tileSize*i);
			attackLeft2 = setup("/monster/skeletonlord_phase2_attack_left_2", gp.tileSize*i*2, gp.tileSize*i);
			attackRight1 = setup("/monster/skeletonlord_phase2_attack_right_1", gp.tileSize*i*2, gp.tileSize*i);
			attackRight2 = setup("/monster/skeletonlord_phase2_attack_right_2", gp.tileSize*i*2, gp.tileSize*i);
			
			
		}
	}
	public void setDialogue() {

		dialogues[0][0] = "No one can steal my treasure!";
		dialogues[0][1] = "You will die here!";
		dialogues[0][2] = "WELCOME TO YOUR DOOM!!";
		
		startDialogue(this, dialogueSet);

	}
	public void setAction() {
		
		if(inRage == false && life < maxLife/2) {
			inRage = true;
			getImage();
			getAttackImage();
			defaultSpeed++;
			speed = defaultSpeed;
			attack *= 2;
		}

		if (getTileDistance(gp.player) < 10) {
			
		    moveTowardPlayer(60);
		} else {
			// get a random direction
			getRandomDirection(120);
		}
		//check if it attacks
		if(attacking == false) {
			checkAttack(60, gp.tileSize*7, gp.tileSize*5);
		}
	}
	public void damageReaction() {
		
		actionLockCounter = 0;
		//direction = gp.player.direction;
		onPath = true;
//		if(knockBack == false) {
		if(speed <= defaultSpeed ) {
		speed ++;
		}
//		}
	}
	public void checkDrop() {
		
		int i = new Random().nextInt(100)+1;
		
		//set monster drop
		if(i < 50) {
			dropItem(new OBJ_Potion_Blue(gp));
		}
		if(i < 50) {
			dropItem(new OBJ_Coin_Gold(gp));
		}
//		if(i >= 50 && i < 75) {
//			dropItem(new OBJ_Heart(gp));			
//		}
//		if(i >= 75 && i < 100) {
//			dropItem(new OBJ_ManaCrystal(gp));
//		}
	}

public void speak() {
		
		
		dialogueSet ++;
		
		if(dialogues[dialogueSet][0] == null) {
			dialogueSet = 0;
			//to display last text
			//dialogueSet --;
		}
	
	}

}

