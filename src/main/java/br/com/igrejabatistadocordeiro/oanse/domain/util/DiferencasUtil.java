package br.com.igrejabatistadocordeiro.oanse.domain.util;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

@Component
public class DiferencasUtil {

	public boolean temDiferenca(String s1, String s2) {
		if (s1 == null && s2 == null)
			return false;
		if (s1 == null || s2 == null)
			return true;
		return !s1.equals(s2);
	}

	public boolean temDiferenca(Boolean b1, Boolean b2) {
    	return b1 != b2;
    }
	
	public boolean temDiferenca(boolean b1, boolean b2) {
		return b1 != b2;
	}

	public boolean temDiferenca(Double d1, Double d2) {
    	return d1.compareTo(d2)!=0;
    }
	
	public boolean temDiferenca(double d1, double d2) {
		return d1 != d2;
	}
	
	public boolean temDiferenca(Integer i1, Integer i2) {
		return i1.compareTo(i2)!=0;
	}
	
	public boolean temDiferenca(int i1, int i2) {
		return i1 != i2;
	}
	
	public boolean temDiferenca(BigDecimal d1, BigDecimal d2) {
    	return d1.compareTo(d2)!=0;
    }
}
