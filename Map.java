public class Map{
	Obstacle[][] collidableObjects;

	public Map(int x, int y){//Map size
		collidableObjects = new Obstacle[x][y];
	}

	public void add(Obstacle o, int x, int y){
		collidableObjects[x][y] = o;
	}

	public Obstacle[] find(int cordX, int cordY, int sizeX, int sizeY){
		int top = cordX/30;
		int bottom = (cordX+sizeX)/30;
		int left = cordY/30;
		int right = (cordY+sizeY)/30;
		Obstacle[] overlaps = new Obstacle[(bottom-top)* (right-left)];
		int i = 0;
		for(;top<=bottom; top++)
			for(;left<=right;left++){
				overlaps[i] = collidableObjects[top][left];
				i++;
			}
		return overlaps;
	}
}