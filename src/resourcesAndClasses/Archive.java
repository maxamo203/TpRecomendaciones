package resourcesAndClasses;

import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.LinkedList;

public class Archive {
	Scanner scanner = null;
	
	public Archive() {}
	
	//READ THE ATTRACTION LIST
	public ArrayList<Sight> ReadSights(String path){
		ArrayList<Sight> MyAtraccionList = new ArrayList<Sight>();
		try {
			File file = new File(path);
			scanner = new Scanner(file);

			while(scanner.hasNextLine()) {
				String name = scanner.next();
				Double price = scanner.nextDouble();
				Double time = scanner.nextDouble();
				int quota = scanner.nextInt();
				String StringPreferency = scanner.next();
				
				//TURN THE STRING INTO A PREFECENCY
				Preferency preferency = Preferency.valueOf(StringPreferency);
				
				Sight Data = new Sight(name, price, time, quota, preferency);
				MyAtraccionList.add(Data);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
			
		} finally {
			scanner.close();
		}
		return MyAtraccionList;
	}
	
	public ArrayList<User> ReadUsers(String path){
		ArrayList<User> MyUserList = new ArrayList<User>();
		
		try {
			File file = new File(path);
			scanner = new Scanner(file);

			while(scanner.hasNextLine()) {
				String name = scanner.next();
				Double totalMount = scanner.nextDouble();
				Double totalTime = scanner.nextDouble();
				String StringPreferency = scanner.next();
				
				//TURN THE STRING INTO A PREFECENCY
				Preferency preferency = Preferency.valueOf(StringPreferency);
				
				User Data = new User(name, totalMount, totalTime, preferency);
				
				MyUserList.add(Data);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
			
		} finally {
			scanner.close();
		}
		return MyUserList;
	}

	public ArrayList<Promotion> ReadPromotion(String path, ArrayList<Sight> MyAtraccionList){
		ArrayList<Promotion> MyPromotionList = new ArrayList<Promotion>();
		
		try {
			File file = new File(path);
			scanner = new Scanner(file);
			String Attraction = null;
			Promotion promotion = null;

			while(scanner.hasNextLine()) {
				String type = scanner.next();
				char charType = type.charAt(0);
				
				if(charType == 'A') {
					double percentage = scanner.nextDouble();
					String StringPreferency = scanner.next();
					
					//TURN THE STRING INTO A PREFECENCY
					Preferency preferency = Preferency.valueOf(StringPreferency);
					
					promotion = new PromotionPercentual(preferency, percentage);
					//Scan para saltear el "["
					scanner.next();
					Attraction = scanner.next();
				}
				else if(charType == 'B') {
					double price = scanner.nextInt();
					String StringPreferency = scanner.next();
					
					//TURN THE STRING INTO A PREFECENCY
					Preferency preferency = Preferency.valueOf(StringPreferency);
					
					promotion = new PromotionAbsolute(preferency, price);
					//Scan para saltear el "["
					scanner.next();
					Attraction = scanner.next();
				}
				
				else if(charType == 'C') {
					String StringPreferency = scanner.next();
					
					//TURN THE STRING INTO A PREFECENCY
					Preferency preferency = Preferency.valueOf(StringPreferency);

					promotion = new PromotionFreeSights(preferency);
					scanner.next();
					Attraction = scanner.next();
					((PromotionFreeSights)promotion).addFreeSight(Attraction, MyAtraccionList);
					Attraction = scanner.next();
				}

				//chequeo que no es el corchete final
				while(Attraction.charAt(0) != ']') {
					promotion.loadSight(Attraction, MyAtraccionList);
					Attraction = scanner.next();
				}
				MyPromotionList.add(promotion);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
			
		} finally {
			scanner.close();
		}
		return MyPromotionList;
	}
}
