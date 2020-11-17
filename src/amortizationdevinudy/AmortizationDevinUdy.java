package amortizationdevinudy;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class AmortizationDevinUdy
{

    @SuppressWarnings("UnusedAssignment")
    public static void main(String[] args)
    {

        String name, address, city, state, dueDate;
        int zip, loanMonth, loanDay, loanYear, paymentAmount, paymentNumber;
        double loanAmount, apr, interest, principle, balance, payment;
        InputFile loanReport;
        double aprPercentage;

        loanReport = new InputFile("fcrc loan data.txt");

//        Window Pane
        JFrame jf;
        JTextArea jta;
        JScrollPane jsp;

        //the following instantiates the two objects
        jf = new JFrame();
        jta = new JTextArea();
        // now the text area is sent to the scroll pane
        jsp = new JScrollPane(jta);

        // change the size and position of the window
        jf.setSize(700, 550);
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

            balance = loanAmount;
            aprPercentage = apr * 100;
            payment = 0;
            paymentNumber = 0;

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
            jta.append("Payment# \t Due Date \t\tPayment \tInterest \tPrinciple \tBalance \n");
            jta.append("---------------------------------------------------------------------------------\n");

//            End Heading
//            Table
            int count;
            double interestDisplay, principleDisplay, balanceDisplay;
            String stringMonth = null;
            count = 0;

            //            Payment switch
            switch (paymentAmount)
            {
                case 0 -> payment = 50;
                case 1 -> payment = 55;
                case 2 -> payment = 75;
                case 3 -> payment = 100;
                case 4 -> payment = 0.05 * loanAmount;
                case 5 -> payment = 0.06 * loanAmount;
                case 6 -> payment = 0.05 * loanAmount;
                case 7 -> payment = 0.04 * loanAmount;
                case 8 -> payment = 0.03 * loanAmount;
                case 9 -> payment = 0.02 * loanAmount;
            }

//            End Payment switch
            while (balance > 0)
            {
                count++;
                paymentNumber++;

//                Transaction dates
                loanMonth++;

                if (loanMonth > 13)
                {
                    loanMonth = 1;
                    loanYear++;
                }

                switch (loanMonth)
                {
                    case 1 -> stringMonth = "Jan";
                    case 2 -> stringMonth = "Feb";
                    case 3 -> stringMonth = "Mar";
                    case 4 -> stringMonth = "Apr";
                    case 5 -> stringMonth = "May";
                    case 6 -> stringMonth = "Jun";
                    case 7 -> stringMonth = "Jul";
                    case 8 -> stringMonth = "Aug";
                    case 9 -> stringMonth = "Sept";
                    case 10 -> stringMonth = "Oct";
                    case 11 -> stringMonth = "Nov";
                    case 12 -> stringMonth = "Dec";
                }

                dueDate = stringMonth + " " + loanDay + "," + loanYear;

//                End transaction dates
                interest = interestCalc(apr, balance);
                principle = principleCalc(payment, interest);
                balance -= principle;

                interestDisplay = Math.round(interest * 100.0) / 100.0;
                principleDisplay = Math.round(principle * 100.0) / 100.0;

                if (Math.round(balance * 100.0) / 100.0 < payment)
                {
                    payment = Math.round(balance * 100.0) / 100.0;
                    balance = payment - Math.round(balance * 100.0) / 100.0;
                }
                balanceDisplay = Math.round(balance * 100.0) / 100.0;

                jta.append("    " + paymentNumber + "\t" + dueDate + "\t\t" + payment + "\t" + interestDisplay + "\t" + principleDisplay + "\t" + balanceDisplay + "\n");

            }

            jta.append("_____________________________________________________________________________________________\n");
            jta.append("\n");

//            End Table
        }
//        End Window Pane
    }

    public static double interestCalc(double apr1, double bal1)
    {
        double interest;
        interest = apr1 / 12 * bal1;
        return interest;
    }

    public static double principleCalc(double pay1, double int1)
    {
        double principle;
        principle = pay1 - int1;
        return principle;
    }

}
