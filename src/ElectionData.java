import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

class ElectionData {
  LinkedList<String> ballot = new LinkedList<String>();
  LinkedList<String> votes = new LinkedList<String>();

  HashMap<String, LinkedList<String>> votesHash = new HashMap<>();


  Scanner keyboard = new Scanner(System.in);

  ElectionData() {
    this.ballot.add("Gompei");
    this.ballot.add("Husky");
  }
  
  public void printBallot() {
    System.out.println("The candidates are ");
    for (String s : ballot) {
      System.out.println(s);
    }
  }
  
  public void screen() {
    this.printBallot();
    System.out.println("Who do you want to vote for?");
    String candidate = keyboard.next();
    votes.add(candidate);
    System.out.println("You voted for " + candidate);
  }
  
  public int countVotes(String forcand) {
    int numvotes = 0;
    for (String s : votes) {
      if (s.equals(forcand))
        numvotes = numvotes+1;
    }
    return numvotes;
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

      LinkedList<String> firstList = this.votesHash.get("first");
      LinkedList<String> secondList = this.votesHash.get("second");
      LinkedList<String> thirdList = this.votesHash.get("third");

      for (String c : givenVotes) {
        if (ballot.contains(c)) {
        } else {
          throw new UnknownCandidateException(c);
        }
      }
      if (first == second || first == third) {
          throw new DuplicateVotesException(first);
      }
      else if (second == third) {
        throw new DuplicateVotesException(second);
        }
      else {
          try {
            if(firstList.equals(null)) {
              firstList = new LinkedList<>();
            }
            if(secondList.equals(null)) {
               secondList = new LinkedList<>();
            }
            if(thirdList.equals(null)) {
               thirdList = new LinkedList<>();
            }

            firstList.add(first);
            secondList.add(second);
            thirdList.add(third);
            votesHash.put("first", firstList);
            votesHash.put("second", secondList);
            votesHash.put("third", thirdList);
        }
          catch(NullPointerException e) {
            firstList = new LinkedList<>();
            secondList = new LinkedList<>();
            thirdList = new LinkedList<>();
            firstList.add(first);
            secondList.add(second);
            thirdList.add(third);
            votesHash.put("first", firstList);
            votesHash.put("second", secondList);
            votesHash.put("third", thirdList);
          }
      }
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
}
