package tile_interactive;

import main.GamePanel;

public class IT_DirtDug extends InteractiveTile{
	GamePanel gp;

	public IT_DirtDug(GamePanel gp, int col, int row) {
		super(gp, col, row);
		this.gp = gp;
		
		this.worldX = gp.tileSize * col;
		this.worldY = gp.tileSize * row;
		
		down1 = setup("/tiles_interactive/dirtDug", gp.tileSize, gp.tileSize);
		solidArea.x = 0;
		solidArea.y = 0;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 0;
		solidArea.height = 0;
	}

}
