/**
 * thrown if a candidate already exists this exception is thrown
 */
public class CandidateExistsException extends Exception {

    private String candidate;

    CandidateExistsException(String name) {
        this.candidate = name;
    }

    public String getCandidate() {
        return candidate;
    }

    public String getMessage() {
        return candidate + " already exists!";
    }
}
