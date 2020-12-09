public class Examples {
    public static void main(String[] args) {

        Exception e2 = new CandidateExistsException("gompei");
        Exception e3 = new DuplicateVotesException("gompei");
        ElectionData ED = new ElectionData();

        //adding an existing candidate
        try {
            ED.addCandidate("Gompei");
        }
        catch(CandidateExistsException e) {
            System.out.println(e.getMessage());
        }

        //adding a new candidate
        try {
            ED.addCandidate("Falco");
            System.out.println(ED.getBallot());
        }
        catch(CandidateExistsException e) {
            System.out.println(e.getMessage());
        }

        //one candidate does not exists
        try {
            ED.processVote("Gompei", "Fox", "Falco");
        }
        catch(UnknownCandidateException e) {
            System.out.println(e.getMessage());
        }
        catch(DuplicateVotesException e) {
            System.out.println(e.getMessage());
        }

        //voting for two of the same candidates
        try {
            ED.processVote("Gompei", "Gompei", "Falco");
        }
        catch(UnknownCandidateException e) {
            System.out.println(e.getMessage());
        }
        catch(DuplicateVotesException e) {
            System.out.println(e.getMessage());
        }

        //proper vote
        try {
            ED.processVote("Gompei", "Husky", "Falco");
            System.out.print(ED.getVotesHash() + "\n");
        }
        catch(UnknownCandidateException e) {
            System.out.println(e.getMessage());
        }
        catch(DuplicateVotesException e) {
            System.out.println(e.getMessage());
        }

        try {
            ED.processVote("Husky", "Gompei", "Falco");
            System.out.print(ED.getVotesHash() + "\n");
        }
        catch(UnknownCandidateException e) {
            System.out.println(e.getMessage());
        }
        catch(DuplicateVotesException e) {
            System.out.println(e.getMessage());
        }

        try {
            ED.processVote("Husky", "Gompei", "Falco");
            System.out.print(ED.getVotesHash() + "\n");
        }
        catch(UnknownCandidateException e) {
            System.out.println(e.getMessage());
        }
        catch(DuplicateVotesException e) {
            System.out.println(e.getMessage());
        }


        System.out.println(ED.findWinnerMostFirstVotes());

        //proper vote
        try {
            ED.processVote("Gompei", "Husky", "Falco");
            System.out.print(ED.getVotesHash() + "\n");
        }
        catch(UnknownCandidateException e) {
            System.out.println(e.getMessage());
        }
        catch(DuplicateVotesException e) {
            System.out.println(e.getMessage());
        }

        System.out.println(ED.findWinnerMostFirstVotes());

        System.out.println(ED.findWinnerMostPoints());

    }

}
