/**
 * thrown if a candidate already exists this exception is thrown
 */
public class CandidateExistsException extends Exception {

    String candidate;

    CandidateExistsException(String name) {
        this.candidate = name;
    }
}
