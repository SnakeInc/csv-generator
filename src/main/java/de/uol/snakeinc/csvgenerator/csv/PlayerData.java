package de.uol.snakeinc.csvgenerator.csv;

public class PlayerData {

    private String playerName;

    private int games = 0;
    private int first = 0;
    private int second = 0;
    private int third = 0;
    private int fourth = 0;
    private int fifth = 0;
    private int sixth = 0;

    private double trustedValue = 0.0D;

    private int players = 0;

    private double trustScoreEnemies = 0.0D;

    public PlayerData(String playerName) {
        this.playerName = playerName;
    }

    public void addGame() {
        this.games++;
    }

    public void addPosition(int position) {
        switch(position) {
            case 1:
                this.first++; break;
            case 2:
                this.second++; break;
            case 3:
                this.third++; break;
            case 4:
                this.fourth++; break;
            case 5:
                this.fifth++; break;
            case 6:
                this.sixth++; break;
        }
    }

    public void addTrustValue(int position, int players) {
        this.trustedValue += 1.0D - ((position * 1.0D) / (players * 1.0D));
    }

    public void addTrustScoreEnemies(double score) {
        this.trustScoreEnemies += score;
    }

    public double getTrustValue() {
        return this.trustedValue / (this.games * 1.0D);
    }

    public double getTrustScoreEnemies() {
        return this.trustScoreEnemies / (this.games * 1.0D);
    }

    public double getWinLoseRate() {
        return this.first / (this.games * 1.0D);
    }

    public String getName() {
        return this.playerName;
    }

    public void addPlayers(int players) {
        this.players += players;
    }

    public String getCSVString() {
        double scoring = this.getTrustValue();
        double medianPlayers = (this.players * (1.0D)) / (this.games * 1.0D);
        double trustScoreEnemies = this.getTrustScoreEnemies();

        return this.playerName + ";" + this.games + ";" + this.first + ";" + this.second + ";" + this.third + ";" + this.fourth + ";" + this.fifth + ";" + this.sixth + ";" + scoring + ";" + medianPlayers + ";" + trustScoreEnemies;
    }
}
