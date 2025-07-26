import java.io.File;
import java.util.Date;
import java.util.Scanner;

public class filedemo1main {

	public static void main(String[] args) {

		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the path :");
		String path=sc.next();
		sc.nextLine();
		File f=new File(path);
		if(f.exists()) {
			if(f.isFile()) {
				System.out.println(" File name :"+f.getName());
				System.out.println("File Size :"+f.length());
				System.out.println("LAst modified :"+new Date(f.lastModified()) );
			}
			else if(f.isDirectory()) {
				System.out.println("dir name :"+f.getName());
				String[] file=f.list();
				for(String fil:file) {
					System.out.println(fil);
				}
			}
		}
		else {
			System.out.println("Invalid path");
		}
	}

}
