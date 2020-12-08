/**
 * thrown if a vote is cast for a candidate they already voted for
 */
public class DuplicateVotesException extends Exception{

    String candidate;

    DuplicateVotesException(String candidate) {
        this.candidate = candidate;
    }
}
