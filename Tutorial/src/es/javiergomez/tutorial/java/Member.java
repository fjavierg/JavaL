package es.javiergomez.tutorial.java;

/***************************
 * Anonymous class and lambda expressions
 */

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;


public class Member {

    public enum Sex {
        MALE, FEMALE
    }

    String name;
    Calendar birthday;
    Sex gender;
    String emailAddress;


	public Member(String name, Calendar birthday, Sex gender, String emailAddress) {
		super();
		this.name = name;
		this.birthday = birthday;
		this.gender = gender;
		this.emailAddress = emailAddress;
	}
	
 /**   public int getAge() {
    	Calendar now = Calendar.getInstance();
    	Calendar birthdayCal = Calendar.getInstance();
    	birthdayCal.setTime(birthday);
    	Long diff = (now.getTimeInMillis() - birthdayCal.getTimeInMillis())/(365 * 24 * 60 * 60 * 1000);
        return diff.intValue();
    }**/
	
    public int getAge() {
        Calendar today = GregorianCalendar.getInstance();
        int age = today.get(Calendar.YEAR) - birthday.get(Calendar.YEAR);
        if((birthday.get(Calendar.MONTH) > today.get(Calendar.MONTH))
            || (birthday.get(Calendar.MONTH) == today.get(Calendar.MONTH)
            && birthday.get(Calendar.DAY_OF_MONTH)
            > today.get(Calendar.DAY_OF_MONTH)))
        {
            age--;
        }
        return age;
    }
    public void printMember() {
        System.out.println(name + ", " + this.getAge());
    }
    public Sex getGender() {
        return gender;
    }
    
    public String getName() {
        return name;
    }
    
    
    public String getEmailAddress() {
        return emailAddress;
    }
    // Approach 1: Create Methods that Search for Members that Match One
    // Characteristic

    public static void printMembersOlderThan(List<Member> roster, int age) {
        for (Member p : roster) {
            if (p.getAge() >= age) {
                p.printMember();
            }
        }
    }

    // Approach 2: Create More Generalized Search Methods

    public static void printMembersWithinAgeRange(
        List<Member> roster, int low, int high) {
        for (Member p : roster) {
            if (low <= p.getAge() && p.getAge() < high) {
                p.printMember();
            }
        }
    }

    // Approach 3: Specify Search Criteria Code in a Local Class
    // Approach 4: Specify Search Criteria Code in an Anonymous Class
    // Approach 5: Specify Search Criteria Code with a Lambda Expression
    interface CheckMember {
        boolean test(Member p);
    }
    
    public static void printMembers(
        List<Member> roster, CheckMember tester) {
        for (Member p : roster) {
            if (tester.test(p)) {
                p.printMember();
            }
        }
    }
    
    
 // Approach 6: Use Standard Functional Interfaces with Lambda Expressions
    
    interface Predicate<Member> {
        boolean test(Member t);
    }

    public void printMembersWithPredicate(
    	    List<Member> roster, Predicate<Member> tester) {
    	    for (Member p : roster) {
    	        if (tester.test(p)) {
    	            p.printMember();
    	        }
    	    }
    	}
    
    

    
    
    public static void main (String[] args){
    	
        List<Member> roster = new ArrayList<>();
        roster.add(
            new Member(
            "Fred",
            new GregorianCalendar(1980, 6, 20),
            Member.Sex.MALE,
            "fred@example.com"));
        roster.add(
            new Member(
            "Jane",
            new GregorianCalendar(1990, 7, 15),
            Member.Sex.FEMALE, "jane@example.com"));
        roster.add(
            new Member(
            "George",
            new GregorianCalendar(1991, 8, 13),
            Member.Sex.MALE, "george@example.com"));
        roster.add(
            new Member(
            "Bob",
            new GregorianCalendar(2000, 9, 12),
            Member.Sex.MALE, "bob@example.com"));

        for (Member p : roster) {
            p.printMember();
        }

        // Approach 1: Create Methods that Search for Members that Match One
        // Characteristic

        System.out.println("Members older than 20:");
        printMembersOlderThan(roster, 20);
        System.out.println();

        // Approach 2: Create More Generalized Search Methods

        System.out.println("Members between the ages of 14 and 30:");
        printMembersWithinAgeRange(roster, 14, 30);
        System.out.println();

        // Approach 3: Specify Search Criteria Code in a Local Class

        System.out.println("Members who are eligible for Selective Service:");

        class CheckMemberEligibleForSelectiveService implements CheckMember {
           public boolean test(Member p) {
                return p.getGender() == Member.Sex.MALE
                    && p.getAge() >= 18
                    && p.getAge() <= 25;
            }
        }

        printMembers(
            roster, new CheckMemberEligibleForSelectiveService());


        System.out.println();

        // Approach 4: Specify Search Criteria Code in an Anonymous Class

        System.out.println("Members who are eligible for Selective Service " +
            "(anonymous class):");

        printMembers(
            roster,
            new CheckMember() {
                public boolean test(Member p) {
                    return p.getGender() == Member.Sex.MALE
                        && p.getAge() >= 18
                        && p.getAge() <= 25;
                }
            }
        );

        System.out.println();

        // Approach 5: Specify Search Criteria Code with a Lambda Expression

        System.out.println("Members who are eligible for Selective Service " +
            "(lambda expression):");

        Member p;
//		printMembers(
//            roster,
//            p -> p.getGender() == Member.Sex.MALE
//                && p.getAge() >= 18
//                && p.getAge() <= 25
//        );

        System.out.println();

        // Approach 6: Use Standard Functional Interfaces with Lambda
        // Expressions

        System.out.println("Members who are eligible for Selective Service " +
            "(with Predicate parameter):");

//        printMembersWithPredicate(
//            roster,
//            p -> p.getGender() == Member.Sex.MALE
//                && p.getAge() >= 18
//                && p.getAge() <= 25
//        );

        System.out.println();
    	

    }

}
