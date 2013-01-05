import java.util.Scanner;


public class Main {
	
	public static void main(String[] args) {
		
		Scanner read= new Scanner(System.in);
		System.out.println("Introduzca el String o palabra que desea sustraer algun caracter");
		String string= read.next();
		System.out.println("ingrese el caracter que desea remover");
		String caracter= read.next();
		
		char [] asdf= caracter.toCharArray();
		for(int i= 0; i< asdf.length; i ++){
			System.out.println("");
			lessChar(string, asdf[i]);
		}
		
	}
	public static void lessChar(String string, char lessCharacter){
		char [] array= string.toCharArray();
		for(int i= 0; i< array.length; i++){
			if(array[i] != lessCharacter){
				System.out.print(array[i] + "");
				continue;
			}
		}
	}
}
