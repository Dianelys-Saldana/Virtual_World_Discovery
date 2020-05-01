package Test;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.Test;

import Main.Questions;
import Text.WriterVRML;
import Util.Pair;

//Autor: Angel D. Hernandez Denis
//Fecha: 30/abril/2020

public class SpecialCasesTester {
	
	Questions question = new Questions(null, null);
	WriterVRML w = new WriterVRML(null);
	BigDecimal bd = new BigDecimal(0);
	String height1 = "-10";
	String height2 = "0";
	String height3 = "7";
	String height4 = "15mt";
	String height5 = "jsTBJDW";
	String height6 = "$(*%jsyAFSJ";
	Pair start1 = new Pair(2,2);
	Pair end1 = new Pair(4,4);
	Pair start2 = new Pair(2,-2);
	Pair end2 = new Pair(-4,4);
	Pair start3 = new Pair(0,0);
	Pair end3 = new Pair(0,0);
	Pair start4 = new Pair(-2,-2);
	Pair end4 = new Pair(-4,-4);
	Pair start5 = new Pair(243,161);
	Pair end5 = new Pair(564,785);
	Pair start6 = new Pair(-613,289);
	Pair end6 = new Pair(424,-320);
	Pair start7 = new Pair(0,0);
	Pair end7 = new Pair(0,0);
	Pair start8 = new Pair(-243,-161);
	Pair end8 = new Pair(-564,-785);
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
	
	@Test
	public void lengthTest() {
		result = w.length(start1, end1);
		bd = new BigDecimal(result).setScale(2, RoundingMode.DOWN);
		assertEquals(2.82, bd.doubleValue(), 0);
		
		result = w.length(start2, end2);
		bd = new BigDecimal(result).setScale(2, RoundingMode.DOWN);
		assertEquals(8.48, bd.doubleValue(), 0);
		
		result = w.length(start3, end3);
		bd = new BigDecimal(result).setScale(2, RoundingMode.DOWN);
		assertEquals(0.00, bd.doubleValue(), 0);
		
		result = w.length(start4, end4);
		bd = new BigDecimal(result).setScale(2, RoundingMode.DOWN);
		assertEquals(2.82, bd.doubleValue(), 0);
	}
	
	@Test
	public void angleTest() {
		result = w.angle(start1, end1);
		bd = new BigDecimal(result).setScale(2, RoundingMode.DOWN);
		assertEquals(0.78, bd.doubleValue(), 0);
		
		result = w.angle(start2, end2);
		bd = new BigDecimal(result).setScale(2, RoundingMode.DOWN);
		assertEquals(-0.78, bd.doubleValue(), 0);
		
		result = w.angle(start3, end3);
		assertEquals(Double.NaN, result, 0.0);
		
		result = w.angle(start4, end4);
		bd = new BigDecimal(result).setScale(2, RoundingMode.DOWN);
		assertEquals(0.78, bd.doubleValue(), 0);
	}
	
	@Test
	public void xTransTest() {
		result = w.xTrans(start5, end5);
		bd = new BigDecimal(result).setScale(2, RoundingMode.DOWN);
		assertEquals(-108.5, bd.doubleValue(), 0);
		
		result = w.xTrans(start6, end6);
		bd = new BigDecimal(result).setScale(2, RoundingMode.DOWN);
		assertEquals(-606.5, bd.doubleValue(), 0);
		
		result = w.xTrans(start7, end7);
		bd = new BigDecimal(result).setScale(2, RoundingMode.DOWN);
		assertEquals(-512, bd.doubleValue(), 0);
		
		result = w.xTrans(start8, end8);
		bd = new BigDecimal(result).setScale(2, RoundingMode.DOWN);
		assertEquals(-915.5, bd.doubleValue(), 0);
	}
	
	@Test
	public void zTransTest() {
		result = w.zTrans(start5, end5);
		bd = new BigDecimal(result).setScale(2, RoundingMode.DOWN);
		assertEquals(105.5, bd.doubleValue(), 0);
		
		result = w.zTrans(start6, end6);
		bd = new BigDecimal(result).setScale(2, RoundingMode.DOWN);
		assertEquals(-383, bd.doubleValue(), 0);
		
		result = w.zTrans(start7, end7);
		bd = new BigDecimal(result).setScale(2, RoundingMode.DOWN);
		assertEquals(-367.5, bd.doubleValue(), 0);
		
		result = w.zTrans(start8, end8);
		bd = new BigDecimal(result).setScale(2, RoundingMode.DOWN);
		assertEquals(-840.5, bd.doubleValue(), 0);
	}
}

