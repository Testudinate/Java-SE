http://stackoverflow.com/questions/3481828/how-to-split-a-string-in-java

public class Main {

	public static void main(String[] args){
		String isAccessible = null;
		String[] partsAccesible = null;
		
		isAccessible = "F; сообщение об ошибке";
		
		partsAccesible = isAccessible.split(";");
		String partAccesible1 = partsAccesible[0];
		String partAccesible2 = partsAccesible[1];
		System.out.println( partAccesible2);
	}
}
