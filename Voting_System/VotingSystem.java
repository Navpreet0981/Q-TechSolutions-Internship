package Voting_System;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class VotingSystem {
    private static Map<String, Boolean> voters = new HashMap<>();
    private static Map<String, Integer> votes = new HashMap<>();

    public static void main(String[] args) {
        // Sample voters and candidates
        voters.put("V123", false);
        voters.put("V456", false);
        votes.put("C1", 0);
        votes.put("C2", 0);

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Voter ID: ");
        String voterId = scanner.nextLine();

        if (!voters.containsKey(voterId)) {
            System.out.println("Invalid Voter ID.");
            return;
        }

        if (voters.get(voterId)) {
            System.out.println("You have already voted.");
            return;
        }

        System.out.println("Candidates: C1, C2");
        System.out.print("Enter Candidate ID to vote for: ");
        String candidateId = scanner.nextLine();

        if (!votes.containsKey(candidateId)) {
            System.out.println("Invalid Candidate ID.");
            return;
        }

        // Cast vote
        votes.put(candidateId, votes.get(candidateId) + 1);
        voters.put(voterId, true);

        System.out.println("Vote cast successfully! Thank you for voting.");
    }
}
