package be.vdab.personeel.constraints;

import java.time.LocalDate;

public class RijksregisternummerControle {

	public static void main(String[] args) {
		
		long rijksregisternr=78122923726L; 
				
		long eerste9Cijfers=rijksregisternr/100; 
		long controlegetal=rijksregisternr%100;
		
		LocalDate geboorte=LocalDate.of(1978, 11, 29); 
		
		long eerste6Cijfers = rijksregisternr/100000;
		
		System.out.println(eerste6Cijfers/10000 == geboorte.getYear() % 100);
		
		System.out.println((eerste6Cijfers % 10000)/100 == geboorte.getMonthValue());
		
		System.out.println(eerste6Cijfers % 100 == geboorte.getDayOfMonth()); 
		
		System.out.println(controlegetal == (97-eerste9Cijfers%97)); 
	}

}
