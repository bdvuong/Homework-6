import org.junit.Test;

import static org.junit.Assert.*;

/**
 * class that holds examples and jUnit testing
 */
public class Examples {

    ElectionData Setup1 () {

        ElectionData ED = new ElectionData();

        // put candidates on the ballot
        try {

            ED.addCandidate("gompei");
            ED.addCandidate("husky");
            ED.addCandidate("ziggy");

        } catch (Exception e) {}

        // cast votes

        try {

            ED.processVote("gompei", "husky", "ziggy");
            ED.processVote("gompei", "ziggy", "husky");
            ED.processVote("husky", "gompei", "ziggy");

        } catch (Exception e) {}

        return(ED);

    }

    ElectionData Setup2() {
        ElectionData ED2 = new ElectionData();

        try {

            ED2.addCandidate("gompei");
            ED2.addCandidate("husky");
            ED2.addCandidate("ziggy");

        } catch (Exception e) {}

        // cast votes

        try {

            ED2.processVote("gompei", "husky", "ziggy");
            ED2.processVote("gompei", "ziggy", "husky");
            ED2.processVote("husky", "gompei", "ziggy");
            ED2.processVote("husky", "gompei", "ziggy");

        } catch (Exception e) {}

        return(ED2);
    }

    ElectionData Setup3() {
        ElectionData ED3 = new ElectionData();

        try {

            ED3.addCandidate("gompei");
            ED3.addCandidate("husky");
            ED3.addCandidate("ziggy");
        } catch (Exception e) {}

        // cast votes

        try {

            ED3.processVote("gompei", "husky", "ziggy");
            ED3.processVote("gompei", "husky", "ziggy");
            ED3.processVote("husky", "gompei", "ziggy");
            ED3.processVote("husky", "gompei", "ziggy");

        } catch (Exception e) {}

        return(ED3);
    }

    ElectionData DuplicateVotesSetup() {
        ElectionData ExceptionsData = new ElectionData();

        try {
            ExceptionsData.addCandidate("gompei");
            ExceptionsData.addCandidate("husky");
            ExceptionsData.addCandidate("ziggy");

            ExceptionsData.processVote("gompei", "husky", "ziggy");
            ExceptionsData.processVote("gompei", "ziggy", "husky");
            ExceptionsData.processVote("husky", "husky", "ziggy");
            ExceptionsData.processVote("husky", "gompei", "ziggy");
        }
        catch(DuplicateVotesException e) {

        }
        catch(CandidateExistsException e) {}
        catch(UnknownCandidateException e) {}

        return ExceptionsData;
    }

    public static void main(String[] args) {
        VotingMachine vMachine = new VotingMachine();

        try {
            vMachine.getED().addCandidate("gompei");
            vMachine.getED().addCandidate("husky");
            vMachine.getED().addCandidate("ziggy");
        }
        catch(Exception e) {}

        vMachine.screen();
    }

    @Test
    public void testMostFirstWinner1() {
        assertEquals ("gompei", Setup1().findWinnerMostFirstVotes());
    }

    @Test
    public void testMostPointsWinner1() {
        assertEquals("gompei", Setup1().findWinnerMostPoints());
    }

    //tests when a candidate has 50% of the votes
    @Test
    public void test50ExactRunoff() {
        assertEquals("Runoff required", Setup2().findWinnerMostFirstVotes());
    }

    @Test
    public void testMostPointsWinner2() {
        assertEquals("gompei", Setup2().findWinnerMostPoints());
    }

    @Test
    public void equalPointsWinner() {
        assertEquals("gompei", Setup3().findWinnerMostPoints());
    }

    @Test(expected = CandidateExistsException.class)
    public void CandidateExistsExceptionTest() throws CandidateExistsException {
        Setup2().addCandidate("gompei");
    }

    @Test(expected = UnknownCandidateException.class)
    public void voteForUnknownCandidate() throws UnknownCandidateException, DuplicateVotesException {
        Setup2().processVote("falco", "gompei", "husky");
    }

    @Test(expected = DuplicateVotesException.class)
    public void duplicateExceptionTest() throws DuplicateVotesException, UnknownCandidateException {
        Setup2().processVote("gompei", "gompei", "husky");
    }

    //as said in the canvas page, when given both a DuplicatesVotesException and an UnknownCandidateException
    //expected to throw the UnknownCandidateException first
    @Test(expected = UnknownCandidateException.class)
    public void duplicateAndUnknownExceptionTest() throws DuplicateVotesException, UnknownCandidateException {
        Setup2().processVote("iepmog", "iepmog", "husky");
    }
}
