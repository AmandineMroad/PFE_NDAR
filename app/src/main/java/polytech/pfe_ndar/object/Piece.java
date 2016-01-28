package polytech.pfe_ndar.object;

/**
 * PFE_NDAR _ Polytech Marseille _ 2016
 * Nicolas DELRIO, Amandine ROGER, IRM 2016
 */
public class Piece{
    //TODO un identifiant unique

    private final int room;
    private final String name;
    private final String description;


    Piece(int roomNumber, String name, String description){
        this.room = roomNumber;
        this.name = name;
        this.description = description;

    }

    public int getRoom(){
        return room;
    }
    public String getName (){return name;}
    public String getDescription(){return  description;}

}
