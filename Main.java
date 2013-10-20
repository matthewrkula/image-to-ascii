import java.io.File;



public class Main {
	
	public static void main(String[] args) throws Exception{
		if(args.length > 0)
			new ImageToAscii(new File(args[0]), 1).printMap();
		else
			new ImageToAscii(new File("monster.jpeg"), 1).printMap();
	}

}
