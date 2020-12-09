/**
 * thrown if a vote is cast for a candidate they already voted for
 */
public class DuplicateVotesException extends Exception {

    private String candidate;

    DuplicateVotesException(String candidate) {
        this.candidate = candidate;
    }

    public String getCandidate() {
        return candidate;
    }

    public String getMessage() {
        return "You cannot vote for the same candidate twice!";
    }
}
