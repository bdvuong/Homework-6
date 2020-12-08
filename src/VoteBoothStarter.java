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
    public void processVote(String first, String second, String third) {
      LinkedList<String> firstList = this.votesHash.get("First");
      firstList.add(first);
      LinkedList<String> secondList = this.votesHash.get("Second");
      secondList.add(second);
      LinkedList<String> thirdList = this.votesHash.get("Third");
      thirdList.add(third);

      votesHash.put("First", firstList);
      votesHash.put("Second", secondList);
      votesHash.put("Third", thirdList);
    }

  }
