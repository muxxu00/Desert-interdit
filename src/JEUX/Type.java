package JEUX;

public enum Type {
    OASIS,OASIS_FAKE,CITE, OEIL,TUNNEL,INDICE,DECOLLAGE, CRASH;

    @Override
    public String toString() {
        if (this == CITE){
            return " CITE ";
        }
        else if (this == OEIL){
            return " OEIL ";
        }else if (this == OASIS){
            return "OASIS";
        }else if (this == OASIS_FAKE){
            return "OASIS FAKE";
        }else if (this == TUNNEL){
            return "TUNNEL";
        }else if (this == DECOLLAGE){
            return "DECOLLAGE";
        }else if (this == INDICE){
            return "INDICE";
        }else if (this == CRASH){
            return "CRASH";
        }
        return "None";
    }
};
