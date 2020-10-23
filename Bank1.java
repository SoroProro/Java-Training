import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.*;

class Data{
	int account;
	int balance;
	String name;
}

class Withdraw{
	int process(Data d){
		System.out.println("how much do you want to withdraw?");
		Scanner sc = new Scanner(System.in);
		int wd = sc.nextInt();
		d.balance = d.balance - wd;
		return(wd);
	}
	void storeTransaction(Data d, int count, int wamount){
		try {
        	BufferedWriter out = new BufferedWriter(new FileWriter("passbook.txt", true));
        	out.write("Withdrawn : " + wamount + "\n");
        	out.write("Latest Balance: " + d.balance + "\n");
        	out.write("-------------------------\n");
        	out.close();
        }
      	catch (IOException e) {
        System.out.println("exception occoured"+ e);
      }

	}
}


class Deposit{
	int process(Data d){
		System.out.println("how much do you want to deposit?");
		Scanner sc = new Scanner(System.in);
		int dp = sc.nextInt();
		d.balance = d.balance + dp;
		return(dp);
	}
	void storeTransaction(Data d, int count, int damount){
		try {
        	BufferedWriter out = new BufferedWriter(new FileWriter("passbook.txt", true));
        	out.write("Deposited : " + damount + "\n");
        	out.write("Latest Balance: " + d.balance + "\n");
        	out.write("-------------------------\n");
        	out.close();
        }
      	catch (IOException e) {
        System.out.println("exception occoured"+ e);
      }

	}
}

class Balance{
	void print(Data d){
		System.out.println("Your balance is: " + d.balance + " only");
	}
}


class Menu{
	void show(Data d){
		System.out.println("---- Welcome " + d.name + " ----");
		System.out.println("1. Balance");
		System.out.println("2. Withdraw");
		System.out.println("3. Deposit");
		System.out.println("4. Generate Passbook");
		int count = 1;
		int amt;
		Scanner sc = new Scanner(System.in);
		int option = sc.nextInt();
		switch(option){
			case 1: Balance b = new Balance();
					b.print(d);
					break;
			case 2: Withdraw w = new Withdraw();
					amt = w.process(d);
					w.storeTransaction(d,count,amt);
					count = count + 1;
					break;
			case 3: Deposit dp = new Deposit();
					amt = dp.process(d);
					dp.storeTransaction(d,count,amt);
					count = count + 1;
					break;
			case 4: System.out.println(d.name +" you passbook is generated ");
					break;
			default : System.out.println("This functionality is not available");
					break;
		}
	}
}
class Filee{
	void generate(Data d){
		File myObj = new File("passbook.txt");
		try {
      		FileWriter pen = new FileWriter("passbook.txt");
      		pen.write("----------- PASSBOOK------------\n");
      		pen.write("NAME : " + d.name + "\n");
      		pen.write("account : " + d.account+ "\n");
      		pen.write("INITIAL BALANCE : " + d.balance+ "\n");
      		pen.write("--------------------------------\n");
      		pen.close();
      		System.out.println("Account logged in successfully");
    		} catch (IOException e) {
      			System.out.println("An error occurred while generating passbook");
      			e.printStackTrace();
    			}

	}
}

public class Bank1{
	public static void main(String[] args){
	Scanner sc = new Scanner(System.in);
	Menu m = new Menu();
	Data d = new Data();
	System.out.println("Enter Your name");
	d.name = sc.nextLine();
	d.account = 12870;
	d.balance = 28890;
	Filee start = new Filee();
	start.generate(d);
	char answer = 'y';
	while(answer == 'y'){
		m.show(d);
		System.out.println("Interact again with the BanK? y/n");
		answer = sc.next().charAt(0);
	}
	}
}