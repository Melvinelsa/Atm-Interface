import java.util.*;

public class AtmInterface {
    private static double balance = 20000.00;
    private static String userId = "user1241";
    private static String pin = "4356";
    private static List<String> transactionHistory = new ArrayList<>();

    public static void main(String[] args){
        try (Scanner sc=new Scanner(System.in)){

        System.out.println("Welcome to the ATM");
        System.out.print("Enter your user ID: ");
        String inputUserId=sc.nextLine();

        if(isValidUserId(inputUserId)){
            System.out.print("Enter your pin: ");
            String inputPin = sc.nextLine();

        if(authenticate(inputUserId,inputPin)){
            System.out.println("Authentication successful. ");
            while(true){
            System.out.println("1.Check Balance");
            System.out.println("2.Deposit");
            System.out.println("3.Withdraw");
            System.out.println("4.Transfer");
            System.out.println("5.Transaction History");
            System.out.println("6.Quit");
            System.out.print("Enter your choice: ");
            int choice=sc.nextInt();

            switch(choice){
                case 1:checkbalance();
                        break;

                case 2: deposit(sc);
                        break;

                case 3: withdraw(sc);
                        break;

                case 4:transfer(sc);
                        break;

                case 5: displayTransactionHistory();
                        break;

                case 6: System.out.println("Thank you for using the ATM.");
                        return;
                
                default: System.out.println("Invalid choice. Please try again");
            }
        }
    }
    else{
        System.out.println("Authentication failed. Please try again.");
    }
}
else{
    System.out.println("Invalid user Id. Please enter valid user ID.");
  }
 }
}
private static boolean isValidUserId(String inputUserId){
    return !inputUserId.isEmpty();
}
private static boolean authenticate(String inputUserId,String inputPin){
    return userId.equals(inputUserId) && pin.equals(inputPin);
}

private static void checkbalance(){
    System.out.println("Your balance is $ "+balance);
}

private static void deposit(Scanner sc){
    System.out.print("Enter the deposit amount: ");
    double amount=sc.nextDouble();
    if(amount>0){
        balance = balance + amount;
        String transaction = "Deposit: +" + amount+ ", New Balance: $" +balance;
        transactionHistory.add(transaction);
        System.out.println("Deposit successful. Your new balance is $" +balance);
    }
    else{
        System.out.println("Invalid amount. Deposit failed.");
    }
}

private static void withdraw(Scanner sc){
    System.out.print("Enter the amount: ");
    double amount=sc.nextDouble();
    if(amount>0 && amount <= balance){
        balance -= amount;
        String transaction = "Withdraw: -" + amount + ",New Balance: " +balance;
        transactionHistory.add(transaction);
        System.out.println("Withdrawal successful. Your new balance is $" +balance);
    }
    else{
        System.out.println("Insufficient balance. Withdrawal failed.");
      }
   }

   private static void transfer(Scanner sc){
    System.out.println("Enter the recipient's user Id: ");
    String recipientUserId = sc.next();

    if(!isValidRecipient(recipientUserId)){
        System.out.println("Invalid recipient ID. Transfer failed.");
        return;
    }

    System.out.println("Enter the transfer amount: ");
    double amount = sc.nextDouble();
    if (amount > 0 && amount <= balance){
        balance -= amount;
        String transaction = "Tranfer to " + recipientUserId + ": -" +amount + ",New Balance: $" + balance;
        transactionHistory.add(transaction);
        System.out.println("Transfer successful. Your new balance is $ " +balance);
    }
    else{
        System.out.println("Invalid transfer amount or insufficient balance. Transfer failed ");
    }
   }

   
   private static boolean isValidRecipient(String recipientUserId){
    return recipientUserId.length() ==8 && !recipientUserId.equals(userId);// must be 8 characters long and have a different value than the sender's ID.
  }

   private static void displayTransactionHistory(){
    System.out.println("Transaction History: ");
    for (String transaction :transactionHistory){
        System.out.println(transaction);
    }
   }
}
