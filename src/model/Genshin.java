package model;

public class Genshin {
	
	private int ColitionWithDvalin;
	private int ColitionWithAndrius;
	
	public Genshin() {
		ColitionWithAndrius = 0;
		ColitionWithDvalin  = 0;
	}

	public int getColitionWithDvalin() {
		return ColitionWithDvalin;
	}
	
	public void noMoreColitionWithDvalin() {
		ColitionWithDvalin++;
	}
	
	public void noMoreColitionWithAndrius() {
		ColitionWithAndrius++;
	}

	public void setColitionWithDvalin(int colitionWithDvalin) {
		ColitionWithDvalin = colitionWithDvalin;
	}

	public int getColitionWithAndrius() {
		return ColitionWithAndrius;
	}

	public void setColitionWithAndrius(int colitionWithAndrius) {
		ColitionWithAndrius = colitionWithAndrius;
	}
	
	
}
