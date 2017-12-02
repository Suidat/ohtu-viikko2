package ohtu;

public class TennisGame {

    private int player1_score = 0;
    private int player2_score = 0;
    private String player1Name;
    private String player2Name;

    String[] responses;

    public TennisGame(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
        responseInitialization();
    }

    private void responseInitialization(){
        responses = new String[5] ;
        responses[0] = "Love";
        responses[1] = "Fifteen";
        responses[2] = "Thirty";
        responses[3] = "Forty";

    }

    public void wonPoint(String playerName) {
        if (playerName == "player1")
            player1_score += 1;
        else
            player2_score += 1;
    }

    public String getScore() {
        if (player1_score == player2_score) {
            return equalScoreResponse();
            
        } else if (player1_score >= 4 || player2_score >= 4) {
            return complicatedResponse();
        } else {
            return scoreResponse();
        }
    }

    private String scoreResponse(){
        return responses[player1_score] + "-" + responses[player2_score];

    }

    private String equalScoreResponse(){
        if(player1_score<4)
            return responses[player1_score]+"-All";
        return "Deuce";
    }

    private String complicatedResponse() {
        int minusResult = player1_score - player2_score;
        if (minusResult == 1) return "Advantage player1";
        else if (minusResult == -1) return "Advantage player2";
        else if (minusResult >= 2) return "Win for player1";
        else return "Win for player2";
    }
}