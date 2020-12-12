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

    public String getCSVString() {
        return this.playerName + "," + this.games + "," + this.first + "," + this.second + "," + this.third + "," + this.fourth + "," + this.fifth + "," + this.sixth + "\n";
    }
}
