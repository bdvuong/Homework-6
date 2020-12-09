import java.util.Locale;
import java.util.Scanner;

/**
 * input/output section of the voting process
 */
public class VotingMachine {

    Scanner keyboard = new Scanner(System.in);
    private ElectionData ED;


    VotingMachine() {
        this.ED = new ElectionData();
    }

    public ElectionData getED() {
        return ED;
    }

    /**
     * helper function for screen method, displays all candidates on ballot
     */
    public void printBallot() {
        System.out.println("The candidates are ");
        for (String s : ED.getBallot()) {
            System.out.println(s);
        }
    }

    /**
     * manual user interface for voting
     */
    public void screen() {
        this.printBallot();
        System.out.println("Who is your first choice?");
        System.out.println("Either enter a name, pointWinner, or firstRankWinner");
        String firstCandidate = keyboard.next();
        if (firstCandidate.equals("pointWinner")) {
            System.out.println(ED.findWinnerMostPoints());
        } else if (firstCandidate.equals("firstRankWinner")) {
            System.out.println(ED.findWinnerMostFirstVotes());
        }
        else {
            System.out.println("Who is your second choice?");
            String secondCandidate = keyboard.next();
            System.out.println("Who is your third choice?");
            String thirdCandidate = keyboard.next();
            System.out.println("You voted for " + firstCandidate + " as your first rank");
            System.out.println(secondCandidate + " as your second rank");
            System.out.println("and " + thirdCandidate + " as your third rank");
            try {
                ED.processVote(firstCandidate, secondCandidate, thirdCandidate);
                screen();
            } catch (DuplicateVotesException e) {
                e.getMessage();
                screen();
            } catch (UnknownCandidateException e) {
                System.out.println(e.getCandidate() + " is not a person on the ballot. Would you like to write them in? y/n");
                String response = keyboard.next();
                if (response.equals("y") || response.equals("Y")) {
                    addWriteIn(e.getCandidate());
                }
                screen();
            }
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
