import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * handles the ballot and voting information
 */
class ElectionData {
    private LinkedList<String> ballot = new LinkedList<>();
    LinkedList<String> votes = new LinkedList<>();

    private HashMap<String, LinkedList<String>> votesHash = new HashMap<>();

    public LinkedList<String> getBallot() {
        return ballot;
    }

    public HashMap<String, LinkedList<String>> getVotesHash() {
      return votesHash;
  }

    Scanner keyboard = new Scanner(System.in);

  ElectionData() {
    this.ballot.add("Gompei");
    this.ballot.add("Husky");
  }

    /**
     * takes three strings as a choice based vote
     * @param first the voter's first choice vote
     * @param second the voter's second choice vote
     * @param third the voter's third choice vote
     */
    public void processVote(String first, String second, String third) throws DuplicateVotesException, UnknownCandidateException {

        LinkedList<String> givenVotes = new LinkedList<>();
        givenVotes.add(first);
        givenVotes.add(second);
        givenVotes.add(third);

        LinkedList<String> firstList = votesHash.get("first");
        LinkedList<String> secondList = votesHash.get("second");
        LinkedList<String> thirdList = votesHash.get("third");

        for (String c : givenVotes) {
            if (!ballot.contains(c)) {
                throw new UnknownCandidateException(c);
            }
        }
        if (first.equals(second) || first.equals(third)) {
            throw new DuplicateVotesException(first);
        }
        else if (second.equals(third)) {
            throw new DuplicateVotesException(second);
        }
        else {
            if(firstList == null) {
                firstList = new LinkedList<>();
            }
            if(secondList == null) {
                secondList = new LinkedList<>();
            }
            if(thirdList == null) {
                thirdList = new LinkedList<>();
            }

            firstList.add(first);
            secondList.add(second);
            thirdList.add(third);
            votesHash.put("first", firstList);
            votesHash.put("second", secondList);
            votesHash.put("third", thirdList);
        }
    }


    /**
     * determines the winner of the election if the winner got more than 50% of the first choice votes
     * @return the name of the candidate that won or "Runoff required" if no candidate gets above 50%
     */
    public String findWinnerMostFirstVotes() {
        LinkedList<String> firstVotes = votesHash.get("first");
        for(String candidate : ballot) {
            double numOfVotes = 0;
            for(String vote : firstVotes) {
                if(vote.equals(candidate)) {
                    numOfVotes += 1;
                }
            }
            if(numOfVotes / firstVotes.size() > .5) {
                return candidate;
            }
        }
        return "Runoff required";
    }

    /**
     * find the winner of the election based on the point value of all the votes
     *  a first choice vote awards 3 points
     *  a second choice vote awards 2 points
     *  a third choice vote awards 1 point
     *  if there are any ties any one of the three tied candidates will be the winner
     * @return the winner of the election
     */

    public String findWinnerMostPoints() {
        String winner = "place-holder";
        int leadingPoints = 0;
        for(String candidate : ballot) {
            int firstChoiceVotes = countNumVotes("first", candidate);
            int secondChoiceVotes = countNumVotes("second", candidate);
            int thirdChoiceVotes = countNumVotes("third", candidate);

            int total = totalPoints(firstChoiceVotes, secondChoiceVotes, thirdChoiceVotes);
            if(total > leadingPoints) {
                leadingPoints = total;
                winner = candidate;
            }
        }
        return winner;
    }


    /**
     * adds a candidate to the ballot if they don't already exist
     * @param candidate candidate that is to be added to the ballot
     * @throws CandidateExistsException thrown if the candidate already exists on the ballot
     */
    public void addCandidate(String candidate) throws CandidateExistsException {
        if(ballot.contains(candidate)) {
            throw new CandidateExistsException(candidate);
        }
        ballot.add(candidate);
    }

    /**
     * given a rank find out the amount of times a candidate was ranked in that rank
     * @param key the rank of votes of the candidate we are looking for (first, second, third)
     * @param candidate the candidate we are looking for
     * @return the amount of times that the candidate was voted for in a specific rank
     */
    public int countNumVotes(String key, String candidate) {
        LinkedList<String> votesList = this.votesHash.get(key);
        int totalNumVotes = 0;
        for(String vote : votesList) {
            if(vote.equals(candidate)) {
                totalNumVotes ++;
            }
        }
        return totalNumVotes;
    }

    /**
     * given the amount of votes a candidate placed in each of the three ranks, calculate the total amount of points they got
     * @param firstVotes amount of times the candidate was ranked first
     * @param secondVotes amount of times the candidate was ranked second
     * @param thirdVotes amount of times the candidate was ranked third
     * @return the total amount of points earned by a candidate
     */
    public int totalPoints(int firstVotes, int secondVotes, int thirdVotes) {
        return 3 * firstVotes + 2 * secondVotes + thirdVotes;
    }

}
