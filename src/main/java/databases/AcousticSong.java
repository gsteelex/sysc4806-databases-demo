package databases;

import javax.persistence.Entity;

@Entity
public class AcousticSong extends Song {



    private String instrument;

    public String getInstrument() {
        return instrument;
    }

    public void setInstrument(String instrument) {
        this.instrument = instrument;
    }
}
