package comparableAndComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Runner {

	public static void main(String[] args) {
		List<Laptop> laps=new ArrayList<Laptop>();
		laps.add(new Laptop("Apple",6,1200));
		laps.add(new Laptop("MSI",16,850));
		laps.add(new Laptop("Acer",8,700));
		laps.add(new Laptop("Dell",12,800));
		
		
		Collections.sort(laps);
		for(Laptop l:laps) {
			System.out.println(l);
		}
		Comparator<Laptop> com=new Comparator<Laptop>() {

			@Override
			public int compare(Laptop o1, Laptop o2) {
				if(o1.getPrice()>o2.getPrice()) {
					return 1;
				}else {
					return -1;
				}
			}
			
		};
		Collections.sort(laps,com);
		for(Laptop l:laps) {
			System.out.println(l);
		}
	}

}
