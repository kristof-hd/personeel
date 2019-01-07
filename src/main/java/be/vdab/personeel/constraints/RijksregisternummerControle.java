package be.vdab.personeel.constraints;

import java.time.LocalDate;

public class RijksregisternummerControle {

	public static void main(String[] args) {
		
		//long rijksregisternr=78112923726L; 
		long rijksregisternr=10011705866L;
		
		//LocalDate geboorte=LocalDate.of(1978, 11, 29); 
		LocalDate geboorte=LocalDate.of(2010, 1, 17);

		long eerste9Cijfers=rijksregisternr/100; 
		long controlegetal=rijksregisternr%100;
		long eerste6Cijfers = rijksregisternr/100000;

		if (geboorte.getYear() < 2000) {
			System.out.println( (eerste6Cijfers/10000 == geboorte.getYear() % 100) && ((eerste6Cijfers % 10000)/100 == geboorte.getMonthValue()) && 
					(eerste6Cijfers % 100 == geboorte.getDayOfMonth()) && controlegetal == (97-eerste9Cijfers%97) ); 
		}
		else {
			System.out.println( (eerste6Cijfers/10000 == geboorte.getYear() % 100) && ((eerste6Cijfers % 10000)/100 == geboorte.getMonthValue()) && 
					(eerste6Cijfers % 100 == geboorte.getDayOfMonth()) && controlegetal == (97-(2*Math.pow(10, 9)+eerste9Cijfers)%97) );
		}		
		
		//System.out.println(eerste6Cijfers/10000 == geboorte.getYear() % 100);
		//System.out.println((eerste6Cijfers % 10000)/100 == geboorte.getMonthValue());
		//System.out.println(eerste6Cijfers % 100 == geboorte.getDayOfMonth()); 
		//System.out.println(controlegetal == (97-eerste9Cijfers%97)); 
	}

}
