package lpoo.chess.logic;

//import java.util.ArrayList;

public class Map {

	private Character[][] map =  
		{
				
						{new Rook(), new Knight(), new Bishop(), new Queen(), new King(), new Bishop(), new Knight(), new Rook()},
						{new Pawn(), new Pawn(), new Pawn(), new Pawn(), new Pawn(), new Pawn(), new Pawn(), new Pawn()},
						{new Floor(), new Floor(), new Floor(), new Floor(), new Floor(), new Floor(), new Floor(), new Floor()},
						{new Floor(), new Floor(), new Floor(), new Floor(), new Floor(), new Floor(), new Floor(), new Floor()},
						{new Floor(), new Floor(), new Floor(), new Floor(), new Floor(), new Floor(), new Floor(), new Floor()},
						{new Floor(), new Floor(), new Floor(), new Floor(), new Floor(), new Floor(), new Floor(), new Floor()},
						{new Pawn(), new Pawn(), new Pawn(), new Pawn(), new Pawn(), new Pawn(), new Pawn(), new Pawn()},
						{new Rook(), new Knight(), new Bishop(), new Queen(), new King(), new Bishop(), new Knight(), new Rook()}
			
					};
					
		
//	private Character[][] map =  
//		{
//				
//				{new Floor(), new Floor(), new Floor(), new Floor(), new Floor(), new Floor(), new Floor(), new Floor()},
//				{new Floor(), new Floor(), new Floor(), new Bishop(), new Floor(), new Queen(), new Floor(), new Floor()},
//				{new Floor(), new Floor(), new Knight(), new Floor(), new Floor(), new Floor(), new Floor(), new Floor()},
//				{new Knight(), new Floor(), new Floor(), new Floor(), new Knight(), new King(), new Floor(), new Floor()},
//				{new Floor(), new Floor(), new King(), new Pawn(), new Floor(), new Floor(), new Bishop(), new Floor()},
//				{new Floor(), new Floor(), new Floor(), new Floor(), new Floor(), new Pawn(), new Floor(), new Floor()},
//				{new Rook(), new Floor(), new Pawn(), new Floor(), new Pawn(), new Floor(), new Floor(), new Rook()},
//				{new Floor(), new Floor(), new Floor(), new Floor(), new Floor(), new Floor(), new Floor(), new Floor()}
//	
//			};
		
	public Map() 
	{
		for (int i = 0; i < map.length; i++)
		{
			for (int j = 0; j < map[i].length; j++)
			{
				map[i][j].setPos(new Pair<Integer, Integer>(j, i));
				
				if (i == 0 || i == 1)
				{
					map[i][j].setPlayer(1);
				}
				else if (i == 6 || i == 7)
				{
					map[i][j].setPlayer(0);
				}
			}
		}
		
	};
	
	public Map(Character[][] map)
	{
		this.map = map;
	}
	
	public char[][] getCharMap()
	{
		char[][] ret = new char[8][8];
		
		for (int i = 0; i < map.length; i++)
		{
			for (int j = 0; j < map[i].length; j++)
			{
				ret[i][j] = map[i][j].ch;
			}
		}
		
		return ret;
	}
	
	public Character[][] getMap()
	{
		return this.map;
	}
	
	
	public void setMap(Character[][] map)
	{
		this.map = map;
	}
	
	public boolean isWithinBounds(int x, int y)
	{
		return (x >= 0 && x < map[0].length && y >= 0 && y < map.length);
	}
	
	public boolean isWithinBounds(Pair<Integer, Integer> pair)
	{
		return (pair.getFirst() >= 0 && pair.getFirst() < map[0].length && pair.getSecond() >= 0 && pair.getSecond() < map.length);
	}
	
	public void printMap()
	{
		char[][] charMap = this.getCharMap();
		
		for (int i = 0; i < charMap.length; i++)
		{
			System.out.println(charMap[i]);
		}
	}
	
	public void move(Character ch1, Character ch2)
	{		
		map[ch2.getPos().getSecond()][ch2.getPos().getFirst()] = map[ch1.getPos().getSecond()][ch1.getPos().getFirst()];
		
		map[ch1.getPos().getSecond()][ch1.getPos().getFirst()] = new Floor(ch1.getPos().getFirst(), ch1.getPos().getSecond());
		
		ch1.pos = ch2.pos;
		
		if (ch1 instanceof Pawn)
		{
			Pawn p = (Pawn)(ch1);
			p.moveCount++;
		}
	}
	
	public Pair<Integer, Integer> getKingsPosition(int player)
	{
		int x = 0, y = 0; // Initialized to 0 to remove the warning 
		for (int i = 0; i < map.length; i++)
		{
			for (int j = 0; j < map[i].length; j++)
			{
				if (map[j][i].ch == 'K' && map[j][i].player == player) 
				{
					x = i;
					y = j;
				}		
			}
		}
		Pair<Integer, Integer> pos = new Pair<Integer, Integer>();
		pos.setFirst(x);
		pos.setSecond(y);
		return pos;
	}
}