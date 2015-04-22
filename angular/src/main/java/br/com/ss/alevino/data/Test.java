package br.com.ss.alevino.data;

public class Test {

	public static void main(String[] args) {

		char[] espaco = { ' ' };
		char[] charArray = { 'a', ' ', 'c', 'd', 'e' };
		char[] charSemEspaco = {'a', ' ', 'c', 'd', 'e'};
		int j =0;

		for (int i = 0; i < charArray.length; i++) {
			if (espaco[0] != charArray[i]) {
				//System.out.println(charArray[i]);
				charSemEspaco[j] = charArray[i];
				j++;
			}
		}
		
		for (int i = 0; i < charSemEspaco.length; i++) {
			System.out.println(charSemEspaco[i]);
			
		}
	}

}
