package ie.gmit.computing.fuzzy;

import ie.gmit.computing.Item;
import ie.gmit.computing.characters.GameCharacter;
import ie.gmit.computing.characters.Hero;
import ie.gmit.computing.characters.Saracen;
import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.FunctionBlock;
import net.sourceforge.jFuzzyLogic.plot.JFuzzyChart;

//how gc interact
public class GCInteraction {
	private boolean isVictory = false;

	public Double fight(Item weapon, GameCharacter me, GameCharacter opponent) {
		String fileName = "fcl/rules.fcl";
		FIS fis = FIS.load(fileName, true);

		FunctionBlock fBlock = fis.getFunctionBlock("fight");
		JFuzzyChart.get().chart(fBlock);

		fis.setVariable("weapon", Integer.valueOf(weapon.getPower()));
		fis.setVariable("lifeOfMe", Integer.valueOf(me.getLifeForce()));
		fis.setVariable("lifeOfOp", Integer.valueOf(opponent.getLifeForce()));
		fis.evaluate();

		Double victory = fis.getVariable("victory").getValue();
		// me.getLifeForce() = me.getLifeForce() * victory;
		return victory;
	}
}