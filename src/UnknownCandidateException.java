/**
 * thrown when a candidate that doesn't exist is voted for
 */
public class UnknownCandidateException extends Exception {
    String candidate;

    UnknownCandidateException(String candidate) {
        this.candidate = candidate;
    }
}
