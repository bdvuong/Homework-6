/**
 * thrown when a candidate that doesn't exist is voted for
 */
public class UnknownCandidateException extends Exception {
    private String candidate;

    UnknownCandidateException(String candidate) {
        this.candidate = candidate;
    }

    public String getCandidate() {
        return candidate;
    }

    public String getMessage() {
        return candidate + " cannot be found!";
    }
}
