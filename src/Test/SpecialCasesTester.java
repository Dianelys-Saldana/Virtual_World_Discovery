package Test;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.Test;

import Main.Questions;
import Text.WriterVRML;
import Util.Pair;

//Angel Hernandez Denis
//30/abril/2020

public class SpecialCasesTester {
	
	private Questions question = new Questions(null, null);
	WriterVRML w = new WriterVRML(null);
	BigDecimal bd = new BigDecimal(0);
	String height1 = "-10";
	String height2 = "0";
	String height3 = "7";
	String height4 = "15mt";
	String height5 = "jsTBJDW";
	String height6 = "$(*%jsyAFSJ";
	Pair start;
	Pair end;
	int startingX = 2;
	int startingY = 2;
	int endingX = 4;
	int endingY = 4;
	double result;
	
	@Test
	public void hasNumTest() {
		assertFalse(question.hasNum(height1));
		assertTrue(question.hasNum(height2));
		assertTrue(question.hasNum(height3));
		assertFalse(question.hasNum(height4));
		assertFalse(question.hasNum(height5));
		assertFalse(question.hasNum(height6));
	}
	
//	@Test
//	public void lengthTest() {
//		result = w.length(start, end);
//		bd = new BigDecimal(result).setScale(2, RoundingMode.DOWN);
//		assertEquals(2.82, bd.doubleValue(), 0);
//	}
	

}

