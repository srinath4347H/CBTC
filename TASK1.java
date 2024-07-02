package Internship;
import java.util.*;
public class GussingNumber {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		Random r=new Random();
		int maxAttempts=10;
		int rounds=3;
		int totalScore=0;
		for(int round=1;round<=rounds;round++)
		{
			System.out.print("The round "+round+" of "+rounds);
			int GussNumber=r.nextInt(100)+1;
			int attempt=0;
			boolean hasGussed=false;
			System.out.println();
			while(attempt<maxAttempts && !hasGussed)
			{
				System.out.print("Enter the guess(0-100):");
				int userNumber=sc.nextInt();
				attempt++;
				if(userNumber==GussNumber)
				{
					System.out.println("Congratulations you gussed the number:");
					int point=maxAttempts-attempt+1;
					System.out.println("you scored "+point+" in this round");
					totalScore+=point;
					hasGussed=true;
				}
				else if(userNumber<GussNumber)
				{
					System.out.println("The number is higher,Try again");
				}
				else
				{
					System.out.println("The number is lower,Try again");
				}
			}
			if(!hasGussed)
			{

			System.out.println("Sorry you Entered 10 attempts,"
					+ "the  guessed number was:"+ GussNumber);
			}
		}
		
		System.out.println("Game Over! Your total score is " + totalScore);
        sc.close();
	}

}
