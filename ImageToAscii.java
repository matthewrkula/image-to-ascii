import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.imageio.ImageIO;


public class ImageToAscii {
	
	 int pixelsDown;
	 int pixelsAcross;
	 BufferedImage img;
	 char[] pieces = {'@', '%', '#', '&', '8', 'B', 'G', 'O', '<', '=', '+', '~', '^', '*', '\'', '`', '-', ',', '.'};
	
	 int yBlocks;
	 int xBlocks;
	
	 int[][] map;
	
	 int pxPerBlock;
	
	public ImageToAscii(File f, int pixelsPerBlock) throws IOException{
		
		img = ImageIO.read(f);
		pxPerBlock = pixelsPerBlock;
		
		int height = img.getHeight();
		int width = img.getWidth();
		
		yBlocks = height / pxPerBlock;
		xBlocks = width / pxPerBlock;
		
		pixelsDown = height / yBlocks;
		pixelsAcross = width / xBlocks;
		
		map = new int[yBlocks][xBlocks];
		
		for(int y=0; y<yBlocks; y++){
			for(int x=0; x < xBlocks; x++){
				int i = getGreyscaleOfArea(x*pixelsAcross, y*pixelsDown);
				map[y][x] = i/pieces.length;
			}
		}
	}
	
	private int getGreyscaleOfArea(int x, int y){
			
		int counter = 0;
		int sum = 0;
		
		for(int i=y; i<y+pixelsDown; i++){
			for(int j=x; j<x+pixelsAcross; j++){
				sum += getGreyscale(Integer.toHexString(img.getRGB(j, i)));
				counter++;
			}
		}
		
		return sum/counter;
	}
	
	private  int getGreyscale(String hex){
		int r = Integer.valueOf(hex.substring(2, 4), 16);
		int g = Integer.valueOf(hex.substring(4, 6), 16);
		int b = Integer.valueOf(hex.substring(6, 8), 16);
		
		return (r+g+b)/3;
	}
	
	public void printMap(){
		for(int i =0; i < yBlocks; i++){
			for(int j=0; j < xBlocks; j++){
				System.out.print(pieces[map[i][j]]);
			}
			System.out.print("\n");
		}
	}
	
	public void printToFile(FileWriter writer) throws IOException{
		for(int i =0; i < yBlocks; i++){
			for(int j=0; j < xBlocks; j++){
				writer.write(""+pieces[map[i][j]]);
			}
			writer.write("\n");
		}
	}

}
