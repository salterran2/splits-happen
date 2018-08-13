package com.foo.bowling;

public class Bowling {

	private static final char NONE = '-';
	private static final char STRIKE = 'X';
	private static final char SPARE = '/';
	
	public static void main(String[] args) {
		System.out.println( totalScore( args[0] ) );
	}

	/**
	 * returns totalScore for rolls.
	 * 
	 * Assume that rolls are valid and number of rolls and frames are correct
	 * 
	 * @param tryInput
	 * @return totalScore
	 */
	private static int totalScore(String rollInput) {
		char[] rolls = rollInput.toCharArray();
		int index = 0;
		int numberOfRolls = rolls.length;
		int totalScore = 0;
		float currentFrame = 1;
		boolean isSecondTry = false;

		// 1~9 frames
		for ( index = 0; currentFrame < 10; index++ ) {
			// if second try, go to next frame
			// else 
			//	if first try is STRIKE
			//  go to next frame
			if (isSecondTry) {
				currentFrame += 1;
				isSecondTry = false;
			} else {
				if (rolls[index] == STRIKE) {
					currentFrame += 1;
				}
				isSecondTry = true;
			}
			
			// get actual Score
			totalScore += getActualScore(rolls, index);
		}

		// if spare made in 10th frame, then 10 + last roll
		if (rolls[numberOfRolls - 2] == '/') {
			// assume last roll is not a spare which cannot be
			totalScore += 10 + scoreForRoll(rolls, numberOfRolls - 1);
		} else {
			while( index < numberOfRolls )
			{
				totalScore += scoreForRoll(rolls, index);
				index++;
			}
		}
		return totalScore;
	}
	
	private static int getActualScore(char[] rolls, int index) {
		char currentRoll = rolls[index];
		if (currentRoll == STRIKE)
			return 10 + scoreForRoll( rolls, index+1 ) + scoreForRoll(rolls, index+2);
		else if (currentRoll == SPARE)
			return scoreForRoll( rolls, index ) + scoreForRoll( rolls, index+1 );
		else
			return scoreForRoll( rolls, index ); 
	}
	
	private static int scoreForRoll(char[] rolls, int pos) {
		char currentRoll = rolls[pos];
		
		// assume input is valid
		switch ( currentRoll ) {
			case STRIKE:
				return 10;
			case SPARE:
				return 10 - Character.getNumericValue(rolls[pos - 1]);
			case NONE:
				return 0;
			default:
				return Character.getNumericValue( currentRoll );
		}
	}
}
