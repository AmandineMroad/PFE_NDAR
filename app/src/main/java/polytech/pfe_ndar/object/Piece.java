package polytech.pfe_ndar.object;

/**
 * PFE_NDAR _ Polytech Marseille _ 2016
 * Nicolas DELRIO, Amandine ROGER, IRM 2016
 */
public class Piece{
    //TODO un identifiant unique

    private final int room;

    Piece(int roomNumber){
        this.room = roomNumber;
    }

    public int getRoom(){
        return room;
    }

}
