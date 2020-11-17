package amortizationdevinudy;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//11 inputs to take in
public class AmortizationDevinUdy
{

    public static void main(String[] args)
    {

        String name, address, city, state, dueDate, dueMonth;
        int zip, loanMonth, loanDay, loanYear, paymentAmount, paymentNumber;
        double loanAmount, apr, interest, principle, balance, payment;
        InputFile loanReport;
        double aprPercentage;

        loanReport = new InputFile("fcrc loan data.txt");

//        displayReport();
//        Window Pane
        JFrame jf;
        JTextArea jta;
        JScrollPane jsp;
//        int count;

        //the following instantiates the two objects
        jf = new JFrame();
        jta = new JTextArea();
        // now the text area is sent to the scroll pane
        jsp = new JScrollPane(jta);

        // change the size and position of the window
        jf.setSize(400, 350);
        jf.setLocation(400, 250);

        //make the X box of the window stop the program
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // add the scroll pane to the JFrame instead of the text area
        jf.add(jsp);

        jf.setVisible(true);
//        End Window pane details

        while (!loanReport.eof())
        {
            name = loanReport.readString();
            address = loanReport.readString();
            city = loanReport.readString();
            state = loanReport.readWord();
            zip = loanReport.readInt();
            loanMonth = loanReport.readInt();
            loanDay = loanReport.readInt();
            loanYear = loanReport.readInt();
            loanAmount = loanReport.readDouble();
            apr = loanReport.readDouble();
            paymentAmount = loanReport.readInt();
            dueMonth = "";

//            Month format
        switch(loanMonth) {
            case 1 -> dueMonth = "January";
            case 2 -> dueMonth = "February";
            case 3 -> dueMonth = "March";
            case 4 -> dueMonth = "April";
            case 5 -> dueMonth = "May";
            case 6 -> dueMonth = "June";
            case 7 -> dueMonth = "July";
            case 8 -> dueMonth = "August";
            case 9 -> dueMonth = "September";
            case 10 -> dueMonth = "October";
            case 11 -> dueMonth = "November";
            case 12 -> dueMonth = "December";
//            If december, add++ to counter at beginning of loop.
        }
        if (dueMonth.equals("December")) {
            loanYear = loanYear++;
        }
//            End Month format

            dueDate = dueMonth + " " + loanDay + "," + loanYear;
            balance = loanAmount;
            aprPercentage = apr * 100;
            payment = 0;
            paymentNumber = 0;

//            Payment amount switch
            switch (paymentAmount)
            {
                case 0 ->
                    payment = 50;
                case 1 ->
                    payment = 55;
                case 2 ->
                    payment = 75;
                case 3 ->
                    payment = 100;
                case 4 ->
                    payment = 0.05 * loanAmount;
                case 5 ->
                    payment = 0.06 * loanAmount;
                case 6 ->
                    payment = 0.05 * loanAmount;
                case 7 ->
                    payment = 0.04 * loanAmount;
                case 8 ->
                    payment = 0.03 * loanAmount;
                case 9 ->
                    payment = 0.02 * loanAmount;
            }

//            Heading
            jta.append("First Community Redevelopment Corporation\n");
            jta.append("101 1st Street\n");
            jta.append("Bloomingville, TN 41663\n");
            jta.append("\n");
            jta.append("\t\t\t\t" + name + "\n");
            jta.append("\t\t\t\t" + address + "\n");
            jta.append("\t\t\t\t" + city + ", " + state + ", " + zip + "\n");
            jta.append("\n");
            jta.append("Loan Amount: \t" + "$" + loanAmount + "\n");
            jta.append("Interest Rate: \t" + Math.round(aprPercentage * 100.0) / 100.0 + "%\n");
            jta.append("\n");
            jta.append("Payment# \t Due Date \t Payment \t Interest \t Principle \t Balance \n");
            jta.append("--------------------------------------------------------------------\n");

            int count;
            double interestDisplay, principleDisplay, balanceDisplay, paymentTotal, interestTotal;
            count = 0;
            paymentTotal = 0;
            interestTotal = 0;
//            Table
            while (balance > 0)
            {
                count++;
                paymentNumber++;
                interest = apr / 12 * balance;
                principle = payment - interest; //paymentAmount / 75
                balance -= principle;
                interestDisplay = Math.round(interest * 100.0) / 100.0;
                principleDisplay = Math.round(principle * 100.0) / 100.0;

                if (Math.round(balance * 100.0) / 100.0 < payment)
                {
                    payment = Math.round(balance * 100.0) / 100.0;
                    balance = payment - Math.round(balance * 100.0) / 100.0;
                }
                balanceDisplay = Math.round(balance * 100.0) / 100.0;

                jta.append("    " + paymentNumber + "\t" + dueDate + "          " + payment + "\t" + interestDisplay + "\t" + principleDisplay + "\t" + balanceDisplay + "\n");
                if (dueMonth.equals("December")) {
                    loanYear = loanYear++;
                }
                paymentTotal += payment;
                interestTotal += interestDisplay;

            }
            
//            jta.append("Totals: " + paymentTotal + " " + interestTotal + "\n");
            jta.append("____________________________________________________________________\n");

        }
//        End Window Pane

//        LocalDateTime myDateObj = LocalDateTime.now();
//        System.out.println("Before Formatting: " + myDateObj);
////        default format is yyyy-MM-dd
//        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("MMMM dd, yyyy");
//
//        String formattedDate = myDateObj.format(myFormatObj);
//        System.out.println("After Formatting: " + formattedDate);
//        import java.util. *; 
//
//          Look into this -> Calendar Class in Java with examples; Java program to demonstrate add() method
//        public class Calendar5
//        {
//
//            public static void main(String[] args)
//            {
//                // creating calendar object 
//                Calendar calendar = Calendar.getInstance();
//                calendar.add(Calendar.DATE, -15);
//                System.out.println("15 days ago: " + calendar.getTime());
//                calendar.add(Calendar.MONTH, 4);
//                System.out.println("4 months later: " + calendar.getTime());
//                calendar.add(Calendar.YEAR, 2);
//                System.out.println("2 years later: " + calendar.getTime());
//            }
//        }

//                  Look at this too -> Get first days in a month with year input and first day input
//                Scanner input = new Scanner(System.in);
//                System.out.print("Enter Year:");
//                int year = input.nextInt();
//                Calendar cal = Calendar.getInstance();
//                for (int i = 0; i < 12; i++)
//                {
//                    cal.set(year, i, 1);
//                    System.out.printf("the first day of %s is %s\n",
//                            cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault()),
//                            cal.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault()));
//                }//end for i  

    }

}
