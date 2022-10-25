package application;
import java.util.Random;

public class Dice {

	private Random rn = new Random();
	private int range = 6;

	public Die[] dice;
	
public Dice() {
		dice = new Die[5];
		for (int i = 0; i < dice.length; i++) {
				dice[i] = new Die();
				}
	}

public Die get(int i) {
	return dice[i];
}

	public void roll() {
		
		for (int i = 0; i < dice.length; i++) {
			if (!dice[i].isOnHold()) {
				dice[i].setDie(rn.nextInt(range) +1);
			} }
		}
	
	public int size() {
		return dice.length;
	}

	public String toString() {
		String diceValue = "";
		for (int i = 0; i < dice.length; i++) {
			if (!dice[i].isOnHold()) {
				diceValue = diceValue + (i+1) + ": " + dice[i].dieValue() + " not fixed; \n";
			}
			else {
				diceValue = diceValue + (i+1) + ": " + dice[i].dieValue() + " fixed; \n";
		}
	}
		return diceValue;
	}
	
}

class Die {

	private boolean fixed;
	
	private int die;
	
	
	public Die (int x) {
		die = x;
	}
	
	public Die() {
		die = 0;
	}
	
	public void setDie (int x) {
		die = x;
	}

	public int dieValue() {
		return die;
	}
	
	 void holdDie () {
		if (fixed) {fixed = false;}
		else {fixed = true;}
		
	}
		
	boolean isOnHold() {
		return fixed;
		}
	
	

}