package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Chain extends Entity{

	GamePanel gp;
	public static final String objName = "Chain";
	
	public OBJ_Chain(GamePanel gp) {	
		    super(gp);
		    this.gp = gp;
		    
			name = objName;
			down1 = setup("/objects/chain", gp.tileSize, gp.tileSize);
			collision = true;
			type = type_obstacle;
			
			solidArea.x = 0;
			solidArea.y = 10;		
			solidArea.width = 40;
			solidArea.height = 32;
			solidAreaDefaultX = solidArea.x;
			solidAreaDefaultY = solidArea.y;
			
			setDialogue() ;
		}
	public void setDialogue() {
		dialogues[0][0] = "You need a key.";
	}
	public void interact() {
		
		startDialogue(this,0);
	}
}
