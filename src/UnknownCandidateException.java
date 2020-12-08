/**
 * thrown when a candidate that doesn't exist is voted for
 */
public class UnknownCandidateException {
    String candidate;

    UnknownCandidateException(String candidate) {
        this.candidate = candidate;
    }
}
