package utilidades;


import gpdraw.*;

public class Grafica {
	
	SketchPad paper;
	DrawingTool pen;
	String[] vars;
	String[] cons;
	String[] axi;
	String[] rules;
	
	public Grafica(String[] variables, String[] constants, String[] axiom, String[] p_rules) {
		vars = variables;
		cons = constants;
		axi = axiom;
		rules = p_rules;
		paper = new SketchPad(1000, 1000);
		pen = new DrawingTool(paper);
	}
	
	public String generate(int iterations, String str) {
		if (iterations == 0)
			return str;
		String newStr = "";
		for (int strNum = 0; strNum < str.length(); strNum++) {
			String strChar = str.substring(strNum, strNum + 1);
			for (int consNum = 0; consNum < cons.length; consNum++) {
				if (strChar.equals(cons[consNum])) {
					newStr += strChar;
				}
			}
			for (int varNum = 0; varNum < vars.length; varNum++) {
				String varChar = vars[varNum];
				if (varChar.equals(strChar)) {
					for (int ruleNum = 0; ruleNum < rules.length; ruleNum++) {
						String ruleChar = rules[ruleNum].substring(0, 1);
						if (ruleChar.equals(varChar) && rules[ruleNum].substring(1, 3).equals("to")) {
							newStr += rules[ruleNum].substring(3);
						} // add rules to new String
					}
				}
			}
		}
	return generate(iterations - 1, newStr); // recursive call
	}
	public void createFractal(String str, String[] numOps, int[] moveTo, int angle) {
		//pen.setColor(Color.GREEN);
		pen.up();
		pen.move(moveTo[0], moveTo[1]); // arbitrary
		pen.setDirection(0);
		int countFrontBrack = 0;
		double[] xSavePos = new double[str.length() / 2];
		boolean[] xUsedPos = new boolean[str.length() / 2];
		double[] ySavePos = new double[str.length() / 2];
		double[] saveAng = new double[str.length() / 2];
		boolean[] yUsedPos = new boolean[str.length() / 2];
		for (int i = 0; i < str.length(); i++) {
			String strChar = str.substring(i, i + 1);
			for (int x = 0; x < numOps.length; x++) {
				String arrChar = numOps[x].substring(0, 1);
				if (strChar.equals(arrChar) && numOps[x].substring(1, 2).equals(":")) {
					if (numOps[x].substring(2).equals("f")) {
						pen.down();
						pen.forward(0.5);
						pen.up();
					}
					if (numOps[x].substring(2).equals("90")) {
						pen.turnRight(angle);
					}
					if (numOps[x].substring(2).equals("-90")) {
						pen.turnLeft(angle);
					}
					if (numOps[x].substring(2).equals("r")) {
						pen.turnRight(90);
					}
					if (numOps[x].substring(2).equals("l")) {
						pen.turnLeft(90);
					}
					if (numOps[x].substring(2).equals("m")) {
						pen.up();
						pen.forward(5);
					}
					if (numOps[x].substring(2).equals("[")) {
						countFrontBrack += 2;
						saveAng[countFrontBrack] = pen.getDirection();
						xSavePos[countFrontBrack] = pen.getXPos();
						ySavePos[countFrontBrack + 1] = pen.getYPos();
						pen.turnLeft(angle);
						// System.out.println(countFrontBrack);
					}
					if (numOps[x].substring(2).equals("]")) {
						for (int y = countFrontBrack; y >= 0; y -= 2) {
							if (xUsedPos[y] == false) {
								pen.move(xSavePos[y], ySavePos[y + 1]);
								pen.setDirection(saveAng[y]);
								pen.turnRight(angle);
								xUsedPos[y] = true;
								break;
							}
						}
					}
				}
			}
		}
	}
}
