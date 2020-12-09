import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * input/output section of the voting process
 */
public class VotingMachine {

    Scanner keyboard = new Scanner(System.in);
    ElectionData ED = new ElectionData();

    VotingMachine() {}


    public void printBallot() {
        System.out.println("The candidates are ");
        for (String s : ED.getBallot()) {
            System.out.println(s);
        }
    }

    public void screen() {
        this.printBallot();
        System.out.println("Who is your first choice?\n");
        String firstCandidate = keyboard.next();
        System.out.println("Who is your second choice?\n");
        String secondCandidate = keyboard.next();
        System.out.println("Who is your third choice? \n");
        String thirdCandidate = keyboard.next();
        System.out.println("You voted for " + firstCandidate + " as your first rank \n");
        System.out.println(secondCandidate + " as your second rank \n");
        System.out.println("and " + thirdCandidate + " as your third rank \n");
        try {
            ED.processVote(firstCandidate, secondCandidate, thirdCandidate);
        }
        catch(DuplicateVotesException e) {
            e.getMessage();
        }
        catch(UnknownCandidateException e) {
            System.out.println(e.getCandidate() + " is not a person on the ballot. Would you like to write them in? y/n");
            String response = keyboard.next();
            if(response.toLowerCase().equals("y")) {
               addWriteIn(e.getCandidate());
            }
            screen();
        }
    }


    /**
     * adds a candidate to the ballot as a write-in if thrown an UnknownCandidateException
     * @param WICandidate the write in candidate
     */
    public void addWriteIn(String WICandidate) {
        try {
            ED.addCandidate(WICandidate);
            System.out.println("The candidate was added successfully!");
        }
        catch(CandidateExistsException e) {
            System.out.println(e.getMessage());
        }
    }

}
