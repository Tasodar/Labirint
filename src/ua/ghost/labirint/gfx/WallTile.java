package ua.ghost.labirint.gfx;

public class WallTile extends Tile{

	public WallTile(int x, int y) {
		super(x, y);
		name="стена";
		solid=true;
	}

	@Override
	public void tick() {
		
	}
}
