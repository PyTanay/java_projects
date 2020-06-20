import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MazeSolver {
	public static void main(String[] args) throws FileNotFoundException {
		ArrayList<Maze> mazes=new ArrayList<Maze>();
		readMaze(mazes,0); //second argument is just to tell the pointer from where to start
		int i=0;
		while(i<mazes.size()) {
			if(solveMaze(mazes.get(i))) {
				System.out.println("You Won!!!");
			}else {
				System.out.println("No path!!!");
			}
			i++;
		}
	}
		
	private static void readMaze(ArrayList<Maze> mazes,int a) throws FileNotFoundException {
		Scanner in=new Scanner(new File("mazes.txt"));
		Maze m=new Maze();
		String tempstr;
		for(int i=0;i<a;i++) {
			if(in.hasNextLine()) {
				tempstr=in.nextLine();
			}else {
				return;
			}
		}
		tempstr=in.nextLine();
		a++;
		ArrayList<ArrayList<Integer>> maze1=new ArrayList<ArrayList<Integer>>();
		while(tempstr.length()>1) {
			int[] numarr=Arrays.stream(tempstr.split(",")).mapToInt(x->Integer.parseInt(x)).toArray();
			ArrayList<Integer> numlist = IntStream.of(numarr).boxed().collect(Collectors.toCollection(ArrayList::new));
			maze1.add(numlist);
			tempstr=in.nextLine();
			a++;
		}
		int x=Integer.parseInt(tempstr);
		tempstr=in.nextLine();
		a+=2; //to skip though the separating line-break in the text file
		int y=Integer.parseInt(tempstr);
		m.start=new Position(x,y);
		m.maze=maze1;
		mazes.add(m);
		for(int i = 0; i < maze1.size(); i++){
            for(int j = 0; j < maze1.get(i).size(); j++){
                System.out.print(maze1.get(i).get(j) + " ");
            }
            System.out.println();
		}
		readMaze(mazes,a);
	}

	private static boolean solveMaze(Maze m) {
		m.path.push(m.start);
		while(true) {
			int x=m.path.peek().x;
			int y=m.path.peek().y;
			m.maze.get(y).set(x, 0);
			if(isValid(y+1,x,m)) {
				//down
				if(m.maze.get(y+1).get(x)==2) {
					System.out.println("Moved Down");
					return true;
				}else if(m.maze.get(y+1).get(x)==1) {
					System.out.println("Moved Down");
					m.path.push(new Position(y+1,x));
					continue;
				}
			}
			if(isValid(y,x+1,m)) {
				//right
				if(m.maze.get(y).get(x+1)==2) {
					System.out.println("Moved right");
					return true;
				}else if(m.maze.get(y).get(x+1)==1) {
					System.out.println("Moved right");
					m.path.push(new Position(y,x+1));
					continue;
				}
			}
			if(isValid(y-1,x,m)) {
				//up
				if(m.maze.get(y-1).get(x)==2) {
					System.out.println("Moved up");
					return true;
				}else if(m.maze.get(y-1).get(x)==1) {
					System.out.println("Moved up");
					m.path.push(new Position(y-1,x));
					continue;
				}
			}
			if(isValid(y,x-1,m)) {
				//left
				if(m.maze.get(y).get(x-1)==2) {
					System.out.println("Moved left");
					return true;
				}else if(m.maze.get(y).get(x-1)==1) {
					System.out.println("Moved left");
					m.path.push(new Position(y,x-1));
					continue;
				}
			}
			
			m.path.pop();
			if(m.path.size()==0) {
				return false;
			}
			System.out.println("Moved back");
		}
	}
	public static boolean isValid(int y,int x,Maze m) {
		if(y<0||y>=m.maze.size()||x<0||x>=m.maze.get(y).size()) {
			return false;
		}
		return true;
	}
}
