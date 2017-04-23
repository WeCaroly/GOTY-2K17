package edu.unomaha.nknc.game.units.status;

import edu.unomaha.nknc.game.units.Hero;

public class Poison {
	private boolean isPoisoned = false;
	
	public Poison() {
		
	}
	
	public void applyPoison() {
		isPoisoned = true;
	}
	
	public void updatePoison(Hero hero) {
		if(isPoisoned) {
			hero.applyDamage(1);;
	
		}
	}
}